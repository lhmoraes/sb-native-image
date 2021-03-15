package net.parlacom.iot.sigfox.service.device.bo

import net.parlacom.common.library.datetime.DateTimeLib.getLocalDateTime
import net.parlacom.common.library.datetime.DateTimeLib.getMillsFromDate
import net.parlacom.common.library.datetime.DateTimeLib.parseDateTime
import net.parlacom.common.library.number.NumberLib
import net.parlacom.iot.sigfox.domain.model.account.RadiusAccount
import net.parlacom.iot.sigfox.domain.model.device.DeviceMessage
import net.parlacom.iot.sigfox.repository.account.RadiusAccountRepository
import net.parlacom.iot.sigfox.repository.config.RepositoryConstant
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-01-07
 */
@Service
class RadiusAccountBO(val radiusAccountRepository: RadiusAccountRepository) {

    /**
     * TODO
     *
     * @param deviceMessage
     */
    suspend fun saveRadiusAccount(deviceMessage: DeviceMessage): Unit {
        val radiusAccount: RadiusAccount = RadiusAccount()
        val sessionId: String = deviceMessage.uuid
        val uniqueId: String = getKey() + getMillsFromDate(Date())
        val eventDateTime = getLocalDateTime(parseDateTime(deviceMessage.time))

        radiusAccount.acctSessionId = sessionId
        radiusAccount.acctUniqueId = uniqueId
        radiusAccount.acctStartTime = eventDateTime
        radiusAccount.acctStopTime = eventDateTime
        radiusAccount.acctSessionTime = 0
        radiusAccount.acctInputOctets = 0
        radiusAccount.acctOutputOctets = 1
        radiusAccount.callingStationId = sessionId
        radiusAccount.imsi = deviceMessage.uuid

        radiusAccountRepository.save(radiusAccount)
    }

    /**
     *
     * @return
     */
    private fun getKey(): String {
        return NumberLib.randomNumber(1000, 9999).toString()
    }
}