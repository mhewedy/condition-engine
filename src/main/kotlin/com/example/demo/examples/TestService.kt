package com.example.demo.examples

import org.springframework.stereotype.Service

@Service
class TestService {

    fun doCallTheService() {
        println("in the service TestService")
    }
}