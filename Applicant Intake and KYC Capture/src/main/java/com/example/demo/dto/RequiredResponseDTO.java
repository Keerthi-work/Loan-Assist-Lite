package com.example.demo.dto;

public class RequiredResponseDTO
{	
	private ApplicationDTO application;
	private ProductDTO product;
	private ApplicantDTO applicant;
	
   public ApplicationDTO getApplication()
   {
        return application;
   }
   
   public void setApplication(ApplicationDTO application)
   {
    this.application = application;
   }

   public ProductDTO getProduct()
   {
    return product;
   }
   
   public void setProduct(ProductDTO product)
   {
    this.product = product;
   }

   public ApplicantDTO getApplicant() {
	return applicant;
   }

   public void setApplicant(ApplicantDTO applicant) {
	this.applicant = applicant;
   }
   
   

}



