package com;

import com.kmmonitor.MonitorFolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
@RestController
public class KmconvertmonitorApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(KmconvertmonitorApplication.class, args);
        System.out.println("启动");
        MonitorFolder monitorFolder = new MonitorFolder();
        monitorFolder.initFileMonitor();
    }

    @RequestMapping("/hello")
    public String heelo(){
        String string = "hello,you are good man";
        System.out.println(string);
        return string;
    }
}
