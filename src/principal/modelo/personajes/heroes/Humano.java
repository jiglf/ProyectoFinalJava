package principal.modelo.personajes.heroes;

import principal.modelo.personajes.Personaje;
import principal.recursos.Raza;
import principal.recursos.Tipo;

public class Humano extends Personaje {

    /** el personaje va iniciado con el valor de los puntos de ataque a 0 ya que es un valor que se calculara de manera
     * especifica para cada personaje, al igual que los atributos tipo y raza que ya van inciados por defecto
     */
    public Humano(String nombre, int puntosVida, int puntosArmadura) {
        super(nombre, puntosVida, puntosArmadura);
        // por defecto lo pongo a 0 ya que lo calculo posteriormente
        puntosAtaque = 0;
        // lo pongo por defecto ya que es un personaje tipo Heroe
        tipo = Tipo.HEROE;
        raza = Raza.HUMANO;

    }

}
