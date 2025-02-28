package crb.gawarammana.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import crb.gawarammana.crm.models.Customer;

public interface CustomerRepositroyInterface  extends JpaRepository<Customer, Long>{
	Optional<Customer> findCustomerById(Long id);
	
	Optional<Customer> findCustomerByUserId(Long userId);
	
	Optional<Customer> findCustomerByPreferredContactNumber(String preferredPhoneNumber);

	void deleteLeadById(Long id);
}
