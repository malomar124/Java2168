/*
Melissa Martin
CIS 2168 Section 003
Assignment 1b
 */
package assignment1b;

class Intcoll2 {

private int[] c;
private int howmany;

   // Initializes an empty collection of size 500
    public Intcoll2() {
        c = new int[500];
        howmany = 0;
    }

    // Initializes an empty collection of a provided/specified size
    public Intcoll2(int i) {
        c = new int[i];
        howmany = 0;
    }

    //Copies the values from a provided collection into a new, empty collection
    public void copy(Intcoll2 obj) {
        if (this != obj) {
            c = new int[obj.c.length];
            int j = 0;
            while (j < howmany) {
                c[j] = obj.c[j]; 
                j++;
            }
        }
    }

    /* Checks a collection to see if the entered integer is present in the 
        collection */
    public boolean belongs(int i) {
        int j = 0;
        while ((j < howmany) && (c[j] != i)) j++;
            return ((i > 0) && (c[j] == i));
        }
    
    // Inserts an integer into the collection
    public void insert(int i) {
        if (i > 0) {
            int j = 0;
            while ((j < howmany) && (c[j] != i)) j++;
            if (j == howmany) {
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
                howmany++;
            }
        }
    }

    // Removes a specified integer from the collection
    public void omit(int i) {
	if (i > 0) {
            int j = 0;
            while ((j < howmany) && (c[j] != i)) j++;
            if (c[j] == i) {
                int k = j + 1;
                while (k < howmany) k++;
                c[j] = c[k-1];
                howmany--;
            }
	}
    }

    // Returns the number of elements/items in the collection
    public int get_howmany(){
        return howmany;
   }
 
    // Prints out the elements of the collection
    public void print() {
        int j = 0;
        System.out.println();
        while (j < howmany){
            System.out.println(c[j]); j++;
      	}
    }

    /* Compares two collections to determine if they are equal/contain the same
        elements
    */
    public boolean equals(Intcoll2 obj) {
        int j = 0; 
        boolean result = true;
        if (this.howmany == obj.howmany) {
            while ((j < howmany) && result) {
                result = obj.belongs(c[j]); j++;
            }
        } else {
            result = false;
        }
        return result;
        }

    }


