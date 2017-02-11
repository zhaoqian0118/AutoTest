package Schedule.Interface.TCStage;

import Schedule.Base.ScheduleConfig;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.FileOutputStream;

/**
 * Created by zhaoqian on 17/2/11.
 */
public class CreateXls {
    // 创建新的Excel 工作簿
    static HSSFWorkbook workbook = new HSSFWorkbook();
    static HSSFFont font = workbook.createFont();
    static HSSFFont font1 = workbook.createFont();
    static HSSFCellStyle style = workbook.createCellStyle();//PASS-style
    static HSSFCellStyle style1 = workbook.createCellStyle();//FAIL-style

    //在Excel工作簿中建一工作表
    static HSSFSheet sheet = workbook.createSheet("TCStage接口");
    static HSSFRow rowinsert;
    static HSSFCell ceilinsert;

    public static void CreateXls(){
        try{
            font.setFontHeightInPoints((short) 10);//设置字体大小
            font.setFontName("Times");
            font1.setFontHeightInPoints((short) 10);//设置字体大小
            font1.setColor(HSSFColor.RED.index);//字体颜色
            font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体增粗
            font1.setFontName("Times");
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            style.setAlignment(HSSFCellStyle.ALIGN_LEFT);//字体左对齐
            style.setWrapText(true);//设置自动换行
            style.setFont(font);
            style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            style1.setAlignment(HSSFCellStyle.ALIGN_LEFT);//字体左对齐
            style1.setWrapText(true);//设置自动换行
            style1.setFont(font1);
            //设置列宽
            for(int i=0;i<5;i++) {
                sheet.autoSizeColumn(i);
            }
            sheet.setColumnWidth(5,4000);
            sheet.setColumnWidth(6,8000);
            sheet.setColumnWidth(7,8000);
            sheet.setColumnWidth(8,4000);
            sheet.setColumnWidth(9,8000);
            // 在索引0的位置创建行（最顶端的行）
            HSSFRow row = sheet.createRow((short) 0);
            //在索引0的位置创建单元格（左上端）
            HSSFCell cell = row.createCell((short) 0);
            // 定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("模块"); cell.setCellStyle(style);
            cell = row.createCell((short) 1);
            cell.setCellValue("前置条件"); cell.setCellStyle(style);
            cell = row.createCell((short) 2);
            cell.setCellValue("执行操作"); cell.setCellStyle(style);
            cell = row.createCell((short) 3);
            cell.setCellValue("API"); cell.setCellStyle(style);
            cell = row.createCell((short) 4);
            cell.setCellValue("Type"); cell.setCellStyle(style);
            cell = row.createCell((short) 5);
            cell.setCellValue("Json"); cell.setCellStyle(style);
            cell = row.createCell((short) 6);
            cell.setCellValue("接口返回结果"); cell.setCellStyle(style);
            cell = row.createCell((short) 7);
            cell.setCellValue("比对结果"); cell.setCellStyle(style);
            cell = row.createCell((short) 8);
            cell.setCellValue("核对接口"); cell.setCellStyle(style);
            cell = row.createCell((short) 9);
            cell.setCellValue("核对接口返回结果"); cell.setCellStyle(style);

            TCInterface.Execute();
            // 新建一输出文件流
            FileOutputStream fOut = new FileOutputStream(ScheduleConfig.outputFile);
            // 把相应的Excel 工作簿存盘
            workbook.write(fOut);
            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();
            System.out.println("文件生成...");
        }catch (Exception e){
            System.out.println("已运行 xlCreate() : " + e);
        }
    }

    //在调用请求前插入row i-插入行数 moudle－模块 function-前置条件 action－执行操作 api－API type－请求类型
    public static void rowbefore(int i,String moudle,String function,String action,String api,String type){
        HSSFRow rowinsert1 = sheet.createRow((short) i);
        rowinsert1.setHeight((short)2000);
        HSSFCell ceilinsert1 = rowinsert1.createCell((short)0);
        ceilinsert1.setCellType(HSSFCell.CELL_TYPE_STRING);
        ceilinsert1.setCellValue(moudle); ceilinsert1.setCellStyle(style);
        ceilinsert1 = rowinsert1.createCell((short)1);
        ceilinsert1.setCellType(HSSFCell.CELL_TYPE_STRING);
        ceilinsert1.setCellValue(function); ceilinsert1.setCellStyle(style);
        ceilinsert1 = rowinsert1.createCell((short)2);
        ceilinsert1.setCellType(HSSFCell.CELL_TYPE_STRING);
        ceilinsert1.setCellValue(action); ceilinsert1.setCellStyle(style);
        ceilinsert1 = rowinsert1.createCell((short)3);
        ceilinsert1.setCellType(HSSFCell.CELL_TYPE_STRING);
        ceilinsert1.setCellValue(api); ceilinsert1.setCellStyle(style);
        ceilinsert1 = rowinsert1.createCell((short)4);
        ceilinsert1.setCellType(HSSFCell.CELL_TYPE_STRING);
        ceilinsert1.setCellValue(type); ceilinsert1.setCellStyle(style);
        rowinsert=rowinsert1;
        ceilinsert=ceilinsert1;
    }

    //在调用请求后插入row json－请求参数 param－接口返回结果 response-数据结果 note－核对接口 noteresult－核对接口返回结果
    public static void rowafter(String json,String param, String response,String note,String noteresult,int i){
        if(json.length()>30000){
            json=json.substring(0,30000);
        }
        if(param.length()>30000){
            param=param.substring(0,30000);
        }
        if(response.length()>30000){
            response=response.substring(0,30000);
        }
        if(note.length()>30000){
            note=note.substring(0,30000);
        }
        if(noteresult.length()>30000){
            noteresult=noteresult.substring(0,30000);
        }
        ceilinsert = rowinsert.createCell((short)5);
        ceilinsert.setCellType(HSSFCell.CELL_TYPE_STRING);
        ceilinsert.setCellValue(json); ceilinsert.setCellStyle(style);
        ceilinsert = rowinsert.createCell((short)6);
        ceilinsert.setCellType(HSSFCell.CELL_TYPE_STRING);
        ceilinsert.setCellValue(param);
        if(i==1) {
            ceilinsert.setCellStyle(style);
        }
        if(i==0) {
            ceilinsert.setCellStyle(style1);
        }
        ceilinsert = rowinsert.createCell((short)7);
        ceilinsert.setCellType(HSSFCell.CELL_TYPE_STRING);
        ceilinsert.setCellValue(response);
        if(i==1) {
            ceilinsert.setCellStyle(style);
        }
        if(i==0) {
            ceilinsert.setCellStyle(style1);
        }
        ceilinsert = rowinsert.createCell((short)8);
        ceilinsert.setCellType(HSSFCell.CELL_TYPE_STRING);
        ceilinsert.setCellValue(note);ceilinsert.setCellStyle(style);
        ceilinsert = rowinsert.createCell((short)9);
        ceilinsert.setCellType(HSSFCell.CELL_TYPE_STRING);
        ceilinsert.setCellValue(noteresult); ceilinsert.setCellStyle(style);
    }
}
