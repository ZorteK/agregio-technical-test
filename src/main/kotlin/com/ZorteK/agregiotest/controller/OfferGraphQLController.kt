package com.ZorteK.agregiotest.controller

import com.ZorteK.agregiotest.domain.Offer
import com.ZorteK.agregiotest.domain.OfferBloc
import com.ZorteK.agregiotest.service.OfferService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class OfferGraphQLController(private val offerService: OfferService) {

    @DgsQuery
    fun offer() : List<Offer> {
        val offers: Map<Offer.OfferType, Set<OfferBloc>> =  offerService.list()
        return  offers.entries.map { Offer(it.key, it.value) }
    }
}
