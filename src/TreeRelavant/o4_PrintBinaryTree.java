package TreeRelavant;

/**
 * Created by yetmare on 18-12-25.
 * 打印策略：
 * １. 首先要规定节点打印时占用的同一长度。整型值占用长度最长的值是Integer.MIN_VALUE=-2147483648　11位，前后均加上后缀以后是13位
 * 为了在打印之后更好地区分，再把前面加上两个空格，后面加上两个空格。至此，约定打印每一个节点的时候，必须让每一个节点在打印时占用长度都为17
 * 如果不足17，前后要用空格补齐
 * 2. 打印的整体过程：结合了二叉树先右子树、再根节点、最后左子树的递归遍历过程。
 * 如果递归到一个节点，首先遍历它的右子树。右子树遍历结束后，回到这个节点。如果这个节点所在层为l,那么先打印l×17个空格（不换行），
 * 然后开始制作该节点的打印内容，这个内容当然包括节点的值，以及确定的前后缀字符。如果该节点是父节点的右孩子，前后缀为"v",
 * 如果是其父节点的左孩子，前后缀为"^"，如果是头节点，前后缀为"H"。最后在前后分别贴上数量几乎一致的空格，占用长度为17的打印内容就制作完了
 * 打印这个内容后换行。最后进行左子树的遍历过程。
 */
public class o4_PrintBinaryTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
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

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printTree(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printTree(head);
    }
}
