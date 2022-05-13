package entidades;

public abstract class Auto extends Entidad {
	protected double aceleracion;
	protected double velocidadMax;
	protected double velocidadActual;

	protected void choqueConMapa() {
		// A agregar efectos
		this.estaVivo = false;
	}

	public abstract boolean moverse(boolean movIzquierda, boolean movDerecha, boolean movArriba, boolean movAbajo);
}
