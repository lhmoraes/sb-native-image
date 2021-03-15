package net.parlacom.iot.sigfox.domain.model.device

import net.parlacom.iot.sigfox.domain.contant.DomainConstant.EMPTY
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-01-07
 */
@Table("cc_card")
data class Device(

    @Id
    @Column("id")
    var id: Long? = null,

    @Column("username")
    var uuid: String?,

    @Column("inuse")
    var inuse: Int? = 0,

    @Column("credit")
    var credit: Double? = 0.0,

    @Column("initialbalance")
    var initialBalance: Double? = 0.0,

    @Column("grandtotal")
    var grandTotal: Double? = 0.0,

    @Column("iccid_card")
    var iccid: String?,

    @Column("apn")
    var apn: String?,

    @Column("nas_ip")
    var nasIp: String? = EMPTY,

    @Column("imei")
    var imei: String? = EMPTY,

    @Column("imsi")
    var imsi: String? = EMPTY,

    @Column("mcc")
    var mcc: String? = EMPTY,

    @Column("mnc")
    var mnc: String? = EMPTY,

    @Column("rat_type")
    var ratType: String? = EMPTY,

    @Column("upload")
    var upload: Int? = 0,

    @Column("download")
    var download: Int? = 0,

    @Column("last_upload")
    var lastUpload: Int? = 0,

    @Column("last_download")
    var lastDownload: Int? = 0,

    @Column("lat")
    var latitude: Double? = 0.0,

    @Column("`long`")
    var longitute: Double? = 0.0,

    @Column("lastuse")
    var lastUse: LocalDateTime?,

    @Column("eventtime")
    var eventTime: LocalDate?
)