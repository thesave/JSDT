/******************************************************************************
 * Copyright (C) 2021 by Saverio Giallorenzo <saverio.giallorenzo@gmail.com>  *
 *                                                                            *
 * This program is free software; you can redistribute it and/or modify       *
 * it under the terms of the GNU Library General Public License as            *
 * published by the Free Software Foundation; either version 2 of the         *
 * License, or (at your option) any later version.                            *
 *                                                                            *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
 * GNU General Public License for more details.                               *
 *                                                                            *
 * You should have received a copy of the GNU Library General Public          *
 * License along with this program; if not, write to the                      *
 * Free Software Foundation, Inc.,                                            *
 * 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.                  *
 *                                                                            *
 * For details about the authors of this software, see the AUTHORS file.      *
 ******************************************************************************/

package jsdt.JSDTVisitor;

import jolie.lang.NativeType;
import jolie.util.Range;
import jsdt.core.cardinality.Cardinalities;

import java.util.*;

class JSDTVisitorUtils {

	static String jolieToJavaType( NativeType ctx ) {
		switch ( ctx ) {
			case VOID:
				return "Void";
			case BOOL:
				return "Boolean";
			case INT:
				return "Integer";
			case LONG:
				return "Long";
			case STRING:
				return "String";
			case DOUBLE:
				return "Double";
			case RAW:
				return "ByteArray";
			default: // "any"
				return "Value";
		}
	}

	static Optional< String > jolieToIsValue( NativeType ctx ) {
		String checker = null;
		switch ( ctx ) {
			case BOOL:
				checker = "isBool";
				break;
			case INT:
				checker = "isInt";
				break;
			case LONG:
				checker = "isLong";
				break;
			case STRING:
				checker = "isString";
				break;
			case DOUBLE:
				checker = "isDouble";
				break;
			case RAW:
				checker = "isByteArray";
				break;
			default: // "any" or "void"
		}
		return Optional.ofNullable( checker );
	}

	static Optional< String > jolieToGetValueOptional( NativeType ctx ) {
		return Optional.ofNullable( jolieToGetValue( ctx ) );
	}

	static String jolieToGetValue( NativeType basicTypeName ) {
		String getter = null;
		switch ( basicTypeName ) {
			case BOOL:
				getter = "boolValue";
				break;
			case INT:
				getter = "intValue";
				break;
			case LONG:
				getter = "longValue";
				break;
			case STRING:
				getter = "strValue";
				break;
			case DOUBLE:
				getter = "doubleValue";
				break;
			case RAW:
				getter = "byteArrayValue";
				break;
			default: // "void", "any" or "undefined"
		}
		return getter;
	}

	static String jolieToGetValue( String basicTypeName ) {
		String getter = null;
		switch ( basicTypeName ) {
			case "bool":
				getter = "boolValue";
				break;
			case "int":
				getter = "intValue";
				break;
			case "double":
				getter = "doubleValue";
				break;
			case "long":
				getter = "longValue";
				break;
			case "string":
				getter = "strValue";
				break;
			case "byte":
				getter = "byteArrayValue";
				break;
			default: // "void" or "any"
				;
		}
		return getter;
	}

	static Cardinalities getCardinalityClass( Range ctx ) {
		if ( ctx.min() == 1 && ctx.max() == 1 ) {
			return Cardinalities.Single;
		} else if ( ctx.min() == 0 && ctx.max() == 1 ) {
			return Cardinalities.MaybeSingle;
		} else {
			return Cardinalities.Multi;
		}
	}

}
