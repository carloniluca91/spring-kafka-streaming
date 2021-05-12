package it.luca.spring.controller;

import it.luca.spring.jdbc.dao.ConduzioneDao;
import it.luca.spring.jdbc.dao.JarvisDao;
import it.luca.spring.model.json.conduzione.ConduzionePayload;
import it.luca.spring.model.json.jarvis.JarvisPayload;
import it.luca.spring.service.IngestionService;
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
    private IngestionService ingestionService;

    /**
     * POST method for datasource CONDUZIONE
     * @param input: request body
     * @return SourceResponse
     */

    @PostMapping("/conduzione")
    public SourceResponse conduzione(@RequestBody String input) {
        return ingestionService.save(input, DataSourceId.CONDUZIONE, ConduzionePayload.class, ConduzioneDao.class);
    }

    /**
     * POST method for datasource JARVIS
     * @param input: request body
     * @return SourceResponse
     */

    @PostMapping("/jarvis")
    public SourceResponse jarvis(@RequestBody String input) {
        return ingestionService.save(input, DataSourceId.JARVIS, JarvisPayload.class, JarvisDao.class);
    }
}
