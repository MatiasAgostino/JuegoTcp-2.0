package entidades;

import coordenada.Coordenada;

public class AutoJugador extends Auto {
	private String username;
	private int score;
	private int aumentadorScore;
	private boolean puedeMoverse;
	private final double VELOCIDAD_X = 1.5;

	public AutoJugador(double aceleracion, double velocidadInicial, double velocidadMaxima) {
		this.aceleracion = aceleracion;
		this.velocidadActual = velocidadInicial;
		this.velocidadMax = velocidadMaxima;
		this.estaVivo = true;
		this.puedeMoverse = true;
	}

	@Override
	public boolean moverse(boolean movIzquierda, boolean movDerecha, boolean movArriba, boolean movAbajo) {
		// Momentaneo hasta que se agreguen los graficos
		if (!this.puedeMoverse) {
			this.puedeMoverse = true;

			return false;
		}

		if (movIzquierda) {
			if (this.coordenada.getX() - this.VELOCIDAD_X > 0)
				this.coordenada.setX(this.coordenada.getX() - this.VELOCIDAD_X);
			else
				this.estaMuerto();
		}

		if (movDerecha) {
			if (this.coordenada.getX() + this.VELOCIDAD_X < 8)
				this.coordenada.setX(this.coordenada.getX() + this.VELOCIDAD_X);
			else
				this.estaMuerto();
		}

		if (movArriba) {
			this.aumentadorScore += 1;
			this.aumentarScore();

			if (this.velocidadActual + this.aceleracion <= this.velocidadMax) {
				this.velocidadActual += this.aceleracion;
				this.coordenada.setY(this.coordenada.getY() + this.velocidadActual);
			} else {
				this.velocidadActual = this.velocidadMax;
				this.coordenada.setY(this.coordenada.getY() + this.velocidadActual);
			}
		}

		if (movAbajo || !movArriba) {
			// Frenado
			if (this.aumentadorScore - 1 > 0)
				this.aumentadorScore -= 1;
			else
				this.aumentadorScore = 0;

			this.velocidadActual -= this.aceleracion;
		}

		return true;
	}

	public void contactar(AutoNpc npc) {
		if (npc.coordenada.getX() < this.coordenada.getX()) {
			if (this.coordenada.getX() + 2 < 8) {
				// Efecto de derrape
				this.puedeMoverse = false;
				this.coordenada.setX(this.coordenada.getX() + 2);
			} else {
				// Efecto de derrape
				this.estaMuerto();
			}
		}

		if (npc.coordenada.getX() > this.coordenada.getX()) {
			if (this.coordenada.getX() - 2 > 0) {
				// Efecto de derrape
				this.puedeMoverse = false;
				this.coordenada.setX(this.coordenada.getX() - 2);
			} else {
				// Efecto de derrape
				this.estaMuerto();
			}
		}

		npc.recibirContactoDe(this);
	}

	public void contactar(AutoJugador otroJugador) {
		if (otroJugador.coordenada.getX() < this.coordenada.getX()) {
			if (this.coordenada.getX() + 2 < 8) {
				// Efecto de derrape
				this.puedeMoverse = false;
				this.coordenada.setX(this.coordenada.getX() + 2);
			} else {
				// Efecto de derrape
				this.estaMuerto();
			}
		}

		if (otroJugador.coordenada.getX() > this.coordenada.getX()) {
			if (this.coordenada.getX() - 2 > 0) {
				// Efecto de derrape
				this.puedeMoverse = false;
				this.coordenada.setX(this.coordenada.getX() - 2);
			} else {
				// Efecto de derrape
				this.estaMuerto();
			}
		}

		otroJugador.recibirContactoDe(this);
	}

	public void contactar(Obstaculo obstaculo) {
		if (this.coordenada.getX() + obstaculo.getDesplazamiento() < 8) {
			// Efecto de derrape
			this.coordenada.setX(this.coordenada.getX() + obstaculo.getDesplazamiento());
		} else {
			// Efecto de derrape
			this.estaMuerto();
		}

		obstaculo.recibirContactoDe(this);
	}

	public void contactar(PowerUp powerUp) {
		this.velocidadMax += powerUp.getPower();
		this.velocidadActual = this.velocidadMax;

		powerUp.recibirContactoDe(this);
	}

	@Override
	public void recibirContactoDe(AutoJugador chocador) {
		if (chocador.coordenada.getX() < this.coordenada.getX()) {
			if (this.coordenada.getX() + 2 < 8) {
				// Efecto de derrape
				this.puedeMoverse = false;
				this.coordenada.setX(this.coordenada.getX() + 2);
			} else {
				// Efecto de derrape
				this.estaMuerto();
			}
		}

		if (chocador.coordenada.getX() > this.coordenada.getX()) {
			if (this.coordenada.getX() - 2 > 0) {
				// Efecto de derrape
				this.puedeMoverse = false;
				this.coordenada.setX(this.coordenada.getX() - 2);
			} else {
				// Efecto de derrape
				this.estaMuerto();
			}
		}
	}

	private void estaMuerto() {
		this.choqueConMapa();
		this.score = 0;
	}

	public void aumentarScore() {
		this.score += 5 + (this.aumentadorScore * 5);
	}

	public boolean respawnear(Coordenada coordenada) {
		if (!this.estaVivo) {
			this.estaVivo = true;
			this.coordenada = coordenada;

			return true;
		}

		return false;
	}

	public double getVelocidadActual() {
		return velocidadActual;
	}

	public void setVelocidadActual(double velocidad) {
		velocidadActual = velocidad;
	}
}