package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {

	public static Object[][] getDataFromExcel(String FilePath, String SheetName) throws IOException {

		File file = new File(FilePath);
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheet(SheetName);

		int rowcount = sheet.getLastRowNum();

		Object[][] SearchStrings = new Object[rowcount][1];
		// String[] SearchStrings= new String[rowcount];

		for (int i = 0, j = 0; i < rowcount; i++) {
			SearchStrings[i][j] = sheet.getRow(i + 1).getCell(0).getStringCellValue();
		}
		wb.close();
		return SearchStrings;

	}

}
