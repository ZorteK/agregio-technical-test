package com.ZorteK.agregiotest.repository

import com.ZorteK.agregiotest.domain.Offer
import com.ZorteK.agregiotest.domain.Offer.OfferType.SECONDARY
import com.ZorteK.agregiotest.domain.OfferBloc
import com.ZorteK.agregiotest.domain.TimeSlot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class OfferApiRepositoryTest {

    private val offerRepository = OfferRepository()

    @Nested
    inner class `Offer should ` {

        @Test
        fun ` return list of offers when offers are present`() {
            //given
            val offerBlocs = setOf(OfferBloc(
                    timeslot = TimeSlot.SLOT_0_3,
                    floorPrice = BigDecimal.TEN
            ))
            val toStore = Offer(
                    type = SECONDARY,
                    offerBlocs = offerBlocs
            )
            offerRepository.store(
                    toStore
            )
            val expected = mapOf(SECONDARY to offerBlocs)


            //when
            val result = offerRepository.list()

            //then
            assertEquals(result, expected)
        }
    }
}
