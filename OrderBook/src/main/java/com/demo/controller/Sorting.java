package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Orderbook;
import com.demo.service.SortingService;

@RestController
public class Sorting {

	@Autowired
	private SortingService service;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/sorted")
	public List<Orderbook> getSortedOrderBooks(@RequestParam String columnName,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		return service.getSortedOrderBooks(columnName, sortOrder);
	}

}
