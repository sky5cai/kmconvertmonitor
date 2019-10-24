package com.kmmonitor;

import com.kmmonitor.observer.FileListener;
import com.kmmonitor.observer.DefaultFileObserver;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import org.springframework.util.StringUtils;
import org.apache.commons.vfs2.FileSystemManager;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MonitorFolder {

    private static FileSystemManager fsManager = null;

    static {
        try {
            fsManager = VFS.getManager();
        } catch (FileSystemException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @throws IOException
     */
    public void initFileMonitor() throws IOException {

        new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    //监听文件
                    FileListenerFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
    //针对文件的监视器，拿到FileChangeEvent事件
    private void FileListenerFileChangeEvent() throws Exception{
        String filePath="F:\\monitor";
        FileObject fileObj = null;
        if (StringUtils.isEmpty(filePath)) {
            throw new IOException("文件 '" + filePath + "' 为空.");
        }
        try {
            fileObj = fsManager.resolveFile(filePath);
        } catch (Exception e) {
            throw new IOException("文件 '" + filePath + "' 解析失败.");
        }
        // 定义一个监视器(初始化观察者)及事件处理程序
        FileMonitorListener fileListener = new FileMonitorListener()
                .addObserver(new DefaultFileObserver());
        DefaultFileMonitor fm = new DefaultFileMonitor(fileListener);



        fm.setRecursive(true); // 设置为级联监控
        fm.addFile(fileObj); // 增加监控文件
        fm.start(); // 启动监视器
    }

    //针对文件的监视器，拿到文件
    private void FileListenerFile() throws Exception{
        String filePath = "F:\\monitor";// 监控目录
        long interval = TimeUnit.MILLISECONDS.toMillis(100);//设置间隔0.1秒
        FileAlterationObserver observer = new FileAlterationObserver(filePath);
        observer.addListener(new FileListener());//设置文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);//常见监听
        monitor.start();//开始监听
    }

}

