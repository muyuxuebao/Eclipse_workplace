// $ANTLR 3.5 SimpleAction.g 2015-04-14 16:14:03

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class SimpleActionParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "WS", "';'", "'float'", 
		"'int'"
	};
	public static final int EOF=-1;
	public static final int T__6=6;
	public static final int T__7=7;
	public static final int T__8=8;
	public static final int ID=4;
	public static final int WS=5;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public SimpleActionParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public SimpleActionParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return SimpleActionParser.tokenNames; }
	@Override public String getGrammarFileName() { return "SimpleAction.g"; }



	// $ANTLR start "variable"
	// SimpleAction.g:3:1: variable : type ID ';' ;
	public final void variable() throws RecognitionException {
		Token ID2=null;
		ParserRuleReturnScope type1 =null;

		try {
			// SimpleAction.g:3:10: ( type ID ';' )
			// SimpleAction.g:3:12: type ID ';'
			{
			pushFollow(FOLLOW_type_in_variable10);
			type1=type();
			state._fsp--;

			ID2=(Token)match(input,ID,FOLLOW_ID_in_variable12); 
			match(input,6,FOLLOW_6_in_variable14); 
			System.out.println((type1!=null?input.toString(type1.start,type1.stop):null) + " " + (ID2!=null?ID2.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "variable"


	public static class type_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "type"
	// SimpleAction.g:4:1: type : ( 'int' | 'float' );
	public final SimpleActionParser.type_return type() throws RecognitionException {
		SimpleActionParser.type_return retval = new SimpleActionParser.type_return();
		retval.start = input.LT(1);

		try {
			// SimpleAction.g:4:6: ( 'int' | 'float' )
			// SimpleAction.g:
			{
			if ( (input.LA(1) >= 7 && input.LA(1) <= 8) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type"

	// Delegated rules



	public static final BitSet FOLLOW_type_in_variable10 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_variable12 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_6_in_variable14 = new BitSet(new long[]{0x0000000000000002L});
}
