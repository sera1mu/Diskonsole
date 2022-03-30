package me.seraimu.diskonsole

import me.seraimu.diskonsole.config.Config
import me.seraimu.diskonsole.config.Configuration
import me.seraimu.diskonsole.jda.BotManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Diskonsole : JavaPlugin() {
    companion object {
        lateinit var PLUGIN: Diskonsole
            private set
    }

    private var botManager: BotManager? = null

    override fun onEnable() {
        PLUGIN = this

        // Init config
        loadConfiguration(Config)

        // Init BotManager
        runCatching {
            BotManager(Config.TOKEN, Config.CONSOLE_CHANNEL_ID, Config.ALLOW_DISPATCH_COMMAND)
        }.onSuccess {
            botManager = it
        }.onFailure {
            logger.severe("Failed to init BotManager: ${it.stackTraceToString()}")

            Bukkit.getScheduler().runTask(
                this,
                Runnable {
                    Bukkit.getPluginManager().disablePlugin(this)
                }
            )

            return
        }

        logger.info("Enabled Diskonsole plugin.")
    }

    override fun onDisable() {
        botManager?.shutdown()

        logger.info("Disabled Diskonsole plugin.")
    }

    private fun loadConfiguration(vararg configurations: Configuration) = configurations.forEach { it.init(this) }
}
