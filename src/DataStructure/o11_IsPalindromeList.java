package DataStructure;

import java.util.Stack;

/**
 * Created by yetmare on 18-12-24.
 * 判断一个链表是否为回文结构
 * 题目：给定一个链表的头节点head，请判断该链表是否为回文结构。
 * 例如：　1 -> 2 -> 1,返回true
 * 1->2->2->1,返回true
 * 15->6->15，返回true
 * 1->2->3, 返回false
 *
 * 进阶：如果链表长度为N,时间复杂度达到O(N),额外空间复杂度达到O(1)
 */
public class o11_IsPalindromeList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    // 方法一：网上考试时的做法　用最快的时间写出最不容易出错的答案
    // 用大小为Ｎ的栈一次保存链表节点，额外空间复杂度O(N)
    public static boolean isPalindrome1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 方法二：与方法一一样，额外空间复杂的O(N)，但是只用N/2大小的栈　
    // 因为如果链表回文　则只需比较链表的后半部分与前半部分对应位置的值是否相等
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 先找到链表中点的位置　然后将链表的后半部分入栈
        Node slow = head;
        Node fast = head;
        if (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //此时如果链表长度为奇数，则slow正好位置中间位置;如果链表长度为偶数，则slow正好位于上半部分的最后一个位置
        Stack<Node> stack = new Stack<>();
        slow = slow.next;
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }

        slow = head;
        while (!stack.isEmpty()) {
            if (slow.value != stack.pop().value) {
                return false;
            }
        }
        return true;
    }

    // 方法３：面试时候的解法　空间复杂度为O(1)
    // 先将链表的后半部分反转　-> 比较　-> 再把链表的后半部分转正
    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 先用快慢指针的方式找到中点
        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 对以slow.next作为后半部分head的链表进行反转
        Node head2 = reverseList(slow.next);
        slow.next = null;
        // 保存后半部分反转后的链表的头部　以备后面再反转回来
        Node lastStart = head2;

        // 比较前半部分与后半部分对应的值是否相等
        Node head1 = head;

        boolean isPalindrom = true;
        while(head1 != null && head2 != null) {
            if(head1.value != head2.value) {
                isPalindrom = false;
                break;
            }
            head1 = head1.next;
            head2 = head2.next;
        }

        slow.next = reverseList(lastStart);
        return isPalindrom;
    }

    public static Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node preNode = null;
        Node curNode = head;
        Node nextNode = null;
        while(curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
    }
}
