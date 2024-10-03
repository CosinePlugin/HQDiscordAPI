package kr.cosine.discordapi.event

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class AsyncDiscordGuildMemberRemoveEvent(
    private val event: GuildMemberRemoveEvent,
    val member: Member
) : Event(true) {

    companion object {
        private val handlers = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }

    override fun getHandlers(): HandlerList {
        return getHandlerList()
    }
}