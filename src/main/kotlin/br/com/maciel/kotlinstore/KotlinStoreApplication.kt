package br.com.maciel.kotlinstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinStoreApplication

fun main(args: Array<String>) {
    runApplication<KotlinStoreApplication>(*args)
}
