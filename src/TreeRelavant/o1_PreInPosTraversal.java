package TreeRelavant;

import java.util.Stack;

/**
 * Created by yetmare on 18-12-24.
 * 分别用递归和非递归的方式实现二叉树的先序、中序、后序遍历
 */
public class o1_PreInPosTraversal {
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int data) {
            this.value = data;
        }
    }

    // 递归方式先序遍历二叉树
    public static void preOrderRecur(TreeNode root) {
        if(root == null) {
            return;
        }
        System.out.print(root.value+" ");
        preOrderRecur(root.left);
        preOrderRecur(root.right);
    }

    // 递归方式中序遍历二叉树
    public static void inOrderRecur(TreeNode root) {
        if(root == null) {
            return;
        }
        inOrderRecur(root.left);
        System.out.print(root.value+" ");
        inOrderRecur(root.right);
    }

    // 递归方式后序遍历二叉树
    public static void posOrderRecur(TreeNode root) {
        if(root == null) {
            return;
        }
        posOrderRecur(root.left);
        posOrderRecur(root.right);
        System.out.print(root.value+" ");
    }

    // 非递归方式实现前序遍历二叉树
    // 策略：先入根节点　再弹根节点 弹出就打印; 有右先压右　没右再压左
    public static void preOrderUnRecur(TreeNode root) {
        if(root == null) {
            return;
        }

        System.out.print("pre-order: ");
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            System.out.print(root.value + " ");
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        System.out.println();
    }

    // 非递归方式实现中序遍历
    // 策略：有左孩子，就将左孩子全部入栈; 没左孩子　就出栈打印　开始考虑　右孩子（还是优先将右子树的左右左孩子依次入栈）
    public static void inOrderUnRecur(TreeNode root) {
        if(root == null) {
            return;
        }

        System.out.print("in-order: ");
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if(root != null) {
                // 不断将左孩子压入栈中
                stack.push(root);
                root = root.left;
            } else {
                // 当左孩子全部压入完毕时　开始弹出
                root = stack.pop();
                System.out.print(root.value+" ");
                root = root.right;
            }
        }
        System.out.println();
    }

    // 非递归方式实现后序遍历
    public static void posOrderUnRecur(TreeNode root) {
        if(root == null) {
            return;
        }

        System.out.print("pos-order: ");
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            root = stack1.pop();
            stack2.push(root);
            if(root.left != null) {
                stack1.push(root.left);
            }
            if(root.right != null) {
                stack1.push(root.right);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur(head);
//        posOrderUnRecur2(head);

    }
}
