/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.service.device.bo

import net.parlacom.common.library.io.IOLib.Companion.createDirectory
import net.parlacom.common.library.io.IOLib.Companion.fileExists
import net.parlacom.common.library.io.IOLib.Companion.saveFile
import net.parlacom.common.library.logger.AppLogger.Companion.error
import net.parlacom.iot.sigfox.constant.LogConstant.FILE_SEPARATOR
import net.parlacom.iot.sigfox.constant.LogConstant.LOG
import net.parlacom.iot.sigfox.constant.LogConstant.NEW_LINE
import net.parlacom.iot.sigfox.domain.model.device.DeviceMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-03-07
 */
@Component
class LoggerMessageBO {

    @Value("\${sigfox.log.enabled:false}")
    private val isLogEnabled = false

    @Value("\${sigfox.log.directory}")
    private val logPath: String? = null

    /**
     * TODO
     *
     * @param deviceMessage
     */
    fun log(deviceMessage: DeviceMessage): Unit {
        try {
            if (isLogEnabled) {
                val archivePath: String = logPath + FILE_SEPARATOR + deviceMessage.company!!.name

                if (!fileExists(archivePath)) {
                    createDirectory(archivePath)
                }
                val fileName = archivePath + FILE_SEPARATOR + deviceMessage.uuid + LOG
                val content = deviceMessage.toString() + NEW_LINE

                saveFile(content, fileName)
            }
        } catch (ex: Exception) {
            error(ex.message, ex)
        }
    }

}