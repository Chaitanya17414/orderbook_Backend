package com.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.model.Orderbook;

public interface OrderbookRepo extends JpaRepository<Orderbook, Long> {

	Optional<Orderbook> findById(Long id);
	
	@Query("Select o from Orderbook o where o.PGM Like %:username%")
	List<Orderbook> findLikePGM(@Param("username") String username);
}
