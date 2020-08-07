package com.ftzp.service.lc;

import com.ftzp.mapper.lc.PermissionMapper;
import com.ftzp.pojo.lc.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PermissionService {

    static String tt = "0000000000000000000000000000000000000000000000000000" +
            "0000000000000000000000000000000000000000000000000000000000000000000000000000";
    PermissionMapper permissionMapper;


    //通过权限数组生成权限字符串
    public static String generatePStr(int[] permissions) {
        StringBuilder sb = new StringBuilder(tt);
        for (int permission : permissions) {
            sb.replace(permission, permission + 1, "1");
        }
        return sb.toString();
    }

    // 将字符串转换成二进制字符串，128位输出
    public static String StrToBinStr(String str) {
        char[] strChar = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : strChar) {
            String tmp = Integer.toBinaryString(c);
            sb.append("0".repeat(16 - tmp.length()));
            sb.append(tmp);
        }
        return String.valueOf(sb);
    }

    public static List<Integer> getPIdList(String str) {
        List<Integer> res = new ArrayList<>();
        String tmp = StrToBinStr(str);
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == '1') res.add(i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        String ttt = "0000000000000000000000000000000000000000000000000000" +
                "0000000000000000000000000000000000000000000000000000000000000000000000000001";
        System.out.println(BinStrToStr(ttt
        ));
    }

    // 将128位的二进制字符串，转化为8个char的字符串
    public static String BinStrToStr(String bstr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append((char) (Integer.parseInt(bstr.substring(i * 16, (i + 1) * 16), 2)));
        }
        return String.valueOf(sb);
    }

    /**
     * @param rPermission  是一个8个字符的字符串，为128位，每一位代表相应的权限
     * @param functionNeed 是一个具体的0-127的数字，代表相应的权限
     * @return 如果rPermission中functionNeed的位为1，则有权限。
     */
    public static boolean checkPermission(String rPermission, Integer functionNeed) {
        return StrToBinStr(rPermission).charAt(functionNeed) == '1';
    }

    @Autowired
    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }


    public List<Permission> getPermission(Integer pId) {
        return permissionMapper.getPermission(pId);
    }


    public void insertPermission(Permission permission) {
        permissionMapper.insertPermission(permission);
    }


    public void deletePermission(Integer pId) {
        permissionMapper.deletePermission(pId);
    }


    public void updatePermission(Permission permission) {
        permissionMapper.updatePermission(permission);
    }

    public List<Permission> getPermission(String rPermission) {
        return permissionMapper.getPermissionByList(getPIdList(rPermission));
    }
}
