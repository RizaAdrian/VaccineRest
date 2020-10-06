package com.vaccine.controller;

import com.vaccine.elasticRepository.ElasticRepository;
import com.vaccine.entity.VaccineEntity;
import com.vaccine.repository.VaccineRepository;
import com.vaccine.service.VaccineService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vaccine")
public class VaccineController {

    @Autowired
    private VaccineRepository vaccineRepository;
    @Autowired
    private
    ElasticRepository elasticRepository;

    @GetMapping
    public List<VaccineEntity> list() {
        return vaccineRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createVaccine(@RequestBody VaccineEntity vaccineEntity){
        vaccineRepository.save(vaccineEntity);
        elasticRepository.save(vaccineEntity);
    }

    @GetMapping("/{id}")
    public VaccineEntity getVaccine(@PathVariable("id") Long id){
        return vaccineRepository.getOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccineEntity> updateVaccine(@PathVariable(value = "id") Long vaccineId,
                                                        @Valid @RequestBody VaccineEntity vaccineDetails) throws ResourceNotFoundException {
        VaccineEntity vaccineEntity = vaccineRepository.findById(vaccineId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + vaccineId));

        vaccineEntity.setName(vaccineDetails.getName());
        vaccineEntity.setEmail(vaccineDetails.getEmail());
        vaccineEntity.setModel(vaccineDetails.getModel());
        vaccineEntity.setPhone(vaccineDetails.getPhone());
        vaccineEntity.setPurchasePrice(vaccineDetails.getPurchasePrice());
        vaccineEntity.setPurchaseDate(vaccineDetails.getPurchaseDate());
        final VaccineEntity updatedEmployee = vaccineRepository.save(vaccineEntity);
        elasticRepository.save(updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteVaccine(@PathVariable(value = "id") Long vaccineId)
            throws ResourceNotFoundException {
        VaccineEntity vaccineEntity = vaccineRepository.findById(vaccineId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + vaccineId));

        vaccineRepository.delete(vaccineEntity);
        elasticRepository.delete(vaccineEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
