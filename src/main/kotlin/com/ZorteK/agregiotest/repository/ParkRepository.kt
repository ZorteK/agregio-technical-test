package com.ZorteK.agregiotest.repository

import com.ZorteK.agregiotest.domain.ProducedBloc
import org.springframework.stereotype.Repository

@Repository
class ParkRepository {

    private val parks = mutableSetOf<ProducedBloc>()

    fun store(toStore: Set<ProducedBloc> ) {
        parks += toStore
    }

    fun list() = parks.toSet()
}

