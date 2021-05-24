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

package cardinality;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MultiValue< T >{
	private final T value;
	private final List< Optional< T > > others;

	public MultiValue( List< T > values ) {
		if( values.get( 0 ) != null ){
			this.value = values.get( 0 );
		} else {
			throw new RuntimeException( "Could not create MultiValue, expected non-null first value" );
		}
		List< Optional < T > > others = new LinkedList<>();
		for( int i = 1; i < values.size(); i++ ){
			others.add( Optional.ofNullable( values.get( i ) ) );
		}
		this.others = others;
	}

	public T getFirst() {
		return value;
	}

	public List< Optional < T > > getOthers(){
		return others;
	}

}
