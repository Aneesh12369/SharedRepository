package com.me.views;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.me.dto.StudentDto;

@SuppressWarnings("unchecked")
@Component("excelSheet")
public class ExcelDocumentWriter extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<StudentDto> listDto = null;
		Row row = null;
		int i = 0;
		listDto = (List<StudentDto>) model.get("studentList");
		response.setContentType("application/vnd.ms-excel");
		HSSFSheet sheet = workbook.createSheet("Sheet1");

		for (StudentDto name : listDto) {

			row = sheet.createRow(i);
			row.createCell(1).setCellValue(name.getId());
			row.createCell(2).setCellValue(name.getName());
			row.createCell(3).setCellValue(name.getAddress());
			row.createCell(4).setCellValue(name.getAge());
			i++;
		}

	}

}
