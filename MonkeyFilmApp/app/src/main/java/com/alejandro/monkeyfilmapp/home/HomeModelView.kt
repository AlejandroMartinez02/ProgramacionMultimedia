package com.alejandro.monkeyfilmapp.home

import androidx.lifecycle.MutableLiveData
import com.alejandro.monkeyfilmapp.MediaModel
import com.alejandro.monkeyfilmapp.R

class HomeModelView {
    private val _peliculas = MutableLiveData<MutableList<MediaModel>>()
    val peliculas = _peliculas
    init {
        _peliculas.value = mutableListOf(

            //hACER UN FOREACH PARA AÑADIR CADA OBJECTO MEDIAMODEL A LA LISTA
            MediaModel(
                id = 1,
                title = "Peaky Blinders",
                description = "Una familia de pandilleros asentada en " + "Birmingham, Reino Unido, tras la Primera Guerra Mundial (1914-1918), dirige un " + "local de apuestas hípicas. Las actividades del ambicioso jefe de la banda " + "llaman la atención del Inspector jefe Chester Campbell, un detective de la Real " + "Policía Irlandesa que es enviado desde Belfast para limpiar la ciudad y acabar " + "con la banda.",
                cartel = R.drawable.c1,
                score = 86,
                genre = listOf("Drama", "Crimen")
            ),
            MediaModel(
                id = 2,
                title = "Pinocho",
                description = "Versión en acción real del aclamado cuento sobre una marioneta que se " + "embarca en una trepidante aventura para convertirse en un niño de verdad. " + "La historia también presenta a otros personajes, como Gepetto, el carpintero " + "que fabrica a Pinocho y lo trata como a su propio hijo; Pepito Grillo, que " + "hace las veces de guía y “conciencia” de Pinocho; el Hada Azul; el Honrado " + "Juan; la gaviota Sofía, y el cochero.",
                cartel = R.drawable.c2,
                score = 67,
                genre = listOf("Fantasía", "Aventura", "Familia")
            ),
            MediaModel(
                id = 3,
                title = "The Walking Dead",
                description = "\"The Walking Dead\" está ambientada en un futuro apocalíptico con " + "la Tierra devastada por el efecto de un cataclismo, que ha provocado la " + "mutación en zombies de la mayor parte de los habitantes del planeta. La " + "serie, explora las dificultades de los protagonistas para sobrevivir en un " + "mundo poblado por el horror, así como las relaciones personales que se " + "establecen entre ellos, en ocasiones también una amenaza para su " + "supervivencia.",
                cartel = R.drawable.c3,
                score = 81,
                genre = listOf("Acción", "Drama", "Ciencia ficción", "Fantasía", "Aventura")
            ),
            MediaModel(
                id = 4,
                title = "Star Wars: Andor",
                description = "Las aventuras del espía rebelde Cassian Andor durante los años de " + "formación de la Rebelión antes de los eventos de Rogue One: A Star Wars " + "Story. La serie explora historias llenas de espionaje y atrevidas misiones " + "para devolver la esperanza a una galaxia dominada por un imperio despiadado.",
                cartel = R.drawable.c4,
                score = 81,
                genre = listOf(
                    "Acción", "Guerra", "Politica", "Ciencia ficción", "Fantasía", "Aventura"
                )
            ),
            MediaModel(
                id = 5,
                title = "Los Simpson",
                description = "Comedia americana de animación creada por Matt Groening para la " + "compañía Fox. La serie es una parodia satírica del estilo de la clase media " + "americana encarnada por una familia con ese mismo nombre, compuesta por " + "Homer, Marge, Bart, Lisa, y Maggie Simpson. La trama se desarrolla en la " + "ciudad ficticia de Springfield y parodia la cultura, la sociedad, la " + "televisión estadounidense y muchos otros aspectos de la condición humana.",
                cartel = R.drawable.c5,
                score = 81,
                genre = listOf("Familia", "Animación", "Comedia")
            ),
            MediaModel(
                id = 6,
                title = "Doctor Who: el día del Doctor",
                description = "Episodio especial de \"Doctor Who\" realizado con motivo de la " + "celebración del 50º aniversario de la serie. En la Tierra, el Undécimo Doctor " + "y Clara descubren una peligrosa conspiración en una galería de arte. En 1562, " + "el Décimo Doctor caza Zygons con la ayuda de la Reina Isabel. El último día " + "de la Guerra del Tiempo, un hombre que ya no quiere llamarse \"El Doctor\" " + "toma una terrible decisión: debe cometer un genocidio contra su propia raza " + "para impedir la destrucción del Universo. Todos estos sucesos resultan estar " + "conectados cuando tres encarnaciones del mismo Doctor deben enfrentarse al " + "momento más terrible de sus vidas.",
                cartel = R.drawable.c6,
                score = 82,
                genre = listOf("Ciencia ficción", "Aventura")
            ),
            MediaModel(
                id = 7,
                title = "SPY×FAMILY",
                description = "Todo el mundo tiene una parte de sí mismos que no puede mostrar a los " + "demás. En una era en la que las naciones de todo el mundo se encuentran " + "involucradas en una feroz guerra de información a puerta cerrada, Ostania " + "y Westalis llevan décadas en guerra fría. La División de Inteligencia de " + "Westalis (WISE) envía a su mejor espía, \"Twilight\", en una misión " + "ultrasecreta para vigilar los movimientos de Donovan Desmond, quien dirige " + "el Partido Nacional por la Unidad de Ostania, responsable de bombardear los " + "esfuerzos de paz entre ambos países.",
                cartel = R.drawable.c7,
                score = 87,
                genre = listOf("Animación", "Aventura", "Acción", "Comedia")
            ),
            MediaModel(
                id = 8,
                title = "Jurassic World: Dominion",
                description = "Cuatro años después de la destrucción de Isla Nublar, los dinosaurios " + "conviven – y cazan – con los seres humanos en todo el mundo. Este frágil " + "equilibrio cambiará el futuro y decidirá, de una vez por todas, si los seres " + "humanos seguirán en la cúspide de los depredadores en un planeta que comparten " + "con los animales más temibles de la creación.",
                cartel = R.drawable.c8,
                score = 70,
                genre = listOf("Ciencia ficción", "Aventura", "Acción")
            ),
            MediaModel(
                id = 9,
                title = "Fullmetal Alchemist: La alquimia final",
                description = "El largo y tortuoso viaje de los hermanos Elric llega a su épico " + "final, en el que deben enfrentar una amenaza de otro mundo que afecta a " + "todo el país.",
                cartel = R.drawable.c9,
                score = 63,
                genre = listOf("Fantasía", "Aventura", "Acción")
            ),
            MediaModel(
                id = 10,
                title = "Top Gun: Maverick",
                description = "Después de más de 30 años de servicio como uno de los mejores " + "aviadores de la Armada, Pete \"Maverick\" Mitchell se encuentra dónde siempre " + "quiso estar, empujando los límites como un valiente piloto de prueba y " + "esquivando el alcance en su rango, que no le dejaría volar emplazándolo en " + "tierra. Cuando se encuentra entrenando a un destacamento de graduados de Top " + "Gun para una misión especializada, Maverick se encuentra allí con el teniente " + "Bradley Bradshaw, el hijo de su difunto amigo \"Goose\".",
                cartel = R.drawable.c10,
                score = 83,
                genre = listOf("Drama", "Acción")
            ),
        )
    }

    fun addPelicula(mediaModel: MediaModel){
        _peliculas.value?.add(mediaModel)
    }
}