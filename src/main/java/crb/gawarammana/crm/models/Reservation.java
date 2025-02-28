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
@Table(name = "RESERVATIONS")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;

    @ManyToOne
    @JoinColumn(name = "sales_agent", nullable = false)
    private Employee salesAgent;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "reservation_fee", nullable = false, precision = 30, scale = 2)
    private BigDecimal reservationFee;

    @Column(name = "expiried_at", nullable = false)
    private Timestamp expiriedAt;

    @ManyToOne
    @JoinColumn(name = "financial_status", nullable = false)
    private FinancialStatus financialStatus;

    @Column(name = "loan_amount", precision = 30, scale = 2)
    private BigDecimal loanAmount;

	public Reservation() {
		super();
	}

	public Reservation(Long id, Lead lead, Employee salesAgent, Property property, Timestamp createdAt,
			BigDecimal reservationFee, Timestamp expiriedAt, FinancialStatus financialStatus, BigDecimal loanAmount) {
		super();
		this.id = id;
		this.lead = lead;
		this.salesAgent = salesAgent;
		this.property = property;
		this.createdAt = createdAt;
		this.reservationFee = reservationFee;
		this.expiriedAt = expiriedAt;
		this.financialStatus = financialStatus;
		this.loanAmount = loanAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Lead getLead() {
		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;
	}

	public Employee getSalesAgent() {
		return salesAgent;
	}

	public void setSalesAgent(Employee salesAgent) {
		this.salesAgent = salesAgent;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public BigDecimal getReservationFee() {
		return reservationFee;
	}

	public void setReservationFee(BigDecimal reservationFee) {
		this.reservationFee = reservationFee;
	}

	public Timestamp getExpiriedAt() {
		return expiriedAt;
	}

	public void setExpiriedAt(Timestamp expiriedAt) {
		this.expiriedAt = expiriedAt;
	}

	public FinancialStatus getFinancialStatus() {
		return financialStatus;
	}

	public void setFinancialStatus(FinancialStatus financialStatus) {
		this.financialStatus = financialStatus;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

    
}