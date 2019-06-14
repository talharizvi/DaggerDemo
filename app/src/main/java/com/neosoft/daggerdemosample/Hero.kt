package com.neosoft.daggerdemosample

data class Hero(
    //@SerializedName("name")
     val name:String,
     val realname:String,
     val team:String,
     val firstappearance:String,
     val createdby:String,
     val publisher:String,
     val imageurl:String,
     val bio:String) {
}