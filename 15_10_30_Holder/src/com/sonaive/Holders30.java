package com.sonaive;// generics/Holders30.java
// TIJ4 Chapter Generics, Exercise 30, page 696
// Create a Holder for each of the primitive wrapper types, and show that
// autoboxing and autounboxing works for the set() and get() methods of 
// each instance.
import static net.mindview.util.Print.*;

public class Holders30 {
	public static void main(String[] args) {
		Holder<Character> charHolder = new Holder<Character>();
		Holder<Byte> byteHolder = new Holder<Byte>();
		Holder<Short> shortHolder = new Holder<Short>();
		Holder<Integer> intHolder = new Holder<Integer>();
		Holder<Long> longHolder = new Holder<Long>();
		Holder<Float> floatHolder = new Holder<Float>();
		Holder<Double> doubleHolder = new Holder<Double>();
		// Autoboxing and autounboxing work:
		charHolder.set('a');
		print(charHolder.get() + ", ");
		print(charHolder.get().getClass());
		char c = charHolder.get();
		print("char c = charHolder.get() = " + c);
		byte b = 1;
		byteHolder.set(b);
		print(byteHolder.get() + ", ");
		print(byteHolder.get().getClass());
		byte bb = byteHolder.get();
		print("byte bb = byteHolder.get() = " + bb);
		short s = 1;
		shortHolder.set(s);
		print(shortHolder.get() + ", ");
		print(shortHolder.get().getClass());
		short ss = shortHolder.get();
		print("short ss = shortHolder.get() = " + ss);
		intHolder.set(1);
		print(intHolder.get() + ". ");
		print(intHolder.get().getClass());
		int i = intHolder.get();
		print("int i = intHolder.get() = " + i);
		long l = 2;
		longHolder.set(l);
		print(longHolder.get() + ", ");
		print(longHolder.get().getClass());
		long ll = longHolder.get();
		print("long ll = longHolder.get() = " + ll);
		float f = 1f;
		floatHolder.set(f);
		print(floatHolder.get() + ", ");
		print(floatHolder.get().getClass());
		float ff = floatHolder.get();
		print("float ff = floatHolder.get() = " + ff);
		double d = 1.1;
		doubleHolder.set(d);
		print(doubleHolder.get() + ", ");
		print(doubleHolder.get().getClass());
		double dd = doubleHolder.get();
		print("double dd = doubleHolder.get() = " + dd);
	}		
		
}