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

import crb.gawarammana.crm.models.LeadType;
import crb.gawarammana.crm.services.LeadTypeService;



@RestController
@RequestMapping("/api/leadType")
public class LeadTypeController {
private final LeadTypeService leadTypeService;
	
	public LeadTypeController(LeadTypeService leadTypeService) {
		this.leadTypeService = leadTypeService;
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<LeadType> getLeadStatusById(@PathVariable("id") Long id){
		LeadType leadTypeById = leadTypeService.findLeadTypeById(id);
		return new ResponseEntity<LeadType>(leadTypeById, HttpStatus.OK);
	}
	
	@GetMapping("/find/{code}")
	public ResponseEntity<LeadType> getLeadStatusByCode(@PathVariable("code") String code){
		LeadType leadTypeByCode = leadTypeService.findLeadTypesByCode(code);
		return new ResponseEntity<LeadType>(leadTypeByCode, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<LeadType>> getAllLeadStatuses(){
		List<LeadType> leadStatuses = leadTypeService.findAllLeadTypes();
		return new ResponseEntity<List<LeadType>>(leadStatuses, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<LeadType> createLeadStatus(@RequestBody LeadType leadStatus) {
		LeadType newleadStatus = leadTypeService.addLeadType(leadStatus);
		return new ResponseEntity<LeadType>(newleadStatus, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<LeadType> udateLeadStatus(@RequestBody LeadType leadStatus, @PathVariable  Long id) {
		LeadType newLeadStatus = leadTypeService.updateLeadType(leadStatus, id);
		return new ResponseEntity<LeadType>(newLeadStatus, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
		leadTypeService.deleteLeadType(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
