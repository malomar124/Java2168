/*
 Melissa Martin
 CIS 2168 Section 3
 Assignment 1 Part 1a
 */

package assignment1;
import java.util.*;
class Intcoll1{
    
   private int[] c;

   // Initializes an empty collection of size 500
    public Intcoll1() {
        c = new int[500+1];
        c[0] = 0;
    }

    // Initializes an empty collection of a provided/specified size
    public Intcoll1(int i) {
        c = new int[i+1];
        c[0] = 0;
    }

    //Copies the values from a provided collection into a new, empty collection
    public void copy(Intcoll1 obj) {
        if (this != obj) {
            c = new int[obj.c.length];
            int j = 0;
            while (obj.c[j] != 0) {
                c[j] = obj.c[j]; 
                j++;
            }
            c[j] = 0;
        }
    }

    /* Checks a collection to see if the entered integer is present in the 
        collection */
    public boolean belongs(int i) {
        int j = 0;
        while ((c[j] != 0) && (c[j] != i)) j++;
            return ((i > 0) && (c[j] == i));
        }
    
    // Inserts an integer into the collection
    public void insert(int i) {
        if (i > 0) {
            int j = 0;
            while ((c[j] != 0) && (c[j] != i)) j++;
            if (c[j] == 0) {
                if (j == c.length - 1) {
                    int[] array = new int[2 * c.length];
                    int m = 0;
                    while (m < c.length) {
                        array[m] = c[m];
                        m++;
                    }
                    c = array;
                    }
                c[j] = i;
                c[j + 1] = 0;
            }
        }
    }

    // Removes a specified integer from the collection
    public void omit(int i) {
	if (i > 0) {
            int j = 0;
            while ((c[j] != 0) && (c[j] != i)) j++;
            if (c[j] == i) {
                int k = j + 1;
                while (c[k] != 0) k++;
                c[j] = c[k-1]; c[k-1] = 0;
            }
	}
    }

    // Returns the number of elements/items in the collection
    public int get_howmany() {
        int j=0, howmany=0;
        while (c[j]!=0) {howmany++; j++;}
        return howmany;
   }
 
    // Prints out the elements of the collection
    public void print() {
        int j = 0;
        System.out.println();
        while (c[j] != 0){
            System.out.println(c[j]); j++;
      	}
    }

    /* Compares two collections to determine if they are equal/contain the same
        elements
    */
    public boolean equals(Intcoll1 obj) {
        int j = 0; boolean result = true;
        while ((c[j] != 0) && result) {
            result = obj.belongs(c[j]); j++;
        }
        j = 0;
        while ((obj.c[j] != 0) && result) {
            result = belongs(obj.c[j]); j++;
        }
        return result;
   }

   public static final int SENTINEL = 0;

   public static void main(String[] args)
   {
      int value; Scanner keyboard=new Scanner(System.in);
      Intcoll1 P=new Intcoll1(), N=new Intcoll1(), L= new Intcoll1();

      System.out.println("Enter an integer to be inserted or 0 to quit:");
      value=keyboard.nextInt();
      while(value != SENTINEL)
      {
         if (value > 0) {P.insert(value); L.insert(value);}
         else {N.insert(-value); L.omit(-value);}
         System.out.println("Enter next integer to be inserted or 0 to quit:");
         value=keyboard.nextInt();
      }
      System.out.println("\nThe values in collection P are:");
      P.print();
      System.out.println("\nThe values in collection N are:");
      N.print();
      System.out.println("\nThe values in collection L are:");
      L.print();
      if (P.equals(N)) System.out.println("\nP and N are equal.");
      else System.out.println("\nP and N are NOT equal.");
      Intcoll1 A=new Intcoll1(); A.copy(L);
      System.out.println("\nThe values in the copy of L are:\n");
      A.print();
   }
}





