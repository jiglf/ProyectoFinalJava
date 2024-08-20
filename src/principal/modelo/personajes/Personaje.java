package principal.modelo.personajes;

import principal.recursos.Raza;
import principal.recursos.Tipo;

/** esta es la clase personaje de la cual van a heredar el resto de clases de los diferentes tipos de personajes */

public class Personaje  {

    protected String nombre;
    protected int puntosVida;
    protected int puntosArmadura;
    protected int puntosAtaque;
    /** los atributos Tipo y Raza son parametros que se usaran concretamente en la creacion de personajes por parte del
     * usuario y asi poder especificar en que bando ira cada personaje
     */
    protected Tipo tipo;
    protected Raza raza;

    /** creo el constructores el estandar para crear los personajes ya dados en el ejemplo */
    public Personaje(String nombre, int puntosVida, int puntosArmadura) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.puntosArmadura = puntosArmadura;
    }

    /** este constructor lo creo para crear los personajes por parte del usuario donde se especifica segun la raza que se
     * indique a que bando va a pertener el personaje creado
     */
    public Personaje(String nombre, Raza raza, int puntosVida, int puntosArmadura) {
        this.nombre = nombre;
        this.raza = raza;
        this.puntosVida = puntosVida;
        this.puntosArmadura = puntosArmadura;
        if (raza == Raza.ELFO || raza == Raza.HUMANO || raza == Raza.HOBBIT || raza == Raza.MAGO) {
            tipo = Tipo.HEROE;
        } else {
            tipo = Tipo.BESTIA;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {this.nombre = nombre; }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public int getPuntosArmadura() {
        return puntosArmadura;
    }

    public void setPuntosArmadura(int puntosArmadura) {
        this.puntosArmadura = puntosArmadura;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }

    public Tipo getType() {
        return tipo;
    }

    public void setType() {this.tipo = tipo;}

    public Raza getRaza() { return raza;}

    @Override
    public String toString() {
        return getNombre() + " " + getRaza() + " " + getPuntosVida() + " " + getPuntosArmadura();
    }
}
