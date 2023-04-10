package com.example.chatapplication.model

class User {
    var name: String? = null
    var phoneNumber: String? = null
    var uid: String? = null

    constructor(){}

    constructor(name: String?, phoneNumber: String?, uid: String?) {
        this.name = name
        this.phoneNumber = phoneNumber
        this.uid = uid
    }
}