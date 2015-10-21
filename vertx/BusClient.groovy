def eb = vertx.eventBus()

def echoed = 0
def run = true
def send

send = {
    eb.send("vertx.test", "hi") { ar ->
        if (ar.succeeded()) {
            echoed++
            if (run) send()
            else println echoed
        }
    }
}

vertx.setTimer(2000) {
    run = false
}

send()
