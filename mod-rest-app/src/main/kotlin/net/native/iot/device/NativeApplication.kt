package net.native.iot.device

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.nativex.hint.NativeHint
import org.springframework.nativex.hint.TypeHint

@SpringBootApplication(scanBasePackages = ["net.native"], exclude = [R2dbcAutoConfiguration::class])
@NativeHint(options = ["-H:+AddAllCharsets"])
@TypeHint(types = [HttpStatus::class])
class NativeApplication

fun main(args: Array<String>) {
    runApplication<NativeApplication>(*args)
}
