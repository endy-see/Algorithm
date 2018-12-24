package DataStructure;

import java.util.Stack;

/**
 * Created by yetmare on 18-12-22.
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作
 * 要求：
 * １. pop、push、getMin操作的时间复杂的O(1)
 * ２. 设计的栈类型可以使用现成的栈结构
 */
public class o2_GetMinStack {
    public class StackWithMin {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;
        public StackWithMin() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int num) {
            this.stackData.push(num);
            if(this.stackMin.isEmpty()) {
                this.stackMin.push(num);
            } else if(this.getMin() >= num){
                // 注意：相等也要push进去　因为出栈的时候，当stackData栈顶元素与stackMin栈顶元素相等时也是要出栈　的
                this.stackMin.push(num);
            }
        }

        public int pop() {
            if(this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty");
            }
            int value = stackData.pop();
            if(value == this.getMin()) {
                this.stackMin.pop();
            }
            return value;
        }

        public int  getMin() {
            if(stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty");
            }
            return this.stackMin.peek();
        }
    }
}
