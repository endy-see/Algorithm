package SortRelevant;

/**
 * Created by yetmare on 18-12-16.
 * 逆序对问题
 * 在一个数组中，左边的数如果比右边的数大，则这两个数构成一个逆序对，请打印所有逆序对　
 * 思想：归并排序过程中　从Right部分找到比Left当前数小的个数　与小和问题属于同一套路
 * 在merge过程中不断判断right部分有多少比left当前数小　那么就说明当前数有多少逆序对了
 */
public class o1_RevereseOrderPair {
    public static int RevereseOrderPair(int[] arr) {
        if(arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length-1);
    }

    public static int mergeSort(int[] arr, int left, int right) {
        if(left == right) {
            return 0;
        }

        int mid = left + ((right-left)>>1);
        int leftReverseNum = mergeSort(arr, left, mid);
        int rightReverseNum = mergeSort(arr, mid+1, right);
        int mergeReverseNum = merge(arr, left, mid, right);
        return leftReverseNum+rightReverseNum+mergeReverseNum;
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int reverseNum = 0;
        int[] help = new int[right-left+1];
        int i=left, j=mid+1, k=0;
        while(i<=mid && j<=right) {
            if(arr[i] > arr[j]) {
                // 则arr的i~mid的所有数都比arr[j]大，对arr[j]来说都是逆序对
                reverseNum += mid-i+1;
                help[k++] = arr[j++];
            } else {
                help[k++] = arr[i++];
            }
        }
        while(i<=mid) {
            help[k++] = arr[i++];
        }
        while(j<=right) {
            help[k++] = arr[j++];
        }
        for(i=0; i<help.length; i++) {
            arr[left+i] = help[i];
        }
        return reverseNum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        int result = RevereseOrderPair(arr);
        System.out.print("reverse pair is: "+result);
    }
}
