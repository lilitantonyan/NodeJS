var http = require('http'),
    express = require('express'),
    candy_module = require('./models/candy'),
    mysql = require('mysql');

var app = express();
app.use(express.bodyParser());


var connection = mysql.createConnection({
    host : 'localhost',
    user : 'root',
    password : 'lilit',
    database : 'test'
});

app.post('/addCandy', function(request, result){
    console.log(request.body);
    var name = request.body.name;
    var brand = request.body.brand;
    var type = request.body.type;
    var price = request.body.price;

    var post={
        CandyName: name,
        CandyBrand: brand,
        CandyType: type,
        CandyPrice: price
    };

    connection.query('Insert into candy SET ?',post,function(post_err, post_result) {
        if (post_err) {
            result.send(post_err);
        } else {
            result.send(post_result);
        }
    });
    connection.end();

    result.set("OK");
});


app.get('/getcandy', function(req, res, next){
    connection.connect();

    connection.query('SELECT * FROM candy', function(err, rows, fields){
        if(err) throw err;
        var candy_array = new Array();

        for(var j = 0; j < rows.length; ++j){
            var candy = new candy_module.candy();
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

    app.get('/candy', function(req, res, next) {
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

        connection.query('Insert into candy SET ?',post,function(sql_err, sql_result) {
            if (sql_err) {
                res.send(sql_err);
            } else {
                res.send(sql_result);
            }
        });
        connection.end();
    });



http.createServer(app).listen(3001, function(){
    console.log('Server start');
});