package com.ZorteK.agregiotest.service

import com.ZorteK.agregiotest.domain.Offer
import com.ZorteK.agregiotest.domain.Offer.OfferType
import com.ZorteK.agregiotest.domain.OfferBloc
import com.ZorteK.agregiotest.domain.ProducedBloc
import com.ZorteK.agregiotest.domain.TimeSlot
import com.ZorteK.agregiotest.repository.OfferRepository
import org.springframework.stereotype.Service

@Service
class OfferService(
        private val offerRepository: OfferRepository,
        private val parkService: ParcService,
) {

    fun list(): Map<OfferType, Set<OfferBloc>> {
        val offer = offerRepository.list()
        val producedBlocByTimeslot = parkService.list().groupBy { it.timeSlot }
        offer.values.forEach { addProducedBloc(it, producedBlocByTimeslot) }
        return offer
    }

    private fun addProducedBloc(offerBlocs: Set<OfferBloc>, producedBlocByTimeslot: Map<TimeSlot, List<ProducedBloc>>) {
        offerBlocs.forEach { addProducedBloc(it, producedBlocByTimeslot) }
    }

    private fun addProducedBloc(oneOfferBloc: OfferBloc, producedBlocByTimeslot: Map<TimeSlot, List<ProducedBloc>>) {
        val timeslot = oneOfferBloc.timeslot
        oneOfferBloc.availableBlocs = producedBlocByTimeslot[timeslot]?.toSet()?: setOf()
    }

    fun store(offer: Offer) = offerRepository.store(offer)

}
