package Sort;

/**
 * Created by yetmare on 18-12-10.
 * 快速排序
 * 经典快速排序：选定数组最后一个数　然后将比它小的数放在它的右边　将比它大的数放在它的左边
 * 快速排序：随机选定锚点数　然后将比锚点数小的数放在锚点左边　将比锚点数大的数放在锚点的右边
 */
public class QuickSort {
    // 经典快排：方式一
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


    public static void QuickSortWithPartion(int[] arr) {
        // 以arr的end作为anchor来partion
        PartionSort(arr, 0, arr.length-1);
    }

    public static void PartionSort(int[] arr, int start, int end) {
        if(start > end) {
            return;
        }

        // 随机快排是从arr中随机选出一个数，与arr最后一个数交换　其他与经典快排一样
        swap(arr, end, (int)(Math.random()*(end-start+1)+start));
        int anchor = arr[end];
        int[] small_big = partition(arr, 0, arr.length-1, anchor);
        PartionSort(arr, start, small_big[0]-1);
        PartionSort(arr, small_big[1]+1, end);
    }


    // 荷兰国旗问题　返回中间等于num的数的下标范围
    public static int[] partition(int[] arr, int begin, int end, int num) {
        // 小于等于num的数放在左边部分　左边推着大于num的数往右走
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while(cur != big) {
            if(arr[cur] < num) {
                swap(arr, ++small, cur++);
            } else if(arr[cur] > num) {
                swap(arr, cur, --big);
            } else {
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small+1;
        range[1] = big-1;
        return range;
    }

    // 小于等于给定数的放在数组的左边部分 然后由左边推着大于num的数往右边走　所以只需要一个指针指向左边界
    public static void partion1(int[] arr, int num) {
        if(arr == null || arr.length < 1) {
            return;
        }
        // 记录小于等于num的左边部分
        int left = -1;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] <= num) {
                swap(arr, ++left, i);
            }
        }
    }

    // 荷兰国企问题问题：小于给定数的放在数组的左边部分　等于给定数的放在数组的中间部分　大于给定数的放在数组的右边部分
    // 需要两个指针
    public static void partion2(int[] arr, int num) {
        if(arr == null || arr.length < 1) {
            return;
        }
        int left = -1;
        int right = arr.length;
        int cur = 0;
        while(cur < right) {
            if(arr[cur] < num) {
                swap(arr, ++left, cur++);
            } else if(arr[cur] > num) {
                swap(arr, --right, cur);
            } else {
                cur++;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 50, 76, 13, 27, 49, 50};

        // 经典快速排序：每次选定数组的最后一个数作为参照的比较对象
        // QuickSort1(arr);

        // partion的两种过程：1.　小于等于给定数的放在数组的左边部分　大于给定数的放在数组的右边部分
        // partion1(arr, 50);

        // partion的令一个过程：２. 荷兰国企问题问题：小于给定数的放在数组的左边部分　等于给定数的放在数组的中间部分　大于给定数的放在数组的右边部分
        // partion2(arr, 50);

        // 用partion过程实现经典快排/随机快排
        QuickSortWithPartion(arr);

        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
