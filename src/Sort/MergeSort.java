package Sort;

/**
 * Created by yetmare on 18-12-9.
 * 归并排序：思想是分治　通过递归不断二分　再将二者排序合并
 */
public class MergeSort {
    public static int[] MergeSort1(int[] arr, int start, int end) {
        if(start == end) {
            return new int[]{arr[start]};
        }

        int mid = (start + end) / 2;
        int[] left = MergeSort1(arr, start, mid);
        int[] right = MergeSort1(arr, mid+1, end);
        return merge(left, right);
    }

    // 将左右各自有序的数组合并为一个有序的数组　并返回
    public static int[] merge(int[] left, int[] right) {
        // 需要借助额外的空间
        int len1 = left.length;
        int len2 = right.length;
        int len = len1 + len2;
        int[] res = new int[len];
        int i=0, j = 0, k = 0;
        while(i<len1 && j<len2) {
            if(right[j] < left[i]) {
                res[k++] = right[j++];
            } else {
                res[k++] = left[i++];
            }
        }
        // 如果left数组有剩余元素　则将剩余元素都加到res中
        while(i<len1) {
            res[k++] = left[i++];
        }
        while(j<len2) {
            res[k++] = right[j++];
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 76, 13, 27, 49};
        int [] sorted_arr = MergeSort1(arr, 0, arr.length-1);
        for(int i=0; i<sorted_arr.length; i++) {
            System.out.print(sorted_arr[i]+" ");
        }
        System.out.println();
    }
}
