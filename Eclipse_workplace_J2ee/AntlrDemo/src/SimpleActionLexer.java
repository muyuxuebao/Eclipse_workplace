// $ANTLR 3.5 SimpleAction.g 2015-04-14 16:14:03

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class SimpleActionLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__6=6;
	public static final int T__7=7;
	public static final int T__8=8;
	public static final int ID=4;
	public static final int WS=5;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public SimpleActionLexer() {} 
	public SimpleActionLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public SimpleActionLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "SimpleAction.g"; }

	// $ANTLR start "T__6"
	public final void mT__6() throws RecognitionException {
		try {
			int _type = T__6;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SimpleAction.g:2:6: ( ';' )
			// SimpleAction.g:2:8: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__6"

	// $ANTLR start "T__7"
	public final void mT__7() throws RecognitionException {
		try {
			int _type = T__7;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SimpleAction.g:3:6: ( 'float' )
			// SimpleAction.g:3:8: 'float'
			{
			match("float"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__7"

	// $ANTLR start "T__8"
	public final void mT__8() throws RecognitionException {
		try {
			int _type = T__8;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SimpleAction.g:4:6: ( 'int' )
			// SimpleAction.g:4:8: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__8"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SimpleAction.g:5:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
			// SimpleAction.g:5:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// SimpleAction.g:5:30: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// SimpleAction.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// SimpleAction.g:6:4: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
			// SimpleAction.g:6:6: ( ' ' | '\\t' | '\\r' | '\\n' )+
			{
			// SimpleAction.g:6:6: ( ' ' | '\\t' | '\\r' | '\\n' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '\t' && LA2_0 <= '\n')||LA2_0=='\r'||LA2_0==' ') ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// SimpleAction.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			 _channel = HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// SimpleAction.g:1:8: ( T__6 | T__7 | T__8 | ID | WS )
		int alt3=5;
		switch ( input.LA(1) ) {
		case ';':
			{
			alt3=1;
			}
			break;
		case 'f':
			{
			int LA3_2 = input.LA(2);
			if ( (LA3_2=='l') ) {
				int LA3_6 = input.LA(3);
				if ( (LA3_6=='o') ) {
					int LA3_8 = input.LA(4);
					if ( (LA3_8=='a') ) {
						int LA3_10 = input.LA(5);
						if ( (LA3_10=='t') ) {
							int LA3_12 = input.LA(6);
							if ( ((LA3_12 >= '0' && LA3_12 <= '9')||(LA3_12 >= 'A' && LA3_12 <= 'Z')||LA3_12=='_'||(LA3_12 >= 'a' && LA3_12 <= 'z')) ) {
								alt3=4;
							}

							else {
								alt3=2;
							}

						}

						else {
							alt3=4;
						}

					}

					else {
						alt3=4;
					}

				}

				else {
					alt3=4;
				}

			}

			else {
				alt3=4;
			}

			}
			break;
		case 'i':
			{
			int LA3_3 = input.LA(2);
			if ( (LA3_3=='n') ) {
				int LA3_7 = input.LA(3);
				if ( (LA3_7=='t') ) {
					int LA3_9 = input.LA(4);
					if ( ((LA3_9 >= '0' && LA3_9 <= '9')||(LA3_9 >= 'A' && LA3_9 <= 'Z')||LA3_9=='_'||(LA3_9 >= 'a' && LA3_9 <= 'z')) ) {
						alt3=4;
					}

					else {
						alt3=3;
					}

				}

				else {
					alt3=4;
				}

			}

			else {
				alt3=4;
			}

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
		case '_':
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'g':
		case 'h':
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
		case 'z':
			{
			alt3=4;
			}
			break;
		case '\t':
		case '\n':
		case '\r':
		case ' ':
			{
			alt3=5;
			}
			break;
		default:
			NoViableAltException nvae =
				new NoViableAltException("", 3, 0, input);
			throw nvae;
		}
		switch (alt3) {
			case 1 :
				// SimpleAction.g:1:10: T__6
				{
				mT__6(); 

				}
				break;
			case 2 :
				// SimpleAction.g:1:15: T__7
				{
				mT__7(); 

				}
				break;
			case 3 :
				// SimpleAction.g:1:20: T__8
				{
				mT__8(); 

				}
				break;
			case 4 :
				// SimpleAction.g:1:25: ID
				{
				mID(); 

				}
				break;
			case 5 :
				// SimpleAction.g:1:28: WS
				{
				mWS(); 

				}
				break;

		}
	}



}
