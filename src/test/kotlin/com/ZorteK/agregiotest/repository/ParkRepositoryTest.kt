package com.ZorteK.agregiotest.repository


import com.ZorteK.agregiotest.domain.EnergyType.ELECTRICITY
import com.ZorteK.agregiotest.domain.ProducedBloc
import com.ZorteK.agregiotest.domain.TimeSlot.SLOT_12_15
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal.ONE

internal class ParkRepositoryTest {

    private val parkRepository = ParkRepository()

    @Nested
    inner class `store should ` {

        @Test
        fun ` return list of parks when parks are present`() {
            //given
            val toStore = ProducedBloc(
                    name = "name",
                    energyType = ELECTRICITY,
                    quantity = ONE,
                    timeSlot = SLOT_12_15
            )
            parkRepository.store(
                    setOf(toStore)
            )

            //when
            val result = parkRepository.list()

            //then
            assertEquals(result, setOf(toStore))
        }
    }
}
