package DataStructure;

/**
 * Created by yetmare on 18-12-23.
 * 在行列都排好序的矩阵中找数
 * 题目：给定一个有N*M的整形矩阵matrix和一个整数K,　matrix的每一行和每一列都是排好序的。
 * 实现一个函数，判断Ｋ是否在matrix中
 * 例如：[[0, 1, 2, 5], [2, 3, 4, 7], [4, 4, 4, 8], [5, 7, 7, 9]]
 * 如果Ｋ＝７，返回true; 如果K为６，返回false
 * 要求：时间复杂度为O(N+M)，额外空间复杂度O(1)
 * 本题的最优解和本题所给的数据结构有很大关系，找准右上角做锚点很关键
 */
public class o8_FindNumInSortedMatrix {
    public static boolean isContains1(int[][] matrix, int K) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // 以右上角的点为锚点　每次把Ｋ与anchor做比较　如果K>anchor 则往下移动; K<anchor往左移动 相等则直接返回
        // 循环截止条件是遍历到左边界或下边界的时候仍未找到　就停止
        int row = 0;
        int col = 0;
        // 不能写成双层循环的原因：内层循环结束的时候　而外层循环仍有可能找到对应的Ｋ　但是已经被内层循环阻断了
        while(row < matrix.length && col > -1) {
            if(matrix[row][col] == K) {
                return true;
            } else if(matrix[row][col] > K) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
                { 10, 12, 13, 15, 16, 17, 18 },// 1
                { 23, 24, 25, 26, 27, 28, 29 },// 2
                { 44, 45, 46, 47, 48, 49, 50 },// 3
                { 65, 66, 67, 68, 69, 70, 71 },// 4
                { 96, 97, 98, 99, 100, 111, 122 },// 5
                { 166, 176, 186, 187, 190, 195, 200 },// 6
                { 233, 243, 321, 341, 356, 370, 380 } // 7
        };
        int K = 233;
        System.out.println(isContains1(matrix, K));
    }
}
