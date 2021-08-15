package it.luca.spring.controller;

import it.luca.spring.configuration.ApplicationDatasources;
import it.luca.spring.data.model.common.DataSourceSpecification;
import it.luca.spring.data.model.conduzione.ConduzionePayload;
import it.luca.spring.data.model.int002.Int002Payload;
import it.luca.spring.data.model.jarvis.JarvisPayload;
import it.luca.spring.data.model.webdisp.WebdispPayload;
import it.luca.spring.model.DataSourceResponseDto;
import it.luca.spring.service.PublishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/source")
public class SourceController {

    @Autowired
    private ApplicationDatasources datasources;

    @Autowired
    private PublishService service;

    /**
     * POST method for datasource WEBDISP
     * @param input: request body
     * @return response entity with dataSource response POJO
     */

    @PostMapping("/webdisp")
    public ResponseEntity<DataSourceResponseDto> webdisp(@RequestBody String input) {

        DataSourceSpecification<WebdispPayload> specification2 = datasources.getSpecificationForDataClass(WebdispPayload.class);
        DataSourceResponseDto dto = service.send(input, specification2);
        return new ResponseEntity<>(dto, dto.getHttpStatus());
    }


    /**
     * POST method for datasource JARVIS
     * @param input: request body
     * @return response entity with dataSource response POJO
     */

    @PostMapping("/jarvis")
    public ResponseEntity<DataSourceResponseDto> jarvis(@RequestBody String input) {

        DataSourceSpecification<JarvisPayload> specification2 = datasources.getSpecificationForDataClass(JarvisPayload.class);
        DataSourceResponseDto dto = service.send(input, specification2);
        return new ResponseEntity<>(dto, dto.getHttpStatus());
    }

    /**
     * POST method for datasource INT002
     * @param input: request body
     * @return response entity with dataSource response POJO
     */

    @PostMapping("/int002")
    public ResponseEntity<DataSourceResponseDto> int002(@RequestBody String input) {

        DataSourceSpecification<Int002Payload> specification = datasources.getSpecificationForDataClass(Int002Payload.class);
        DataSourceResponseDto dto = service.send(input, specification);
        return new ResponseEntity<>(dto, dto.getHttpStatus());
    }

    /**
     * POST method for datasource CONDUZIONE
     * @param input: request body
     * @return response entity with dataSource response POJO
     */

    @PostMapping("/conduzione")
    public ResponseEntity<DataSourceResponseDto> conduzione(@RequestBody String input) {

        DataSourceSpecification<ConduzionePayload> specification2 = datasources.getSpecificationForDataClass(ConduzionePayload.class);
        DataSourceResponseDto dto = service.send(input, specification2);
        return new ResponseEntity<>(dto, dto.getHttpStatus());
    }
}
