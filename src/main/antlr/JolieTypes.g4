/******************************************************************************
* Copyright (C) 2021 by Saverio Giallorenzo <saverio.giallorenzo@gmail.com>  *
*                                                                            *
*  This program is free software; you can redistribute it and/or modify      *
*  it under the terms of the GNU Library General Public License as           *
*  published by the Free Software Foundation; either version 2 of the        *
*  License, or (at your option) any later version.                           *
*                                                                            *
*  This program is distributed in the hope that it will be useful,           *
*  but WITHOUT ANY WARRANTY; without even the implied warranty of            *
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             *
*  GNU General Public License for more details.                              *
*                                                                            *
*  You should have received a copy of the GNU Library General Public         *
*  License along with this program; if not, write to the                     *
*  Free Software Foundation, Inc.,                                           *
*  59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.                 *
*                                                                            *
*  For details about the authors of this software, see the AUTHORS file.     *
******************************************************************************/

grammar JolieTypes;

typeDeclaration: 'type' Identifier ':' nativeType nodes? typeChoice?
	;

nativeType: 'void' | 'bool' | 'int' | 'long' | 'string' | 'byte' | 'any' | 'undefined'
	;

nodes: '{' subNodes+ '}'
	;

subNodes: '.'? Identifier cardinality? ':' nativeType nodes? typeChoice?
	;

typeChoice: '|' nativeType nodes? typeChoice?
	;

cardinality:  Single | MaybeSingle | Multi
	;

Single: '[' '1' ',' '1'  ']'
	;

MaybeSingle: '[' '0' ',' '1'  ']' | '?'
	;

Multi: '[' PositiveIntegerLiteral ',' PositiveIntegerLiteral ']' | '[' PositiveIntegerLiteral ',' '*' ']'  | '*'
	;

PositiveIntegerLiteral: '0' | NonZeroDigits Digits*
	;

fragment NonZeroDigits: [1-9]
	;

fragment Digits: [0-9]
	;

Identifier: JavaLetter JavaLetterOrDigit*
	;

fragment
JavaLetter:
	[a-zA-Z$_] // these are the "java letters" below 0x7F
	| // covers all characters above 0x7F which are not a surrogate
	~[\u0000-\u007F\uD800-\uDBFF]
	{Character.isJavaIdentifierStart(_input.LA(-1))}?
	| // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
	[\uD800-\uDBFF] [\uDC00-\uDFFF]
	{Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;

fragment
JavaLetterOrDigit:
	[a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
	| // covers all characters above 0x7F which are not a surrogate
	~[\u0000-\u007F\uD800-\uDBFF]
	{Character.isJavaIdentifierPart(_input.LA(-1))}?
	| // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
	[\uD800-\uDBFF] [\uDC00-\uDFFF]
	{Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;

WS: [ \t\r\n\u000C]+ -> skip
	;

COMMENT: '/*' .*? '*/' -> skip
	;

LINE_COMMENT:  '//' ~[\r\n]* -> skip
	;