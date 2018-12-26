package TreeRelavant;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by yetmare on 18-12-25.
 * 判断一棵树是否是搜索二叉树、判断一棵树是否是完全二叉树
 * 搜索二叉树：中序遍历是否是升序的　如果中序遍历不是升序的　那么就不是搜索二叉树
 * 完全二叉树：层序遍历　标识tag　判断节点是否是叶子节点
 */
public class o6_IsBSTAndCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    // 判断一棵树是否是搜索二叉树
    // 先序遍历　记录遍历过的节点　如果当前节点的值比上一个节点值小　则不是搜索二叉树
    // 由于要与遍历过的上一个节点做比较　所以这里要用非递归中序遍历　否则就酸爽了..
    public static boolean isBST(Node head) {
        if(head == null) {
            // 认为空树是搜索二叉树
            return true;
        }

        Stack<Node> stack = new Stack<>();
        int pre = Integer.MIN_VALUE;
        // 循环条件：head不空　就一直把左孩子往栈里压; head空就出栈　出栈才就打印
        while (head != null || !stack.isEmpty()) {
            if(head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.value+" ");
                if(head.value < pre) {
                    return false;
                }
                pre = head.value;
                head = head.right;
            }
        }
        return true;
    }

    // TODO: Morris中序遍历　判断一棵数是否是搜索二叉树...
    public static boolean isBSTWithRecur(Node head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        Node pre = null;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if (pre != null && pre.value > cur1.value) {
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }

    // 判断一棵树是否是完全二叉树
    // 策略：层序遍历
    public static boolean isCBT(Node head) {
        if(head == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        boolean tag = false;
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 先把左孩子为null　右孩子不为null的排除掉
            if(cur.left == null && cur.right != null) {
                return false;
            }

            // 接下来都是会保证每个队列中的节点　如果该节点不是叶子节点　则该节点必定有左孩子
            // 如果tag已变　而当前节点却不是叶子节点　则不是完全二叉树
            if((tag && (cur.left != null || cur.right != null))) {
                return false;
            }
            if(cur.left != null) {
                queue.offer(cur.left);
            }
            if(cur.right != null) {
                queue.offer(cur.right);
            } else {
                // 在左孩子不为null的情况下　第一个右孩子为null的节点以后的所有节点都必须是叶子节点了
                tag = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        System.out.println(isBST(head));
        System.out.println(isBSTWithRecur(head));
        System.out.println(isCBT(head));

    }
}
