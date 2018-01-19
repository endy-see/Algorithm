public class Main {
//  System.out.print();

    public static void main(String[] args) {
        testMissNum();
    }

    public static void testMissNum() {
        int[] arr = new int[] {-1, 2, 3, 4};
        int[] arr1 = new int[] {-1, -3, 6, 9, 1, 8, 2, 3, 4, 7};
        int[] arr2 = new int[] {3, 2, 6, 2, 5, 1, 7, 8, 9};
        int result = MissNum.missNum(arr2);
        System.out.println("result = "+result);
    }

}
