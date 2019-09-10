package com.example.demo

import groovy.lang.Binding
import groovy.lang.GroovyShell
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScriptEngine(
        @Autowired val beanFactory: ListableBeanFactory) {

    fun execute(condition: Condition): Boolean {
        return execute(condition.script,
                condition.conditionVars?.map { it.name to it.value }?.toMap())
    }

    fun execute(script: String): Boolean {
        return execute(script, null)
    }

    fun execute(script: String, vars: Map<String, String>?): Boolean {

        val binding = bind()

        vars?.forEach {
            binding.setVariable(it.key, it.value)
        }

        val shell = GroovyShell(binding)
        val result = shell.evaluate(script)
        return result as Boolean
    }

    // TODO cache this binding to reuse across invocations
    private fun bind(): Binding {
        val binding = Binding()
        beanFactory
                .beanDefinitionNames
                .map { it to beanFactory.getBean(it) }
                .forEach { binding.setVariable(it.first, it.second) }
        return binding
    }
}