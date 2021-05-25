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

import jolie.runtime.Value;
import jolie.runtime.ValuePrettyPrinter;
import jolie.runtime.ValueVector;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Optional;
import java.util.stream.Stream;

public class Test {

	public static void main( String[] args ) {

		Value v = Value.create();

		ValueVector a = v.getChildren( "a" );
		Value a1 = Value.create();
		a.add( a1 );
		a1.setValue( "A1" );
		Value a2 = Value.create();
		a.add( a2 );
		a2.setValue( "A2" );
		Value a3 = Value.create();
		a.add( a3 );
		a3.setValue( "A3" );
		v.getChildren( "d" ).get( 0 ).setValue( 42 );


		Value b2 = Value.create();
		a2.getChildren( "b" ).add( b2 );
		b2.setValue( "B1" );

		Value b3 = Value.create();
		a3.getChildren( "b" ).add( b3 );
		b3.setValue( 42 );
		ValueVector c3 = b3.getChildren( "c" );
		Value c31 = Value.create();
		c31.setValue( true );
		c3.add( c31 );
		Value c32 = Value.create();
		c32.setValue( false );
		c3.add( c32 );

		debugInfo( v );

		// PARSE INTO TYPED VALUE
		MyType typedValue = MyType.parse( v );

		typedValue.a().get().stream()
						.filter( Optional::isPresent )
						.map( _a -> _a.get().b().get() )
						.filter( Optional::isPresent )
						.map( Optional::get )
						.map( b -> b.left().isPresent() ?
										b.left().get() :
										b.right().get()
						)
						.forEach( b -> System.out.println( b.root() ) );
	}


	public static void debugInfo( Value v ) {
		StringWriter w = new StringWriter();
		try {
			new ValuePrettyPrinter( v, w, "" ).run();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		System.out.println( w );
	}


}
