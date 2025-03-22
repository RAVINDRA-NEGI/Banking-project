package com.raz.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Accounts extends BaseEntity {
	
	    @Column(nullable = false, unique = true)
	    @Id
		private Long customerId;
		
	    @Column(nullable = false, unique = true)
	   	private Long accountNumber;
	   	
	    @Column(nullable = false, unique = true)
	   	private String accountType;
	   	
	    @Column(nullable = false, unique = true)
	   	private String branchAddress;
}
