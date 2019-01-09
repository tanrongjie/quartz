package com.trj.quartz;

import org.springframework.beans.BeanUtils;

/**
 * @BelongsProject: quartz
 * @BelongsPackage: com.trj.quartz
 * @Author: 谭荣杰
 * @CreateTime: 2018-12-04 22:06
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        String[][] a = {{"1", "2"}};
        String[][] b = {{"1", "2"}};
        System.out.println(a[0][0]);
        System.out.println(b[0][0]);
        BeanUtils.copyProperties(a, b);
        b[0][0] = "3";
        System.out.println(a[0][0]);
        System.out.println(b[0][0]);
    }
}
