package com.demo.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.model.Orderbook;
import com.demo.model.UserEntity;
import com.demo.repository.OrderbookRepo;
import com.demo.repository.UserRepo;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

@Service
public class OrderbookService {

	@Autowired
	private OrderbookRepo orderbookRepo;
	@Autowired
	private UserRepo repo;

	public List<Orderbook> fetchOrderbookList() {
		return orderbookRepo.findAll();
	}

	public Optional<Orderbook> fetchOrderbookListById(Long id) {
		return orderbookRepo.findById(id);
	}

	public Orderbook saveOrderbookToBD(Orderbook Orderbook) {

		orderbookRepo.save(Orderbook);
		return Orderbook;

	}

	public String getTotalRecords() {
		long count = orderbookRepo.count();
		if (count > 0) {
			return "Total Number Of Records Present in the Database = " + count;

		} else {
			return "No Records Found Try to Insert Records";
		}
	}

	public Page<Orderbook> getOrderBooks(int page, int size) {

		PageRequest pageable = PageRequest.of(page, size);
		return orderbookRepo.findAll(pageable);
	}

	public Orderbook updateOrderbookToBD(Orderbook Orderbook) {

		Orderbook existingOrderbook = null;

		Optional<Orderbook> optionalOrderbook = orderbookRepo.findById(Orderbook.getId());
		System.out.println(optionalOrderbook.isPresent());

		if (optionalOrderbook.isPresent()) {

			existingOrderbook = optionalOrderbook.get();
			System.out.println(existingOrderbook.getId());
			existingOrderbook.setId(Orderbook.getId());

			existingOrderbook.setDel_IBU(Orderbook.getDel_IBU());

			existingOrderbook.setSales_IBU(Orderbook.getSales_IBU());

			existingOrderbook.setSales_IBU_Head(Orderbook.getSales_IBU_Head());

			existingOrderbook.setDel_IBU_Head(Orderbook.getDel_IBU_Head());

			existingOrderbook.setOpportunity_Description(Orderbook.getOpportunity_Description());

			existingOrderbook.setCustomer_Name(Orderbook.getCustomer_Name());

			existingOrderbook.setOpportunity_ID(Orderbook.getOpportunity_ID());

			existingOrderbook.setPO(Orderbook.getPO());

			existingOrderbook.setPo_availability(Orderbook.getPo_availability());

			existingOrderbook.setPID(Orderbook.getPID());

			existingOrderbook.setProject_Name(Orderbook.getProject_Name());

			existingOrderbook.setTML(Orderbook.getTML());

			existingOrderbook.setAccount_Name(Orderbook.getAccount_Name());

			existingOrderbook.setDigital_Flag(Orderbook.getDigital_Flag());

			existingOrderbook.setProject_vs_Annuity(Orderbook.getProject_vs_Annuity());

			existingOrderbook.setHeadwind_Tailwind(Orderbook.getHeadwind_Tailwind());

			existingOrderbook.setDev_IMP_Supp(Orderbook.getDev_IMP_Supp());

			existingOrderbook.setTechnology(Orderbook.getTechnology());

			existingOrderbook.setCompetency(Orderbook.getCompetency());

			existingOrderbook.setPillar(Orderbook.getPillar());

			existingOrderbook.setVertical_Name(Orderbook.getVertical_Name());

			existingOrderbook.setGe_nonGE(Orderbook.getGe_nonGE());

			existingOrderbook.setStatus_CASUM(Orderbook.getStatus_CASUM());

			existingOrderbook.setPM(Orderbook.getPM());

			existingOrderbook.setPGM(Orderbook.getPGM());

			existingOrderbook.setBRM(Orderbook.getBRM());

			existingOrderbook.setCDM_L2(Orderbook.getCDM_L2());

			existingOrderbook.setBusiness(Orderbook.getBusiness());

			existingOrderbook.setSub_business(Orderbook.getSub_business());

			existingOrderbook.setTotal_Contract_Amount_in_USD(Orderbook.getTotal_Contract_Amount_in_USD());

			existingOrderbook.setPO_Currency(Orderbook.getPO_Currency());

			existingOrderbook.setEnd_date(Orderbook.getEnd_date());

			existingOrderbook.setUnbilled_Amount(Orderbook.getUnbilled_Amount());

			existingOrderbook.setApr_23(Orderbook.getApr_23());

			existingOrderbook.setMay_23(Orderbook.getMay_23());

			existingOrderbook.setJun_23(Orderbook.getJun_23());

			existingOrderbook.setQ1_24_net(Orderbook.getQ1_24_net());

			existingOrderbook.setJul_23(Orderbook.getJul_23());

			existingOrderbook.setAug_23(Orderbook.getAug_23());

			existingOrderbook.setSep_23(Orderbook.getSep_23());

			existingOrderbook.setQ2_24_net(Orderbook.getQ2_24_net());

			existingOrderbook.setOct_23(Orderbook.getOct_23());

			existingOrderbook.setNov_23(Orderbook.getNov_23());

			existingOrderbook.setDec_23(Orderbook.getDec_23());

			existingOrderbook.setQ3_24_net(Orderbook.getQ3_24_net());

			existingOrderbook.setJan_24(Orderbook.getJan_24());

			existingOrderbook.setFeb_24(Orderbook.getFeb_24());

			existingOrderbook.setMar_24(Orderbook.getMar_24());

			existingOrderbook.setQ4_24_net(Orderbook.getQ4_24_net());

			existingOrderbook.setFy_23_24(Orderbook.getFy_23_24());

			existingOrderbook.setRemarks(Orderbook.getRemarks());

			orderbookRepo.save(existingOrderbook);

		}

		return existingOrderbook;

	}

	public String deleteOrderbookListById(Long id) {
		String result;
		try {
			orderbookRepo.deleteById(id);
			result = "Deleted";
		} catch (Exception e) {
			result = "Not Deleted";
		}
		return result;
	}

	public void importDataFromCSV(List<MultipartFile> files)
			throws SQLException, ClassNotFoundException, CsvException, InvalidFormatException {
		try {
			for (MultipartFile file : files) {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/excel", "root", "root");

				InputStreamReader reader = new InputStreamReader(file.getInputStream());

				CSVReader csvReader = new CSVReaderBuilder(reader)

						.withSkipLines(1)

						.build();

				List<String[]> data = csvReader.readAll();

				for (String[] row : data) {

					PreparedStatement statement = con.prepareStatement(
							"insert into orderbook(DEL_IBU, SALES_IBU, SALES_IBU_HEAD, DEL_IBU_HEAD, OPPORTUNITY_DESCRIPTION,"
									+ "CUSTOMER_NAME,OPPORTUNITY_ID, PO, PO_AVAILABILITY, PID, PROJECT_NAME, TML, ACCOUNT_NAME,"
									+ "DIGITAL_FLAG, PROJECT_VS_ANNUITY, HEADWIND_TAILWIND, DEV_IMP_SUPP, TECHNOLOGY, COMPETENCY,"
									+ "PILLAR, VERTICAL_NAME, GE_NONGE, STATUS_CASUM, PM, PGM, BRM, CDM_L2, BUSINESS, SUB_BUSINESS,"
									+ "Total_contract_amount_in_usd, PO_CURRENCY, END_DATE, UNBILLED_AMOUNT, Apr_23, May_23, Jun_23,"
									+ "Q1_24_NET,Jul_23, Aug_23, Sep_23, Q2_24_NET, Oct_23, Nov_23, Dec_23, Q3_24_NET, Jan_24, Feb_24,"
									+ "Mar_24, Q4_24_NET,FY_23_24, Remarks)"
									+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

					for (int i = 0; i < 51; i++) {
						statement.setString(i + 1, row[i]);
					}

					statement.executeUpdate();

				}

				System.out.print("data inserted into database");
				con.close();
			}

		} catch (IOException e) {
			System.out.println("invalid file");
		}
	}

}
