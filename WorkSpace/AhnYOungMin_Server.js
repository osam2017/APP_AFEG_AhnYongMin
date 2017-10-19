//채팅을 위한 socket.io example

var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var mysql = require('mysql');

var connection = mysql.createConnection({
  host: 'localhost',
  query: {pool: true},
  user: 'root',
  password: 'root',
  database: 'OSAM'
});

app.get('/', function(req, res){
  res.sendFile(__dirname + '/index.html');
});

io.on('connection', function(socket){
  console.log('a user connected');

  socket.on('chat message', function(msg){
    console.log('message: ' + msg);
    io.emit('chat message', msg);
  });

  socket.on('disconnect', function(){
    console.log('user disconnected');
  });
});

http.listen(5030, function(){
  console.log('listening on *: 5030');
});

app.get('/event/', function(request, response){
  //event route로 GET 요청을 받을 때 쿼리 실행하고 response를 보냄
  var sql = 'SELECT * FROM event';
  connection.query(sql, function(err, rows, fiels) {
    // body...
    if(err)
    {
      //에러 상황
      response.sendStatus(400);
      console.log('event 400 sent');
      return;
    }
    if(rows.length == 0)
    {
      //아무것도 없을 때
      response.sendStatus(204);
      console.log('event 204 sent');
      return;
    }
    else
    {
      console.log('event good sent');
      response.status(201).send(rows);
      response.end();
    }
  });
});

app.get('/userinfo/', function(request, response){
  //event route로 GET 요청을 받을 때 쿼리 실행하고 response를 보냄
  var sql = 'SELECT * FROM userinfo';
  connection.query(sql, function(err, rows, fiels) {
    // body...
    if(err)
    {
      //에러 상황
      response.sendStatus(400);
      console.log('user 400 sent');
      return;
    }
    if(rows.length == 0)
    {
      //아무것도 없을 때
      response.sendStatus(204);
      console.log('user 204 sent');
      return;
    }
    else
    {
      console.log('user good sent');
      response.status(201).send(rows);
      response.end();
    }
  });
});