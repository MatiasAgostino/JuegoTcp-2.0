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

	public Mapa(ArrayList<AutoNpc> autosNpc, ArrayList<AutoJugador> autosJugadores, ArrayList<PowerUp> powerUps) {
		super();
		this.posInicioCarrera = new Coordenada(0, 0);
		this.posFinCarrera = new Coordenada(0, 10000);
		this.anchoMax = 6;
		this.autosNpc = autosNpc;
		this.autosJugadores = autosJugadores;
		this.powerUps = powerUps;
		limiteIzq = 0;
		limiteDer = 7;
	}

	public void alinearJugadores() {

		int i = 1;

		for (AutoJugador autoJugador : autosJugadores) {

			autoJugador.coordenada.setX(i);
			autoJugador.coordenada.setY(0);
			i++;
		}
	}

	public void alinearPowerUps() {
		Random random = new Random();

		for (PowerUp powerUp : powerUps) {

			powerUp.coordenada.setX(random.nextInt(anchoMax) + 1); /// crea un random de 1 a 6
			powerUp.coordenada.setY(random.nextDouble(posFinCarrera.getY() - 500) + 100); // crea un random desde 100
																							// hasta 9599
		}
	}

	public void alinearNpc() {
		Random random = new Random();

		for (AutoNpc autoNpc : autosNpc) {

			autoNpc.coordenada.setX(random.nextInt(anchoMax) + 1); // crea un random de 1 a 6
			autoNpc.coordenada.setY(random.nextDouble(posFinCarrera.getY() - 500) + 100); 	// crea un random desde 100
																							// hasta 9599
		}

		int i = 100;
		int autosCadaY = (int) posFinCarrera.getY() / autosNpc.size();
		for (AutoNpc autoNpc : autosNpc) {

			autoNpc.coordenada.setX(random.nextInt(anchoMax) + 1); /// crea un random de 1 a 6
			autoNpc.coordenada.setY(random.nextDouble(i) + 100); // crea un auto random desde i hasta i+100

			i += autosCadaY; // para destribuir los npc y que no aparezcan 6 np en la misma fila
		}
	}
}