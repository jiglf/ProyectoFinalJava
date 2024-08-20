package principal.modelo.personajes.bestias;

import principal.modelo.personajes.Personaje;
import principal.recursos.Raza;
import principal.recursos.Tipo;

public class Orco extends Personaje {


    /** el personaje va iniciado con el valor de los puntos de ataque a 0 ya que es un valor que se calculara de manera
     * especifica para cada personaje, al igual que los atributos tipo y raza que ya van inciados por defecto
     */
    public Orco(String nombre, int puntosVida, int puntosArmadura) {
        super(nombre, puntosVida, puntosArmadura);
        puntosAtaque = 0;
        tipo = Tipo.BESTIA;
        raza = Raza.ORCO;

    }

}
