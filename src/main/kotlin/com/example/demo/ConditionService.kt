package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConditionService(
        @Autowired val conditionRepository: ConditionRepository) {

    fun addNew(name: String, script: String) {
        addNew(name, script, null)
    }

    fun addNew(name: String, script: String, vars: Map<String, String>?) {
        conditionRepository.save(Condition(name, null, script,
                vars?.map { ConditionVars(it.key, it.value) }?.toSet()
        ))
    }

    @Transactional
    fun listAll(): List<Condition> {
        val list = conditionRepository.findAll()
        list.forEach { it.conditionVars?.size }
        return list;
    }

}