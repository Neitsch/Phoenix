var express = require('express');
var app = express();
var amqp = require('amqplib').connect('amqp://localhost');

amqp.then(function(conn) {
  var ok = conn.createChannel();
  ok = ok.then(function(ch) {
    ch.assertQueue("testcaseid");
    app.use("/static", express.static(__dirname + '/../dist'));
    app.get('/en', function(req, res) {
      ch.sendToQueue("testcaseid", new Buffer('"' + req.query.id + '"'));
      res.end();
    });
    app.listen(3000, function() {
      console.log('Listening on 3000!');
    });
  });
  return ok;
});
