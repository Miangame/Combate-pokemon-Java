<b>1. Introducción</b></br>
El proyecto consistirá en reproducir combates como el famoso juego “Pokemon”, en el que dos entrenadores, cada uno con 6 pokemons lucharán por turnos hasta que alguno de los dos se agoten o se rinda.

<b>2. Partes</b>
<ul>
<li>Inicio</li>
El inicio será una pantalla con:</br>
Un campo para introducir el nombre del usuario</br>
Un botón de entrar para entrar en la aplicación</br>
Un botón de ‘Acerca de’ que mostrará información sobre el autor.</br>
Un botón de ‘Ayuda’ que abrirá una ayuda</br>
<li>Pantalla de manejo de pokemons</li>
Se podrán añadir nuevos pokemons, borrar, modificarlos, mostrarlos ordenados o buscar por nombre. Tendrá un botón de jugar para pasar a la siguiente pantalla.</br>
<li>Pantalla de elección de pokemons</li>
Se escogerán 6 pokemons para cada entrenador de los que hay precargados
<li>Pantalla de combate</li>
Se mostrará nuestro pokemon y el rival, ambos con su barra de vida y su nombre, además de 4 opciones: Lucha, Pokemon(para elegir otro pokemon) y huida(para huir del combate).
En el combate habrá interacción con el usuario.
<li>Pantalla final</li>
Al acabar la partida, saldrá una pantalla con victoria o con derrota

<li>Código</li>
</ul>
<ol>
<li><b>Clase Usuario</b></li>
Será el usuario o entrenador del juego. Dispondrá de los siguientes campos:</br>
Alias: Alias del usuario</br>
Lista de pokemons: Hasta 6 pokemons puede tener</br>
Fecha de registro: Para conocer cuando se registra y el tiempo que lleva registrado</br>
Se almacenarán en un archivo</br></br>
<li><b>Clase Pokemon</b></li>
Será la clase padre de los pokemons. Tendrá los siguientes campos:</br>
Nombre: Nombre del pokemon</br>
Tipo: Será el tipo que tenga cada pokemon</br>
Vida: Vida del pokemon entre 300 y 500
Además todos los pokemons se almacenarán en un fichero, y al cargarlos se introducirán en un arrayList para manejarlos</br></br>

<li><b>Clase Agua</b></li>
Clase hija que hereda de Pokemon. Implementa la interfaz Atacable y Defensable</br>
danoBase: Daño base para cada ataque de tipo agua.</br>
Defensa: Defensa de cada pokemon dependiendo del tipo del atacante</br>
Precisión: Precisión de cada ataque del pokemon</br>
Ataques[4]: Array de ataques de tipo agua. Cada uno tendrá un daño base + número aleatorio entre 30 y 90</br></br>
<li><b>Clase Fuego</b></li>
Clase hija que hereda de Pokemon. Implementa la interfaz Atacable y Defensable</br>
danoBase: Daño base para cada ataque de tipo Fuego.</br>
Defensa: Defensa de cada pokemon dependiendo del tipo del atacante</br>
Precisión: Precisión de cada ataque del pokemon</br>
Ataques[4]: Array de ataques de tipo fuego. Cada uno tendrá un daño base + número aleatorio entre 30 y 90</br></br>

<li><b>Clase Planta</b></li>
Clase hija que hereda de Pokemon. Implementa la interfaz Atacable y Defensable</br>
danoBase: Daño base para cada ataque de tipo Planta.</br>
Defensa: Defensa de cada pokemon dependiendo del tipo del atacante</br>
Precisión: Precisión de cada ataque del pokemon</br>
Ataques[4]: Array de ataques de tipo planta. Cada uno tendrá un daño base + número aleatorio entre 30 y 90</br></br>

<li><b>Clase Electrico</b></li>
Clase hija que hereda de Pokemon. Implementa la interfaz Atacable y Defensable</br>
danoBase: Daño base para cada ataque de tipo eléctrico.</br>
Defensa: Defensa de cada pokemon dependiendo del tipo del atacante</br>
Precisión: Precisión de cada ataque del pokemon</br>
Ataques[4]: Array de ataques de tipo eléctrico. Cada uno tendrá un daño base + número aleatorio entre 30 y 90</br></br>

<li><b>Clase Volador</b></li>
Clase hija que hereda de Pokemon. Implementa la interfaz Atacable y Defensable</br>
danoBase: Daño base para cada ataque de tipo volador.</br>
Defensa: Defensa de cada pokemon dependiendo del tipo del atacante</br>
Precisión: Precisión de cada ataque del pokemon</br>
Ataques[4]: Array de ataques de tipo volador. Cada uno tendrá un daño base + número aleatorio entre 30 y 90</br></br>

<li><b>Enumeración Ataques</b></li>

    /**
     * Ataques de tipo agua
     */
    PISTOLA_AGUA, HIDROBOMBA, RAYO_BURBUJA, SURF, HIDRO_CANON, ACUA_COLA, CASCADA, HIDROPULSO,
 
    /**
     * Ataques de tipo elÃ©ctrico
     */
    BOLA_VOLTIO, CHISPA, CHISPAZO, ELECTROCANION, IMPACTRUENO, ONDA_VOLTIO, RAYO, TRUENO,
 
    /**
     * Ataques de tipo fuego
     */
    ASCUAS, GIRO_FUEGO, LANZALLAMAS, LLAMARADA, ONDA_IGNEA, RUEDA_FUEGO, HUMAREDA, PIROTECNIA,
 
    /**
     * Ataques de tipo planta
     */
    HOJA_AFILADA, LATIGAZO, RAYO_SOLAR, FOGONAZO, ENERGIBOLA, CUCHILLA_SOLAR, DANZA_PETALO, HOJA_AGUDA,
 
    /**
     * Ataques de tipo volador
     */
    ACROBATA, AIRE_AFILADO, ATAQUE_AEREO, ATAQUE_ALA, GOLPE_AEREO, PICO_TALADRO, PICOTAZO, TORNADO;
 

<li><b>Interfaz Atacable</b></li>
Tendrá getAtaque().</br>
La clase que lo implemente, tendrá que calcular el daño para cada ataque que tenga, partiendo de un daño base de la propia clase y sumándole un numero aleatorio entre 30 y 90. Por ejemplo si el daño base es 15, se le sumará un aleatorio entre 30 y 90</br></br>
<li><b>Interfaz Defensable</b></li>
Tendrá getDefensa().</br>
La clase que lo implemente, calculará la defensa según el tipo atacante. Si el tipo del atacante es superior al de nuestro pokemon actual, el daño recibido es doble, mientras que si es al contrario, el daño recibido es la mitad. Si es de un tipo indiferente, se reducirá del daño solo la defensa que tenga nuestro pokemon.</br></br>
<li><b>Excepciones</b></li>
ContrasenaNoValidaException, CorreoNoValidoException, IndiceNoValidoException, PokemonNoExisteException, PokemonYaExisteException, UsuarioNoValidoException, VidaNoValidaException</br></br>
<li><b>Expresiones regulares</b></li>
Para controlar que la dirección de correo, contraseña y nombre de usuario sean válidos.</br></br>
<li><b>Fechas</b></li>
Para calcular cuanto tiempo lleva el usuario registrado en el sistema
</ol>
