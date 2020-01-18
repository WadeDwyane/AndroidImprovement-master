package demo.mvp.com.mvpdemo2;

/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: kui.liu
 * Time: 2019/11/20 13:23
 * Description: 选择排序的思想精髓,先从未排序的序列中选取最小值,
 * 放到序列的开始,再从剩下未排序的序列中,选取最小值,放到已经排序的序列的最后.
 * -----------------------------------------------------------
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr  = {-9, -12, 3, 1, 9, -10, 5};

        for (int i = 0, k = 0; i < arr.length; i++, k= i) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[k] > arr[j]) {
                    k = j;
                }
            }

            if (arr[i] > arr[k]) {
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
