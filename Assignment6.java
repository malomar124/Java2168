/*
 Melissa Martin
 CIS 2168 Section 003
 Assingment/Intcoll 6
 */
package assignment6;

import java.util.*;
import java.io.*;

class Intcoll6 {

    private int howmany;
    private btNode c;

    public static void main(String[] args) {
        Intcoll6 P = new Intcoll6();
        Intcoll6 Q = new Intcoll6();

//        int value;
//        Scanner keyboard = new Scanner(System.in);
//        System.out.println(
//                "Enter a pos integer or neg integer to be inserted or 0 to quit:");
//        value = keyboard.nextInt();
//        while (value != 0) {
//            if (value > 0) {
//                P.insert(value);
//            } else {
//                Q.insert(-value);
//            }
//            System.out.println(
//                    "Enter pos integer or neg integer to be inserted or 0 to quit:");
//            value = keyboard.nextInt();
//        }
        P.insert(100);
        P.insert(70);
        P.insert(200);
        P.insert(50);
        P.insert(90);
        P.insert(150);
        P.insert(300);
        P.insert(20);
        P.insert(60);
        P.insert(80);
        P.insert(95);
        P.insert(110);
        P.insert(170);
        P.insert(210);
        P.insert(400);
        P.insert(10);
        P.insert(65);
        P.insert(92);
        P.insert(130);
        P.insert(250);

        System.out.println("\nThe values in collection P are:");
        P.print("P.out");
        P.print();
        Q.print("Q.out");
        if (P.equals(Q)) {
            System.out.println("P equals Q");
        } else {
            System.out.println("P not equal to Q");
        }
        Intcoll6 A = new Intcoll6();
        A.copy(P);
        System.out.println("\nThe values in A are:");
        A.omit(95);
        A.print("A.out");
        A.print();
    }

    public Intcoll6() {
        c = null;
        howmany = 0;
    }

    public Intcoll6(int i) {
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

    public void copy(Intcoll6 obj) {
        if (this != obj) {
            howmany = obj.howmany;
            c = copytree(obj.c);
        }
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
            howmany++;
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

    public void omit(int i) {
        btNode p = c;
        btNode pred = null;
        while ((p != null) && (p.info != i)) {
            pred = p;
            if (i > p.info) {
                p = p.right;
            } else {
                p = p.left;
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
                } else if (p.info < pred.info) {
                    pred.left = null;
                } else {
                    pred.right = null;
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

    public boolean belongs(int i) {
        btNode p = c;
        while ((p != null) && (p.info != i)) {
            if (p.info > i) {
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

    public boolean equals(Intcoll6 obj) {
        int j = 0;
        boolean result = (howmany == obj.howmany);
        if (result) {
            int[] a = new int[howmany];
            int[] b = new int[howmany];
            toarray(c, a, 0);
            toarray(obj.c, b, 0);

            j = 0;
            while ((result) && (j < howmany)) {
                result = (a[j] == b[j]);
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

    private static int toarray(btNode t, int[] a, int i) {
        int num_nodes = 0;
        if (t != null) {
            num_nodes = toarray(t.left, a, i);
            a[num_nodes + i] = t.info;
            num_nodes = num_nodes + 1 + toarray(t.right, a, num_nodes + i + 1);
        }
        return num_nodes;
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
