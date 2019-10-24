package com.kmmonitor.observer;


import com.util.PdfConverterUtilByAspose;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

public class FileListener extends FileAlterationListenerAdaptor {

    @Override
    public void onFileChange(File file) {
        System.out.println("文件"+file.getName()+"内容改变了");
    }

    @Override
    public void onFileCreate(File file) {
        System.out.println("文件被创建了" + file.getName());
        //如果是doc文档，则创建pdf
        PdfConverterUtilByAspose.doc2pdf(file.getPath(),"F://a.pdf");
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("文件被删除了" + file.getName());
    }

}