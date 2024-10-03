package kr.cosine.discordapi.listener

import kr.cosine.discordapi.event.AsyncDiscordButtonInteractionEvent
import kr.cosine.discordapi.event.AsyncDiscordGuildMemberRemoveEvent
import kr.cosine.discordapi.event.AsyncDiscordModalInteractionEvent
import kr.hqservice.framework.global.core.component.Bean
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.bukkit.plugin.PluginManager

@Bean
class BotListener(
    private val pluginManager: PluginManager
) : ListenerAdapter() {

    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        val member = event.member ?: return
        val asyncDiscordButtonInteractionEvent = AsyncDiscordButtonInteractionEvent(event, member)
        pluginManager.callEvent(asyncDiscordButtonInteractionEvent)
    }

    override fun onModalInteraction(event: ModalInteractionEvent) {
        val member = event.member ?: return
        val asyncDiscordModalInteractionEvent = AsyncDiscordModalInteractionEvent(event, member)
        pluginManager.callEvent(asyncDiscordModalInteractionEvent)
    }

    override fun onGuildMemberRemove(event: GuildMemberRemoveEvent) {
        val member = event.member ?: return
        val asyncDiscordGuildMemberRemoveEvent = AsyncDiscordGuildMemberRemoveEvent(event, member)
        pluginManager.callEvent(asyncDiscordGuildMemberRemoveEvent)
    }
}