package com.ftzp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zjg
 * @date 2018/5/20 0:58
 * @Description
 */
public class ZipUtils {

    static Logger log = (Logger) LoggerFactory.getLogger(ZipUtils.class);

    /**
     * 创建ZIP文件
     *
     * @param sourcePath      目标文件或文件夹路径
     * @param filePathAndName 生成的zip文件存在路径（包括文件名）
     */
    public static void createZip(String sourcePath, String filePathAndName) {
        ZipOutputStream zos = null;
        try {
            //实际上此压缩包并不存在，只是目标压缩包文件
            FileOutputStream fos = new FileOutputStream(filePathAndName);
            zos = new ZipOutputStream(fos);
            //如果是浏览器请求下载zip
//            OutputStream os = httpResponse.getOutputStream();
//            zos = new ZipOutputStream(os);
//            String fileNameUnEncode = URLEncoder.encode(filePathAndName,"UTF-8"); //更改文件编码，将压缩包命名为中文
//            httpResponse.reset();
//            httpResponse.setContentType("application/msexcel");
//            httpResponse.setHeader("Content-disposition", "attachment; filename="+fileNameUnEncode+".zip");
            writeZip(new File(sourcePath), "", zos);
        } catch (FileNotFoundException e) {
            log.error("创建ZIP文件失败", e);
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                log.error("创建ZIP文件失败", e);
            }

        }
    }

    /**
     * 压缩zip,循环压缩子目录文件
     */
    private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if (file.exists()) {
            if (file.isDirectory()) {//处理文件夹
                parentPath += file.getName() + File.separator;
                File[] files = file.listFiles();
                if (files.length != 0) {
                    for (File f : files) {
                        writeZip(f, parentPath, zos);
                    }
                } else {       //空目录则创建当前目录
                    try {
                        zos.putNextEntry(new ZipEntry(parentPath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());//创建压缩文件
                    zos.putNextEntry(ze);//添加压缩文件
                    byte[] content = new byte[1024];
                    int len;
                    while ((len = fis.read(content)) != -1) {
                        zos.write(content, 0, len);
                        zos.flush();
                    }

                } catch (IOException e) {
                    log.error("创建ZIP文件失败", e);
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        log.error("创建ZIP文件失败", e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        createZip("C:\\2016-2017上半学期原始成绩", "E:\\java压缩包.zip");
    }
}
