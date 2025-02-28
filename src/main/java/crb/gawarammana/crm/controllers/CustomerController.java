package crb.gawarammana.crm.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crb.gawarammana.crm.models.Customer;
import crb.gawarammana.crm.models.Lead;
import crb.gawarammana.crm.models.LeadStatus;
import crb.gawarammana.crm.models.LeadType;
import crb.gawarammana.crm.models.User;
import crb.gawarammana.crm.services.CustomerService;
import crb.gawarammana.crm.services.LeadService;
import crb.gawarammana.crm.services.LeadStatusService;
import crb.gawarammana.crm.services.LeadTypeService;
import crb.gawarammana.crm.services.UserService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	private final CustomerService customerService;
	private final UserService userService;
	private final LeadService leadService;
	private final LeadStatusService leadStatusService;
	private final LeadTypeService leadTypeService;

	public CustomerController(CustomerService customerService, UserService userService, LeadService leadService, LeadStatusService leadStatusService, LeadTypeService leadTypeService) {
		this.customerService = customerService;
		this.userService = userService;
		this.leadService = leadService;
		this.leadStatusService = leadStatusService;
		this.leadTypeService = leadTypeService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Customer> createRegisteredCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.addCutomer(customer);
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
	}

	@PostMapping("/addRegisteredCustomer")
	public ResponseEntity<Customer> createRegisteredCustomer(@RequestBody Customer customer,
			@RequestHeader("Authorization") String jwt) {
		User user = userService.findUserByJWT(jwt);
		if(user == null) {
			
		}
		Customer newCustomer = customerService.addCustomer(customer, user);
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
	}

	@PostMapping("/add/inquiry")
	public ResponseEntity<Lead> createLead(@RequestBody Lead lead, @RequestHeader("Authorization") String jwt) {
		User user = userService.findUserByJWT(jwt);
		Customer customer = customerService.findCustomerByUserId(user.getId());
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
		Lead newlead = leadService.addLead(lead, customer);
		return new ResponseEntity<Lead>(newlead, HttpStatus.CREATED);
	}

}
