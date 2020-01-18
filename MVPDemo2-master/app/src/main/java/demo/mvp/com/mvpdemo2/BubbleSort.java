package demo.mvp.com.mvpdemo2;

/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: kui.liu
 * Time: 2019/11/20 13:28
 * Description: 冒泡排序的思想:先选取未排序序列中的最后一个元素,依次与之前的元素比较,
 * 元素比它大,两者交换,这样就把最大的元素排到序列最末尾
 * -----------------------------------------------------------
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr  = {-9, -12, 3, 1, 9, -10, 5};

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

        for (int i : arr) {
            System.out.println(i);
        }
    }
}
