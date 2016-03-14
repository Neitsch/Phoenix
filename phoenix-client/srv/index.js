var express = require('express');
var app = express();
var promiseRetry = require('promise-retry');

promiseRetry(function(retry, number) {
  console.log("Attempt no. " + number + " to connect to " + process.env.QUEUE_HOST);
  return require('amqplib').connect('amqp://'+process.env.QUEUE_HOST).catch(function(err) { console.log(err); retry(err);});
})
  .then(function(conn) {
    var ok = conn.createChannel();
    ok = ok.then(function(ch) {
      ch.assertQueue("testcaseid");
      app.use("/static", express.static(__dirname + '/../dist'));
      app.get('/en', function(req, res) {
        ch.sendToQueue("testcaseid", new Buffer('"'+req.query.id+'"'));
        res.end();
      });
      app.get('/en2', function(req, res) {
        ch.sendToQueue("testsuiteid", new Buffer('"'+req.query.id+'"'));
        res.end();
      });
      app.listen(3000, function() {
        console.log('Listening on 3000!');
      });
    });
    return ok;
  });
