package Sort;

/**
 * Created by yetmare on 18-12-11.
 * 堆排序：　主要包括两个步骤：１. 构建堆　２. 交换堆顶元素与数组的最后一个元素
 * 所以　这里是大根堆　数组排序是从小到大排序
 */
public class HeapSort {
    public static void HeapSort1(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        // 先构建一个大顶堆（不需额外空间）
        for(int i=1; i<arr.length; i++) {
            HeapInsert(arr, i);
        }

        // 将堆顶元素与数组最后一个元素交换（堆顶一直都是bigHeap[0])
        swap(arr, arr.length-1, 0);
        for(int i=arr.length-2; i>0; i--) {
            // 原来的大根堆的堆顶被替换掉了以后　现在的对就不是大根堆了　需要重新将０－i调整成大根堆
            Heapify(arr, 0, i);
            swap(arr,i, 0);
        }
    }
    // 构建大根堆:　从下往上
    public static void HeapInsert(int[] arr, int left) {
        // 找到当前index位置的父节点的位置
        while(left > 0) {
            int parent = (left-1) / 2;
            if(arr[left]>arr[parent]) {
                // 如果当前要插入的值比其父节点的值大　则应与其父节点交换
                swap(arr, parent, left);
                left = parent;  // 注意：这里是一个循环赋值　即不断往上找父节点　父节点的父节点　直到找到第一个比value大的父时停止与父的交换
            } else {
                break;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 调整堆: 从上往下　把arr的start-end重新调整成大根堆
    public static void Heapify(int[] arr, int start, int end) {
        int left = 2*start + 1;
        int right = 2*start + 2;
        int largest = start;
        while(left < end) {
            if(arr[left] > arr[largest]) {
                largest = left;
            }
            if(right < end && arr[right] > arr[largest]) {
                largest = right;
            }
            if(largest != start) {
                swap(arr, start, largest);
            } else {
                break; // break的前提是largest已经比左右孩子都大了　再也没有更大的数能往上滚了
            }

            start = largest;
            left = 2*largest + 1;
            right = 2*largest + 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 76, 13, 27, 49};
        HeapSort1(arr);
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
