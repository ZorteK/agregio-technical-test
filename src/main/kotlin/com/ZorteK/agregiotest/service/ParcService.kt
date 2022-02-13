package com.ZorteK.agregiotest.service

import com.ZorteK.agregiotest.domain.ProducedBloc
import com.ZorteK.agregiotest.repository.ParkRepository
import org.springframework.stereotype.Service

@Service
class ParcService(private val parcRepository: ParkRepository) {
    fun store(park: Set<ProducedBloc>) {
        parcRepository.store(park)
    }

    fun list() = parcRepository.list()


}
