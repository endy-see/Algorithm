package Sort;

/**
 * Created by yetmare on 18-12-10.
 * 快速排序
 * 经典快速排序：选定数组最后一个数　然后将比它小的数放在它的右边　将比它大的数放在它的左边
 * 快速排序：随机选定锚点数　然后将比锚点数小的数放在锚点左边　将比锚点数大的数放在锚点的右边
 */
public class QuickSort {
    public static void QuickSort1(int[] arr) {
        // 对数组arr　从０～arr.length-1的范围进行排序
        Sort(arr, 0, arr.length-1);
    }

    public static void Sort(int[] arr, int start, int end) {
        if(start >= end) {
            return;
        }
        // 指定anchor的值为头一个
        int i=start, j=end, anchor=arr[i];
        while(i < j) {
            // 此循环终止的条件是i=j
            // 从后往前循环遍历，直到遇见第一个小于anchor的位置
            while(i<j && arr[j]>=anchor) {
                j--;
            }
            //　经过上面的循环以后　ｉ有可能与ｊ相等了 所以需要再次判断一下终止条件
            if(i<j) {
                // 把后面的值往前换（此时anchor在前）
                arr[i++] = arr[j];
            }

            // 从前往后循环遍历，直到遇见第一个大于anchor的位置
            while(i<j && arr[i]<=anchor) {
                i++;
            }
            if(i<j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = anchor;
        Sort(arr, start, i-1);
        Sort(arr, i+1, end);
    }

    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 76, 13, 27, 49};
        QuickSort1(arr);
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
