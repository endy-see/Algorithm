package Sort;

/**
 * Created by yetmare on 18-12-8.
 * 冒泡排序：
 * 法一：将较大的数往后不断交换（相当于大数往后冒泡）
 * 法二：将较小的数往前不断交换（相当于小数往前冒泡）
 * 可以做到稳定
 */
public class PopSort {
    // 法一：将较大的数往后不断交换
    public static void PopSort1(int[] arr) {
        // 默认第一层循环是从后向前表示已经排好的数字
        for(int i=arr.length-1; i>0; i--) {
            for(int j=0; j<i; j++) {
                if(arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    // 法二：将较小的数往前不断交换
    public static void PopSort2(int[] arr) {
        // 默认第一层循环表示数组从前往后是从小到大已经排好序的
        for(int i=0; i<arr.length-1; i++) {
            for(int j=arr.length-1; j>i; j--) {
                if(arr[j] < arr[j-1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = tmp;
                }
            }
        }
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 76, 13, 27, 49};
        PopSort1(arr);
        PopSort2(arr);
    }
}
