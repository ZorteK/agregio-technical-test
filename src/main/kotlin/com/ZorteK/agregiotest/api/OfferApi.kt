package com.ZorteK.agregiotest.api

import com.ZorteK.agregiotest.domain.Offer.OfferType
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class OfferApi(
    @field:NotNull val type: OfferType,
    @field:NotEmpty @field:Valid val offerBlocs: Set<@Valid OfferBlocApi>,
) {


}
