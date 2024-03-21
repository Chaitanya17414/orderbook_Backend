package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.model.ApiResponse;
import com.demo.model.Orderbook;
import com.demo.service.OrderbookService;

@RestController
//@RequestMapping("/api/orders")
public class OrderbookController {
	@Autowired
	private OrderbookService service;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/getOrderbook")
	public List<Orderbook> fetchUserList() {
		List<Orderbook> orderbook = new ArrayList<>();
		orderbook = service.fetchOrderbookList();
		return orderbook;
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/getOrderbook/{id}")
	public Orderbook fetchOrderbookListById(@PathVariable Long id) {
		return service.fetchOrderbookListById(id).get();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/addOrderbook")
	public Orderbook saveOrderbookList(@RequestBody Orderbook orderbook) {
		return service.saveOrderbookToBD(orderbook);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/deleteOrderbook/{id}")
	public String deleteOrderbookListById(@PathVariable Long id) {
		return service.deleteOrderbookListById(id);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/updateOrderbook")
	public Orderbook updateOrderbookListById(@RequestBody Orderbook orderbook) {
		return service.updateOrderbookToBD(orderbook);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/import")
	public ResponseEntity<ApiResponse> importData(@RequestParam("files") List<MultipartFile> files) {

		if (files != null) {

			for (MultipartFile file : files) {

				String contentType = file.getContentType();
				if (contentType != null && (contentType.equals("text/csv"))) {

					try {
						service.importDataFromCSV(files);
						return ResponseEntity
								.ok(new ApiResponse("your data has been Uploaded successfully ", "success"));
					} catch (Exception e) {

						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								.body(new ApiResponse("please upload a valid file", "Error"));

					}

				} else if (contentType != null
						&& (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))) {
					return ResponseEntity.badRequest()
							.body(new ApiResponse("please upload a valid file", "please upload a valid file"));
				} else {

					return ResponseEntity.badRequest()
							.body(new ApiResponse("please upload a valid file", "please upload a valid file"));
				}
			}

		}
		return ResponseEntity.badRequest().body(new ApiResponse("please upload a file", "please upload a file"));
	}

	@GetMapping("/totalRecords")
	public String getTotalRecords() {
		return service.getTotalRecords();
	}

	@GetMapping("/pagination")
	public List<Orderbook> getOrderBooks(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<Orderbook> orderbookPage = service.getOrderBooks(page, size);
		return orderbookPage.getContent();
	}

}
