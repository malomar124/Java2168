/*
Melissa Martin
CIS 2168 Section 003
Assignment 1c
 */
package assignment1c;

class Intcoll3 {

private boolean[] c;
private int howmany;

   // Initializes an empty collection of size 500
    public Intcoll3() {
        c = new boolean[501];
        howmany = 0;
    }

    // Initializes an empty collection of a provided/specified size
    public Intcoll3(int i) {
        c = new boolean[i + 1];
        howmany = 0;
    }
    
    //Copies the values from a provided collection into a new, empty collection
    public void copy(Intcoll3 obj) {
        if (this != obj) {
            boolean[] e = new boolean[obj.c.length];
            int j = 0;
            while (j < obj.c.length) {
                e[j] = obj.c[j]; 
                j++;
            }
            this.howmany = obj.get_howmany();
            c = e;
        }
    }

    /* Checks a collection to see if the entered integer is present in the 
        collection */
    public boolean belongs(int i) {
        return c[i];
        }
    
    // Inserts an integer into the collection
    public void insert(int i) {
        if (i > 0) {
            if (c.length <= i) {
                boolean[] array = new boolean[2 * i];
                int m = 0;
                while (m < c.length) {
                    array[m] = c[m];
                    m++;
                    }
                    c = array;
            }
            if (c[i] != true) {
                c[i] = true;
                howmany++;
            }
        }
    }

    // Removes a specified integer from the collection
    public void omit(int i) {
	if (i > 0) {
            if (c[i] == true) {
                c[i] = false;
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
        while (j < c.length){
            if (c[j] == true) {
                System.out.println(j);
      	}
            j++;
        }
    }
    /* Compares two collections to determine if they are equal/contain the same
        elements
    */
    public boolean equals(Intcoll3 obj) {
        int j = 0; 
        boolean result = true;
        if (this.howmany == obj.howmany) {
            while ((j < c.length) && result) {
                if (obj.c[j] == this.c[j]) {
                result = true; 
                j++;
            }
        }
        } else {
            result = false;
        }
        return result;
        }
}
