package TreeRelavant;

/**
 * Created by yetmare on 18-12-25.
 * 在一棵二叉树中找到一个节点的前驱节点（具体描述请参考找后继节点）
 * 策略（画一棵树分析）：
 * 判断当前节点是否有左子树　
 * １. 如果当前节点有左子树　则当前节点的前驱节点一定是其左子树最右边的节点
 * 2. 如果当前节点没有左子树　则从当前节点向上回溯　直到找到第一个parent, 使得当前节点所在的分支是该parent的右子树（如果找不到就返回null）
 */
public class o3_PrecursorNode {
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;
        public TreeNode(int data) {
            this.value = data;
        }
    }

    // 找给定节点的前驱节点
    public static TreeNode getPrecursorNode(TreeNode curNode) {
        if(curNode == null) {
            return curNode;
        }

        if(curNode.left != null) {
            TreeNode nextNode = curNode.left;
            while (nextNode.right != null) {
                nextNode = nextNode.right;
            }
            return nextNode;
        } else {
            TreeNode parent = curNode.parent;
            while (parent!=null && parent.right != curNode) {
                curNode = parent;
                parent = curNode.parent;
            }
            return parent;
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(6);
        head.parent = null;
        head.left = new TreeNode(3);
        head.left.parent = head;
        head.left.left = new TreeNode(1);
        head.left.left.parent = head.left;
        head.left.left.right = new TreeNode(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new TreeNode(4);
        head.left.right.parent = head.left;
        head.left.right.right = new TreeNode(5);
        head.left.right.right.parent = head.left.right;
        head.right = new TreeNode(9);
        head.right.parent = head;
        head.right.left = new TreeNode(8);
        head.right.left.parent = head.right;
        head.right.left.left = new TreeNode(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new TreeNode(10);
        head.right.right.parent = head.right;

        TreeNode test = head.left.left;
        System.out.println(test.value + " pre: " + getPrecursorNode(test));
        test = head.left.left.right;
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.left;
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head;
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.right;
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " pre: " + getPrecursorNode(test).value);
    }
}
