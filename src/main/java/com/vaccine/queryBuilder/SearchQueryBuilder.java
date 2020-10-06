package com.vaccine.queryBuilder;

import com.vaccine.entity.VaccineEntity;
import java.util.List;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
public class SearchQueryBuilder {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public List<VaccineEntity> getAllVaccines(String query) {

        QueryBuilder buildQuery = QueryBuilders.boolQuery()
                .should(
                        QueryBuilders.queryStringQuery(query)
                                .lenient(true)
                                .field("name")
                                .field("model")
                                .field("email")
                ).should(QueryBuilders.queryStringQuery("*" + query + "*")
                        .lenient(true)
                        .field("name")
                        .field("model")
                        .field("email"));
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(buildQuery)
                .build();
        List<VaccineEntity> vaccineEntities = elasticsearchTemplate.queryForList(build, VaccineEntity.class);

        return vaccineEntities;
    }
}
