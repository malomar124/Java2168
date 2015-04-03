/*
 Melissa Martin
 CIS 2168 
 */
package multistringcoll;

import java.io.*;


class MultiStringColl {

    private btNode c;
    private int uniqueCount;

    public MultiStringColl() {
        c = null;
        uniqueCount = 0;
    }

    public MultiStringColl(String i) {
        c = null;
        uniqueCount = 0;
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

    public void copy(MultiStringColl obj) {
        if (this != obj) {
            uniqueCount = obj.uniqueCount;
            c = copytree(obj.c);
        }
    }

    public void insert(String i) {
        btNode pred = null, p = c;
        i = i.toLowerCase();
        while (p != null && !p.info.equals(i)) {
            pred = p;
            if (i.compareTo(p.info) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            uniqueCount++;
            p = new btNode(i, null, null, 1);
            if (pred != null) {
                if (pred.info.compareTo(p.info) < 0) {
                    pred.right = p;
                } else {
                    pred.left = p;
                }
            } else {
                c = p;
            }
        } else {
            p.count++;
        }
        System.out.println(p.info + " " + p.count);
        System.out.println("Unique Count: " + uniqueCount);
    }

    public void omit(String i) {
        btNode p = c;
        btNode pred = null;
        i = i.toLowerCase();
        while (p != null && !(p.info.equals(i))) {
            pred = p;
            if (p.info.compareTo(i) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p.count <= 1) {
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
                    if (p.info.compareTo(pred.info) < 0) {
                        pred.left = p.right;
                    } else {
                        pred.right = p.right;
                    }
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
                uniqueCount--;
            }
        } else {
            p.count--;
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

    public int get_uniqueCount() {
        return uniqueCount;
    }

    public void print(String outname) {
        try {
            PrintWriter outs = new PrintWriter(new FileOutputStream(outname));
            outs.println("The number of integers is " + uniqueCount);
            outs.println();
            printtree(c, outs);
            outs.close();
        } catch (IOException ex) {
        }
    }

    public void print() {
        printtree(c);
    }

    public boolean equals(MultiStringColl obj) {
        int j = 0;
        boolean result = (uniqueCount == obj.uniqueCount);
        if (result) {
            String[] a = new String[uniqueCount];
            String[] b = new String[uniqueCount];
            toarray(c, a, 0);
            toarray(obj.c, b, 0);

            j = 0;
            while ((result) && (j < uniqueCount)) {
                result = (a[j].equals(b[j]) && (c.count == obj.c.count));
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
        int count;

        private btNode(String s, btNode lt, btNode rt, int i) {
            info = s;
            left = lt;
            right = rt;
            count = 1;
        }

        private btNode() {
            info = null;
            left = null;
            right = null;
            count = 1;
        }

        private int getCount() {
            return count;
        }
    }
}



