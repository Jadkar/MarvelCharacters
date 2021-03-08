package com.globant.openbankassignment.ui.charactersdetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.openbank.domain.model.CharacterDetailsModel
import com.openbank.domain.model.CharactersDeatilsType
import com.openbank.domain.model.DetailCharacterConverter
import com.globant.openbankassignment.databinding.RowItemCharacterDetailstypeBinding
import com.openbank.domain.model.ItemModel

class CharacterDetailTypeAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<CharacterDetailTypeAdapter.CharactersDetailsTypeHolder>() {

    private var mCharacterDetailsModelList: List<CharacterDetailsModel> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDetailsTypeHolder {
        val binding = RowItemCharacterDetailstypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return CharactersDetailsTypeHolder(binding)
    }

    fun setDetailsList(characterDetailsList: List<CharacterDetailsModel>) {
        this.mCharacterDetailsModelList = characterDetailsList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CharactersDetailsTypeHolder, position: Int) {
        holder.bind(mCharacterDetailsModelList[position])
    }

    override fun getItemCount(): Int = mCharacterDetailsModelList.size

    inner class CharactersDetailsTypeHolder(private val itemRowBinding: RowItemCharacterDetailstypeBinding) :
        RecyclerView.ViewHolder(
            itemRowBinding.root
        ) {

        fun bind(characterDetailsModel: CharacterDetailsModel) {

            itemRowBinding.setVariable(BR.characterDetails,characterDetailsModel)

            var listItem: List<ItemModel> = emptyList()
            when (characterDetailsModel.title) {

                CharactersDeatilsType.COMICS.value -> {
                    // handleComicsView()
                    listItem =
                        DetailCharacterConverter.convertComicsItem(characterDetailsModel.comics!!)
                }
                CharactersDeatilsType.SERIES.value -> {
                    listItem =
                        DetailCharacterConverter.convertSeriesItem(characterDetailsModel.series!!)
                }
                CharactersDeatilsType.STORIES.value -> {
                    listItem =
                        DetailCharacterConverter.convertStoriesItem(characterDetailsModel.stories!!)
                }
                CharactersDeatilsType.EVENTS.value -> {
                    listItem =
                        DetailCharacterConverter.convertEventsItem(characterDetailsModel.events!!)
                }
                CharactersDeatilsType.CHARACTERSDETAILSSOURCE.value -> {
                    listItem =
                        DetailCharacterConverter.convertUrlsItem(characterDetailsModel.urls!!)
                }

            }

            val itemListDataAdapter =
                CharacterDetailsTypeItemAdapter(
                    listItem
                )
            itemRowBinding.rvDetailsType.setHasFixedSize(true)
            itemRowBinding.rvDetailsType.layoutManager = LinearLayoutManager(
                mContext,
                LinearLayoutManager.HORIZONTAL, false
            )
            itemRowBinding.rvDetailsType.adapter = itemListDataAdapter
            itemRowBinding.rvDetailsType.isNestedScrollingEnabled = false
        }

    }

}