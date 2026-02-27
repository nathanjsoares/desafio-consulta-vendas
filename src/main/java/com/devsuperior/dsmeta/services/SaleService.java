package com.devsuperior.dsmeta.services;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.projection.ReportSaleProjection;
import com.devsuperior.dsmeta.projection.ReportSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleDTO> saleReport(String dtInitial, String dtFinal, String name, Pageable pageable){

		LocalDate dataFinal = dtInitial != null?LocalDate.parse(dtFinal):  LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate dataInitial = dtInitial != null?LocalDate.parse(dtInitial): dataFinal.minusYears(1L);

		Page<ReportSaleProjection> result = repository.reportSales(Date.valueOf(dataInitial), Date.valueOf(dataFinal), name, pageable);

		Page<SaleDTO> saleDTOPage = result.map(x -> new SaleDTO(x));

		return  saleDTOPage;
	}

	public Page<SummaryDTO> summaryReport(String dtInitial, String dtFinal, Pageable pageable){

		LocalDate dataFinal = dtInitial != null?LocalDate.parse(dtFinal):  LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate dataInitial = dtInitial != null?LocalDate.parse(dtInitial): dataFinal.minusYears(1L);

		Page<ReportSummaryProjection> result = repository.reportSummary(Date.valueOf(dataInitial), Date.valueOf(dataFinal), pageable);

		Page<SummaryDTO> summaryDTOPage = result.map(x -> new SummaryDTO(x));

        return summaryDTOPage;
	}
}
