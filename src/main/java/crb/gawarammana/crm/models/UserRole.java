package crb.gawarammana.crm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERSROLES")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "users_id", nullable = false)
    private Long usersId;

    @Column(name = "roles_id", nullable = false)
    private Long rolesId;

    public UserRole() {
    	
    }

	public UserRole(Long id, Long usersId, Long rolesId) {
		super();
		this.id = id;
		this.usersId = usersId;
		this.rolesId = rolesId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	public Long getRolesId() {
		return rolesId;
	}

	public void setRolesId(Long rolesId) {
		this.rolesId = rolesId;
	}
    
    
}

