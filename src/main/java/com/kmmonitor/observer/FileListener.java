package com.kmmonitor.observer;


import com.util.FileWrite;
import com.util.PdfConverterUtilByAspose;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;
import java.util.List;

public class FileListener extends FileAlterationListenerAdaptor {

    @Override
    public void onFileChange(File file) {
        System.out.println("文件"+file.getName()+"内容改变了");
    }

    @Override
    public void onFileCreate(File file) {

        List<String> lists = FileWrite.readFile(file.getPath());
        for (String filePath : lists) {
            String ffix = filePath.substring(filePath.lastIndexOf(".")+1,filePath.length());
            System.out.println(ffix);
            //截取路径
            File fileTemp = new File(filePath);
            String fileName = fileTemp.getName();
            String pdfFileName = fileName.substring(0,fileName.lastIndexOf("."))+".pdf";
            File parentFile = fileTemp.getParentFile();
            File pdfFile = new File(parentFile.getPath()+File.separator+"swf",pdfFileName);
            //如果是doc文档，则创建pdf
            System.out.println("文件被创建了" + file.getName());
            PdfConverterUtilByAspose.doc2pdf(filePath,pdfFile.getPath());
            System.out.println("dd");

            //转换完之后删掉文件
            if(file.exists()){
                file.delete();
            }
        }
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("文件被删除了" + file.getName());
    }

}