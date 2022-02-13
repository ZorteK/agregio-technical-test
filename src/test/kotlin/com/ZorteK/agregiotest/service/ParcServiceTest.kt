package com.ZorteK.agregiotest.service

import com.ZorteK.agregiotest.domain.EnergyType
import com.ZorteK.agregiotest.domain.ProducedBloc
import com.ZorteK.agregiotest.domain.TimeSlot
import com.ZorteK.agregiotest.repository.ParkRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
internal class ParcServiceTest {

    @Mock
    lateinit var parkRepository: ParkRepository

    @InjectMocks
    lateinit var parcService: ParcService

    @Nested
    inner class `Store should`
    @Test
    fun `store produced blocs `() {
        //given
        val name = "name"
        val blocs =
                setOf(
                        ProducedBloc(name, EnergyType.WATER, BigDecimal.TEN, TimeSlot.SLOT_0_3),
                        ProducedBloc(name, EnergyType.WATER, BigDecimal(20), TimeSlot.SLOT_15_18)

                )

        //when
        parcService.store(blocs)

        //then
        verify(parkRepository).store(blocs)
    }

    @Test
    fun list() {
        //given
        val name = "name"
        val blocs =
                setOf(
                        ProducedBloc(name, EnergyType.WATER, BigDecimal.TEN, TimeSlot.SLOT_0_3),
                        ProducedBloc(name, EnergyType.WATER, BigDecimal(20), TimeSlot.SLOT_15_18)

                )
        `when`(parkRepository.list()).thenReturn(blocs)

        //when
        val result = parcService.list()

        //then
        assertEquals(result, blocs)
        verify(parkRepository).list()
    }
}
