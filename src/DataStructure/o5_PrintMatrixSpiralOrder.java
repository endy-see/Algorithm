package DataStructure;

/**
 * Created by yetmare on 18-12-23.
 * 转圈打印矩阵
 * 题目：给定一个整形矩阵matrix,请按照转圈的方式打印它
 * 例如：一个４×４的矩阵：１　２　３　４　... 16的打印结果是：
 * 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11, 10
 *　要求：额外空间复杂的O(1)
 */
public class o5_PrintMatrixSpiralOrder {
    // 把左上角点和右下角点定成锚点　向着锚点转圈打印
    public static void printMatrixSpiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        // 左上角锚点
        int tR = 0;
        int tC = 0;
        // 右下角锚点
        int dR = matrix.length-1;
        int dC = matrix[0].length-1;
        // 想着锚点目标打印
        while(tR <= dR && tC <= dC) {
            // 左上角锚点向右下角移动　右下角锚点向左上角移动
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public static void printEdge(int[][] matrix, int tR, int tC, int dR, int dC) {
        // 只有一行时，打印这一行
        if(tR == dR) {
            for(int i=tC; i<=dC; i++) {
                System.out.print(matrix[tR][i]+" ");
            }
        } else if(tC == dC) {
            // 只有一列时，打印这一列
            for(int i=tR; i<=dR; i++) {
                System.out.print(matrix[i][tC]+" ");
            }
        } else {
            // 多行多列时　顺时针向着锚点打印
            int curR = tR;
            int curC = tC;
            // 打印第tR行
            while(curC != dC) {
                System.out.print(matrix[tR][curC]+" ");
                curC++;
            }
            // 打印第dC列
            while(curR != dR) {
                System.out.print(matrix[curR][dC]+" ");
                curR++;
            }
            // 打印第dR行
            while(curC != tC) {
                System.out.print(matrix[dR][curC]+" ");
                curC--;
            }
            // 打印第tC列　
            while(curR != tR) {
                System.out.print(matrix[curR][tC]+" ");
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrixSpiralOrder(matrix);
    }
}
