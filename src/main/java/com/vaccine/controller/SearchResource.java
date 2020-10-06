package com.vaccine.controller;

import com.vaccine.entity.VaccineEntity;
import com.vaccine.queryBuilder.SearchQueryBuilder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vaccine/search-resouce")
public class SearchResource {

    @Autowired
    private SearchQueryBuilder searchQueryBuilder;

    @GetMapping(value = "/{query}")
    public List<VaccineEntity> getAllVaccines(@PathVariable final String query) {
        return searchQueryBuilder.getAllVaccines(query);
    }
}
