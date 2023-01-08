package com.kk;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        int[] arr = {123, 212, 11, 23, 1, 24, 23, 11, 22, 161};
//        int[] arr = {123, 212, 11};

//        bubbleSort(arr);
//        selectSort(arr);
//        quickSort(0, arr.length - 1, arr);
//        quickSortDesc(0, arr.length - 1, arr);
//        int[] arr2 = separate(arr);
//        insertSort(arr);
        shellSort(arr);

        for (int j : arr) {
            System.out.print(j + " ");
        }
    }

    public static void bubbleSort(int[] arr) {
        int temp;
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            if (!flag) {
                return;
            }
            flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j+1] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
        }
    }

    /**
     * 选择排序
     * 引入分区概念：无序区和有序区
     * 第一轮排序有序区为空
     */
    public static void selectSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            // 默认 arr[smallest] 为无序区最小元素；i 开始是无序区，i 以前都是有序区
            int smallest = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[smallest]) {
                    smallest = j;
                }
            }
            if (i != smallest) {
                temp = arr[smallest];
                arr[smallest] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /**
     * 快排（升序）
     */
    public static void quickSort(int left, int right, int[] arr) {
        int i = left;
        int j = right;
        int temp;
        if (i >= j) {
            return;
        }
        int k = arr[i];
        // 小的往前放，从前往后找大的，从后往前找小的；
        while (i != j) {
            while (arr[i] <= k && i < j) {
                i++;
            }
            while (arr[j] > k && i < j) {
                j--;
            }
            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (arr[i] < k) {
            temp = arr[left];
            arr[left] = arr[i];
            arr[i] = temp;
        }
        quickSort(left, i - 1, arr);
        quickSort(i, right, arr);
    }

    /**
     * 快排（降序）
     */
    public static void quickSortDesc(int left, int right, int[] arr) {
        int i = left;
        int j = right;
        int temp;
        if (i >= j) {
            return;
        }
        int k = arr[i];
        // 大的往前放，从前往后找小的，从后往前找大的；
        while (i != j) {
            while (arr[i] >= k && i < j) {
                i++;
            }
            while (arr[j] < k && i < j) {
                j--;
            }
            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (k < arr[i]) {
            temp = arr[left];
            arr[left] = arr[i];
            arr[i] = temp;
        }
        quickSortDesc(left, i - 1, arr);
        quickSortDesc(i, right, arr);
    }

    /**
     * 归并排序
     * 分割
     */
    public static int[] separate(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(separate(left), separate(right));
    }

    /**
     * 归并排序
     * 合并
     */
    public static int[] merge(int[] left, int[] right) {
        int[] arr = new int[left.length + right.length];
        if (arr.length < 2) {
            return left.length == 0 ? right : left;
        }
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
        return arr;
    }

    /**
     * 插入排序
     */
    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int index = i - 1;
            while (index >= 0 && arr[index] > temp) {
                arr[index+1] = arr[index];
                index--;
            }
            arr[index+1] = temp;
        }
    }

    /**
     * 希尔排序
     * 加强版的插入排序，增量递减插入排序
     */
    public static void shellSort(int[] arr) {
        int gap = arr.length / 2;
        while (gap >= 1) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int index = i - gap;
                while (index >= 0 && arr[index] > temp) {
                    arr[index+gap] = arr[index];
                    index -= gap;
                }
                arr[index+gap] = temp;
            }
            gap /= 2;
        }
    }

}
