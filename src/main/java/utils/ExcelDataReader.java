package utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import base.BasePage;

public class ExcelDataReader extends BasePage {

	@DataProvider(name = "cogmentoData")
	public Object[][] excelDataProvider() {
		Object[][] arr = getExcelData(
				"C:\\Users\\Lenovo\\eclipse-workspace\\TestNgFramework_MasterX\\src\\main\\java\\testData\\CogmentoTestData.xlsx",
				"Sheet1");
		return arr;
	}

	// Creating a method to read the data from excel sheet
	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(0);

			int noOfRows = sheet.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();
			Cell cell;
			data = new String[noOfRows - 1][noOfCols];

			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfCols; j++) {
					row = sheet.getRow(i);
					cell = row.getCell(j);
					data[i - 1][j] = cell.getStringCellValue();
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}

}
