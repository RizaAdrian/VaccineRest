package com.vaccine.elasticRepository;

import com.vaccine.entity.VaccineEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticRepository extends ElasticsearchRepository<VaccineEntity, Long> {
    Page<VaccineEntity> findByName(String name, Pageable pageable);

    Page<VaccineEntity> findByEmail(String email, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"vaccine.name\": \"?0\"}}]}}")
    Page<VaccineEntity> findByNameUsingCustomQuery(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"vaccine.email\": \"?0\"}}]}}")
    Page<VaccineEntity> findByEmailUsingCustomQuery(String email, Pageable pageable);
}
