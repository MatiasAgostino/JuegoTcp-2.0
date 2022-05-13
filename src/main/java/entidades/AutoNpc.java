package entidades;

import coordenada.Coordenada;

public class AutoNpc extends Auto {

	public AutoNpc(Coordenada coordenadas, int vConstante) {
		this.coordenada = coordenadas;
		this.velocidadActual = vConstante;
		this.velocidadMax = vConstante;
		this.estaVivo = coordenadas.getX() != 0;
	}

	@Override
	public boolean moverse(boolean movIzquierda, boolean movDerecha, boolean movArriba, boolean movAbajo) {
		if (movArriba) {
			this.coordenada.setY(this.coordenada.getY() + this.velocidadActual);

			return true;
		}

		return false;
	}

	@Override
	public void recibirContactoDe(AutoJugador jugador) {
		if (jugador.coordenada.getX() < this.coordenada.getX()) {
			if (this.coordenada.getX() + 2 < 7) {
				// Efecto de derrape
				this.coordenada.setX(this.coordenada.getX() + 2);
			} else {
				// Efecto de derrape
				this.choqueConMapa();
			}
		}

		if (jugador.coordenada.getX() > this.coordenada.getX()) {
			if (this.coordenada.getX() - 2 > 0) {
				// Efecto de derrape
				this.coordenada.setX(this.coordenada.getX() - 2);
			} else {
				// Efecto de derrape
				this.choqueConMapa();
			}
		}
	}
}
