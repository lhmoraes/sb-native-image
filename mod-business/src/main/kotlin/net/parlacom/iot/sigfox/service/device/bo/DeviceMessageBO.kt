/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.service.device.bo

import net.parlacom.common.library.datetime.DateTimeLib.dateNow
import net.parlacom.common.library.datetime.DateTimeLib.dateTimeNow
import net.parlacom.common.library.string.StringLib.isEmpty
import net.parlacom.iot.sigfox.domain.model.device.Device
import net.parlacom.iot.sigfox.domain.model.device.DeviceMessage
import net.parlacom.iot.sigfox.repository.config.RepositoryConstant
import net.parlacom.iot.sigfox.repository.device.DeviceRepository
import net.parlacom.iot.sigfox.service.exception.DeviceNotFoundException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-01-07
 */
@Service
class DeviceMessageBO(val deviceRepository: DeviceRepository) {

    /**
     * TODO
     *
     * @param deviceMessage
     */
    suspend fun updateDeviceData(deviceMessage: DeviceMessage): Unit {
        val device: Device = deviceRepository.findByDeviceId(deviceMessage.uuid) ?: throw DeviceNotFoundException("Device not found for UUID: $deviceMessage.uuid")

        if (isEmpty(device.upload)) device.upload = 0 else device.upload = device.upload!! + 1
        if (isEmpty(device.credit)) device.credit = 0.0 else device.credit = device.credit!! - 1
        if (isEmpty(device.lastUpload)) device.lastUpload = 0 else device.lastUpload = device.lastUpload!! + 1
        if (isEmpty(device.initialBalance)) device.initialBalance = 0.0 else device.initialBalance = device.initialBalance!! + 1
        if (!isEmpty(deviceMessage.longitude)) device.longitute = deviceMessage.longitude
        if (!isEmpty(deviceMessage.latitude)) device.latitude = deviceMessage.latitude

        device.lastUse = dateTimeNow()
        device.eventTime = dateNow()

        deviceRepository.save(device)
    }
}

