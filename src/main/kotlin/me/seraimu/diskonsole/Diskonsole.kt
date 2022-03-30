package me.seraimu.diskonsole

import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Diskonsole : JavaPlugin() {
    override fun onEnable() {
        logger.info("Enabled Diskonsole plugin.")
    }

    override fun onDisable() {
        logger.info("Disabled Diskonsole plugin.")
    }
}