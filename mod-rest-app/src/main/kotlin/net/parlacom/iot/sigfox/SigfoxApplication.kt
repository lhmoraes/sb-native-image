/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.runApplication

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-01-07
 */
@SpringBootApplication(scanBasePackages = ["net.parlacom"], exclude = [R2dbcAutoConfiguration::class])
class SigfoxApplication

/**
 * TODO
 *
 * @param args
 */
fun main(args: Array<String>) {
    runApplication<SigfoxApplication>(*args)
}
