package com.redactedopedia.factquery.endpoint;

import com.redactedopedia.factquery.api.FactQuery;
import com.redactedopedia.factquery.api.FactQueryResult;
import com.redactedopedia.factquery.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fact")
public class FactQueryController {

    @Autowired
    private QueryService service;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public FactQueryResult doQuery(@RequestBody FactQuery query) {
        return service.execute(query);
    }


}
