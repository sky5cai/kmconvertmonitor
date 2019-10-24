package com.util;

import com.aspose.cells.Workbook;
import com.aspose.slides.Presentation;
import com.aspose.words.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class PdfConverterUtilByAspose {

	public static String fontFolder = "";


	   private static boolean getLicense(String type) {
	        boolean result = false;
	        try {

//				String path = getPath();
				File file = new File("D:\\project\\test434\\kmconvertmonitor\\src\\main\\resources\\license.xml");
				InputStream is = new FileInputStream(file);
	            if(isEqual(type, "xls", "xlsx")){
	                com.aspose.cells.License aposeLic = new com.aspose.cells.License();
	                aposeLic.setLicense(is);
	            }else if(isEqual(type, "ppt", "pptx","pot","dps")){
	                com.aspose.slides.License aposeLic = new com.aspose.slides.License();
	                aposeLic.setLicense(is);
	            }else if(isEqual(type, "doc", "docx","txt","rtf")){
	                com.aspose.words.License aposeLic = new com.aspose.words.License();
	                aposeLic.setLicense(is);
	            }
	            result = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }

	    private static boolean isEqual(String type, String ... formats) {

	        for (String format:formats) {
	            if(type.equalsIgnoreCase(format)){
	                return true;
	            }
	        }
	        return false;
	    }

	    /**
	     * @param wordPath 需要被转换的word全路径带文件名
	     * @param pdfPath 转换之后pdf的全路径带文件名
	     */
	    public static void doc2pdf(String wordPath, String pdfPath) {
	        String type = wordPath.substring(wordPath.lastIndexOf(".")+1,wordPath.length());
	        if (!getLicense(type)) { // 验证License 若不验证则转化出的pdf文档会有水印产生
	            return;
	        }
	        try {
	            long old = System.currentTimeMillis();
	            File file = new File(pdfPath); //新建一个pdf文档
	            FileOutputStream os = new FileOutputStream(file);
	            Document doc = new Document(wordPath); //Address是将要被转化的word文档

	            doc.save(os, com.aspose.words.SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
	            long now = System.currentTimeMillis();
	            os.close();
	            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); //转化用时
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * @param excelPath 需要被转换的excel全路径带文件名
	     * @param pdfPath 转换之后pdf的全路径带文件名
	     */
	    public static void excel2pdf(String excelPath, String pdfPath) {
	        String type = excelPath.substring(excelPath.lastIndexOf(".")+1,excelPath.length());
	        if (!getLicense(type)) { // 验证License 若不验证则转化出的pdf文档会有水印产生
	            return;
	        }
	        try {
	            long old = System.currentTimeMillis();
	            Workbook wb = new Workbook(excelPath);// 原始excel路径

	            FileOutputStream fileOS = new FileOutputStream(new File(pdfPath));
	            wb.save(fileOS, com.aspose.cells.SaveFormat.PDF);
	            fileOS.close();
	            long now = System.currentTimeMillis();
	            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); //转化用时
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * @param pptPath 需要被转换的excel全路径带文件名
	     * @param pdfPath 转换之后pdf的全路径带文件名
	     */
	    public static void ppt2pdf(String pptPath, String pdfPath) {
	        String type = pptPath.substring(pptPath.lastIndexOf(".")+1,pptPath.length());
	        if (!getLicense(type)) { // 验证License 若不验证则转化出的pdf文档会有水印产生
	            return;
	        }
	        try {
	            long old = System.currentTimeMillis();
	            Presentation ppt = new Presentation(pptPath);
	            FileOutputStream fileOS = new FileOutputStream(new File(pdfPath));
	            ppt.save(fileOS, com.aspose.slides.SaveFormat.Pdf);
	            fileOS.close();
	            long now = System.currentTimeMillis();
	            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); //转化用时
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static void main(String[] args) {

	        //word 和excel 转为pdf
	        String filePaths="F:\\office\\添加脚本帮助指南123.docx";
	        String pdfPath="F:\\office\\添加脚本帮助指南123.pdf";
//	        String pdfPath="D:/t.pdf";
	         doc2pdf(filePaths, pdfPath);//filePaths需要转换的文件位置 pdfPath为存储位置
//	        String excel2pdf="D:/t.xlsx";
//	        excel2pdf(excel2pdf,pdfPath);
	    }

}
