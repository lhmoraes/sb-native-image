/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.domain.dto.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import net.parlacom.iot.sigfox.domain.contant.DomainConstant.EMPTY

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-01-07
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(
    "device",
    "station",
    "duplicate",
    "snr",
    "avgSnr",
    "rssi",
    "seqNumber",
    "latitude",
    "longitude",
    "data",
    "time")
data class PayloadMessage(
    @JsonProperty("device") var uuid: String,
    @JsonProperty("time") var time: Int,
    @JsonProperty("seqNumber") var seqNumber: Int,
    @JsonProperty("data") var data: String = EMPTY,
    @JsonProperty("deviceTypeId") var deviceTypeId: String = EMPTY,
    @JsonProperty("duplicate") var duplicate: Boolean= false,
    @JsonProperty("snr") var snr: Float = 0f,
    @JsonProperty("station") var station: String = EMPTY,
    @JsonProperty("avgSnr") var avgSnr: Float = 0f,
    @JsonProperty("latitude") var latitude: Double = 0.0,
    @JsonProperty("longitude") var longitude: Double = 0.0,
    @JsonProperty("rssi") var rssi: Float = 0f,
    @JsonProperty("batt") var battery: Float = 0f,
    @JsonProperty("temp") var temperature: Float = 0f
)
