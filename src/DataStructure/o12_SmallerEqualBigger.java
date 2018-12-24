package DataStructure;

/**
 * Created by yetmare on 18-12-24.
 * 将单向链表按某值划分成左边小　、中间相等、右边大的形式（荷兰国旗问题）
 * 题目：给定一个单向链表的头节点head,节点的值类型是整型，再给定一个整数pivot。
 * 实现一个调整链表的函数，将链表调整为左部分都是值小于pivot的节点，中间部分都是值等于pivot的节点，右部分的值都是大于pivot的节点
 * 除了这个要求外，对调整后的节点顺序没有更多要求。
 * 例如：链表9->0->4->5->1,pivot=3　调整后链表可以是1->0->4->9->5，也可以是0->1->9->5->4。
 * 总之，满足左部分都是小于３的节点，中间部分都是等于３的节点（本例中这个部分为空），右部分都是大于３的节点。对某部分内部的节点顺序不做要求。
 *
 * 最快的方式是把链表节点放到数组中，然后把数组中所有元素都连接起来（荷兰国旗问题做不到稳定性）
 *
 * 进阶：在原问题的要求上之上在增加如下两个要求。
 * 在左、中、右三个部分的内部也做顺序要求，要求每部分的节点从左到右的顺序与原链表中节点的先后次序一致。
 * 例如：链表9->0->4->5->1, pivot=3。调整后的链表是0->1->9->4->5。在满足原问题要求的同时,左部分节点从左到右为0、1。
 * 在原链表中也是先出现0,后出现1;中间部分在本例中为空,不再讨论;右部分节点 从左到右为9、4、5。在原链表中也是先出现9,然后出现4,最后出现5
 * 如果链表长度为N,时间复杂度请达到O(N),额外空间复杂度请达到O(1)
 */
public class o12_SmallerEqualBigger {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data) {
            value = data;
        }
    }

    // 最快的方式
    public static Node listPartition1(Node head, int pivot) {
        if(head == null) {
            return head;
        }
        int len = 0;
        Node cur = head;
        while(cur != null) {
            len = len+1;
            cur = cur.next;
        }

        Node[] nodeArray = new Node[len];
        cur = head;
        for(int i=0; i<len; i++) {
            nodeArray[i] = cur;
            cur = cur.next;
        }

        arrPartition(nodeArray, pivot);

        // 把partition后的数组中的节点再连接成链表返回
        int i=0;
        for(i=1; i<len; i++) {
            nodeArray[i-1].next = nodeArray[i];
        }
        nodeArray[i-1].next = null;
        return nodeArray[0];
    }

    // 对nodeArray进行partition 分别用left,right卡住小于pivot和大于pivot的部分　中间就自然而然地成了相等的部分
    public static void arrPartition(Node[] nodeArr, int pivot) {
        int left = -1;
        int right = nodeArr.length;
        int index = 0;
        while (index < right) {
            if(nodeArr[index].value < pivot) {
                swip(nodeArr, ++left, index++);
            } else if(nodeArr[index].value > pivot) {
                // 当前数往右right区交换 但是右区换过来的数暂时是未知　所以index暂不更新，需要继续观察
                swip(nodeArr, index, --right);
            } else {
                index++;
            }
        }
    }

    public static void swip(Node[] nodeArray, int index1, int index2) {
        Node tmp = nodeArray[index1];
        nodeArray[index1] = nodeArray[index2];
        nodeArray[index2] = tmp;
    }

    // 进阶：保持稳定性　且额外空间复杂的O(1)
    public static Node listPartition2(Node head, int pivot) {
        if(head == null) {
            return head;
        }

        // 没有什么是增加指针解决不了的
        // sH、sT：分别指向小于pivot部分的头节点和尾节点
        // eH、eT：分别指向等于pivot部分的头节点和尾节点
        // bH、bT：分别指向大于pivot部分的头节点和尾节点
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node bH = null; // big head
        Node bT = null; // big tail
        Node next = null; // next指针：保存剩余链表的头节点　避免链表丢失
        while (head != null) {
            next = head.next;
            head.next = null;
            if(head.value < pivot) {
                if(sH == null) {
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if(head.value == pivot){
                if(eH == null) {
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if(bH == null) {
                    bH = head;
                    bT = head;
                }else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }

        // small and equal part reconnect
        if(sT != null) {
            sT.next = eH;
            eT = eT==null ? sT : eT;
        }

        // all reconnect
        if(eT != null) {
            eT.next = bH;
        }

        return sH!=null ? sH : eH!=null ? eH : bH;
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
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//        head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);
    }
}
