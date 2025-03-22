package com.raz.cards.entity;

import java.time.LocalTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
 @Getter @Setter @ToString
public class BaseEntity {
		
	@CreatedDate
	@Column(updatable = false)
	private LocalTime createdAt;
	
	@CreatedBy
	@Column(updatable = false)
	private String createdBY;
	
	@LastModifiedDate
	@Column(insertable = false)
	private LocalTime updatedAT;
	
	@LastModifiedBy
	@Column(insertable = false)
	private String updatedBy;
}
