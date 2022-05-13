package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import entidades.AutoJugador;
import entidades.Mapa;
import partida.Partida;

public class TestPartida {

	@Test
	public void ganadores() {
		ArrayList<AutoJugador> jugadores = new ArrayList<AutoJugador>(3);

		AutoJugador j1 = new AutoJugador(5, 10, 0);
		AutoJugador j2 = new AutoJugador(3, 8, 0);
		AutoJugador j3 = new AutoJugador(1, 6, 0);

		jugadores.add(j1);
		jugadores.add(j2);
		jugadores.add(j3);

		Mapa mapa = new Mapa(null, jugadores, null, null);

		mapa.alinearJugadores();

		Partida partida = new Partida(mapa, jugadores);

		ArrayList<AutoJugador> esperado = new ArrayList<AutoJugador>(3);

		esperado.add(j1);
		esperado.add(j2);
		esperado.add(j3);

		ArrayList<AutoJugador> actual = partida.iniciarCarrera();

		assertEquals(true, esperado.equals(actual));
	}

	@Test
	public void finalizoPartida() {
		ArrayList<AutoJugador> jugadores = new ArrayList<AutoJugador>(3);

		AutoJugador j2 = new AutoJugador(5, 12, 0);
		AutoJugador j1 = new AutoJugador(7, 20, 0);
		AutoJugador j3 = new AutoJugador(1, 6, 0);

		jugadores.add(j1);
		jugadores.add(j2);
		jugadores.add(j3);

		Mapa mapa = new Mapa(null, jugadores, null, null);

		mapa.alinearJugadores();

		Partida partida = new Partida(mapa, jugadores);

		partida.iniciarCarrera();

		assertEquals(true, partida.finalizaCarrera());
	}
}