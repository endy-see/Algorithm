package Sort;

/**
 * Created by yetmare on 18-12-17.
 * 不是基于比较的排序，与被排序的样本的实际数据状况很有关系　所以实际中并不经常使用
 * 桶排序　计数排序:计数排序就是实践了桶排序　基数排序
 * 时间复杂度O(N) 　空间复杂的O(N)
 */
public class OtherSort {
    // BucketSort: 给定一个数组 其中存放的所有数（大量）都是在一定范围内的正整数
    public static void BuckerSort(int[] arr, int n) {
        if(arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for(int i=0; i<arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        // 因为已知了数据范围　所以新建一个最大范围的数组
        int[] bucket = new int[max+1];
        // 遍历arr的每个元素　如果arr[i]=2,则help[2]++　即辅助数组上的词频数+1
        for(int i=0; i<arr.length; i++) {
            bucket[arr[i]] = bucket[arr[i]] + 1;
        }

        // 输出排序结果
        int i=0;
        for(int j=0; j<bucket.length; j++) {
            while(bucket[j]>0) {
                arr[i++] = j;
                bucket[j] = bucket[j] - 1;
            }
        }
    }
}
