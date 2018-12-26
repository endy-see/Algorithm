package TreeRelavant;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yetmare on 18-12-25.
 * 二叉树的序列化与反序列化
 * 策略：通过递归的方式　遍历二叉树实现序列化（比如先序遍历）　怎么序列化的就怎么反序列化　
 */
public class o4_SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    // 按照先序遍历的顺序　将一棵二叉树序列化
    public static String serialByPre(Node head) {
        if(head == null) {
            return "#_";
        }

        String res = head.value+"_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // 对序列化的字符串按照先序遍历的方式还原成二叉树
    // 怎么序列化的　就怎么反序列化　果然说的一点都没错
    public static Node reconByPreString(String preOrderStr) {
        if(preOrderStr == null || preOrderStr.length() < 1) {
            return null;
        }

        String[] values = preOrderStr.split("_");
        Queue<String> queue = new LinkedList<>();
        // 将所有序列化的值加入队列　此处用队列是保存了一种顺序：先被序列化值，也将先被反序列化出来
        for(int i=0; i<values.length; i++) {
            queue.offer(values[i]);
        }
        return reconTree(queue);
    }

    public static Node reconTree(Queue<String> queue) {
        if(queue.isEmpty()) {
            return null;
        }

        String value = queue.poll();
        if(value.equals("#"))  {
            return null;
        }

        Node head = new Node(Integer.valueOf(value));
        head.left = reconTree(queue);
        head.right = reconTree(queue);
        return head;
    }

    // 按层序列化
    public static String serialByLevel(Node  head) {
        if(head == null) {
            return "#_";
        }

        Queue<Node> queue = new LinkedList<>();
        String res = head.value+"_";
        queue.offer(head);
        // 因为序列化是要考虑null的　而进入队列中的元素都是非空的了（已经把null排除了）　
        // 所以序列化的过程要从入队时记录
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if(cur.left != null) {
                res += cur.left.value+"_";
                queue.offer(cur.left);
            } else {
                res += "#_";
            }

            if(cur.right != null) {
                res += cur.right.value+"_";
                queue.offer(cur.right);
            } else {
                res += "#_";
            }
        }
        return res;
    }

    // 把层序遍历树所得的序列化字符串重新恢复成一棵树
    public static Node reconByLevelString(String levelStr) {
        if(levelStr == null || levelStr.length()<3) {
            return null;
        }

        String[] values = levelStr.split("_");
        Queue<Node> queue = new LinkedList<>();

        int index = 0;
        Node head = generateNodeByString(values[index++]);
        if(head != null) {
            queue.offer(head);
        }

        Node curNode = null;
        while (!queue.isEmpty()) {
            curNode = queue.poll();
            curNode.left = generateNodeByString(values[index++]);
            curNode.right = generateNodeByString(values[index++]);
            if(curNode.left != null) {
                queue.offer(curNode.left);
            }
            if(curNode.right != null) {
                queue.offer(curNode.right);
            }
        }
        return head;
    }

    public static Node generateNodeByString(String value) {
        if(value.equals("#")) {
            return null;
        }
        return new Node(Integer.valueOf(value));
    }


    // 可视化打印一棵二叉树
    public static void printTree(Node head) {
        if(head == null) {
            return;
        }

        // 中序遍历：右、中、左　17
        System.out.println("print a binary tree:   ");
        visualizeBinaryTree(head, 0, "H", 17);
        System.out.println();
    }

    public static void visualizeBinaryTree(Node head, int height, String tag, int len) {
        // 递归打印　所以需要设定BaseCase
        if(head == null) {
            return;
        }

        // 先递归完右子树
        visualizeBinaryTree(head.right, height+1, "v", len);
        // 再打印（即最优边的节点就是可视化后最上边的节点）
        String value = tag+head.value+tag;
        int midLen = value.length();
        int leftLen = (len-midLen) >> 1;
        int rightLen = len - midLen - leftLen;
        value = getSpace(leftLen)+value+getSpace(rightLen);
        System.out.println(getSpace(height*len)+value);
        // 在递归打印左子树（即可视化后靠近下方的节点））
        visualizeBinaryTree(head.left, height+1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer sb = new StringBuffer("");
        for(int i=0; i<num; i++) {
            sb.append(space);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Node head = null;
        printTree(head);

        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

    }
}
