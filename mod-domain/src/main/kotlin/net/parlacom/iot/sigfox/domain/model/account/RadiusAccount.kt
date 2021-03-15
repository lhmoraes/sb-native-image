/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.domain.model.account;

import net.parlacom.iot.sigfox.domain.contant.DomainConstant.EMPTY
import net.parlacom.iot.sigfox.domain.contant.DomainConstant.FRAMED_USER
import net.parlacom.iot.sigfox.domain.contant.DomainConstant.GPRS_PDP_CONTEXT
import net.parlacom.iot.sigfox.domain.contant.DomainConstant.LOCALHOST_IP
import net.parlacom.iot.sigfox.domain.contant.DomainConstant.RADIUS
import net.parlacom.iot.sigfox.domain.contant.DomainConstant.SIGFOX
import net.parlacom.iot.sigfox.domain.contant.DomainConstant.VIRTUAL
import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-01-07
 */
@Table("radacct")
class RadiusAccount {

    @Id
    @Column("radacctid")
    var radacctId: Long? = null

    @Column("acctsessionid")
    lateinit var acctSessionId: String

    @Column("acctuniqueid")
    lateinit var acctUniqueId: String

    @Column("username")
    val username: String = SIGFOX

    @Column("groupname")
    val groupName: String = EMPTY

    @Column("realm")
    val realm: String = EMPTY

    @Column("nasipaddress")
    val nasIpAddress: String = LOCALHOST_IP

    @Column("nasportid")
    val nasPortId: Int = 0

    @Column("nasporttype")
    val nasPortType: String = VIRTUAL

    @Column("acctstarttime")
    lateinit var acctStartTime: LocalDateTime

    @Column("acctstoptime")
    lateinit var acctStopTime: LocalDateTime

    @Column("acctsessiontime")
    var acctSessionTime: Int = 0

    @Column("acctauthentic")
    val acctAuthentic: String = RADIUS

    @Column("connectinfo_start")
    val connectInfoStart: String = EMPTY

    @Column("connectinfo_stop")
    val connectInfoStop: String = EMPTY

    @Column("acctinputoctets")
    var acctInputOctets: Long = 0

    @Column("acctoutputoctets")
    var acctOutputOctets: Long = 0

    @Column("calledstationid")
    lateinit var calledStationId: String

    @Column("callingstationid")
    lateinit var callingStationId: String

    @Column("imsi")
    lateinit var imsi: String

    @Column("acctterminatecause")
    val acctTerminateCause: String = EMPTY

    @Column("servicetype")
    val serviceType: String = FRAMED_USER

    @Column("framedprotocol")
    val framedProtocol: String = GPRS_PDP_CONTEXT

    @Column("framedipaddress")
    lateinit var framedIpAddress: String

    @Column("acctstartdelay")
    val acctStartDelay: Int = 0

    @Column("acctstopdelay")
    val acctStopDelay: Int = 0

    @Column("xascendsessionsvrkey")
    val xascendSessionSvrKey: String = EMPTY

    @Column("last_chk_octets")
    val lastChkOctets: Int = 0

    @Column("last_chk_datetime")
    var lastChkDatetime: LocalDateTime? = null

    @Column("last_chk_all_done")
    val lastChkAllDone: Int = 0

    @Column("dupe")
    val dupe: Int = 0

    @Column("datausage")
    var dataUsage: Double = 0.0

    @Column("sessionbill")
    var sessionBill: Double = 0.0

    @Column("terminatecauseid")
    val terminateCauseId: Int = 1

    @Column("card_id")
    var cardId: Int = 0

    @Column("imei")
    val imei: String = EMPTY

    @Column("cellid")
    val cellId: String = EMPTY
}

