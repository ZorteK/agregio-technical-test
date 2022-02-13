package com.ZorteK.agregiotest.controller

import com.ZorteK.agregiotest.api.OfferApi
import com.ZorteK.agregiotest.api.OfferBlocApi
import com.ZorteK.agregiotest.api.StoreReply
import com.ZorteK.agregiotest.api.StoreReply.StoreCodeReply.OK
import com.ZorteK.agregiotest.domain.EnergyType.ELECTRICITY
import com.ZorteK.agregiotest.domain.Offer
import com.ZorteK.agregiotest.domain.Offer.OfferType.PRIMARY
import com.ZorteK.agregiotest.domain.OfferBloc
import com.ZorteK.agregiotest.domain.ProducedBloc
import com.ZorteK.agregiotest.domain.TimeSlot
import com.ZorteK.agregiotest.domain.TimeSlot.SLOT_12_15
import com.ZorteK.agregiotest.service.OfferService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.put
import java.math.BigDecimal
import java.math.BigDecimal.TEN

@SpringBootTest
@AutoConfigureMockMvc
internal class OfferApiControllerTest @Autowired
constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
) {
    @MockBean
    lateinit var offerService: OfferService

    @Nested
    inner class `Store should` {
        @Test
        fun ` store offer `() {
            //given
            val offerApi = OfferApi(
                type = PRIMARY,
                offerBlocs = setOf(OfferBlocApi(
                    timeslot = TimeSlot.SLOT_0_3,
                    floorPrice = BigDecimal(1025.15)))
            )
            val offer = Offer(
                type = PRIMARY,
                offerBlocs = setOf(OfferBloc(
                    timeslot = TimeSlot.SLOT_0_3,
                    floorPrice = BigDecimal(1025.15)))
            )
            val expectation = StoreReply(OK)


            //when
            mockMvc.put("/offer/offer") {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(offerApi)
                accept = APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
                content {
                    json(objectMapper.writeValueAsString(expectation))
                }
            }

            //then
            verify(offerService).store(offer)
        }


        @Test
        //TODO "Should refactor default error messages")
        fun `  refuse invalid requests `() {
            //given
            val offer = Offer(
                type = PRIMARY,
                offerBlocs = setOf(OfferBloc(
                    timeslot = TimeSlot.SLOT_0_3,
                    floorPrice = BigDecimal(-1025.15)))
            )


            //when
            mockMvc.put("/offer/offer") {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(offer)
                accept = APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }

            //then
        }
    }


    @Nested
    inner class `List should` {
        @Test
        fun ` list offer `() {
            //given
            val offerBloc = OfferBloc(
                timeslot = TimeSlot.SLOT_0_3,
                floorPrice = BigDecimal(1025.15),
                availableBlocs = setOf(
                    ProducedBloc(
                        name = "name",
                        energyType = ELECTRICITY,
                        quantity = TEN,
                        timeSlot = SLOT_12_15,
                    )
                )
            )
            val expected = mapOf(PRIMARY to setOf(offerBloc))
            Mockito.`when`(offerService.list()).thenReturn(expected)

            //when
            mockMvc.get("/offer/offers") {
                contentType = APPLICATION_JSON
                accept = APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
                content {
                    json(objectMapper.writeValueAsString(expected))
                }
            }

            //then
            verify(offerService).list()
        }
    }
}
