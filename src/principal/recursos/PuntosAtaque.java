package principal.recursos;

import principal.modelo.personajes.Personaje;

import java.util.Random;

public class PuntosAtaque {

    /** metodo para determinar los puntos de ataque obtenidos
     *
     * @param atancante recibe el personaje atancante y determina su valor de ataque teniendo en cuenta las condiciones indicadas
     * @return devuelve el valor del ataque, segun sea Heroe o Bestia el personaje atacante
     */
    public static int PuntosAtaque(Personaje atancante) {
        Random random = new Random();
        /*compruebo el tipo de personaje del atacante y dependiendo si es heroe o bestia aplico
        la tirada correspondiente
         */
        if (atancante.getType() == Tipo.HEROE) {
            int dado1 = random.nextInt(100) + 1;
            int dado2 = random.nextInt(100) + 1;
            if (dado1 > dado2) {
                return dado1;
            } else {
                return dado2;
            }

        } else {
            return random.nextInt(90) + 1;
        }

    }
}
