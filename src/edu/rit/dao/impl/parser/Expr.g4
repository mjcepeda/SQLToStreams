/*compilation command: java -cp "C:\antlr\antlr-4.3-complete.jar" org.antlr.v4.Tool -visitor Expr.g4*/
grammar Expr;
prog:	predicate;
predicate:	a 
	| o 
	| n
	| c 
	| /*epsilon*/;
a:	'AND('predicate','predicate')';
o:	'OR('predicate','predicate')';
n:	'NOT('c')';
c: 	id op id;
id:	INT 
	| TEXT;
INT:	'#'?[0-9]+;
TEXT:	'"'[A-Za-z]+'"';
op:		'=' 
	| '>' 
	| '<' 
	| '>=' 
	| '<=' 
	| '<>';