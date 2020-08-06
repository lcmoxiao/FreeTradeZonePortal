package com.ftzp.service.lc;

import com.ftzp.mapper.lc.PermissionMapper;
import com.ftzp.mapper.lc.RoleMapper;
import com.ftzp.pojo.lc.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
权限生成流程：
传入权限数组，generatePStr(int[] permissions)生成128位权限字符串。
再用BinStrToStr(String bstr)将其转化成8位的存储字符串
以便存储到数据库

权限判断流程：
先从数据库中取得role对应的权限字符串，
先用StrToBinStr将存储的8位字符串扩展为128位的权限字符串，
再使用checkPermission(String rPermission, Integer functionNeed)判断权限
 */


@Service
public class RoleService implements RoleMapper {
    static String tt = "0000000000000000000000000000000000000000000000000000" +
            "0000000000000000000000000000000000000000000000000000000000000000000000000000";
    PermissionMapper permissionMapper;


    RoleMapper roleMapper;

    //通过权限数组生成权限字符串
    public static String generatePStr(int[] permissions) {
        StringBuilder sb = new StringBuilder(tt);
        for (int permission : permissions) {
            sb.replace(permission, permission + 1, "1");
        }
        return sb.toString();
    }

    // 将字符串转换成二进制字符串，每个字符为16位输出
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

    // 将128位的二进制字符串，转化为8个char的字符串
    public static String BinStrToStr(String bstr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append((char) (Integer.parseInt(bstr.substring(i * 16, (i + 1) * 16), 2)));
        }
        return String.valueOf(sb);
    }

    /**
     * @param rPermission  是一个8个字符的字符安川，为128位，每一位代表相应的权限
     * @param functionNeed 是一个具体的0-127的数字，代表相应的权限
     * @return 如果rPermission中functionNeed的位为1，则有权限。
     */
    public static boolean checkPermission(String rPermission, Integer functionNeed) {
        return StrToBinStr(rPermission).charAt(functionNeed) == '1';
    }

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> getRole(int rId) {
        return roleMapper.getRole(rId);
    }

    @Override
    public Role insertRole(String rName, int rPermission) {
        return roleMapper.insertRole(rName, rPermission);
    }

}
