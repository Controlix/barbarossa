var kafka = require('kafka-node'),
    Consumer = kafka.Consumer,
    client = new kafka.Client(),
    payload = [
            { topic: 'test', partition: 0 }
    ]
    consumer = new Consumer(client, payload);

consumer.on('message', function (message) {
    console.log(message);
});

consumer.on('error', function (err) {});
