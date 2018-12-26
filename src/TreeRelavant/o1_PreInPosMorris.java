package TreeRelavant;

/**
 * Created by yetmare on 18-12-26.
 * 二叉树的Morris遍历
 */
public class o1_PreInPosMorris {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    // Morris中序遍历: 仅用有限几个变量就搞定了由下回到上的难题
    // 策略：让当前节点左子树最右的节点指向自己　然后自己向左移动直到为空时开始打印　
    // 打印时如果指针是指向自己的就打印
    public static void inOrderMorris(Node head) {
        if(head == null) {
            return;
        }

        Node cur1 = head;
        Node cur2 = null;
        // 循环截止条件是整棵树都遍历完了
        while (cur1 != null) {
            cur2 = cur1.left;
            if(cur2 != null) {
                // 找左子树最右节点
                while(cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }

                if(cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }

            System.out.print(cur1.value+" ");
            cur1 = cur1.right;
        }
    }

    // Morris先序遍历：　类中序遍历　只是打印时机不同　连线时打印（根节点）＋拆线前打印（左孩子）
    // 总体策略还是：找每个节点左子树最右节点的right指针指向自己，然后自己往左孩子上移动
    public static void preOrderMorris(Node head) {
        if(head == null) {
            return;
        }

        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if(cur2 != null) {
                while (cur2.right!=null && cur2.right!=cur1) {
                    cur2 = cur2.right;
                }

                // 此时的cur2有两种情况：一种是cur2.right=null　即cur2也叶子节点　此时将cur2.right指向cur1即可
                // 另一种情况是cur2.right=cur1　即cur2.right已经指向过根节点了　这里需要给恢复一下
                if(cur2.right == null) {
                    cur2.right = cur1;
                    // 由于是先序遍历　所以这里在指向连线时就直接打印
                    System.out.print(cur1.value+" ");
                    cur1 = cur1.left;  // cur1移向自己的左孩子
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {
                // 说明到达叶子节点了　直接打印即可　因为后续不会再有节点指向自己了（或者理解为null指向自己也行）
                System.out.print(cur1.value+" ");
            }
            cur1 = cur1.right;
        }
        System.out.println();
    }

    // Morris后序遍历：也是在中序遍历的基础上改进而来的
    // 策略：打印时机是在拆线的时候　每次逆序打印左子树的右边界（因为是后序嘛）　由于需要逆序打印　所以涉及到类似链表反转的问题　
    // 即把左子树的右边界逆序后进行打印　由于整棵树不再是任何树的子树了　所以它的右边界要在最后打印一下
    public static void posOrderMorris(Node head) {
        if(head == null) {
            return;
        }

        Node cur1 = head;
        Node cur2 = null;
        //　无论何种遍历　都要先新建连线过程　以备后序找得到下一个节点
        while (cur1 != null) {
            cur2 = cur1.left;  // cur2是cur1的左子树
            if(cur2 != null) {
                // 找左子树的右边界
                while(cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }

                // 能走到这一步　要么是cur2到了左子树的右边界了　要么是cur2已经指向过了
                if(cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
    }

    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value+" ");
            cur = cur.right;
        }

        reverseEdge(tail);
    }

    public static Node reverseEdge(Node head) {
        Node pre = null;
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
