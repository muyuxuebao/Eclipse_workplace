// $ANTLR 3.5 E.g 2015-04-07 17:06:48

import org.antlr.runtime.CharStream;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

@SuppressWarnings("all")
public class ELexer extends Lexer {
	public static final int EOF = -1;
	public static final int T__8 = 8;
	public static final int T__9 = 9;
	public static final int T__10 = 10;
	public static final int T__11 = 11;
	public static final int T__12 = 12;
	public static final int T__13 = 13;
	public static final int T__14 = 14;
	public static final int INT = 4;
	public static final int STRING = 5;
	public static final int VAR = 6;
	public static final int WS = 7;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public ELexer() {
	}

	public ELexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}

	public ELexer(CharStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override
	public String getGrammarFileName() {
		return "E.g";
	}

	// $ANTLR start "T__8"
	public final void mT__8() throws RecognitionException {
		try {
			int _type = T__8;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E.g:2:6: ( '(' )
			// E.g:2:8: '('
			{
				this.match('(');
			}

			this.state.type = _type;
			this.state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__8"

	// $ANTLR start "T__9"
	public final void mT__9() throws RecognitionException {
		try {
			int _type = T__9;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E.g:3:6: ( ')' )
			// E.g:3:8: ')'
			{
				this.match(')');
			}

			this.state.type = _type;
			this.state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__9"

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E.g:4:7: ( '*' )
			// E.g:4:9: '*'
			{
				this.match('*');
			}

			this.state.type = _type;
			this.state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E.g:5:7: ( '+' )
			// E.g:5:9: '+'
			{
				this.match('+');
			}

			this.state.type = _type;
			this.state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E.g:6:7: ( '-' )
			// E.g:6:9: '-'
			{
				this.match('-');
			}

			this.state.type = _type;
			this.state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E.g:7:7: ( ';' )
			// E.g:7:9: ';'
			{
				this.match(';');
			}

			this.state.type = _type;
			this.state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E.g:8:7: ( '=' )
			// E.g:8:9: '='
			{
				this.match('=');
			}

			this.state.type = _type;
			this.state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__14"

	// $ANTLR start "VAR"
	public final void mVAR() throws RecognitionException {
		try {
			int _type = VAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E.g:8:5: ( ( 'a' .. 'z' | 'A' .. 'Z' )+ )
			// E.g:8:7: ( 'a' .. 'z' | 'A' .. 'Z' )+
			{
				// E.g:8:7: ( 'a' .. 'z' | 'A' .. 'Z' )+
				int cnt1 = 0;
				loop1: while (true) {
					int alt1 = 2;
					int LA1_0 = this.input.LA(1);
					if ((((LA1_0 >= 'A') && (LA1_0 <= 'Z')) || ((LA1_0 >= 'a') && (LA1_0 <= 'z')))) {
						alt1 = 1;
					}

					switch (alt1) {
					case 1:
							// E.g:
						{
						if (((this.input.LA(1) >= 'A') && (this.input.LA(1) <= 'Z')) || ((this.input.LA(1) >= 'a') && (this.input.LA(1) <= 'z'))) {
							this.input.consume();
						} else {
							MismatchedSetException mse = new MismatchedSetException(null, this.input);
							this.recover(mse);
							throw mse;
						}
						}
						break;

					default:
						if (cnt1 >= 1) {
							break loop1;
						}
						EarlyExitException eee = new EarlyExitException(1, this.input);
						throw eee;
					}
					cnt1++;
				}

			}

			this.state.type = _type;
			this.state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "VAR"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E.g:9:5: ( ( '0' .. '9' )+ )
			// E.g:9:7: ( '0' .. '9' )+
			{
				// E.g:9:7: ( '0' .. '9' )+
				int cnt2 = 0;
				loop2: while (true) {
					int alt2 = 2;
					int LA2_0 = this.input.LA(1);
					if ((((LA2_0 >= '0') && (LA2_0 <= '9')))) {
						alt2 = 1;
					}

					switch (alt2) {
					case 1:
							// E.g:
						{
						if (((this.input.LA(1) >= '0') && (this.input.LA(1) <= '9'))) {
							this.input.consume();
						} else {
							MismatchedSetException mse = new MismatchedSetException(null, this.input);
							this.recover(mse);
							throw mse;
						}
						}
						break;

					default:
						if (cnt2 >= 1) {
							break loop2;
						}
						EarlyExitException eee = new EarlyExitException(2, this.input);
						throw eee;
					}
					cnt2++;
				}

			}

			this.state.type = _type;
			this.state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "INT"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException {
		try {
			int _type = STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E.g:10:8: ( '\"' ( ( 'A' .. 'Z' | 'a' .. 'z' | ' ' )+ ) '\"' )
			// E.g:10:10: '\"' ( ( 'A' .. 'Z' | 'a' .. 'z' | ' ' )+ ) '\"'
			{
				this.match('\"');
				// E.g:10:14: ( ( 'A' .. 'Z' | 'a' .. 'z' | ' ' )+ )
				// E.g:10:15: ( 'A' .. 'Z' | 'a' .. 'z' | ' ' )+
				{
					// E.g:10:15: ( 'A' .. 'Z' | 'a' .. 'z' | ' ' )+
					int cnt3 = 0;
					loop3: while (true) {
						int alt3 = 2;
						int LA3_0 = this.input.LA(1);
						if (((LA3_0 == ' ') || ((LA3_0 >= 'A') && (LA3_0 <= 'Z')) || ((LA3_0 >= 'a') && (LA3_0 <= 'z')))) {
							alt3 = 1;
						}

						switch (alt3) {
						case 1:
						// E.g:
						{
							if ((this.input.LA(1) == ' ') || ((this.input.LA(1) >= 'A') && (this.input.LA(1) <= 'Z')) || ((this.input.LA(1) >= 'a') && (this.input.LA(1) <= 'z'))) {
								this.input.consume();
							} else {
								MismatchedSetException mse = new MismatchedSetException(null, this.input);
								this.recover(mse);
								throw mse;
							}
						}
							break;

						default:
							if (cnt3 >= 1) {
								break loop3;
							}
							EarlyExitException eee = new EarlyExitException(3, this.input);
							throw eee;
						}
						cnt3++;
					}

				}

				this.match('\"');
			}

			this.state.type = _type;
			this.state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "STRING"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// E.g:11:4: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
			// E.g:11:6: ( ' ' | '\\t' | '\\n' | '\\r' )+
			{
				// E.g:11:6: ( ' ' | '\\t' | '\\n' | '\\r' )+
				int cnt4 = 0;
				loop4: while (true) {
					int alt4 = 2;
					int LA4_0 = this.input.LA(1);
					if ((((LA4_0 >= '\t') && (LA4_0 <= '\n')) || (LA4_0 == '\r') || (LA4_0 == ' '))) {
						alt4 = 1;
					}

					switch (alt4) {
					case 1:
							// E.g:
						{
						if (((this.input.LA(1) >= '\t') && (this.input.LA(1) <= '\n')) || (this.input.LA(1) == '\r') || (this.input.LA(1) == ' ')) {
							this.input.consume();
						} else {
							MismatchedSetException mse = new MismatchedSetException(null, this.input);
							this.recover(mse);
							throw mse;
						}
						}
						break;

					default:
						if (cnt4 >= 1) {
							break loop4;
						}
						EarlyExitException eee = new EarlyExitException(4, this.input);
						throw eee;
					}
					cnt4++;
				}

				this.skip();
			}

			this.state.type = _type;
			this.state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// E.g:1:8: ( T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | VAR | INT | STRING | WS )
		int alt5 = 11;
		switch (this.input.LA(1)) {
		case '(': {
			alt5 = 1;
		}
		break;
		case ')': {
			alt5 = 2;
		}
		break;
		case '*': {
			alt5 = 3;
		}
		break;
		case '+': {
			alt5 = 4;
		}
		break;
		case '-': {
			alt5 = 5;
		}
		break;
		case ';': {
			alt5 = 6;
		}
		break;
		case '=': {
			alt5 = 7;
		}
		break;
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
		case 'j':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
		case 't':
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z': {
			alt5 = 8;
		}
		break;
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9': {
			alt5 = 9;
		}
		break;
		case '\"': {
			alt5 = 10;
		}
		break;
		case '\t':
		case '\n':
		case '\r':
		case ' ': {
			alt5 = 11;
		}
		break;
		default:
			NoViableAltException nvae = new NoViableAltException("", 5, 0, this.input);
			throw nvae;
		}
		switch (alt5) {
		case 1:
		// E.g:1:10: T__8
		{
			this.mT__8();

		}
			break;
		case 2:
		// E.g:1:15: T__9
		{
			this.mT__9();

		}
			break;
		case 3:
		// E.g:1:20: T__10
		{
			this.mT__10();

		}
			break;
		case 4:
		// E.g:1:26: T__11
		{
			this.mT__11();

		}
			break;
		case 5:
		// E.g:1:32: T__12
		{
			this.mT__12();

		}
			break;
		case 6:
		// E.g:1:38: T__13
		{
			this.mT__13();

		}
			break;
		case 7:
		// E.g:1:44: T__14
		{
			this.mT__14();

		}
			break;
		case 8:
		// E.g:1:50: VAR
		{
			this.mVAR();

		}
			break;
		case 9:
		// E.g:1:54: INT
		{
			this.mINT();

		}
			break;
		case 10:
		// E.g:1:58: STRING
		{
			this.mSTRING();

		}
			break;
		case 11:
		// E.g:1:65: WS
		{
			this.mWS();

		}
			break;

		}
	}

}
