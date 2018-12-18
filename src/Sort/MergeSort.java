package Sort;

/**
 * Created by yetmare on 18-12-9.
 * 归并排序：思想是分治　通过递归不断二分　再将二者排序合并
 * 可以做到稳定：　merge过程中　遇到相同的数先拷贝左边的即可
 * 额外空间复杂的O(N)
 */
public class MergeSort {
    public static int[] MergeSort1(int[] arr, int start, int end) {
        if(start == end) {
            return new int[]{arr[start]};
        }

        int mid = (start + end) / 2;
        int[] left = MergeSort1(arr, start, mid);
        int[] right = MergeSort1(arr, mid+1, end);
        // 外拍序
        // 其实这种直接返回新new的数组是不太好的　试试下面的直接在arr上调整的方法吧
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

    // 优先：归并排序
    public static void  MergeSort2(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        sortProcess(arr, 0, arr.length-1);
    }

    public static void sortProcess(int[] arr, int left, int right) {
        // 递归实现分治
        if(left == right) {
            return;
        }

        int mid = left + ((right-left)>>2);
        sortProcess(arr, left, mid);       // T(N/2)
        sortProcess(arr, mid+1, right);  // T(N/2)
        merge(arr, left, mid, right);        //O(N)
        // T(N) = 2T(N/2) + O(N) --->> master公式　时间复杂度为O(NlogN) 额外空间复杂的O(N)
    }

    // 将arr的start-mid-1, mid-end两段有序数组进行合并
    public static void merge(int[] arr, int left, int mid, int right) {
        // 新建一个大小为start-end+1的数组help 并在help中执行对arr start-end的外排
        // 排好序后用help去覆盖arr start-end 位置上的数
        int[] help = new int[right-left+1];
        // 定义３个指针　分别指向arr的start~mid-1, mid~end和help
        int i = left, j = mid+1, k = 0;
        while(i <= mid && j <= right) {
            if(arr[i] <= arr[j]) {
                help[k++] = arr[i++];
            } else if(arr[i] > arr[j]) {
                help[k++] = arr[j++];
            }
        }

        // 如果一个部分已经排完　那么就把另一部分剩下的元素直接拷贝到help中
        while(i <= mid) {
            help[k++] = arr[i++];
        }

        while(j <= right) {
            help[k++] = arr[j++];
        }

        for(i=0; i<help.length; i++) {
            arr[left+i] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 76, 13, 27, 49};
//        int [] sorted_arr = MergeSort1(arr, 0, arr.length-1);
//        for(int i=0; i<sorted_arr.length; i++) {
//            System.out.print(sorted_arr[i]+" ");
//        }
        MergeSort2(arr);
        for(int i=0; i<arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println();
    }
}
