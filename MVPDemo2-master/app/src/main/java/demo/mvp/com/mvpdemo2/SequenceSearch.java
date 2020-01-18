package demo.mvp.com.mvpdemo2;

/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: kui.liu
 * Time: 2019/11/20 13:38
 * Description: 顺序查找的思路:就是遍历
 * -----------------------------------------------------------
 */
public class SequenceSearch {

    public static void main(String[] args) {
        int[] arr = {-9, -12, 3, 1, 9, -10, 5};
        int target = 1;

        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                index = i;
            }
        }

        System.out.println(index == -1 ? "元素在数组中不存在" : "元素位于数组中的第" + (index + 1) + "个");
    }
}
