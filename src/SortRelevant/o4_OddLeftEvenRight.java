package SortRelevant;

/**
 * Created by yetmare on 18-12-16.
 * 奇数放在数组左边　偶数放在数组右边（如果面试官要求原始的相对次序不变，则面试官非良人，因为快排要做到稳定很难）
 * 思想：partition
 */
public class o4_OddLeftEvenRight {
    public static void OddLeftEvenRight(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        int left = -1;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] % 2 == 1) {
                swap(arr, ++left, i);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 76, 13, 27, 49};
        OddLeftEvenRight(arr);
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
