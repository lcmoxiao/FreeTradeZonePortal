package com.ftzp;

import java.util.ArrayList;
import java.util.List;

public class PermissionParseUtil {
    //128位的辅助权限生成字符串
    static String tt = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

    //通过权限数组生成权限字符串
    public static String generateBinStrByArray(int[] permissions) {
        StringBuilder sb = new StringBuilder(tt);
        for (int permission : permissions) {
            sb.replace(127 - permission, 127 - permission + 1, "1");
        }
        return sb.toString();
    }

    /**
     * @param rPermission  是一个8个16位数字的字符串，为128bit，每一位代表相应的权限
     * @param functionNeed 是一个具体的0-127的数字，代表相应的权限
     * @return 如果rPermission中functionNeed的位为1，则有权限。
     */
    public static boolean checkPermission(String rPermission, Integer functionNeed) {
        return HexStrToBinStr(rPermission).charAt(functionNeed) == '1';
    }

    // 将16进制字符串转换成2进制字符串
    private static String HexStrToBinStr(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int it = Integer.parseInt(str.substring(i * 2, (i + 1) * 2), 16);
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

    // 将128位的二进制字符串，转化为8个16位的字符串
    private static String BinStrToHexStr(String bstr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int it = Integer.parseInt(bstr.substring(i * 16, (i + 1) * 16), 2);
            String s16 = Integer.toHexString(it);
            if (s16.length() == 1) sb.append(0);
            sb.append(s16);
        }
        return String.valueOf(sb);
    }

}
