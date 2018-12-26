package TreeRelavant;

import java.util.Stack;

/**
 * Created by yetmare on 18-12-25.
 * 折纸问题
 * 题目：请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折１次，压出折痕后展开．
 * 此时折痕是凹下去的，即折痕突起的方向指向纸条的背面．如果从纸条的下边向上方连续对折２次，压出折痕后展开，
 * 此时有三条折痕，从上到下依次是下折痕，下折痕和上折痕．
 * 给定一个输入参数Ｎ，代表纸条都从下边向上方连续对折N次，请从上到下打印所有折痕的方向．
 * 例如：　N=1时，打印down; N=2时，打印: down down up
 * 策略：中序遍历一个根节点是down,每个节点左孩子是down，右孩子是up的二叉树
 */
public class o5_PaperFolding {
    public static class Node {
        public String value;
        public Node left;
        public Node right;
        public Node(String data) {
            this.value = data;
        }
    }

    public static void printMidOrderTree(int N) {
        if(N<=0) {
            return;
        }

        Node head = new Node("down");
        head.left = generateTree("down", 2, N);
        head.right = generateTree("up", 2, N);
        printTree(head);
        System.out.println("in-order Recur: ");
        printTreeByMidOrder1(head);
        System.out.println();

        System.out.println("in-order no Recur: ");
        printTreeByMidOrder2(head);

        System.out.println("in-order Morris: ");
        printTreeByMidOrder3(head);

        System.out.println();
    }

    public static Node generateTree(String tag, int curLevel, int totalLevel) {
        if(curLevel > totalLevel) {
            return null;
        }
        Node curNode = new Node(tag);
        curNode.left = generateTree("down", curLevel+1, totalLevel);
        curNode.right = generateTree("up", curLevel+1, totalLevel);
        return curNode;
    }

    // 中序遍历二叉树：　递归方式
    public static void printTreeByMidOrder1(Node head) {
        if(head == null) {
            return;
        }
        printTreeByMidOrder1(head.left);
        System.out.print(head.value+" ");
        printTreeByMidOrder1(head.right);
    }

    //　中序遍历二叉树：非递归方式
    public static void printTreeByMidOrder2(Node head) {
        if(head == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head!= null) {
            if(head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.value+" ");
                head = head.right;
            }
        }
        System.out.println();
    }

    // 中序遍历二叉树：Morris遍历
    // 用指针来记录指向上层节点的方向
    public static void printTreeByMidOrder3(Node head) {
        if(head == null) {
            return;
        }

        Node cur1 = head;   // 指向当前考察的节点
        Node cur2 = null;   // 找到以cur1为根节点的左子树的最右节点 并让其指向cur1
        while (cur1 != null) {
            cur2 = cur1.left;
            if(cur2 !=null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                // 此时cur2.right为null, 而cur2就是当前以cur1为根节点的最右节点
                // 这里判断一下cur2.right是否已经指向了cur1 如果没指向cu2,就指向cur1; 如果指向了　就恢复为null
                if(cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;   // cur1向左移动（即不断的让左子树的最右节点指向自己）
                    continue;
                } else {
                    cur2.right = null;
                }
            }

            System.out.print(cur1.value+" ");
            cur1 = cur1.right;
        }
    }

    public static void printTree(Node head) {
        if(head == null) {
            return;
        }

        System.out.println("print tree: ");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        // 定义递归结束的BaseCase
        if(head == null) {
            return;
        }
        // 如果当前节点有右子树　就先打印右子树
        printInOrder(head.right, height+1, "v", len);
        // 打印当前节点　先构造当前节点的打印内容
        String value = to+head.value+to;
        int lenM = value.length();
        int leftLen = (len-lenM)>>1;        // 左边的空格长度
        int rightLen = len-lenM-leftLen;    // 右边的空格长度
        value = getSpace(leftLen)+value+getSpace(rightLen);
        System.out.println(getSpace(height*len)+value);
        printInOrder(head.left, height+1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buffer = new StringBuffer("");
        for(int i=0; i<num; i++) {
            buffer.append(space);
        }
        return buffer.toString();
    }

    // 中序打印折纸Ｎ次后的结构
    public static void printAllFolds(int N) {
        if(N <= 0) {
            return;
        }

        printResult(1, N, true);
    }

    public static void printResult(int curLevel, int totalLevel, boolean isDown) {
        if(curLevel > totalLevel) {
            return;
        }

        printResult(curLevel+1, totalLevel, true);
        System.out.print(isDown ? "down " : "up ");
        printResult(curLevel+1, totalLevel, false);
    }

    public static void main(String[] args) {
        printMidOrderTree(4);
        System.out.println("target answer: ");
        printAllFolds(4);
    }
}
