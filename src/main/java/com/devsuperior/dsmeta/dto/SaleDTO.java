package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projection.ReportSaleProjection;
import com.devsuperior.dsmeta.projection.ReportSummaryProjection;

import java.time.LocalDate;

public class SaleDTO {
    private Long id;
    private LocalDate date;
    private Double amount;
    private String name;

    public SaleDTO(){
    }

    public SaleDTO(ReportSaleProjection projection) {
        id = projection.getId();
        date = projection.getDate();
        amount = projection.getAmount();
        name = projection.getName();
    }

    public SaleDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.name = sellerName;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
