package com.sonaive;// generics/Ex29.java
// TIJ4 Chapter Generics, Exercise 29, page 694
/* Create a generic method that takes as an argument a Holder<List<?>>. 
* Determine what methods you can and can't call for the Holder and for
* the List. Repeat for an argument of List<Holder<?>>.
*/
import java.util.*;
import static net.mindview.util.Print.*;

public class Ex29 {
	static void f1(Holder<List<?>> holder) {
		print("Calling methods for the Holder: ");
		print("holder: " + holder);
		print("holder.get(): " + holder.get());
		print("Calling holder.set(Arrays.asList(1,2,3)");
		holder.set(Arrays.asList(1,2,3));
		print("holder.get(): " + holder.get());
		int[] ia = {1,2,3};
		print("int[] ia = {1,2,3}");
		print("holder.equals(ia): " + holder.equals(ia));
		List iaList = Arrays.asList(ia);
		print("List iaList = Arrays.asList(ia)");
		print("holder.equals(iaList): " + holder.equals(iaList));
		List<Integer> l = new ArrayList<Integer>();
		for(int i = 1; i < 4; i++) l.add(i);
		print("l = List<Integer>() and add 1,2,3");
		print("holder.equals(l): " + holder.equals(l));
		print();
					
		print("Calling methods for the List: ");
		print("holder.get(): " + holder.get());
		print("holder.get().getClass(): " + holder.get().getClass());
		// Incompatible types:
//		 List<? extends Integer> list = holder.get();
		// OK, but can't add anything:
		List<?> list = holder.get();
		print("list: " + list); 
//		 list.add(new Object());
//		List<?> alist = new ArrayList<>();
//		alist.add(new Object());
		List list1 = holder.get(); // OK, but:
		// list1.add(new Object()); // Warning: unchecked call to add(E) and
		// UnsupportedOperationException at runtime
		print("list1.getClass(): " + list1.getClass());
		print("list.equals(list1): " + list.equals(list1));
		// list.clear(); // runtime UnsupportedOperationException
 		print("list.contains(1): " + list.contains(1));		
		Collection<Integer> c = new HashSet<Integer>();
		for(int i = 1; i < 4; i++) c.add(i);
		print("list.containsAll(c): " + list.containsAll(c));	
		print("list.equals(c): " + list.equals(c));
		print("list = " + list);
		print("c = " + c);
		print("c.getClass(): " + c.getClass()); // c is not a List
		print("list.get(0) = " + list.get(0));
		print("list.hashCode() = " + list.hashCode());
		print("list.indexOf(2) = " + list.indexOf(2));	
		ListIterator li = list.listIterator();
		print("After ListIterator li = list.listIterator():");
		print("li.next() = " + li.next());
		// runtime errors: UnsupportedOperationException:
		// list.remove(0);
		// list.removeAll(c);
//		 list.retainAll(c1);
//		 list.set(1,4); // compile error: set(int, ?) in list<?> cannot
		// be applied to (int,int)
		print("list.size() = " + list.size());
		print("list.subList(1,2) = " + list.subList(1,2));
		Object[] oa = list.toArray();
		print("After Object[] oa = list.toArray():");
		print("oa = ");
		for(Object o : oa) print(o + " ");
		print(); 
		// Error: runtime error ArrayStoreException:
		// Double[] da = list.toArray(new Double[3]);
		Number[] na = list.toArray(new Number[3]);
		print("After Number[] na = list.toArray(new Number[3]):");
		print("na = ");
		for(Number n : na) print(n + " ");
		print();
	}
	static void f2(List<Holder<?>> list) {
		print("Calling methods for the List: ");
		print("list = " + list);
		print("Adding Integer, String, Double");
		list.add(new Holder<Integer>(1));
		list.add(new Holder<String>("two"));
		list.add(new Holder<Double>(3.0));
		print("list = " + list);
		print("Elements of list: ");
		for(Holder h : list) 
			print("h.get() = " + h.get());
		print("Adding Float");
		list.add(3, new Holder<Float>(4.0f));
		print("list = " + list);
		print("Elements of list: ");
		for(Holder h : list) 
			print("h.get() = " + h.get());
		print("list.clear()");
		list.clear();
		print("list = " + list);
		Collection<Holder<?>> c = 
			new ArrayList<Holder<?>>();
		c.add(new Holder<String>("one"));
		c.add(new Holder<Float>(2.0f));
		c.add(new Holder<Double>(3.0));
		list.addAll(c);
		print("Adding String, Float, Double");
		print("list = " + list);
		print("Elements of list: ");
		for(Holder h : list) 
			print("h.get() = " + h.get().getClass().getSimpleName()
				+ " " + h.get());
		list.add(3, new Holder<String>("four"));
		print("Elements of list: ");
		for(Holder h : list) 
			print("h.get() = " + h.get().getClass().getSimpleName()
				+ " " + h.get());
		Object listClone = ((ArrayList)list).clone();
		print("((ArrayList)list).clone() = " +  listClone);
		print("((ArrayList)list).clone().getClass() = " +
			((ArrayList)list).clone().getClass());
		// Appears to be an ArrayList, but cannot assign:
//		 ArrayList alistClone = ((ArrayList)list).clone(); // found Object
		print("Replacing element with h4, new Holder<Integer>(4)");
		Holder<Integer> h4 = new Holder<Integer>(4);
		list.set(3, h4);
		print("Elements of list Holders: ");
		for(Holder h : list) 
			print(h.get().getClass().getSimpleName()
				+ " " + h.get());
		print("list.contains(list.set(3, h4): " +
				list.contains(list.set(3, h4)));
		print("list.contains(4): " + list.contains(4));
		print("list.contains(h4): " + list.contains(h4));
		print("Adding null member to list");		
		list.add(null);
		print("list.contains(null): " + list.contains(null));
		print("list.get(0).get(): " + list.get(0).get());
		print("list.indexOf(h4) = " + list.indexOf(h4));
		print("list.indexOf(null) = " + list.indexOf(null));
		print("list.isEmpty(): " + list.isEmpty());
		print("list.lastIndexOf(null) = " + list.lastIndexOf(null));	
		print("Removing index 0");
		list.remove(0);
		print("Elements of list Holders: ");
		for(Holder h : list) {
			if(h == null) print("null");
			else
			print(h.get().getClass().getSimpleName()
				+ " " + h.get());
			}
		print("Removing null");
		print("Elements of list Holders: ");
		for(Holder h : list) {
			if(h == null) print("null");
			else
			print(h.get().getClass().getSimpleName()
				+ " " + h.get());
			}
		// Error: removeRange has protected access:
		// ((ArrayList)list).removeRange(0,2);
		print("list.size() = " + list.size());
		Object[] oa = list.toArray();
		print("list.toArray() = ");
		for(int i = 0; i < oa.length; i++) 
			print(oa[i] + " ");
		print();
		Holder[] ha = ((ArrayList<Holder<?>>)list).toArray(new Holder[4]);
		print("(ArrayList<Holder<?>>list).toArray(new Holder[4]) = ");
		for(int i = 0; i < ha.length; i++)
			print(ha[i] + " ");
		print();
		print("Holder[4] Holders are holding: ");
		for(Holder h : ha) {
			if(h == null) print("null");
			else print(h.get());
		}
		print();		
	
		print("Calling methods for the Holder: ");
		print("list = " + list);
		print("Three Holders (one null) in list:");
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) == null) print("null"); 
			else
			print(list.get(i).getClass().getSimpleName());
		}
		list.remove(3);
		print("Holders are holding:");
		for(Holder h : list) print(h.get());
		Holder<?> h1 = list.get(0);
		Holder<?> h2 = list.get(1);
		Holder<?> h3 = list.get(2);
		print(list.get(0).getClass() + ": "
			+ list.get(0).get().getClass().getSimpleName() + ", "
			+ list.get(0).get());
		print(list.get(1).getClass() + ": "
			+ list.get(1).get().getClass().getSimpleName() + ", "
			+ list.get(1).get());
		print(list.get(2).getClass() + ": "
			+ list.get(2).get().getClass().getSimpleName() + ", "
			+ list.get(2).get());
		// Error: cannot set <?> to int or Object:
		// h3.set(5);
		// h2.set(new Object());
		
	}	
	public static void main(String[] args) {
		print("new Holder<List<?>> holder");
		f1(new Holder<List<?>>());
		print();
		print("List<Holder<?>> list = new ArrayList<Holder<?>>();");
		List<Holder<?>> list = new ArrayList<Holder<?>>();
		f2(list);
	}
}