# Diskonsole

### Languages: **English** / [日本語](https://github.com/sera1mu/Diskonsole/blob/main/README-ja.md)

[![Build](https://github.com/sera1mu/Diskonsole/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/sera1mu/Diskonsole/actions/workflows/build.yml) [![Lint](https://github.com/sera1mu/Diskonsole/actions/workflows/lint.yml/badge.svg?branch=main)](https://github.com/sera1mu/Diskonsole/actions/workflows/lint.yml)

Tested version: 1.18

Diskonsole is a Spigot/Paper plugin that allows you to send console logs to a Discord channel and execute commands from that channel.

## Installation

1. Download Diskonsole jar file from [Releases](https://github.com/sera1mu/Diskonsole/releases).
2. Stop the server.
3. Place the file to `plugins` directory.
4. Start the server and stop the server.
5. [Configure](#Configuration)
6. Start the server.

GLHF 🎉

## Configuration

The configuration file would be generated in `plugins/Diskonsole/config.yml`.

| NAME                 | TYPE      | DESCRIPTION                                                                                                                                                                               |
|----------------------|-----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| token                | `String`  | Bot token to be used                                                                                                                                                                      |
| consoleChannelId     | `Long`    | ID of the channel to which console logs are sent                                                                                                                                          |
| allowDispatchCommand | `Boolean` | Allow or disallow execution of commands from the console channel                                                                                                                          |
| chatSettings         | `Map`     | Settings for messages to be sent.  See https://github.com/Scarsz/JDAAppender/blob/794d25d5179e0092927fdf22fd7c194da8966a77/src/main/java/me/scarsz/jdaappender/ChannelLoggingHandler.java |

Default config:
```yaml
# Bot token to be used
# 使用するBotのトークン
token: TOKEN HERE

# ID of the channel to which console logs are sent
# コンソールのログが送信されるチャンネルのID
consoleChannelId: 000000000000000000

# Allow or disallow execution of commands from the console channel
# コンソールチャンネルからのコマンド実行を許可するか否か
allowDispatchCommand: true

# Settings for messages to be sent
# 送信されるメッセージの設定
chatSettings:
  # TODO(@sera1mu): mapLoggerName
  # See https://github.com/Scarsz/JDAAppender/blob/794d25d5179e0092927fdf22fd7c194da8966a77/src/main/java/me/scarsz/jdaappender/ChannelLoggingHandler.java
  isUseCodeBlocks: true
  isSplitCodeBlockForLinks: false
  isAllowLinkEmbeds: true
  isColored: true
  isTruncateLongItems: true

```

## Build

Just run `build` task with Gradle. If an error occurs related to spotless, please execute the `spotlessApply` task.

## License

MIT (c) 2022 Seraimu.
