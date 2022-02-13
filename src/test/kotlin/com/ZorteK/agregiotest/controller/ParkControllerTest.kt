package com.ZorteK.agregiotest.controller

import com.ZorteK.agregiotest.api.ParkApi
import com.ZorteK.agregiotest.api.StoreReply
import com.ZorteK.agregiotest.api.StoreReply.StoreCodeReply.OK
import com.ZorteK.agregiotest.domain.*
import com.ZorteK.agregiotest.domain.EnergyType.WATER
import com.ZorteK.agregiotest.domain.TimeSlot.*
import com.ZorteK.agregiotest.service.ParcService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.put
import java.math.BigDecimal
import java.math.BigDecimal.TEN

@SpringBootTest
@AutoConfigureMockMvc
internal class ParkControllerTest @Autowired
constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper,
) {
    @MockBean
    lateinit var parkService: ParcService

    @Nested
    inner class `Park should` {
        @Test
        fun ` store park `() {
            //given
            val name = "park name"
            val park = ParkApi(
                            name  = name,
                            energyType = WATER,
                            producedBlocs = mapOf(
                                    SLOT_0_3 to BigDecimal(10),
                                    SLOT_15_18 to BigDecimal(20)
                            ),
            )
            val expectation = StoreReply(OK)


            //when
            mockMvc.put("/park/park") {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(park)
                accept = APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
                content {
                    json(objectMapper.writeValueAsString(expectation))
                }
            }

            //then
            verify(parkService).store(setOf(
                    ProducedBloc(name, WATER, TEN,SLOT_0_3 ),
                    ProducedBloc(name, WATER, BigDecimal(20),SLOT_15_18 )
            ))
        }


        @Test
        //TODO dont work, fix it :(
        fun ` refuse invalid requests `() {
            //given
            val name = "park name"
            val park = ParkApi(
                    name  = name,
                    energyType = WATER,
                    producedBlocs = mapOf(
                            SLOT_0_3 to BigDecimal(-10),
                    )
            )

            //when
            mockMvc.put("/park/park") {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(park)
                accept = APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }

            //then
        }
    }

}
