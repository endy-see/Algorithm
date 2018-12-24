package DataStructure;

/**
 * Created by yetmare on 18-12-24.
 * 两个单链表相交的一系列问题
 * 题目：在本题中，单链表可能有环，也可能无环。给定两个单链表的头节点head1和head2,
 * 如果两个链表相交，请返回相交的第一个节点;如果不相交，返回null即可。
 * 要求：如果链表１的长度为Ｎ，链表２的长度为M，时间复杂度O(N+M),额外空间复杂度O(1)
 */
public class o14_FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;
        public Node(int data) {
            value = data;
        }
    }

    // 这个题有坑　要明确一下链表中是否存在环的问题　否则就容易只写出最简单的形式
    public static Node getIntersectNode(Node head1, Node head2) {
        if(head1 == null || head2 == null) {
            return null;
        }

        // 先得到各个链表中第一个入环的节点　如果为null，则说明该链表无环
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if(loop1 == null && loop2 == null) {
            // 如果两个链表均无环，问题就变成了两条常规链表是否有交点的问题
            return noLoop(head1, head2);
        }

        if(loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        // 如果一个链表有环，另一个链表无环，则这两个链表一定不相交
        return null;
    }

    public static Node noLoop(Node head1, Node head2) {
        if(head1 == null || head2 == null) {
            return null;
        }

        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        // 最后一个节点并未被计入ｎ　
        // 因为希望cur1停留在链表１的最后一个元素位置，cur2停留在链表２的最后一个位置
        // 以比较两个链表的最后一个节点是否相同
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while(cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        // 当前cur1停留在链表１的最后一个节点; cur2停留在链表２的最后一个节点
        if(cur1 != cur2) {
            return null;
        }

        // 再次让cur1指向较长的链表，cur2指向较短的链表
        cur1 = n>0 ? head1 : head2;
        cur2 = cur1==head1 ? head2 : head1;
        n = Math.abs(n);
        while(n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while(cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 获取链表中第一个入环节点
    public static Node getLoopNode(Node head) {
        // 只要出现fast.next==null，必定无环
        if(head == null || head.next == null || head.next.next==null) {
            return null;
        }
        // 先判断当前链表是否有环
        Node slow = head.next;
        Node fast = head.next.next;
        while(slow != fast) {
            if(fast.next == null || fast.next.next == null) {
                return null;
            }

            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;  // fast -> again from head
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 两个链表均有环时，返回两个有环链表的交点
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        // 如果两个链表均有环，则可能相交，也可能不相交
        if(loop1 == loop2) {
            // 如果两个链表的入环节点相同，则必定相交
            // 以入环节点为两个链表的临时终止节点，来求两个链表的交点
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while(cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while(cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }

            // cur1重新指向较长的链表　cur2重新指向较短的链表
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1==head1 ? head2 : head1;
            n = Math.abs(n);
            while(n != 0) {
                cur1 = cur1.next;
                n--;
            }
            while(cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;
        } else {
            // 两个链表的入环节点不相同，则看两个入环节点是否会相遇　
            // 如果相遇，则相遇点就是两条链表的交点;否则不相交（解决的两种情况）
            cur1 = loop1.next;
            while(cur1 != loop1) {
                if(cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
    }
}
