/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.controller.callback.presenter

import net.parlacom.common.library.string.StringLib.isEmpty
import net.parlacom.common.presenter.Presenter
import net.parlacom.iot.sigfox.domain.dto.callback.PayloadMessage
import net.parlacom.iot.sigfox.domain.mapper.callback.DeviceMessageMapperImpl
import net.parlacom.iot.sigfox.domain.model.device.DeviceMessage
import net.parlacom.iot.sigfox.service.exception.InvalidCompanyException
import net.parlacom.iot.sigfox.service.exception.InvalidPayloadException
import org.springframework.stereotype.Component

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2018-10-24
 */
@Component
class CallbackPresenter : Presenter() {

    /**
     *
     * @param company
     * @return
     */
    @Throws(InvalidCompanyException::class)
    fun validateCompany(company: String): Boolean {
        if (isEmpty(company)) {
            throw InvalidCompanyException()
        }

        return true
    }

    /**
     *
     * @param payloadMessage
     * @return
     */
    @Throws(InvalidPayloadException::class)
    fun validatePayloadMessage(payloadMessage: PayloadMessage): Boolean {
        if (isEmpty(payloadMessage)) {
            throw InvalidPayloadException()
        }

        return true
    }

    /**
     *
     * @param payloadMessage
     * @return
     */
    fun getDomainModel(payloadMessage: PayloadMessage): DeviceMessage {
        return DeviceMessageMapperImpl().convertToModel(payloadMessage)
    }

}
