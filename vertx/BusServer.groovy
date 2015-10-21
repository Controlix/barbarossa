def eb = vertx.eventBus()

eb.consumer("vertx.test") { message ->
    message.reply("hi")
}
