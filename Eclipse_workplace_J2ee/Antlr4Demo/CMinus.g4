/**
 * Define a grammar called Hello
 */
 grammar CMinus;

 methodDeclaration
 :
 	type Identifier
 	(
 		(
 			'('
 			(
 				variableModifier* type formalParameterDeclsRest?
 			)? ')'
 		)
 		(
 			'[' ']'
 		)*
 		(
 			'throws' qualifiedNameList
 		)?
 		(
 			methodBody
 			| ';'
 		)
 	)
 ;

 formalParameterDeclsRest
 :
 	variableDeclaratorId
 	(
 		','
 		(
 			variableModifier* type formalParameterDeclsRest?
 		)
 	)?
 	| '...' variableDeclaratorId
 ;

 type
 :
 	Identifier
 	(
 		typeArguments
 	)?
 	(
 		'.' Identifier
 		(
 			typeArguments
 		)?
 	)*
 	(
 		'[' ']'
 	)*
 	| primitiveType
 	(
 		'[' ']'
 	)*
 ;

 variableModifier
 :
 	'final'
 	| annotation
 ;

 formalParameterDeclsRest
 :
 	variableDeclaratorId
 	(
 		','
 		(
 			variableModifier* type formalParameterDeclsRest?
 		)
 	)?
 	| '...' variableDeclaratorId
 ;

 variableDeclaratorId
 :
 	Identifier
 	(
 		'[' ']'
 	)*
 ;

 Identifier
 :
 	(
 		'a' .. 'z'
 		| 'A' .. 'Z'
 		| '_'
 	)
 	(
 		'a' .. 'z'
 		| 'A' .. 'Z'
 		| '_'
 		| '0' .. '9'
 	)*
 ;

 typeArguments
 :
 	'<' typeArgument
 	(
 		',' typeArgument
 	)* '>'
 ;

 typeArgument
 :
 	type
 	| '?'
 	(
 		(
 			'extends'
 			| 'super'
 		) type
 	)?
 ;

 qualifiedNameList
 :
 	qualifiedName
 	(
 		',' qualifiedName
 	)*
 ;

 qualifiedName
 :
 	Identifier
 	(
 		'.' Identifier
 	)*
 ;


