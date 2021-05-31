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

package jsdt.core.cardinality;

import jolie.runtime.Value;
import jolie.runtime.ValueVector;
import jsdt.core.types.BasicType;
import jsdt.core.types.ChoiceType;

import java.util.Optional;

// represents ? === [0,1]
public class MaybeSingle< T > extends Cardinality< Optional< T > > {
	private MaybeSingle( Optional< T > value ) {
		super( value );
	}

	public static < T > MaybeSingle< T > of( T value ) {
		return new MaybeSingle<>( Optional.ofNullable( value ) );
	}

	@Override
	public void addChildenIfNotEmpty( String name, Value destination ) {
		ValueVector values = ValueVector.create();
		if ( this.get().isPresent() ) {
			if ( this.get().get() instanceof Value ) {
				values.add( ( Value ) this.get().get() );
			} else {
				if ( this.get().get() instanceof Value ) {
					values.add( ( Value ) this.get().get() );
				} else {
					if ( this.get().get() instanceof BasicType ) {
						values.add( ( ( BasicType< ? > ) this.get().get() ).toValue() );
					} else if ( this.get().get() instanceof ChoiceType ) {
						values.add( ( ( ChoiceType< ?, ? > ) this.get().get() ).toValue() );
					} else {
						throw new RuntimeException( "Expected to find classes extending either BasicType or ChoiceType, found " + this.get().get().getClass() );
					}
				}
				if ( !values.isEmpty() ) {
					destination.children().put( name, values );
				}
			}
		}
		if ( !values.isEmpty() ) {
			destination.children().put( name, values );
		}
	}

}
