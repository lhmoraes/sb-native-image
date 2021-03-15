package net.native.iot.device

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["net.native"], exclude = [R2dbcAutoConfiguration::class])
class NativeApplication

fun main(args: Array<String>) {
    runApplication<NativeApplication>(*args)
}
