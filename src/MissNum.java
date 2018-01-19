/**
 * Created by zhym on 19/01/2018.
 * 数组中未出现的最小正整数
 * 题目：给定一个无序整型数组arr，找到数组中未出现的最小正整数
 * 举例：arr = [-1, 2,3,4] 返回1
 *      arr = [1, 2, 3, 4] 返回5
 */
public class MissNum {
    public static int missNum(int[] arr) {
        int l = 0;
        int r = arr.length;
        while(l<r) {
            if(arr[l] == l+1) {
                l++;
            } else if(arr[l] <=1) {
                // 小于1，表示非正整数（过小），应该减小合法数的个数，同时可能合法数的范围应减小
                arr[l] = arr[--r];
            } else if(arr[l] > r) {
                // 大于r，表示过大，因为arr中最小正整数的最大值就是数组的大小了，如果arr[l]大于了最小正整数的最大范围，即说明此数不合法
                arr[l] = arr[--r];
            } else if(arr[arr[l] - 1] == arr[l]) {
                // 当前arr[l]的值合法（不大不小），而且在其正确的位置上，说明此时有重复的数了，此时只需缩小最大值的范围，因为重复的数只能算一个合法数
                arr[l] = arr[--r];
            } else {
                // 当前arr[l]的值合法，但不在其正确的位置上，则应交换到正确的位置上，然后继续考察l位置上从后面换过来的数
                swap(arr, l, arr[l] - 1);
            }
        }
        return l+1;
    }

    private static void swap(int[] arr, int index1,int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
