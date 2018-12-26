package TreeRelavant;

/**
 * Created by yetmare on 18-12-25.
 * 在二叉树中找到一个节点的后继节点
 * 题目：现在有一种新的二叉树节点类型如下：
 * public class TreeNode {
 *      public int value;
 *      public TreeNode left;
 *      public TreeNode right;
 *      public TreeNode parent;
 *      public TreeNode(int data) { this.value = data; }}
 * 该结构比普通二叉树节点多了一个指向父节点的parent指针。
 * 假设有一颗Node类型的节点组成的二叉树，树中每个节点的parent指针都正确地指向自己的父节点
 * 头节点的parent指向null。只给一个二叉树中某个节点node,请实现返回node的后继节点的函数。
 * 在二叉树的中序遍历的序列中，node的下一个节点叫做node的后继节点
 *
 * 策略：对当前节点判断是否有右子树：
 * １. 如果当前节点有右子树，那么它的后继节点就是它右子树最左的节点
 * ２. 如果当前节点没有右子树，则从当前节点向上回溯　直到找到第一个parent,　使得当前节点所在的分支是该parent的左子树（如果找不到就返回null)
 */
public class o2_SuccessorNode {
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;
        public TreeNode(int data) {
            this.value = data;
        }
    }

   public static TreeNode getSuccessorNode(TreeNode curNode) {
        if(curNode == null) {
            return curNode;
        }

        if(curNode.right != null) {
            // 右子树不为null　则当前节点的后继节点就是其右子树最左的节点
            TreeNode nextNode = curNode.right;
            while (nextNode.left != null) {
                nextNode = nextNode.left;
            }
            return nextNode;
        } else {
            // 右子树为null 就不断向上找parent
            TreeNode parent = curNode.parent;
            while(parent != null && parent.left != curNode) {
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
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}
