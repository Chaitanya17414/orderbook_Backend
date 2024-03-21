package com.demo.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.model.Orderbook;
import com.demo.repository.OrderbookRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class SearchService {

	@Autowired
	private OrderbookRepo orderbookRepo;

	@PersistenceContext
	private EntityManager manager;

	public List<Orderbook> searchByColumnNameandValue(Map<String, String> searchParams) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Orderbook> query = cb.createQuery(Orderbook.class);
		Root<Orderbook> root = query.from(Orderbook.class);
 
		Predicate[] predicates = new Predicate[searchParams.size()];
		int index = 0;
 
		for (Map.Entry<String, String> entry : searchParams.entrySet()) {
			String columnName = entry.getKey();
			String searchValue="%"+entry.getValue().replaceAll("\\s+", "")+"%";
			String hyphenatedSearchvalue = "%" +entry.getValue().replaceAll("\\s+","-") + "%";
 
			Expression<String> expression = cb.lower(root.get(columnName));
			
			predicates[index++]=cb.or(cb.like(expression, searchValue.toLowerCase()),
					cb.like(expression,"%" + entry.getValue().toLowerCase() + "%"),    
					cb.equal(expression,hyphenatedSearchvalue.toLowerCase()));        
			
		}
 
		query.select(root).where(cb.and(predicates));
 
		List<Orderbook> resultList = manager.createQuery(query).getResultList();
 
		return resultList;
 
	}

}
