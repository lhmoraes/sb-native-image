/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.controller.callback

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import net.parlacom.common.library.logger.AppLogger.Companion.info
import net.parlacom.iot.sigfox.config.constant.api.ApiConstant.API_BASE_URL
import net.parlacom.iot.sigfox.config.constant.api.ApiConstant.API_V1
import net.parlacom.iot.sigfox.config.constant.api.CallbackConstant.URI_CALLBACK_EVENTS
import net.parlacom.iot.sigfox.config.constant.api.MediaTypeConstant.APPLICATION_JSON
import net.parlacom.iot.sigfox.domain.dto.callback.PayloadMessage
import net.parlacom.iot.sigfox.service.device.DeviceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import net.parlacom.common.presenter.WebResponse
import net.parlacom.iot.sigfox.controller.callback.presenter.CallbackPresenter
import net.parlacom.iot.sigfox.domain.model.company.Company
import net.parlacom.iot.sigfox.domain.model.device.DeviceMessage

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2018-10-24
 */
@CrossOrigin
@RestController
@RequestMapping(API_V1 + API_BASE_URL)
class CallbackController(private val deviceService: DeviceService,
                         private val callbackPresenter: CallbackPresenter) {
    /**
     * Sigfox Official Payload:
     *
     * {
     *   "device":"{device}",
     *   "time":"{time}",
     *   "data":"{data}",
     *   "snr":"{snr}",
     *   "rssi":"{rssi}",
     *   "station":"{station}",
     *   "deviceTypeId":"{deviceTypeId}",
     *   "seqNumber":"{seqNumber}"
     * }
     *
     * @param company
     * @param payloadMessage
     * @return
     */
    @Operation(summary = "Sigfox Callback Message Events", tags = ["Sigfox Callback Events"], description = "Import Sigfox event message",
        responses = [
            ApiResponse(responseCode = "200", description = "Successful Request", content = [Content(mediaType = APPLICATION_JSON, schema = Schema(implementation = PayloadMessage::class))]),
            ApiResponse(responseCode = "500", description = "Internal Server Error", content = [Content(mediaType = APPLICATION_JSON, schema = Schema(implementation = WebResponse::class))]),
            ApiResponse(responseCode = "406", description = "Request was not acceptable due a business logic rule", content = [Content(mediaType = APPLICATION_JSON, schema = Schema(implementation = WebResponse::class))])])

    @RequestMapping(value = [URI_CALLBACK_EVENTS], method = [RequestMethod.POST], produces = [APPLICATION_JSON], consumes = [APPLICATION_JSON])
    suspend fun callbackEventMessage(
        @Parameter(required = true, name = "Company Identifier", description = "The company identifier") @PathVariable(required = false, name = "company") payloadCompany: String,
        @Parameter(required = true, name = "Event Payload", description = "The event message payload")   @RequestBody(required = false) payloadMessage: PayloadMessage): ResponseEntity<WebResponse>{

        // Payload validation
        callbackPresenter.validateCompany(payloadCompany)
        callbackPresenter.validatePayloadMessage(payloadMessage)

        // Get the domain model from the payload
        val company: Company = Company(payloadCompany)
        val deviceMessage: DeviceMessage = callbackPresenter.getDomainModel(payloadMessage)
        deviceMessage.company = company

        info("Device Message: $deviceMessage")

        // Save the radius account and device info from the payload data
        deviceService.saveDeviceAccount(deviceMessage);

        return callbackPresenter.response(payloadMessage)
    }
}


