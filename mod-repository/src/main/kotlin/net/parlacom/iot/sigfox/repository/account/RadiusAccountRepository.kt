/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.repository.account

import net.parlacom.iot.sigfox.domain.model.account.RadiusAccount
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-01-07
 */
interface RadiusAccountRepository : CoroutineCrudRepository<RadiusAccount, Long>
