package crb.gawarammana.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import crb.gawarammana.crm.models.LeadType;

public interface LeadTypeRepositoryInterface extends JpaRepository<LeadType, Long> {
	Optional<LeadType> findLeadTypeById(Long id);

	Optional<LeadType> findLeadTypeByCode(String code);

	void deleteLeadTypeById(Long id);
}
