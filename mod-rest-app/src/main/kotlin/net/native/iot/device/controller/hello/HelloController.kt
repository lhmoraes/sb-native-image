/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.native.iot.device.controller.hello

import net.native.iot.device.service.device.HelloService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class HelloController(private val deviceService: HelloService) {
    @RequestMapping(value = ["hello"])
    suspend fun callbackEventMessage(): String{

        // Save the radius account and device info from the payload data
        return deviceService.hello().toString()
    }
}


