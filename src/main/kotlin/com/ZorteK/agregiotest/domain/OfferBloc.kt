package com.ZorteK.agregiotest.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class OfferBloc(
        @field:JsonProperty("timeslot") @field:NotNull val timeslot: TimeSlot,
        @field:JsonProperty("floorPrice") @field:NotNull @field:Positive val floorPrice: BigDecimal,
        @field:JsonProperty("availableBlocs") var availableBlocs: Set<ProducedBloc>? = null
)
