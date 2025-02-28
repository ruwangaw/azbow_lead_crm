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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crb.gawarammana.crm.models.LeadStatus;
import crb.gawarammana.crm.services.LeadStatusService;



@RestController
@RequestMapping("/api/leadStatus")
public class LeadStatusController {
private final LeadStatusService leadStatusService; 
	
	public LeadStatusController(LeadStatusService leadStatusService) {
		this.leadStatusService = leadStatusService;
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<LeadStatus> getLeadStatusById(@PathVariable("id") Long id){
		LeadStatus leadStatusById = leadStatusService.findLeadStatusById(id);
		return new ResponseEntity<LeadStatus>(leadStatusById, HttpStatus.OK);
	}
	
	@GetMapping("/find/{code}")
	public ResponseEntity<LeadStatus> getLeadStatusByCode(@PathVariable("code") String code){
		LeadStatus leadStatusByCode = leadStatusService.findLeadStatusByCode(code);
		return new ResponseEntity<LeadStatus>(leadStatusByCode, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<LeadStatus>> getAllLeadStatuses(){
		List<LeadStatus> leadStatuses = leadStatusService.findAllLeadStatuses();
		return new ResponseEntity<List<LeadStatus>>(leadStatuses, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<LeadStatus> createLeadStatus(@RequestBody LeadStatus leadStatus) {
		LeadStatus newleadStatus = leadStatusService.addLeadStatus(leadStatus);
		return new ResponseEntity<LeadStatus>(newleadStatus, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<LeadStatus> udateLeadStatus(@RequestBody LeadStatus leadStatus, @PathVariable  Long id) {
		LeadStatus newLeadStatus = leadStatusService.updateLeadStatus(leadStatus, id);
		return new ResponseEntity<LeadStatus>(newLeadStatus, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
		leadStatusService.deleteLeadStatus(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
