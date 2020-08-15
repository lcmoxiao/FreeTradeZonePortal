package com.ftzp;

import java.util.ArrayList;
import java.util.List;

public class PermissionParseUtil {
    //128位的辅助权限生成字符串
    static String tt = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

    //通过权限数组生成权限字符串
    public static String generateHexStrByArray(String[] permissions) {
        StringBuilder sb = new StringBuilder(tt);
        for (String perStr : permissions) {
            int permission = Integer.parseInt(perStr);
            sb.replace(127 - permission, 127 - permission + 1, "1");
        }
        String res = BinStrToHexStr(sb.toString());
        System.out.println(res);
        System.out.println(res.length());
        return res;
    }

    // 将16进制字符串转换成2进制字符串
    private static String HexStrToBinStr(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int it = Integer.parseInt(str.substring(i * 4, (i + 1) * 4), 16);
            String s2 = Integer.toBinaryString(it);
            sb.append("0".repeat(16 - s2.length()));
            sb.append(s2);
        }
        return sb.toString();
    }

    public static List<Integer> getPIdList(String str) {
        List<Integer> res = new ArrayList<>();
        String tmp = HexStrToBinStr(str);
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(127 - i) == '1') res.add(i + 1);
        }
        return res;
    }

    // 将128位的二进制字符串，转化为32个16位的字符串
    private static String BinStrToHexStr(String bstr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            String s = bstr.substring(i * 16, (i + 1) * 16);
            int it = Integer.parseInt(s, 2);
            String s16 = Integer.toHexString(it);
            sb.append("0".repeat(4 - s16.length()));
            sb.append(s16);
        }
        return sb.toString();
    }

}
