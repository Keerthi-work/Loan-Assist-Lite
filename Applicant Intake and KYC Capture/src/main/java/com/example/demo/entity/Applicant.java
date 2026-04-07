package com.example.demo.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Applicant")
public class Applicant {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long applicantId;
  
    private String name;
    
    private LocalDate dob;
    
    private String nationalId;
    
    private String address;
    
    private String contactInfo;
    
    private String kycStatus;
    
    public Applicant() {}

	public Applicant(Long applicantId, String name, LocalDate dob, String nationalId, String address, String contactInfo,
			String kycStatus) {
		super();
		this.applicantId = applicantId;
		this.name = name;
		this.dob = dob;
		this.nationalId = nationalId;
		this.address = address;
		this.contactInfo = contactInfo;
		this.kycStatus = kycStatus;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(String kycStatus) {
		this.kycStatus = kycStatus;
	}

	@Override
	public String toString() {
		return "Applicant [appid=" + applicantId + ", name=" + name + ", dob=" + dob + ", nationalId=" + nationalId
				+ ", address=" + address + ", contactInfo=" + contactInfo + ", kycStatus=" + kycStatus + "]";
	}
	
 
}
