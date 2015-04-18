package com.vn.ael.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.format.annotation.NumberFormat;

import com.vn.ael.constants.FormatterPattern;
import com.vn.ael.webapp.util.ConvertUtil;


/**
 * The persistent class for the truckingdetail database table.
 * 
 */
@Entity
@NamedQuery(name="Truckingdetail.findAll", query="SELECT t FROM Truckingdetail t")
public class Truckingdetail extends BasedChildEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "consteal")
	private Contseal consteal;

	private String noOfVehicle;

	private String vehicleNo;
	
	//bi-directional many-to-one association to Truckingservice
	@ManyToOne
	@JoinColumn(name="trucking")
	private Truckingservice truckingservice;
	
	@ManyToOne
	@JoinColumn(name="nhathau")
	private Nhathau nhathau;
	
	@ManyToOne
	@JoinColumn(name="phuthau1")
	private Nhathau phuthau1;
	
	@ManyToOne
	@JoinColumn(name="phuthau2")
	private Nhathau phuthau2;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "truckingdetail")
	private List<Exfeetable> exfeetables;
	
	private Date dateDev;
	
	private String deliveryPlace;
	
	@Transient
	@NumberFormat(pattern = FormatterPattern.NUMBER_HAS_EXTENSION)
	private BigDecimal chiho;
	
	@NumberFormat(pattern = FormatterPattern.NUMBER_HAS_EXTENSION)
	private BigDecimal phuthu;
	
	private String noContractUp;
	
	private String noContractDown;
	
	@Transient
	private BigDecimal total;	
	
	@NumberFormat(pattern = FormatterPattern.NUMBER_HAS_EXTENSION)
	private BigDecimal otherFees;
	
	@NumberFormat(pattern = FormatterPattern.NUMBER_HAS_EXTENSION)
	private BigDecimal accountingPrice;

	public Truckingdetail() {
	}

	public Contseal getConsteal() {
		return this.consteal;
	}

	public void setConsteal(Contseal consteal) {
		this.consteal = consteal;
	}

	public String getNoOfVehicle() {
		return this.noOfVehicle;
	}

	public void setNoOfVehicle(String noOfVehicle) {
		this.noOfVehicle = noOfVehicle;
	}

	public String getVehicleNo() {
		return this.vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Truckingservice getTruckingservice() {
		return this.truckingservice;
	}

	public void setTruckingservice(Truckingservice truckingservice) {
		this.truckingservice = truckingservice;
	}

	public List<Exfeetable> getExfeetables() {
		return exfeetables;
	}

	public void setExfeetables(List<Exfeetable> exfeetables) {
		this.exfeetables = exfeetables;
	}

	public Nhathau getNhathau() {
		return nhathau;
	}

	public void setNhathau(Nhathau nhathau) {
		this.nhathau = nhathau;
	}

	public Date getDateDev() {
		return dateDev;
	}

	public void setDateDev(Date dateDev) {
		this.dateDev = dateDev;
	}

	public String getDeliveryPlace() {
		return deliveryPlace;
	}

	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}

	public Nhathau getPhuthau1() {
		return phuthau1;
	}

	public void setPhuthau1(Nhathau phuthau1) {
		this.phuthau1 = phuthau1;
	}

	public Nhathau getPhuthau2() {
		return phuthau2;
	}

	public void setPhuthau2(Nhathau phuthau2) {
		this.phuthau2 = phuthau2;
	}

	public BigDecimal getPhuthu() {
		return phuthu;
	}

	public void setPhuthu(BigDecimal phuthu) {
		this.phuthu = phuthu;
	}

	public String getNoContractUp() {
		return noContractUp;
	}

	public void setNoContractUp(String noContractUp) {
		this.noContractUp = noContractUp;
	}

	public String getNoContractDown() {
		return noContractDown;
	}

	public void setNoContractDown(String noContractDown) {
		this.noContractDown = noContractDown;
	}

	public BigDecimal getTotal() {
//		this.total = BigDecimal.ZERO;
//		if(this.phuthu != null){
//			this.total = this.total.add(this.phuthu);
//		}		
//		if(this.exfeetables != null && !this.exfeetables.isEmpty()){
//			for (Exfeetable exfeetable : this.exfeetables) {
//				if(exfeetable.getTotal() != null){
//					this.total = this.total.add(exfeetable.getTotal());
//				}				
//			}
//		}
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public BigDecimal getAccountingPrice() {
		return accountingPrice;
	}

	public void setAccountingPrice(BigDecimal accountingPrice) {
		this.accountingPrice = accountingPrice;
	}

	public BigDecimal getOtherFees() {
		return otherFees;
	}

	public void setOtherFees(BigDecimal otherFees) {
		this.otherFees = otherFees;
	}

	public BigDecimal getChiho() {
		return chiho;
	}

	public void setChiho(BigDecimal chiho) {
		this.chiho = chiho;
	}
	
	@Transient
	public BigDecimal getTotalTransReport(){
		return ConvertUtil.getNotNullValue(this.chiho).add(ConvertUtil.getNotNullValue(this.accountingPrice))
				.add(ConvertUtil.getNotNullValue(otherFees));
	}

}