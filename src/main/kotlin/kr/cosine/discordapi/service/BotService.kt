package kr.cosine.discordapi.service

import kr.cosine.discordapi.listener.BotListener
import kr.hqservice.framework.global.core.component.Service
import kr.hqservice.framework.yaml.config.HQYamlConfiguration
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction

@Service
class BotService(
    private val config: HQYamlConfiguration,
    private val botListener: BotListener
) {

    private lateinit var jda: JDA
    private lateinit var guild: Guild

    internal fun start() {
        val token = config.getString("bot.token")
        val activity = config.getString("bot.activity")
        val intents = GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS)
        val builder = JDABuilder.create(token, intents).apply {
            setStatus(OnlineStatus.ONLINE)
            setActivity(Activity.playing(activity))
        }
        jda = builder.build()
        jda.addEventListener(botListener)
        jda.awaitReady()
        val guildId = config.getLong("bot.guild-id")
        guild = jda.getGuildById(guildId)
            ?: throw NullPointerException("${guildId}의 아이디를 가진 디스코드 방을 찾지 못했습니다.")
    }

    internal fun stop() {
        jda.shutdownNow()
    }

    fun findTextChannelById(id: Long): TextChannel? {
        return guild.getTextChannelById(id)
    }

    fun findRoleById(id: Long): Role? {
        return guild.getRoleById(id)
    }

    fun findMemberById(id: Long): Member? {
        return guild.getMemberById(id)
    }

    fun modifyNickname(member: Member, nickname: String): AuditableRestAction<Void> {
        return guild.modifyNickname(member, nickname)
    }

    fun addRoleToMember(member: Member, role: Role): AuditableRestAction<Void> {
        return guild.addRoleToMember(member, role)
    }

    fun removeRoleToMember(member: Member, role: Role): AuditableRestAction<Void> {
        return guild.removeRoleFromMember(member, role)
    }

    fun deleteAllMessageFromTextChannel(textChannel: TextChannel) {
        val history = MessageHistory.getHistoryFromBeginning(textChannel).complete()
        for (message in history.retrievedHistory) {
            message.delete().queue()
        }
    }
}