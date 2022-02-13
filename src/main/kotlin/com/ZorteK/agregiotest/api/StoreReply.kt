package com.ZorteK.agregiotest.api

data class StoreReply(val code : StoreCodeReply) {

    enum class StoreCodeReply {
        OK
    }
}
