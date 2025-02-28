package crb.gawarammana.crm.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import crb.gawarammana.crm.exceptions.LeadNotFoundException;
import crb.gawarammana.crm.models.Customer;
import crb.gawarammana.crm.models.Lead;
import crb.gawarammana.crm.repositories.LeadRepositoryInterface;

@Service
public class LeadService {
	private final LeadRepositoryInterface leadRepository;

	@Autowired
	public LeadService(LeadRepositoryInterface leadRepository) {
		super();
		this.leadRepository = leadRepository;
	}
	
	public Lead addLead(Lead lead) {
	    Lead newLead = createNewLeadFromExisting(lead);
	    return leadRepository.save(newLead);
	}
	
	public Lead addLead(Lead lead, Customer customer) {
	    Lead newLead = createNewLeadFromExisting(lead);
	    newLead.setCustomerInitiator(customer);
	    return leadRepository.save(newLead);
	}

	private Lead createNewLeadFromExisting(Lead lead) {
	    Lead newLead = new Lead();
	    newLead.setName(lead.getName());
	    newLead.setDescription(lead.getDescription());
	    newLead.setSource(lead.getSource());
	    newLead.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
	    newLead.setLeadStatus(lead.getLeadStatus());
	    newLead.setLeadType(lead.getLeadType());
	    return newLead;
	}
	
	public Lead updateLead(Lead lead, Long id) {
		Lead oldLead = findLeadById(id);
		
		if(lead.getName() != null) {
			oldLead.setName(lead.getName());
		}
		if(lead.getDescription() != null) {
			oldLead.setDescription(lead.getDescription());
		}
		if(lead.getSource() != null) {
			oldLead.setSource(lead.getSource());
		}
		if(lead.getLeadStatus() != null) {
			oldLead.setLeadStatus(lead.getLeadStatus());
		}
		if(lead.getSalesAgent() != null) {
			oldLead.setSalesAgent(lead.getSalesAgent());
		}
		
		return leadRepository.save(oldLead);
	}
	
	public List<Lead> findAllLeads(){
		return leadRepository.findAll();
	}
	
	public Lead findLeadById(Long id) {
		return leadRepository.findLeadById(id).orElseThrow(()-> new LeadNotFoundException("Lead by id " + id + " was not found"));
	}
	
	public void deleteLead(Long id) {
		leadRepository.deleteLeadById(id);
	}
}
