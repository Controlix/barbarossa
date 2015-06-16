var http = require('http');
var dispatcher = require('httpdispatcher');

http.createServer(function (req, res) {
	dispatcher.dispatch(req, res);
}).listen(8090, function() {
	console.log('Server running ...');
});

dispatcher.onGet("/webclient/rest/echo/hello", function(req,res) {
	res.writeHead(200, {'Content-Type': 'text/plain'});
	res.end('hello');
});

