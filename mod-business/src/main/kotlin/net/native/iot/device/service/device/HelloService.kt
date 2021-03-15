package net.native.iot.device.service.device

import net.native.iot.device.domain.Hello

interface HelloService {

    suspend fun hello(): Hello

}