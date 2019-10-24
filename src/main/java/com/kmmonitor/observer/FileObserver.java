package com.kmmonitor.observer;

import org.apache.commons.vfs2.FileChangeEvent;

/**
 * 文件观察者接口
 */
public interface FileObserver {

    /**
     * 处理文件创建
     * @param event
     * @throws Exception
     */
    public void handleFileCreated(FileChangeEvent event) throws Exception;

    /**
     * 处理文件修改
     * @param event
     * @throws Exception
     */
    public void handleFileChanged(FileChangeEvent event) throws Exception;

    /**
     * 处理文件删除
     * @param event
     * @throws Exception
     */
    public void handleFileDeleted(FileChangeEvent event) throws Exception;
}
