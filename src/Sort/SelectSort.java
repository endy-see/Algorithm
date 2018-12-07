package Sort;

/**
 * Created by yetmare on 18-12-7.
 *
 * 选择排序
 * 法一、每遍历一次数组，获取该轮遍历的最大值，并将其与最后位置的元素互换可以实现从小到大的排序
 * 同理，法二：每遍历一次数组，获取该轮遍历的最小值，并将其前面位置的元素互换也可以实现从小到大的排序结果
 *
 */
public class SelectSort {
    // 每次遍历选择一个最大数与后面待排序的元素交换，从而使得从后向前、从大到小变得有序
    public static void Sort1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        for(int i=arr.length-1; i>0; i--) {
            int max_index = i;
            int max_value = arr[i];
            for(int j=0; j<i; j++) {
                // 从i到ｊ遍历，如果遇到比arr[i]大的元素，则二者交换
                if(arr[j] > max_value) {
                    max_index = j;
                    max_value = arr[j];
                }
            }
            // 由于最大值在遍历过程中已经被max_value保存了　所以这里的交换就不再需要定义中间变量了
            if(max_index != i) {
                arr[max_index] = arr[i];
                arr[i] = max_value;
            }
        }

        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    // 每次遍历选择一个最小数与前面待排序的元素交换，从而使得从前向后、从小到大变得有序
    public static void Sort２(int[] arr) {
        if(arr==null || arr.length < 1) {
            return;
        }
        for(int i=0; i<arr.length-1; i++) {
            int min_index = i;
            int min_value = arr[i];
            for(int j=i+1; j<arr.length; j++) {
                if(arr[j] < min_value) {
                    min_index = j;
                    min_value = arr[j];
                }
            }
            // 由于最小值在遍历过程中已经被min_value保存了　所以这里的交换就不再需要定义中间变量了
            if(i != min_index) {
                arr[min_index] = arr[i];
                arr[i] = min_value;
            }
        }
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 76, 13, 27, 49};
        Sort1(arr);
        Sort２(arr);
    }

}
