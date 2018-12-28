package HashRelevant;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yetmare on 18-12-28.
 * 认识并查集结构
 * 从名字可以看出，并查集有两种操作：
 * １．合并两个节点所在的并查集
 * ２．查询两个节点是否属于一个并查集（是否具有相同的代表节点）　－> 做到O(1)
 * 并查集是一种不相交集合的数据结构，每个集合通过一个代表来标识，代表就是集合中的某个元素．
 * 数据结构的基本操作决定了它的应用范围，对并查集而言，一个简单的应用就是判断无向图的连通分量个数
 * 或者判断无向图中任何两个顶点是否连通
 */
public class o2_UnionFind {
    public class Node {
        // whatever you like
        public int value;
        public Node(int data) {
            this.value = data;
        }
    }

    public class UnionFind {
        private HashMap<Node, Node> fatherMap;      // 记录每个node节点的代表节点
        private HashMap<Node, Integer> sizeMap;     // 记录每个节点所在集合中节点的个数（该节点通常指代表节点了）

        public UnionFind(List<Node> nodeList) {
            makeSets(nodeList);
        }

        private void makeSets(List<Node> nodeList) {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            // 对nodeList中的每个节点　创建以自己为代表节点的集合
            for(Node node: nodeList) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 合并操作：合并两个节点a和b所在的集合
        public void union(Node a, Node b) {
            if(a==null || b==null) {
                return;
            }

            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if(aHead != bHead) {
                // 合并　并将链表形式的连接用递归的方式给打平（让链下的每个节点直接连向代表节点）
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                if(aSize <= bSize) {
                    // 把a所在集合下的所有元素都挂到b链下
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSize + bSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSize + bSize);
                }
            }
        }

        // 查询两个节点是否属于同一个集合　即查询两个节点所在集合的代表节点是否相同
        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        // 查找node节点所在集合的代表节点（包含了打平链表的过程） 同时让沿途的父节点都指向最终的代表节点
        public Node findHead(Node node) {
            Node father = this.fatherMap.get(node);
            if(father != node) {
                father = findHead(father);
            }
            fatherMap.put(node, father);
            return father;
        }
    }
}
