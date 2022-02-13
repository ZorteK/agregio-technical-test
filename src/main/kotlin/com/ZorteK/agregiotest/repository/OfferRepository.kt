package com.ZorteK.agregiotest.repository

import com.ZorteK.agregiotest.domain.Offer
import com.ZorteK.agregiotest.domain.Offer.OfferType
import com.ZorteK.agregiotest.domain.OfferBloc
import org.springframework.stereotype.Repository

@Repository
class OfferRepository {
    private var offers = mutableMapOf<OfferType, Set<OfferBloc>>()

    fun list(): Map<OfferType, Set<OfferBloc>> {
        return offers
    }

    fun store(toStore: Offer) {
        offers[toStore.type] = toStore.offerBlocs
    }
}
