/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.repository.device

import net.parlacom.iot.sigfox.domain.model.device.Device
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-01-07
 */
interface DeviceRepository : CoroutineCrudRepository<Device, Long> {

    /**
     * TODO
     *
     * @param uuid
     * @return
     */
    @Query("SELECT dev.* FROM mya2billing8.cc_card dev WHERE dev.username = :uuid")
    suspend fun findByDeviceId(uuid: String): Device?
}
