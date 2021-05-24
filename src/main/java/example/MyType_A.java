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
import cardinality.MaybeValue;

public class MyType_A extends BasicType< String > {

	private final MaybeValue< MyType_A_B > b;

	public MyType_A( String root, MaybeValue< MyType_A_B > b ) {
		super( root );
		this.b = b;
	}

	public MaybeValue< MyType_A_B > b() {
		return b;
	}

	public static MyType_A parse( Value v ) {
		MaybeValue< MyType_A_B > b = new MaybeValue<>( MyType_A_B.parse( v.getChildren( "b" ).get( 0 ) ) );
		return new MyType_A( v.strValue(), b );
	}
}
