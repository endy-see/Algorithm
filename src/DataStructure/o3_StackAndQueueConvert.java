package DataStructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by yetmare on 18-12-22.
 * 如何用队列实现栈结构？
 * 如何用栈实现队列结构？
 */
public class o3_StackAndQueueConvert {
    // 用两个队列实现栈的功能
    // 由于pop过程涉及到两个队列相互倒数据，仅留下队列最后一个元素作为弹出结果的过程
    // 所以需要实时知道当前的数据都在哪个队列中　本题采用了不断交换queuePush和queuePop内存地址的方法
    // 让queuePush始终保持为有数据的一方　
    public class TwoQueuesStack {
        // queue1负责进数据　queue2负责出数据
        private Queue<Integer> queue;
        private Queue<Integer> help;
        public TwoQueuesStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(int num) {
            this.queue.add(num);
        }

        public int pop() {
            if(this.queue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while(queue.size() != 1) {
                help.add(queue.poll());
            }
            int value = queue.poll();
            swap();
            return value;
        }

        public int peek() {
            if(this.queue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while(queue.size() != 1) {
                help.add(queue.poll());
            }
            int value = queue.poll();
            help.add(value);
            swap();
            return value;
        }

        public void swap() {
            Queue<Integer> tmp = help;
            help = queue;
            queue = tmp;
        }

    }

    // 用两个栈实现队列的两个原则：
    // １. 如果stackPop不为null 则不能往stackPop中倒数据
    // ２. 如果stackPush要往stackPop中倒数据，则必须一次性全部倒完　
    public class TwoStacksQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;
        public TwoStacksQueue() {
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        public void push(int num) {
            stackPush.push(num);
        }

        public int poll() {
            dao();
            return stackPop.pop();
        }

        public int peek() {
            dao();
            return stackPop.peek();
        }

        // 可以把重复部分提取成
        // 倒数据操作　这个操作贯穿了push、pop和peek这三个过程
        public void dao() {
            if(stackPush.empty() && stackPop.empty()) {
                throw new RuntimeException("Queue is empty");
            } else if(stackPop.empty()) {
                while(!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }
    }
}
