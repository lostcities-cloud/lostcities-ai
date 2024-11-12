package org.example;

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.*
import io.dereknelson.lostcities.models.commands.CommandDto
import io.dereknelson.lostcities.models.matches.PlayerEvent
import io.dereknelson.lostcities.models.state.Card
import io.dereknelson.lostcities.models.state.PlayArea


val QUEUE_NAME: String = "ai-player-request-event"
val objectMapper: ObjectMapper = ObjectMapper()

@Throws(Exception::class)
fun main() {
    val factory = ConnectionFactory()

    factory.setHost("192.168.1.233")
    factory.port = 5672
    factory.username = "guest"
    factory.password = "guest"

    val connection = factory.newConnection()
    val channel: Channel = connection.createChannel()

    println(" [*] Waiting for messages. To exit press CTRL+C")

    val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
        val message = String(delivery.body, charset("UTF-8"))
        println(" [x] Received '$message'")
    }

    channel.basicConsume(QUEUE_NAME, true, deliverCallback) { consumerTag: String? ->

    }
}


class PlayerViewDto(
    val id: Long,
    val deckRemaining: Int,
    val player: String,
    val isPlayerTurn: Boolean,
    val hand: MutableList<Card>,
    val playAreas: Map<String, PlayArea>,
    val discard: PlayArea,
    val playerEvents: List<PlayerEvent>,
    var log: List<CommandDto> = listOf(),
)
