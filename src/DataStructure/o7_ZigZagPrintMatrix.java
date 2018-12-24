package DataStructure;

/**
 * Created by yetmare on 18-12-23.
 * "之“字型打印矩阵
 * 题目：给定一个矩阵matrix,按照”之“字形的方式打印这个矩阵，例如：
 * 1 2 3 4 5 6 7 8 9 10 11 12　(3×4)　的”之“字形打印结果为：
 * １，２，５，９，６，３，４，７，10，11，８，12
 */
public class o7_ZigZagPrintMatrix {
    public static void printMatrixZigZag(int[][] matrix) {
        if(matrix==null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        // 以对角线上的两个点为锚点　打印对角线上的点
        int tR = 0;  // 对角线右上锚点的横坐标
        int tC = 0;  // 对角线右上锚点的纵坐标（当横坐标未到达矩阵右边界时　纵坐标保持不变）
        int dR = 0;  // 对角线左下锚点的横坐标
        int dC = 0;  // 对角线左下锚点的纵坐标
        int endR = matrix.length-1;
        int endC = matrix[0].length-1;

        boolean fromTop = false;
        // 截止条件包含了矩阵的最后一个元素
        while(tR !=  endR + 1) {
            // 经过刚开始的赋值　现在是符合打印条件的　所以直接打印
            printLevel(matrix, tR, tC, dR, dC, fromTop);
            // 更新对角线的两个锚点
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR+1;
            fromTop = !fromTop;
        }
        System.out.println();
    }

    // 打印指定锚点的对角线的值
    public static void printLevel(int[][] matrix, int tR, int tC, int dR, int dC, boolean fromTop) {
        if(fromTop) {
            // 从右上角向左下角打印(注意：是要把终点左下角点也给打印了　所以循环的停止条件是要打印完dR,dC后才停止)
            while(tR != dR+1) {
                System.out.print(matrix[tR++][tC--]+" ");
            }
        } else {
            // 从左下角向右上角打印
            while(dR != tR-1) {
                System.out.print(matrix[dR--][dC++]+" ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }
}
