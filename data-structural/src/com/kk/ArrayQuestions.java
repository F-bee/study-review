package com.kk;

import java.util.Hashtable;

/**
 * @author         kk
 * @Date           2023/1/16 14:02
 * @Description    数组面试题
 */
public class ArrayQuestions {

    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 2, 1};
//        findNumsBySum3(arr, 4);

//        int[] arr = {-1, 9, -3, 0, 5, 4, -6};
//        int[] result = adjustArray3(arr);
//        for(int k : result) {
//            System.out.print(k + "  ");
//        }

        findNumExceedHalf(arr);
    }

    /*
      1.输入一个数组和一个值，寻找数组中任意两个数的和等于给定的值的下标。（如果存在多对值,则任意输出一组即可）
      核心：任意一个a[k]，另一个值sum-a[k]；
      可借鉴快排双指针的思想；最快的方法是使用哈希表。
     */
    // 暴力求解法
    public static void findNumsBySum(int[] arr, int sum) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j && arr[i] + arr[j] == sum) {
                    System.out.println("[" + i + ", " + j + "]");
                    return;
                }
            }
        }
        System.out.println("数组中没有和为" + sum + "的两个元素");
    }

    // 双指针法
    // 需要有序数组，先排序，两边求和判断，小了左指针右移，大了右指针左移

    // 哈希表法
    public static void findNumsBySum3(int[] arr, int sum) {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        for (int i = 0; i < arr.length; i++) {
            hashtable.put(arr[i], i);
        }
        Integer index;
        for (int i = 0; i < arr.length; i++) {
            index = hashtable.get(sum-arr[i]);
            if (index != null && index != i) {
                System.out.println("[" + i + ", " + index + "]");
                return;
            }
        }
        System.out.println("数组中没有和为" + sum + "的两个元素");
    }


    /*
      2.给定一个整型数组，里面的值有正有负（0也认为是正数部分），调整位置，将负数放在数组的左边，正数放在数组的右边
     */
    // 两次填充
    public static int[] adjustArray(int[] arr) {
        int[] result = new int[arr.length];
        int j = 0;
        for (int k : arr) {
            if (k < 0) {
                result[j++] = k;
            }
        }
        for (int k : arr) {
            if (k >= 0) {
                result[j++] = k;
            }
        }
        return result;

    }

    // 任何一种排序算法升序排列结果（复杂度最低O(nlog n)

    // 借鉴快排双指针
    public static int[] adjustArray3(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int temp;
        while (left != right) {
            while (arr[left] < 0) {
                left++;
            }
            while (arr[right] > 0) {
                right--;
            }
            if (left < right) {
                temp = arr[left];
                // 左右指针再自增或自减减少一次重复判断
                arr[left++] = arr[right];
                arr[right--] = temp;
            }
        }
        return arr;
    }


    /*
      3.在一个数组中，某一个元素的次数超过了数组长度的一半，输出这个数字
     */
    // 排序，排完序相同的元素都在一块儿
    public static void findNumExceedHalf(int[] arr) {
        // 快排
        Sort.quickSortDesc(0, arr.length - 1, arr);
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                count++;
            } else {
                if (count > arr.length / 2) {
                    System.out.println(arr[i-1]);
                    return;
                } else {
                    count = 1;
                }
            }
        }
        // 如果排序数组最后一位是刚好超过一半总元素的目标值，直接输出
        if (count > arr.length / 2) {
            System.out.println(arr[arr.length - 1]);
        }
    }

    // 哈希表,key存数字，value存出现的次数
    public static void findNumExceedHalf2(int[] arr) {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        int count;
        for (int k : arr) {
            count = hashtable.getOrDefault(k, 1);
            if (count > arr.length / 2) {
                System.out.println(k);
                return;
            } else {
                hashtable.put(k, count + 1);
            }
        }
    }

}
