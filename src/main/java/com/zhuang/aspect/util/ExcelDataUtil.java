package com.zhuang.aspect.util;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class ExcelDataUtil {
    static List<String> unitList = new ArrayList<>();
    static List<String> powerList = new ArrayList<>();
    static HashSet<String> set=new HashSet<>();
    public static void main(String[] args) {
        try {
            List<Map<String, String>> maps = redExcel("D:\\台账查询清单.xlsx");
            //maps.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> res = new ArrayList<>();
        for (String s : unitList) {
            for (String p : powerList) {
                StringBuilder sb = new StringBuilder();
                res.add(sb.append("浙江省/丽水市/").append(s).append("/").append(p).toString());
            }
        }
/*        for (String re : res) {
            System.out.println(re);
        }*/
        System.out.println(set.size());
    }

    /**
     * 读取excel内容
     * <p>
     * 用户模式下：
     * 弊端：对于少量的数据可以，单数对于大量的数据，会造成内存占据过大，有时候会造成内存溢出
     * 建议修改成事件模式
     */
    public static List<Map<String, String>> redExcel(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件不存在!");
        }
        InputStream in = new FileInputStream(file);

        // 读取整个Excel
        XSSFWorkbook sheets = new XSSFWorkbook(in);
        // 获取第一个表单Sheet
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        ArrayList<Map<String, String>> list = new ArrayList<>();

        //默认第一行为标题行，i = 0
        XSSFRow titleRow = sheetAt.getRow(0);
        // 循环获取每一行数据
//        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
        for (int i = 1; i < 1000; i++) {
            XSSFRow row = sheetAt.getRow(i);
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            // 读取每一格内容
            for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                String unit = "";
                String power = "";
                // title
                XSSFCell titleCell = titleRow.getCell(index);
                // title对应的值
                XSSFCell cell = row.getCell(index);
                if (titleCell.toString().equals("县级单位")) {
                    String s = cell.toString();
                    if (s.startsWith("丽水")) {
                        unit = "丽水区";
                    }
                    /*} else if (s.startsWith("缙云")) {
                        unit = "缙云区";
                    } else if (s.startsWith("景宁")) {
                        unit = "景宁区";
                    } else if (s.startsWith("莲都")) {
                        unit = "莲都区";
                    } else if (s.startsWith("龙泉")) {
                        unit = "龙泉区";
                    } else if (s.startsWith("青田")) {
                        unit = "青田区";
                    } else if (s.startsWith("庆元")) {
                        unit = "庆元区";
                    } else if (s.startsWith("松阳")) {
                        unit = "松阳区";
                    } else if (s.startsWith("遂昌")) {
                        unit = "遂昌区";
                    } else if (s.startsWith("云和")) {
                        unit = "云和区";
                    }*/
                    unitList.add(unit);
                    if (titleCell.toString().equals("供电所")) {
                        String se = cell.toString();
                        set.add(se);
                    }
                }

                // cell.setCellType(XSSFCell.CELL_TYPE_STRING); 过期，使用下面替换
                cell.setCellType(CellType.STRING);
                if (cell.getStringCellValue().equals("")) {
                    continue;
                }
                map.put(getString(titleCell), getString(cell));
            }
            if (map.isEmpty()) {
                continue;
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 把单元格的内容转为字符串
     *
     * @param xssfCell 单元格
     * @return String
     */
    public static String getString(XSSFCell xssfCell) {
        if (xssfCell == null) {
            return "";
        }
        if (xssfCell.getCellTypeEnum() == CellType.NUMERIC) {
            return String.valueOf(xssfCell.getNumericCellValue());
        } else if (xssfCell.getCellTypeEnum() == CellType.BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else {
            return xssfCell.getStringCellValue();
        }
    }
}
