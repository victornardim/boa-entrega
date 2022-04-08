package boa.entrega.orders.consumer

interface Consumer {
    val queue: String
    fun process(message: String)
}
