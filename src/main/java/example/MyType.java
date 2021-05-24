/******************************************************************************
 * Copyright (C) 2021 by Saverio Giallorenzo <saverio.giallorenzo@gmail.com>  *
 *                                                                            *
 * This program is free software; you can redistribute it and/or modify       *
 * it under the terms of the GNU Library General Public License as            *
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

package example;

import types.BasicType;
import jolie.runtime.Value;
import cardinality.MultiValue;

import java.util.stream.Collectors;

/*
type MyType: void {
 a[1,*]: string {
  b?: string | int {
  	c*: boolean
  	}
	}
}
*/

public class MyType extends BasicType< Void > {

	private final MultiValue< MyType_A > a;

	public MyType( MultiValue< MyType_A > a ) {
		super( null );
		this.a = a;
	}

	public MultiValue< MyType_A > a() {
		return a;
	}

	public static MyType parse( Value v ) {
		MultiValue< MyType_A > a = new MultiValue< MyType_A >(
						v.getChildren( "a" ).stream()
										.map( MyType_A::parse )
										.collect( Collectors.toList()) );
		return new MyType( a );
	}
}
