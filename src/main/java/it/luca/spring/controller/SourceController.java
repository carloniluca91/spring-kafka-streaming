package it.luca.spring.controller;

import it.luca.spring.enumeration.DataSourceId;
import it.luca.spring.jdbc.dao.Bancll01Dao;
import it.luca.spring.json.bancll01.Bancll01Payload;
import it.luca.spring.service.IngestionService;
import it.luca.spring.service.SourceResponse;
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
    private IngestionService service;

    @PostMapping("/bancll01")
    public SourceResponse xMLToBancll01(@RequestBody String input) {
        return service.processAndSend(input, DataSourceId.BANCLL_01, Bancll01Payload.class, Bancll01Dao.class);
    }

    /*
    @PostMapping("/bancll34")
    public SourceResponse xMLToBancll34(@RequestBody String input) {
        return service.processAndSend(input, DataSourceId.BANCLL_34);
    }

     */
}
