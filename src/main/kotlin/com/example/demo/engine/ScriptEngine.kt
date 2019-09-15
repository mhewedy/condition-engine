package com.example.demo.engine

import groovy.lang.Binding
import groovy.lang.GroovyShell
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScriptEngine(
        @Autowired val beanFactory: ListableBeanFactory) {

    private val beans by lazy {
        beanFactory.beanDefinitionNames.map { it to beanFactory.getBean(it) }
    }

    fun execute(condition: Condition) = execute(condition.script,
            condition.conditionVars?.map { it.name to it.value }?.toMap())

    fun execute(script: String, vars: Map<String, String>?): Boolean {

        val binding = bind()
        vars?.forEach {
            binding.setVariable(it.key, it.value)
        }

        val shell = GroovyShell(binding)
        val result = shell.evaluate(script)
        return result as Boolean
    }

    private fun bind(): Binding {
        val binding = Binding()
        beans.forEach { binding.setVariable(it.first, it.second) }
        return binding
    }
}