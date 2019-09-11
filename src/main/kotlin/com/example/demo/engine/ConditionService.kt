package com.example.demo.engine

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConditionService(
        @Autowired val conditionRepository: ConditionRepository) {

    fun addNew(name: String, script: String, desc: String) {
        addNew(name, script, null, null)
    }

    fun addNew(name: String, script: String, vars: Map<String, String>?, desc: String?) {
        conditionRepository.save(
                Condition(name, desc, script, vars?.map { ConditionVars(it.key, it.value) }?.toSet())
        )
    }

    @Transactional
    fun listAll(): List<Condition> {
        val list = conditionRepository.findAll()
        list.forEach { it.conditionVars?.size }
        return list
    }
}