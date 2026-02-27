package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projection.ReportSaleProjection;
import com.devsuperior.dsmeta.projection.ReportSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "SELECT tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name " +
            "FROM tb_seller INNER JOIN tb_sales ON tb_seller.id = tb_sales.SELLER_ID " +
            "WHERE tb_sales.date BETWEEN :dtInitial AND :dtFinal " +
            "AND UPPER(tb_seller.name) LIKE UPPER(CONCAT( '%' , :name , '%')) ",
            countQuery = "SELECT tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name " +
                    "FROM tb_seller INNER JOIN tb_sales ON tb_seller.id = tb_sales.SELLER_ID " +
                    "WHERE tb_sales.date BETWEEN :dtInitial AND :dtFinal " +
                    "AND UPPER(tb_seller.name) LIKE UPPER(CONCAT( '%' , :name , '%')) ")
    Page<ReportSaleProjection> reportSales(Date dtInitial, Date dtFinal, String name, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT tb_seller.name , SUM(tb_sales.amount) AS amount " +
            "FROM tb_seller INNER JOIN tb_sales ON tb_seller.id = tb_sales.SELLER_ID " +
            "WHERE tb_sales.date BETWEEN :dtInitial AND :dtFinal " +
            "GROUP BY tb_seller.name ")
    Page<ReportSummaryProjection> reportSummary(Date dtInitial, Date dtFinal, Pageable pageable);

}
