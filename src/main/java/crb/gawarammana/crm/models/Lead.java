package crb.gawarammana.crm.models;

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
@Table(name = "LEADS")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "lead_status", nullable = false)
    private LeadStatus leadStatus;

    @ManyToOne
    @JoinColumn(name = "lead_type", nullable = false)
    private LeadType leadType;

    @ManyToOne
    @JoinColumn(name = "customer_initiator")
    private Customer customerInitiator;

    @ManyToOne
    @JoinColumn(name = "sales_agent")
    private Employee salesAgent;
    
    public Lead() {
    	
    }

	public Lead(Long id, String name, String description, String source, Timestamp createdAt, LeadStatus leadStatus,
			LeadType leadType, Customer customerInitiator, Employee salesAgent) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.source = source;
		this.createdAt = createdAt;
		this.leadStatus = leadStatus;
		this.leadType = leadType;
		this.customerInitiator = customerInitiator;
		this.salesAgent = salesAgent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public LeadStatus getLeadStatus() {
		return leadStatus;
	}

	public void setLeadStatus(LeadStatus leadStatus) {
		this.leadStatus = leadStatus;
	}

	public LeadType getLeadType() {
		return leadType;
	}

	public void setLeadType(LeadType leadType) {
		this.leadType = leadType;
	}

	public Customer getCustomerInitiator() {
		return customerInitiator;
	}

	public void setCustomerInitiator(Customer customerInitiator) {
		this.customerInitiator = customerInitiator;
	}

	public Employee getSalesAgent() {
		return salesAgent;
	}

	public void setSalesAgent(Employee salesAgent) {
		this.salesAgent = salesAgent;
	}

    
}
