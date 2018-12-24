package SortRelevant;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yetmare on 18-12-16.
 * 一个发射器不断发射数　在不知发射器最终会发射多少数的情况下　给出到任意时刻时所发射的所有数的中位数
 * 思想：利用堆排序
 */
public class o3_MedianQuick {
    public static class MedianHolder {
        // 构建２个堆　一个大根堆用来存放数组的前半部分的数据　一个小根堆用来存放数组的后半部分的数据
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new maxHeapComparator());
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>(new minHeapComparator());

        // 调整大根堆和小根堆的大小　使得这两个堆的大小差不超过２
        // 即如果当前数组arr中的数的个数为偶数个时　|minHeap.size-maxHeap.size|=0
        // 如果当前数组arr中的数的个数为奇数个时　|minHeap.size-maxHeap.size|=1
        public void modifyTwoHeapsSize(){
            int maxHeapSize = this.maxHeap.size();
            int minHeapSize = this.minHeap.size();
            if(maxHeapSize - minHeapSize == 2) {
                // 大根堆中元素的个数多余小根堆中元素的个数
                // 就把大根堆的堆顶加入到小根堆中
                this.minHeap.add(this.maxHeap.poll());
            }

            if(minHeapSize - maxHeapSize == 2) {
                this.maxHeap.add(this.minHeap.poll());
            }
        }

        public void addNumber(int num) {
            // 首先考虑大根堆是否为空
            if(this.maxHeap.isEmpty()) {
                this.maxHeap.add(num);
                return;
            }

            if(this.maxHeap.peek() >= num) {
                // num属于前半部分（大根堆）
                this.maxHeap.add(num);
            } else {
                if(this.minHeap.size() == 0) {
                    this.minHeap.add(num);
                    return;
                }
                if(this.minHeap.peek() <= num) {
                    this.minHeap.add(num);
                } else {
                    // 例如cur = 7, 而大根堆的堆顶ex:5 与小根堆的堆顶ex:10
                    // 本来大根堆(为了避免heapify而)把cur踢给了小根堆　但是小根堆发现不满足自己的入堆条件（达不到heapify的条件）
                    // 　于是又踢回给了大根堆
                    this.maxHeap.add(num);
                }
            }
            // 每插入一个数，就动态调整一次大根堆和小根堆的大小　使得这两个堆的大小差不超过２
            modifyTwoHeapsSize();
        }

        public Integer getMedian() {
            int maxHeapSize = this.maxHeap.size();
            int minHeapSize = this.minHeap.size();
            if(maxHeapSize + minHeapSize == 0) {
                return null;
            }

            // 至少有一个不为堆不为空时
            Integer maxHeapHead =  this.maxHeap.peek();
            Integer minHeapHead = this.minHeap.peek();
            if(((maxHeapSize+minHeapSize) & 1) == 0) {
                float tmp = (maxHeapHead + minHeapHead) / 2.0f;
                System.out.print("tmp="+tmp);
                return (maxHeapHead+minHeapHead) / 2;
            }  else {
                return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;
            }
        }
    }

    public static class maxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }

    public static class minHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    }

    public static int[] generateRandomArray(int maxCount, int maxValue) {
        // 随机产生[1,maxCount]之间个[0, maxValue)之间的数
        // 注: Math.random()返回的是double类型
        int[] randomArray = new int[(int)(Math.random()*maxCount) + 1];
        for(int i=0; i<randomArray.length; i++) {
            randomArray[i] = (int)Math.random()*maxValue;
        }
        return  randomArray;
    }
    public static void main(String[] args) {
        int maxCount = 30;
        int maxValue = 1000;
        int[] arr = generateRandomArray(maxCount, maxValue);
//        int[] arr = new int[] {1, 2};
        MedianHolder holder = new MedianHolder();
        for(int i=0; i<arr.length; i++) {
            holder.addNumber(arr[i]);
        }
        int median = holder.getMedian();
        System.out.print("median of arr is: "+median);
    }

}
