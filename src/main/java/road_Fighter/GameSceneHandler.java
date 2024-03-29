package road_Fighter;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Shape;
import roadFighter_interfaces.Collidator;
import roadFighter_interfaces.Collideable;
import road_Fighter.objects.AutoJugador;
import road_Fighter.objects.Background;
import road_Fighter.objects.GuardaRailDerecha;
import road_Fighter.objects.GuardaRailIzquierda;
import road_Fighter.objects.Habilidad;
import road_Fighter.objects.Meta;
import road_Fighter.objects.ObjetoBuilder;
import road_Fighter.objects.Pista;
import road_Fighter.objects.Score;
import road_Figther.utils.GameObjectBuilder;

public class GameSceneHandler extends SceneHandler {
	private Background background;
	private Pista pista;
	private AutoJugador autoJugador;
	private ObjetoBuilder objetoBuilder;
	private Meta meta;
	private Score score;
	private Habilidad habilidad;
	private GuardaRailDerecha gRd;
	private GuardaRailIzquierda gRi;

	private AudioClip audioCarrera;

	boolean started = false;
	boolean ended = false;

	public GameSceneHandler(RoadFighter g) {
		super(g);
	}

	protected void prepareScene() {
		Group rootGroup = new Group();
		scene = new Scene(rootGroup, Config.anchoBase, Config.altoBase);
	}

	protected void defineEventHandlers() {
		keyEventHandlerPressed = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {

				case W:
				case UP:
					autoJugador.setAcelerando(true);
					pista.setMovArriba(true);
					meta.setMovArriba(true);
					break;
				case DOWN:
					autoJugador.setFrenando(true);
					pista.setMovAbajo(true);
					meta.setMovAbajo(true);
					break;
				case LEFT:
				case A:
					autoJugador.setMoviendoIzquierda(true);
					break;
				case RIGHT:
				case D:
					autoJugador.setMoviendoDerecha(true);
					break;
				case SPACE:
					if (!autoJugador.isActiva()) {
						if ((autoJugador.getCantidadHabilidadesActivas()) > 0) {
							autoJugador.setEstaActiva(true);
						}
					}
				default:
					break;
				}
			}

		};

		keyEventHandlerReleased = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {

				case W:
				case UP:
					autoJugador.setAcelerando(false);
					pista.setMovArriba(false);
					meta.setMovArriba(false);
					break;
				case DOWN:
					autoJugador.setFrenando(false);
					pista.setMovAbajo(false);
					meta.setMovAbajo(false);
					break;
				case LEFT:
				case A:
					autoJugador.setMoviendoIzquierda(false);
					break;
				case RIGHT:
				case D:
					autoJugador.setMoviendoDerecha(false);
					break;
				default:
					break;
				}
			}
		};
	}

	public void load(boolean fullStart) {
		Group rootGroup = new Group();
		this.scene.setRoot(rootGroup);

		this.background = new Background("file:src/main/resources/img/bg3.png");
		this.score = new Score();
		this.pista = new Pista();
		this.habilidad = new Habilidad();
		this.autoJugador = new AutoJugador(540, 720, this.score, this.habilidad);
		this.objetoBuilder = new ObjetoBuilder();
		this.meta = new Meta();
		this.gRd = new GuardaRailDerecha(Config.limiteDerecha + 15, 0);
		this.gRi = new GuardaRailIzquierda(Config.limiteIzquierda - 25, -300);

		this.audioCarrera = new AudioClip("file:src/main/resources/sounds/backMusic.mp3");

		this.audioCarrera.play();

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(rootGroup);
		gameOB.add(this.background, this.pista, this.autoJugador, this.objetoBuilder, this.meta, this.score, this.gRi,
				this.gRd, this.habilidad);// ,this.autoJugadorTest,this.scoreTest,this.habilidadTest);

		addTimeEventsAnimationTimer();
		addInputEvents();
	}

	public void update(double delta) {
		super.update(delta);
		checkColliders();

		if (this.autoJugador.getTermino()) {
			this.audioCarrera.stop();
			juego.endGameScreen();
		}
	}

	private void checkColliders() {
		List<Collidator> collidators = GameObjectBuilder.getInstance().getCollidators();
		List<Collideable> collideables = GameObjectBuilder.getInstance().getCollideables();

		for (int i = 0; i < collidators.size(); i++) {
			Collidator collidator = collidators.get(i);
			for (int j = i + 1; j < collidators.size(); j++) {
				Collidator otherCollidator = collidators.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), otherCollidator.getCollider());
				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.collide(otherCollidator);
					otherCollidator.collide(collidator);
				}
			}

			for (int j = 0; j < collideables.size(); j++) {
				Collideable collideable = collideables.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), collideable.getCollider());

				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.collide(collideable);
				} else {
					Bounds collideableBounds = collideable.getCollider().getBoundsInLocal();
					Bounds collidatorBounds = collidator.getCollider().getBoundsInLocal();
					if (collideableBounds.contains(collidatorBounds.getCenterX(), collidatorBounds.getCenterY())) {
						collidator.collide(collideable);
					}
				}
			}
		}
	}

	public Score getScore() {
		return score;
	}
}