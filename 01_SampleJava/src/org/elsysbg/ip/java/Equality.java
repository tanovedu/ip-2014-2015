package org.elsysbg.ip.java;

public class Equality {
	private final int value;
	
	// to define constructor
	public Equality(int value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
// optimization:
//		if (obj == this) {
//			return true;
//		}
		
		// check if obj is instance of Equality (is of this class or child of this class)
		if (obj instanceof Equality) {
			Equality equality = (Equality) obj;
			return equality.value == this.value;
		}
		return false;
	}

	public static void main(String[] args) {
		final Equality a1 = new Equality(1);
		final Equality b1 = new Equality(1);
		final Equality ca = a1;
		final Equality d2 = new Equality(2);
		
		// the only true
		System.out.println("a1 == ca:" + (a1 == ca));
		System.out.println("a1 == d2:" + (a1 == d2));
		System.out.println("b1 == ca:" + (b1 == ca));
		System.out.println("b1 == d2:" + (b1 == d2));
		System.out.println("ca == d2:" + (ca == d2));

		// true
		System.out.println("a1.equals(ca):" + (a1.equals(ca)));
		System.out.println("a1.equals(d2):" + (a1.equals(d2)));
		// true
		System.out.println("b1.equals(ca):" + (b1.equals(ca)));
		System.out.println("b1.equals(d2):" + (b1.equals(d2)));
		System.out.println("ca.equals(d2):" + (ca.equals(d2)));
	}
}
