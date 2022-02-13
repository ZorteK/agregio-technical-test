package com.ZorteK.agregiotest.controller

import com.ZorteK.agregiotest.domain.EnergyType.ELECTRICITY
import com.ZorteK.agregiotest.domain.Offer
import com.ZorteK.agregiotest.domain.Offer.OfferType.PRIMARY
import com.ZorteK.agregiotest.domain.OfferBloc
import com.ZorteK.agregiotest.domain.ProducedBloc
import com.ZorteK.agregiotest.domain.TimeSlot.SLOT_6_9
import com.ZorteK.agregiotest.service.OfferService
import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.TypeRef
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.math.BigDecimal.valueOf


@SpringBootTest(classes = [DgsAutoConfiguration::class, OfferGraphQLController::class])
internal class OfferApiGraphQLControllerTest {

    @MockBean
    lateinit var offerService: OfferService


    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor


    @Test
    fun shows() {
        //given
        val offerBlocs = setOf(OfferBloc(
            timeslot = SLOT_6_9,
            floorPrice = valueOf(1.25),
            availableBlocs = setOf(ProducedBloc(
                name = "jj",
                energyType = ELECTRICITY,
                quantity = valueOf(125.01),
                timeSlot = SLOT_6_9
            ))
        ))
        val offers = mapOf(PRIMARY to offerBlocs)
        val expected = listOf(Offer(
            type = PRIMARY,
            offerBlocs = offerBlocs
        )
        )


        //mock
        `when`(offerService.list()).thenReturn(offers)
        val typeRef2: TypeRef<List<Offer>> = object : TypeRef<List<Offer>>(){}

        //when
        val content:List<Offer> =
        dgsQueryExecutor.executeAndExtractJsonPathAsObject(
                """query{
          offer{
            type
            offerBlocs{
              timeslot
              floorPrice
              availableBlocs{
                name
                energyType
                quantity
                timeSlot
              }
            }
          }
        }""","data.offer" ,typeRef2)

        //then
        assertEquals(expected, content)
        verify(offerService).list()
    }


}


