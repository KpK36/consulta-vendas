package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.amount, obj.date) "
            + "FROM Sale obj "
            + "WHERE obj.date BETWEEN :minDate AND :maxDate "
            + "AND obj.seller.name LIKE CONCAT('%', :name, '%')")
    Page<SaleMinDTO> sales(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

}
