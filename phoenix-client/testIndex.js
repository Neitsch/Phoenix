var q = 'testcaseid';

var open = require('amqplib').connect('amqp://localhost');

// Publisher
open.then(function(conn) {
  var ok = conn.createChannel();
  ok = ok.then(function(ch) {
    ch.assertQueue(q);
    ch.sendToQueue(q, new Buffer('"56c35c13f922c0ba6451ff8f"'));
  });
  return ok;
})
