package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projection.ReportSummaryProjection;

public class SummaryDTO {
    private String sellerName;
    private Double total;

    public SummaryDTO() {
    }

    public SummaryDTO(String name, Double amount) {
        this.sellerName = name;
        this.total = amount;
    }

    public SummaryDTO(ReportSummaryProjection projection) {
        sellerName = projection.getName();
        total = projection.getAmount();
    }

    public void setName(String name) {
        this.sellerName = name;
    }

    public void setAmount(Double amount) {
        this.total = amount;
    }

    public String getName() {
        return sellerName;
    }

    public Double getAmount() {
        return total;
    }
}
