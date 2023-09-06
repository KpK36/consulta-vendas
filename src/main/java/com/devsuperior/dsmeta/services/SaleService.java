package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

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

    public Page<SaleMinDTO> salesSummaryPerSeller(String minDate, String maxDate, String name, Pageable pageable) {

        LocalDate minDateParse = LocalDate.parse(minDate);
        LocalDate maxDateParse = LocalDate.parse(maxDate);

        if (minDate.isBlank()) {
            minDateParse = maxDateParse.minusYears(1L);

        } else if (maxDate.isBlank()) {
            maxDateParse = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        }

        return repository.sales(minDateParse, maxDateParse, name, pageable);


    }
}
