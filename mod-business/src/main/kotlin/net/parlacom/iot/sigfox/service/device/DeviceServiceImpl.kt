/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.service.device

import net.parlacom.iot.sigfox.domain.model.device.DeviceMessage
import net.parlacom.iot.sigfox.service.device.bo.DeviceMessageBO
import net.parlacom.iot.sigfox.service.device.bo.LoggerMessageBO
import net.parlacom.iot.sigfox.service.device.bo.RadiusAccountBO
import org.springframework.stereotype.Service

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-01-07
 */
@Service
class DeviceServiceImpl(private val deviceMessageBO: DeviceMessageBO,
                        private val radiusAccountBO: RadiusAccountBO,
                        private val loggerDeviceMessage: LoggerMessageBO) : DeviceService {

    /**
     * TODO
     *
     * @param deviceMessage
     */
    override suspend fun saveDeviceAccount(deviceMessage: DeviceMessage) {
        deviceMessageBO.updateDeviceData(deviceMessage)

        radiusAccountBO.saveRadiusAccount(deviceMessage)

        loggerDeviceMessage.log(deviceMessage)
    }

}

