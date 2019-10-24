package com.kmmonitor;

import com.kmmonitor.observer.FileObserver;
import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;


import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;


/**
 * 文件监控监听器
 * @author zhanbo
 */
public class FileMonitorListener implements FileListener{

    /**
     * 观察者列表
     */
    private Set<FileObserver> observers = new HashSet<FileObserver>();

    @Override
    public void fileCreated(FileChangeEvent fileChangeEvent) throws Exception {
        handle(fileChangeEvent,"handleFileCreated");
    }

    @Override
    public void fileDeleted(FileChangeEvent fileChangeEvent) throws Exception {
        handle(fileChangeEvent,"handleFileDeleted");
    }

    @Override
    public void fileChanged(FileChangeEvent fileChangeEvent) throws Exception {
        handle(fileChangeEvent,"handleFileChanged");
    }

    /**
     * 添加观察者
     * @param observer
     */
    public FileMonitorListener addObserver(FileObserver observer) {
        this.observers.add(observer);
        return this;
    }


    /**
     * 处理操作
     * @param event
     * @param methodName
     * @throws Exception
     */
    private void handle(FileChangeEvent event,String methodName) throws Exception {
        try {
            Method handleMethod = FileObserver.class.getMethod(methodName,FileChangeEvent.class);

            for(FileObserver observer:observers) {
                handleMethod.invoke(observer,event);
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("处理方法运行出错!");
        }
    }
}
