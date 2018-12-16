package SortRelevant;

/**
 * Created by yetmare on 18-12-16.
 * 返回数组arr中前k小的数
 * 思想：利用堆排序
 */
public class o3_FindKSmallNum {
    public static int[] FindKSmallNum(int[] arr, int k) {
        if(arr == null || k<=0 || arr.length<k) {
            return null;
        }

        // 构建一个heapSize=k的大根堆（因为希望arr中ｋ位置之后较小的数往堆顶放　从而实现调整堆的目的）
        int[] kHeap = new int[k];
        for(int i=0; i<k; i++) {
            // 将arr第i个位置上的数　放到kHeap的第i个位置　并将kHeap调整成大根堆
            heapInsert(kHeap, arr[i], i);
        }
        // 将arr的k+1~arr.length-1位置的元素依次查看是否能插入堆顶
        for(int i=k; i<arr.length; i++) {
            if(arr[i] < kHeap[0]) {
                kHeap[0] = arr[i];
                heapify(kHeap, 0, k);
            }
        }
        return kHeap;
    }

    // 给heap的heapSize位置赋值value
    public static void heapInsert(int[] heap, int value, int index) {
        heap[index] = value;
        // 找其父节点的值　如果比其父节点的值大　则交换
        while(index != 0) {
            int parent = (index-1)/2;
            if(heap[parent] < heap[index]) {
                swap(heap, index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    // 将数组heap从index起调整成大顶堆
    public static void heapify(int[] heap, int index, int heapSize) {
        int left = 2*index + 1;
        int right = 2*index + 2;
        int largest = index;
        while(left < heapSize) {
            if(heap[left] > heap[index]) {
                largest = left;
            }
            if(right < heapSize && heap[right] > heap[largest]) {
                largest = right;
            }
            if(largest != index) {
                swap(heap, index, largest);
            } else {
                break;
            }
            index = largest;
            left = 2*index + 1;
            right = 2*index + 2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 76, 13, 27, 49};
        int[] res = FindKSmallNum(arr, 4);
        for(int i=0; i<res.length; i++) {
            System.out.print(res[i]+" ");
        }
    }
}
