package HashRelevant;

/**
 * Created by yetmare on 18-12-28.
 * 岛问题
 * 一个矩阵中只有０和１两种值，每个位置都可以和自己的上，下，左，右四个位置相连，
 * 如果有一片１连在一起，这个部分叫做一个岛，求一个矩阵中有多少岛？
 * 举例：
 * ０　０　１　０　１　０
 * １　１　１　０　１　０
 * 1　 0   0  1   0  0
 * 0   0   0  0   0  0
 * 这个矩阵中有三个岛
 * 策略：感染函数　被感染的值重置为２
 */
public class o3_Islands {
    public static int countIslands(int[][] arr) {
        if(arr == null || arr[0] == null) {
            return 0;
        }

        int islandNum = 0;
        int m = arr.length;
        int n = arr[0].length;
        // 从矩阵左上角点开始向右，向下遍历 遇到被感染过的值就跳过
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(arr[i][j] == 1) {
                    infect(arr, i, j, m, n);
                    islandNum++;
                }
            }
        }

        return islandNum;
    }

    public static void infect(int[][] arr, int row, int col, int M, int N) {
        // 如果当前判断的位置越界或者当前值不为１　就返回
        if(row < 0 || row >= arr.length
        || col < 0 || col >= arr[0].length
        || arr[row][col] != 1) {
            return;
        }
        // 否则就先感染当前值
        arr[row][col] = 2;
        // 然后试图感染周围值
        infect(arr, row + 1, col, M, N);
        infect(arr, row -1, col, M, N);
        infect(arr, row, col - 1, M, N);
        infect(arr, row, col + 1, M, N);
    }

    public static void main(String[] args) {
        int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m1));

        int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m2));

    }
}
