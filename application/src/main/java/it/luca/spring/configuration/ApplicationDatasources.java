package it.luca.spring.configuration;

import it.luca.spring.data.model.common.DataSourceSpecification;
import it.luca.spring.exception.DuplicateDatasourceException;
import it.luca.spring.exception.UnexistingDatasourceException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

import static it.luca.utils.functional.Stream.filter;

@Data
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationDatasources {

    private List<DataSourceSpecification<?>> dataSources;

    public <T> DataSourceSpecification<T> getSpecificationForDataClass(Class<T> dataClass) {

        List<DataSourceSpecification<?>> matchingSourceSpecification2s = filter(dataSources, x -> x.getDataClass().equals(dataClass));
        if (matchingSourceSpecification2s.isEmpty()) {
            throw new UnexistingDatasourceException(dataClass);
        } else if (matchingSourceSpecification2s.size() > 1) {
            throw new DuplicateDatasourceException(matchingSourceSpecification2s.size(), dataClass);
        } else {
            //noinspection unchecked
            return (DataSourceSpecification<T>) matchingSourceSpecification2s.get(0);
        }
    }
}
