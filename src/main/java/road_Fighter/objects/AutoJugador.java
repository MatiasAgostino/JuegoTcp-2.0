package road_Fighter.objects;

import javafx.animation.Animation;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import roadFighter_interfaces.Collidator;
import roadFighter_interfaces.Collideable;
import roadFighter_interfaces.Renderable;
import roadFighter_interfaces.Updatable;
import road_Fighter.Config;
import road_Figther.utils.IndividualSpriteAnimation;

public class AutoJugador extends Auto implements Updatable, Renderable, Collidator {
	private final int HEIGHT = 50;
	private final int WIDTH = 50;

	private String username;

	private boolean acelerando;
	private boolean frenando;
	private boolean choco;

	private boolean moviendoDerecha;
	private boolean moviendoIzquierda;

	private double velocidadActualY;
	private double velocidadActualX;

	private Score score;
	private Habilidad habilidad;

	private Image imagenBase;
	private Image imagenAbajo;
	private Image imagenIzquierda;
	private Image imagenDerecha;
	private Image explosionBase;
	private Image explosionMedia;
	private Image explosionAvanzada;
	private Image explosionFinal;
	private Image imagenActiva;

	private ImageView render;

	private Rectangle collider;

	private final double colliderTolerance = 0.75;
	private final int colliderWidth = (int) (WIDTH * colliderTolerance);
	private final int colliderHeight = (int) (HEIGHT * colliderTolerance);
	private IndividualSpriteAnimation autoAnimacion;
	private IndividualSpriteAnimation explosionAnimacion;

	private int tiempoDeGiro;
	private int framesPowerUp;
	private int direccionDeChoque;
	private int tiempoDeExplosion;

	private boolean estaActiva = false;
	private boolean termino;
	private int direccionDeExplosion;

	public AutoJugador(double x, double y, Score score, Habilidad habilidad) {
		super(x, y);
		this.estaVivo = true;
		this.velocidadActualX = 0.0;
		this.velocidadActualY = 0.0;
		this.score = score;
		this.habilidad = habilidad;
		this.termino = false;

		this.imagenBase = new Image("file:src/main/resources/img/car-player.png", WIDTH, HEIGHT, false, false);
		this.imagenAbajo = new Image("file:src/main/resources/img/car-player-downTurn.png", WIDTH, HEIGHT, false,
				false);
		this.imagenDerecha = new Image("file:src/main/resources/img/car-player-rightTurn.png", WIDTH, HEIGHT, false,
				false);
		this.imagenIzquierda = new Image("file:src/main/resources/img/car-player-leftTurn.png", WIDTH, HEIGHT, false,
				false);

		this.explosionBase = new Image("file:src/main/resources/img/e1.png", WIDTH, HEIGHT, false, false);
		this.explosionMedia = new Image("file:src/main/resources/img/e2.png", WIDTH, HEIGHT, false, false);
		this.explosionAvanzada = new Image("file:src/main/resources/img/e3.png", WIDTH, HEIGHT, false, false);
		this.explosionFinal = new Image("file:src/main/resources/img/e4.png", WIDTH, HEIGHT, false, false);
		this.imagenActiva = new Image("file:src/main/resources/img/car-Activa.png", WIDTH, HEIGHT, false, false);

		this.render = new ImageView(imagenBase);

		this.render.relocate(-this.WIDTH / 2, -this.HEIGHT);
		this.render.setX(this.x);
		this.render.setY(this.y);

		this.collider = new Rectangle(this.x - colliderWidth / 2, this.y - colliderHeight / 2, colliderWidth,
				colliderHeight);

		this.autoAnimacion = iniciarAnimacionAuto();
		this.explosionAnimacion = iniciarExplosionAuto();
	}

	private IndividualSpriteAnimation iniciarAnimacionAuto() {
		IndividualSpriteAnimation individualSpriteAnimation = new IndividualSpriteAnimation(
				new Image[] { imagenBase, imagenAbajo, imagenIzquierda, imagenDerecha }, render, Duration.millis(1000));
		individualSpriteAnimation.setCustomFrames(new int[] { 0, 1, 2, 1, 1 });
		individualSpriteAnimation.setCycleCount(Animation.INDEFINITE);
		return individualSpriteAnimation;
	}

	private IndividualSpriteAnimation iniciarExplosionAuto() {
		IndividualSpriteAnimation individualSpriteAnimation = new IndividualSpriteAnimation(
				new Image[] { explosionBase, explosionMedia, explosionAvanzada, explosionFinal }, render,
				Duration.millis(1000));
		individualSpriteAnimation.setCustomFrames(new int[] { 0, 1, 2, 1, 1 });
		individualSpriteAnimation.setCycleCount(Animation.INDEFINITE);
		return individualSpriteAnimation;
	}

	public void setMoviendoDerecha(boolean moviendoDerecha) {
		this.moviendoDerecha = moviendoDerecha;
	}

	public void setMoviendoIzquierda(boolean moviendoIzquierda) {
		this.moviendoIzquierda = moviendoIzquierda;
	}

	public void setAcelerando(boolean acelerando) {
		this.acelerando = acelerando;
	}

	public void setFrenando(boolean frenando) {
		this.frenando = frenando;
	}

	@Override
	public void update(double deltaTime) {

		if (this.estaVivo && !this.choco) {
			if (this.acelerando) {
				if (this.velocidadActualY + (deltaTime * Config.aceleracionBaseAuto) > Config.velocidadMaxAutoY) {
					this.velocidadActualY = Config.velocidadMaxAutoY;
				} else {
					this.velocidadActualY += (deltaTime * Config.aceleracionBaseAuto);
				}

				if (this.velocidadActualX + (deltaTime * Config.aceleracionBaseAuto) > Config.velocidadMaxAutoX) {
					this.velocidadActualX = Config.velocidadMaxAutoX;
				} else {
					this.velocidadActualX += (deltaTime * Config.aceleracionBaseAuto);
				}
				this.score.increase(this.velocidadActualX);
			} else {
				if (this.velocidadActualY + (deltaTime * -Config.aceleracionBaseAuto) < 0)
					this.velocidadActualY = 0;
				else
					this.velocidadActualY -= deltaTime * Config.aceleracionBaseAuto;
				if (this.frenando)
					this.velocidadActualY *= 0.3;
				if (this.velocidadActualX + (deltaTime * -Config.aceleracionBaseAuto) < 0)
					this.velocidadActualX = 0;
				else
					this.velocidadActualX -= deltaTime * Config.aceleracionBaseAuto;
				if (this.frenando)
					this.velocidadActualX *= 0.3;
			}

			int direccionX = this.moviendoIzquierda ? -1 : (this.moviendoDerecha ? 1 : 0),
					direccionY = this.acelerando ? 1 : (this.frenando ? -1 : 0);
			double x = (this.x + (direccionX * deltaTime * this.velocidadActualX)),
					y = (this.y - (direccionY * deltaTime * this.velocidadActualY));

			if (this.framesPowerUp > 0) {
				this.framesPowerUp--;
			} else {
				this.framesPowerUp = 0;
				this.velocidadActual = this.velocidadActualY;
				Config.velocidadBaseDinamica = Config.velocidadBase;
			}

			this.setX(x);
			this.setY(y);

		} else if (this.estaVivo && this.choco) {
			if (this.tiempoDeGiro > 0) {
				this.tiempoDeGiro--;
				this.setX(this.x + (this.direccionDeChoque * deltaTime * 20));
			} else {
				this.tiempoDeGiro = 0;
				this.autoAnimacion.stop();
				this.choco = false;
				this.render.setImage(imagenBase);
			}
		} else if (!this.estaVivo) {
			if (this.tiempoDeExplosion > 0) {
				this.tiempoDeExplosion--;
				this.setX(this.x + (this.direccionDeExplosion * deltaTime * 20));
			} else {
				this.tiempoDeExplosion = 0;
				this.explosionAnimacion.stop();
				this.render.setImage(imagenBase);
				this.estaVivo = true;
			}
		}
	}

	private void setX(double x) {
		x = x <= Config.limiteIzquierda ? Config.limiteIzquierda
				: (x > Config.limiteDerecha ? Config.limiteDerecha : x);

		if (this.velocidadActualY > 0) {
			this.x = x;
			this.render.setX(x);
			this.collider.setX(this.x);
		}
	}

	private void setY(double y) {
		y = y <= Config.limiteSuperior ? Config.limiteSuperior
				: (y >= Config.limiteInferior ? Config.limiteInferior : y);
		this.y = y;
		this.render.setY(y);
		this.collider.setY(this.y);
	}

	@Override
	public Node getRender() {
		return this.render;
	}

	@Override
	public void destroy() {
	}

	@Override
	public Shape getCollider() {
		return this.collider;
	}

	@Override
	public void collide(Collideable collideable) {

		if (collideable.getClass() == PowerUp.class) {

			this.framesPowerUp = Config.framesPowerUp;
			Config.velocidadBaseDinamica = Config.velocidadMaxPowerUp;

		} else if (collideable.getClass() == Meta.class) {

			this.termino = true;

		} else if (!this.isActiva()) {

			if (collideable.getClass() == Obstaculo.class) {
				this.choco = true;
				this.tiempoDeGiro = 100;

				int signo = -1;

				if (this.render.getX() > ((Obstaculo) collideable).getX())
					signo = 1;

				this.direccionDeChoque = signo;
				this.autoAnimacion.play();

			} else if (collideable.getClass() == AutoNpc.class) {

				this.choco = true;
				this.tiempoDeGiro = 100;

				int signo = -1;
				if (this.render.getX() > ((AutoNpc) collideable).getX())
					signo = 1;

				this.direccionDeChoque = signo;
				this.autoAnimacion.play();

			} else if (collideable.getClass() == AutoJugador.class
					|| collideable.getClass() == GuardaRailDerecha.class) {
				this.direccionDeExplosion = -1;
				this.explosionAnimacion.play();
				this.tiempoDeExplosion = 100;
				this.estaVivo = false;
			} else if (collideable.getClass() == GuardaRailIzquierda.class) {
				this.direccionDeExplosion = 1;
				this.explosionAnimacion.play();
				this.tiempoDeExplosion = 100;
				this.estaVivo = false;
			}
		} else {
			if (this.getX() < Config.anchoBase / 2) {
				this.setX(x + 80);
			} else
				this.setX(x - 80);
			this.estaActiva = false;
			render.setImage(imagenBase);
		}
	}

	public boolean isActiva() {
		return estaActiva;
	}

	public void setEstaActiva(boolean estaActiva) {
		this.estaActiva = true;
		render.setImage(imagenActiva);

		this.habilidad.decrease();
	}

	public int getCantidadHabilidadesActivas() {
		return this.habilidad.getHabilidad();
	}

	public boolean getTermino() {
		return this.termino;
	}
}