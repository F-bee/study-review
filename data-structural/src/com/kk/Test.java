package com.kk;

public class Test {

    public static void main(String[] args) {
        String msg = "<msg category=\"sandbox\" type=\"suspicious_file_report\">\n" +
                "  <item>\n" +
                "    <md5>d54fbb4f977a4ca42280f88013b08195</md5>\n" +
                "    <result>white</result>\n" +
                "    <severity>0</severity>\n" +
                "    <confidence>80</confidence>\n" +
                "    <virusName>\n" +
                "    </virusName>\n" +
                "  </item>\n" +
                "</msg>";
//        int index = msg.indexOf("\"");
        String result = analysisXmlMsg(msg, "type");
        System.out.println(result);
    }

    public static String analysisXmlMsg(String msg, String content) {
        try {
            if (!msg.contains("category") && !msg.contains("type")) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int first = msg.indexOf("\"");
        int second = msg.indexOf("\"", first + 1);
        if ("type".equals(content)) {
            first = msg.indexOf("\"", second + 1);
            second = msg.indexOf("\"", first + 1);
        }
        return msg.substring(first + 1, second);
    }
}
