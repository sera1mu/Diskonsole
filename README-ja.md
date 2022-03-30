# Diskonsole

### Languages: [English](https://github.com/sera1mu/Diskonsole/blob/main/README.md) / **日本語**

[![Build](https://github.com/sera1mu/Diskonsole/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/sera1mu/Diskonsole/actions/workflows/build.yml) [![Lint](https://github.com/sera1mu/Diskonsole/actions/workflows/lint.yml/badge.svg?branch=main)](https://github.com/sera1mu/Diskonsole/actions/workflows/lint.yml)

テストしたバージョン: 1.18

Diskonsoleは、コンソールのログをDiscordのチャンネルに送信したり、そのチャンネルからコマンドを実行することができるSpigot/Paperプラグインです。

## Installation

1. DiskonsoleのJarファイルを[Releases](https://github.com/sera1mu/Diskonsole/releases) からダウンロードする。
2. サーバーを停止する。
3. ファイルを`plugins`ディレクトリに設置する。
4. サーバーを起動して止める。
5. [設定する](#Configuration)。
6. サーバーを起動する。

GLHF 🎉

## Configuration

コンフィグファイルは `plugins/Diskonsole/config.yml` に生成されます。

| NAME                 | TYPE      | DESCRIPTION                                                                                                                                                                      |
|----------------------|-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| token                | `String`  | 使用するBotのトークン                                                                                                                                                                     |
| consoleChannelId     | `Long`    | コンソールのログを送信するチャンネル                                                                                                                                                               |
| allowDispatchCommand | `Boolean` | コンソールチャンネルからのコマンド実行を許可するか否か                                                                                                                                                      |
| chatSettings         | `Map`     | 送信されるメッセージの設定。  詳しくは https://github.com/Scarsz/JDAAppender/blob/794d25d5179e0092927fdf22fd7c194da8966a77/src/main/java/me/scarsz/jdaappender/ChannelLoggingHandler.java を御覧ください。 |

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

ただ `build` タスクをGradleで実行してください。Spotless関連でエラーが起きた際は、`spotlessApply`タスクを実行してください。

## License

MIT (c) 2022 Seraimu.
