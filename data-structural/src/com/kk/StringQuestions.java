package com.kk;

/**
 * @author         kk
 * @Date           2023/1/16 22:50
 * @Description    字符串面试题
 */
public class StringQuestions {

    public static void main(String[] args) {
//        String str = "abcdefg";
//        System.out.println(reverseString2(str, 0, str.length() - 1));
//        System.out.println(reverseString2(str, 2, 5));

        String str = "abcdedcabccba";
        System.out.println(lengthOfMaxPalindromeSubString(str));
    }

    /*
      1.字符串反转：给定一个字符串，反转指定的部分；例如：
        给定字符串"abc"，全部反转为"cba"
        给定字符串"abcde"，反转2-4，就是"abedc"
     */
    // 划分反转区与未反转区
    public static String reverseString(String str, int start, int end) {
        char[] arr = str.toCharArray();
        // 已反转区的首个位置
        int finish = end + 1;
        char temp;
        // 未反转区逐渐缩小，已反转区逐渐扩大
        while (finish > start) {
            temp = arr[start];
            for (int i = start + 1; i < finish; i++) {
                arr[i-1] = arr[i];
            }
            arr[finish-1] = temp;
            finish--;
        }
        return String.valueOf(arr);
    }

    public static String reverseString2(String str, int start, int end) {
        char[] arr = str.toCharArray();
        // 中轴线
        int mid = start + (end - start) / 2;
        int n = 0;
        for (int i = start; i <= mid; i++) {
            swap(arr, start + n, end - n);
            n++;
        }
        return String.valueOf(arr);
    }

    public static void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    /*
      2.寻找最大的回文子串长度，比如"abcdedcabba"，最大回文子串即"cdedc"
     */
    public static int lengthOfMaxPalindromeSubString(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        int max = 0;
        int current = 0;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            // 长度为奇数的情况
            for (int j = 0; (i - j) > 0 && (i + j) < length; j++) {
                if (str.charAt(i - j) != str.charAt(i + j)) {
                    break;
                }
                current = 2 * j + 1;
            }
            // 长度为偶数的情况
            for (int j = 0; (i - j) > 0 && (i + j + 1) < length; j++) {
                if (str.charAt(i - j) != str.charAt(i + j + 1)) {
                    break;
                }
                current = 2 * j + 2;
            }
            if (current > max) {
                max = current;
            }
        }
        return max;
    }

}
