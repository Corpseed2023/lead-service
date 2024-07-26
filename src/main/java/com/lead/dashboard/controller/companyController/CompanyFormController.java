package com.lead.dashboard.controller.companyController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.CompanyForm;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.dto.CreateFormDto;
import com.lead.dashboard.repository.CompanyFormRepo;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class CompanyFormController {
	
	@Autowired
	CompanyFormRepo companyFormRepo;
	
	@Autowired
	LeadRepository leadRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	UserRepo userRepo;
	
	
	@PostMapping(UrlsMapping.CREATE_COMPANY_FORM)
	public CompanyForm createCompanyForm(@RequestBody CreateFormDto createFormDto)
	{				
		CompanyForm companyForm =  new CompanyForm();
		
		companyForm.setIsPresent(createFormDto.getIsPresent());
		companyForm.setCompanyName(createFormDto.getCompanyName());
		companyForm.setCompanyId(createFormDto.getCompanyId());
		
		companyForm.setIsUnit(createFormDto.getIsUnit());
		companyForm.setUnitName(createFormDto.getUnitName());
		companyForm.setUnitId(createFormDto.getUnitId());
		
		companyForm.setAddress(createFormDto.getAddress());
		companyForm.setAssigneeId(createFormDto.getAssigneeId());
		companyForm.setCity(createFormDto.getCity());
		companyForm.setCompanyAge(createFormDto.getCompanyAge());
		companyForm.setCompanyName(createFormDto.getCompanyName());
		companyForm.setCompanyId(createFormDto.getCompanyId());
		companyForm.setCountry(createFormDto.getCountry());
		companyForm.setGstType(createFormDto.getGstType());
		companyForm.setGstDocuments(createFormDto.getGstDocuments());
		companyForm.setGstNo(createFormDto.getGstNo());
		
		companyForm.setsAddress(createFormDto.getsAddress());
		companyForm.setsCity(createFormDto.getsCity());
		companyForm.setsState(createFormDto.getsState());
		companyForm.setsCountry(createFormDto.getsCountry());
		companyForm.setStatus(createFormDto.getStatus() );
		companyForm.setIsPresent(createFormDto.getIsPresent());
		companyForm.setIsUnit(createFormDto.getIsUnit());
		Lead lead = leadRepository.findById(createFormDto.getLeadId()).get();
		companyForm.setLead(lead);
		companyForm.setPanNo(createFormDto.getPanNo());
		companyForm.setState(createFormDto.getState());
	    companyFormRepo.save(companyForm);
	   return companyForm;
	}
	@GetMapping(UrlsMapping.GET_ALL_COMPANY_FORM)
	public List<Map<String,Object>> getAllCompanyForm()
	{
		List<Map<String,Object>>result = new ArrayList<>();
		List<CompanyForm> compList = companyFormRepo.findAll();
		for(CompanyForm c:compList) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", c.getId());
			map.put("unitName", c.getUnitName());
//			map.put("primaryAddress", c.get);
			map.put("companyName", c.getCompanyName());
			map.put("lead", c.getLead());
			map.put("gstNo", c.getGstNo());
			map.put("gstType", c.getGstType());
			map.put("gstDocuments", c.getGstDocuments());
			map.put("status", c.getStatus());
			result.add(map);

		}
		return result;
	}
	
	@GetMapping(UrlsMapping.CHECK_COMPANY_EXIST)
	public Company checkCompanyExist(Long leadId)
	{
		Company company=null;
		 Long compId = companyRepository.findCompanyIdByLeadId(leadId);
		 if(compId!=null) {
			 Optional<Company> comp = companyRepository.findById(compId);
			 if(comp!=null && comp.get()!=null) {
				 company=comp.get();
			 }
		 }
		
		return company;
	}
	
	public Map<String,Object> AccountApprovalOnInvoice(String status,Long id){
		CompanyForm companyForm = companyFormRepo.findById(id).get();
		
		if("approved".equals(status)) {
			if(companyForm.getIsPresent()) {
				
			}else {
				Company company = new Company();
				company.setName(companyForm.getCompanyName());
				
				company.setGstNo(status);
				company.setGstType(companyForm.getGstType());
				company.setGstDocuments(companyForm.getGstDocuments());
				
				company.setCompanyAge(companyForm.getCompanyAge());
				Lead lead = companyForm.getLead();
				List<Lead>leadList = company.getCompanyLead();
			}
			
		}
		return null;
	}
	

	

}