package demo.mvp.com.mvpdemo2;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {-12, -10, -9, 1, 3, 5, 9};
        int target = -9;

        int index = getIndex(arr, target);
        System.out.println("位于" + index);
    }

    private static int getIndex(int[] arr, int target) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int povit = (min + max) / 2;
            if (arr[povit] == target) {
                return povit;
            } else if (arr[povit] < target) {
                min = povit + 1;
            } else {
                max = povit - 1;
            }
        }
        return -1;
    }
}
