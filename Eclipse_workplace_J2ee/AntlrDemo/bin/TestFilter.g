lexer grammar TestFilter;
options {
filter=true;
language=Java;
}
A : aText=AA{System.out.println("A: "+$aText.getText());};
B : bText=BB{System.out.println("B: "+$bText.toString());};
AA : 'ab';
BB : 'a';
