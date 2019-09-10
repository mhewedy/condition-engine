# condition-engine

Engine uses groovy language to exeucte rules conditions.

* The groovy script will have all spring beans already injected.
* user can pass as many variables as he wants.
* the script need to return true/false at the end.

Add Condition to the engine:

```java
@Autowired
private ConditionService conditionService;
...
...
Map<String, String> vars = new HashMap<>();
vars.put("year", "2019");
conditionService.addNew("First Condition", "" +
        "abcService.doAbc()       // example on calling spring service \n" +
        "return (year == \"2019\")  // example on passing variables" +
        "", vars);
```

To exeucte condition:

```java
@Autowired
private final ScriptEngine scriptEngine;
...
...
Condition condition = // ... get condition from database
boolean result = scriptEngine.execute(condition);
System.out.println("executing: " + condition.getName() + ", result= " + result);
```

For complete Example in java see [Example.java](https://github.com/mhewedy/condition-engine/blob/master/src/main/kotlin/com/example/demo/Example.java)
