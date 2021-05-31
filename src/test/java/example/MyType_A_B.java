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

import jsdt.core.types.ChoiceType;
import jolie.runtime.Value;

public class MyType_A_B extends ChoiceType< MyType_A_B_1, MyType_A_B_2 > {

	public MyType_A_B( MyType_A_B_1 left, MyType_A_B_2 right ) {
		super( left, right );
	}

	public static MyType_A_B parse( Value v ) {
		MyType_A_B_1 left = MyType_A_B_1.parse( v );
		MyType_A_B_2 right = MyType_A_B_2.parse( v );
		if ( left == null && right == null ) {
			return null;
		} else {
			return new MyType_A_B( left, right );
		}
	}

}
