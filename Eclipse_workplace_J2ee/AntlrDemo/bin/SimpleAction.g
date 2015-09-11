grammar SimpleAction;

variable : type ID ';' {System.out.println($type.text + " " + $ID.text);};
type : 'int' | 'float';
ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;
WS : ( ' ' | '\t' | '\r' | '\n' )+ { $channel = HIDDEN; } ;