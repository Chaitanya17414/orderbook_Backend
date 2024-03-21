package com.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.model.Orderbook;
import com.demo.repository.OrderbookRepo;
import com.demo.service.SearchService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class SearchController {

	@Autowired
	private OrderbookRepo repo;
	@Autowired
	private SearchService service;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/search")
	public ResponseEntity<List<Orderbook>> searchByColumnNameandValue(@RequestBody Map<String, String> searchParams) {
		List<Orderbook> result = service.searchByColumnNameandValue(searchParams);
		return ResponseEntity.ok(result);
	}

	// CSV file

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("csv")
	public void exportCSV(@RequestBody Map<String, String> searchParams, HttpServletResponse response) {
		List<Orderbook> resultlist = service.searchByColumnNameandValue(searchParams);

		try {
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename=orderbook_export.csv");

			PrintWriter writer = response.getWriter();
			CSVWriter csvWriter = new CSVWriter(writer);

			String[] header = { "DEL_IBU", "SALES_IBU", "SALES_IBU_HEAD", "DEL_IBU_HEAD", "OPPORTUNITY_DESCRIPTION",
					"CUSTOMER_NAME", "OPPORTUNITY_ID", "PO", "PO_AVAILABILITY", "PID", "PROJECT_NAME", "TML",
					"ACCOUNT_NAME", "DIGITAL_FLAG", "PROJECT_VS_ANNUITY", "HEADWIND_TAILWIND", "DEV_IMP_SUPP",
					"TECHNOLOGY", "COMPETENCY", "PILLAR", "VERTICAL_NAME", "GE_NONGE", "STATUS_CASUM", "PM", "PGM",
					"BRM", "CDM_L2", "BUSINESS", "SUB_BUSINESS", "Total_contract_amount_in_usd", "PO_CURRENCY",
					"END_DATE", "UNBILLED_AMOUNT", "Apr_23", "May_23", "Jun_23", "Q1_24_NET", "Jul_23", "Aug_23",
					"Sep_23", "Q2_24_NET", "Oct_23", "Nov_23", "Dec_23", "Q3_24_NET", "Jan_24", "Feb_24", "Mar_24",
					"Q4_24_NET", "FY_23_24", "Remarks" };
			csvWriter.writeNext(header);

			for (Orderbook orderbook : resultlist) {
				String[] data = { orderbook.getDel_IBU(), orderbook.getSales_IBU(), orderbook.getSales_IBU_Head(),
						orderbook.getDel_IBU_Head(), orderbook.getOpportunity_Description(),
						orderbook.getCustomer_Name(), orderbook.getOpportunity_ID(), orderbook.getPO(),
						orderbook.getPo_availability(), orderbook.getPID(), orderbook.getProject_Name(),
						orderbook.getTML(), orderbook.getAccount_Name(), orderbook.getDigital_Flag(),
						orderbook.getProject_vs_Annuity(), orderbook.getHeadwind_Tailwind(),
						orderbook.getDev_IMP_Supp(), orderbook.getTechnology(), orderbook.getCompetency(),
						orderbook.getPillar(), orderbook.getVertical_Name(), orderbook.getGe_nonGE(),
						orderbook.getStatus_CASUM(), orderbook.getPM(), orderbook.getPGM(), orderbook.getBRM(),
						orderbook.getCDM_L2(), orderbook.getBusiness(), orderbook.getSub_business(),
						orderbook.getTotal_Contract_Amount_in_USD(), orderbook.getPO_Currency(),
						orderbook.getEnd_date(), orderbook.getUnbilled_Amount(), orderbook.getApr_23(),
						orderbook.getMay_23(), orderbook.getJun_23(), orderbook.getQ1_24_net(), orderbook.getJul_23(),
						orderbook.getAug_23(), orderbook.getSep_23(), orderbook.getQ2_24_net(), orderbook.getOct_23(),
						orderbook.getNov_23(), orderbook.getDec_23(), orderbook.getQ3_24_net(), orderbook.getJan_24(),
						orderbook.getFeb_24(), orderbook.getMar_24(), orderbook.getQ4_24_net(), orderbook.getFy_23_24(),
						orderbook.getRemarks() };
				csvWriter.writeNext(data);
			}
			csvWriter.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Excel

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/excel")
	public ResponseEntity<byte[]> exportToExcel(@RequestBody Map<String, String> searchParams) {
		List<Orderbook> searchResults = service.searchByColumnNameandValue(searchParams);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Search Results");
		XSSFRow headerRow = sheet.createRow(0);
		String[] headers = { "ID", "DEL_IBU", "SALES_IBU", "SALES_IBU_HEAD", "DEL_IBU_HEAD", "OPPORTUNITY_DESCRIPTION",
				"CUSTOMER_NAME", "OPPORTUNITY_ID", "PO", "PO_AVAILABILITY", "PID", "PROJECT_NAME", "TML",
				"ACCOUNT_NAME", "DIGITAL_FLAG", "PROJECT_VS_ANNUITY", "HEADWIND_TAILWIND", "DEV_IMP_SUPP", "TECHNOLOGY",
				"COMPETENCY", "PILLAR", "VERTICAL_NAME", "GE_NONGE", "STATUS_CASUM", "PM", "PGM", "BRM", "CDM_L2",
				"BUSINESS", "SUB_BUSINESS", "Total_contract_amount_in_usd", "PO_CURRENCY", "END_DATE",
				"UNBILLED_AMOUNT", "Apr_23", "May_23", "Jun_23", "Q1_24_NET", "Jul_23", "Aug_23", "Sep_23", "Q2_24_NET",
				"Oct_23", "Nov_23", "Dec_23", "Q3_24_NET", "Jan_24", "Feb_24", "Mar_24", "Q4_24_NET", "FY_23_24",
				"Remarks" };
		for (int i = 0; i < headers.length; i++) {
			XSSFCell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
		}
		int rowNum = 1;
		for (Orderbook orderbook : searchResults) {
			XSSFRow row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(orderbook.getId());
			row.createCell(1).setCellValue(orderbook.getDel_IBU());
			row.createCell(2).setCellValue(orderbook.getSales_IBU());
			row.createCell(3).setCellValue(orderbook.getSales_IBU_Head());
			row.createCell(4).setCellValue(orderbook.getDel_IBU_Head());
			row.createCell(5).setCellValue(orderbook.getOpportunity_Description());
			row.createCell(6).setCellValue(orderbook.getCustomer_Name());
			row.createCell(7).setCellValue(orderbook.getOpportunity_ID());
			row.createCell(8).setCellValue(orderbook.getPO());
			row.createCell(9).setCellValue(orderbook.getPo_availability());
			row.createCell(10).setCellValue(orderbook.getPID());
			row.createCell(11).setCellValue(orderbook.getProject_Name());
			row.createCell(12).setCellValue(orderbook.getTML());
			row.createCell(13).setCellValue(orderbook.getAccount_Name());
			row.createCell(14).setCellValue(orderbook.getDigital_Flag());
			row.createCell(15).setCellValue(orderbook.getProject_vs_Annuity());
			row.createCell(16).setCellValue(orderbook.getHeadwind_Tailwind());
			row.createCell(17).setCellValue(orderbook.getDev_IMP_Supp());
			row.createCell(18).setCellValue(orderbook.getTechnology());
			row.createCell(19).setCellValue(orderbook.getCompetency());
			row.createCell(20).setCellValue(orderbook.getPillar());
			row.createCell(21).setCellValue(orderbook.getVertical_Name());
			row.createCell(22).setCellValue(orderbook.getGe_nonGE());
			row.createCell(23).setCellValue(orderbook.getStatus_CASUM());
			row.createCell(24).setCellValue(orderbook.getPM());
			row.createCell(25).setCellValue(orderbook.getPGM());
			row.createCell(26).setCellValue(orderbook.getBRM());
			row.createCell(27).setCellValue(orderbook.getCDM_L2());
			row.createCell(28).setCellValue(orderbook.getBusiness());
			row.createCell(29).setCellValue(orderbook.getSub_business());
			row.createCell(30).setCellValue(orderbook.getTotal_Contract_Amount_in_USD());
			row.createCell(31).setCellValue(orderbook.getPO_Currency());
			row.createCell(32).setCellValue(orderbook.getEnd_date());
			row.createCell(33).setCellValue(orderbook.getUnbilled_Amount());
			row.createCell(34).setCellValue(orderbook.getApr_23());
			row.createCell(35).setCellValue(orderbook.getMay_23());
			row.createCell(36).setCellValue(orderbook.getJun_23());
			row.createCell(37).setCellValue(orderbook.getQ1_24_net());
			row.createCell(38).setCellValue(orderbook.getJul_23());
			row.createCell(39).setCellValue(orderbook.getAug_23());
			row.createCell(40).setCellValue(orderbook.getSep_23());
			row.createCell(41).setCellValue(orderbook.getQ2_24_net());
			row.createCell(42).setCellValue(orderbook.getOct_23());
			row.createCell(43).setCellValue(orderbook.getNov_23());
			row.createCell(44).setCellValue(orderbook.getDec_23());
			row.createCell(45).setCellValue(orderbook.getQ3_24_net());
			row.createCell(46).setCellValue(orderbook.getJan_24());
			row.createCell(47).setCellValue(orderbook.getFeb_24());
			row.createCell(48).setCellValue(orderbook.getMar_24());
			row.createCell(49).setCellValue(orderbook.getQ4_24_net());
			row.createCell(50).setCellValue(orderbook.getFy_23_24());
			row.createCell(51).setCellValue(orderbook.getRemarks());
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=orderbook_export.xlsx");
		header.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		return new ResponseEntity<>(outputStream.toByteArray(), header, HttpStatus.OK);
	}

	// pdf
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> exportToPDF(@RequestBody Map<String, String> searchParams)
			throws IllegalArgumentException, IllegalAccessException {
 
		ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
 
		Document document=new Document(PageSize.A4);
		try {
			List<Orderbook> searchResults = service.searchByColumnNameandValue(searchParams);
			PdfWriter.getInstance(document, outputstream);
			document.open();
			
			Paragraph searchStatement=new Paragraph("search Results",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA,16,com.itextpdf.text.Font.BOLD));
            searchStatement.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(searchStatement);
            
            document.add(new Paragraph("\n"));
            
            int numberofResults = searchResults.size();
            Paragraph resultsCount=new Paragraph("Number of search results: "+numberofResults);
			resultsCount.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			document.add(resultsCount);
			
			document.add(new Paragraph("\n"));
 
			PdfPTable table = new PdfPTable(2);
			float[] columnWidths= {3f,6f};
			
			table.setWidths(columnWidths);
			table.setWidthPercentage(80);
			Field[] fields = Orderbook.class.getDeclaredFields();
 
			for (Orderbook orderbook : searchResults) {
				for (Field field : fields) {
					field.setAccessible(true);
					PdfPCell cell=new PdfPCell(new Paragraph(field.getName()));
					cell.setPadding(5);
					cell.setMinimumHeight(20);
					table.addCell(cell);
					
					PdfPCell valuecell=new PdfPCell(new Paragraph(String.valueOf(field.get(orderbook))));
					cell.setPadding(5);
					cell.setMinimumHeight(20);
					table.addCell(valuecell);
				}
			}
			Paragraph tableParagraph=new Paragraph();
			tableParagraph.add(table);
			tableParagraph.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			
			document.add(tableParagraph);
			document.close();
		} catch (DocumentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "search_results.pdf");
 
		return new ResponseEntity<>(outputstream.toByteArray(), headers, HttpStatus.OK);
	}

}
