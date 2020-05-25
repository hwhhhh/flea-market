package com.hwhhhh.fleamarket.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/13 20:56
 */
public class ImageUtil {
    public static String COMMODITY_IMG = "//commodities//", USER_PROFILE = "//users//",
    ROOT_URL="http://localhost:8082//api//images", ROOT_PATH = "E://fleaMarketTempImg";

    public static String uploadImg(MultipartFile file, String path) throws IOException {
        //用时间戳设置新的文件名
        Date date = new Date();
        String filename = Long.toString(date.getTime());
        String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //创建文件
        File newFile = new File(ROOT_PATH + path);
        if (!newFile.exists()) {
            newFile.mkdirs();
        }
        //打开输入输出准备保存文件
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        String savePath = ROOT_PATH + path + filename + prefix;
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath));
        byte[] bs = new byte[1024];
        int len;
        while ((len = inputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        //返回文件的url
        return ROOT_URL + path + filename + prefix;
    }
}
