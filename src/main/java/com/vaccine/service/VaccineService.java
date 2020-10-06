package com.vaccine.service;

import com.vaccine.entity.VaccineEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface VaccineService {

    Iterable<VaccineEntity> findAll();

    Page<VaccineEntity> findByName(String name, Pageable pageable);

    Page<VaccineEntity> findByNameUsingCustomQuery(String name, Pageable pageable);

    Page<VaccineEntity> findByEmail(String email, Pageable pageable);

    Page<VaccineEntity> findByEmailUsingCustomQuery(String email, Pageable pageable);


}
