package DataStructure;

/**
 * Created by yetmare on 18-12-23.
 * 旋转正方形矩阵
 * 题目：给定一个整形正方形矩阵matrix,请把该矩阵调整成顺时针旋转90度的样子
 * 要求：额外空间复杂的为O(1)
 */
public class o6_RotateMatrix {
    public static void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 ||  matrix[0].length == 0) {
            return;
        }
        // 对方阵进行90度旋转　为了保证额外空间复杂的O(1) 这里需要用变量来保存交换值
        // 这里仍然以左上角点和右下角点为锚点
        int tR = 0;
        int tC = 0;
        int dR = matrix.length-1;
        int dC = matrix[0].length-1;
        // 如果matrix有偶数行　则tR<dR; 如果matrix有奇数上　则最后会只剩一个数，它的90度旋转就还是它自己　
        // 故这里循环结束的条件是tR<dR
        while(tR<dR) {
            rotateEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public static void rotateEdge(int[][] matrix, int tR, int tC, int dR, int dC) {
        // 用左边第一列替换上面第一行上对应的元素值
        int times = dC - tC;
        int tmp = 0;
        for(int i=0; i<=times; i++) {
            tmp = matrix[tR][tC+i];
            matrix[tR][tC+i] = matrix[dR-i][tC];
            matrix[dR-i][tC] = matrix[dR][dC-i];
            matrix[dR][dC-i] = matrix[tR+i][dC];
            matrix[tR+i][dC] = tmp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        printMatrix(matrix);
    }
}
