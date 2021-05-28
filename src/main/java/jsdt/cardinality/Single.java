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

package jsdt.cardinality;

import jolie.runtime.Value;
import jolie.runtime.ValueVector;
import jsdt.types.BasicType;
import jsdt.types.ChoiceType;

public class Single< T > extends Cardinality< T >{

	private Single( T value ) {
		super( value );
	}

	public static < R > Single< R > of( R value ){
		if( value == null ){
			throw new RuntimeException( "Expected single value, found none" );
		} else {
			return new Single<>( value );
		}
	}

	@Override
	public void addChildenIfNotEmpty( String name, Value destination ) {
		ValueVector values = ValueVector.create();
		if ( this.get() instanceof Value ) {
			values.add( ( Value ) this.get() );
		} else {
			if ( this.get() instanceof BasicType ) {
				values.add( ( ( BasicType<?> ) this.get() ).toValue() );
			} else if ( this.get() instanceof ChoiceType ) {
				values.add( ( ( ChoiceType<?,?> ) this.get() ).toValue() );
			} else {
				throw new RuntimeException( "Expected to find classes extending either BasicType or ChoiceType, found " + this.get().getClass() );
			}
		}
		if( ! values.isEmpty() ){
			destination.children().put( name, values );
		}
	}

}
