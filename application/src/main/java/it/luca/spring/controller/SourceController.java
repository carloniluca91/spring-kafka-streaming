package it.luca.spring.controller;

import it.luca.spring.data.model.conduzione.ConduzioneSpecificationFactory;
import it.luca.spring.data.model.int002.Int002SpecificationFactory;
import it.luca.spring.data.model.jarvis.JarvisSpecificationFactory;
import it.luca.spring.data.model.webdisp.WebdispSpecificationFactory;
import it.luca.spring.model.DataSourceResponseDto;
import it.luca.spring.service.PublishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/source")
public class SourceController {

    @Value("${topic.webdisp}")
    private String webdispTopic;

    @Autowired
    private WebdispSpecificationFactory webdispSpecificationFactory;

    @Value("${topic.jarvis}")
    private String jarvisTopic;

    @Autowired
    private JarvisSpecificationFactory jarvisSpecificationFactory;

    @Value("${topic.int002}")
    private String int002Topic;

    @Autowired
    private Int002SpecificationFactory int002SpecificationFactory;

    @Value("${topic.conduzione}")
    private String conduzioneTopic;

    @Autowired
    private ConduzioneSpecificationFactory conduzioneSpecificationFactory;

    @Autowired
    private PublishService service;

    /**
     * POST method for datasource WEBDISP
     * @param input: request body
     * @return response entity with dataSource response POJO
     */

    @PostMapping("/webdisp")
    public ResponseEntity<DataSourceResponseDto> webdisp(@RequestBody String input) {

        DataSourceResponseDto dto = service.send(webdispTopic, input, webdispSpecificationFactory.getInstance());
        return new ResponseEntity<>(dto, dto.getHttpStatus());
    }

    /**
     * POST method for datasource JARVIS
     * @param input: request body
     * @return response entity with dataSource response POJO
     */

    @PostMapping("/jarvis")
    public ResponseEntity<DataSourceResponseDto> jarvis(@RequestBody String input) {

        DataSourceResponseDto dto = service.send(jarvisTopic, input, jarvisSpecificationFactory.getInstance());
        return new ResponseEntity<>(dto, dto.getHttpStatus());
    }

    /**
     * POST method for datasource INT002
     * @param input: request body
     * @return response entity with dataSource response POJO
     */

    @PostMapping("/int002")
    public ResponseEntity<DataSourceResponseDto> int002(@RequestBody String input) {

        DataSourceResponseDto dto = service.send(int002Topic, input, int002SpecificationFactory.getInstance());
        return new ResponseEntity<>(dto, dto.getHttpStatus());
    }

    /**
     * POST method for datasource CONDUZIONE
     * @param input: request body
     * @return response entity with dataSource response POJO
     */

    @PostMapping("/conduzione")
    public ResponseEntity<DataSourceResponseDto> conduzione(@RequestBody String input) {

        DataSourceResponseDto dto = service.send(conduzioneTopic, input, conduzioneSpecificationFactory.getInstance());
        return new ResponseEntity<>(dto, dto.getHttpStatus());
    }
}
