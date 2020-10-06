package com.vaccine.elasticLoader;

import com.vaccine.entity.VaccineEntity;
import javax.annotation.PostConstruct;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.elasticsearch.common.Priority.IMMEDIATE;

@Component("loader")
public class ElasticSearchLoader {
    @Qualifier("elasticsearchTemplate")
    @Autowired
    ElasticsearchOperations elasticsearchOperations;
    @Autowired
    ElasticsearchRepository elasticsearchRepository;


    @PostConstruct
    @Transactional
    public void loadAll(){
        /*System.out.println("CREATING INDEX FOR ENTITY");
        elasticsearchOperations.createIndex(VaccineEntity.class);
        System.out.println("INDEX CREATED!!!");
        System.out.println("MAPPING OF THE ELASTIC");
        elasticsearchOperations.putMapping(VaccineEntity.class);*/
    }
}
