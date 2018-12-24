package DataStructure;

/**
 * Created by yetmare on 18-12-24.
 * 返回数组中的局部最小值（只要说在数组中找数，就要往二分方向去考虑）
 * 定义局部最小的概念：arr长度为１时，arr[0]是局部最小;
 * arr的长度为N(N>1)时，如果arr[0]<arr[1],那么arr[0]为局部最小; 如果arr[N-1]<arr[N-2],那么arr[N-1]是局部最小;
 * 如果0<i<N-1,　arr[i]<arr[i+1] && arr[i]<arr[i-1],那么arr[i]是局部最小
 * 给定无序数组arr，已知arr中任何两个相邻的数不相等。写一个函数，只需返回arr中任何一个局部最小出现的位置即可
 */
public class o8_FindOneLessValueIndex {
    // 用二分法查找最快
    public static int getLessIndex(int[] arr) {
        if(arr == null || arr.length<1) {
            return -1;
        }

        int len = arr.length;
        // 局部最小位于数组的第一个位置
        if(len == 1 || arr[0]<arr[1]) {
            return 0;
        }
        // 局部最小位于数组的最后一个位置
        if(arr[len-1] < arr[len-2]) {
            return len-1;
        }
        // 既然左右边界都不是局部最小，那么局部最小必定位于数组中间区域
        int left = 1;
        int right = len-2;
        int mid = 0;
        while(left < right) {
            mid = (left+right)/2;
            if(arr[mid] > arr[mid-1]) {
                right = mid-1;
            } else if(arr[mid] > arr[mid+1]) {
                left = mid+1;
            } else {
                return mid;
            }
        }

        return left;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 6, 5, 3, 4, 6, 7, 8 };
        printArray(arr);
        int index = getLessIndex(arr);
        System.out.println("index: " + index + ", value: " + arr[index]);

    }
}
