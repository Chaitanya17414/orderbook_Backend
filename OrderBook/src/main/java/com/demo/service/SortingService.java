package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.model.Orderbook;
import com.demo.repository.OrderbookRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class SortingService {

	@Autowired
	private EntityManager manager;

	public List<Orderbook> getSortedOrderBooks(String columnName, String sortOrder) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Orderbook> query = cb.createQuery(Orderbook.class);
		Root<Orderbook> root = query.from(Orderbook.class);

		if (sortOrder.equalsIgnoreCase("asc")) {
			query.orderBy(cb.asc(root.get(columnName)));
		} else if (sortOrder.equalsIgnoreCase("desc")) {
			query.orderBy(cb.desc(root.get(columnName)));
		} else {
			throw new IllegalArgumentException("invalid setOrder. use asc or desc");
		}
		return manager.createQuery(query).getResultList();
	}

}
