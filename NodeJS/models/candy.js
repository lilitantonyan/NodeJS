/**
 * Created by lilit on 11/5/13.
 */

exports.candy = function(){

    var id;
    var candyname;
    var candybrand;
    var candytype;
    var candyprice;

    this.get_id = function(){
        return this.id;
    }

    this.set_id = function(name){
        this.id = name;
    }

    this.get_candyname = function(){
        return this.candyname;
    }

    this.set_candyname = function(name){
        this.candyname = name;
    }

    this.get_candybrand = function(){
        return this.candybrand;
    }

    this.set_candybrand = function(name){
        this.candybrand = name;
    }

    this.get_candytype = function(){
        return this.candytype;
    }

    this.set_candytype = function(name){
        this.candytype = name;
    }

    this.get_candyprice = function(){
        return this.candyprice;
    }

    this.set_candyprice = function(name){
        this.candyprice = name;
    }

}