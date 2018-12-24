package DataStructure;

import java.security.InvalidParameterException;

/**
 * Created by yetmare on 18-12-18.
 * 用数组结构实现大小固定的栈和队列
 */
public class o0_Array_To_Stack_Queue {
    public static class ArrayStack {
        private int[] stack;
        // index：记录每次待入栈/出栈的下一个位置
        private int index = 0;

        public ArrayStack(int initialSize) {
            if(initialSize < 0) {
                throw new InvalidParameterException("the initial size cannot less than 0!");
            }
            stack = new int[initialSize];
            index = 0;
        }

        // 入栈操作(index指向待插入位置)
        public void push(int data) {
            // 如果当前栈已满　则报错
            if(index == stack.length) {
                throw new ArrayIndexOutOfBoundsException("Stack is full!");
            }
            stack[index] = data;
            index++;
        }

        // 出栈操作
        public int pop() {
            if(index == 0) {
                throw new ArrayIndexOutOfBoundsException("Stack is empty!");
            }
            return stack[--index];
        }

        // 查询操作
        public int peek() {
            if(index == 0) {
                throw new ArrayIndexOutOfBoundsException("Stack is empty!");
            }
            return stack[index-1];
        }
    }

    public static class ArrayQueue {
        private int[] queue;
        // size：用于随时记录经过多次入队、出队之后　留在数组中的队列的实际大小
        // start和end相互独立　二者是分别与size产生关系的
        private int size;
        // start: 用于记录每次插入队列的位置
        private int start;
        // end: 用于记录每次出队的位置
        private int end;
        public ArrayQueue(int initialSize) {
            if(initialSize < 0) {
                throw new InvalidParameterException("the queue size cannot less than 0");
            }
            queue = new int[initialSize];
            size = 0;
            start = 0;
            end = 0;
        }

        // 入队列操作: 队尾入队列
        public void push(int data) {
            if(size == queue.length) {
                throw new ArrayIndexOutOfBoundsException("the queue is full!");
            }

            queue[end] = data;
            size++;
            if(end == queue.length-1){
                end = 0;
            } else {
                end = end + 1;
            }
        }

        // 出队列操作: 从队头start出队列
        public int poll() {
            if(size == 0) {
                throw new ArrayIndexOutOfBoundsException("the queue is empty!");
            }
            // 由于end指向的是下一个待出栈的位置　但是弹出操作是需要返回弹出值的
            int tmp = queue[start];
            if(start == queue.length-1) {
                start = 0;
            } else {
                start = start + 1;
            }
            size = size-1;
            return tmp;
        }

        public Integer peek() {
            if(size == 0) {
                return null;
            }
            return queue[start];
        }
    }
}
