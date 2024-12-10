package com.lbg.pensionsdemo.ui.characterlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.lbg.pensionsdemo.R
import com.lbg.pensionsdemo.domain.model.House
import com.lbg.pensionsdemo.ui.model.CharacterUiModel

@Composable
fun CharacterItem(character: CharacterUiModel, onCharacterClick: (CharacterUiModel) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_small))
            .clickable { onCharacterClick(character) },
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.card_elevation))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(dimensionResource(id = R.dimen.width_house_indicator))
                    .background(color = character.house.color)
            )
            Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))) {

                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Actor: ${character.actor}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "House: ${character.house.name}", // Display house name
                    style = MaterialTheme.typography.bodyMedium
                )
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterItemPreview(){
    val uiModel = CharacterUiModel(
        id = "1232",
        name = "Harry Potter",
        actor = "Daniel Radcliffe",
        alive = "Yes",
        house = House.GRYFFINDOR,
        dateOfBirth = "31-07-1980",
        image = "https://ik.imagekit.io/hpapi/harry.jpg",
        species = "human"
    )
    CharacterItem(character = uiModel) {}
}

