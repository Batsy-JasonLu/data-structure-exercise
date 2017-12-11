package com.lu.btree;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 
 * @author lu
 * 
 * @description 用tarjan算法与并查集解决二叉树节点间最近公共祖先的批量查询问题。
 *
 */
public class TarjanSearchTwoNodesLatestParent {

    public TNode[] searchParent(TNode head, Query[] queries) {
        
        return new Tarjan().query(head, queries);
    }
    
    /* 二叉树如图：      1
                /    \
               2      3
              / \     /  
             4   5   6    
                / \ /       
               7  8 9      
    */
    public static void main(String[] args) {
        TNode node1 = new TNode(1);
        TNode node2 = new TNode(2);
        TNode node3 = new TNode(3);
        TNode node4 = new TNode(4);
        TNode node5 = new TNode(5);
        TNode node6 = new TNode(6);
        TNode node7 = new TNode(7);
        TNode node8 = new TNode(8);
        TNode node9 = new TNode(9);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node5.left = node7;
        node5.right = node8;
        node3.left = node6;
        node6.left = node9;

        TarjanSearchTwoNodesLatestParent main = new TarjanSearchTwoNodesLatestParent();
        Query[] queries = new Query[7];
        queries[0] = new Query(node4, node7);
        queries[1] = new Query(node7, node8);
        queries[2] = new Query(node8, node9);
        queries[3] = new Query(node9, node3);
        queries[4] = new Query(node6, node6);
        queries[5] = new Query(null, node5);
        queries[6] = new Query(null, null);
        TNode[] res = main.searchParent(node1, queries);
        
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] != null ? res[i].value : res[i]);
            System.out.print(" ");
        }
    }

}

// tarjan算法。
class Tarjan {
    private HashMap<TNode, LinkedList<TNode>> queryMap;
    private HashMap<TNode, LinkedList<Integer>> indexMap;
    private HashMap<TNode, TNode> ancestorMap;
    private DisjointSets sets;
    
    public Tarjan() {
        queryMap = new HashMap<TNode, LinkedList<TNode>>();
        indexMap = new HashMap<TNode, LinkedList<Integer>>();
        ancestorMap = new HashMap<TNode, TNode>();
        sets = new DisjointSets();
    }
    
    public TNode[] query(TNode node, Query[] queries) {
        TNode[] answer = new TNode[queries.length];
        setQueries(queries, answer);
        sets.makeSets(node);
        setAnswer(node, answer);
        
        return answer;
    }
    
    // 设置查询数组，如果两节点相等或者其中一个为null，则祖先直接为其中一个；否则在querymap和indexmap中添加未设置过的节点。
    private void setQueries(Query[] queries, TNode[] answer) {
        TNode o1 = null;
        TNode o2 = null;
        
        for (int i = 0; i != answer.length; i++) {
            o1 = queries[i].o1;
            o2 = queries[i].o2;
            
            if (o1 == o2 || o1 == null || o2 == null) {
                answer[i] = o1 != null ? o1 : o2;
            } else {
                if (!queryMap.containsKey(o1)) {
                    queryMap.put(o1, new LinkedList<TNode>());
                    indexMap.put(o1, new LinkedList<Integer>());
                }
                
                if (!queryMap.containsKey(o2)) {
                    queryMap.put(o2, new LinkedList<TNode>());
                    indexMap.put(o2, new LinkedList<Integer>());
                }
                
                queryMap.get(o1).add(o2);
                indexMap.get(o1).add(i);
                queryMap.get(o2).add(o1);
                indexMap.get(o2).add(i);
            }
        }
    }
    
    // 设置答案数组，与并查集的合并方法一起，得到ancestormap，并把里面的值放入到answer数组中返回。
    private void setAnswer(TNode node, TNode[] answer) {
        if (node == null) {
            return;
        }
        
        setAnswer(node.left, answer);
        sets.union(node.left, node);
        ancestorMap.put(sets.findFather(node), node);
        setAnswer(node.right, answer);
        sets.union(node.right, node);
        ancestorMap.put(sets.findFather(node), node);
        LinkedList<TNode> nList = queryMap.get(node);
        LinkedList<Integer> iList = indexMap.get(node);
        TNode n = null;
        TNode nFather = null;
        int index = 0;
        
        while (nList != null && !nList.isEmpty()) {
            n = nList.poll();
            index = iList.poll();
            
            nFather = sets.findFather(n);
            if (ancestorMap.containsKey(nFather)) {
                answer[index] = ancestorMap.get(nFather);
            }
        }

    }
    
}


/**
 * @description 并查集，是集合的集合。用于querymap和indexmap的操作中，集合的生成、合并，它单次查询集合的操作的平均时间复杂度为O(n+m)。
 *
 */
class DisjointSets {
    public HashMap<TNode, TNode> fatherMap;
    public HashMap<TNode, Integer> rankMap;
    
    // 构造，fathermap是集合祖先的集合。rankmap是改节点下面有多少层节点 。
    public DisjointSets() {
        fatherMap = new HashMap<TNode, TNode>();
        rankMap = new HashMap<TNode, Integer>();
    }
    
    // 初始化，先令所有的节点所属的集合为它自己，而查找节点属于哪个集合，其实是找这个节点所在集合的代表节点（即当集合有多个节点时，最上层的节点叫代表节点）。
    public void makeSets(TNode node) {
        fatherMap.clear();
        rankMap.clear();
        preOrderMake(node);
    }
    
    public void preOrderMake(TNode node) {
        if (node == null) {
            return;
        }
        
        fatherMap.put(node, node);
        rankMap.put(node, 0);
        preOrderMake(node.left);
        preOrderMake(node.right);
    }
    
    // 上面提到的查找节点的代表节点，同时会把途经的节点的代表节点都找到。
    public TNode findFather(TNode node) {
        TNode father = fatherMap.get(node);
        
        if (father != node) {
            father = findFather(father);
        }
        
        fatherMap.put(node, father);
        return father;
    }
    
    // 该方法是用来合并集合，实际上是找到两集合的代表节点，然后，如果相等，说明在一个集合里，不用合并；如果不相等，根据rank来确定代表节点。
    public void union(TNode a, TNode b) {
        if (a == null || b == null) {
            return;
        }
        
        TNode aFather = findFather(a);
        TNode bFather = findFather(b);
        
        if (aFather != bFather) {
            int aFatherRank = rankMap.get(aFather);
            int bFatherRank = rankMap.get(bFather);
            
            if (aFatherRank > bFatherRank) {
                fatherMap.put(bFather, aFather);
            } else if (aFatherRank < bFatherRank) {
                fatherMap.put(aFather, bFather);
            } else {
                fatherMap.put(bFather, aFather);
                rankMap.put(aFather, aFatherRank + 1);
            }
        }
    }
}

class Query {
    
    public TNode o1;
    public TNode o2;
    
    public Query(TNode o1, TNode o2) {
        this.o1 = o1;
        this.o2 = o2;
    }
}