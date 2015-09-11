grammar CScript;

options
{
	backtrack = true;
	memoize	= true;
	k = 2;
	//output=AST;
}

@parser::header {
	package ITSExecutor.Compiler;
	import java.io.IOException;
	import java.util.Iterator;
	import ITSConfig.CGlobal;
	import ITSExecutor.Codec.*;
	import ITSExecutor.Compiler.CScript;
}

@lexer::header {
	package ITSExecutor.Compiler;
	import ITSExecutor.Codec.*;
}

@parser::members {
	private String mFilePath = null;
	private CScript mScript = null;
	private CEncoder mEncoder = null;
	private CExeObj mExeObj = null;
	private HashMap<String, Object> mEnv = null;
	private int tagForTrueShort = 0;
	private int tagForFalseShort = 0;
	private CSymbolAPI FetchAPI(String Name) {
		CSymbolAPI Api = mScript.GetAPI(Name);
		if (Api == null) {
			throw new CException(IErrCode.ERR_UNDEFINED_SYMBOL, Name);
		}
		if (Api.IsAPI() == false) {
			throw new CException(IErrCode.ERR_SYMBOL_ISNOTSYSCALL, Name);
		}
		return Api;
	}

	private String FetchKey(String Key) {
		if (Key.startsWith("\"") && Key.endsWith("\"")) {
			return Key.substring(1, Key.length()-1);
		}
		return Key;
	}

	private void SyscallArgTypeCheck(CExpression ArgExpr, int Type, Token Tok) {
		if (!ArgExpr.TypeMatch(Type)) {
			throw new CException(IErrCode.ERR_SYSCALLARG_NOTMATCH, mFilePath, Tok.getLine(), Tok.getText());
		}
	}

	public static void Parse(String FileName, ISysTbl SysTbl, CEncoder Encoder, CExeObj ExeObj, HashMap<String, Object> Env, CPreObj preObj) throws RecognitionException, IOException {
		CCodeSection MainCodeSection = ExeObj.GetMainCodeSection();
		MainCodeSection.Open('W');
		Encoder.SetCodeSection(MainCodeSection);

		CDebugSection MainDebugSection = ExeObj.GetMainDebugSection();
		Encoder.SetDebugSection(MainDebugSection);

		CScriptLexer Lexer = new CScriptLexer(new ANTLRFileStream(FileName));
		CommonTokenStream Tokens = new CommonTokenStream(Lexer);
		CScriptParser Parser = new CScriptParser(Tokens);
		Parser.script(FileName, new CScript(FileName, SysTbl), Encoder, ExeObj, Env, preObj);

		MainCodeSection.Close();
    }

/*
	@Override
	public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
		throw e;
	}

	@Override
	protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
		throw new MismatchedTokenException(ttype, input);
	}
*/
	@Override
	public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
		String Hdr = getErrorHeader(e);
		String Msg = getErrorMessage(e, tokenNames);
    	throw new CException(IErrCode.ERR_UNEXPECTED_TOKEN, Hdr + " " + Msg);
	}
}

@lexer::members {
/*
	@Override
	public void reportError(RecognitionException e) {
		throw new RuntimeException(e);
	}
*/
	@Override
	public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
    		String[] Words = getErrorMessage(e, tokenNames).split(" ");
    		throw new CException(IErrCode.ERR_UNEXPECTED_TOKEN, getErrorHeader(e)+Words[Words.length-1]);
	}
}


/*
@rulecatch {
	catch (RecognitionException e) {
		throw e;
	}
}
*/

@parser::init {
	
}

include_cmd : 
	Name=INC_LITERAL
	{
		String fileName = String.valueOf($Name.text);
		int beginIndex = fileName.indexOf("\"");
		fileName = fileName.substring(beginIndex + 1, fileName.length() - 1);
		CCompiler.Include(fileName, mScript, mEncoder, mExeObj, mEnv);
	}
	;

var_type_def returns [int Type] : 
	'bool' {$Type = ICodec.TYPE_BOOL;}
	| 'char' {$Type = ICodec.TYPE_CHAR;}
	| 'int' {$Type = ICodec.TYPE_INT;}
	| 'string' {$Type = ICodec.TYPE_STRING;}
	| 'float' {$Type = ICodec.TYPE_FLOAT;}
	;

func_type_def returns [int Type] : 
	'bool' {$Type = ICodec.TYPE_BOOL;}
	| 'char' {$Type = ICodec.TYPE_CHAR;}
	| 'int' {$Type = ICodec.TYPE_INT;}
	| 'string' {$Type = ICodec.TYPE_STRING;}
	| 'float' {$Type = ICodec.TYPE_FLOAT;}
	| 'void'{$Type = ICodec.TYPE_VOID;}
	;

global_def[CScope Scope, CStatement ThisStat]
options {k=1;}
	: ( func_type_def IDENTIFIER '(' )=> function_def[$Scope]
	| variable_def[$Scope, ThisStat]
	;

unarray_var_def[CScope Scope, int Type, CStatement ThisStat]
@init {
	CSymbolVARIABLE VarSymbol = null;
	CExpression NewExpr = null;
}
	:
	VarName=IDENTIFIER
	{
		if ($Scope.IsGlobal()) {
			VarSymbol = $Scope.AddField($VarName.text, $Type, 0);
			mEncoder.SETFIELDINFO($VarName.text, VarSymbol.GetID());
		} else {
			VarSymbol = $Scope.AddLocal($VarName.text, $Type, 0);
			mEncoder.SETLOCALINFO($VarName.text, VarSymbol.GetLoc());
		}
	}
	(
		options {k=1; backtrack=false;}: '=' 
		{
			NewExpr = new CExpression();
			mEncoder.SETLINEINFO(mFilePath, $VarName.line, mEncoder.GetCodeOffset());
		}
		expression[$Scope, NewExpr, ThisStat]
		{
			if (!NewExpr.TypeMatch($Type)) {
				throw new CException(IErrCode.ERR_VARINIT_TYPEMISMATCH);
			}
			if ($Scope.IsGlobal()) {
				mEncoder.PUTFIELD(COperand.ValueOf(VarSymbol));
			} else {
				mEncoder.STORE(COperand.ValueOf(VarSymbol));
			}
		}
	)?
	;
	catch[CException E] {
		E.SetFile(mFilePath);
		E.SetLine($VarName.getLine());
		throw E;
	}

array_var_def[CScope Scope, int Type, CStatement ThisStat]
@init {
	CSymbolVARIABLE VarSymbol = null;
	CExpression NewExpr = null;
	int Idx = 0;
	int Dim = 0;
}
	:
	VarName=IDENTIFIER
	(
		'[' Size=DECIMAL_LITERAL ']' 
		{
			mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_INT, 0, Integer.decode($Size.text)));
			Dim++;
		}
	)+
	{
		if ($Scope.IsGlobal()) {
			VarSymbol = $Scope.AddField($VarName.text, $Type | ICodec.TYPE_ARRAY, Dim);
			mEncoder.SETFIELDINFO($VarName.text, VarSymbol.GetID());
			mEncoder.NEWARRAY($Type | ICodec.TYPE_ARRAY, Dim);
			mEncoder.PUTFIELD(COperand.ValueOf(VarSymbol));
		} else {
			VarSymbol = $Scope.AddLocal($VarName.text, $Type | ICodec.TYPE_ARRAY, Dim);
			mEncoder.SETLOCALINFO($VarName.text, VarSymbol.GetLoc());
			mEncoder.NEWARRAY($Type | ICodec.TYPE_ARRAY, Dim);
			mEncoder.STORE(COperand.ValueOf(VarSymbol));
		}
	}
	(
		options {k=1; backtrack=false;}: '=' '{' 
		{
			if (VarSymbol.IsMultiArray()) {
				new CException(IErrCode.ERR_MULTIARRAY_INITFORBIT);
			}
			mEncoder.SETLINEINFO(mFilePath, $VarName.line, mEncoder.GetCodeOffset());
			if ($Scope.IsGlobal()) {
				mEncoder.GETFIELD(COperand.ValueOf(VarSymbol));
			} else {
				mEncoder.LOAD(COperand.ValueOf(VarSymbol));
			}
			Idx = 0;
			COperand IdxOperand = new COperand(ICodec.CLASS_CONST, ICodec.TYPE_INT, 0, Integer.valueOf(Idx));
			mEncoder.PUSH(IdxOperand);
		} 
		{NewExpr = new CExpression();} expression[$Scope, NewExpr, ThisStat] 
		{
			if (!NewExpr.TypeMatch($Type)) {
				throw new CException(IErrCode.ERR_VARINIT_TYPEMISMATCH);
			}
			mEncoder.ASTORE();
		}
		(
			',' 
			{
				if ($Scope.IsGlobal()) {
					mEncoder.GETFIELD(COperand.ValueOf(VarSymbol));
				} else {
					mEncoder.LOAD(COperand.ValueOf(VarSymbol));
				}
				Idx++;
				COperand IdxOperand = new COperand(ICodec.CLASS_CONST, ICodec.TYPE_INT, 0, Integer.valueOf(Idx));
				mEncoder.PUSH(IdxOperand);
			}
			{NewExpr = new CExpression();} expression[$Scope, NewExpr, ThisStat]
			{
				if (!NewExpr.TypeMatch($Type)) {
					throw new CException(IErrCode.ERR_VARINIT_TYPEMISMATCH);
				}
				mEncoder.ASTORE();
			}
		)*
		'}'
	)?
	;
	catch[CException E] {
		E.SetFile(mFilePath);
		E.SetLine($VarName.getLine());
		throw E;
	}

variable_def[CScope Scope, CStatement ThisStat, CStatement ThisStat]
@init {
	int VarType;
}
	: 
	Type=var_type_def
	(
		unarray_var_def[$Scope, $Type.Type, ThisStat]
		| array_var_def[$Scope, $Type.Type, ThisStat]
	)
	(
		','
		(
			unarray_var_def[$Scope, $Type.Type, ThisStat]
			| array_var_def[$Scope, $Type.Type, ThisStat]
		)
	)*
	;

function_def [CScope Scope]
@init {
	CCodeSection CurCodeSection = null;
	CCodeSection Entry = null;
	CDebugSection CurDebugSection = null;
	CDebugSection NewDebugSection = null;
	CScope NewScope = null;
	CSymbolMETHOD Method = null;
	CStatement CompoundStat = null;
}
	: 
	Type=func_type_def MethodName=IDENTIFIER 
	'('
	{
		CurCodeSection = mEncoder.GetCodeSection();
		Entry = mExeObj.AddCodeSection($MethodName.text);
		Entry.Open('W');
		mEncoder.SetCodeSection(Entry);

		CurDebugSection = mEncoder.GetDebugSection();
		NewDebugSection = mExeObj.AddDebugSection($MethodName.text);
		mEncoder.SetDebugSection(NewDebugSection);

		NewScope = mScript.EnterScope(CScope.SCOPE_FUNCTION);
		Method = mScript.GetMethod($MethodName.text);
		Method.SetArgPropertys(new ArrayList<CProperty>());
		Method.SetEntry(Entry);
	}
	param_list[NewScope, Method]?
	')'
	{CompoundStat = new CStatement();} compound_statement[NewScope, CompoundStat]
	{
		Method.SetLocalNum(NewScope.LastLoc() - Method.GetArgNum());
		CompoundStat.ExpectAchieve("GOTO_LABEL", NewScope);
		CompoundStat.ExpectAchieve("FUNC_RETURN", CProperty.ValueOf($Type.Type, 0));
		ArrayList<CExpect> Expects = CompoundStat.GetUnachieveExpects();
		if (!Expects.isEmpty()) {
			for (Iterator<CExpect> It = Expects.iterator(); It.hasNext();) {
				CExpect Expect = It.next();
				if (Expect.Meet("GOTO_LABEL")) {
					throw new CException(IErrCode.ERR_UNDEFINED_LABEL, mScript.GetFileName(), Expect.GetLine(), ((CExpectLabel)Expect).GetLabel());
				} else if (Expect.Meet("FUNC_RETURN")) {
					throw new CException(IErrCode.ERR_RETURN_TYPEMISMATCH, mScript.GetFileName(), Expect.GetLine());
				} else {
					throw new CException(IErrCode.ERR_UNDEFINED_SYMBOL, mScript.GetFileName(), Expect.GetLine(), Expect.GetName());
				}
			}
		}
		switch ($Type.Type) {
		case ICodec.TYPE_VOID: mEncoder.RETURN(); break;
		case ICodec.TYPE_BOOL: mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_BOOL, 0, Boolean.valueOf(false))); mEncoder.VRETURN(); break;
		case ICodec.TYPE_CHAR: mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_CHAR, 0, Character.valueOf((char)0))); mEncoder.VRETURN(); break;
		case ICodec.TYPE_INT: mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_INT, 0, Integer.valueOf(0))); mEncoder.VRETURN(); break;
		case ICodec.TYPE_STRING:
			{
				CSymbolCONST ConstSymbol = mScript.AddConst("", new String(""));
				mEncoder.LDC(COperand.ValueOf(ConstSymbol));
				mEncoder.VRETURN();
				break;
			}
		case ICodec.TYPE_FLOAT:
			{
				CSymbolCONST ConstSymbol = mScript.AddConst("0.0", new Float(0.0));
				mEncoder.LDC(COperand.ValueOf(ConstSymbol));
				mEncoder.VRETURN();
				break;
			}
		}
		mScript.ExitScope(mFilePath);
		Entry.Close();
		mEncoder.SetCodeSection(CurCodeSection);
		mEncoder.SetDebugSection(CurDebugSection);
	}
	;

param_list[CScope Scope, CSymbolMETHOD Method]
	: 
	Arg=param_def[$Scope] {$Method.AddArgProperty($Arg.ArgVar);}
	( ',' Arg=param_def[$Scope] {$Method.AddArgProperty($Arg.ArgVar);} )*
	;

param_def[CScope Scope] returns [CSymbolVARIABLE ArgVar]
@init {
	int Dim = 0;
}
	: 
	VarType=var_type_def
	(
		'[' ']'
		{
			$VarType.Type |= ICodec.TYPE_ARRAY;
			Dim++;
		}
	)*
	VarName=IDENTIFIER
	{
		$ArgVar = $Scope.AddLocal($VarName.text, $VarType.Type, Dim);
		mEncoder.SETLOCALINFO($VarName.text, $ArgVar.GetLoc());
	}
	;

expression[CScope Scope, CExpression ThisExpr, CStatement ThisStat]
	: 
	logical_or_expression[$Scope, $ThisExpr, ThisStat]
	;

assignment_expression[CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init {
	CExpression PrimaryExpr = null;
	CExpression AssignExpr = null;
}
@after {
	if (PrimaryExpr.IsArray()) {
		mEncoder.ASTORE();
	} else {
		if (PrimaryExpr.IsField()) {
			mEncoder.PUTFIELD(PrimaryExpr);
		} else if (PrimaryExpr.IsLocal()) {
			mEncoder.STORE(PrimaryExpr);
		} else {
			throw new CException(IErrCode.ERR_EXPRASSIGN_INVALIDLVALUE, mFilePath, $Left.start.getLine());
		}
	}
	$ThisExpr.SetClass(ICodec.CLASS_VOLATILE);
	$ThisExpr.SetType(PrimaryExpr.GetType() & ~ICodec.TYPE_ARRAY);
}
	: 
	{PrimaryExpr = new CExpression(true);}
	Left=primary_expression[$Scope, PrimaryExpr, ThisStat] 
	{
		if ((PrimaryExpr.IsField() == false) && (PrimaryExpr.IsLocal() == false)) {
			throw new CException(IErrCode.ERR_EXPRASSIGN_INVALIDLVALUE, mFilePath, $Left.start.getLine());
		}
	}
	(
		(
			options {k=1; backtrack=false;}: '=' {AssignExpr = new CExpression();} expression[$Scope, AssignExpr, ThisStat]
			{
				if (!PrimaryExpr.VarAttrMatch(AssignExpr)) {
					throw new CException(IErrCode.ERR_EXPRASSIGN_TYPEMISMATCH, mFilePath, $Left.start.getLine(), PrimaryExpr.Type2String()+" - "+AssignExpr.Type2String());
				}
			}
		)
		|
		(
			options {k=1; backtrack=false;}: '++'
			{
				if (!PrimaryExpr.TypeMatch(ICodec.TYPE_INT | ICodec.TYPE_FLOAT)) {
					throw new CException(IErrCode.ERR_INVALID_OPERANDTYPE, mFilePath, $Left.start.getLine(), PrimaryExpr.Type2String());
				}
				if (PrimaryExpr.IsArray()) {
					mEncoder.DUP(2);
					mEncoder.ALOAD();
				} else {
					if (PrimaryExpr.IsField()) {
						mEncoder.GETFIELD(PrimaryExpr);
					} else if (PrimaryExpr.IsLocal()) {
						mEncoder.LOAD(PrimaryExpr);
					} else {
						throw new CException(IErrCode.ERR_INVALID_OPERAND, mFilePath, $Left.start.getLine());
					}
				}
				mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_INT, 0, Integer.valueOf(1)));
				mEncoder.ADD();
			}
		)
		|
		(
			options {k=1; backtrack=false;}: '--'
			{
				if (!PrimaryExpr.TypeMatch(ICodec.TYPE_INT | ICodec.TYPE_FLOAT)) {
					throw new CException(IErrCode.ERR_INVALID_OPERANDTYPE, mFilePath, $Left.start.getLine(), PrimaryExpr.Type2String());
				}
				if (PrimaryExpr.IsArray()) {
					mEncoder.DUP(2);
					mEncoder.ALOAD();
				} else {
					if (PrimaryExpr.IsField()) {
						mEncoder.GETFIELD(PrimaryExpr);
					} else if (PrimaryExpr.IsLocal()) {
						mEncoder.LOAD(PrimaryExpr);
					} else {
						throw new CException(IErrCode.ERR_INVALID_OPERAND, mFilePath, $Left.start.getLine());
					}
				}
				mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_INT, 0, Integer.valueOf(1)));
				mEncoder.SUB();
			}
		)
	)
	;


logical_or_expression [CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init {
	CExpression AndExprL = null;
	CExpression AndExprR = null;
	int tag = 0;
}
	: 
	{AndExprL = new CExpression();} Left=logical_and_expression[$Scope, AndExprL, ThisStat] {$ThisExpr.SetProperty(AndExprL);}
	(
		options {k=1; backtrack=false;}: '||' 
		{
			AndExprR = new CExpression();
			if(tag == 0)
				tag = ++tagForTrueShort;
			$ThisStat.AddExpect(new CExpectAddrFix("TRUE_SHORT" + tag, mEncoder.GetCodeSection(), mEncoder.GetCodeOffset()+1));
			mEncoder.TRUESHORT(0xFFFFFFFF);
		}
		logical_and_expression[$Scope, AndExprR, ThisStat]
		{
			if ((AndExprR.IsBool() == false) || (AndExprL.IsBool() == false)) {
				throw new CException(IErrCode.ERR_EXPROR_TYPEMISMATCH, mFilePath, $Left.start.getLine());
			}
			mEncoder.OR();
			$ThisExpr.SetClass(ICodec.CLASS_VOLATILE);
			$ThisExpr.SetType(ICodec.TYPE_BOOL);
		}
	)*
	{
		if(tag > 0)
			$ThisStat.ExpectAchieve("TRUE_SHORT" + tag, new Integer(mEncoder.GetCodeOffset()));
	}
	;

logical_and_expression [CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init {
	CExpression EqExprL = null;
	CExpression EqExprR = null;
	int tag = 0;
}
	: 
	{EqExprL = new CExpression();} Left=equality_expression[$Scope, EqExprL, ThisStat] {$ThisExpr.SetProperty(EqExprL);}
	(
		options {k=1; backtrack=false;}: '&&' 
		{
			EqExprR = new CExpression();
			if(tag == 0)
				tag = ++tagForFalseShort;
			$ThisStat.AddExpect(new CExpectAddrFix("FALSE_SHORT" + tag, mEncoder.GetCodeSection(), mEncoder.GetCodeOffset()+1));
			mEncoder.FALSESHORT(0xFFFFFFFF);
		} 
		equality_expression[$Scope, EqExprR, ThisStat]
		{
			if ((EqExprR.IsBool() == false) || (EqExprL.IsBool() == false)) {
				throw new CException(IErrCode.ERR_EXPRAND_TYPEMISMATCH, mFilePath, $Left.start.getLine());
			}
			mEncoder.AND();
			$ThisExpr.SetClass(ICodec.CLASS_VOLATILE);
			$ThisExpr.SetType(ICodec.TYPE_BOOL);
		}
	)*
	{	
		if(tag > 0 )
			$ThisStat.ExpectAchieve("FALSE_SHORT" + tag, new Integer(mEncoder.GetCodeOffset()));
	}
	;

equality_expression[CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init {
	byte Op = ICodec.INSTRUCTION_INVALID;
	CExpression ReExprL = null;
	CExpression ReExprR = null;
}
	: 
	{ReExprL = new CExpression();} Left=relational_expression[$Scope, ReExprL, ThisStat] {$ThisExpr.SetProperty(ReExprL);}
	(
		options {k=1; backtrack=false;}: ( '==' {Op=ICodec.INSTRUCTION_CMPEQ;} | '!=' {Op=ICodec.INSTRUCTION_CMPNE;} ) {ReExprR = new CExpression();} relational_expression[$Scope, ReExprR, ThisStat]
		{
			if (CExpression.IsComputeFeasible(Op, ReExprL, ReExprR) == false) {
				throw new CException(IErrCode.ERR_ILLEGAL_EXPRCOMPUTE, mFilePath, $Left.start.getLine());
			}
			switch(Op) {
			case ICodec.INSTRUCTION_CMPEQ: mEncoder.CMPEQ(); break;
			case ICodec.INSTRUCTION_CMPNE: mEncoder.CMPNE(); break;
			default: throw new CException(IErrCode.ERR_UNKNOWN_ERROR, mFilePath, $Left.start.getLine());
			}
			$ThisExpr.SetClass(ICodec.CLASS_VOLATILE);
			$ThisExpr.SetType(ICodec.TYPE_BOOL);
		}
	)*
	;

relational_expression[CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init {
	byte Op = ICodec.INSTRUCTION_INVALID;
	CExpression AddExprL = null;
	CExpression AddExprR = null;
}
	: 
	{AddExprL = new CExpression();} Left=additive_expression[$Scope, AddExprL, ThisStat] {$ThisExpr.SetProperty(AddExprL);}
	(
		options {k=1; backtrack=false;}: ( '<' {Op=ICodec.INSTRUCTION_CMPLT;} | '>' {Op=ICodec.INSTRUCTION_CMPGT;} | '<=' {Op=ICodec.INSTRUCTION_CMPLE;} | '>=' {Op=ICodec.INSTRUCTION_CMPGE;} ) 
		{AddExprR = new CExpression();} additive_expression[$Scope, AddExprR, ThisStat]
		{
			if (CExpression.IsComputeFeasible(Op, AddExprL, AddExprR) == false) {
				throw new CException(IErrCode.ERR_ILLEGAL_EXPRCOMPUTE, mFilePath, $Left.start.getLine());
			}
			switch(Op) {
			case ICodec.INSTRUCTION_CMPGE: mEncoder.CMPGE(); break;
			case ICodec.INSTRUCTION_CMPLE: mEncoder.CMPLE(); break;
			case ICodec.INSTRUCTION_CMPGT: mEncoder.CMPGT(); break;
			case ICodec.INSTRUCTION_CMPLT: mEncoder.CMPLT(); break;
			default: throw new CException(IErrCode.ERR_UNKNOWN_ERROR, mFilePath, $Left.start.getLine());
			}
			$ThisExpr.SetClass(ICodec.CLASS_VOLATILE);
			$ThisExpr.SetType(ICodec.TYPE_BOOL);
		}
	)*
	;

additive_expression[CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init {
	byte Op = ICodec.INSTRUCTION_INVALID;
	CExpression MultiExprL = null;
	CExpression MultiExprR = null;
}
	:
	{MultiExprL = new CExpression();} Left=multiplicative_expression[$Scope, MultiExprL, ThisStat] {$ThisExpr.SetProperty(MultiExprL);}
	(
		options {k=1; backtrack=false;}: ( '+' {Op=ICodec.INSTRUCTION_ADD;} | '-' {Op=ICodec.INSTRUCTION_SUB;} )
		{MultiExprR = new CExpression();} multiplicative_expression[$Scope, MultiExprR, ThisStat]
		{
			if (CExpression.IsComputeFeasible(Op, MultiExprL, MultiExprR) == false) {
				throw new CException(IErrCode.ERR_ILLEGAL_EXPRCOMPUTE, mFilePath, $Left.start.getLine());
			}
			switch(Op) {
			case ICodec.INSTRUCTION_ADD: mEncoder.ADD(); break;
			case ICodec.INSTRUCTION_SUB: mEncoder.SUB(); break;
			default: throw new CException(IErrCode.ERR_UNKNOWN_ERROR, mFilePath, $Left.start.getLine());
			}
			$ThisExpr.SetClass(ICodec.CLASS_VOLATILE);
			$ThisExpr.SetComputeResultType(Op, $ThisExpr, MultiExprR);
		}
	)*
	;

multiplicative_expression[CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init {
	byte Op = ICodec.INSTRUCTION_INVALID;
	CExpression PrimaryExprL = null;
	CExpression PrimaryExprR = null;
}
	: 
	{PrimaryExprL = new CExpression();} Left=negative_expression[$Scope, PrimaryExprL, ThisStat] {$ThisExpr.SetProperty(PrimaryExprL);}
	(
		options {k=1; backtrack=false;}: ( '*' {Op=ICodec.INSTRUCTION_MUL;} | '/' {Op=ICodec.INSTRUCTION_DIV;} )
		{PrimaryExprR = new CExpression();} negative_expression[$Scope, PrimaryExprR, ThisStat]
		{
			if (CExpression.IsComputeFeasible(Op, PrimaryExprL, PrimaryExprR) == false) {
				throw new CException(IErrCode.ERR_ILLEGAL_EXPRCOMPUTE, mFilePath, $Left.start.getLine());
			}
			switch(Op) {
			case ICodec.INSTRUCTION_MUL: mEncoder.MUL(); break;
			case ICodec.INSTRUCTION_DIV: mEncoder.DIV(); break;
			default: throw new CException(IErrCode.ERR_UNKNOWN_ERROR, mFilePath, $Left.start.getLine());
			}
			$ThisExpr.SetClass(ICodec.CLASS_VOLATILE);
			$ThisExpr.SetComputeResultType(Op, $ThisExpr, PrimaryExprR);
		}
	)*
	;

negative_expression[CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init {
	byte Op = ICodec.INSTRUCTION_INVALID;
	CExpression PrimaryExprL = null;
	boolean negative = false;
}
	: 
	(
		options {k=1; backtrack=false;}: ( '-' {negative = true;} )
	)?
	{PrimaryExprL = new CExpression();} Left=primary_expression[$Scope, PrimaryExprL, ThisStat] {$ThisExpr.SetProperty(PrimaryExprL);}
	{
		if (negative) {
			if (CExpression.CanNegative(PrimaryExprL) == false) {
				throw new CException(IErrCode.ERR_ILLEGAL_EXPRCOMPUTE, mFilePath, $Left.start.getLine());
			}
			mEncoder.NEG();
			$ThisExpr.SetClass(ICodec.CLASS_VOLATILE);
			$ThisExpr.SetComputeResultType(Op, $ThisExpr, null);
		}
	}
	;


argument_list[CScope Scope, ArrayList<CProperty> ArgPropertys, CStatement ThisStat]
@init {
	CExpression ArgExpr = null;
}
	:
	{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {$ArgPropertys.add(ArgExpr);}
	( ',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {$ArgPropertys.add(ArgExpr);} )* 
	;

array_var[CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init{
	CSymbolVARIABLE ArrayVarSymbol = null;
	CExpression IdxExpr = null;
}
	:
	ID=IDENTIFIER
	{
		ArrayVarSymbol = mScript.GetVariable($ID.text);
		if (ArrayVarSymbol == null) {
			throw new CException(IErrCode.ERR_UNDEFINED_SYMBOL, mFilePath, $ID.getLine(), $ID.text);
		}
		if (ArrayVarSymbol.IsArray() == false) {
			throw new CException(IErrCode.ERR_SYMBOL_ISNOTARRAY, mFilePath, $ID.getLine(), $ID.text);
		}
		if (ArrayVarSymbol.IsField()) {
			mEncoder.GETFIELD(COperand.ValueOf(ArrayVarSymbol));
		} else if (ArrayVarSymbol.IsLocal()) {
			mEncoder.LOAD(COperand.ValueOf(ArrayVarSymbol));
		} else {
			throw new CException(IErrCode.ERR_INVALID_OPERAND, mFilePath, $ID.getLine());
		}
		$ThisExpr.SetFields(ArrayVarSymbol);
	}
	(
		'[' {IdxExpr = new CExpression();} expression[$Scope, IdxExpr, ThisStat] ']'
		{
			$ThisExpr.DimDec();
			if (IdxExpr.VarAttrMatch(CProperty.ValueOf(ICodec.TYPE_INT, 0)) == false) {
				throw new CException(IErrCode.ERR_INVALID_ARRAYINDEX, mFilePath, $ID.getLine());
			}
			if (($ThisExpr.IsLeft() == false) || ($ThisExpr.IsVector())) {
				mEncoder.ALOAD();
			}
		}
	)+
	;

function_call[CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init{
	CSymbolMETHOD Method = null;
	CExpression ArgExpr = null;
	ArrayList<CProperty> ArgPropertys = null;
	ArrayList<CProperty> PushArgPropertys = new ArrayList<CProperty>();
}
	:
	ID=IDENTIFIER
	'('
	{
		Method = mScript.GetMethod($ID.text);
		if (Method == null) {
			throw new CException(IErrCode.ERR_UNDEFINED_SYMBOL, mFilePath, $ID.getLine(), $ID.text);
		}
		if (Method.IsMethod() == false) {
			throw new CException(IErrCode.ERR_SYMBOL_ISNOTFUNCTION, mFilePath, $ID.getLine(), $ID.text);
		}
	}
	(
		ArgList=argument_list[$Scope, PushArgPropertys, ThisStat]
	)?
	')'
	{
		ArgPropertys = Method.GetArgPropertys();
		if (((ArgPropertys == null) && (PushArgPropertys != null)) || ((ArgPropertys != null) && (PushArgPropertys == null))) {
			throw new CException(IErrCode.ERR_METHODARG_INVALID, mFilePath, $ID.getLine(), $ID.text);
		} else if ((ArgPropertys != null) && (PushArgPropertys != null)) {
			if (ArgPropertys.size() != PushArgPropertys.size()) {
				throw new CException(IErrCode.ERR_METHODARG_INVALID, mFilePath, $ID.getLine(), $ID.text);
			} else {
				for (int Idx = 0; Idx < ArgPropertys.size(); Idx++) {
					CProperty ArgProperty = ArgPropertys.get(Idx);
					CProperty PushArgProperty = PushArgPropertys.get(Idx);
					if (!ArgProperty.TypeMatch(PushArgProperty) || !ArgProperty.DimEqual(PushArgProperty)) {
						String Suffix = new String($ID.text+" (");
						Suffix += ArgPropertys.get(0).Type2String();
						for (int I = 1; I < ArgPropertys.size(); I++) {
							Suffix += ("," + ArgPropertys.get(I).Type2String());
						}
						Suffix += " )";
						throw new CException(IErrCode.ERR_METHODARG_NOTMATCH, mFilePath, $ID.getLine(), Suffix);
					}
				}
			}
		}
		$ThisExpr.SetFields(Method);
		mEncoder.INVOKE(COperand.ValueOf(Method));
	}
	;

unarray_var[CScope Scope, CExpression ThisExpr]
@init {
	CSymbolVARIABLE UnarrayVarSymbol = null;
}
	: ID=IDENTIFIER
	{
		UnarrayVarSymbol = mScript.GetVariable($ID.text);
		if (UnarrayVarSymbol == null) {
			throw new CException(IErrCode.ERR_UNDEFINED_SYMBOL, mFilePath, $ID.getLine(), $ID.text);
		}
		if ($ThisExpr.IsLeft() && UnarrayVarSymbol.IsArray()) {
			throw new CException(IErrCode.ERR_SYMBOL_ISARRAY, mFilePath, $ID.getLine(), $ID.text);
		}
		if ($ThisExpr.IsLeft() == false) {
			if (UnarrayVarSymbol.IsField()) {
				mEncoder.GETFIELD(COperand.ValueOf(UnarrayVarSymbol));
			} else if (UnarrayVarSymbol.IsLocal()) {
				mEncoder.LOAD(COperand.ValueOf(UnarrayVarSymbol));
			} else {
				throw new CException(IErrCode.ERR_INVALID_OPERAND, mFilePath, $ID.getLine());
			}
		}
		$ThisExpr.SetFields(UnarrayVarSymbol);
	}
	;

primary_expression[CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init {
	CSymbolCONST ConstSymbol = null;
}
	: 
	(IDENTIFIER '[')=>array_var[$Scope, $ThisExpr, ThisStat]
	| (IDENTIFIER '(')=>function_call[$Scope, $ThisExpr, ThisStat]
	| (IDENTIFIER)=>unarray_var[$Scope, $ThisExpr]
	| (
		Text=constant[$Scope, $ThisExpr]
		{
			if ($ThisExpr.IsString() || $ThisExpr.IsFloat()) {
				ConstSymbol = mScript.AddConst($Text.text, $ThisExpr.GetValue());
				mEncoder.LDC(COperand.ValueOf(ConstSymbol));
			} else {
				mEncoder.PUSH($ThisExpr);
			}
		}
	)
	| '(' expression[$Scope, $ThisExpr, ThisStat] ')'
	| api_expression[$Scope, $ThisExpr, ThisStat]
	| (
		'%' ID=DECIMAL_LITERAL
		{
			COperand ArgID = new COperand(ICodec.CLASS_CONST, ICodec.TYPE_INT, 0, Integer.decode($ID.text));
			mEncoder.GETARG(ArgID);
			$ThisExpr.SetClass(ICodec.CLASS_CONST);
			$ThisExpr.SetType(ICodec.TYPE_STRING);
		}
	)
	| (
		'sizeof' '(' ID=IDENTIFIER ')'
		{
			CSymbolVARIABLE ArrayVarSymbol = mScript.GetVariable($ID.text);
			if (ArrayVarSymbol == null) {
				throw new CException(IErrCode.ERR_UNDEFINED_SYMBOL, mFilePath, $ID.getLine(), $ID.text);
			}
			if (ArrayVarSymbol.IsArray() == false) {
				throw new CException(IErrCode.ERR_SYMBOL_ISNOTARRAY, mFilePath, $ID.getLine(), $ID.text);
			}
			if (ArrayVarSymbol.IsField()) {
				mEncoder.GETFIELD(COperand.ValueOf(ArrayVarSymbol));
			} else if (ArrayVarSymbol.IsLocal()) {
				mEncoder.LOAD(COperand.ValueOf(ArrayVarSymbol));
			} else {
				throw new CException(IErrCode.ERR_INVALID_OPERAND, mFilePath, $ID.getLine());
			}
			mEncoder.ARRAYLENGTH();
			$ThisExpr.SetClass(ICodec.CLASS_CONST);
			$ThisExpr.SetType(ICodec.TYPE_INT);
		}
	)
	;

constant[CScope Scope, CExpression ThisExpr]
	:
	Value=HEX_LITERAL {
		if($Value.text.length()>10)
			throw new NumberFormatException();
		$ThisExpr.SetClass(ICodec.CLASS_CONST);
		$ThisExpr.SetType(ICodec.TYPE_INT);
		$ThisExpr.SetValue(Long.decode($Value.text).intValue());
	}
	| Value=EXPONENT_LITERAL{
		$ThisExpr.SetClass(ICodec.CLASS_CONST);
		$ThisExpr.SetType(ICodec.TYPE_FLOAT);
		$ThisExpr.SetValue(Float.valueOf($Value.text));
	}
	| Value=DECIMAL_LITERAL {$ThisExpr.SetClass(ICodec.CLASS_CONST);$ThisExpr.SetType(ICodec.TYPE_INT);$ThisExpr.SetValue(Integer.decode($Value.text));}
	| Value=CHARACTER_LITERAL 
		{
			String CharValue = String.valueOf($Value.text);
			CharValue = CharValue.substring(1, CharValue.length()-1);
			$ThisExpr.SetClass(ICodec.CLASS_CONST);
			$ThisExpr.SetType(ICodec.TYPE_CHAR);
			if (CharValue.equals("\\n")) {
				$ThisExpr.SetValue(Character.valueOf('\n'));
			} else if (CharValue.equals("\\t")) {
				$ThisExpr.SetValue(Character.valueOf('\t'));
			} else if (CharValue.equals("\\'")) {
				$ThisExpr.SetValue(Character.valueOf('\''));
			} else if (CharValue.equals("\\\"")) {
				$ThisExpr.SetValue(Character.valueOf('\"'));
			} else if (CharValue.equals("\\\\")) {
				$ThisExpr.SetValue(Character.valueOf('\\'));
			} else {
				$ThisExpr.SetValue(Character.valueOf(CharValue.toCharArray()[0]));
			}
		}
	| Value=STRING_LITERAL
		{
			String StrValue = String.valueOf($Value.text);
			char[] content = StrValue.toCharArray();
			StringBuilder sb = new StringBuilder();
			int maxLen = content.length - 1;
			for (int i = 1; i < maxLen; i++) {
				if (content[i] == '\\') {
					i++;
					if (content[i] == 'n') {
						sb.append('\n');
					} else if (content[i] == 't') {
						sb.append('\t');
					} else if (content[i] == '\'') {
						sb.append('\'');
					} else if (content[i] == '"') {
						sb.append('"');
					} else if (content[i] == '\\') {
						sb.append('\\');
					}
				} else {
					sb.append(content[i]);
				}
			}
			$ThisExpr.SetClass(ICodec.CLASS_CONST);
			$ThisExpr.SetType(ICodec.TYPE_STRING);
			$ThisExpr.SetValue(sb.toString());
		}
	| Value=FLOAT_LITERAL {$ThisExpr.SetClass(ICodec.CLASS_CONST);$ThisExpr.SetType(ICodec.TYPE_FLOAT);$ThisExpr.SetValue(Float.valueOf($Value.text));}
	| Value=BOOLEAN_LITERAL {$ThisExpr.SetClass(ICodec.CLASS_CONST);$ThisExpr.SetType(ICodec.TYPE_BOOL);$ThisExpr.SetValue(Boolean.valueOf($Value.text));}
	;
	catch[NumberFormatException e] {throw new CException(IErrCode.ERR_INVALUD_CONSTDATA, mFilePath, $Value.getLine());}

statement[CScope Scope, CStatement ThisStat]
@init{
	int CurAddr = 0;
	CScope NewScope = null;
}
	:
	labeled_statement[$Scope, $ThisStat]
	|
	(
		{NewScope = mScript.EnterScope();}
		compound_statement[NewScope, $ThisStat]
		{mScript.ExitScope(mFilePath);}
	)
	|
	(
		{CurAddr = mEncoder.GetCodeOffset();} SelectStat=selection_statement[$Scope, $ThisStat] {mEncoder.SETLINEINFO(mFilePath, $SelectStat.start.getLine(), CurAddr);}
	)
	|
	(
		{CurAddr = mEncoder.GetCodeOffset();} IterStat=iteration_statement[$Scope, $ThisStat] {mEncoder.SETLINEINFO(mFilePath, $IterStat.start.getLine(), CurAddr);}
	)
	|
	(
		{CurAddr = mEncoder.GetCodeOffset();} JumpStat=jump_statement[$Scope, $ThisStat] {mEncoder.SETLINEINFO(mFilePath, $JumpStat.start.getLine(), CurAddr);}
	)
	|
	(
		{CurAddr = mEncoder.GetCodeOffset();} ExprStat=expression_statement[$Scope, $ThisStat] {mEncoder.SETLINEINFO(mFilePath, $ExprStat.start.getLine(), CurAddr);}
	)
	|
	(
		{CurAddr = mEncoder.GetCodeOffset();} V1Stat=v1_statement[$Scope, $ThisStat] {mEncoder.SETLINEINFO(mFilePath, $V1Stat.start.getLine(), CurAddr);}
	)
	|
	(
		'__BREAK_POINT__' {mEncoder.BREAKPOINT();}
	)
	|
	(
		'__FAIL_POINT__' {mEncoder.FAILPOINT();}
	)
	;

labeled_statement [CScope Scope, CStatement ThisStat]
@init{
	CStatement NewStat = null;
}
	: 
	Label=IDENTIFIER ':' {mScript.AddLabel($Label.text, mEncoder.GetCodeOffset());}
	{NewStat = new CStatement();} statement[$Scope, NewStat] {$ThisStat.ExpectHandover(NewStat);}
	;

compound_statement[CScope Scope, CStatement ThisStat]
@init {
	CStatement NewStat = null;
}
	:
	'{'
	(
		( variable_def[$Scope, ThisStat] ) 
		|
		(
			{NewStat = new CStatement();}
			statement[$Scope, NewStat]
			{$ThisStat.ExpectHandover(NewStat);}
		)
	)*
	'}'
	;

selection_statement[CScope Scope, CStatement ThisStat]
@init{
	CExpression IfExpr = null;
	CExpression SwitchExpr = null;
	CExpression CaseExpr = null;
	CStatement NewStat = null;
	int FailOffset = 0, EndOffset = 0, CaseAddr = 0;
	CSwitchMatchTbl MatchTbl = null;
}
	: 
	(
		'if' '(' {IfExpr = new CExpression();} Exp=expression[$Scope, IfExpr, ThisStat] ')' 
		{
			if (IfExpr.IsBool() == false) {
				throw new CException(IErrCode.ERR_INVALID_EXPRTYPE, mFilePath, $Exp.start.getLine(), "bool");
			}
			FailOffset = mEncoder.GetCodeOffset()+1;
			$ThisStat.AddExpect(new CExpectAddrFix("IF_FAIL", mEncoder.GetCodeSection(), FailOffset));
			mEncoder.IFFALSE(0xFFFFFFFF);
		}
		{NewStat = new CStatement();} statement[$Scope, NewStat]
		{
			$ThisStat.ExpectAchieve("IF_FAIL", new Integer(mEncoder.GetCodeOffset()));
			$ThisStat.ExpectHandover(NewStat);
		}
		(
			options {k=1; backtrack=false;}: 'else'
			{
				EndOffset = mEncoder.GetCodeOffset()+1;
				mEncoder.GOTO(0xFFFFFFFF);
				$ThisStat.AddExpect(new CExpectAddrFix("IF_FAIL", mEncoder.GetCodeSection(), FailOffset));
				$ThisStat.AddExpect(new CExpectAddrFix("IF_END", mEncoder.GetCodeSection(), EndOffset));
				$ThisStat.ExpectAchieve("IF_FAIL", new Integer(mEncoder.GetCodeOffset()));
			}
			{NewStat = new CStatement();} statement[$Scope, NewStat]
			{
				$ThisStat.ExpectAchieve("IF_END", new Integer(mEncoder.GetCodeOffset()));
				$ThisStat.ExpectHandover(NewStat);
			}
		)?
	)
	|
	(
		'switch' '(' {SwitchExpr = new CExpression();} expression[$Scope, SwitchExpr, ThisStat] ')' 
		{
			int MatchTblOffset = mEncoder.GetCodeOffset()+1;
			mEncoder.SWITCH(0xFFFFFFFF);
			$ThisStat.AddExpect(new CExpectAddrFix("MATCH_TBL", mEncoder.GetCodeSection(), MatchTblOffset));
			MatchTbl = new CSwitchMatchTbl();
		}
		'{'
		(
			(
				options {k=1; backtrack=false;}: 'case'
				{
					CaseAddr = mEncoder.GetCodeOffset();
				}
				{CaseExpr = new CExpression();} Text=constant[$Scope, CaseExpr] ':' 
				{
					if (CaseExpr.IsString() || CaseExpr.IsFloat()) {
						CSymbolCONST ConstSymbol = mScript.AddConst($Text.text, CaseExpr.GetValue());
						CaseExpr.SetFields(ConstSymbol);
					}
					if (SwitchExpr.TypeMatch(CaseExpr) == false) {
						throw new CException(IErrCode.ERR_INVALID_EXPRTYPE, mFilePath, $Text.start.getLine(), SwitchExpr.Type2String());
					}
					MatchTbl.AddCase(CaseExpr, CaseAddr);
				}
				({NewStat = new CStatement();} statement[$Scope, NewStat] {$ThisStat.ExpectHandover(NewStat);})*
			)* 
			(
				options {k=1; backtrack=false;}: 'default' ':'
				{
					MatchTbl.AddDefault(mEncoder.GetCodeOffset());
				}
				({NewStat = new CStatement();} statement[$Scope, NewStat] {$ThisStat.ExpectHandover(NewStat);})+
			)? 
			(
				options {k=1; backtrack=false;}: 'case'
				{
					CaseAddr = mEncoder.GetCodeOffset();
				}
				{CaseExpr = new CExpression();} constant[$Scope, CaseExpr] ':'
				{
					if (CaseExpr.IsString() || CaseExpr.IsFloat()) {
						CSymbolCONST ConstSymbol = mScript.AddConst($Text.text, CaseExpr.GetValue());
						CaseExpr.SetFields(ConstSymbol);
					}
					if (SwitchExpr.TypeMatch(CaseExpr) == false) {
						throw new CException(IErrCode.ERR_INVALID_EXPRTYPE, mFilePath, $Text.start.getLine(), SwitchExpr.Type2String());
					}
					MatchTbl.AddCase(CaseExpr, CaseAddr);
				}
				({NewStat = new CStatement();} statement[$Scope, NewStat] {$ThisStat.ExpectHandover(NewStat);})*
			)*
		)
		'}'
		{
			EndOffset = mEncoder.GetCodeOffset()+1;
			$ThisStat.AddExpect(new CExpectAddrFix("SWITCH_END", mEncoder.GetCodeSection(), EndOffset));
			mEncoder.GOTO(0xFFFFFFFF);
			$ThisStat.ExpectAchieve("MATCH_TBL", new Integer(mEncoder.GetCodeOffset()));
			mEncoder.RAW(MatchTbl.GenMatchTblCode());
			$ThisStat.ExpectAchieve("SWITCH_END", new Integer(mEncoder.GetCodeOffset()));
			$ThisStat.ExpectAchieve("BREAK_OUT", new Integer(mEncoder.GetCodeOffset()));
		}
	)
	;

iteration_statement[CScope Scope, CStatement ThisStat]
@init {
	CExpression WhileExpr = null;
	CStatement NewStat = null;
	int LoopStartAddr = 0, LoopEndAddr = 0;
}
	: 
	'while' {LoopStartAddr = mEncoder.GetCodeOffset();}
	'(' {WhileExpr = new CExpression();} Exp=expression[$Scope, WhileExpr, ThisStat] ')'
	{
		if (WhileExpr.IsBool() == false) {
			throw new CException(IErrCode.ERR_INVALID_EXPRTYPE, mFilePath, $Exp.start.getLine(), "bool");
		}
		$ThisStat.AddExpect(new CExpectAddrFix("IF_FAIL", mEncoder.GetCodeSection(), mEncoder.GetCodeOffset()+1));
		mEncoder.IFFALSE(0xFFFFFFFF);
	}
	{NewStat = new CStatement();} statement[$Scope, NewStat]
	{
		mEncoder.GOTO(LoopStartAddr);
		LoopEndAddr = mEncoder.GetCodeOffset();
		$ThisStat.ExpectAchieve("IF_FAIL", new Integer(LoopEndAddr));
		NewStat.ExpectAchieve("BREAK_OUT", new Integer(LoopEndAddr));
		NewStat.ExpectAchieve("CONTINUE_BACK", new Integer(LoopStartAddr));
		$ThisStat.ExpectHandover(NewStat);
	}
	;

jump_statement[CScope Scope, CStatement ThisStat]
@init{
	CExpression ReturnExpr = null;
	boolean HasVarReturn = false;
}
	: 
	(
		'goto' Label=IDENTIFIER
		{
			CSymbolLABEL LabelSymbol = mScript.GetLabel($Label.text);
			int GotoOffset = mEncoder.GetCodeOffset();
			if (LabelSymbol != null) {
				mEncoder.GOTO(LabelSymbol.GetAddr());
			} else {
				mEncoder.GOTO(0xFFFFFFFF);
				$ThisStat.AddExpect(new CExpectLabel("GOTO_LABEL", $Label.text, mEncoder.GetCodeSection(), GotoOffset+1, $Label.line, $Label.pos));
			}
		}
	)
	|
	(
		'continue'
		{
			int ContinueOffset = mEncoder.GetCodeOffset()+1;
			mEncoder.GOTO(0xFFFFFFFF);
			$ThisStat.AddExpect(new CExpectAddrFix("CONTINUE_BACK", mEncoder.GetCodeSection(), ContinueOffset));
		}
	)
	|
	(
		'break'
		{
			int BreakOffset = mEncoder.GetCodeOffset()+1;
			mEncoder.GOTO(0xFFFFFFFF);
			$ThisStat.AddExpect(new CExpectAddrFix("BREAK_OUT", mEncoder.GetCodeSection(), BreakOffset));
		}
	)
	|
	(
		Return='return' 
		'(' ( {ReturnExpr = new CExpression();} expression[$Scope, ReturnExpr, ThisStat] {HasVarReturn = true;} )? ')'
		{
			if (HasVarReturn) {
				$ThisStat.AddExpect(new CExpectReturn(ReturnExpr, $Return.line, $Return.pos));
				mEncoder.VRETURN();
			} else {
				$ThisStat.AddExpect(new CExpectReturn(CProperty.ValueOf(ICodec.TYPE_VOID, 0), $Return.line, $Return.pos));
				mEncoder.RETURN();
			}
		}
	)
	;

expression_statement[CScope Scope, CStatement ThisStat]
@init{
	CExpression NewExpr = null;
}
	:
	(IDENTIFIER '(')=>{NewExpr = new CExpression();} function_call[$Scope, NewExpr, ThisStat]
	| (
		API_IDENTIFIER | FUNCTIONKEY_IDENTIFIER | ':Exit' | ':SystemInScanner' | ':SystemOutPrint' | ':Str2Int' | ':Str2Float' | ':SetControlMode' | ':SetDefaultSimilarity' | ':SetKvmTriedTimes' | ':OpenProgram'
		| ':SelectWindow' | ':CloseWindow' | ':WaitUntil' | ':WaitVanish' | ':Verify' | ':SetOpIndex' | ':Input' | ':KeyDown'
		| ':KeyUp' | ':SetAutoSync' | ':Click' | ':RightClick' | ':DoubleClick' | ':MouseMove' | ':MousePress' | ':MouseRelease' | ':MouseWheel' | ':MouseDragDrop' | ':FilePath' | ':Log' | ':CleanLog' | ':Capture' | ':FindText' | ':Call'  | ':Exec' | ':OpenApp'
		| ':CloseApp' | ':FindOption' | ':WatchDog' | ':Pause' | ':Delay' | ':RecSerialBytes' | ':CleanBuffer' | ':Env' | ':SetEnv' | ':Str2IntEx' | ':SetSutResolution' | ':SystemTime' | ':CopyBuffer' | ':GetString' | ':SetSeriAtt' | ':StartVideoRec' | ':StopVideoRec' | ':Byte2Bit' | ':StartAudioRec' | ':StopAudioRec' | ':FindMatches' | ':SelectRegion'
	)=>{NewExpr = new CExpression();} api_expression[$Scope, NewExpr, ThisStat]
	| {NewExpr = new CExpression();} assignment_expression[$Scope, NewExpr, ThisStat]
	;

api_expression[CScope Scope, CExpression ThisExpr, CStatement ThisStat]
@init{
	CSymbolAPI ApiCall = null;
	CExpression ArgExpr = null;
	int ArgNum = 0;
	boolean OR = false;
	boolean AND = false;
	int preType = ICodec.TYPE_VOID ;
	boolean isRelative1 = false;
	boolean isRelative2 = false;
}
@after{
	mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_INT, 0, Integer.valueOf(ArgNum)));
	$ThisExpr.SetFields(ApiCall);
	mEncoder.INVOKE(COperand.ValueOf(ApiCall));
}
	:
	(
		Name=':Exit' '(' ')' {ApiCall = FetchAPI(":Exit"); ArgNum = 0;}
	)
	|
	(
		Name=':SystemInScanner' {ApiCall = FetchAPI(":SystemInScanner"); ArgNum = 0;} '(' ')'
	)
	|
	(
		Name=':SystemOutPrint' {ApiCall = FetchAPI(":SystemOutPrint"); ArgNum = 1;}
		'(' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] ')'
	)
	|
	(
		Name=':Str2Int' {ApiCall = FetchAPI(":Str2Int"); ArgNum = 1;}
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		')'
	)
	|
	(
		Name=':Str2Float' {ApiCall = FetchAPI(":Str2Float"); ArgNum = 1;}
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		')'
	)
	|
	(
		Name=':SetControlMode' {ApiCall = FetchAPI(":SetControlMode"); ArgNum = 2;}
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name);}
		',' Port=( 'KVM' | 'kvm' | 'Kvm' | 'SERIAL' | 'HOST' | 'host' | 'Host' | 'serial' | 'Serial' | 'SUT' | 'sut' | 'Sut' | 'itshub' | 'ITSHUB' | 'Itshub' | 'ItsHub' )
		{
			CSymbolCONST ConstSymbol = mScript.AddConst($Port.text, new String($Port.text));
			mEncoder.LDC(COperand.ValueOf(ConstSymbol));
		}
		')'
	)
	|
	(
		Name=':SetDefaultSimilarity' {ApiCall = FetchAPI(":SetDefaultSimilarity"); ArgNum = 1;}
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name);}
		')'
	)
	|
	(
		Name=':SetKvmTriedTimes' {ApiCall = FetchAPI(":SetKvmTriedTimes"); ArgNum = 1;}
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name);}
		')'
	)
	|
	(
		Name=':OpenProgram' {ApiCall = FetchAPI(":OpenProgram"); ArgNum = 1;}
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		')'
	)
	|
	(
		Name=':SelectWindow' {ApiCall = FetchAPI(":SelectWindow"); ArgNum = 1;} 
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		')'
	)
	|
	(
		Name=':CloseWindow' {ApiCall = FetchAPI(":CloseWindow"); ArgNum = 1;} 
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		')'
	)
	|
	(
		Name=':WaitUntil' {ApiCall = FetchAPI(":WaitUntil"); AND = false; OR = false; ArgNum = 0;} 
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name); ArgNum++;}
		( ( '|' {OR = true;} | '&' {AND = true;} ) {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name); ArgNum++;} )* 
		{
			if ((OR == true) && (AND == true)) {
				throw new CException(IErrCode.ERR_SEPARATOR_PROMISCUOUS, mFilePath, $Name.getLine(), "'|' , '&'");
			} else if ((OR == false) && (AND == false)) {
				mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_CHAR, 0, Character.valueOf('|')));
				ArgNum++;
			} else {
				mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_CHAR, 0, OR ? Character.valueOf('|') : Character.valueOf('&')));
				ArgNum++;
			}
		}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name); ArgNum++;}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name); ArgNum++;}
		(
			',' Color=COLOR_FLAG
			{
				CSymbolCONST ConstSymbol = mScript.AddConst($Color.text, new String($Color.text));
				mEncoder.LDC(COperand.ValueOf(ConstSymbol));
				ArgNum++;
			}
			(
				'|' Color=COLOR_FLAG
				{
					CSymbolCONST ConstSymbol = mScript.AddConst($Color.text, new String($Color.text));
					mEncoder.LDC(COperand.ValueOf(ConstSymbol));
					ArgNum++;
				}
			)?
		)?
		')'
	)
	|
	(
		Name=':WaitVanish' {ApiCall = FetchAPI(":WaitVanish"); AND = false; OR = false; ArgNum = 0;} 
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name); ArgNum++;}
		( ( '|' {OR = true;} | '&' {AND = true;} ) {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name); ArgNum++;} )* 
		{
			if ((OR == true) && (AND == true)) {
				throw new CException(IErrCode.ERR_SEPARATOR_PROMISCUOUS, mFilePath, $Name.getLine(), "'|' , '&'");
			} else if ((OR == false) && (AND == false)) {
				mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_CHAR, 0, Character.valueOf('|')));
				ArgNum++;
			} else {
				mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_CHAR, 0, OR ? Character.valueOf('|') : Character.valueOf('&')));
				ArgNum++;
			}
		}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name); ArgNum++;}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name); ArgNum++;}
		(
			',' Color=COLOR_FLAG
			{
				CSymbolCONST ConstSymbol = mScript.AddConst($Color.text, new String($Color.text));
				mEncoder.LDC(COperand.ValueOf(ConstSymbol));
				ArgNum++;
			}
			(
				'|' Color=COLOR_FLAG
				{
					CSymbolCONST ConstSymbol = mScript.AddConst($Color.text, new String($Color.text));
					mEncoder.LDC(COperand.ValueOf(ConstSymbol));
					ArgNum++;
				}
			)?
		)?
		')'
	)
	|
	(
		Name=':Verify' {ApiCall = FetchAPI(":Verify"); AND = false; OR = false; ArgNum = 0;} 
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name); ArgNum++;}
		( ( '|' {OR = true;} | '&' {AND = true;} ) {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name); ArgNum++;} )* 
		{
			if ((OR == true) && (AND == true)) {
				throw new CException(IErrCode.ERR_SEPARATOR_PROMISCUOUS, mFilePath, $Name.getLine(), "'|' , '&'");
			} else if ((OR == false) && (AND == false)) {
				mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_CHAR, 0, Character.valueOf('|')));
				ArgNum++;
			} else {
				mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_CHAR, 0, OR ? Character.valueOf('|') : Character.valueOf('&')));
				ArgNum++;
			}
		}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name); ArgNum++;}
		(
			',' Color=COLOR_FLAG
			{
				CSymbolCONST ConstSymbol = mScript.AddConst($Color.text, new String($Color.text));
				mEncoder.LDC(COperand.ValueOf(ConstSymbol));
				ArgNum++;
			}
			(
				'|' Color=COLOR_FLAG
				{
					CSymbolCONST ConstSymbol = mScript.AddConst($Color.text, new String($Color.text));
					mEncoder.LDC(COperand.ValueOf(ConstSymbol));
					ArgNum++;
				}
			)?
		)?
		')'
	)
	|
	(
		Name=':SetOpIndex' {ApiCall = FetchAPI(":SetOpIndex"); ArgNum = 1;} 
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name);}
		')'
	)
	|
	(
		Name=':Input' {ApiCall = FetchAPI(":Input"); ArgNum = 2;} 
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name);}
		')'
	)
	|
	(
		Name=':KeyDown' {ApiCall = FetchAPI(":KeyDown"); ArgNum = 0;}
		'(' 
		( 
			Key=SPECIFIC_KEY 
			{
				String KeyStr = FetchKey($Key.text);
				CSymbolCONST KeySymbol = mScript.AddConst(KeyStr, KeyStr);
				mEncoder.LDC(COperand.ValueOf(KeySymbol));
				ArgNum++;
			}
			|
			{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING , $Name);ArgNum++;} 
		)
		(
			'|' 
			( 
				Key=SPECIFIC_KEY
				{
					String KeyStr = FetchKey($Key.text);
					CSymbolCONST KeySymbol = mScript.AddConst(KeyStr, KeyStr);
					mEncoder.LDC(COperand.ValueOf(KeySymbol));
					ArgNum++;
				}
				|
				{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING , $Name);ArgNum++;}
			)
		)* 
		')'
	)
	|
	(
		Name=':KeyUp' {ApiCall = FetchAPI(":KeyUp"); ArgNum = 0;}
		'(' 
		( 
			Key=SPECIFIC_KEY 
			{
				String KeyStr = FetchKey($Key.text);
				CSymbolCONST KeySymbol = mScript.AddConst(KeyStr, KeyStr);
				mEncoder.LDC(COperand.ValueOf(KeySymbol));
				ArgNum++;
			}
			|
			{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING , $Name);ArgNum++;} 
		)
		(
			'|' 
			( 
				Key=SPECIFIC_KEY
				{
					String KeyStr = FetchKey($Key.text);
					CSymbolCONST KeySymbol = mScript.AddConst(KeyStr, KeyStr);
					mEncoder.LDC(COperand.ValueOf(KeySymbol));
					ArgNum++;
				}
				|
				{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING , $Name);ArgNum++;}
			)
		)* 
		')'
	)
	|
 	(
		Name=':SetAutoSync' {ApiCall = FetchAPI(":SetAutoSync"); ArgNum = 1;} 
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_BOOL , $Name);}
		')'
	)
	|
	(
		Name= (':MouseMove' | ':DoubleClick' | ':RightClick' | ':Click') {ApiCall = FetchAPI($Name.text); ArgNum = 3;} 
		'(' 
		{ArgExpr = new CExpression();} 
		('@'{isRelative1 = true;})? 
		expression[$Scope, ArgExpr, ThisStat]
		{
		if( isRelative1 )
			SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name);
		else
			SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING | ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name);
		}
		',' {ArgExpr = new CExpression();} 
		('@'{isRelative2 = true;})? 
		expression[$Scope, ArgExpr, ThisStat] 
		{SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name);}
		{
			if ( isRelative1 != isRelative2 ) {
				throw new CException(IErrCode.ERR_SEPARATOR_PROMISCUOUS, mFilePath, $Name.getLine(), "'@'");
			} else {
				mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_BOOL, 0, isRelative1));
			} 
		}
		')'
	)
	|
 	(
		Name=':MousePress' {ApiCall = FetchAPI(":MousePress"); ArgNum = 0;} '('')'
	)
	|
 	(
		Name=':MouseRelease' {ApiCall = FetchAPI(":MouseRelease"); ArgNum = 0;} '('')'
	)
	|
 	(
		Name=':MouseWheel' {ApiCall = FetchAPI(":MouseWheel"); ArgNum = 1;} 
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT , $Name);}
		')'
	)
	|
 	(
		Name=':MouseDragDrop' {ApiCall = FetchAPI(":MouseDragDrop"); ArgNum = 3;} 
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING , $Name);}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name);}
		')'
	)
	|
	(
		Name=':FilePath' {ApiCall = FetchAPI(":FilePath"); ArgNum = 1;} 
		'(' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);} ')'
	)
	|
	(
		Name=':Log' {ApiCall = FetchAPI(":Log"); ArgNum = 1;} 
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		(
			',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);ArgNum++;}
		)?
		')'
	)
	|
 	(
		Name=':CleanLog' '(' ')' {ApiCall = FetchAPI(":CleanLog"); ArgNum = 0;}
	)
	|
	(
		Name=':Capture' {ApiCall = FetchAPI(":Capture"); ArgNum = 0;}
		'('
		( {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name); ArgNum++;} )?
		')'
	)
	|
	(
		Name=':FindText' {ApiCall = FetchAPI(":FindText"); ArgNum = 2;}
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		')'
	)
	|
	(
		Name=':Call' {ApiCall = FetchAPI(":Call"); ArgNum = 0;} 
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name); ArgNum++;}
		( ',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {ArgNum++;} )* 
		')'
	)
	|
	(
		Name=':Exec' {ApiCall = FetchAPI(":Exec"); ArgNum = 1;} 
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		(',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name); ArgNum++; })?
		')'
	)
	|
	(
		Name=':OpenApp' {ApiCall = FetchAPI(":OpenApp"); ArgNum = 1;}
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		')'
	)
	|
	(
		Name=':CloseApp' {ApiCall = FetchAPI(":CloseApp"); ArgNum = 1;}
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		')'
	)
	|
	(
		Name=':FindOption' {ApiCall = FetchAPI(":FindOption"); ArgNum = 0;} 
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name); ArgNum++;}
		( '&' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name); ArgNum++;})*
		{
			mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_INT, 0, Integer.valueOf(ArgNum)));
			ArgNum++;
		}
		','
		(
			Key=SPECIFIC_KEY 
			{
				CSymbolCONST ConstSymbol = mScript.AddConst($Key.text, new String($Key.text));
				mEncoder.LDC(COperand.ValueOf(ConstSymbol));
				ArgNum++;
			}
			|
			{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING , $Name);ArgNum++;} 
		)
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name); ArgNum++;}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name); ArgNum++;}
		(
			',' Color=COLOR_FLAG
			{
				CSymbolCONST ConstSymbol = mScript.AddConst($Color.text, new String($Color.text));
				mEncoder.LDC(COperand.ValueOf(ConstSymbol));
				ArgNum++;
			}
			(
				'|' Color=COLOR_FLAG
				{
					CSymbolCONST ConstSymbol = mScript.AddConst($Color.text, new String($Color.text));
					mEncoder.LDC(COperand.ValueOf(ConstSymbol));
					ArgNum++;
				}
			)?
		)?
		(
			',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name); ArgNum++;}
		)?
		')'
	)
	|
	(
		Name=':WatchDog' {ApiCall = FetchAPI(":WatchDog"); ArgNum = 0;} 
		'('
		Action=( 'ON' | 'on' | 'On' | 'OFF' | 'off' | 'Off' )
		{
			CSymbolCONST ConstSymbol = mScript.AddConst($Action.text, new String($Action.text));
			mEncoder.LDC(COperand.ValueOf(ConstSymbol));
			ArgNum++;
		}
		(
			',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name); ArgNum++;}
			',' Label=IDENTIFIER
			{
				CSymbolLABEL LabelSymbol = mScript.GetLabel($Label.text);
				int GotoOffset = mEncoder.GetCodeOffset();
				CExpression exp4Label = new CExpression();
				exp4Label.SetClass(ICodec.CLASS_CONST);
				exp4Label.SetType(ICodec.TYPE_INT);
				if (LabelSymbol != null) {
					exp4Label.SetValue(LabelSymbol.GetAddr());
					mEncoder.PUSH(exp4Label);
				} else {
					exp4Label.SetValue(0xFFFFFFFF);
					mEncoder.PUSH(exp4Label);
					$ThisStat.AddExpect(new CExpectLabel("GOTO_LABEL", $Label.text, mEncoder.GetCodeSection(), GotoOffset+1, $Label.line, $Label.pos));
				}
				ArgNum++;
			}
		)?
		')'
	)
	|
	(
		Name=':Pause' {ApiCall = FetchAPI(":Pause"); ArgNum = 0;} 
	)
	|
	(
		Name=':Delay' {ApiCall = FetchAPI(":Delay"); ArgNum = 1;} 
		'(' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name);} ')'
	)
	|
	(
		Name=':RecSerialBytes' {ApiCall = FetchAPI(":RecSerialBytes"); ArgNum = 1;} 
		'(' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name);} ')'
	)
	|
	(
		Name=':CleanBuffer' {ApiCall = FetchAPI(":CleanBuffer"); ArgNum = 0;} '(' ')'
	)
	|
	(
		Name=':Env' {ApiCall = FetchAPI(":Env"); ArgNum = 1;}
		'(' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);} ')'
	)
	|
 	(
		Name=':SetEnv' {ApiCall = FetchAPI(":SetEnv"); ArgNum = 2;}
		'('
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		')'
	)
	|
	(
		Name=':Str2IntEx' {ApiCall = FetchAPI(":Str2IntEx"); ArgNum = 3;} 
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_CHAR , $Name);}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT , $Name);}
		')'
	)
	|
 	(
		Name=':SystemTime' {ApiCall = FetchAPI(":SystemTime"); ArgNum = 0;} 
		'('
		({ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name); ArgNum++; })?
		')'
	)
	|
	(
		Name=':SetSutResolution' {ApiCall = FetchAPI(":SetSutResolution"); ArgNum = 2;} 
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT , $Name);}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT , $Name);}
		')'
	)
	|
 	(
		Name=':CopyBuffer' {ApiCall = FetchAPI(":CopyBuffer"); ArgNum = 1;}
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}	
		')'
	)
	|
	(
		Name=':SetSeriAtt' {ApiCall = FetchAPI(":SetSeriAtt"); ArgNum = 2;}
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}	
		','{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		')'
	)
	|
	(
		Name=':GetString' {ApiCall = FetchAPI(":GetString"); ArgNum = 4;}
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name);}
		','{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name);}
		','{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name);}
		','{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name);}
		(
			',' 
			(
				Color=COLOR_FLAG
				{
					CSymbolCONST ConstSymbol = mScript.AddConst($Color.text, new String($Color.text));
					mEncoder.LDC(COperand.ValueOf(ConstSymbol));
					ArgNum++;
				}
				|
				{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING , $Name);ArgNum++;}
			)
			(
				'|' 
				(
					Color=COLOR_FLAG
					{
						CSymbolCONST ConstSymbol = mScript.AddConst($Color.text, new String($Color.text));
						mEncoder.LDC(COperand.ValueOf(ConstSymbol));
						ArgNum++;
					}
					|
					{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING , $Name);ArgNum++;}
				)
			)?
		)?
		')'
	)
	|
 	(
		Name=':StartVideoRec' {ApiCall = FetchAPI(":StartVideoRec"); ArgNum = 0; }
		'(' 
		(
			{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING | ICodec.TYPE_FLOAT | ICodec.TYPE_INT, $Name); preType=ArgExpr.GetType(); ArgNum ++;}
			(	
				','
				{ArgExpr = new CExpression();} 
				expression[$Scope, ArgExpr, ThisStat] 
				{
					SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_FLOAT, $Name); ArgNum ++;
					if(preType == ICodec.TYPE_FLOAT){
						throw new CException(IErrCode.ERR_SYSCALLARG_NOTMATCH, mFilePath, $Name.getLine(), $Name.getText());
					}
				}
			)?	
		)?
		')'
	)
	|
 	(
		Name=':StopVideoRec' {ApiCall = FetchAPI(":StopVideoRec"); ArgNum = 0;} '('')'
	)
	|
 	(
		Name=':StartAudioRec' {ApiCall = FetchAPI(":StartAudioRec"); ArgNum = 0;} '('')'
	)
	|
 	(
		Name=':StopAudioRec' {ApiCall = FetchAPI(":StopAudioRec"); ArgNum = 0;} '('')'
	)
	|
	(
		Name=':Byte2Bit' {ApiCall = FetchAPI(":Byte2Bit"); ArgNum = 2;} 
		'(' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name);}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name);}
		')'
	)
	|
	(
		Name=':FindMatches' {ApiCall = FetchAPI(":FindMatches"); ArgNum = 2;}
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_STRING, $Name);}
		','  {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name);}
		')'
	)
	|
	(
		Name=':SelectRegion' {ApiCall = FetchAPI(":SelectRegion"); ArgNum = 2;}
		'(' 
		{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_STRING, $Name);}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT | ICodec.TYPE_FLOAT, $Name);}
		(
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name);ArgNum++;}
		',' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name);ArgNum++;}
		)?
		')'
	)
	|
	(
		Name=API_IDENTIFIER {ApiCall = FetchAPI($Name.text); ArgNum = 0;}
		'('
		(
			(
				(
					{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {ArgNum++;}
				)
				| 
				(
					Action=( 'ON' | 'on' | 'On' | 'OFF' | 'off' | 'Off' )
					{
						CSymbolCONST ConstSymbol = mScript.AddConst($Action.text, new String($Action.text));
						mEncoder.LDC(COperand.ValueOf(ConstSymbol));
						ArgNum++;
					}
				)
			) 
			(
				',' 
				(
					(
						{ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {ArgNum++;}
					)
					| 
					(
						Action=( 'ON' | 'on' | 'On' | 'OFF' | 'off' | 'Off' )
						{
							CSymbolCONST ConstSymbol = mScript.AddConst($Action.text, new String($Action.text));
							mEncoder.LDC(COperand.ValueOf(ConstSymbol));
							ArgNum++;
						}
					)
				)
			)*
		)?
		')'
		{
			if (ApiCall instanceof CSymbolUSERAPI) {
				if (((CSymbolUSERAPI)ApiCall).GetArgNum() != ArgNum) {
					throw new CException(IErrCode.ERR_API_INVALIDARGNUM);
				}
			}
		}
	)
	|	
	(
		Name=FUNCTIONKEY_IDENTIFIER 
		{
			ApiCall = FetchAPI(":SpecificKey");
			CSymbolCONST ConstSymbol = mScript.AddConst($Name.text, new String($Name.text).split(":")[1]);
			mEncoder.LDC(COperand.ValueOf(ConstSymbol));
			ArgNum = 1;
		}
		(
			options {k=1; backtrack=false;}:
			(
				(
					'|' Key=SPECIFIC_KEY 
					{
						CSymbolCONST ConstSymbol = mScript.AddConst($Key.text, new String($Key.text));
						mEncoder.LDC(COperand.ValueOf(ConstSymbol));
						ArgNum++;
					}
				)+ 
				'(' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name); ArgNum++;} ')'
			)
			|
			(
				'(' {ArgExpr = new CExpression();} expression[$Scope, ArgExpr, ThisStat] {SyscallArgTypeCheck(ArgExpr, ICodec.TYPE_INT, $Name); ArgNum++;} ')'
			)
		)?
		{
			if (ArgNum == 1) {
				mEncoder.PUSH(new COperand(ICodec.CLASS_CONST, ICodec.TYPE_INT, 0, Integer.valueOf(1)));
				ArgNum++;
			}
		}
	)
	;
	catch[CException E] {
		E.SetFile(mFilePath);
		E.SetLine($Name.getLine());
		throw E;
	}

v1_statement[CScope Scope, CStatement ThisStat]
@init {
	CExpression WhileExpr = null;
	CStatement NewStat = null;
	int LoopStartAddr = 0, LoopEndAddr = 0;
}
	:
	(
		':IfTrue'
		{
			$ThisStat.AddExpect(new CExpectAddrFix("IF_FAIL", mEncoder.GetCodeSection(), mEncoder.GetCodeOffset()+1));
			mEncoder.IFFALSEDUP(0xFFFFFFFF);
		}
		(
			{NewStat = new CStatement();} statement[$Scope, NewStat] {$ThisStat.ExpectHandover(NewStat);}
		)*
		':EndIf' {$ThisStat.ExpectAchieve("IF_FAIL", new Integer(mEncoder.GetCodeOffset()));}
	) 
	|
	(
		':IfFalse'
		{
			$ThisStat.AddExpect(new CExpectAddrFix("IF_FAIL", mEncoder.GetCodeSection(), mEncoder.GetCodeOffset()+1));
			mEncoder.IFTRUEDUP(0xFFFFFFFF);
		}
		(
			{NewStat = new CStatement();} statement[$Scope, NewStat] {$ThisStat.ExpectHandover(NewStat);}
		)*
		':EndIf' {$ThisStat.ExpectAchieve("IF_FAIL", new Integer(mEncoder.GetCodeOffset()));}
	)
	|
	(
		':While' {LoopStartAddr = mEncoder.GetCodeOffset();}
		'(' {WhileExpr = new CExpression();} Exp=expression[$Scope, WhileExpr, ThisStat] ')'
		{
			if (WhileExpr.IsBool() == false) {
				throw new CException(IErrCode.ERR_INVALID_EXPRTYPE, mFilePath, $Exp.start.getLine(), "bool");
			}
			$ThisStat.AddExpect(new CExpectAddrFix("IF_FAIL", mEncoder.GetCodeSection(), mEncoder.GetCodeOffset()+1));
			mEncoder.IFFALSE(0xFFFFFFFF);
		}
		(
			{NewStat = new CStatement();} statement[$Scope, NewStat] {$ThisStat.ExpectHandover(NewStat);}
		)*
		':EndWhile'
		{
			mEncoder.GOTO(LoopStartAddr);
			LoopEndAddr = mEncoder.GetCodeOffset();
			$ThisStat.ExpectAchieve("IF_FAIL", new Integer(LoopEndAddr));
			$ThisStat.ExpectAchieve("BREAK_OUT", new Integer(LoopEndAddr));
			$ThisStat.ExpectAchieve("CONTINUE_BACK", new Integer(LoopStartAddr));
		}
	)
	|
	(
		':Label' '(' Label=STRING_LITERAL ')'
		{mScript.AddLabel($Label.text, mEncoder.GetCodeOffset());}
	)
	|
	(
		':Goto' '(' Label=STRING_LITERAL ')'
		{
			CSymbolLABEL LabelSymbol = mScript.GetLabel($Label.text);
			int GotoOffset = mEncoder.GetCodeOffset();
			if (LabelSymbol != null) {
				mEncoder.GOTO(LabelSymbol.GetAddr());
			} else {
				mEncoder.GOTO(0xFFFFFFFF);
				$ThisStat.AddExpect(new CExpectLabel("GOTO_LABEL", $Label.text, mEncoder.GetCodeSection(), GotoOffset+1, $Label.pos, $Label.pos));
			}
		}
	)
	;

script[String FileName, CScript Script, CEncoder Encoder, CExeObj ExeObj, HashMap<String, Object>Env, CPreObj preObj]
@init{
	mFilePath = FileName;
	mScript = Script;
	mEncoder = Encoder;
	mExeObj = ExeObj;
	mEnv = Env;

	CStatement NewStat = null;
	CScope Scope = mScript.EnterScope(CScope.SCOPE_GLOBAL);
	if (preObj != null) {
		Scope.setPreMethod(preObj.getMethods());
	}
	if (mScript.IsInclueConfigHeader() == false) {
		mScript.InclueConfigHeader();
		CCompiler.Include(CGlobal.GetGlobalLocalHome()+"config/config.inc", mScript, mEncoder, mExeObj, mEnv);
	}
}
@after{
	mScript.ExpectAchieve("FUNC_RETURN", CProperty.ValueOf(ICodec.TYPE_VOID|ICodec.TYPE_BOOL|ICodec.TYPE_CHAR|ICodec.TYPE_INT|ICodec.TYPE_FLOAT|ICodec.TYPE_STRING, 0));
	mScript.ExpectAchieve("GOTO_LABEL", Scope);
	ArrayList<CExpect> Expects = mScript.GetUnachieveExpects();
	if (!Expects.isEmpty()) {
		for (Iterator<CExpect> It = Expects.iterator(); It.hasNext();) {
			CExpect Expect = It.next();
			if (Expect.Meet("GOTO_LABEL")) {
				throw new CException(IErrCode.ERR_UNDEFINED_LABEL, mFilePath, Expect.GetLine(), ((CExpectLabel)Expect).GetLabel());
			} else if (Expect.Meet("FUNC_RETURN")) {
				throw new CException(IErrCode.ERR_RETURN_TYPEMISMATCH, mFilePath, Expect.GetLine());
			} else {
				throw new CException(IErrCode.ERR_UNDEFINED_SYMBOL, mFilePath, Expect.GetLine(), Expect.GetName());
			}
		}
	}
	mExeObj.SetSymTbl(mEncoder.GenSymTbl(Scope));
	mScript.ExitScope(mFilePath);
}
	:
	include_cmd*
	(
		({NewStat = new CStatement();} global_def[Scope, NewStat] {mScript.ExpectHandover(NewStat);})
		| ({NewStat = new CStatement();} statement[Scope, NewStat] {mScript.ExpectHandover(NewStat);})
	)*
	EOF
	;

FLOAT_LITERAL :
	( '0' | '1'..'9' '0'..'9'* ) '.' ('0'..'9')*
	;

HEX_LITERAL : 
	'0' ( 'x' | 'X' ) ( '0'..'9' | 'a'..'f' | 'A'..'F' )+
	;

EXPONENT_LITERAL :
	((( '0' | '1'..'9' '0'..'9'* ) '.' ('0'..'9')*)|('0' | '1'..'9' '0'..'9'*)) ('e'|'E') 
	( '-' )?
	('0' | '1'..'9' '0'..'9'*);

DECIMAL_LITERAL : 
	'0' | '1'..'9' '0'..'9'*
	;

INC_LITERAL :
	'include' (' ')+ '"' ( ('\\' '\"') | ~('"') )* '"'
	;

STRING_LITERAL :
	'"' ( ESC_SEQ | ~( '\"' | '\\' ) )* '"'
	;
	
CHARACTER_LITERAL :
	'\'' ( ESC_SEQ | ~( '\'' | '\\' ) ) '\''
	;

BOOLEAN_LITERAL :
	('false' | 'true')
	;

SPECIFIC_KEY : 
	'ESC' | 'ENTER' | 'TAB' | 'BACKSPACE' | 'DELETE' | 'SPACE' 
	| 'F1' | 'F2' | 'F3' | 'F4' | 'F5' | 'F6' | 'F7' | 'F8' | 'F9' 
	| 'F10' | 'F11' | 'F12' | 'PGDN' | 'PGUP' | 'ARROWUP' | 'ARROWDN' 
	| 'ARROWRIGHT' | 'ARROWLEFT' | 'ALT' | 'CTRL' | 'SHIFT' | 'WIN'
	;

FUNCTIONKEY_IDENTIFIER : 
	':ESC' | ':ENTER' | ':TAB' | ':BACKSPACE' | ':DELETE' | ':SPACE' 
	| ':F1' | ':F2' | ':F3' | ':F4' | ':F5' | ':F6' | ':F7' | ':F8' | ':F9' 
	| ':F10' | ':F11' | ':F12' | ':PGDN' | ':PGUP' | ':ARROWUP' | ':ARROWDN' 
	| ':ARROWRIGHT' | ':ARROWLEFT' | ':ALT' | ':CTRL' | ':SHIFT' | ':WIN'
	;

COLOR_FLAG :
	'Black' | 'Red' | 'Green' | 'Yellow' | 'Blue' | 'Magenta' | 'Cyan' | 'White' 
	| 'BackBlack' | 'BackRed' | 'BackGreen' | 'BackYellow' | 'BackBlue' | 'BackMagenta' | 'BackCyan' | 'BackWhite'
	;

IDENTIFIER :
	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
	;

API_IDENTIFIER :
	':' ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
	;

fragment
HEX_DIGIT : 
	('0'..'9'|'a'..'f'|'A'..'F') 
	;

fragment
ESC_SEQ :
	'\\' ('t'|'n'|'\''|'\"'|'\\')
	;

COMMENT :
	'//' (~'\n')* {$channel=HIDDEN;}
	| '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
	| '#' (~'\n')* {$channel=HIDDEN;}
    ;

WS :
	(' ' | '\t' | '\r' | '\n') {$channel=HIDDEN;}
	;
