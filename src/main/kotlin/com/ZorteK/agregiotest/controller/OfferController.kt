package com.ZorteK.agregiotest.controller

import com.ZorteK.agregiotest.api.OfferApi
import com.ZorteK.agregiotest.api.OfferBlocApi
import com.ZorteK.agregiotest.api.StoreReply
import com.ZorteK.agregiotest.api.StoreReply.StoreCodeReply.OK
import com.ZorteK.agregiotest.domain.Offer
import com.ZorteK.agregiotest.domain.Offer.OfferType
import com.ZorteK.agregiotest.domain.OfferBloc
import com.ZorteK.agregiotest.service.OfferService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
@RestController
@RequestMapping("/offer")
class OfferController(private val offerService: OfferService) {

    @PutMapping("/offer")
    fun store(@RequestBody @Valid offer:OfferApi): ResponseEntity<StoreReply> {
        offerService.store(convertToDomain(offer))
        return ok(StoreReply(OK))
    }


    @GetMapping("/offers")
    fun list(): ResponseEntity<Map<OfferType, Set<OfferBloc>>> {
        return ok(offerService.list())
    }


    //don't want a domain object with field "availableBlocs" in the store method, need a API dedicated object
    //todo create a convert class
    private fun convertToDomain(api: OfferApi): Offer =Offer(api.type, convertToDomain(api.offerBlocs))
    private fun convertToDomain(api:Set<OfferBlocApi>): Set<OfferBloc> = api.map { OfferBloc(it.timeslot, it.floorPrice) }.toSet()


}

