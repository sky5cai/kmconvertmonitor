package com.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWrite {

    public static void writeFile(String filePathString,String filePath) {
        try {
            String toStr=filePathString;
            File systemMonitorFile=new File(filePath);
            if(!systemMonitorFile.exists()) systemMonitorFile.createNewFile();
            FileWriter fw=new FileWriter(filePath);

            fw.write(toStr);
            fw.flush();
            fw.close();
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFile(String filePath){
        List<String> lists=null;
        try {
            lists = new ArrayList<>();
            File fileSystemMonitor=new File(filePath);
            if(!fileSystemMonitor.exists()){
                fileSystemMonitor.createNewFile();
            }
            String line="";
            BufferedReader br=new BufferedReader(new FileReader(filePath));
            while((line=br.readLine())!=null){
                lists.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }

}
