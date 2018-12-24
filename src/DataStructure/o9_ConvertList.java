package DataStructure;

/**
 * Created by yetmare on 18-12-23.
 * 反转单向和双向链表
 * 题目：分别实现反转单向链表和反转双向链表的函数
 * 要求：如果链表长度为Ｎ，时间复杂的要求为O(N),额外空间复杂度O(1)
 */
public class o9_ConvertList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 反转单向链表
    public static Node reverseList(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

        // 单向链表的反转需要如下３个变量
        Node preNode = null;    // 已经被反转后的链表的头节点
        Node curNode = head;    // 当前节点要被反转的节点
        Node nextNode = null;   // 保存下一个待反转的链表的头节点　否则会找不到
        while(curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }

    public static void printLinkedList(Node head) {
        System.out.print("Linked List:");
        while (head != null) {
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int value) {
            this.value = value;
        }
    }

    // 反转双向链表
    public static DoubleNode convertList(DoubleNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        DoubleNode preNode = null;
        DoubleNode curNode = head;
        DoubleNode nextNode = null;
        while(curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            curNode.last = nextNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }

    // 打印双向链表：先从头打印next直到尾部; 再从尾部打印last直到头部
    public static void printDoubleLinkedList(DoubleNode head) {
        System.out.println("print double linked list: ");
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.value+" ");
            end = head;
            head = head.next;
        }
        System.out.print("| ");
        while(end != null) {
            System.out.print(end.value+" ");
            end = end.last;
        }
        System.out.println();
    }

    public static void main(String[] ars) {
        Node head1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;

        Node res = reverseList(head1);
        printLinkedList(res);

        DoubleNode head2 = new DoubleNode(1);
        head2.next = new DoubleNode(2);
        head2.next.last = head2;
        head2.next.next = new DoubleNode(3);
        head2.next.next.last = head2.next;
        head2.next.next.next = new DoubleNode(4);
        head2.next.next.next.last = head2.next.next;
        printDoubleLinkedList(head2);
    }
}
