package crb.gawarammana.crm.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crb.gawarammana.crm.models.Lead;
import crb.gawarammana.crm.models.LeadStatus;
import crb.gawarammana.crm.models.LeadType;
import crb.gawarammana.crm.models.User;
import crb.gawarammana.crm.services.LeadService;
import crb.gawarammana.crm.services.LeadStatusService;
import crb.gawarammana.crm.services.LeadTypeService;
import crb.gawarammana.crm.services.UserService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {
	private final LeadService leadService;
	private final UserService userService;	 
	private final LeadStatusService leadStatusService;
	private final LeadTypeService leadTypeService;

	public LeadController(LeadService leadService, UserService userService, LeadStatusService leadStatusService, LeadTypeService leadTypeService) {
		super();
		this.leadService = leadService;
		this.userService = userService;
		this.leadStatusService = leadStatusService;
		this.leadTypeService = leadTypeService;
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Lead> getLeadById(@PathVariable("id") Long id) {
		Lead leadById = leadService.findLeadById(id);
		return new ResponseEntity<>(leadById, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Lead>> getAllLeads() {
		List<Lead> leads = leadService.findAllLeads();
		return new ResponseEntity<>(leads, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Lead> createLead(@RequestBody Lead lead, @RequestHeader("Authorization") String jwt) {

	    User user = userService.findUserByJWT(jwt);

	    LeadStatus leadStatus = leadStatusService.findLeadStatusByCode(lead.getLeadStatus().getCode());
	    if (leadStatus == null) {
	        leadStatus = leadStatusService.addLeadStatus(lead.getLeadStatus());
	    }

	    LeadType leadType = leadTypeService.findLeadTypesByCode(lead.getLeadType().getCode());
	    if (leadType == null) {
	        leadType = leadTypeService.addLeadType(lead.getLeadType());
	    }

	    lead.setLeadStatus(leadStatus);
	    lead.setLeadType(leadType);

	    Lead newLead = leadService.addLead(lead);

	    return new ResponseEntity<Lead>(newLead, HttpStatus.CREATED);
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<Lead> updateUser(@RequestBody Lead lead, @PathVariable  Long id) {
		Lead updatedLead = leadService.updateLead(lead, id);
		return new ResponseEntity<>(updatedLead, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteLead(@PathVariable("id") Long id){
		leadService.deleteLead(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
