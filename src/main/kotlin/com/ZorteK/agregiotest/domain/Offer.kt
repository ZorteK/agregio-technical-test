package com.ZorteK.agregiotest.domain

import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class Offer (@field:NotNull val type:OfferType,
                  @field:NotEmpty  @field:Valid val offerBlocs: Set<@Valid OfferBloc>,
                  ){

    enum class OfferType {
        PRIMARY, SECONDARY, FAST
    }

}
