package crb.gawarammana.crm.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crb.gawarammana.crm.exceptions.LeadStatusNotFoundException;
import crb.gawarammana.crm.models.LeadStatus;
import crb.gawarammana.crm.repositories.LeadStatusRepositoryInterface;

@Service
public class LeadStatusService {

	private final LeadStatusRepositoryInterface leadStatusRepository;

	@Autowired
	public LeadStatusService(LeadStatusRepositoryInterface leadStatusRepository) {
		super();
		this.leadStatusRepository = leadStatusRepository;
	}
	
	public LeadStatus addLeadStatus(LeadStatus leadStatus) {
		LeadStatus newLeadStatus = new LeadStatus();
	    
		newLeadStatus.setCode(leadStatus.getCode());
		newLeadStatus.setName(leadStatus.getName());
		newLeadStatus.setDescription(leadStatus.getDescription());
		newLeadStatus.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		
	    return leadStatusRepository.save(newLeadStatus);
	}
	
	public LeadStatus updateLeadStatus(LeadStatus leadStatus, Long id) {
		LeadStatus oldLead = findLeadStatusById(id);
	    
		if(leadStatus.getName() != null) {
			oldLead.setName(leadStatus.getName());
		}
		if(leadStatus.getCode() != null) {
			oldLead.setCode(leadStatus.getCode());
		}
		if(leadStatus.getDescription() != null) {
			oldLead.setDescription(leadStatus.getDescription());
		}
		
	    return leadStatusRepository.save(oldLead);
	}
	
	public LeadStatus findLeadStatusById(Long id) {
		return leadStatusRepository.findLeadStatusById(id).orElseThrow(()-> new LeadStatusNotFoundException("Lead status by id " + id + " was not found"));
	}
	
	public LeadStatus findLeadStatusByCode(String code) {
		return leadStatusRepository.findLeadStatusByCode(code).orElseThrow(()-> new LeadStatusNotFoundException("Lead status by code " + code + " was not found"));
	}
	
	public List<LeadStatus> findAllLeadStatuses(){
		return leadStatusRepository.findAll();
	}
	
	public void deleteLeadStatus(Long id) {
		leadStatusRepository.deleteLeadStatusById(id);
	}
}
