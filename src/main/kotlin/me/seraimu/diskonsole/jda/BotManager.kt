package me.seraimu.diskonsole.jda

import me.scarsz.jdaappender.ChannelLoggingHandler
import me.seraimu.diskonsole.Diskonsole
import me.seraimu.diskonsole.config.ChatSetting
import me.seraimu.diskonsole.config.Config
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.bukkit.Bukkit

/**
 * Manage JDA bot and channel logging handler
 */
class BotManager(private val token: String, private val consoleChannelId: Long, private val allowDispatchCommand: Boolean) : EventListener {
    private lateinit var bot: JDA
    private var loggingHandler: ChannelLoggingHandler? = null
    private val logger = Diskonsole.PLUGIN.logger

    init {
        // Login bot
        runCatching {
            this.bot = JDABuilder.createDefault(token)
                .addEventListeners(this)
                .setAutoReconnect(true)
                .build()
        }.onSuccess {
            logger.info("Login to the bot was successful.")
        }.onFailure { cause ->
            throw Throwable("Failed to login to bot.", cause)
        }

        logger.info("BotManager has been initialized.")
    }

    /**
     * Shutdown BotManager
     */
    fun shutdown() {
        this.loggingHandler?.shutdown()
        this.bot.shutdown()

        logger.info("BotManager shutdown is complete.")
    }

    override fun onEvent(event: GenericEvent) {
        if (event is ReadyEvent) {
            // Attach logging handler
            val consoleChannel = this.bot.getTextChannelById(consoleChannelId)

            if (consoleChannel == null) {
                Diskonsole.PLUGIN.disablePlugin()

                throw Throwable("Failed to get console channel from bot.")
            }

            val chatSettings = Config.CHAT_SETTINGS

            this.loggingHandler = ChannelLoggingHandler({
                consoleChannel
            }) {
                it.isUseCodeBlocks = chatSettings[ChatSetting.IS_USE_CODE_BLOCKS]!!
                it.isSplitCodeBlockForLinks = chatSettings[ChatSetting.IS_SPLIT_CODE_BLOCK_FOR_LINKS]!!
                it.isAllowLinkEmbeds = chatSettings[ChatSetting.IS_ALLOW_LINK_EMBEDS]!!
                it.isColored = chatSettings[ChatSetting.IS_COLORED]!!
                it.isTruncateLongItems = chatSettings[ChatSetting.IS_TRUNCATE_LONG_ITEMS]!!
            }.attachLog4jLogging().schedule()

            logger.info("Attached ChannelLoggingHandler.")
            logger.info("Ready!")
        }

        if (allowDispatchCommand && event is MessageReceivedEvent && !(event.author.isBot) && event.isFromGuild && event.textChannel.idLong == Config.CONSOLE_CHANNEL_ID) {
            val rawContent = event.message.contentRaw
            Bukkit.getScheduler().runTask(
                Diskonsole.PLUGIN,
                Runnable {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), rawContent)
                    logger.info("authorId=${event.author.id}: Executed command \"$rawContent\"")
                }
            )
        }
    }
}
