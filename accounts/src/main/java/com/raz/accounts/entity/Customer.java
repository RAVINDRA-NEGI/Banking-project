package com.raz.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long customerId;
		
	    @Column(nullable = false, unique = true)
	   	private String customerName;
	   	
	    @Column(nullable = false, unique = true)
	   	private String customerEmail;
	   	
	    @Column(nullable = false, unique = true)
	   	private String customerMobileNumber;
}
