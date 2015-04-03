/*
Melissa Martin
CIS 2168 Section 003
Assignment 4
 */

package assignment4;
    
class Intcoll4 {
   
   private int howmany;
   private ListNode c;

   // Initializes a collection
   public Intcoll4()
   {
      c = null;
      howmany = 0;
   }

   // Initializes a collection
   public Intcoll4(int i)
   {
      c = null;
      howmany = 0;
   }

   //Copies the values from a provided collection into a new, empty collection
   public void copy(Intcoll4 obj)
    {
        if (this != obj)
        {
            c = null;
            ListNode j = obj.c, k = null,m= new ListNode();
            while (j != null)
            {
                m.info = j.info;
                if (k != null) k.link = m;
                else c = m;
                k = m;
                m= new ListNode();
                j = j.link;
            }
            howmany = obj.howmany;
        }
    }
    
    /* Checks a collection to see if the entered integer is present in the 
        collection */
    public boolean belongs(int i) {
        ListNode p = c;
        while ((p != null) && (p.info != i)) p = p.link;
        return (p != null);
    }
      
    // Inserts an integer into the collection
    public void insert(int i) {
        ListNode p = c;
        ListNode pred = null;
        while ((p != null) && (p.info != i)) {
            pred = p;
            p = p.link;
        }
        if (p == null) {
            p = new ListNode();
            p.info = i;
            if (pred != null)
                pred.link = p;
            else
                c = p;
            howmany++;
        }
    }

    // Removes a specified integer from the collection
    public void omit(int i) {
        ListNode p = c; ListNode pred = null;
            while ((p != null) && (p.info != i)) {pred = p; p = p.link;}
            if (p != null)
            {
                if (pred != null) pred.link = p.link;
                else c = p.link;
                howmany--;
            }
   }
   
   // Returns the number of elements/items in the collection
   public int get_howmany() {
        return howmany;
    }

    // Prints out the elements of the collection
    public void print() {
        ListNode j = c;
        System.out.println();
        while (j != null)
        {
            System.out.println(j.info); j = j.link;
        }
    }
     
    /* Compares two collections to determine if they are equal/contain the same
        elements
    */
    public boolean equals(Intcoll4 obj)
    {
        ListNode j = c;
        boolean result = (howmany == obj.howmany);
        while ((j != null) && result)
        {
            result = obj.belongs(j.info); j = j.link;
        }
        return result;
    }

   private class ListNode {
        private int info;
        private ListNode link;

        // Initializes an empty collection
        public ListNode() {
            info = 0; 
            link = null;
         }
        
        // Initializes a collection with one number provided by the user
        public ListNode(int i) {
            info = i;
            link = null;
        }
   }

} 
