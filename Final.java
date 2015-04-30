
package pkgfinal;

import static java.lang.Double.max;


class Practice {

    
    private btNode c;
    
    public Practice() {
        c = null;
    }
    
    public btNode get_c() {
        return c;
    }
    
    public int NumNodess() {
       return NumNodes(c);
    }
    
    public static int NumNodes(btNode t) {
        int Num, l, r;
        if (t != null) {
            l = NumNodes(t.left);
            r = NumNodes(t.right);
            
            Num = r + l + 1;System.out.println("Num:" + Num + ", L: " + l + ", R: " + r);
        } else {
            return 0;
        }
        return Num;
    }
    
    public int depth(btNode t) {
        int result, r, l;
        if (t!= null) {
            l = depth(t.left);
            r = depth(t.right);
            result = (int) (max(r,l) + 1);
        } else {
            result = 0;
        }
        return result;
    }
    
        public void print() {
        printtree(c);
    }
        
       private static void printtree(btNode t) {
        if (t != null) {
            printtree(t.left);
            System.out.println(t.info);
            printtree(t.right);
            }

        }
       
       public int depthh() {
           return depth(c);
       }
    
    
     public void insert(int i) {
        btNode pred = null, p = c;

        while ((p != null) && (p.info != i)) {
            pred = p;
            if (p.info > i) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            p = new btNode(i, null, null);
            if (pred != null) {
                if (pred.info > i) {
                    pred.left = p;
                } else {
                    pred.right = p;
                }
            } else {
                c = p;
            }
        }
    }
     private static class btNode {

        int info;
        btNode left;
        btNode right;

        private btNode(int s, btNode lt, btNode rt) {
            info = s;
            left = lt;
            right = rt;
        }

        private btNode() {
            info = 0;
            left = null;
            right = null;
        }
    }
}

public class Final {


    public static void main(String[] args) {
       Practice help = new Practice();
       help.insert(20);
       help.insert(15);
       help.insert(10);
       help.insert(30);
       help.insert(25);
       help.insert(50);
       help.insert(70);
       help.print();
       System.out.println();
       System.out.println(help.NumNodess());
        System.out.println(help.depthh());
    }
    
}
