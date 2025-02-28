package crb.gawarammana.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import crb.gawarammana.crm.models.Lead;

public interface LeadRepositoryInterface extends JpaRepository<Lead, Long>{
	Optional<Lead> findLeadById(Long id);

	void deleteLeadById(Long id);
}