var kafka = require('kafka-node'),
    Producer = kafka.Producer,
    client = new kafka.Client(),
    producer = new Producer(client),
    payloads = [
        { topic: 'test', messages: 'hello world', partition: 0, attributes: 0 }
    ];

var MAX = 10000;
var sent = 0;

function send(payloads, cnt) {
    producer.send(payloads, function (err, data) {
        // console.log(data);
        if (cnt == MAX) {
		client.close(function() {
                    console.log('Done');
                });
        }
    });
};

producer.on('ready', function () {
    console.log('Ready to send...');
    
    for (var i=1; i <= MAX; i++) {
	send(payloads, i);
    }
});
 
producer.on('error', function (err) {
    console.log(err);
});
