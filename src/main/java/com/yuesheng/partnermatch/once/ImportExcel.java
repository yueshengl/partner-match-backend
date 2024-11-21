package com.yuesheng.partnermatch.once;

import com.alibaba.excel.EasyExcel;

import java.util.List;

/**
 * @Author: Dai
 * @Date: 2024/11/12 11:37
 * @Description: ImportExcel
 * @Version: 1.0
 */
public class ImportExcel {

    /**
     * 读取数据
     */
    public static void main(String[] args) {
        String fileName = "F:\\IDEA\\IdeaProjects\\user-center\\src\\main\\resources\\testExcel.xlsx";
       // readByListener(fileName);
        synchronousRead(fileName);
    }

    /**
     * 监听器读取
     * @param fileName
     */
    private static void readByListener(String fileName) {
        EasyExcel.read(fileName, XingQiuTableUserInfo.class, new TableListener()).sheet().doRead();
    }


    /**
     * 同步读取
     * @param fileName
     */
    public static void synchronousRead(String fileName) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<XingQiuTableUserInfo> totalDatalist = EasyExcel.read(fileName).head(XingQiuTableUserInfo.class).sheet().doReadSync();
        for(XingQiuTableUserInfo userInfo:totalDatalist){
            System.out.println(userInfo);
        }
    }
}
