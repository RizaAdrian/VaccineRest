package com.vaccine.service;

import com.vaccine.entity.VaccineEntity;
import com.vaccine.elasticRepository.ElasticRepository;
import com.vaccine.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class VaccineServiceImpl implements VaccineService {

    private final
    VaccineRepository vaccineRepository;
    public final ElasticRepository elasticRepository;

    @Autowired
    public VaccineServiceImpl(VaccineRepository vaccineRepository, ElasticRepository elasticRepository) {
        this.vaccineRepository = vaccineRepository;
        this.elasticRepository = elasticRepository;
    }

    @Override
    public Iterable<VaccineEntity> findAll() {
        return vaccineRepository.findAll();
    }

    @Override
    public Page<VaccineEntity> findByName(String name, Pageable pageable) {
        return elasticRepository.findByName(name, pageable);
    }

    @Override
    public Page<VaccineEntity> findByNameUsingCustomQuery(String name, Pageable pageable) {
        return elasticRepository.findByNameUsingCustomQuery(name, pageable);

    }

    @Override
    public Page<VaccineEntity> findByEmail(String email, Pageable pageable) {
        return elasticRepository.findByEmail(email, pageable);
    }

    @Override
    public Page<VaccineEntity> findByEmailUsingCustomQuery(String email, Pageable pageable) {
        return elasticRepository.findByEmailUsingCustomQuery(email, pageable);
    }
}
