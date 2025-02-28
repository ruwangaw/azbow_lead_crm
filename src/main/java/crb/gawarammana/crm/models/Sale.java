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
@Table(name = "SALES")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(name = "sold_at")
    private Timestamp soldAt;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "commission_details", length = 150)
    private String commisionDetails;

	public Sale() {
		super();
	}

	public Sale(Long id, Reservation reservation, Timestamp soldAt, Timestamp createdAt, String commisionDetails) {
		super();
		this.id = id;
		this.reservation = reservation;
		this.soldAt = soldAt;
		this.createdAt = createdAt;
		this.commisionDetails = commisionDetails;
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

	public Timestamp getSoldAt() {
		return soldAt;
	}

	public void setSoldAt(Timestamp soldAt) {
		this.soldAt = soldAt;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getCommisionDetails() {
		return commisionDetails;
	}

	public void setCommisionDetails(String commisionDetails) {
		this.commisionDetails = commisionDetails;
	}
    
    
}
