package Sort;

/**
 * Created by yetmare on 18-12-11.
 * 希尔排序：　也称“缩小增量排序”，原理如下：
 * 先将待排序的数组元素分成多个子序列（log n），使得每个子序列的元素个数相对较少，
 * 然后对各个子序列分别进行直接插入排序，待正规待排序序列“基本有序”以后，
 * 最后再对所有元素进行一次直接插入排序
 * 希尔排序的关键不是随便地分组后各自排序，而是将相隔某个“增量”的记录组成一个子序列　实现跳跃式移动　使得排序的效率提高
 */
public class ShellSort {
    // 用直接插入排序来排列子序列的希尔排序
    public static void ShellSort1(int[] arr) {
        // 先生成子序列的跨度
        for(int h=arr.length/2; h>0; h=h/2) {
            // 对以ｈ为跨度的子序列做插入排序
            // i是从跨度位置开始　一直往后移动　直到数组尾部
            for(int i=h; i<arr.length; i++) {
                int anchor = arr[i];
                int j=0;
                // 默认从后往前是有序的
                for(j=i-h; j>=0; j-=h) {
                    // 从后面往前　找到第一个比anchor小的值　如果当前ｊ位置的值比anchor大　则不断往后挪
                    if(arr[j] > anchor) {
                        arr[j+h] = arr[j];
                    } else {
                        // 注意：这里敢直接break的原因是　一旦找到了第一个比anchor小的值的位置　那么该位置之前的必定都是比anchor小的
                        // 因为ｉ每次是从ｈ开始，往后遍历　直接插入排序的　所以在可实现２个以上元素构成的序列之前　前面的序列都是已经排好序的
                        break;
                    }
                }
                // 能执行到这一步　说明arr[j]<=anchor了　那么anchor的值应该放在arr[j+h]的位置
                arr[j+h] = anchor;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {38, 65, 97, 76, 13, 27, 49};
        ShellSort1(arr);
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
