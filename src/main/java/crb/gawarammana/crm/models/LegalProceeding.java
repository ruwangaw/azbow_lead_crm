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
@Table(name = "LEGAL_PROCEEDINGS")
public class LegalProceeding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(name = "is_contract_signed", nullable = false)
    private Boolean isContractSigned = false;

    @Column(name = "legal_notes", length = 400)
    private String legalNotes;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

	public LegalProceeding() {
		super();
	}

	public LegalProceeding(Long id, Reservation reservation, Boolean isContractSigned, String legalNotes,
			Timestamp createdAt) {
		super();
		this.id = id;
		this.reservation = reservation;
		this.isContractSigned = isContractSigned;
		this.legalNotes = legalNotes;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Boolean getIsContractSigned() {
		return isContractSigned;
	}

	public void setIsContractSigned(Boolean isContractSigned) {
		this.isContractSigned = isContractSigned;
	}

	public String getLegalNotes() {
		return legalNotes;
	}

	public void setLegalNotes(String legalNotes) {
		this.legalNotes = legalNotes;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

    
}
