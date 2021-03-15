/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.domain.model.device

import net.parlacom.iot.sigfox.domain.model.company.Company

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-01-07
 */
data class DeviceMessage(
    var company: Company?,
    val uuid: String,
    val time: Int,
    val data: String,
    val seqNumber: Int,
    val duplicate: Boolean,
    val snr: Float,
    val station: String,
    var deviceTypeId: String,
    val avgSnr: Float,
    val latitude: Double,
    val longitude: Double,
    val rssi: Float,
    val battery: Float,
    val temperature: Float) {

    /**
     * TODO
     *
     * @return
     */
    override fun toString(): String {
        return "company=${company?.name} uuid=$uuid time=$time data=$data seqNumber=$seqNumber duplicate=$duplicate snr=$snr station=$station deviceTypeId=$deviceTypeId avgSnr=$avgSnr latitude=$latitude longitude=$longitude rssi=$rssi battery=$battery temperature=$temperature"
    }
}


