package it.luca.cedacri.controller;

import it.luca.cedacri.enumeration.DataSourceId;
import it.luca.cedacri.jdbc.dao.Bancll01Dao;
import it.luca.cedacri.jdbc.dao.Bancll34Dao;
import it.luca.cedacri.json.beans.Bancll01;
import it.luca.cedacri.json.beans.Bancll34;
import it.luca.cedacri.model.response.SourceResponse;
import it.luca.cedacri.service.CedacriService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/source")
public class CedacriSourceController {

    @Autowired
    private CedacriService service;

    @PostMapping(value = "/bancll01")
    public SourceResponse xMLToBancll01(@RequestBody String input) {
        return service.processAndSend(input, DataSourceId.BANCLL_01, Bancll01.class, Bancll01Dao.class);
    }

    @PostMapping(value = "/bancll34")
    public SourceResponse xMLToBancll34(@RequestBody String input) {
        return service.processAndSend(input, DataSourceId.BANCLL_34, Bancll34.class, Bancll34Dao.class);
    }
}
