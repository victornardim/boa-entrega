package boa.entrega.registration.information.consumer

interface Consumer {
    val queue: String
    fun process(message: String)
}
