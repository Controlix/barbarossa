import io.vertx.groovy.ext.web.Router

def router = Router.router(vertx)

router.route("/vertx/rest/echo/:msg").handler({ routingContext ->
  def req = routingContext.request()
  def msg = req.getParam("msg")

  def resp = routingContext.response()
  resp.putHeader("content-type", "text/plain")
  resp.end(msg)
})

vertx.createHttpServer().requestHandler(router.&accept).listen(8091, "ip6-localhost") {
  println 'Server running ...'
}
