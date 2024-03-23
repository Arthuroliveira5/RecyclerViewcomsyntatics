package com.example.recyclerviewcomsyntatics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerviewcomsyntatics.R
import com.example.recyclerviewcomsyntatics.models.Live
import kotlinx.android.synthetic.main.res_item_live.view.author
import kotlinx.android.synthetic.main.res_item_live.view.thumbnail
import kotlinx.android.synthetic.main.res_item_live.view.title

//criando a classe adapter e exentendo o recyclerview.adapter o compilador exige a extensao de um viewholder via generitcs
//<RecyclerView.ViewHolder>(). A classe live apos isso ainda pede para implementar os metodos

class LiveAdapter(private val onItemClicked: (Live) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//antes de adiconao o evento de click a classe Liveadapter nao possuia paramentro,porem para
    //adiconar evento de click é necessario fazer private val onItemClicked: (Live) -> Unit

    // var tipo List do objeto Live


    private  var itens : List<Live> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //oncreateviewholder estende  o viewholder igual a classe liveviewholder

        //aqui retornamos um ojeto do tipo RecyclerView.ViewHolder
    //esse metodo sera chamado toda vez que criamos um titem em nosso recyclerview
    //retornamos  o LiveViewHolder que configuramos lá em baixo com syntatic e bind com parametros de layout

        return LiveViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.res_item_live,parent,false))



    }

    override fun getItemCount(): Int {
//retorna o tamanho do
        return itens.size

    }

    //tem que passar os dados para o adapter
    fun setList(liveList: List<Live>) {
        this.itens = liveList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //esse é o metodo que popular as informaçoes . é aqui que chamaos o bind
        //agora vamos para mainactivy iniciar o recyclerview

        when (holder) {
            is LiveViewHolder -> {
                holder.bind(itens[position],onItemClicked)
            }
        }
    }

    //apos implementar os metodos é necessario criar a classe ViewHolder que é a representacao do xml aqui no adapter. vou chamar um constrictor com
    // itemView do tipo View e extender um RecyclerView.ViewHolder(itemView)

    class LiveViewHolder  constructor(
        itemView : View
    ) : RecyclerView.ViewHolder(itemView){

        //aqui esta importando do xml via plugin syntatics
        private val liveTitle = itemView.title
        private val liveAuthor = itemView.author
        private val liveThumbnail = itemView.thumbnail

        //bibioteca glide





    //agora vamos criar um bind que pega as informaçoes da Model e coloca no item do recyclerview
    //recebe um obejo do tipo Live. o parametro private val onItemClicked: (Live) -> Unit é para evento de click
        fun bind(live :Live,onItemClicked: (Live) -> Unit){

            liveTitle.text = live.title
            liveAuthor.text = live.author
        //apos esse set dos atributos do model para item do recyclervier .Vamos mexer no metodo onCreateViewHolder

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(live.thumbnailUrl)
            .into(liveThumbnail)

        itemView.setOnClickListener {
            onItemClicked(live)
        }


        }


    }


}