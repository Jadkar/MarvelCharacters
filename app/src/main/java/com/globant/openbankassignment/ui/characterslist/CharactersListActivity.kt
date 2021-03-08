package com.globant.openbankassignment.ui.characterslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.globant.openbankassignment.R
import dagger.android.AndroidInjection

class CharactersListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    companion object{
        const val ARG_CHARACTER_ID="characterId"
        const val ARG_CHARACTER_NAME="characterName"
    }
}