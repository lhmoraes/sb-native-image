/*
 * File: TimezoneConfig
 *
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 *
 */

package net.parlacom.iot.sigfox.config;

import net.parlacom.common.library.datetime.DateTimeLib.dateTimeNow
import net.parlacom.common.library.logger.AppLogger.Companion.info
import org.joda.time.DateTimeZone
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.util.*

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-02-27
 */
@Configuration
class TimezoneConfig {

    @Value("\${time-zone:UTC}")
    lateinit var timeZone: String

    /**
     *
     * @return
     */
    @Bean
    fun setTimeZone(): String {
        DateTimeZone.setDefault(DateTimeZone.forID(timeZone))
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone))

        info("Timezone: " + TimeZone.getDefault().toString())
        info("Current Date Time: " + dateTimeNow())

        return "TimeZone: " + TimeZone.getDefault().toString()
    }

    /**
     * TODO
     *
     * @return
     */
    @Bean
    fun jacksonObjectMapperCustomization(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer { jacksonObjectMapperBuilder: Jackson2ObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone(timeZone)) }
    }
}
