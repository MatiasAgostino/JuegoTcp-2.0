package entidades;

import coordenada.Coordenada;

public abstract class Entidad {
	protected boolean estaVivo;
	protected Coordenada coordenada;

	public Entidad() {
		this.coordenada = new Coordenada(0, 0);
	}

	public Entidad(double posX, double posY) {
		coordenada = new Coordenada(posX, posY);
	}

	public boolean getEstado() {
		return this.estaVivo;
	}

	public Coordenada getCoordenada() {
		return this.coordenada;
	}

	protected abstract void recibirContactoDe(AutoJugador autoJugador);
}