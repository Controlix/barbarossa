def echoed = 0
def run = true

def client = vertx.createHttpClient().setPort(8090).setHost("localhost")
def send

send = {
  client.getNow("/vertx/rest/echo/hello") {
    echoed++
    if (run) send()
    else println echoed
  }
}

vertx.setTimer(10000) {
  run = false
}

send()

