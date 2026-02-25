package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<?> getReport(@RequestParam(required = false)  String minDate,
									   @RequestParam(required = false)  String maxDate,
									   @RequestParam(required = false)  String name,
									   Pageable pageable) {
		Page<SaleDTO> summary = service.saleReport(minDate,maxDate,name,pageable);

		return ResponseEntity.ok(summary);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SummaryDTO>> getSummary(@RequestParam(required = false)  String minDate,
													   @RequestParam(required = false)  String maxDate,
													   Pageable pageable) {
		Page<SummaryDTO> summary = service.summaryReport(minDate,maxDate,pageable);

		return ResponseEntity.ok(summary);
	}
}
