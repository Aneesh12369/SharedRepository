package com.me.views;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.me.dto.StudentDto;

@SuppressWarnings("unchecked")
@Component("pdfSheet")
public class PdfDocumentWriter extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<StudentDto> listDto = null;
		listDto = (List<StudentDto>) model.get("studentList");
		Paragraph p = new Paragraph();
		p.add("StudentDetails");
		p.setAlignment("center");
		p.setFont(new Font(Font.BOLD));
		document.add(p);
		Table t = new Table(4);
		for (StudentDto dto : listDto) {
			t.addCell(dto.getId() + "");
			t.addCell(dto.getName() + "");
			t.addCell(dto.getAddress() + "");
			t.addCell(dto.getAge() + "");
		}
		document.add(t);

	}

}
