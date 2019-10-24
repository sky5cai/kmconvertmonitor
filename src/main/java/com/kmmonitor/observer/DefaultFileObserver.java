package com.kmmonitor.observer;


import org.apache.commons.vfs2.FileChangeEvent;

/**
 * 文件默认观察者
 * @author zhanbo
 */
public class DefaultFileObserver implements FileObserver{

    @Override
    public void handleFileCreated(FileChangeEvent event) throws Exception {
        System.err.println("fileCreated ---> "+event.getFile().getName());
    }

    @Override
    public void handleFileChanged(FileChangeEvent event) throws Exception {
        System.err.println("fileChanged ---> "+event.getFile().getName());
    }

    @Override
    public void handleFileDeleted(FileChangeEvent event) throws Exception {
        System.err.println("fileDeleted ---> "+event.getFile().getName());
    }
}
