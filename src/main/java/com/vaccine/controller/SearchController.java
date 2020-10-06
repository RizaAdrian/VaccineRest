package com.vaccine.controller;

import com.vaccine.elasticRepository.ElasticRepository;
import com.vaccine.entity.VaccineEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/vaccine/search")
public class SearchController {

    @Autowired
    ElasticRepository elasticRepository;


    @GetMapping(value = "/name/{name}")
    public Page<VaccineEntity> searchName(@PathVariable final String name) {
        return elasticRepository.findByName(name, PageRequest.of(0, 20));
    }


    @GetMapping(value = "/email/{email}")
    public Page<VaccineEntity> searchEmail(@PathVariable final String email) {
        return elasticRepository.findByEmail(email, PageRequest.of(0, 20));
    }


    @GetMapping(value = "/all")
    public List<VaccineEntity> searchAll() {
        List<VaccineEntity> vaccineList = new ArrayList<>();
        Iterable<VaccineEntity> vaccineEntities = elasticRepository.findAll();
        vaccineEntities.forEach(vaccineList::add);

        return vaccineList;
    }

}
