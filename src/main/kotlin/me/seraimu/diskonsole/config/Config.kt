package me.seraimu.diskonsole.config

object Config : Configuration("config") {
    /**
     * Bot token to be used
     */
    val TOKEN: String by lazy { getString("token")!! }

    /**
     * ID of the channel to which console logs are sent
     */
    val CONSOLE_CHANNEL_ID: Long by lazy { getLong("consoleChannelId") }

    /**
     * Allow or disallow execution of commands from the console channel
     */
    val ALLOW_DISPATCH_COMMAND: Boolean by lazy { getBoolean("allowDispatchCommand") }
}
