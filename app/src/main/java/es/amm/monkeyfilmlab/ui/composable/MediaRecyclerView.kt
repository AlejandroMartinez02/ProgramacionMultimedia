package es.amm.monkeyfilmlab.ui.composable

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.amm.monkeyfilmlab.R
import es.amm.monkeyfilmlab.model.MediaModel
import kotlinx.coroutines.launch

@Composable
fun MediaListView(){
    val context = LocalContext.current
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(vertical = 1.dp) ){
        items(getListOfMedia()){ mediaModel ->
            MediaItem(mediaModel){
                Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MediaGridView(){
    val context = LocalContext.current
    LazyVerticalGrid(cells = GridCells.Fixed(2)){
        items(getListOfMedia()){ mediaModel ->
            MediaItem(mediaModel){
                Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
@Composable
fun MediaListViewWidthCustomControl(){
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val showbackButton by remember{
        derivedStateOf { listState.firstVisibleItemIndex > 0}
    }
    val showForwardButton by remember{
        derivedStateOf { listState.firstVisibleItemIndex < listState.layoutInfo.totalItemsCount - 2}
    }

    val coroutineScope = rememberCoroutineScope()
    Column() {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 1.dp),
            state = listState,
            modifier = Modifier.weight(1f)){
            items(getListOfMedia()){ mediaModel ->
                MediaItem(mediaModel){
                    Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
                }
            }
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)){
            if(showbackButton){
                IconButton(onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(listState.firstVisibleItemIndex - 1)
                    }
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "<--")
                }
            }
            if(showForwardButton){
                IconButton(onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(listState.firstVisibleItemIndex + 1)
                    }
                }) {
                    Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "-->")
                }
            }
        }
    }
}

@Composable
fun MediaItem(mediaModel: MediaModel, onItemPress : (MediaModel) -> Unit){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    //val screenWidth = LocalConfiguration.current.screenWidthDp.dp - 8.dp
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier
            .width(screenWidth)
            .clickable { onItemPress }
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Image(
                painterResource(id = mediaModel.cartel),
                contentDescription = mediaModel.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                contentScale = ContentScale.Crop
            )
            Text(
                text = mediaModel.description,
                modifier = Modifier
                    .requiredHeight(100.dp)
                    .padding(start = 8.dp, bottom = 4.dp, end = 8.dp)
                    .verticalScroll(rememberScrollState())
                    .align(Alignment.CenterHorizontally),
                fontSize = 10.sp
            )
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)){
                mediaModel.genre.forEach{genre ->
                    Text(text = genre)
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }


    }
}


fun getListOfMedia(): List<MediaModel> {
    return listOf(
        MediaModel(
            id = 1,
            title = "Peaky Blinders",
            description = "Una familia de pandilleros asentada en " +
                    "Birmingham, Reino Unido, tras la Primera Guerra Mundial (1914-1918), dirige un " +
                    "local de apuestas h??picas. Las actividades del ambicioso jefe de la banda " +
                    "llaman la atenci??n del Inspector jefe Chester Campbell, un detective de la Real " +
                    "Polic??a Irlandesa que es enviado desde Belfast para limpiar la ciudad y acabar " +
                    "con la banda.",
            cartel = R.drawable.c1,
            score = 86,
            genre = listOf("Drama", "Crimen")
        ),
        MediaModel(
            id = 2,
            title = "Pinocho",
            description = "Versi??n en acci??n real del aclamado cuento sobre una marioneta que se " +
                    "embarca en una trepidante aventura para convertirse en un ni??o de verdad. " +
                    "La historia tambi??n presenta a otros personajes, como Gepetto, el carpintero " +
                    "que fabrica a Pinocho y lo trata como a su propio hijo; Pepito Grillo, que " +
                    "hace las veces de gu??a y ???conciencia??? de Pinocho; el Hada Azul; el Honrado " +
                    "Juan; la gaviota Sof??a, y el cochero.",
            cartel = R.drawable.c2,
            score = 67,
            genre = listOf("Fantas??a", "Aventura", "Familia")
        ),
        MediaModel(
            id = 3,
            title = "The Walking Dead",
            description = "\"The Walking Dead\" est?? ambientada en un futuro apocal??ptico con " +
                    "la Tierra devastada por el efecto de un cataclismo, que ha provocado la " +
                    "mutaci??n en zombies de la mayor parte de los habitantes del planeta. La " +
                    "serie, explora las dificultades de los protagonistas para sobrevivir en un " +
                    "mundo poblado por el horror, as?? como las relaciones personales que se " +
                    "establecen entre ellos, en ocasiones tambi??n una amenaza para su " +
                    "supervivencia.",
            cartel = R.drawable.c3,
            score = 81,
            genre = listOf("Acci??n", "Drama", "Ciencia ficci??n", "Fantas??a", "Aventura")
        ),
        MediaModel(
            id = 4,
            title = "Star Wars: Andor",
            description = "Las aventuras del esp??a rebelde Cassian Andor durante los a??os de " +
                    "formaci??n de la Rebeli??n antes de los eventos de Rogue One: A Star Wars " +
                    "Story. La serie explora historias llenas de espionaje y atrevidas misiones " +
                    "para devolver la esperanza a una galaxia dominada por un imperio despiadado.",
            cartel = R.drawable.c4,
            score = 81,
            genre = listOf(
                "Acci??n",
                "Guerra",
                "Politica",
                "Ciencia ficci??n",
                "Fantas??a",
                "Aventura"
            )
        ),
        MediaModel(
            id = 5,
            title = "Los Simpson",
            description = "Comedia americana de animaci??n creada por Matt Groening para la " +
                    "compa????a Fox. La serie es una parodia sat??rica del estilo de la clase media " +
                    "americana encarnada por una familia con ese mismo nombre, compuesta por " +
                    "Homer, Marge, Bart, Lisa, y Maggie Simpson. La trama se desarrolla en la " +
                    "ciudad ficticia de Springfield y parodia la cultura, la sociedad, la " +
                    "televisi??n estadounidense y muchos otros aspectos de la condici??n humana.",
            cartel = R.drawable.c5,
            score = 81,
            genre = listOf("Familia", "Animaci??n", "Comedia")
        ),
        MediaModel(
            id = 6,
            title = "Doctor Who: el d??a del Doctor",
            description = "Episodio especial de \"Doctor Who\" realizado con motivo de la " +
                    "celebraci??n del 50?? aniversario de la serie. En la Tierra, el Und??cimo Doctor " +
                    "y Clara descubren una peligrosa conspiraci??n en una galer??a de arte. En 1562, " +
                    "el D??cimo Doctor caza Zygons con la ayuda de la Reina Isabel. El ??ltimo d??a " +
                    "de la Guerra del Tiempo, un hombre que ya no quiere llamarse \"El Doctor\" " +
                    "toma una terrible decisi??n: debe cometer un genocidio contra su propia raza " +
                    "para impedir la destrucci??n del Universo. Todos estos sucesos resultan estar " +
                    "conectados cuando tres encarnaciones del mismo Doctor deben enfrentarse al " +
                    "momento m??s terrible de sus vidas.",
            cartel = R.drawable.c6,
            score = 82,
            genre = listOf("Ciencia ficci??n", "Aventura")
        ),
        MediaModel(
            id = 7,
            title = "SPY??FAMILY",
            description = "Todo el mundo tiene una parte de s?? mismos que no puede mostrar a los " +
                    "dem??s. En una era en la que las naciones de todo el mundo se encuentran " +
                    "involucradas en una feroz guerra de informaci??n a puerta cerrada, Ostania " +
                    "y Westalis llevan d??cadas en guerra fr??a. La Divisi??n de Inteligencia de " +
                    "Westalis (WISE) env??a a su mejor esp??a, \"Twilight\", en una misi??n " +
                    "ultrasecreta para vigilar los movimientos de Donovan Desmond, quien dirige " +
                    "el Partido Nacional por la Unidad de Ostania, responsable de bombardear los " +
                    "esfuerzos de paz entre ambos pa??ses.",
            cartel = R.drawable.c7,
            score = 87,
            genre = listOf("Animaci??n", "Aventura", "Acci??n", "Comedia")
        ),
        MediaModel(
            id = 8,
            title = "Jurassic World: Dominion",
            description = "Cuatro a??os despu??s de la destrucci??n de Isla Nublar, los dinosaurios " +
                    "conviven ??? y cazan ??? con los seres humanos en todo el mundo. Este fr??gil " +
                    "equilibrio cambiar?? el futuro y decidir??, de una vez por todas, si los seres " +
                    "humanos seguir??n en la c??spide de los depredadores en un planeta que comparten " +
                    "con los animales m??s temibles de la creaci??n.",
            cartel = R.drawable.c8,
            score = 70,
            genre = listOf("Ciencia ficci??n", "Aventura", "Acci??n")
        ),
        MediaModel(
            id = 9,
            title = "Fullmetal Alchemist: La alquimia final",
            description = "El largo y tortuoso viaje de los hermanos Elric llega a su ??pico " +
                    "final, en el que deben enfrentar una amenaza de otro mundo que afecta a " +
                    "todo el pa??s.",
            cartel = R.drawable.c9,
            score = 63,
            genre = listOf("Fantas??a", "Aventura", "Acci??n")
        ),
        MediaModel(
            id = 10,
            title = "Top Gun: Maverick",
            description = "Despu??s de m??s de 30 a??os de servicio como uno de los mejores " +
                    "aviadores de la Armada, Pete \"Maverick\" Mitchell se encuentra d??nde siempre " +
                    "quiso estar, empujando los l??mites como un valiente piloto de prueba y " +
                    "esquivando el alcance en su rango, que no le dejar??a volar emplaz??ndolo en " +
                    "tierra. Cuando se encuentra entrenando a un destacamento de graduados de Top " +
                    "Gun para una misi??n especializada, Maverick se encuentra all?? con el teniente " +
                    "Bradley Bradshaw, el hijo de su difunto amigo \"Goose\".",
            cartel = R.drawable.c10,
            score = 83,
            genre = listOf("Drama", "Acci??n")
        ),
    )
}