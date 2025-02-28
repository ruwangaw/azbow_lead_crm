package crb.gawarammana.crm.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @Column(name = "Preferred_name", nullable = false, length = 300)
    private String preferredName;

    @Column(name = "preferred_contact_number", nullable = false, length = 20, unique = true)
    private String preferredContactNumber;

    @Column(name = "budget", nullable = false, precision = 30, scale = 2)
    private BigDecimal budget;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Customer() {
    	
    }

	public Customer(Long id, User user, String preferredName, String preferredContactNumber, BigDecimal budget,
			Timestamp createdAt) {
		super();
		this.id = id;
		this.user = user;
		this.preferredName = preferredName;
		this.preferredContactNumber = preferredContactNumber;
		this.budget = budget;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPreferredName() {
		return preferredName;
	}

	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}

	public String getPreferredContactNumber() {
		return preferredContactNumber;
	}

	public void setPreferredContactNumber(String preferredContactNumber) {
		this.preferredContactNumber = preferredContactNumber;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
    
    
}
