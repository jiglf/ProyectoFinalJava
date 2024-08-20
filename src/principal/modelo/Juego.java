package principal.modelo;

import principal.modelo.ejercito.Ejercito;
import principal.modelo.personajes.Personaje;
import principal.modelo.personajes.bestias.Trasgo;
import principal.modelo.personajes.bestias.Orco;
import principal.modelo.personajes.heroes.Elfo;
import principal.modelo.personajes.heroes.Hobbit;
import principal.modelo.personajes.heroes.Humano;
import principal.modelo.personajes.heroes.Mago;
import principal.recursos.PuntosAtaque;
import principal.recursos.Tipo;

/** clase Juego creada con la logica del juego */
public class Juego {

    /** realizo la instancia de la clase Ejercito, 2 veces una para asociarla al ejercitoHeroes
     * y otra al ejercitoBestias y asi poder utilizar los metodos implentados para ejercito
     */
    static Ejercito ejercitoHeroes = new Ejercito();
    static Ejercito ejercitoBestias = new Ejercito();

    public  Ejercito creacionEjercitoHeroes() {
        ejercitoHeroes.anadirPersonaje(new Elfo("Légolas", 150, 30));
        ejercitoHeroes.anadirPersonaje(new Humano("Aragorn", 150, 50));
        ejercitoHeroes.anadirPersonaje(new Humano("Boromir", 100, 60));
        ejercitoHeroes.anadirPersonaje(new Mago("Gandalf", 300, 30));
        ejercitoHeroes.anadirPersonaje(new Hobbit("Frodo", 20, 10));

        return ejercitoHeroes;

    }

    public  Ejercito creacionEjercitoBestias() {
        ejercitoBestias.anadirPersonaje(new Orco("Lurtz", 200, 60));
        ejercitoBestias.anadirPersonaje(new Orco("Shagrat", 220, 50));
        ejercitoBestias.anadirPersonaje(new Trasgo("Uglúk", 120, 30));
        ejercitoBestias.anadirPersonaje(new Trasgo("Mauhúr", 100, 30));

        return ejercitoBestias;
    }

    /** metodo para presentar la lucha recibe 2 personajes el primero sera el atacante y el segundo el defensor */
    public String presentacionLucha(Personaje atacante, Personaje defensor) {
        String presentacion = "Lucha entre " + atacante.getNombre() + " (Vida: " + atacante.getPuntosVida() +
                " Armadura: " + atacante.getPuntosArmadura() + ") y " + defensor.getNombre() +
                " (Vida: " + defensor.getPuntosVida() + " Armadura: " + defensor.getPuntosArmadura() + ")";
        return presentacion;
    }

    /** metodo para calcular la bonificacion de ataque en el caso de que el personaje tenga bonificacion
           en el caso de que no tenga bonificacion se aplican los puntos de ataque normales
        en este metodo se tienen en cuenta los tipos a los que pertenece cada personaje ya sea atacante o defensor
     para asi poder aplicar cada bonus especificado
     */
    public  int ataqueBonus(Personaje atacante, Personaje defensor) {
        int puntosAtaque = PuntosAtaque.PuntosAtaque(atacante);
        if (atacante instanceof Elfo && defensor instanceof Orco) {
            return puntosAtaque + 10;
        } else if (atacante instanceof Hobbit && defensor instanceof Trasgo) {
            return puntosAtaque -5;
        } else {
            return puntosAtaque;
        }
    }

    /** metodo para calcular los puntos de defensa en el caso de que el personaje tenga bonificacion
           en el caso de que no tenga bonificacion se aplican los puntos de armadura normales
     se valora tanto el tipo del atacante como el defensor
     */
    public  int armaduraBonus(Personaje atacante, Personaje defensor) {
        int puntosArmadura = defensor.getPuntosArmadura();
        if (atacante instanceof Orco) {
            // al ser el atacante un orco, le quita un 10 % de armadura al rival, calculo el 90 %
            return (int) (defensor.getPuntosArmadura() * 0.90);
        } else {
            return puntosArmadura;
        }
    }

    /** metodo para simular turno de lucha
     *
     * @param atacante recibe el atacante donde a la hora de determinar el valor del ataque se llama al metodo ataqueBonus
     * @param defensor recibe al defensor y se llama a armaduraBonus para determinar el valor de su defensa
     * @return devuelve el String con el resultado de la lucha tras determinar el daño
     */
    public String turnoLucha(Personaje atacante, Personaje defensor) {
        int ataque = ataqueBonus(atacante, defensor);
        int armadura = armaduraBonus(atacante, defensor);
        String cadenaAtaque ="";

        if (ataque > armadura) {
            int herida = ataque - armadura;
            cadenaAtaque ="   " + atacante.getNombre() + " saca " + ataque + " y le quita "
                    + herida + " de vida a " + defensor.getNombre();
            // actualizo la vida para el siguiente turno
            defensor.setPuntosVida(defensor.getPuntosVida() - herida);

        } else {
            cadenaAtaque ="   " + atacante.getNombre() + " saca " + ataque + " pero no hace daño a "
                    + defensor.getNombre();
        }
        return cadenaAtaque;
    }

    /** metodo para determinar si un personaje esta muerto y en el caso eliminarlo del ejercito correspondiente */
    public String estaMuerto(Personaje heroe, Personaje bestia) {
        String cadenaMuerte ="";
        if (heroe.getPuntosVida() <= 0 && heroe.getType() == Tipo.HEROE) {
            ejercitoHeroes.borrarPersonaje(heroe);
            cadenaMuerte = "¡" + heroe.getNombre() + " ha caido en combate!";
        } else if (bestia.getPuntosVida() <= 0 && bestia.getType() == Tipo.BESTIA) {
            ejercitoBestias.borrarPersonaje(bestia);
            cadenaMuerte ="¡" + bestia.getNombre() + " ha caido en combate!";
        }
        return cadenaMuerte;
    }

    /** metodo para determinar el ganador de la lucha, cuando uno de los ejercitos este vacio */
    public String ganadorLucha(Ejercito ejercitoHeroes, Ejercito ejercitoBestias) {
        String cadenaGanador ="";
        if (ejercitoHeroes.estaVacio()) {
            cadenaGanador = "¡Las bestias ganan y dominarán la tierra media";
        } else {
            cadenaGanador = "¡Los ganadores han sido los heroes, el anillo será destruido!";
        }
        return cadenaGanador;
    }
}