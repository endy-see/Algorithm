package Sort;

/**
 * Created by yetmare on 18-12-8.
 * 插入排序：假定从前往后是有序的，从前面已排好序的数组中找到第一个小于等于当前元素值的位置，
 * 然后将该位置后的所有元素右移一位（腾出一个空位），最后将当前元素插入到腾出的空位上即可
 */
public class InsertSort {
    public static void InsertSort1(int[] arr) {
        for(int i=1; i<arr.length; i++) {
            int cur_index = i;
            // 不断把当前元素与它之前比它大的元素交换位置，直到遇见第一个小于等于当前元素的元素时停止
            while(cur_index > 0 && arr[cur_index] < arr[cur_index-1]) {
                int tmp = arr[cur_index];
                arr[cur_index] = arr[cur_index-1];
                arr[cur_index-1] = tmp;
                cur_index--;
            }
        }
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void InsertSort2(int[] arr) {
        if(arr == null || arr.length < 1) {
            return;
        }

        for(int i=1; i<arr.length; i++) {
            // 先用变量保存下当前遍历到的元素位置和值
            int cur_index = i;
            int cur_value = arr[i];
            // 下面就需要把ｋ～ｊ－１之间的元素挪到k+1~j，然后把cur_value赋值给arr[k]
            while(cur_index > 0 && arr[cur_index-1] > cur_value) {
                arr[cur_index] = arr[cur_index-1];
                cur_index--;
            }
            // 只有当发生了后移（cur_index变化了）的时候才会留有空位　才需要当前值去填充
            if(cur_index != i) {
                arr[cur_index] = cur_value;
            }
        }
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 76, 13, 27, 49};
//        InsertSort1(arr);
        InsertSort2(arr);
    }
}
