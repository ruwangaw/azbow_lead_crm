package crb.gawarammana.crm.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import crb.gawarammana.crm.exceptions.CustomerNotFoundException;
import crb.gawarammana.crm.exceptions.LeadNotFoundException;
import crb.gawarammana.crm.models.Customer;
import crb.gawarammana.crm.models.Lead;
import crb.gawarammana.crm.models.User;
import crb.gawarammana.crm.repositories.CustomerRepositroyInterface;

@Service
public class CustomerService {

	private final CustomerRepositroyInterface customerRepository;

	public CustomerService(CustomerRepositroyInterface customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	public Customer addCutomer(Customer customer) {
		Customer newCustomer = createNewCustomerFromExisting(customer);
		return customerRepository.save(newCustomer);
	}

	public Customer addCustomer(Customer customer, User user) {
	    Customer existingCustomer = findCustomerByPreferredContactNumber(customer.getPreferredContactNumber());

	    if (existingCustomer != null) {
	        existingCustomer.setUser(user);
	        return customerRepository.save(updateCustomer(existingCustomer, existingCustomer.getId()));
	    } 

	    Customer newCustomer = createNewCustomerFromExisting(customer);
	    newCustomer.setUser(user);

	    return customerRepository.save(newCustomer);
	}


	private Customer createNewCustomerFromExisting(Customer customer) {
		Customer newCustomer = new Customer();
		newCustomer.setPreferredName(customer.getPreferredName());
		newCustomer.setPreferredContactNumber(customer.getPreferredContactNumber());
		newCustomer.setBudget(customer.getBudget());
		newCustomer.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		return newCustomer;
	}

	public Customer updateCustomer(Customer customer, Long id) {
		Customer oldCustomer = findCustomerById(id);

		if (customer.getPreferredName() != null) {
			oldCustomer.setPreferredName(customer.getPreferredName());
		}
		if (customer.getPreferredContactNumber() != null) {
			oldCustomer.setPreferredContactNumber(customer.getPreferredContactNumber());
		}
		if (customer.getUser() != null) {
			oldCustomer.setUser(customer.getUser());
		}
		if (customer.getBudget() != null) {
			oldCustomer.setBudget(customer.getBudget());
		}

		return customerRepository.save(oldCustomer);
	}

	public Customer findCustomerByUserId(Long userId) {
		return customerRepository.findCustomerByUserId(userId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer by user id " + userId + " was not found"));
	}
	
	public Customer findCustomerById(Long id) {
		return customerRepository.findCustomerById(id).orElseThrow(()-> new CustomerNotFoundException("Customer by id " + id + " was not found"));
	}

	public Customer findCustomerByPreferredContactNumber(String preferredContactNumber) {
		return customerRepository.findCustomerByPreferredContactNumber(preferredContactNumber)
				.orElseThrow(() -> new CustomerNotFoundException(
						"Customer by preferred contact number " + preferredContactNumber + " was not found"));
	}

}
