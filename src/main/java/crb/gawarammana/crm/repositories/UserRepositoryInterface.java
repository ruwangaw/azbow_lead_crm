package crb.gawarammana.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import crb.gawarammana.crm.models.User;



public interface UserRepositoryInterface extends JpaRepository<User, Long>{
	Optional<User> findUserById(Long id);
	
	Optional<User> findUserByEmail(String email);

	void deleteUserById(Long id);
}
