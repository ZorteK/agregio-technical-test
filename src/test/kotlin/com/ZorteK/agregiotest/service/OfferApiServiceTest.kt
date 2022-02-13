package com.ZorteK.agregiotest.service

import com.ZorteK.agregiotest.domain.*
import com.ZorteK.agregiotest.domain.Offer.OfferType.PRIMARY
import com.ZorteK.agregiotest.repository.OfferRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
internal class OfferApiServiceTest {

    @Mock
    lateinit var offerRepository: OfferRepository

    @Mock
    lateinit var parkService: ParcService

    @InjectMocks
    lateinit var offerService: OfferService

    @Nested
    inner class `list should` {
        @Test
        fun `return listed elements`() {
            //given
            val producedBloc = ProducedBloc(
                    name = "name",
                    energyType = EnergyType.ELECTRICITY,
                    quantity = BigDecimal.TEN,
                    timeSlot = TimeSlot.SLOT_12_15,
            )
            val offerBloc = OfferBloc(
                    timeslot = TimeSlot.SLOT_0_3,
                    floorPrice = BigDecimal(1025.15)
            )

            val expected = mapOf(PRIMARY to setOf(offerBloc))
            val offerBdd = mapOf(PRIMARY to setOf(offerBloc))
            val producedBlocs = setOf(producedBloc)

            //mock
            `when`(offerRepository.list()).thenReturn(offerBdd)
            `when`(parkService.list()).thenReturn(producedBlocs)

            //when
            val result = offerService.list()

            //then
            assertEquals(expected, result)
            verify(offerRepository).list()
            verify(parkService).list()
        }
    }
    @Nested
    inner class `store should` {
        @Test
        fun ` store offer`() {
            //given
            val offer = Offer(
                    type = PRIMARY,
                    offerBlocs = setOf(OfferBloc(
                            timeslot = TimeSlot.SLOT_0_3,
                            floorPrice = BigDecimal(1025.15)
                    ))
            )

            //mock

            //when
            offerService.store(offer)

            //then
            verify(offerRepository).store(offer)
        }

    }
}
