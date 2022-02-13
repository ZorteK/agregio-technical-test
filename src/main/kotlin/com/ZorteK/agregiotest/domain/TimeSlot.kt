package com.ZorteK.agregiotest.domain

import java.time.LocalTime

enum class TimeSlot(private val start: LocalTime, private val end: LocalTime) {
    SLOT_0_3(LocalTime.of(0, 0), LocalTime.of(3, 0)),
    SLOT_3_6(LocalTime.of(3, 0), LocalTime.of(6, 0)),
    SLOT_6_9(LocalTime.of(6, 0), LocalTime.of(9, 0)),
    SLOT_9_12(LocalTime.of(9, 0), LocalTime.of(12, 0)),
    SLOT_12_15(LocalTime.of(12, 0), LocalTime.of(15, 0)),
    SLOT_15_18(LocalTime.of(15, 0), LocalTime.of(18, 0)),
    SLOT_18_21(LocalTime.of(18, 0), LocalTime.of(21, 0)),
    SLOT_21_24(LocalTime.of(21, 0), LocalTime.of(0, 0));
}
