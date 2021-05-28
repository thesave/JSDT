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

import jolie.runtime.ValueVector;
import jsdt.cardinality.Multi;
import jsdt.cardinality.Single;
import jsdt.types.BasicType;
import jolie.runtime.Value;

import java.util.stream.Collectors;

/*
type MyType: void {
 a[1,*]: string {
  b?: string | int {
  	c*: boolean
  	}
	}
	d: int
}
*/

public class MyType extends BasicType< Void > {

	private final Multi< MyType_A > a;
	private final Single< MyType_D > d;

	public MyType( Multi< MyType_A > a, Single< MyType_D > d ) {
		super( null );
		this.a = a;
		this.d = d;
	}

	public Multi< MyType_A > a() {
		return a;
	}

	public Single< MyType_D > d() {
		return d;
	}

	public static MyType parse( Value v ) {
		if( v != null ){
			Multi< MyType_A > a = Multi.of( v.getChildren( "a" ).stream().map( MyType_A::parse ).collect( Collectors.toList()) );
			Single< MyType_D > d = Single.of( MyType_D.parse( v.getChildren( "d" ).get( 0 ) ) );
			return new MyType( a, d );
		} else {
			return null;
		}
	}

	public Value toValue(){
		Value value = super.toValue();
		this.a().addChildenIfNotEmpty( "a", value );
		this.d().addChildenIfNotEmpty( "d", value );
		return value;
	}

}
