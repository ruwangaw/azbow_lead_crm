package crb.gawarammana.crm.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crb.gawarammana.crm.exceptions.LeadTypeNotFoundException;
import crb.gawarammana.crm.models.LeadType;
import crb.gawarammana.crm.repositories.LeadTypeRepositoryInterface;

@Service
public class LeadTypeService {
	private final LeadTypeRepositoryInterface leadTypeRepository;

	@Autowired
	public LeadTypeService(LeadTypeRepositoryInterface leadTypeRepository) {
		super();
		this.leadTypeRepository = leadTypeRepository;
	}
	
	public LeadType addLeadType(LeadType leadType) {
		LeadType newLeadType = new LeadType();
	    
		newLeadType.setCode(leadType.getCode());
		newLeadType.setName(leadType.getName());
		newLeadType.setDescription(leadType.getDescription());
		newLeadType.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		
	    return leadTypeRepository.save(newLeadType);
	}
	
	public LeadType updateLeadType(LeadType leadType, Long id) {
		LeadType oldLeadType = findLeadTypeById(id);
	    
		if(leadType.getName() != null) {
			oldLeadType.setName(leadType.getName());
		}
		if(leadType.getCode() != null) {
			oldLeadType.setCode(leadType.getCode());
		}
		if(leadType.getDescription() != null) {
			oldLeadType.setDescription(leadType.getDescription());
		}
		
	    return leadTypeRepository.save(leadType);
	}
	
	public LeadType findLeadTypeById(Long id) {
		return leadTypeRepository.findLeadTypeById(id).orElseThrow(()-> new LeadTypeNotFoundException("Lead type by id " + id + " was not found"));
	}
	
	public LeadType findLeadTypesByCode(String code) {
		return leadTypeRepository.findLeadTypeByCode(code).orElseThrow(()-> new LeadTypeNotFoundException("Lead type by code " + code + " was not found"));
	}
	
	public List<LeadType> findAllLeadTypes(){
		return leadTypeRepository.findAll();
	}
	
	public void deleteLeadType(Long id) {
		leadTypeRepository.deleteLeadTypeById(id);
	}
}
