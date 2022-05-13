package entidades;

import coordenada.Coordenada;

public class AutoJugador extends Auto {
	private String username;
	private int score;
	private double posMeta;
	private int aumentadorScore;
	private int habilidadesRestantes;
	private boolean puedeMoverse;
	private boolean habilidadActiva;
	private boolean llegoALaMeta;
	private double vMAXIMA_INCIAL;
	private final double VELOCIDAD_X = 1.5;

	public AutoJugador(double aceleracion, double velocidadMaxima, int habilidadesRestantes) {
		this.aceleracion = aceleracion;
		this.velocidadMax = velocidadMaxima;
		this.vMAXIMA_INCIAL = velocidadMaxima;
		this.estaVivo = true;
		this.puedeMoverse = true;
		this.habilidadesRestantes = habilidadesRestantes;
	}

	@Override
	public boolean moverse(boolean movIzquierda, boolean movDerecha, boolean movArriba, boolean movAbajo) {
		// Momentaneo hasta que se agreguen los graficos
		if (!this.puedeMoverse) {
			this.puedeMoverse = true;

			return false;
		}

		if (movIzquierda) {
			if (this.coordenada.getY() != 0) {
				if (this.coordenada.getX() - this.VELOCIDAD_X > 0)
					this.coordenada.setX(this.coordenada.getX() - this.VELOCIDAD_X);
				else
					this.estaMuerto();
			}
		}

		if (movDerecha) {
			if (this.coordenada.getY() != 0) {
				if (this.coordenada.getX() + this.VELOCIDAD_X < 7)
					this.coordenada.setX(this.coordenada.getX() + this.VELOCIDAD_X);
				else
					this.estaMuerto();
			}
		}

		if (movArriba) {
			this.aumentadorScore += 1;
			this.aumentarScore();

			if (this.velocidadActual + this.aceleracion <= this.velocidadMax) {
				this.velocidadActual += this.aceleracion;
				this.coordenada.setY(this.coordenada.getY() + this.velocidadActual);
				if (this.coordenada.getY() >= this.posMeta)
					this.llegoALaMeta = true;
			} else {
				this.velocidadActual = this.velocidadMax;
				this.coordenada.setY(this.coordenada.getY() + this.velocidadActual);
				if (this.coordenada.getY() >= this.posMeta)
					this.llegoALaMeta = true;
			}
		}

		if (movAbajo || !movArriba) {
			// Frenado
			if (this.aumentadorScore - 1 > 0)
				this.aumentadorScore--;
			else
				this.aumentadorScore = 0;

			if (this.velocidadActual - this.aceleracion > 0)
				this.velocidadActual -= this.aceleracion;
			else
				this.velocidadActual = 0;
		}

		return true;
	}

	public boolean activarHabilidad(boolean activacion) {
		if (this.habilidadesRestantes > 0 && !this.habilidadActiva) {
			this.habilidadActiva = true;
			this.habilidadesRestantes--;

			return true;
		}

		return false;
	}

	public boolean contactar(AutoNpc npc) {
		if (this.coordenada.getDistanciaA(npc.coordenada) < 2) {
			if (!this.habilidadActiva) {
				if (npc.coordenada.getX() < this.coordenada.getX()) {
					if (this.coordenada.getX() + 2 < 7) {
						// Efecto de derrape
						this.puedeMoverse = false;
						this.coordenada.setX(this.coordenada.getX() + 2);
					} else {
						// Efecto de derrape
						this.estaMuerto();
					}
				}

				if (npc.coordenada.getX() >= this.coordenada.getX()) {
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

			boolean choco = this.habilidadActiva;

			if (this.habilidadActiva)
				this.habilidadActiva = false;

			npc.recibirContactoDe(this);

			return choco;
		}

		return false;
	}

	public void contactar(AutoJugador otroJugador) {
		if (this.coordenada.getDistanciaA(otroJugador.coordenada) < 2) {
			if (otroJugador.coordenada.getX() <= this.coordenada.getX()) {
				if (this.coordenada.getX() + 2 < 7) {
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
	}

	public void contactar(Obstaculo obstaculo) {
		if (this.coordenada.getDistanciaA(obstaculo.coordenada) < 2) {
			if (this.coordenada.getX() > obstaculo.getCoordenada().getX()) {
				if (this.coordenada.getX() + obstaculo.getDesplazamiento() < 7) {
					// Efecto de derrape
					this.coordenada.setX(this.coordenada.getX() + obstaculo.getDesplazamiento());
				} else {
					// Efecto de derrape
					this.estaMuerto();
				}
			}

			if (this.coordenada.getX() <= obstaculo.getCoordenada().getX()) {
				if (this.coordenada.getX() - obstaculo.getDesplazamiento() > 0) {
					// Efecto de derrape
					this.coordenada.setX(this.coordenada.getX() - obstaculo.getDesplazamiento());
				} else {
					// Efecto de derrape
					this.estaMuerto();
				}
			}

			obstaculo.recibirContactoDe(this);
		}
	}

	public void contactar(PowerUp powerUp) {
		if (this.coordenada.getDistanciaA(powerUp.coordenada) < 2) {
			this.velocidadMax += powerUp.getPower();
			this.velocidadActual = this.velocidadMax;

			powerUp.recibirContactoDe(this);
		}
	}

	@Override
	public void recibirContactoDe(AutoJugador chocador) {
		if (chocador.coordenada.getX() < this.coordenada.getX()) {
			if (this.coordenada.getX() + 2 < 7) {
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

	private void setearValores() {
		this.velocidadActual = 0;
		this.velocidadMax = this.vMAXIMA_INCIAL;
		this.score = 0;
	}

	private void estaMuerto() {
		this.choqueConMapa();
		this.setearValores();
	}

	public void aumentarScore() {
		this.score += 5 + (this.aumentadorScore * 5);
	}

	public boolean respawnear(Coordenada coordenada) {
		if (!this.estaVivo) {
			this.estaVivo = true;
			this.coordenada = coordenada;
			this.setearValores();

			return true;
		}

		return false;
	}

	public double getVelocidadActual() {
		return velocidadActual;
	}

	public double getVelocidadMaxima() {
		return this.velocidadMax;
	}

	public int getScore() {
		return this.score;
	}

	public void setVelocidadActual(double velocidad) {
		velocidadActual = velocidad;
	}

	public void setPosicion(double x, double y) {
		this.coordenada.setX(x);
		this.coordenada.setY(y);
	}

	public void setPosMeta(double y) {
		this.posMeta = y;
	}

	public boolean finalizo() {
		return this.llegoALaMeta;
	}
}