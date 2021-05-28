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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Multi< T > extends Cardinality< List< Optional< T > > > {

	Multi( List< Optional< T > > value ) {
		super( value );
	}

	public static < R > Multi< R > of( List< R > values ) {
		if ( values == null ) {
			return new Multi<>( Collections.emptyList() );
		} else {
			return new Multi<>( values.stream().map( Optional::ofNullable ).collect( Collectors.toList() ) );
		}
	}

	@Override
	public void addChildenIfNotEmpty( String name, Value destination ) {
		ValueVector values = ValueVector.create();
		this.get().stream().filter( Optional::isPresent ).forEach( v -> {
							if ( v.get() instanceof BasicType ) {
								values.add( ( ( BasicType<?> ) v.get() ).toValue() );
							} else if ( v.get() instanceof ChoiceType ) {
								values.add( ( ( ChoiceType<?,?> ) v.get() ).toValue() );
							} else {
								throw new RuntimeException( "Expected to find classes extending either BasicType or ChoiceType, found " + v.getClass() );
							}
						}
		);
		if( ! values.isEmpty() ){
			destination.children().put( name, values );
		}
	}

}
