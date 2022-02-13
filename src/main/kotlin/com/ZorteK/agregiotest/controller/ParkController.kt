package com.ZorteK.agregiotest.controller

import com.ZorteK.agregiotest.api.ParkApi
import com.ZorteK.agregiotest.api.StoreReply
import com.ZorteK.agregiotest.api.StoreReply.StoreCodeReply.OK
import com.ZorteK.agregiotest.domain.ProducedBloc
import com.ZorteK.agregiotest.service.ParcService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/park")
class ParkController(private val parcService: ParcService) {

    @PutMapping("/park")
    fun store(@RequestBody @Valid park: ParkApi): ResponseEntity<StoreReply> {
        val producedBlocs = park.producedBlocs.map { ProducedBloc(park.name, park.energyType, it.value, it.key) }.toSet()
        parcService.store(producedBlocs)
        return ResponseEntity.ok(StoreReply(OK))
    }
}
