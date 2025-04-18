package com.raz.cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raz.cards.entity.Cards;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
	
	Optional<Cards> findByCardNumber(String cardNumber);
	Optional<Cards> findByMobileNumber(String mobileNumber);
}
