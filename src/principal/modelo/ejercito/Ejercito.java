package principal.modelo.ejercito;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import principal.modelo.personajes.Personaje;



public class Ejercito {

    /** clase para definir la estructura y los metodos de cada lista para asi poder operar con los opciones de lista
     * la de heroes y la de bestias
     */

    ObservableList<Personaje> ejercitoPersonajes = FXCollections.observableArrayList();


    public void anadirPersonaje(Personaje personaje) {
        ejercitoPersonajes.add(personaje);
    }


    public boolean borrarPersonaje(Personaje personaje) {
        return ejercitoPersonajes.remove(personaje);
    }

    public boolean estaVacio() {
        return ejercitoPersonajes.isEmpty();
    }

    public int tamanhoEjercito() {
        return ejercitoPersonajes.size();
    }

    public Personaje obtenerPersonaje(int indice) {
        return ejercitoPersonajes.get(indice);
    }

    public Personaje borrarPosicion(int indice) {
        return ejercitoPersonajes.remove(indice);
    }

    public void cambiarPersonaje(int indice, Personaje personaje) {
        ejercitoPersonajes.set(indice, personaje);
    }

}
