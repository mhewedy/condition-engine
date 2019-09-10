package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Example {

    private final ScriptEngine scriptEngine;
    private final ConditionRepository conditionRepository;
    private final ConditionService conditionService;

    public Example(ScriptEngine scriptEngine, ConditionRepository conditionRepository, ConditionService conditionService) {
        this.scriptEngine = scriptEngine;
        this.conditionRepository = conditionRepository;
        this.conditionService = conditionService;
    }

    public void run() {
        conditionRepository.deleteAll();

        Map<String, String> vars = new HashMap<>();
        vars.put("year", "2019");
        conditionService.addNew("First Condition", "" +
                "abcService.doAbc()       // example on calling spring service \n" +
                "return (year == \"2019\")  // example on passing variables" +
                "", vars);

        System.out.println("** Executing conditions **** ");

        for (Condition condition : conditionService.listAll()) {
            boolean result = scriptEngine.execute(condition);
            System.out.println("executing: " + condition.getName() + ", result= " + result);
        }

    }
}
