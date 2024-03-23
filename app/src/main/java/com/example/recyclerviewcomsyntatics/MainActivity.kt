package com.example.recyclerviewcomsyntatics

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewcomsyntatics.adapter.LiveAdapter
import com.example.recyclerviewcomsyntatics.datasource.DataSource
import kotlinx.android.synthetic.main.activity_main.recyclerview

class MainActivity : AppCompatActivity() {

    // inicar o adapter como uma variavel lateinit do tipo LiveAdapter .
    //lateinit é uma variavel que inica apos alguns procedimentos terem  sido concluidos
    private  lateinit var liveAdapter: LiveAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//apos criar o adapter vamos inicar o recyclerview

        initRecyclerView()
        //adicionar dados no adapter
        addDataSource()


    }
//pegando os dados mockados classe Datasource
    private fun addDataSource() {
        val dataSource = DataSource.createDataSet()
            liveAdapter.setList(dataSource)
    }

    private fun initRecyclerView() {

        //instacia a variavel lateinit aqui
       this.liveAdapter = LiveAdapter { live ->
            openLink(live.link)
        }
        //Apos iniciar o adapter,precisamos colocar ele na recyclerview da main activty
        //import via sintatcs o id da mainactivity
        recyclerview.apply{
            recyclerview.layoutManager=LinearLayoutManager(this@MainActivity)
            recyclerview.adapter = liveAdapter
        }





    }

    private fun openLink(link: String) {

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)


    }


}