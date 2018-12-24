package SortRelevant;

/**
 * Created by yetmare on 18-12-18.
 * 给定一个数组，求如果排序之后，相邻两数的最大差值，要求时间复杂的O(N),其要求不能用非基于比较的排序
 * 思想：借用了桶的概念　但是没有用桶排序
 */
public class o5_MaxNeighborGap {
    public static int MaxGap(int[] arr) {
        if(arr == null || arr.length < 2) {
            return 0;
        }
        // 获取数组arr中的最大数和最小数
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        for(int i=0; i<arr.length; i++) {
            maxValue = Math.max(arr[i], maxValue);
            minValue = Math.min(arr[i], minValue);
        }

        if(minValue == maxValue) {
            return 0;
        }

        // arr总共有Ｎ个数，分配N+1个桶 所以maxGap一定不会出现在同一个桶内（毕竟存在空桶，至少也是大于一个桶宽的）
        // maxGap一定出现在桶间（但并不一定是空桶的两边）
        // bucketMax记录每个桶中的最大值　bucketMin记录每个桶中的最小值
        boolean[] bucketHasNum = new boolean[arr.length+1];
        int[] bucketMax = new int[arr.length+1];
        int[] bucketMin = new int[arr.length+1];
        int bid = 0;
        int bucketWidth = (maxValue - minValue) / arr.length;
        // 遍历arr的每个数，看其应该进哪个桶(去改变哪个桶的max和min)
        for(int i=0; i<arr.length; i++) {
            // 只给arr中现有的数分桶　现有的数量的大小就是arr中元素的个数
            bid = bucket(arr[i], minValue, maxValue, arr.length);
            bucketMin[bid] = bucketHasNum[bid] ? Math.min(bucketMin[bid], arr[i]) : arr[i];
            bucketMax[bid] = bucketHasNum[bid] ? Math.max(bucketMax[bid], arr[i]) : arr[i];
            bucketHasNum[bid] = true;
        }

        // 由于maxGap一定出现不同桶之间，所以依次遍历bucketHasNum, bucketMax, bucketMin
        int maxGap = 0;
        int preMax = bucketMax[0];
        for(int i=1; i<arr.length+1; i++) {
            if(bucketHasNum[i]) {
                maxGap = Math.max(maxGap, bucketMin[i] - preMax);
                // 无论maxGap是否会变　preMax肯定是每轮都会跟着变的
                preMax = bucketMax[i];
            }
        }
        return maxGap;
    }

    public static int bucket(int cur, int minValue, int maxValue, int arrLen) {
        return (int)(cur - minValue)*arrLen/(maxValue-minValue);
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] randomArray = new int[(int)(Math.random()*(maxSize+1))];
        for(int i=0; i<randomArray.length; i++) {
            randomArray[i] = (int)(Math.random()*(maxValue+1)) - (int)(Math.random()*maxValue);
        }
        return randomArray;
    }
    public static void Main(String[] args) {
        int[] arr = generateRandomArray(100, 100);
        System.out.print("max gap of array is: "+MaxGap(arr));
    }
}
