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

    /**
     * JDAAppender configurations
     *
     * See https://github.com/Scarsz/JDAAppender/blob/794d25d5179e0092927fdf22fd7c194da8966a77/src/main/java/me/scarsz/jdaappender/ChannelLoggingHandler.java
     */
    val CHAT_SETTINGS: Map<ChatSetting, Boolean> by lazy {
        mapOf(
            ChatSetting.IS_USE_CODE_BLOCKS to getBoolean("chatSettings.isUseCodeBlocks"),
            ChatSetting.IS_SPLIT_CODE_BLOCK_FOR_LINKS to getBoolean("chatSettings.isSplitCodeBlockForLinks"),
            ChatSetting.IS_ALLOW_LINK_EMBEDS to getBoolean("chatSettings.isAllowLinkEmbeds"),
            ChatSetting.IS_COLORED to getBoolean("chatSettings.isColored"),
            ChatSetting.IS_TRUNCATE_LONG_ITEMS to getBoolean("chatSettings.isTruncateLongItems")
        )
    }
}
