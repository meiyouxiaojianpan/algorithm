package stringmatching;

public class Sunday {

    private int search(String origin, String sub) {
        assert origin != null && origin.length() > 0;
        assert sub != null && sub.length() > 0;
        assert sub.length() <= origin.length();

        //1左端对齐，检查是否已经超过原字符串的长度
        //2逐个匹配, 是否全匹配
        //3取最后一个检测字符的下一个字符c
        //4c是否在子串中出现
        //5出现:字串右端对齐c    没有:字串左端对齐c的下一个字符
        //回到1

        //返回值，字串出现位置，-1代表未找到
        int result = -1;
        //原字符串长度
        int originLength = origin.length();
        //字串长度
        int subLength = sub.length();
        //用于标记字串从何处匹配
        int originFlag = 0;
        //循环条件：当前检查范围没有超过原字符串的长度
        while (originFlag + subLength <= originLength) {
            //标记是否全匹配
            int count = 0;
            //全匹配检查
            for (int i = 0; i < subLength; i++) {
                if (sub.charAt(i) == origin.charAt(originFlag + i)) {
                    count++;
                } else {
                    break;
                }
            }
            //若count等于字串长度则表示全匹配成功
            if (count == subLength) {
                result = originFlag;
                break;
            }

            //检查是否超界
            if (originFlag + subLength > originLength) {
                break;
            }
            //取检查范围后的第一个字符
            char c = origin.charAt(originFlag + subLength);

            //检查c在字串中是否存在
            int existsFlag = 0;
            for (int i = subLength - 1; i >= 0; i--) {
                if (sub.charAt(i) == c) {
                    existsFlag = i;
                    break;
                }
            }
            //字串对齐字符c：可以兼容两种情况
            originFlag = originFlag + subLength - existsFlag;
        }
        return result;
    }

    public static void main(String[] args) {
        String origin = "this is a simple example";
        String sub = "example";
        Sunday sunday = new Sunday();
        int search = sunday.search(origin, sub);
        System.out.println(search);
        System.out.println(origin.charAt(search));
        System.out.println(origin.indexOf(sub));
    }
}
