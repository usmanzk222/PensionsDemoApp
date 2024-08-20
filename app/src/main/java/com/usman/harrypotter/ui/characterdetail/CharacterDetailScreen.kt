package com.usman.harrypotter.ui.characterdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.usman.harrypotter.R
import com.usman.harrypotter.domain.model.House
import com.usman.harrypotter.ui.model.CharacterUiModel

const val DETAIL_NAV_KEY = "character"

@Composable
fun CharacterDetailScreen(character: CharacterUiModel, padding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        character.image?.let { imageUrl ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Character Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.33f),
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.no_image)
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        }

        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            LabelText(text = "Actor: ${character.actor}")
            LabelText(text = "Species: ${character.species}")
            LabelText(text = "House: ${character.house}")
            LabelText(text = "Alive: ${(character.alive)}")
            character.dateOfBirth?.let {
                LabelText(text = "Date of Birth: $it")
            }
        }


    }
}

@Composable
fun LabelText(text: String, modifier: Modifier = Modifier){
    Text(text = text,
        modifier = modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small)),
        style = MaterialTheme.typography.bodyLarge)
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
    CharacterDetailScreen(character = uiModel, PaddingValues(dimensionResource(id = R.dimen.padding_medium)))
}