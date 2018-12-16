package SortRelevant;

/**
 * Created by yetmare on 18-12-16.
 * 小和问题：
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。
 * 求一个数组的小和。
 * 例子：　[1, 3, 4, 2, 5]
 * 1左边比１小的数，没有；
 * ３左边比３小的数，１;
 * ４左边比４小的数，１、３;
 * ２左边比２小的数，１;
 * 5左边比５小的数，１、３、４、２；
 * 所以小和为１＋１＋３＋１＋１＋３＋４＋２＝16
 *
 * 思想：归并排序　O(NlogN)
 * 将找左边有多少数比ｋ小　转换成右边有多少数比ｋ大的问题　
 * 在merge过程中不断判断right部分有多少比left当前数大　那么就说明当前数有多少被有部分榨了　
 */
public class o0_SmallSum {
    public static int SmallSum(int[] arr) {
        if(arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length-1);
    }

    public static int mergeSort(int[] arr, int left, int right) {
        if(left == right) {
            return 0;
        }

        int mid = left + ((right - left) >> 1);
        int leftSum = mergeSort(arr, left, mid);
        int rightSum = mergeSort(arr, mid+1, right);
        int smallSum = merge(arr, left, mid, right);
        return leftSum + rightSum + smallSum;
//        return mergeSort(arr, left, mid) + mergeSort(arr, mid+1, right) + merge(arr, left, mid, right);
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int sum = 0;
        // 求merge过程中产生的小和
        int[] help = new int[right-left+1];
        int i=left, j=mid+1, k=0;
        while(i<=mid && j<=right) {
            if(arr[i] < arr[j]) {
                // 开始计算小和　在Right部分的j~right的所有数其实都会在arr[i]上榨取小和
                sum += arr[i] * (right-j+1);
                help[k++] = arr[i++];
            } else {
                help[k++] = arr[j++];
            }
        }

        // 由于每个小部分在merge的时候已经算过小和了　所以这里就不需再对多余的部分求小和了
        while(i<=mid) {
            help[k++] = arr[i++];
        }
        while(j<=right) {
            help[k++] = arr[j++];
        }
        for(i=0; i<help.length; i++) {
            arr[left+i] = help[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        int result = SmallSum(arr);
        System.out.print("small sum is: "+result);
    }
}
