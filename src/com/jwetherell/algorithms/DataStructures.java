package com.jwetherell.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.jwetherell.algorithms.data_structures.BinarySearchTree;
import com.jwetherell.algorithms.data_structures.BinaryHeap;
import com.jwetherell.algorithms.data_structures.BinaryHeap.TYPE;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;
import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.HashMap;
import com.jwetherell.algorithms.data_structures.PatriciaTrie;
import com.jwetherell.algorithms.data_structures.RadixTree;
import com.jwetherell.algorithms.data_structures.SuffixTree;
import com.jwetherell.algorithms.data_structures.TrieMap;
import com.jwetherell.algorithms.data_structures.LinkedList;
import com.jwetherell.algorithms.data_structures.Matrix;
import com.jwetherell.algorithms.data_structures.Queue;
import com.jwetherell.algorithms.data_structures.SegmentTree;
import com.jwetherell.algorithms.data_structures.SkipList;
import com.jwetherell.algorithms.data_structures.SplayTree;
import com.jwetherell.algorithms.data_structures.Stack;
import com.jwetherell.algorithms.data_structures.SuffixTrie;
import com.jwetherell.algorithms.data_structures.Treap;
import com.jwetherell.algorithms.data_structures.Trie;
import com.jwetherell.algorithms.graph.BellmanFord;
import com.jwetherell.algorithms.graph.CycleDetection;
import com.jwetherell.algorithms.graph.Dijkstra;
import com.jwetherell.algorithms.graph.FloydWarshall;
import com.jwetherell.algorithms.graph.Johnson;
import com.jwetherell.algorithms.graph.Prim;
import com.jwetherell.algorithms.graph.TopologicalSort;


public class DataStructures {

    private static final int NUMBER_OF_TESTS = 100;
    private static final Random RANDOM = new Random();
    private static final int ARRAY_SIZE = 1000;

    private static Integer[] unsorted = null;
    private static String string = null;
    private static boolean debug = false;
    private static boolean debugTime = false;


    public static void main(String[] args) {
        System.out.println("Starting tests.");
        boolean passed = true;
        for (int i=0; i<NUMBER_OF_TESTS; i++) {
            passed = runTests();
            if (!passed) break;
        }
        if (passed) System.out.println("Tests finished. All passed.");
        else System.err.println("Tests finished. Detected a failure.");
    }
    
    private static boolean runTests() {
        StringBuilder builder = new StringBuilder();
        builder.append("Array=");
        unsorted = new Integer[ARRAY_SIZE];
        for (int i=0; i<unsorted.length; i++) {
            Integer j = RANDOM.nextInt(unsorted.length*10);
            //Make sure there are no duplicates
            boolean found = true;
            while (found) {
                found = false;
                for (int k=0; k<i; k++) {
                    int l = unsorted[k];
                    if (j==l) {
                        found = true;
                        j = RANDOM.nextInt(unsorted.length*10);
                        break;
                    }
                }
            }
            unsorted[i] = j;
            builder.append(j).append(',');
        }
        builder.append('\n');
        string = builder.toString();
        if (debug) System.out.println(string);

        boolean passed = true;
        long before = 0L;
        long after = 0L;
        
        before = System.currentTimeMillis();
        passed = testHeap();
        if (!passed) {
            System.err.println("Heap failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Heap time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testBST();
        if (!passed) {
            System.err.println("BST failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("BST time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testGraph();
        if (!passed) {
            System.err.println("Graph failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Graph time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testHashMap();
        if (!passed) {
            System.err.println("Hash Map failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Hash Map time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testLinkedList();
        if (!passed) {
            System.err.println("Linked List failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Linked List time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testMatrix();
        if (!passed) {
            System.err.println("Matrix failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Matrix time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testPatriciaTrie();
        if (!passed) {
            System.err.println("Patricia Trie failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Patricia Trie time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testQueue();
        if (!passed) {
            System.err.println("Queue failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Queue time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testRadixTree();
        if (!passed) {
            System.err.println("Radix Tree failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Radix Tree time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testSegmentTree();
        if (!passed) {
            System.err.println("Segment Tree failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Segment Tree time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testSkipList();
        if (!passed) {
            System.err.println("Skip List failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Skip List time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testSplayTree();
        if (!passed) {
            System.err.println("Splay Tree failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Splay Tree time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testStack();
        if (!passed) {
            System.err.println("Stack failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Stack time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testSuffixTree();
        if (!passed) {
            System.err.println("Suffix Tree failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Suffix Tree time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testSuffixTrie();
        if (!passed) {
            System.err.println("Suffix Trie failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Suffix Trie time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testTreap();
        if (!passed) {
            System.err.println("Treap failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Treap time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testTrie();
        if (!passed) {
            System.err.println("Trie failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Trie time = "+(after-before)+" ms");

        before = System.currentTimeMillis();
        passed = testTrieMap();
        if (!passed) {
            System.err.println("Trie Map failed.");
            return false;
        }
        after = System.currentTimeMillis();
        if (debugTime) System.out.println("Trie Map time = "+(after-before)+" ms");

        if (debugTime) System.out.println();
        return true;
    }

    private static void handleError(Object obj) {
        System.err.println(string);
        System.err.println(obj.toString());
    }
    
    private static boolean testHeap() {
        {
            // MIN-HEAP
            if (debug) System.out.println("Min-Heap.");
            BinaryHeap<Integer> minHeap = new BinaryHeap<Integer>();

            for (int i=0;  i<unsorted.length; i++) {
                int item = unsorted[i];
                minHeap.add(item);
                boolean exists = (minHeap.getSize()==i+1);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(minHeap);
                    return false;
                }
            }
            if (debug) System.out.println(minHeap.toString());

            for (int i=0; i<unsorted.length; i++) {
                minHeap.removeRoot();
                boolean exists = (minHeap.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+i+" indexed item still exists.");
                    handleError(minHeap);
                    return false;
                }
            }
            if (minHeap.getRootValue()!=null) {
                System.err.println("YIKES!! heap isn't empty.");
                handleError(minHeap);
                return false;
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                minHeap.add(item);
                boolean exists = (minHeap.getSize()==unsorted.length-i);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(minHeap);
                    return false;
                }
            }
            if (debug) System.out.println(minHeap.toString());

            for (int i=0; i<unsorted.length; i++) {
                minHeap.removeRoot();
                boolean exists = (minHeap.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+i+" indexed item still exists.");
                    handleError(minHeap);
                    return false;
                }
            }
            if (minHeap.getRootValue()!=null) {
                System.err.println("YIKES!! heap isn't empty.");
                handleError(minHeap);
                return false;
            }

            if (debug) System.out.println();
        }
        
        {
            // MAX-HEAP
            if (debug) System.out.println("Max-Heap.");
            BinaryHeap<Integer> maxHeap = new BinaryHeap<Integer>(TYPE.MAX);
            for (int i=0;  i<unsorted.length; i++) {
                int item = unsorted[i];
                maxHeap.add(item);
                boolean exists = (maxHeap.getSize()==i+1);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(maxHeap);
                    return false;
                }
            }
            if (debug) System.out.println(maxHeap.toString());

            for (int i=0; i<unsorted.length; i++) {
                maxHeap.removeRoot();
                boolean exists = (maxHeap.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+i+" indexed item still exists.");
                    handleError(maxHeap);
                    return false;
                }
            }
            if (maxHeap.getRootValue()!=null) {
                System.err.println("YIKES!! heap isn't empty.");
                handleError(maxHeap);
                return false;
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                maxHeap.add(item);
                boolean exists = (maxHeap.getSize()==unsorted.length-i);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(maxHeap);
                    return false;
                }
            }
            if (debug) System.out.println(maxHeap.toString());

            for (int i=0; i<unsorted.length; i++) {
                maxHeap.removeRoot();
                boolean exists = (maxHeap.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+i+" indexed item still exists.");
                    handleError(maxHeap);
                    return false;
                }
            }
            if (maxHeap.getRootValue()!=null) {
                System.err.println("YIKES!! heap isn't empty.");
                handleError(maxHeap);
                return false;
            }

            if (debug) System.out.println();
        }

        return true;
    }
    
    private static boolean testBST() {
        {
            // BINARY SEARCH TREE (first)
            if (debug) System.out.println("Binary search tree with first HashNode.");
            BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(BinarySearchTree.TYPE.FIRST);
            for (int i : unsorted) {
                bst.add(i);
                boolean exists = bst.contains(i);
                if (!exists) {
                    System.err.println("YIKES!! "+i+" doesn't exists.");
                    handleError(bst);
                    return false;
                }
            }
            if (debug) System.out.println(bst.toString());

            for (int i : unsorted) {
                bst.remove(i);
                boolean exists = bst.contains(i);
                if (exists) {
                    System.err.println("YIKES!! "+i+" still exists.");
                    handleError(bst);
                    return false;
                }
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                bst.add(item);
                boolean exists = bst.contains(item);
                if (!exists) {
                    System.err.println("YIKES!! "+i+" doesn't exists.");
                    handleError(bst);
                    return false;
                }
            }
            if (debug) System.out.println(bst.toString());

            for (int i : unsorted) {
                bst.remove(i);
                boolean exists = bst.contains(i);
                if (exists) {
                    System.err.println("YIKES!! "+i+" still exists.");
                    handleError(bst);
                    return false;
                }
            }
            
            if (debug) System.out.println();
        }

        {
            // BINARY SEARCH TREE (middle)
            if (debug) System.out.println("Binary search tree with middle HashNode.");
            BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(BinarySearchTree.TYPE.MIDDLE);
            for (int i : unsorted) {
                bst.add(i);
                boolean exists = bst.contains(i);
                if (!exists) {
                    System.err.println("YIKES!! "+i+" doesn't exists.");
                    handleError(bst);
                    return false;
                }
            }
            if (debug) System.out.println(bst.toString());

            for (int i : unsorted) {
                bst.remove(i);
                boolean exists = bst.contains(i);
                if (exists) {
                    System.err.println("YIKES!! "+i+" still exists.");
                    handleError(bst);
                    return false;
                }
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                bst.add(item);
                boolean exists = bst.contains(item);
                if (!exists) {
                    System.err.println("YIKES!! "+i+" doesn't exists.");
                    handleError(bst);
                    return false;
                }
            }
            if (debug) System.out.println(bst.toString());

            for (int i : unsorted) {
                bst.remove(i);
                boolean exists = bst.contains(i);
                if (exists) {
                    System.err.println("YIKES!! "+i+" still exists.");
                    handleError(bst);
                    return false;
                }
            }

            if (debug) System.out.println();
        }

        {
            // BINARY SEARCH TREE (random)
            if (debug) System.out.println("Binary search tree using random HashNode.");
            BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(BinarySearchTree.TYPE.RANDOM);
            for (int i : unsorted) {
                bst.add(i);
                boolean exists = bst.contains(i);
                if (!exists) {
                    System.err.println("YIKES!! "+i+" doesn't exists.");
                    handleError(bst);
                    return false;
                }
            }
            if (debug) System.out.println(bst.toString());

            for (int i : unsorted) {
                bst.remove(i);
                boolean exists = bst.contains(i);
                if (exists) {
                    System.err.println("YIKES!! "+i+" still exists.");
                    handleError(bst);
                    return false;
                }
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                bst.add(item);
                boolean exists = bst.contains(item);
                if (!exists) {
                    System.err.println("YIKES!! "+i+" doesn't exists.");
                    handleError(bst);
                    return false;
                }
            }
            if (debug) System.out.println(bst.toString());

            for (int i : unsorted) {
                bst.remove(i);
                boolean exists = bst.contains(i);
                if (exists) {
                    System.err.println("YIKES!! "+i+" still exists.");
                    handleError(bst);
                    return false;
                }
            }

            if (debug) System.out.println();
        }

        return true;
    }
    
    private static boolean testGraph() {
        {
            // UNDIRECTED GRAPH
            if (debug) System.out.println("Undirected Graph.");
            List<Vertex<Integer>> verticies = new ArrayList<Vertex<Integer>>();
            Graph.Vertex<Integer> v1 = new Graph.Vertex<Integer>(1);            
            verticies.add(v1);
            Graph.Vertex<Integer> v2 = new Graph.Vertex<Integer>(2);            
            verticies.add(v2);
            Graph.Vertex<Integer> v3 = new Graph.Vertex<Integer>(3);            
            verticies.add(v3);
            Graph.Vertex<Integer> v4 = new Graph.Vertex<Integer>(4);            
            verticies.add(v4);
            Graph.Vertex<Integer> v5 = new Graph.Vertex<Integer>(5);            
            verticies.add(v5);
            Graph.Vertex<Integer> v6 = new Graph.Vertex<Integer>(6);            
            verticies.add(v6);

            List<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();
            Graph.Edge<Integer> e1_2 = new Graph.Edge<Integer>(7, v1, v2);
            edges.add(e1_2);
            Graph.Edge<Integer> e1_3 = new Graph.Edge<Integer>(9, v1, v3);
            edges.add(e1_3);
            Graph.Edge<Integer> e1_6 = new Graph.Edge<Integer>(14, v1, v6);
            edges.add(e1_6);
            Graph.Edge<Integer> e2_3 = new Graph.Edge<Integer>(10, v2, v3);
            edges.add(e2_3);
            Graph.Edge<Integer> e2_4 = new Graph.Edge<Integer>(15, v2, v4);
            edges.add(e2_4);
            Graph.Edge<Integer> e3_4 = new Graph.Edge<Integer>(11, v3, v4);
            edges.add(e3_4);
            Graph.Edge<Integer> e3_6 = new Graph.Edge<Integer>(2, v3, v6);
            edges.add(e3_6);
            Graph.Edge<Integer> e5_6 = new Graph.Edge<Integer>(9, v5, v6);
            edges.add(e5_6);
            Graph.Edge<Integer> e4_5 = new Graph.Edge<Integer>(6, v4, v5);
            edges.add(e4_5);

            Graph<Integer> undirected = new Graph<Integer>(verticies,edges);
            if (debug) System.out.println(undirected.toString());
            
            Graph.Vertex<Integer> start = v1;
            if (debug) System.out.println("Dijstra's shortest paths of the undirected graph from "+start.getValue());
            Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> map1 = Dijkstra.getShortestPaths(undirected, start);
            if (debug) System.out.println(getPathMapString(start,map1));

            Graph.Vertex<Integer> end = v5;
            if (debug) System.out.println("Dijstra's shortest path of the undirected graph from "+start.getValue()+" to "+end.getValue());
            Graph.CostPathPair<Integer> pair1 = Dijkstra.getShortestPath(undirected, start, end);
            if (debug) {
                if (pair1!=null) System.out.println(pair1.toString());
                else System.out.println("No path from "+start.getValue()+" to "+end.getValue());
            }

            start = v1;
            if (debug) System.out.println("Bellman-Ford's shortest paths of the undirected graph from "+start.getValue());
            Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> map2 = BellmanFord.getShortestPaths(undirected, start);
            if (debug) System.out.println(getPathMapString(start,map2));

            end = v5;
            if (debug) System.out.println("Bellman-Ford's shortest path of the undirected graph from "+start.getValue()+" to "+end.getValue());
            Graph.CostPathPair<Integer> pair2 = BellmanFord.getShortestPath(undirected, start, end);
            if (debug) {
                if (pair2!=null) System.out.println(pair2.toString());
                else System.out.println("No path from "+start.getValue()+" to "+end.getValue());
            }

            if (debug) System.out.println("Prim's minimum spanning tree of the undirected graph from "+start.getValue());
            Graph.CostPathPair<Integer> pair = Prim.getMinimumSpanningTree(undirected, start);
            if (debug) System.out.println(pair.toString());

            if (debug) System.out.println();
        }

        {
            // DIRECTED GRAPH
            if (debug) System.out.println("Directed Graph.");
            List<Vertex<Integer>> verticies = new ArrayList<Vertex<Integer>>();
            Graph.Vertex<Integer> v1 = new Graph.Vertex<Integer>(1);            
            verticies.add(v1);
            Graph.Vertex<Integer> v2 = new Graph.Vertex<Integer>(2);            
            verticies.add(v2);
            Graph.Vertex<Integer> v3 = new Graph.Vertex<Integer>(3);            
            verticies.add(v3);
            Graph.Vertex<Integer> v4 = new Graph.Vertex<Integer>(4);            
            verticies.add(v4);
            Graph.Vertex<Integer> v5 = new Graph.Vertex<Integer>(5);            
            verticies.add(v5);
            Graph.Vertex<Integer> v6 = new Graph.Vertex<Integer>(6);            
            verticies.add(v6);
            Graph.Vertex<Integer> v7 = new Graph.Vertex<Integer>(7);            
            verticies.add(v7);

            List<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();
            Graph.Edge<Integer> e1_2 = new Graph.Edge<Integer>(7, v1, v2);
            edges.add(e1_2);
            Graph.Edge<Integer> e1_3 = new Graph.Edge<Integer>(9, v1, v3);
            edges.add(e1_3);
            Graph.Edge<Integer> e1_6 = new Graph.Edge<Integer>(14, v1, v6);
            edges.add(e1_6);
            Graph.Edge<Integer> e2_3 = new Graph.Edge<Integer>(10, v2, v3);
            edges.add(e2_3);
            Graph.Edge<Integer> e2_4 = new Graph.Edge<Integer>(15, v2, v4);
            edges.add(e2_4);
            Graph.Edge<Integer> e3_4 = new Graph.Edge<Integer>(11, v3, v4);
            edges.add(e3_4);
            Graph.Edge<Integer> e3_6 = new Graph.Edge<Integer>(2, v3, v6);
            edges.add(e3_6);
            Graph.Edge<Integer> e6_5 = new Graph.Edge<Integer>(9, v6, v5);
            edges.add(e6_5);
            Graph.Edge<Integer> e4_5 = new Graph.Edge<Integer>(6, v4, v5);
            edges.add(e4_5);
            Graph.Edge<Integer> e4_7 = new Graph.Edge<Integer>(16, v4, v7);
            edges.add(e4_7);
            
            Graph<Integer> directed = new Graph<Integer>(Graph.TYPE.DIRECTED,verticies,edges);
            if (debug) System.out.println(directed.toString());
            
            Graph.Vertex<Integer> start = v1;
            if (debug) System.out.println("Dijstra's shortest paths of the directed graph from "+start.getValue());
            Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> map = Dijkstra.getShortestPaths(directed, start);
            if (debug) System.out.println(getPathMapString(start,map));

            Graph.Vertex<Integer> end = v5;
            if (debug) System.out.println("Dijstra's shortest path of the directed graph from "+start.getValue()+" to "+end.getValue());
            Graph.CostPathPair<Integer> pair = Dijkstra.getShortestPath(directed, start, end);
            if (debug) {
                if (pair!=null) System.out.println(pair.toString());
                else System.out.println("No path from "+start.getValue()+" to "+end.getValue());
            }
            
            start = v1;
            if (debug) System.out.println("Bellman-Ford's shortest paths of the undirected graph from "+start.getValue());
            Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> map2 = BellmanFord.getShortestPaths(directed, start);
            if (debug) System.out.println(getPathMapString(start,map2));

            end = v5;
            if (debug) System.out.println("Bellman-Ford's shortest path of the undirected graph from "+start.getValue()+" to "+end.getValue());
            Graph.CostPathPair<Integer> pair2 = BellmanFord.getShortestPath(directed, start, end);
            if (debug) {
                if (pair2!=null) System.out.println(pair2.toString());
                else System.out.println("No path from "+start.getValue()+" to "+end.getValue());
            }

            if (debug) System.out.println();
        }

        {
            // DIRECTED GRAPH (WITH NEGATIVE WEIGHTS)
            if (debug) System.out.println("Undirected Graph with Negative Weights.");
            List<Vertex<Integer>> verticies = new ArrayList<Vertex<Integer>>();
            Graph.Vertex<Integer> v1 = new Graph.Vertex<Integer>(1);            
            verticies.add(v1);
            Graph.Vertex<Integer> v2 = new Graph.Vertex<Integer>(2);            
            verticies.add(v2);
            Graph.Vertex<Integer> v3 = new Graph.Vertex<Integer>(3);            
            verticies.add(v3);
            Graph.Vertex<Integer> v4 = new Graph.Vertex<Integer>(4);            
            verticies.add(v4);

            List<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();
            Graph.Edge<Integer> e1_4 = new Graph.Edge<Integer>(2, v1, v4);
            edges.add(e1_4);
            Graph.Edge<Integer> e2_1 = new Graph.Edge<Integer>(6, v2, v1);
            edges.add(e2_1);
            Graph.Edge<Integer> e2_3 = new Graph.Edge<Integer>(3, v2, v3);
            edges.add(e2_3);
            Graph.Edge<Integer> e3_1 = new Graph.Edge<Integer>(4, v3, v1);
            edges.add(e3_1);
            Graph.Edge<Integer> e3_4 = new Graph.Edge<Integer>(5, v3, v4);
            edges.add(e3_4);
            Graph.Edge<Integer> e4_2 = new Graph.Edge<Integer>(-7, v4, v2);
            edges.add(e4_2);
            Graph.Edge<Integer> e4_3 = new Graph.Edge<Integer>(-3, v4, v3);
            edges.add(e4_3);
            
            Graph<Integer> directed = new Graph<Integer>(Graph.TYPE.DIRECTED,verticies,edges);
            if (debug) System.out.println(directed.toString());

            Graph.Vertex<Integer> start = v1;
            if (debug) System.out.println("Bellman-Ford's shortest paths of the directed graph from "+start.getValue());
            Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> map2 = BellmanFord.getShortestPaths(directed, start);
            if (debug) System.out.println(getPathMapString(start,map2));

            Graph.Vertex<Integer> end = v3;
            if (debug) System.out.println("Bellman-Ford's shortest path of the directed graph from "+start.getValue()+" to "+end.getValue());
            Graph.CostPathPair<Integer> pair2 = BellmanFord.getShortestPath(directed, start, end);
            if (debug) {
                if (pair2!=null) System.out.println(pair2.toString());
                else System.out.println("No path from "+start.getValue()+" to "+end.getValue());
            }

            if (debug) System.out.println("Johnson's all-pairs shortest path of the directed graph.");
            Map<Vertex<Integer>, Map<Vertex<Integer>, Set<Edge<Integer>>>> paths = Johnson.getAllPairsShortestPaths(directed);
            if (debug) {
                if (paths==null) System.out.println("Directed graph contains a negative weight cycle.");
                else System.out.println(getPathMapString(paths));
            }

            if (debug) System.out.println("Floyd-Warshall's all-pairs shortest path weights of the directed graph.");
            Map<Vertex<Integer>, Map<Vertex<Integer>, Integer>> pathWeights = FloydWarshall.getAllPairsShortestPaths(directed);
            if (debug) System.out.println(getWeightMapString(pathWeights));

            if (debug) System.out.println();
        }

        {
            // UNDIRECTED GRAPH
            if (debug) System.out.println("Undirected Graph cycle check.");
            List<Vertex<Integer>> cycledVerticies = new ArrayList<Vertex<Integer>>();
            Graph.Vertex<Integer> cv1 = new Graph.Vertex<Integer>(1);            
            cycledVerticies.add(cv1);
            Graph.Vertex<Integer> cv2 = new Graph.Vertex<Integer>(2);            
            cycledVerticies.add(cv2);
            Graph.Vertex<Integer> cv3 = new Graph.Vertex<Integer>(3);            
            cycledVerticies.add(cv3);
            Graph.Vertex<Integer> cv4 = new Graph.Vertex<Integer>(4);            
            cycledVerticies.add(cv4);
            Graph.Vertex<Integer> cv5 = new Graph.Vertex<Integer>(5);            
            cycledVerticies.add(cv5);
            Graph.Vertex<Integer> cv6 = new Graph.Vertex<Integer>(6);            
            cycledVerticies.add(cv6);

            List<Edge<Integer>> cycledEdges = new ArrayList<Edge<Integer>>();
            Graph.Edge<Integer> ce1_2 = new Graph.Edge<Integer>(7, cv1, cv2);
            cycledEdges.add(ce1_2);
            Graph.Edge<Integer> ce2_4 = new Graph.Edge<Integer>(15, cv2, cv4);
            cycledEdges.add(ce2_4);
            Graph.Edge<Integer> ce3_4 = new Graph.Edge<Integer>(11, cv3, cv4);
            cycledEdges.add(ce3_4);
            Graph.Edge<Integer> ce3_6 = new Graph.Edge<Integer>(2, cv3, cv6);
            cycledEdges.add(ce3_6);
            Graph.Edge<Integer> ce5_6 = new Graph.Edge<Integer>(9, cv5, cv6);
            cycledEdges.add(ce5_6);
            Graph.Edge<Integer> ce4_5 = new Graph.Edge<Integer>(6, cv4, cv5);
            cycledEdges.add(ce4_5);

            Graph<Integer> undirectedWithCycle = new Graph<Integer>(cycledVerticies,cycledEdges);
            if (debug) System.out.println(undirectedWithCycle.toString());

            if (debug) {
                System.out.println("Cycle detection of the undirected graph.");
                boolean result = CycleDetection.detect(undirectedWithCycle);
                System.out.println("result="+result);
                System.out.println();
            }

            List<Vertex<Integer>> verticies = new ArrayList<Vertex<Integer>>();
            Graph.Vertex<Integer> v1 = new Graph.Vertex<Integer>(1);
            verticies.add(v1);
            Graph.Vertex<Integer> v2 = new Graph.Vertex<Integer>(2);
            verticies.add(v2);
            Graph.Vertex<Integer> v3 = new Graph.Vertex<Integer>(3);
            verticies.add(v3);
            Graph.Vertex<Integer> v4 = new Graph.Vertex<Integer>(4);
            verticies.add(v4);
            Graph.Vertex<Integer> v5 = new Graph.Vertex<Integer>(5);
            verticies.add(v5);
            Graph.Vertex<Integer> v6 = new Graph.Vertex<Integer>(6);
            verticies.add(v6);
            
            List<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();
            Graph.Edge<Integer> e1_2 = new Graph.Edge<Integer>(7, v1, v2);
            edges.add(e1_2);
            Graph.Edge<Integer> e2_4 = new Graph.Edge<Integer>(15, v2, v4);
            edges.add(e2_4);
            Graph.Edge<Integer> e3_4 = new Graph.Edge<Integer>(11, v3, v4);
            edges.add(e3_4);
            Graph.Edge<Integer> e3_6 = new Graph.Edge<Integer>(2, v3, v6);
            edges.add(e3_6);
            Graph.Edge<Integer> e4_5 = new Graph.Edge<Integer>(6, v4, v5);
            edges.add(e4_5);

            Graph<Integer> undirectedWithoutCycle = new Graph<Integer>(verticies,edges);
            if (debug) System.out.println(undirectedWithoutCycle.toString());

            if (debug) {
                System.out.println("Cycle detection of the undirected graph.");
                boolean result = CycleDetection.detect(undirectedWithoutCycle);
                System.out.println("result="+result);
                System.out.println();
            }
        }

        {
            // DIRECTED GRAPH
            if (debug) System.out.println("Directed Graph topological sort.");
            List<Vertex<Integer>> verticies = new ArrayList<Vertex<Integer>>();
            Graph.Vertex<Integer> cv1 = new Graph.Vertex<Integer>(1);            
            verticies.add(cv1);
            Graph.Vertex<Integer> cv2 = new Graph.Vertex<Integer>(2);            
            verticies.add(cv2);
            Graph.Vertex<Integer> cv3 = new Graph.Vertex<Integer>(3);            
            verticies.add(cv3);
            Graph.Vertex<Integer> cv4 = new Graph.Vertex<Integer>(4);            
            verticies.add(cv4);
            Graph.Vertex<Integer> cv5 = new Graph.Vertex<Integer>(5);            
            verticies.add(cv5);
            Graph.Vertex<Integer> cv6 = new Graph.Vertex<Integer>(6);            
            verticies.add(cv6);

            List<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();
            Graph.Edge<Integer> ce1_2 = new Graph.Edge<Integer>(1, cv1, cv2);
            edges.add(ce1_2);
            Graph.Edge<Integer> ce2_4 = new Graph.Edge<Integer>(2, cv2, cv4);
            edges.add(ce2_4);
            Graph.Edge<Integer> ce4_3 = new Graph.Edge<Integer>(3, cv4, cv3);
            edges.add(ce4_3);
            Graph.Edge<Integer> ce3_6 = new Graph.Edge<Integer>(4, cv3, cv6);
            edges.add(ce3_6);
            Graph.Edge<Integer> ce5_6 = new Graph.Edge<Integer>(5, cv5, cv6);
            edges.add(ce5_6);
            Graph.Edge<Integer> ce4_5 = new Graph.Edge<Integer>(6, cv4, cv5);
            edges.add(ce4_5);

            Graph<Integer> directed = new Graph<Integer>(Graph.TYPE.DIRECTED,verticies,edges);
            if (debug) System.out.println(directed.toString());

            if (debug) System.out.println("Topological sort of the directed graph.");
            List<Graph.Vertex<Integer>> results = TopologicalSort.sort(directed);
            if (debug) {
                System.out.println("result="+results);
                System.out.println();
            }
        }
        
        return true;
    }
    
    private static boolean testHashMap() {
        {
            // Hash Map
            if (debug) System.out.println("Hash Map.");
            HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
            for (int i : unsorted) {
                hash.put(i, i);
                boolean exists = hash.containsValue(i);
                if (!exists) {
                    System.err.println("YIKES!! "+i+" doesn't exists.");
                    handleError(hash);
                    return false;
                }
            }
            if (debug) System.out.println(hash.toString());

            for (int i : unsorted) {
                hash.remove(i);
                boolean exists = hash.containsValue(i);
                if (exists) {
                    System.err.println("YIKES!! "+i+" still exists.");
                    handleError(hash);
                    return false;
                }
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                hash.put(item,item);
                boolean exists = hash.containsValue(item);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(hash);
                    return false;
                }
            }
            if (debug) System.out.println(hash.toString());

            for (int i : unsorted) {
                hash.remove(i);
                boolean exists = hash.containsValue(i);
                if (exists) {
                    System.err.println("YIKES!! "+i+" still exists.");
                    handleError(hash);
                    return false;
                }
            }
            
            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testLinkedList() {
        {
            // Linked List
            if (debug) System.out.println("Linked List.");
            LinkedList<Integer> list = new LinkedList<Integer>();
            for (int i=0;  i<unsorted.length; i++) {
                int item = unsorted[i];
                list.add(item);
                boolean exists = (list.getSize()==i+1);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(list);
                    return false;
                }
            }
            if (debug) System.out.println(list.toString());

            for (int i=0; i<unsorted.length; i++) {
                int item = unsorted[i];
                list.remove(item);
                boolean exists = (list.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(list);
                    return false;
                }
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                list.add(item);
                boolean exists = (list.getSize()==unsorted.length-i);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(list);
                    return false;
                }
            }
            if (debug) System.out.println(list.toString());

            for (int i=0; i<unsorted.length; i++) {
                int item = unsorted[i];
                list.remove(item);
                boolean exists = (list.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(list);
                    return false;
                }
            }

            if (debug) System.out.println();
        }

        return true;
    }
    
    private static boolean testMatrix() {
        {
            // MATRIX
            if (debug) System.out.println("Matrix.");
            Matrix<Integer> matrix1 = new Matrix<Integer>(4,3);
            matrix1.set(0, 0, 14);
            matrix1.set(0, 1, 9);
            matrix1.set(0, 2, 3);
            matrix1.set(1, 0, 2);
            matrix1.set(1, 1, 11);
            matrix1.set(1, 2, 15);
            matrix1.set(2, 0, 0);
            matrix1.set(2, 1, 12);
            matrix1.set(2, 2, 17);
            matrix1.set(3, 0, 5);
            matrix1.set(3, 1, 2);
            matrix1.set(3, 2, 3);
            
            Matrix<Integer> matrix2 = new Matrix<Integer>(3,2);
            matrix2.set(0, 0, 12);
            matrix2.set(0, 1, 25);
            matrix2.set(1, 0, 9);
            matrix2.set(1, 1, 10);
            matrix2.set(2, 0, 8);
            matrix2.set(2, 1, 5);

            if (debug) System.out.println("Matrix multiplication.");
            Matrix<Integer> matrix3 = matrix1.multiply(matrix2);
            if (debug) System.out.println(matrix3);
            
            int rows = 2;
            int cols = 2;
            int counter = 0;
            Matrix<Integer> matrix4 = new Matrix<Integer>(rows,cols);
            for (int r=0; r<rows; r++) {
                for (int c=0; c<cols; c++) {
                    matrix4.set(r, c, counter++);
                }
            }

            if (debug) System.out.println("Matrix subtraction.");
            Matrix<Integer> matrix5 = matrix4.subtract(matrix4);
            if (debug) System.out.println(matrix5);

            if (debug) System.out.println("Matrix addition.");
            Matrix<Integer> matrix6 = matrix4.add(matrix4);
            if (debug) System.out.println(matrix6);
            
            Matrix<Integer> matrix7 = new Matrix<Integer>(2,2);
            matrix7.set(0, 0, 1);
            matrix7.set(0, 1, 2);
            matrix7.set(1, 0, 3);
            matrix7.set(1, 1, 4);
            
            Matrix<Integer> matrix8 = new Matrix<Integer>(2,2);
            matrix8.set(0, 0, 1);
            matrix8.set(0, 1, 2);
            matrix8.set(1, 0, 3);
            matrix8.set(1, 1, 4);
            
            if (debug) System.out.println("Matrix multiplication.");
            Matrix<Integer> matrix9 = matrix7.multiply(matrix8);
            if (debug) System.out.println(matrix9);
        }
        
        return true;
    }
    
    private static boolean testPatriciaTrie() {
        {
            //Patricia Trie
            if (debug) System.out.println("Patricia Trie.");
            PatriciaTrie<String> trie = new PatriciaTrie<String>();
            for (int i : unsorted) {
                String string = String.valueOf(i);
                trie.add(string);
                boolean exists = trie.contains(string);
                if (!exists) {
                    System.err.println("YIKES!! "+string+" doesn't exist.");
                    handleError(trie);
                    return false;
                }
            }
            if (debug) System.out.println(trie.toString());

            for (int i : unsorted) {
                String string = String.valueOf(i);
                trie.remove(string);
                boolean exists = trie.contains(string);
                if (exists) {
                    System.err.println("YIKES!! "+string+" still exists.");
                    handleError(trie);
                    return false;
                }
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                String string = String.valueOf(item);
                trie.add(string);
                boolean exists = trie.contains(string);
                if (!exists) {
                    System.err.println("YIKES!! "+string+" doesn't exists.");
                    handleError(trie);
                    return false;
                }
            }
            if (debug) System.out.println(trie.toString());

            for (int i=0; i<unsorted.length; i++) {
                int item = unsorted[i];
                String string = String.valueOf(item);
                trie.remove(string);
                boolean exists = trie.contains(string);
                if (exists) {
                    System.err.println("YIKES!! "+string+" still exists.");
                    handleError(trie);
                    return false;
                }
            }

            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testQueue() {
        {
            // Queue
            if (debug) System.out.println("Queue.");
            Queue<Integer> queue = new Queue<Integer>();
            for (int i=0; i<unsorted.length; i++) {
                int item = unsorted[i];
                queue.enqueue(item);
                boolean exists = (queue.getSize()==i+1);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(queue);
                    return false;
                }
            }
            if (debug) System.out.println(queue.toString());

            int size = queue.getSize();
            for (int i=0; i<size; i++) {
                queue.dequeue();
                boolean exists = (queue.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+i+" indexed item doesn't exists.");
                    handleError(queue);
                    return false;
                }
            }
            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                queue.enqueue(item);
                boolean exists = (queue.getSize()==unsorted.length-i);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(queue);
                    return false;
                }
            }
            if (debug) System.out.println(queue.toString());

            for (int i=0; i<unsorted.length; i++) {
                queue.dequeue();
                boolean exists = (queue.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+i+" indexed item doesn't exists.");
                    handleError(queue);
                    return false;
                }
            }

            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testRadixTree() {
        {
            //Radix Tree (map)
            if (debug) System.out.println("Radix Tree (map).");
            RadixTree<String,Integer> tree = new RadixTree<String,Integer>();
            for (int i : unsorted) {
                String string = String.valueOf(i);
                tree.put(string, i);
                boolean exists = tree.contains(string);
                if (!exists) {
                    System.err.println("YIKES!! "+string+" doesn't exist.");
                    handleError(tree);
                    return false;
                }
            }
            if (debug) System.out.println(tree.toString());

            for (int i : unsorted) {
                String string = String.valueOf(i);
                tree.remove(string);
                boolean exists = tree.contains(string);
                if (exists) {
                    System.err.println("YIKES!! "+string+" still exists.");
                    handleError(tree);
                    return false;
                }
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                String string = String.valueOf(item);
                tree.put(string, i);
                boolean exists = tree.contains(string);
                if (!exists) {
                    System.err.println("YIKES!! "+string+" doesn't exists.");
                    handleError(tree);
                    return false;
                }
            }
            if (debug) System.out.println(tree.toString());

            for (int i=0; i<unsorted.length; i++) {
                int item = unsorted[i];
                String string = String.valueOf(item);
                tree.remove(string);
                boolean exists = tree.contains(string);
                if (exists) {
                    System.err.println("YIKES!! "+string+" still exists.");
                    handleError(tree);
                    return false;
                }
            }

            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testSegmentTree() {
        {
            //Segment tree
            if (debug) System.out.println("Segment Tree.");
            SegmentTree.Segment[] segments = new SegmentTree.Segment[4];
            segments[0] = new SegmentTree.Segment(0,1,0,0,0); //first point in the 0th quadrant
            segments[1] = new SegmentTree.Segment(1,0,1,0,0); //second point in the 1st quadrant
            segments[2] = new SegmentTree.Segment(2,0,0,1,0); //third point in the 2nd quadrant
            segments[3] = new SegmentTree.Segment(3,0,0,0,1); //fourth point in the 3rd quadrant
            SegmentTree tree = new SegmentTree(segments);
            
            SegmentTree.Query query = tree.query(0, 3);
            if (debug) System.out.println(query.quad1+" "+query.quad2+" "+query.quad3+" "+query.quad4);

            tree.update(1, 0, -1, 1, 0); //Move the first point from quadrant one to quadrant two
            tree.update(2, 0, 1, -1, 0); //Move the second point from quadrant two to quadrant one
            tree.update(3, 1, 0, 0, -1); //Move the third point from quadrant third to quadrant zero
            
            query = tree.query(2, 3);
            if (debug) System.out.println(query.quad1+" "+query.quad2+" "+query.quad3+" "+query.quad4);

            tree.update(0, -1, 1, 0, 0); //Move the zeroth point from quadrant zero to quadrant one
            tree.update(1, 0, 0, -1, 1); //Move the first point from quadrant three to quadrant four
            
            query = tree.query(0, 2);
            if (debug) System.out.println(query.quad1+" "+query.quad2+" "+query.quad3+" "+query.quad4);

            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testSkipList() {
        {
            // SkipList
            if (debug) System.out.println("Skip List.");
            SkipList<Integer> list = new SkipList<Integer>();
            for (int i=0; i<unsorted.length; i++) {
                int item = unsorted[i];
                list.add(item);
                boolean exists = (list.getSize()==i+1);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(list);
                    return false;
                }
            }
            if (debug) System.out.println(list.toString());

            for (int i=0; i<unsorted.length; i++) {
                int item = unsorted[i];
                list.remove(item);
                boolean exists = (list.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(list);
                    return false;
                }
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                list.add(item);
                boolean exists = (list.getSize()==unsorted.length-i);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(list);
                    return false;
                }
            }
            if (debug) System.out.println(list.toString());

            for (int i=0; i<unsorted.length; i++) {
                int item = unsorted[i];
                list.remove(item);
                boolean exists = (list.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(list);
                    return false;
                }
            }

            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testSplayTree() {
        {
            //Splay Tree
            if (debug) System.out.println("Splay Tree.");
            SplayTree<Character> splay = new SplayTree<Character>();
            String alphabet = new String("KLMUFGNRSTABHIJVWXYZCDEOPQ");
            for (int i=0; i<alphabet.length(); i++) {
                char c = alphabet.charAt(i);
                splay.add(c);
                boolean exists = splay.contains(c);
                if (!exists) {
                    System.err.println("YIKES!! "+c+" doesn't exists.");
                    handleError(splay);
                    return false;
                }
            }
            if (debug) System.out.println(splay.toString());

            int length = alphabet.length()-1;
            for (int i=0; i<=length; i++) {
                char letter = alphabet.charAt(length-i);
                //Moves node at 'letter' up the tree
                splay.contains(letter);
                splay.contains(letter);
                splay.contains(letter);
                //Remove
                splay.remove(letter);
                boolean exists = splay.contains(letter);
                if (exists) {
                    System.err.println("YIKES!! "+letter+" still exists.");
                    handleError(splay);
                    return false;
                }
            }

            for (int i=alphabet.length()-1; i>=0; i--) {
                char c = alphabet.charAt(i);
                splay.add(c);
                boolean exists = splay.contains(c);
                if (!exists) {
                    System.err.println("YIKES!! "+c+" doesn't exists.");
                    handleError(splay);
                    return false;
                }
            }
            if (debug) System.out.println(splay.toString());

            length = alphabet.length()-1;
            for (int i=0; i<=length; i++) {
                char letter = alphabet.charAt(length-i);
                //Moves node at 'letter' up the tree
                splay.contains(letter);
                splay.contains(letter);
                splay.contains(letter);
                //Remove
                splay.remove(letter);
                boolean exists = splay.contains(letter);
                if (exists) {
                    System.err.println("YIKES!! "+letter+" still exists.");
                    handleError(splay);
                    return false;
                }
            }

            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testStack() {
        {
            // Stack
            if (debug) System.out.println("Stack.");
            Stack<Integer> stack = new Stack<Integer>();
            for (int i=0; i<unsorted.length; i++) {
                int item = unsorted[i];
                stack.push(item);
                boolean exists = (stack.getSize()==i+1);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(stack);
                    return false;                
                }
            }
            if (debug) System.out.println(stack.toString());

            int size = stack.getSize();
            for (int i=0; i<size; i++) {
                stack.pop();
                boolean exists = (stack.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+i+" indexed item doesn't exists.");
                    handleError(stack);
                    return false;
                }
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                stack.push(item);
                boolean exists = (stack.getSize()==unsorted.length-i);
                if (!exists) {
                    System.err.println("YIKES!! "+item+" doesn't exists.");
                    handleError(stack);
                    return false;
                }
            }
            if (debug) System.out.println(stack.toString());

            for (int i=0; i<unsorted.length; i++) {
                stack.pop();
                boolean exists = (stack.getSize()==unsorted.length-(i+1));
                if (!exists) {
                    System.err.println("YIKES!! "+i+" indexed item doesn't exists.");
                    handleError(stack);
                    return false;
                }
            }

            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testSuffixTree() {
        {
            //Suffix Tree
            if (debug) System.out.println("Suffix Tree.");
            String bookkeeper = "bookkeeper";
            SuffixTree<String> tree = new SuffixTree<String>(bookkeeper);
            if (debug) System.out.println(tree.toString());
            if (debug) System.out.println(tree.getSuffixes());

            boolean exists = tree.doesSubStringExist(bookkeeper);
            if (!exists) {
                System.err.println("YIKES!! "+bookkeeper+" doesn't exists.");
                handleError(tree);
                return false;                
            }
            
            String failed = "booker";
            exists = tree.doesSubStringExist(failed);
            if (exists) {
                System.err.println("YIKES!! "+failed+" exists.");
                handleError(tree);
                return false;                
            }

            String pass = "kkee";
            exists = tree.doesSubStringExist(pass);
            if (!exists) {
                System.err.println("YIKES!! "+pass+" doesn't exists.");
                handleError(tree);
                return false;                
            }

            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testSuffixTrie() {
        {
            //Suffix Trie
            if (debug) System.out.println("Suffix Trie.");
            String bookkeeper = "bookkeeper";
            SuffixTrie<String> trie = new SuffixTrie<String>(bookkeeper);
            if (debug) System.out.println(trie.toString());
            if (debug) System.out.println(trie.getSuffixes());

            boolean exists = trie.doesSubStringExist(bookkeeper);
            if (!exists) {
                System.err.println("YIKES!! "+bookkeeper+" doesn't exists.");
                handleError(trie);
                return false;                
            }
            
            String failed = "booker";
            exists = trie.doesSubStringExist(failed);
            if (exists) {
                System.err.println("YIKES!! "+failed+" exists.");
                handleError(trie);
                return false;                
            }

            String pass = "kkee";
            exists = trie.doesSubStringExist(pass);
            if (!exists) {
                System.err.println("YIKES!! "+pass+" doesn't exists.");
                handleError(trie);
                return false;                
            }

            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testTreap() {
        {
            //Treap
            if (debug) System.out.println("Treap.");
            Treap<Character> treap = new Treap<Character>();
            String alphabet = new String("TVWXYABHIJKLMUFGNRSZCDEOPQ");
            for (int i=0; i<alphabet.length(); i++) {
                char c = alphabet.charAt(i);
                treap.add(c);
                boolean exists = treap.contains(c);
                if (!exists) {
                    System.err.println("YIKES!! "+c+" doesn't exists.");
                    handleError(treap);
                    return false;     
                }
            }
            if (debug) System.out.println(treap.toString());

            for (int i=0; i<alphabet.length(); i++) {
                char letter = alphabet.charAt(i);       
                treap.remove(letter);
                boolean exists = treap.contains(letter);
                if (exists) {
                    System.err.println("YIKES!! "+letter+" still exists.");
                    handleError(treap);
                    return false;     
                }
            }

            for (int i=alphabet.length()-1; i>=0; i--) {
                char c = alphabet.charAt(i);
                treap.add(c);
                boolean exists = treap.contains(c);
                if (!exists) {
                    System.err.println("YIKES!! "+c+" doesn't exists.");
                    handleError(treap);
                    return false;     
                }
            }
            if (debug) System.out.println(treap.toString());

            for (int i=0; i<alphabet.length(); i++) {
                char letter = alphabet.charAt(i);       
                treap.remove(letter);
                boolean exists = treap.contains(letter);
                if (exists) {
                    System.err.println("YIKES!! "+letter+" still exists.");
                    handleError(treap);
                    return false;     
                }
            }

            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testTrie() {
        {
            //Trie.
            if (debug) System.out.println("Trie.");
            Trie<String> trie = new Trie<String>();
            for (int i : unsorted) {
                String string = String.valueOf(i);
                trie.add(string);
                boolean exists = trie.contains(string);
                if (!exists) {
                    System.err.println("YIKES!! "+string+" doesn't exist.");
                    handleError(trie);
                    return false;
                }
            }
            if (debug) System.out.println(trie.toString());

            for (int i : unsorted) {
                String string = String.valueOf(i);
                trie.remove(string);
                boolean exists = trie.contains(string);
                if (exists) {
                    System.err.println("YIKES!! "+string+" still exists.");
                    handleError(trie);
                    return false;
                }
            }

            for (int i=unsorted.length-1; i>=0; i--) {
                int item = unsorted[i];
                String string = String.valueOf(item);
                trie.add(string);
                boolean exists = trie.contains(string);
                if (!exists) {
                    System.err.println("YIKES!! "+string+" doesn't exists.");
                    handleError(trie);
                    return false;
                }
            }
            if (debug) System.out.println(trie.toString());

            for (int i=0; i<unsorted.length; i++) {
                int item = unsorted[i];
                String string = String.valueOf(item);
                trie.remove(string);
                boolean exists = trie.contains(string);
                if (exists) {
                    System.err.println("YIKES!! "+string+" still exists.");
                    handleError(trie);
                    return false;
                }
            }

            if (debug) System.out.println();
        }
        
        return true;
    }
    
    private static boolean testTrieMap() {
        {
            //Trie Map
            if (debug) System.out.println("Trie Map.");
            TrieMap<String,Integer> trieMap = new TrieMap<String,Integer>();
            for (int i : unsorted) {
                String string = String.valueOf(i);
                trieMap.put(string, i);
                boolean exists = trieMap.contains(string);
                if (!exists) {
                    System.err.println("YIKES!! "+string+" doesn't exist.");
                    handleError(trieMap);
                    return false;
                }
            }
            if (debug) System.out.println(trieMap.toString());            

            for (int i : unsorted) {
                String string = String.valueOf(i);
                trieMap.remove(string);
                boolean exists = trieMap.contains(string);
                if (exists) {
                    System.err.println("YIKES!! "+string+" still exists.");
                    handleError(trieMap);
                    return false;
                }
            }

            if (debug) System.out.println();
        }
        
        return true;
    }

    private static final String getPathMapString(Graph.Vertex<Integer> start, Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> map) {
        StringBuilder builder = new StringBuilder();
        for (Graph.Vertex<Integer> v : map.keySet()) {
            Graph.CostPathPair<Integer> pair = map.get(v);
            builder.append("From ").append(start.getValue()).append(" to vertex=").append(v.getValue()).append("\n");
            if (pair!=null) builder.append(pair.toString()).append("\n");

        }
        return builder.toString();
    }

    private static final String getPathMapString(Map<Vertex<Integer>, Map<Vertex<Integer>, Set<Edge<Integer>>>> paths) {
        StringBuilder builder = new StringBuilder();
        for (Graph.Vertex<Integer> v : paths.keySet()) {
            Map<Vertex<Integer>, Set<Edge<Integer>>> map = paths.get(v);
            for (Graph.Vertex<Integer> v2 : map.keySet()) {
                builder.append("From=").append(v.getValue()).append(" to=").append(v2.getValue()).append("\n");
                Set<Graph.Edge<Integer>> path = map.get(v2);
                builder.append(path).append("\n");
            }
        }
        return builder.toString();
    }

    private static final String getWeightMapString(Map<Vertex<Integer>, Map<Vertex<Integer>, Integer>> paths) {
        StringBuilder builder = new StringBuilder();
        for (Graph.Vertex<Integer> v : paths.keySet()) {
            Map<Vertex<Integer>, Integer> map = paths.get(v);
            for (Graph.Vertex<Integer> v2 : map.keySet()) {
                builder.append("From=").append(v.getValue()).append(" to=").append(v2.getValue()).append("\n");
                Integer weight = map.get(v2);
                builder.append(weight).append("\n");
            }
        }
        return builder.toString();
    }
}
