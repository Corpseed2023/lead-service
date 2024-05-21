package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.lead.Lead;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	@Query(value = "SELECT c.id FROM company c left join company_lead cl on c.id=cl.company_id where cl.company_lead_id=:leadId", nativeQuery = true)
	Long findCompanyIdByLeadId(Long leadId);

	@Query(value = "SELECT * FROM company c where c.assignee_id in(:userList)", nativeQuery = true)
	List<Company> findAllByAssigneeIdIn(List<Long> userList);

	@Query(value = "SELECT * FROM company c where c.assignee_id=:userId", nativeQuery = true)
	List<Company> findByAssigneeId(Long userId);
	
	

}
