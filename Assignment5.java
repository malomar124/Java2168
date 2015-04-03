/*
Melissa Martin
CIS 2168 Section 003
Intcoll5
 */

package assignment5;

import java.util.*;

class Intcoll5 {
    private LinkedList<Integer> c;
    private int howmany;

    public Intcoll5() {
        c = new LinkedList<Integer>();
        howmany = 0;
    }

    public Intcoll5(int i) {
        c = new LinkedList<Integer>();
        howmany = 0;
    }

    public void copy(Intcoll5 obj) {
        if (this != obj) {
            ListIterator<Integer> m = obj.c.listIterator();
            c = new LinkedList();
            while (m.hasNext()) {
                c.add(m.next());
            }
            this.howmany = obj.howmany;
        }
    }
    
    public void copy2(Intcoll5 obj) {
        if (this != obj) {
            c = new LinkedList();
            c = (LinkedList<Integer>) obj.c.clone();
        }
        this.howmany = obj.howmany;
    }

    public boolean belongs(int i) {
        Integer I = new Integer(i);
        return (c.contains(I));
    }

    public void insert(int i) {
        Integer I = new Integer(i);
        if (i > 0) {
            if (!c.contains(I)) {  
                c.add(I);
                howmany++;
            }
        }     
    }

    public void omit(int i) {
        Integer I = new Integer(i);
        c.remove(I);
        howmany--;
    }

    public int get_howmany() {
        return howmany;
    }

    public void print() {
        ListIterator<Integer> t = c.listIterator();
        while (t.hasNext()) {
            System.out.println(t.next());
        }
    }

    public boolean equals(Intcoll5 obj) {
        return c.equals(obj.c);
    }   
    
    public boolean equals2(Intcoll5 obj) {
        ListIterator<Integer> m = obj.c.listIterator();
        boolean result = (this.howmany == obj.howmany);
            while (m.hasNext() && result) {
                result = obj.belongs(m.next());
                }
        return result;
        }
}

class intcoll1client
{
   public static final int SENTINEL = 0;

   public static void main(String[] args)
   {
      Intcoll5 C = new Intcoll5(5);
      Intcoll5 D = new Intcoll5(2);
      // first test
      C.copy2(D);
      System.out.println("\n After Copy the number of elements in a collection C is:\n");
      System.out.print(C.get_howmany());
      System.out.println("\nThe values in the collection C are:\n");
      C.print();

      // second test
      C = new Intcoll5(5);
      D.insert(235);
      C.copy2(D);
      System.out.println("\n After Copy the number of elements in a collection C is:\n");
      System.out.print(C.get_howmany());
      System.out.println("\nThe values in the collection C are:\n");
      C.print();

      // third test
      C = new Intcoll5(5);
      D = new Intcoll5(2);
      for (int i=1;i<10;i++)
      {
          D.insert(i);
      }
      if (C.equals2(D)) System.out.println("\nC and D should not be equal but they are.");
      C.copy2(D);
      System.out.println("\n After clearing the objects and copy the number of elements in a collection C is:\n");
      System.out.print(C.get_howmany());
      System.out.println("\nThe values in the collection C are:\n");
      C.print();

      if (C.equals2(D)) System.out.println("\nC and D are equal.");
   }
}