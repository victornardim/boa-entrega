package boa.entrega.registration.information.model.domain

import com.google.gson.Gson
import java.time.Instant

data class Event<T>(
    val body: T,
    val createdAt: String = Instant.now().toString()
) {
    fun toMessage(): ByteArray =
        Gson().toJson(this).toByteArray(Charsets.UTF_8)
}