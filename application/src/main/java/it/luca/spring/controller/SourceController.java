package it.luca.spring.controller;

import it.luca.spring.data.model.webdisp.WebdispSpecification;
import it.luca.spring.jdbc.dao.dao.ConduzioneDao;
import it.luca.spring.jdbc.dao.dao.JarvisDao;
import it.luca.spring.model.json.conduzione.ConduzionePayload;
import it.luca.spring.model.json.jarvis.JarvisPayload;
import it.luca.spring.service.SenderService;
import it.luca.spring.model.response.SourceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/source")
public class SourceController {

    @Autowired
    private SenderService senderService;

    /**
     * POST method for datasource WEBDISP
     * @param input: request body
     * @return SourceResponse
     */

    @PostMapping("/webdisp")
    public SourceResponse conduzione(@RequestBody String input) {

        WebdispSpecification webdispSpecification = new WebdispSpecification();
        return senderService.send(input, webdispSpecification);
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
