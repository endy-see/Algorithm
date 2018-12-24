package DataStructure;

import java.util.HashMap;

/**
 * Created by yetmare on 18-12-24.
 * 复制含有随机指针节点的链表
 * 题目：一种特殊的链表节点描述如下：
 * public class Node {
 * public int value;
 * public Node next;
 * public Node rand;
 * public Node(int data) { this.value = data; }}
 * Node类中的value是节点值，next指针和正常单链表中next指针意义一样，都指向下一个节点，也可能指向null
 * 给定一个由Node节点类型组成的无环单链表的头节点head,请实现一个函数完成这个链表中所有结构的复制，并返回复制的新链表的头节点。
 *
 * 进阶：不使用额外数据结构，只用有限几个变量，且在时间复杂度为O(N)内完成原问题要实现的函数
 */
public class o13_CopyListWithRandom {
    public static class Node{
        public int value;
        public Node next;
        public Node rand;
        public Node(int data) {
            value = data;
        }
    }

    // 方法一：借助哈希表来保存原链表节点和复制节点之间的对应关系
    // 然后根据原链表的连接方式　把哈希表中的value都连接起来
    public static Node copyListWithRand1(Node head) {
        if(head == null) {
            return head;
        }

        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            Node copyNode = new Node(cur.value);
            map.put(cur, copyNode);
            cur = cur.next;
        }

        // 连接拷贝节点
        cur = head;
        while(cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("print order: ");
        while (cur != null) {
            System.out.print(cur.value+" ");
            cur = cur.next;
        }
        System.out.println();
        System.out.print("print rand: ");
        cur = head;
        while(cur != null) {
            System.out.print(cur.rand ==null?"- ":cur.rand.value+" ");
            cur = cur.next;
        }
        System.out.println();
    }

    // 额外空间复杂的为O(1)的方法: 链表中每个节点都拷贝一份，并且直接连接在被拷贝节点的后面，以保证顺序
    public static Node copyListWithRand2(Node head) {
        if(head == null) {
            return head;
        }

        // copy node and link to every node
        Node curNode = head;
        Node copyNode = null;
        while (curNode != null) {
            copyNode = new Node(curNode.value);
            copyNode.next = curNode.next;
            curNode.next = copyNode;
            curNode = copyNode.next;
        }

        // set copy node rand
        curNode = head;
        Node next = null;
        Node curCopy = null;
        while(curNode != null) {
            next = curNode.next.next;
            curCopy = curNode.next;
            curCopy.rand = curNode.rand==null ? null: curNode.rand.next;
            curNode = next;
        }

        // 拆分成两个链表
        curNode = head;
        Node newHead = head.next;
        Node newCur = newHead;
        while (curNode != null) {
            next = curNode.next.next;
            curNode.next = next;
            newCur.next = next==null ?null : next.next;
            curNode = next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");
    }
}
