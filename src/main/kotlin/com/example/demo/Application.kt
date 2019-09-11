package com.example.demo

import com.example.demo.examples.Example
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application : CommandLineRunner {

    @Autowired
    private lateinit var example: Example

    override fun run(vararg args: String?) {
        example.run()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
