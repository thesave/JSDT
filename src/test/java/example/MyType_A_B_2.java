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

package example;

import jsdt.core.cardinality.Multi;
import jsdt.core.types.BasicType;
import jolie.runtime.Value;

import java.util.stream.Collectors;

public class MyType_A_B_2 extends BasicType< Integer > {

	private final Multi< MyType_A_B_2_C > c;

	public MyType_A_B_2( Integer root, Multi< MyType_A_B_2_C > c ) {
		super( root );
		this.c = c;
	}

	public Multi< MyType_A_B_2_C > c() {
		return c;
	}

	public static MyType_A_B_2 parse( Value v ) {
		if ( v != null && v.isInt() ) {
			Multi< MyType_A_B_2_C > c = Multi.of(
							v.getChildren( "c" ).stream()
											.map( MyType_A_B_2_C::parse )
											.collect( Collectors.toList() ) );
			return new MyType_A_B_2( v.intValue(), c );
		} else {
			return null;
		}
	}

	public Value toValue() {
		Value value = super.toValue();
		this.c().addChildenIfNotEmpty( "c", value );
		return value;
	}

}
