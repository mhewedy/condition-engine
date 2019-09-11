package com.example.demo.examples;

import com.example.demo.engine.Condition;
import com.example.demo.engine.ConditionRepository;
import com.example.demo.engine.ConditionService;
import com.example.demo.engine.ScriptEngine;
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

        String script = "" +
                "testService.doCallTheService()            // example on calling spring service \n" +
                "return (year == \"2019\")                 // example on using defined variables\n";

        conditionService.addNew("First Condition", script, vars, "my first condition");

        System.out.println("** Executing conditions **** ");

        for (Condition condition : conditionService.listAll()) {
            System.out.println("executing: " + condition.getName() + ",with script:  " + condition.getScript());
            boolean result = scriptEngine.execute(condition);
            System.out.println("result: " + result);
        }
    }
}
