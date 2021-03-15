package net.native.iot.device.repository.hello

import net.native.iot.device.domain.Hello
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface HelloRepository : CoroutineCrudRepository<Hello, Long> {

}
