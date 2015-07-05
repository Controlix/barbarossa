import org.vertx.groovy.core.http.RouteMatcher

def rm = new RouteMatcher()
rm.get("/vertx/rest/echo/:msg") { req ->

  // This handler gets called for each request that arrives on the server
  def response = req.response
  response.putHeader("content-type", "text/plain")

  // Write to the response and end it
  def msg = req.params.msg
  // println msg
  response.end(msg)
}

vertx.createHttpServer().requestHandler(rm.asClosure()).listen(8090) {
  println 'Server running ...'
}
