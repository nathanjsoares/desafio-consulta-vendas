package com.devsuperior.dsmeta.projection;

import java.time.LocalDate;

public interface ReportSaleProjection {
    Long getId();
    LocalDate getDate();
    Double getAmount();
    String getName();
}
