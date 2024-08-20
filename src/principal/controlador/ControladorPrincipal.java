package principal.controlador;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import principal.modelo.Juego;
import principal.modelo.ejercito.Ejercito;
import principal.modelo.personajes.Personaje;
import principal.recursos.Raza;


import java.net.URL;
import java.util.ResourceBundle;

/** implemento la interfaz Initializable para poder usar el metodo initialize */
public class ControladorPrincipal  implements Initializable {

    /** hago instacia de juego para tener acceso a los metodos de la clase para poder gestionar la logica del juego */
    static Juego juego = new Juego();

    /** creo 2 instancias de ejercito una para heroes y otra para  con llamada al metodo de juego para crearlos */
    Ejercito ejercitoHeroes = juego.creacionEjercitoHeroes();
    Ejercito ejercitoBestias = juego.creacionEjercitoBestias();


    @FXML
    private Label labelResumen;

    @FXML
    private ListView<Personaje> listaBestias;

    @FXML
    private ListView<Personaje> listaHeroes;

    @FXML
    private Pane panelCrear;

    @FXML
    private Pane panelPrincipal;

    @FXML
    private TextField tArmadura;

    @FXML
    private ChoiceBox<Raza> tChoice;

    @FXML
    private TextField tNombre;

    @FXML
    private TextArea tResumen;

    @FXML
    private TextField tVida;

    @FXML
    /** metodo utilizado para bajar al personaje de la lista del ejercito de las Bestias, para ello se saca el indice
     * del personaje de la lista marcado en la lista, una vez obtenido el indico se utiliza el metodo creado en la clase
     * ejercito que permite cambiar el personaje del indice indicado por el personaje posterior en la lista
     *
     */
    void bajarBestia(ActionEvent event) {
        if (!ejercitoBestias.estaVacio()) {
            Personaje bestiaSeleccionada = listaBestias.getSelectionModel().getSelectedItem();
            if (bestiaSeleccionada != null) {
                int indiceSeleccionado = listaBestias.getSelectionModel().getSelectedIndex();
                if (indiceSeleccionado < ejercitoBestias.tamanhoEjercito() - 1) {
                    ejercitoBestias.cambiarPersonaje(indiceSeleccionado, ejercitoBestias.obtenerPersonaje(indiceSeleccionado + 1));
                    ejercitoBestias.cambiarPersonaje(indiceSeleccionado + 1, bestiaSeleccionada);
                    cargarListas();
                    listaBestias.getSelectionModel().select(indiceSeleccionado + 1);
                }
            }
        } else {
            Alert alertaBajarBestia = new Alert(Alert.AlertType.WARNING);
            alertaBajarBestia.setContentText("No hay elementos para modificar");
            alertaBajarBestia.showAndWait();
        }
    }

    @FXML
    /** metodo utilizado para bajar al personaje de la lista del ejercito de las Heroes, para ello se saca el indice
     * del personaje de la lista marcado en la lista, una vez obtenido el indico se utiliza el metodo creado en la clase
     * ejercito que permite cambiar el personaje del indice indicado por el personaje posterior en la lista
     *
     */
    void bajarHeroe(ActionEvent event) {
        if(!ejercitoHeroes.estaVacio()) {
            Personaje heroeSeleccionado = listaHeroes.getSelectionModel().getSelectedItem();
            if (heroeSeleccionado != null) {
                int indiceSeleccionado = listaHeroes.getSelectionModel().getSelectedIndex();
                if (indiceSeleccionado < ejercitoHeroes.tamanhoEjercito() - 1) {
                    ejercitoHeroes.cambiarPersonaje(indiceSeleccionado, ejercitoHeroes.obtenerPersonaje(indiceSeleccionado + 1));
                    ejercitoHeroes.cambiarPersonaje(indiceSeleccionado + 1, heroeSeleccionado);
                    cargarListas();
                    listaHeroes.getSelectionModel().select(indiceSeleccionado + 1);
                }
            }
        } else {
            Alert alertaBajarHeroe = new Alert(Alert.AlertType.WARNING);
            alertaBajarHeroe.setContentText("No hay elementos para modificar");
            alertaBajarHeroe.showAndWait();
        }
    }

    @FXML
    /** metodo que se llama al accionar el boton Crear Personaje, donde si la introducción de los datos es correcta
     * se genera la creacion del personaje segun los datos previamente indicados
     */
    void crearPersonaje(ActionEvent event) {
        //si alguno de los datos esta vacio no se puede crear el personaje de manera correcta, sale mensaje de aviso
        if (tNombre.getText().isEmpty() || tChoice.getValue() == null || tVida.getText().isEmpty() || tArmadura.getText().isEmpty()) {
            Alert alertaFaltanDatos = new Alert(Alert.AlertType.WARNING);
            alertaFaltanDatos.setTitle("Error en los datos");
            alertaFaltanDatos.setContentText("Faltan datos por completar, no es posible crear personaje");
            alertaFaltanDatos.show();
        } else {
            recogerDatos();
            anadirPersonajeEjercito(recogerDatos());
            panelCrear.setVisible(false);
            vaciarCampos();
            cargarListas();
        }

    }


    @FXML
    /** metodo para eliminar al personaje indicado de la lista Bestias, segun el indice del personaje señalado */
    void eliminarBestia(ActionEvent event) {
        if (!ejercitoBestias.estaVacio()) {
            Personaje bestiaSeleccionada = listaBestias.getSelectionModel().getSelectedItem();
            if (bestiaSeleccionada != null) {
                ejercitoBestias.borrarPersonaje(bestiaSeleccionada);
                cargarListas();

            }
        } else {
            Alert alertaEliminarBestia = new Alert(Alert.AlertType.WARNING);
            alertaEliminarBestia.setContentText("No hay elementos para modificar");
            alertaEliminarBestia.showAndWait();
        }
    }

    @FXML
    /** metodo para eliminar al personaje indicado de la lista Heroes, segun el indice del personaje señalado */
    void eliminarHeroe(ActionEvent event) {
        if (!ejercitoHeroes.estaVacio()) {
            Personaje heroeSeleccionado = listaHeroes.getSelectionModel().getSelectedItem();
            if (heroeSeleccionado != null) {
                ejercitoHeroes.borrarPersonaje(heroeSeleccionado);
                cargarListas();

            }
        } else {
            Alert alertaEliminarHeroe = new Alert(Alert.AlertType.WARNING);
            alertaEliminarHeroe.setContentText("No hay elementos para modificar");
            alertaEliminarHeroe.showAndWait();
        }
    }


    /** metodo que al pulsar lucha, genera la simulacion de la lucha
    llamando a los metodos de la clase juego, con la logica del juego
     */
    @FXML
    void lucha(ActionEvent event) {
        labelResumen.setVisible(true);
        tResumen.setVisible(true);

        int turno = 1;//contador de turnos

            while ((!ejercitoHeroes.estaVacio()) && (!ejercitoBestias.estaVacio())) {
                String contadorTurno = "\n ----- Turno " + turno + " ----- ";
                tResumen.setText(tResumen.getText() + contadorTurno + "\n");
                turno++;
                for (int i = 0; i < Math.min(ejercitoHeroes.tamanhoEjercito(), ejercitoBestias.tamanhoEjercito()); i++) {
                    /** de esta manera se determina el valor de los ejercitos para hacer luchar a los iguales en posicion */
                    Personaje heroeActual = ejercitoHeroes.obtenerPersonaje(i);
                    Personaje bestiaActual = ejercitoBestias.obtenerPersonaje(i);

                    //lo hago de esta forma para que se muestren todas las lineas, sino solo saldría la última
                    tResumen.setText(tResumen.getText() + juego.presentacionLucha(heroeActual, bestiaActual) + "\n");
                    /** se gestiona dos llamadas al metodo turno lucha ya que una de ellas ataca el heroe
                     * y en la otra llamada al metodo ataca la bestia
                     */
                    tResumen.setText(tResumen.getText() + juego.turnoLucha(heroeActual, bestiaActual) + "\n");
                    tResumen.setText(tResumen.getText() + juego.turnoLucha(bestiaActual, heroeActual) + "\n");

                    /** metodo para comprobar si algun personaje ya quedo sin vida */
                    tResumen.setText(tResumen.getText() + juego.estaMuerto(heroeActual, bestiaActual) + "\n");
                }
            }
            /** en el momento que uno de los bandos esta vacio, se declara ganador al bando contrario */
            tResumen.setText(tResumen.getText() + juego.ganadorLucha(ejercitoHeroes, ejercitoBestias));

    }

    @FXML
    /** al dar a nuevo personaje se habilita el panel para la introducción de datos para el personaje que crees */
    void nuevoPersonaje(ActionEvent event) {
        panelCrear.setVisible(true);


    }

    @FXML
    /** metodo utilizado para subir al personaje de la lista del ejercito de las Bestias, para ello se saca el indice
     * del personaje de la lista marcado en la lista, una vez obtenido el indico se utiliza el metodo creado en la clase
     * ejercito que permite cambiar el personaje del indice indicado por el personaje superior en la lista
     *
     */
    void subirBestia(ActionEvent event) {
        if (!ejercitoHeroes.estaVacio()) {
            Personaje bestiaSeleccionada = listaBestias.getSelectionModel().getSelectedItem();
            if (bestiaSeleccionada != null) {
                int indiceSeleccionado = listaBestias.getSelectionModel().getSelectedIndex();
                if (indiceSeleccionado > 0) {
                    ejercitoBestias.cambiarPersonaje(indiceSeleccionado, ejercitoBestias.obtenerPersonaje(indiceSeleccionado - 1));
                    ejercitoBestias.cambiarPersonaje(indiceSeleccionado - 1, bestiaSeleccionada);
                    cargarListas();
                    listaBestias.getSelectionModel().select(indiceSeleccionado - 1);
                }
            }
        } else {
            Alert alertaSubirBestia = new Alert(Alert.AlertType.WARNING);
            alertaSubirBestia.setContentText("No hay elementos para modificar");
            alertaSubirBestia.showAndWait();
        }
    }

    @FXML
    /** metodo utilizado para subir al personaje de la lista del ejercito de las Heroes, para ello se saca el indice
     * del personaje de la lista marcado en la lista, una vez obtenido el indico se utiliza el metodo creado en la clase
     * ejercito que permite cambiar el personaje del indice indicado por el personaje superior en la lista
     *
     */
    void subirHeroe(ActionEvent event) {
        if (!ejercitoHeroes.estaVacio()) {
            Personaje heroSeleccionado = listaHeroes.getSelectionModel().getSelectedItem();
            if (heroSeleccionado != null) {
                int indiceSeleccionado = listaHeroes.getSelectionModel().getSelectedIndex();
                if (indiceSeleccionado > 0) {
                    ejercitoHeroes.cambiarPersonaje(indiceSeleccionado, ejercitoHeroes.obtenerPersonaje(indiceSeleccionado - 1));
                    ejercitoHeroes.cambiarPersonaje(indiceSeleccionado - 1, heroSeleccionado);
                    cargarListas();
                    listaHeroes.getSelectionModel().select(indiceSeleccionado - 1);
                }
            }
        } else {
            Alert alertaSubirHeroe = new Alert(Alert.AlertType.WARNING);
            alertaSubirHeroe.setContentText("No hay elementos para modificar");
            alertaSubirHeroe.showAndWait();
        }
    }

    @Override
    /** se cargan los defecto la lista de los personajes ya creados y se añaden las opciones para seleccionar
     * en la creacion de personajes nuevos por parte del usuario
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarListas();
        tChoice.getItems().addAll(Raza.ELFO, Raza.HUMANO, Raza.HOBBIT, Raza.MAGO, Raza.ORCO, Raza.TRASGO);

    }

    /** este metodo se usa tanto para cargar las listas por primera vez, como cada vez que se realiza una modificacion
     * en las listas y asi se actualizen el orden de las listas segun sea el evento indicado
     */
    public void cargarListas() {
        /* borro las listas al inicio para que se carguen de nuevo en el caso de que haya habido
        modificaciones en ellas, ya sea con Subir, Bajar o Eliminar
        */
        listaHeroes.getItems().clear();
        listaBestias.getItems().clear();
        //recorro con un for cada ejercito para mostrar los elementos de la lista
        for(int i = 0; i < ejercitoHeroes.tamanhoEjercito(); i++) {
            listaHeroes.getItems().add(ejercitoHeroes.obtenerPersonaje(i));
        }
        for(int i = 0; i < ejercitoBestias.tamanhoEjercito(); i++) {
            listaBestias.getItems().add(ejercitoBestias.obtenerPersonaje(i));
        }
    }

    /**  se verifica que los datos introducidos sean correctos y crea un nuevo personaje */
    public Personaje recogerDatos() {
        String nombre = tNombre.getText();
        Raza raza = tChoice.getValue();
        Personaje personaje = null;
        try {
            int vida = Integer.parseInt(tVida.getText());
            int armadura = Integer.parseInt(tArmadura.getText());

            personaje = new Personaje(nombre, raza, vida, armadura);


        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            Alert alertaCrear = new Alert(Alert.AlertType.ERROR);
            alertaCrear.setContentText("Los datos introducidos no son correctos, la vida y la armadura" +
                    "tienen que ser datos numericos");
            alertaCrear.showAndWait();
        }
        return personaje;
    }

    public void vaciarCampos() {
        tNombre.setText("");
        tChoice.setValue(null);
        tVida.setText("");
        tArmadura.setText("");
    }

    /**
     *
     * @param personaje se recibe al personaje creado por parte del usuario y se determina segun el valor del
     * atributo Raza en que ejercito se añadira
     */
    public void anadirPersonajeEjercito(Personaje personaje) {
        switch(personaje.getRaza()) {
            case ELFO:ejercitoHeroes.anadirPersonaje(personaje);
                break;
            case MAGO:ejercitoHeroes.anadirPersonaje(personaje);
                break;
            case HUMANO:ejercitoHeroes.anadirPersonaje(personaje);
                break;
            case HOBBIT:ejercitoHeroes.anadirPersonaje(personaje);
                break;

             case ORCO:ejercitoBestias.anadirPersonaje(personaje);
                break;
            case TRASGO:ejercitoBestias.anadirPersonaje(personaje);
                break;
        }
    }

}
