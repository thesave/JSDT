interface MyInterface {
	oneWay: ow_1( string ), ow_2( MyType )
	requestResponse: rr_1( string )( void ), rr_2( MyOtherType )( string )
}

type MyType: void {
	a[1,*]: string {
		b?: string
			| int { c*: bool }
	}
	d: int
}

type MyOtherType: void {
	e[2,15]: int {
		f: int | string
	}
}