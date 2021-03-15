package net.native.iot.device.service.device

import net.native.iot.device.domain.Hello
import org.springframework.stereotype.Service

@Service
class HelloServiceImpl : HelloService {

    override suspend fun hello(): Hello {
        return Hello("Spring Native")
    }

}

