var express = require('express');
var app = express();

var stompit = require('stompit');

var connectOptions = {
  'host': 'localhost',
  'port': 61613,
  'connectHeaders':{
    'host': '0.0.0.0',
    'login': 'admin',
    'passcode': 'admin'
  }
};

stompit.connect(connectOptions, function(error, client) {

  if (error) {
    console.log('connect error ' + error.message + error.isTransportError() + error.isProtocolError() + error.isApplicationError());
    return;
  }
  else {
    console.log('Connected');
  }
});

app.use("/static", express.static(__dirname+'/../dist'));

app.get('/en', function (req, res) {
  res.end();
});

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});
