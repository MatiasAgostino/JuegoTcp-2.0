package entidades;

import java.util.ArrayList;
import java.util.Random;
import coordenada.Coordenada;

public class Mapa {
	private Coordenada posInicioCarrera;
	private Coordenada posFinCarrera;
	private int anchoMax;
	private int limiteIzq;
	private int limiteDer;
	private ArrayList<AutoNpc> autosNpc;
	private ArrayList<AutoJugador> autosJugadores;
	private ArrayList<PowerUp> powerUps;
	private ArrayList<Obstaculo> obstaculos;

	public Mapa(ArrayList<AutoNpc> autosNpc, ArrayList<AutoJugador> autosJugadores, ArrayList<PowerUp> powerUps,
			ArrayList<Obstaculo> obstaculos) {
		super();
		this.posInicioCarrera = new Coordenada(0, 0);
		this.posFinCarrera = new Coordenada(0, 100);
		this.anchoMax = 6;
		this.autosNpc = autosNpc;
		this.autosJugadores = autosJugadores;
		this.powerUps = powerUps;
		this.obstaculos = obstaculos;
		limiteIzq = 0;
		limiteDer = 7;
	}

	public void alinearJugadores() {
		int i = 1;
		for (AutoJugador autoJugador : autosJugadores) {
			autoJugador.coordenada.setX(i);
			autoJugador.setPosMeta(this.posFinCarrera.getY());
			autoJugador.coordenada.setY(this.posInicioCarrera.getY());
			i++;
		}
	}

	public int alinearPowerUps() {
		Random random = new Random();
		int i = 0;
		for (PowerUp powerUp : powerUps) {
			powerUp.coordenada.setX(random.nextInt(anchoMax) + 1); /// crea un random de 1 a 6
			powerUp.coordenada.setY(random.nextDouble(posFinCarrera.getY() - 500) + 100); // crea un random desde 100
																							// hasta 9599
			i++;
		}

		return i;
	}

	public int alinearObstaculos() {
		Random random = new Random();
		int i = 0;
		for (Obstaculo obstaculo : obstaculos) {
			obstaculo.coordenada.setX(random.nextInt(anchoMax) + 1); /// crea un random de 1 a 6
			obstaculo.coordenada.setY(random.nextDouble(posFinCarrera.getY() - 500) + 100); // crea un random desde 100
																							// hasta 9599
			i++;
		}
		return i;
	}

	public int alinearNpc() {
		Random random = new Random();
		int j = 0;
		int i = 100;
		int autosCadaY = (int) posFinCarrera.getY() / autosNpc.size();
		for (AutoNpc autoNpc : autosNpc) {
			autoNpc.coordenada.setX(random.nextInt(anchoMax) + 1); /// crea un random de 1 a 6
			autoNpc.coordenada.setY(random.nextDouble(i) + 100); // crea un auto random desde i hasta i+100
			i += autosCadaY; // para destribuir los npc y que no aparezcan 6 np en la misma fila

			j++;
		}

		return j;
	}

	public Coordenada getPosFin() {
		return this.posFinCarrera;
	}
}