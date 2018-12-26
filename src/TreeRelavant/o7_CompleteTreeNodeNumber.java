package TreeRelavant;

/**
 * Created by yetmare on 18-12-26.
 * 已知一棵完全二叉树，求其节点的个数
 * 要求时间复杂度低于O(N)，N为这棵树的节点个数
 * 策略：
 * １．先获取完全二叉树的高度
 * 2. 如果右子树不为null, 就获取右子树的高度　
 * 如果右子树的高度等于完全二叉树的高度　则左子树是满二叉树　可以用公式算出节点个数　再对右子树递归
 * 如果右子树的高度小于完全二叉树的高度　则右子树是满二叉树　可以用公司算出节点个数　再对左子树递归
 */
public class o7_CompleteTreeNodeNumber {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    public static int nodeNum(Node head) {
        if(head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    public static int bs(Node head, int curH, int mostLeftHeight) {
        if(curH == mostLeftHeight) {
            return 1;
        }

        if(mostLeftLevel(head.right, curH+1) < mostLeftHeight) {
            // 说明右子树是满的　只需公式算出右子树中节点个数再加上左子树节点个数即可
            return (1<<mostLeftHeight-curH-1)+bs(head.left, curH+1, mostLeftHeight);
        } else {
            // 说明左子树是满的　只需公式算出左子树中节点个数再加上右子树节点个数即可
            return (1<<mostLeftHeight-curH)+bs(head.right, curH+1, mostLeftHeight);
        }
    }

    // 获取从当前高度一直到当前子树最左节点的距离
    public static int mostLeftLevel(Node head, int curHeight) {
        while (head != null) {
            curHeight++;
            head = head.left;
        }
        return curHeight-1;
    }
}
