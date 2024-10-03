package kr.cosine.discordapi

import kr.cosine.discordapi.service.BotService
import kr.hqservice.framework.bukkit.core.component.module.Module
import kr.hqservice.framework.bukkit.core.component.module.Setup
import kr.hqservice.framework.bukkit.core.component.module.Teardown

@Module
class HQDiscordAPIModule(
    private val botService: BotService
) {

    @Setup
    fun setup() {
        botService.start()
    }

    @Teardown
    fun teardown() {
        botService.stop()
    }
}