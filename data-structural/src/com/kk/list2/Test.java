package com.kk.list2;

/**
 * @author         kk
 * @Date           2023/1/5 20:50
 * @Description    list_test class
 */
public class Test {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>();
//        System.out.println(array.isEmpty());
//        array.addLast(1);
//        array.addLast(2);
//        array.addLast(3);
//        array.add(1, 5);
//        array.add(1, 12);
//        System.out.println(array.getCapacity());
//        System.out.println(array.isEmpty());
//        array.add(1, 5);
//        System.out.println(array);
//        array.remove(2);
//        array.removeAlla(3);
//        array.update(1, 3);
//        System.out.println(array);
//        System.out.println(array.get(1));
//        System.out.println(array.contains(2));
//        System.out.println(array.contains(7));
//        System.out.println(array.contains(null));

//        StackDemo<Integer> stack = new StackDemo<>();
//        stack.push(1);
//        stack.push(5);
//        stack.push(3);
//        stack.push(2);
//        stack.push(1);
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());

//        String str = "((({{{[[[]]]}}})))";
//        System.out.println(valid(str));
//        String str1 = "ab#c";
//        String str2 = "ab#c";
//        String str3 = "abc###";
//        String str4 = "";
//        System.out.println(equalsStr(str1, str2));
//        System.out.println(equalsStr(str3, str4));

        QueueDemo<Integer> queue = new QueueDemo<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.poll());
        System.out.println(queue.peekTail());
        System.out.println(queue.peekHead());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    /*
      给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
      有效字符串需满足：
        1. 左括号必须用相同类型的右括号闭合。
        2. 左括号必须以正确的顺序闭合。
        3. 注意空字符串可被认为是有效字符串。
    */
    public static boolean valid(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        StackDemo<Character> stack = new StackDemo<>();
        char c;
        char top;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                top = stack.pop();
                if (c == ')' && top != '(') {
                    return false;
                }
                if (c == '}' && top != '{') {
                    return false;
                }
                if (c == ']'&& top != '[') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /*
      给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
      示例 1：
        输入：S = "ab#c", T = "ad#c"
        输出：true
        解释：S 和 T 都会变成 "ac"。
      示例 2：
        输入：S = "ab##", T = "c#d#"
        输出：true
        解释：S 和 T 都会变成 ""。
    */
    public static boolean equalsStr(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        return saveToStack(s).equals(saveToStack(t));
    }

    public static String saveToStack(String str) {
        StackDemo<Character> stack = new StackDemo<>();
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == '#') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
