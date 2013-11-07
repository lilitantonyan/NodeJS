var http = require('http'),
    express = require('express'),
    candy_module = require('./models/candy'),
    mysql = require('mysql');

var app = express();

var connection = mysql.createConnection({
    host : 'localhost',
    user : 'root',
    password : 'lilit',
    database : 'test'
});
    //var candy_array = new Array();

app.get('/hello', function(req, res, next){
    connection.connect();

    connection.query('SELECT * FROM candy', function(err, rows, fields){
        if(err) throw err;
        var candy_array = new Array();
        var candy = new candy_module.candy();
        for(var j = 0; j < rows.length; ++j){
            candy.set_id(rows[j].ID);
            candy.set_candyname(rows[j].CandyName);
            candy.set_candybrand(rows[j].CandyBrand);
            candy.set_candytype(rows[j].CandyType);
            candy.set_candyprice(rows[j].CandyPrice);
            candy_array.push(candy);
        }
        res.send(candy_array);
    });
        connection.end();
 });

    app.get('/add', function(req, res, next) {
        connection.connect();

        var name = "fantazia";
        var brand = "grand candy";
        var type = "asorti";
        var price = 2000;
        var post={
            CandyName: name,
            CandyBrand: brand,
            CandyType: type,
            CandyPrice: price
        };

        connection.query('Insert into candy SET ?',post,function(err, result) {
            if (err) {
                res.send(err);
            } else {
                res.send(result);
            }
        });
        connection.end();
    });





app.get('/test', function(req, res, next){
    res.send('<h1>hellooooooo</h1>');

});

http.createServer(app).listen(3001, function(){
    console.log('Server start');
});