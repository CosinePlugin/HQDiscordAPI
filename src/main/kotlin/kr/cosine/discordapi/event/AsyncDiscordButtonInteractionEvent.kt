package kr.cosine.discordapi.event

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.interactions.modals.Modal
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class AsyncDiscordButtonInteractionEvent(
    private val event: ButtonInteractionEvent,
    val member: Member
) : Event(true) {

    companion object {
        private val handlers = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }

    val button get() = event.button

    fun replyModal(modal: Modal) {
        event.replyModal(modal).queue()
    }

    override fun getHandlers(): HandlerList {
        return getHandlerList()
    }
}