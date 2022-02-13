package com.ZorteK.agregiotest.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class ProducedBloc(
        @field:JsonProperty("name") private val name:String,
        @field:JsonProperty("energyType") private val energyType: EnergyType,
        @field:JsonProperty("quantity") private val quantity:BigDecimal,
        @field:JsonProperty("timeSlot") val timeSlot : TimeSlot


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProducedBloc

        if (name != other.name) return false
        if (energyType != other.energyType) return false
        if (timeSlot != other.timeSlot) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + energyType.hashCode()
        result = 31 * result + timeSlot.hashCode()
        return result
    }
}
