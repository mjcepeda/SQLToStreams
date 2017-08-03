grammar Expr;
prog:	predicate;
predicate: clause;
clause:	a 
	| o 
	| n
	| c 
	| /*epsilon*/;
a:	'AND('clause','clause')';
o:	'OR('clause','clause')';
n:	'NOT('clause')';
c: 	err 
	| id op id;
err: 'ISERR('INT')'; /* <columnName> is null*/
id:	INT 
	| TEXT | NULL;
INT:	'#'?[0-9]+;
TEXT:	'"' ~('"')* '"'; /*A-Za-z*/
NULL: 'INDEX(0,-1)'; /* <columnName> = null*/
op:		'=' 
	| '>' 
	| '<' 
	| '>=' 
	| '<=' 
	| '<>';
	
	
/*compilation command: java -cp "C:\antlr\antlr-4.3-complete.jar" org.antlr.v4.Tool -visitor Expr.g4*/