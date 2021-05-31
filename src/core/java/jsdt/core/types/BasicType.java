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

package jsdt.core.types;

import jolie.runtime.Value;

public class BasicType< T > {
	final private T root;

	public BasicType( T root ) {
		this.root = root;
	}

	public T root() {
		return root;
	}

	public static < R > BasicType< R > parse( Value v, Class< R > c ) {
		if ( c.equals( Boolean.class ) )
			return ( BasicType< R > ) new BasicType<>( v.boolValue() );
		if ( c.equals( Integer.class ) )
			return ( BasicType< R > ) new BasicType<>( v.intValue() );
		if ( c.equals( Double.class ) )
			return ( BasicType< R > ) new BasicType<>( v.doubleValue() );
		if ( c.equals( Long.class ) )
			return ( BasicType< R > ) new BasicType<>( v.longValue() );
		if ( c.equals( String.class ) )
			return ( BasicType< R > ) new BasicType<>( v.strValue() );
		else
			throw new RuntimeException( "Unsupported root value type " + c );
	}

	public Value toValue() {
		Value value = Value.create();
		if ( this.root() != null ) {
			value.setValue( this.root() );
		}
		return value;
	}

}
