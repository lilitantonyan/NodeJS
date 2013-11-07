/**
 * Created by lilit on 11/5/13.
 */
exports.user = function()
{
    var id;
    var username;
    var surname;
    var age;
    var nationality;

    this.get_id = function(){
        return this.id;
    }

    this.set_id = function(id){
        this.id = id;
    }

    this.get_username = function(){
        return this.username;
    }

    this.set_username = function(name){
        this.username = name;
    }

    this.get_surname = function(){
        return this.surname;
    }

    this.set_surname = function(surname){
        this.surname = surname;
    }

    this.get_age = function(){
        return this.age;
    }

    this.set_age = function(age){
        this.age = age;
    }

};
