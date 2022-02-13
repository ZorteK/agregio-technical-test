package com.ZorteK.agregiotest.api

import com.ZorteK.agregiotest.domain.EnergyType
import com.ZorteK.agregiotest.domain.TimeSlot
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class ParkApi(
    @field:NotBlank val name: String,
    @field:NotNull val energyType: EnergyType,
    @field:NotEmpty val producedBlocs: Map<TimeSlot, @Positive BigDecimal>,
)
