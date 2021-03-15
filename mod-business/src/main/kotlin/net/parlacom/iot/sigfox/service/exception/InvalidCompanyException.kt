/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.service.exception

import net.parlacom.common.exception.BusinessException
import net.parlacom.common.i18n.ApiMessageCode.MC1000

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2018-11-04
 */
class InvalidCompanyException : BusinessException {
    /**
     *
     */
    constructor() : super(MC1000)

    /**
     *
     */
    constructor(ex: Exception) : super(ex, MC1000)

    /**
     *
     */
    constructor(message: String) : super(message, MC1000)
}
