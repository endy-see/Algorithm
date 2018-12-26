package TreeRelavant;

/**
 * Created by yetmare on 18-12-25.
 * 判断一棵二叉树是否是平衡二叉树（左右子树的高度差不超过１）
 * 本题属于高度套路的问题　关键是整理出返回信息
 */
public class o5_IsBalancedTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    // 由于要不断获取每个节点是否平衡，及该节点的高度　
    // 所以这里封装一个数据结构，该结构包括以当前节点为根节点的子树是否平衡，及当前子树的高度
    public static class Data {
        public boolean isBalance;
        public int height;
        public Data(boolean isB, int h) {
            this.isBalance = isB;
            this.height = h;
        }
    }

    public static boolean isBalance(Node head) {
        if(head == null || head.left==null && head.right==null) {
            return true;
        }

        return process(head).isBalance;
    }

    // 类后序遍历：先获取当前节点的左子树是否平衡及高度　再获取右子树是否平衡及高度
    // 然后再总结出当前节点是否平衡及高度，供上层继续转下去
    public static Data process(Node head) {
        if(head == null) {
            // 空树高度为０！
            return new Data(true, 0);
        }

        Data leftData = process(head.left);
        if(!leftData.isBalance) {
            // 只要有一棵子树不平衡　那么整棵树就不平衡了　此时高度信息已经没有意义了
            return new Data(false, 0);
        }
        Data rightData = process(head.right);
        if(!rightData.isBalance) {
            return new Data(false, 0);
        }

        if(Math.abs(leftData.height-rightData.height) > 1) {
            return new Data(false, 0);
        }
        return new Data(true, Math.max(leftData.height, rightData.height)+1);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalance(head));

    }
}
