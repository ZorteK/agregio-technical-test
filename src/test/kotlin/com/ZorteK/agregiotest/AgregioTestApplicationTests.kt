package com.ZorteK.agregiotest

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class AgregioTestApplicationTests {

    private var testRestTemplate: RestTemplate = RestTemplateBuilder().build()

    @Test
    fun main_should_start_application() {

        // When
        val entity: ResponseEntity<String> = testRestTemplate.getForEntity("http://127.0.0.1:8080/actuator/health", String::class.java)

        // Then
        Assertions.assertThat(entity).isNotNull
        Assertions.assertThat(entity.body).isEqualTo("""{"status":"UP"}""")
    }


}
