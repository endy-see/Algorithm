package SortRelevant;

import java.util.PriorityQueue;

/**
 * Created by yetmare on 18-12-16.
 * 一个发射器不断发射数　在不知发射器最终会发射多少数的情况下　给出到任意时刻时所发射的所有数的中位数
 * 思想：利用堆排序
 */
public class o3_getArrMedian {
    public static class MedianHolder {
        // 构建２个堆　一个大根堆用来存放数组的前半部分的数据　一个小根堆用来存放数组的后半部分的数据
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
    }
    public static void getRandomArrMedian() {

    }
    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 76, 13, 27, 49};
        // 堆排序
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
