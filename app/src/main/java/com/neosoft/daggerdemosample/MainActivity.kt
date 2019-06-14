package com.neosoft.daggerdemosample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity:AppCompatActivity() {

    //injecting retrofit
    @Inject lateinit var retrofit:Retrofit

    lateinit var listView:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyApplication().getNetComponent().inject(this)
        listView = findViewById(R.id.listView)
        getHeroes()

    }

    fun getHeroes(){
        val api = retrofit.create(Api::class.java)
        val call:Call<List<Hero>> = api.getHeroes()

        call.enqueue(object :Callback<List<Hero>>{
            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {
                Toast.makeText(getApplicationContext(), t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {
                 //To change body of created functions use File | Settings | File Templates.
                val heroList:List<Hero> = response.body()!!
                val heroes: Array<String?> =arrayOfNulls(heroList.size)

                for (i in 0 until heroList.size){
                    heroes[i] = heroList.get(i).name
                }

                listView.adapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_list_item_1,heroes)


            }

        })
    }
}