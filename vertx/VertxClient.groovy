def echoed = 0
def run = true

def options = [
  defaultHost: "ip6-localhost",
  defaultPort: 8091
]

def client = vertx.createHttpClient(options)
def send

send = {
  client.getNow("/vertx/rest/echo/hello") { response ->
    echoed++
    if (run) send()
    else println echoed
  }
}

vertx.setTimer(2000) {
  run = false
}

send()

