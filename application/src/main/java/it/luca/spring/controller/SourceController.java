package it.luca.spring.controller;

import it.luca.spring.data.model.webdisp.WebdispSpecification;
import it.luca.spring.model.response.DataSourceResponseDto;
import it.luca.spring.service.PublishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    @Value("${topic.jarvis}")
    private String jarvisTopic;

    @Value("${topic.int002}")
    private String int002Topic;

    @Autowired
    private PublishService service;

    /**
     * POST method for datasource WEBDISP
     * @param input: request body
     * @return SourceResponse
     */

    @PostMapping("/webdisp")
    public ResponseEntity<DataSourceResponseDto> webdisp(@RequestBody String input) {

        WebdispSpecification specification = new WebdispSpecification(webdispTopic);
        return new ResponseEntity<>(service.send(input, specification), HttpStatus.OK);
    }

    /**
     * POST method for datasource JARVIS
     * @param input: request body
     * @return SourceResponse
     */

    /*

    @PostMapping("/jarvis")
    public SourceResponse jarvis(@RequestBody String input) {
        return senderService.send(input, DataSourceId.JARVIS, JarvisPayload.class, JarvisDao.class);
    }

     */

}
