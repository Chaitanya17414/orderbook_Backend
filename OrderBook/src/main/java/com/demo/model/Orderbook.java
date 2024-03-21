package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orderbook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Del_IBU;
	private String Sales_IBU;
	private String Sales_IBU_Head;
	private String Del_IBU_Head;
	private String Opportunity_Description;
	private String Customer_Name;
	private String Opportunity_ID;
	private String PO;
	private String po_availability;
	private String PID;
	private String Project_Name;
	private String TML;
	private String Account_Name;
	private String Digital_Flag;
	private String Project_vs_Annuity;
	private String Headwind_Tailwind;
	private String Dev_IMP_Supp;
	private String Technology;
	private String Competency;
	private String Pillar;
	private String Vertical_Name;
	private String Ge_nonGE;
	private String Status_CASUM;
	private String PM;
	private String PGM;
	private String BRM;
	private String CDM_L2;
	private String Business;
	private String Sub_business;
	private String Total_Contract_Amount_in_USD;
	private String PO_Currency;
	private String End_date;
	private String Unbilled_Amount;
	private String apr_23;
	private String may_23;
	private String jun_23;
	private String q1_24_net;
	private String jul_23;
	private String aug_23;
	private String sep_23;
	private String q2_24_net;
	private String oct_23;
	private String nov_23;
	private String dec_23;
	private String q3_24_net;;
	private String jan_24;
	private String feb_24;
	private String mar_24;
	private String q4_24_net;
	private String fy_23_24;
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDel_IBU() {
		return Del_IBU;
	}

	public void setDel_IBU(String del_IBU) {
		Del_IBU = del_IBU;
	}

	public String getSales_IBU() {
		return Sales_IBU;
	}

	public void setSales_IBU(String sales_IBU) {
		Sales_IBU = sales_IBU;
	}

	public String getSales_IBU_Head() {
		return Sales_IBU_Head;
	}

	public void setSales_IBU_Head(String sales_IBU_Head) {
		Sales_IBU_Head = sales_IBU_Head;
	}

	public String getDel_IBU_Head() {
		return Del_IBU_Head;
	}

	public void setDel_IBU_Head(String del_IBU_Head) {
		Del_IBU_Head = del_IBU_Head;
	}

	public String getOpportunity_Description() {
		return Opportunity_Description;
	}

	public void setOpportunity_Description(String opportunity_Description) {
		Opportunity_Description = opportunity_Description;
	}

	public String getCustomer_Name() {
		return Customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		Customer_Name = customer_Name;
	}

	public String getOpportunity_ID() {
		return Opportunity_ID;
	}

	public void setOpportunity_ID(String opportunity_ID) {
		Opportunity_ID = opportunity_ID;
	}

	public String getPO() {
		return PO;
	}

	public void setPO(String pO) {
		PO = pO;
	}

	public String getPo_availability() {
		return po_availability;
	}

	public void setPo_availability(String po_availability) {
		this.po_availability = po_availability;
	}

	public String getProject_Name() {
		return Project_Name;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public void setProject_Name(String project_Name) {
		Project_Name = project_Name;
	}

	public String getTML() {
		return TML;
	}

	public void setTML(String tML) {
		TML = tML;
	}

	public String getAccount_Name() {
		return Account_Name;
	}

	public void setAccount_Name(String account_Name) {
		Account_Name = account_Name;
	}

	public String getDigital_Flag() {
		return Digital_Flag;
	}

	public void setDigital_Flag(String digital_Flag) {
		Digital_Flag = digital_Flag;
	}

	public String getProject_vs_Annuity() {
		return Project_vs_Annuity;
	}

	public void setProject_vs_Annuity(String project_vs_Annuity) {
		Project_vs_Annuity = project_vs_Annuity;
	}

	public String getHeadwind_Tailwind() {
		return Headwind_Tailwind;
	}

	public void setHeadwind_Tailwind(String headwind_Tailwind) {
		Headwind_Tailwind = headwind_Tailwind;
	}

	public String getDev_IMP_Supp() {
		return Dev_IMP_Supp;
	}

	public void setDev_IMP_Supp(String dev_IMP_Supp) {
		Dev_IMP_Supp = dev_IMP_Supp;
	}

	public String getTechnology() {
		return Technology;
	}

	public void setTechnology(String technology) {
		Technology = technology;
	}

	public String getCompetency() {
		return Competency;
	}

	public void setCompetency(String competency) {
		Competency = competency;
	}

	public String getPillar() {
		return Pillar;
	}

	public void setPillar(String pillar) {
		Pillar = pillar;
	}

	public String getVertical_Name() {
		return Vertical_Name;
	}

	public void setVertical_Name(String vertical_Name) {
		Vertical_Name = vertical_Name;
	}

	public String getGe_nonGE() {
		return Ge_nonGE;
	}

	public void setGe_nonGE(String ge_nonGE) {
		Ge_nonGE = ge_nonGE;
	}

	public String getStatus_CASUM() {
		return Status_CASUM;
	}

	public void setStatus_CASUM(String status_CASUM) {
		Status_CASUM = status_CASUM;
	}

	public String getPM() {
		return PM;
	}

	public void setPM(String pM) {
		PM = pM;
	}

	public String getPGM() {
		return PGM;
	}

	public void setPGM(String pGM) {
		this.PGM = pGM;
	}

	public String getBRM() {
		return BRM;
	}

	public void setBRM(String bRM) {
		BRM = bRM;
	}

	public String getCDM_L2() {
		return CDM_L2;
	}

	public void setCDM_L2(String cDM_L2) {
		CDM_L2 = cDM_L2;
	}

	public String getBusiness() {
		return Business;
	}

	public void setBusiness(String business) {
		Business = business;
	}

	public String getSub_business() {
		return Sub_business;
	}

	public void setSub_business(String sub_business) {
		Sub_business = sub_business;
	}

	public String getTotal_Contract_Amount_in_USD() {
		return Total_Contract_Amount_in_USD;
	}

	public void setTotal_Contract_Amount_in_USD(String total_Contract_Amount_in_USD) {
		Total_Contract_Amount_in_USD = total_Contract_Amount_in_USD;
	}

	public String getPO_Currency() {
		return PO_Currency;
	}

	public void setPO_Currency(String pO_Currency) {
		PO_Currency = pO_Currency;
	}

	public String getEnd_date() {
		return End_date;
	}

	public void setEnd_date(String end_date) {
		End_date = end_date;
	}

	public String getUnbilled_Amount() {
		return Unbilled_Amount;
	}

	public void setUnbilled_Amount(String unbilled_Amount) {
		Unbilled_Amount = unbilled_Amount;
	}

	public String getApr_23() {
		return apr_23;
	}

	public void setApr_23(String apr_23) {
		this.apr_23 = apr_23;
	}

	public String getMay_23() {
		return may_23;
	}

	public void setMay_23(String may_23) {
		this.may_23 = may_23;
	}

	public String getJun_23() {
		return jun_23;
	}

	public void setJun_23(String jun_23) {
		this.jun_23 = jun_23;
	}

	public String getQ1_24_net() {
		return q1_24_net;
	}

	public void setQ1_24_net(String q1_24_net) {
		this.q1_24_net = q1_24_net;
	}

	public String getJul_23() {
		return jul_23;
	}

	public void setJul_23(String jul_23) {
		this.jul_23 = jul_23;
	}

	public String getAug_23() {
		return aug_23;
	}

	public void setAug_23(String aug_23) {
		this.aug_23 = aug_23;
	}

	public String getSep_23() {
		return sep_23;
	}

	public void setSep_23(String sep_23) {
		this.sep_23 = sep_23;
	}

	public String getQ2_24_net() {
		return q2_24_net;
	}

	public void setQ2_24_net(String q2_24_net) {
		this.q2_24_net = q2_24_net;
	}

	public String getOct_23() {
		return oct_23;
	}

	public void setOct_23(String oct_23) {
		this.oct_23 = oct_23;
	}

	public String getNov_23() {
		return nov_23;
	}

	public void setNov_23(String nov_23) {
		this.nov_23 = nov_23;
	}

	public String getDec_23() {
		return dec_23;
	}

	public void setDec_23(String dec_23) {
		this.dec_23 = dec_23;
	}

	public String getQ3_24_net() {
		return q3_24_net;
	}

	public void setQ3_24_net(String q3_24_net) {
		this.q3_24_net = q3_24_net;
	}

	public String getJan_24() {
		return jan_24;
	}

	public void setJan_24(String jan_24) {
		this.jan_24 = jan_24;
	}

	public String getFeb_24() {
		return feb_24;
	}

	public void setFeb_24(String feb_24) {
		this.feb_24 = feb_24;
	}

	public String getMar_24() {
		return mar_24;
	}

	public void setMar_24(String mar_24) {
		this.mar_24 = mar_24;
	}

	public String getQ4_24_net() {
		return q4_24_net;
	}

	public void setQ4_24_net(String q4_24_net) {
		this.q4_24_net = q4_24_net;
	}

	public String getFy_23_24() {
		return fy_23_24;
	}

	public void setFy_23_24(String fy_23_24) {
		this.fy_23_24 = fy_23_24;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setSerialNumber(Integer integer) {
		// TODO Auto-generated method stub
		
	}


}
