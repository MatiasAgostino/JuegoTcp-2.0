package entidades;

import coordenada.Coordenada;

public abstract class Entidad {
	protected boolean estaVivo;
	protected Coordenada coordenada;
	
	public Entidad() {}
	
	public Entidad(double posX, double posY) {
		coordenada = new Coordenada(posX, posY);
	}

	protected abstract void recibirContactoDe(AutoJugador autoJugador);
}

