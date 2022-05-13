package partida;

import java.util.ArrayList;

import entidades.*;

public class Partida {
	private Mapa mapa;
	private AutoJugador autoJugador;
	private ArrayList<AutoJugador> autosJugadores;
	private ArrayList<AutoNpc> autosNpcs;
	private ArrayList<AutoJugador> podio;
	private ArrayList<PowerUp> powerUps;
	private ArrayList<Obstaculo> obstaculos;

	public static void main(String[] args) {
		ArrayList<AutoJugador> jugadores = new ArrayList<AutoJugador>(3);

		AutoJugador j1 = new AutoJugador(4, 6, 0);
		AutoJugador j2 = new AutoJugador(20, 20, 0);
		AutoJugador j3 = new AutoJugador(1, 2, 0);

		jugadores.add(j1);
		jugadores.add(j2);
		jugadores.add(j3);

		Mapa mapa = new Mapa(null, jugadores, null, null);

		mapa.alinearJugadores();

		Partida partida = new Partida(mapa, jugadores);

		ArrayList<AutoJugador> actual = partida.iniciarCarrera();

		System.out.println(actual);
	}

	public Partida(Mapa mapa, ArrayList<AutoJugador> autosJugadores) {
		this.mapa = mapa;
		this.autosJugadores = autosJugadores;
		this.podio = new ArrayList<AutoJugador>();
	}

	public ArrayList<AutoJugador> iniciarCarrera() {
		ArrayList<AutoJugador> copia = new ArrayList<AutoJugador>(this.autosJugadores);

		while (this.podio.size() < this.autosJugadores.size()) {
			for (int i = 0; i < copia.size(); i++) {
				copia.get(i).moverse(false, false, true, false);

				if (copia.get(i).finalizo()) {
					this.podio.add(copia.get(i));
					copia.remove(i);
				}
			}
		}

		return this.podio;
	}

	public boolean finalizaCarrera() {
		return this.autosJugadores.size() == this.podio.size();
	}
}