/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stringcoll;

import java.io.*;
import java.util.*;

class StringColl {
    
    private btNode c;
    private int howmany;
    
    public StringColl() {
        c = null;
        howmany = 0;
    }

    public StringColl(String i) {
        c = null;
        howmany = 0;
    }

    private static btNode copytree(btNode t) {
        btNode root = null;
        if (t != null) {
            root = new btNode();
            root.info = t.info;
            root.left = copytree(t.left);
            root.right = copytree(t.right);
        }
        return root;
    }

    public void copy(StringColl obj) {
        if (this != obj) {
            howmany = obj.howmany;
            c = copytree(obj.c);
        }
    }

    public void insert(String i) {
        btNode pred = null, p = c;
        i = i.toLowerCase();
        while(p!=null && !p.info.equals(i)) {
            pred = p;
            if (i.compareTo(p.info) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            howmany++;
            p = new btNode(i, null, null);
            if (pred != null) {
                if (pred.info.compareTo(p.info) < 0) {
                    pred.right = p;
                } else {
                    pred.left = p;
                }
            } else {
                c = p;
            }
        }
    }

    public void omit(String i) {
        btNode p = c;
        btNode pred = null;
        i = i.toLowerCase();
        while(p!=null && !(p.info.equals(i))) {
            pred = p;
            if (p.info.compareTo(i) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p != null) {
            if ((p.left != null) && (p.right == null)) {
                btNode follow = p.left;
                btNode followPred = p;
                while (follow.left != null) {
                    followPred = follow;
                    follow = follow.left;
                }
                p.info = follow.info;
                followPred.left = follow.right;
            } else if ((p.left == null) && (p.right != null)) {
                btNode follow = p.right;
                btNode followPred = p;
                while (follow.left != null) {
                    followPred = follow;
                    follow = follow.left;
                }
                p.info = follow.info;
                followPred.right = follow.right;
            } else if ((p.left == null) && (p.right == null)) {
                if (pred == null) {
                    c = null;
                } else if (p.info.compareTo(pred.info) > 0) {
                    pred.right = null;
                } else {
                    pred.left = null;
                }
            } else if ((p.left != null) && (p.right != null)) {
                btNode follow = p.right;
                btNode followPred = p;
                btNode Node = null;
                if (pred == null) {
                    while (follow.left != null) {
                        Node = follow;
                        follow = follow.left;
                    }
                    p.info = follow.info;

                    Node.left = follow.right;
                } else {
                    while (follow.left != null) {
                        followPred = follow;
                        follow = follow.left;
                    }
                    if (followPred == p) {
                        p.info = follow.info;
                        followPred.right = follow.right;
                    } else {
                        p.info = follow.info;
                        followPred.left = follow.right;

                    }
                }

            }
            howmany--;
        }
    }

    public boolean belongs(String i) {
        btNode p = c;
        while ((p != null) && (!(p.info.equals(i)))) {
            if (p.info.compareTo(i) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return (p != null);
    }

    public int get_howmany() {
        return howmany;
    }

    public void print(String outname) {
        try {
            PrintWriter outs = new PrintWriter(new FileOutputStream(outname));
            outs.println("The number of integers is " + howmany);
            outs.println();
            printtree(c, outs);
            outs.close();
        } catch (IOException ex) {
        }
    }

    public void print() {
        printtree(c);
    }

    public boolean equals(StringColl obj) {
        int j = 0;
        boolean result = (howmany == obj.howmany);
        if (result) {
            String[] a = new String[howmany];
            String[] b = new String[howmany];
            toarray(c, a, 0);
            toarray(obj.c, b, 0);

            j = 0;
            while ((result) && (j < howmany)) {
                result = (a[j].equals(b[j]));
                j++;
            }
        }
        return result;
    }

    private static void printtree(btNode t, PrintWriter outs) {
        if (t != null) {
            printtree(t.left, outs);
            outs.println(t.info);
            printtree(t.right, outs);
        }
    }

    private static void printtree(btNode t) {
        if (t != null) {
            printtree(t.left);
            System.out.println();
            System.out.println("Root: " + t.info);
            if (t.left != null) {
                System.out.println("Left Child: " + t.left.info);
            }
            if (t.right != null) {
                System.out.println("Right Child: " + t.right.info);
            }
            printtree(t.right);
        }
    }

    private static int toarray(btNode t, String[] a, int i) {
        int num_nodes = 0;
        if (t != null) {
            num_nodes = toarray(t.left, a, i);
            a[num_nodes + i] = t.info;
            num_nodes = num_nodes + 1 + toarray(t.right, a, num_nodes + i + 1);
        }
        return num_nodes;
    }

    private static class btNode {

        String info;
        btNode left;
        btNode right;

        private btNode(String s, btNode lt, btNode rt) {
            info = s;
            left = lt;
            right = rt;
        }

        private btNode() {
            info = null;
            left = null;
            right = null;
        }
    }
}

//public class StringCollAssignment {
//
//    public static void main(String[] args) {
//        StringColl Me = new StringColl();
//        Me.insert("Melissa");
//        Me.insert("Colleen");
//        Me.insert("Rachel");
//        Me.insert("Kelly");
//        Me.insert("Jerry");
//        Me.insert("Phil");
//        Me.insert("Chris");
//        Me.insert("Alexis");
//        Me.print();
//        Me.omit("kelly");
//        System.out.println();
//        System.out.println("After omit: ");
//        Me.print();
//    }
//    
//}
