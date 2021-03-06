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

import java.util.Optional;

public class ChoiceType< L, R > {

	private final L left;
	private final R right;

	public ChoiceType( L left, R right ) {
		this.left = left;
		this.right = right;
	}

	public Optional< L > left() {
		return Optional.ofNullable( left );
	}

	public Optional< R > right() {
		return Optional.ofNullable( right );
	}

	public Value toValue() {
		if ( this.left().isPresent() ) {
			if ( this.left().get() instanceof BasicType ) {
				return ( ( BasicType< ? > ) this.left().get() ).toValue();
			} else if ( this.left().get() instanceof ChoiceType ) {
				return ( ( ChoiceType< ?, ? > ) this.left().get() ).toValue();
			} else {
				throw new RuntimeException( "Expected to find classes extending either BasicType or ChoiceType, found " + this.left().get().getClass() );
			}
		} else {
			if ( this.right().get() instanceof BasicType ) {
				return ( ( BasicType< ? > ) this.right().get() ).toValue();
			} else if ( this.right().get() instanceof ChoiceType ) {
				return ( ( ChoiceType< ?, ? > ) this.right().get() ).toValue();
			} else {
				throw new RuntimeException( "Expected to find classes extending either BasicType or ChoiceType, found " + this.right().get().getClass() );
			}
		}
	}

}
