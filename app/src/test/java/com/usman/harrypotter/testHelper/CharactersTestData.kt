package com.usman.harrypotter.testHelper

import com.usman.harrypotter.data.local.entity.CharacterEntity
import com.usman.harrypotter.data.remote.model.Character
import com.usman.harrypotter.data.remote.model.toCharacterEntity
import com.usman.harrypotter.domain.model.House

object CharactersTestData {


    fun getCharactersListFromRemote(range: IntRange = (1..5)): List<Character>{
       return range.map(CharactersTestData::getCharacter)
    }

    fun getCharactersListFromLocal(range: IntRange = (1..5)):List<CharacterEntity>{
        return range.map(CharactersTestData::getCharacter).map { it.toCharacterEntity() }
    }

    fun getCharacter(index: Int): Character {
        return Character(
            id = "$index",
            name = "Name $index",
            actor = "Actor $index",
            species = "Species $index",
            house = House.entries[index % 5],
            alive = index % 2 == 0,
            dateOfBirth = "19-${index%12 + 1}-1979",
            image = "https://example.com/hermione.jpg"
        )
    }

}