var express = require('express');
var app = express();


app.get("/node/rest/echo/:msg", function(req,res) {
	var msg = req.params.msg;
	res.writeHead(200, {'Content-Type': 'text/plain'});
	res.end(msg);
});

app.listen(8090, function() {
	console.log('Server running ...');
});
