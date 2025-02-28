package crb.gawarammana.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import crb.gawarammana.crm.models.LeadStatus;

public interface LeadStatusRepositoryInterface extends JpaRepository<LeadStatus, Long>{
	Optional<LeadStatus> findLeadStatusById(Long id);
	
	Optional<LeadStatus> findLeadStatusByCode(String code);

	void deleteLeadStatusById(Long id);
}
