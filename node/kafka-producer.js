var kafka = require('kafka-node'),
    cluster = require('cluster'),
    Producer = kafka.Producer,
    client = new kafka.Client(),
    
    payloads = [
        { topic: 'test', messages: 'hello world', partition: 0, attributes: 0 }
    ];

var WORKERS = 6;

if (cluster.isMaster) {
    for (var i = 0; i < WORKERS; i++) {
        var worker = cluster.fork();
    }
} else {
    console.log('I am worker #' + cluster.worker.id);
 
var MAX = 5000;
var sent = 0;
var producer = new Producer(client);

function send (payloads, cnt) {
    producer.send(payloads, function (err, data) {
        // console.log(data);
        if (cnt == MAX) {
		client.close(function() {
                    console.log('Done');
                    cluster.worker.disconnect();
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

}

var done = 0;
cluster.on('disconnect', function(worker) {
    console.log('The worker #' + worker.id + ' has disconnected');
    done++;
    if (done == WORKERS) {
        process.exit(0);
    }
});
