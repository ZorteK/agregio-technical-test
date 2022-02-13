package com.ZorteK.agregiotest.api

import com.ZorteK.agregiotest.domain.TimeSlot
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class OfferBlocApi(
    @field:JsonProperty("timeslot") @field:NotNull val timeslot: TimeSlot,
    @field:JsonProperty("floorPrice") @field:NotNull @field:Positive val floorPrice: BigDecimal,
)
