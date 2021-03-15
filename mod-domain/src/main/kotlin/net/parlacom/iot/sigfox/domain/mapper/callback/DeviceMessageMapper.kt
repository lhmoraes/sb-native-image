/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.domain.mapper.callback

import net.parlacom.iot.sigfox.domain.dto.callback.PayloadMessage
import net.parlacom.iot.sigfox.domain.model.device.DeviceMessage
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy

/**
 * Class description
 *
 * @author Luiz Moraes
 * @version 1.0, 2021-01-07
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface DeviceMessageMapper {

    /**
     *
     * @param person
     * @return
     */
    @Mappings(
        Mapping(source = ".", target = "."),
    )
    fun convertToDto(person: DeviceMessage): PayloadMessage

    /**
     *
     * @param filterDto
     * @return
     */
    @Mappings(
        Mapping(source = ".", target = "."),
    )
    @InheritInverseConfiguration
    fun convertToModel(filterDto: PayloadMessage): DeviceMessage
}