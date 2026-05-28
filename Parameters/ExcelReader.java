package com.parameter;
 
import org.testng.annotations.DataProvider;
 
public class DataProviderReader {
 
	@DataProvider(name = "ExcelData")
	public Object[][] getData1() {
	    return ExcelReader.readExcel(
	            "src/test/resources/Exceldata/data.xlsx",
	            "Sheet1"
	    );
	}
	@DataProvider(name = "couponData")
	public Object[][] getData2() {
	    return new Object[][] {
	            {"SAV10"}
	    };
	}
	@DataProvider(name = "searchData")
	public Object[][] getData3() {
	    return new Object[][] {
	            {"Thyroid Profile"}
	    };
	}
}