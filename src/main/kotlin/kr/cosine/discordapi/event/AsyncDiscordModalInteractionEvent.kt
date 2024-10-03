package kr.cosine.discordapi.event

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.interactions.modals.ModalMapping
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class AsyncDiscordModalInteractionEvent(
    private val event: ModalInteractionEvent,
    val member: Member
) : Event(true) {

    companion object {
        private val handlers = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }

    val modalId get() = event.modalId

    fun findInput(id: String): ModalMapping? {
        return event.getValue(id)
    }

    fun reply(message: String) {
        event.reply(message).setEphemeral(true).queue()
    }

    override fun getHandlers(): HandlerList {
        return getHandlerList()
    }
}