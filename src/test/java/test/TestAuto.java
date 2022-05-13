package test;

import static org.junit.Assert.*;

import org.junit.Test;

import coordenada.Coordenada;
import entidades.*;

public class TestAuto {

	@Test
	public void moverseDerecha() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.setPosicion(3, 3);

		jugador.moverse(false, true, false, false);

		Double esperado = 4.5;
		Double salida = jugador.getCoordenada().getX();

		assertEquals(esperado, salida);
	}

	@Test
	public void moverseIzquierda() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.setPosicion(3, 3);

		jugador.moverse(true, false, false, false);

		Double esperado = 1.5;
		Double salida = jugador.getCoordenada().getX();

		assertEquals(esperado, salida);
	}

	@Test
	public void aceleraUnaVez() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.moverse(false, false, true, false);

		Double esperado = 5.0;
		Double salida = jugador.getCoordenada().getY();

		assertEquals(esperado, salida);
	}

	@Test
	public void aceleraDosVeces() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, true, false);

		Double esperado = 15.0;
		Double salida = jugador.getCoordenada().getY();

		assertEquals(esperado, salida);
	}

	@Test
	public void seMueveIzqDer() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.setPosicion(3, 3);

		jugador.moverse(true, true, false, false);

		Coordenada posEsperada = new Coordenada(3, 3);
		Coordenada posActual = jugador.getCoordenada();

		assertEquals(posEsperada, posActual);
	}

	@Test
	public void seMueveArribaDer() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.setPosicion(3, 3);

		jugador.moverse(false, true, true, false);

		Coordenada posEsperada = new Coordenada(4.5, 8);
		Coordenada posActual = jugador.getCoordenada();

		assertEquals(posEsperada, posActual);
	}

	@Test
	public void seMueveArribaIzq() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.setPosicion(3, 3);

		jugador.moverse(true, false, true, false);

		Coordenada posEsperada = new Coordenada(1.5, 8);
		Coordenada posActual = jugador.getCoordenada();

		assertEquals(posEsperada, posActual);
	}

	@Test
	public void seMueveArribaAbajo() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.setPosicion(3, 3);

		jugador.moverse(false, false, true, true);
		jugador.moverse(false, false, true, false);

		Coordenada posEsperada = new Coordenada(3, 13);
		Coordenada posActual = jugador.getCoordenada();

		assertEquals(posEsperada, posActual);
	}

	@Test
	public void frena() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, false, true);

		Double esperado = 10.0;
		Double salida = jugador.getVelocidadActual();

		assertEquals(esperado, salida);
	}

	@Test
	public void seDetiene() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, false, true);
		jugador.moverse(false, false, false, true);
		jugador.moverse(false, false, false, true);
		jugador.moverse(false, false, false, true);

		Double esperado = 0.0;
		Double salida = jugador.getVelocidadActual();

		assertEquals(esperado, salida);
	}

	@Test
	public void chocaConNpcIzq() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		AutoNpc npc = new AutoNpc(new Coordenada(4, 4), 10);

		jugador.setPosicion(3, 3);
		jugador.contactar(npc);

		Coordenada esperada = new Coordenada(1, 3);
		Coordenada posActual = jugador.getCoordenada();

		assertEquals(esperada, posActual);
	}

	@Test
	public void chocaConNpcDer() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		AutoNpc npc = new AutoNpc(new Coordenada(2, 4), 10);

		jugador.setPosicion(3, 3);
		jugador.contactar(npc);

		Coordenada esperada = new Coordenada(5, 3);
		Coordenada posActual = jugador.getCoordenada();

		assertEquals(esperada, posActual);
	}

	@Test
	public void npcChocaConAutoDer() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		AutoNpc npc = new AutoNpc(new Coordenada(4, 4), 10);

		jugador.setPosicion(3, 3);
		jugador.contactar(npc);

		Coordenada esperada = new Coordenada(6, 4);
		Coordenada posActual = npc.getCoordenada();

		assertEquals(esperada, posActual);
	}

	@Test
	public void npcChocaConAutoIzq() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		AutoNpc npc = new AutoNpc(new Coordenada(4, 4), 10);

		jugador.setPosicion(5, 3);
		jugador.contactar(npc);

		Coordenada esperada = new Coordenada(2, 4);
		Coordenada posActual = npc.getCoordenada();

		assertEquals(esperada, posActual);
	}

	@Test
	public void choqueConMapaDer() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.setPosicion(4, 4);
		jugador.moverse(false, true, false, false);
		jugador.moverse(false, true, false, false);

		assertEquals(false, jugador.getEstado());
	}

	@Test
	public void choqueConMapaIzq() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.setPosicion(2, 4);
		jugador.moverse(true, false, false, false);
		jugador.moverse(true, false, false, false);

		assertEquals(false, jugador.getEstado());
	}

	@Test
	public void npcChocaConMapaIzq() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		AutoNpc npc = new AutoNpc(new Coordenada(2, 4), 10);

		jugador.setPosicion(3, 3);

		jugador.contactar(npc);

		assertEquals(false, npc.getEstado());
	}

	@Test
	public void npcChocaConMapaDer() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		AutoNpc npc = new AutoNpc(new Coordenada(5, 4), 10);

		jugador.setPosicion(4, 3);

		jugador.contactar(npc);

		assertEquals(false, npc.getEstado());
	}

	@Test
	public void chocoInmovil() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		AutoNpc npc = new AutoNpc(new Coordenada(5, 4), 10);

		jugador.setPosicion(4, 3);
		jugador.contactar(npc);

		assertEquals(false, jugador.moverse(true, false, false, false));
	}

	@Test
	public void chocoEsperoMeMuevo() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		AutoNpc npc = new AutoNpc(new Coordenada(5, 4), 10);

		jugador.setPosicion(4, 3);

		jugador.contactar(npc);
		jugador.moverse(true, false, false, false);

		assertEquals(true, jugador.moverse(true, false, false, false));
	}

	@Test
	public void chocoConObstaculo() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		Obstaculo obstaculo = new Obstaculo(3, 3);

		jugador.setPosicion(3, 3);

		jugador.contactar(obstaculo);

		assertEquals(false, jugador.getEstado());
	}

	@Test
	public void consumoPowerUpVActual() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		PowerUp powerUp = new PowerUp(3, 30);

		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, true, false);
		jugador.getCoordenada().setX(3);

		jugador.contactar(powerUp);

		Double vMax = 153.7;
		Double vActual = jugador.getVelocidadActual();

		assertEquals(vMax, vActual);
	}

	@Test
	public void consumoPowerUpVMax() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		PowerUp powerUp = new PowerUp(3, 30);

		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, true, false);
		jugador.getCoordenada().setX(3);

		jugador.contactar(powerUp);

		Double esperado = 153.7;
		Double actual = jugador.getVelocidadMaxima();

		assertEquals(esperado, actual);
	}

	@Test
	public void scoreSinFrenar() {
		AutoJugador jugador = new AutoJugador(5, 100, 3);

		for (int i = 0; i < 5; i++) {
			jugador.moverse(false, false, true, false); // 10 + 15 + 20 + 25 + 30 = 100
		}

		assertEquals(100, jugador.getScore());
	}

	@Test
	public void scoreFrenando() {
		AutoJugador jugador = new AutoJugador(5, 100, 3);

		for (int i = 0; i < 3; i++) {
			jugador.moverse(false, false, true, false); // 10 + 15 + 20 = 45
		}

		jugador.moverse(false, false, false, true); // aumentador = 2;
		jugador.moverse(false, false, false, true); // aumentador = 1
		jugador.moverse(false, false, true, false); // 15

		assertEquals(60, jugador.getScore());
	}

	@Test
	public void revive() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.setPosicion(3, 3);

		jugador.moverse(true, false, false, false);
		jugador.moverse(true, false, false, false);
		jugador.moverse(true, false, false, false);

		jugador.respawnear(new Coordenada(3, 3));

		assertEquals(true, jugador.getEstado());
	}

	@Test
	public void respawnInicializaVIni() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.setPosicion(3, 3);

		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, true, false);
		jugador.moverse(false, false, true, false);

		jugador.moverse(true, false, false, false);
		jugador.moverse(true, false, false, false);
		jugador.moverse(true, false, false, false);

		jugador.respawnear(new Coordenada(3, 3));

		Double esperado = 0.0;
		Double actual = jugador.getVelocidadActual();

		assertEquals(esperado, actual);
	}

	@Test
	public void respawnInicializaScore() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);

		jugador.setPosicion(3, 3);

		jugador.moverse(true, false, false, false);
		jugador.moverse(true, false, false, false);
		jugador.moverse(true, false, false, false);

		jugador.respawnear(new Coordenada(3, 3));

		Integer esperado = 0;
		Integer actual = jugador.getScore();

		assertEquals(esperado, actual);
	}

	@Test
	public void tengoEscudoNoMeMuevo() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		AutoNpc npc = new AutoNpc(new Coordenada(4, 4), 10);

		jugador.setPosicion(3, 3);

		jugador.activarHabilidad(true);

		jugador.contactar(npc);

		Coordenada posEsperada = new Coordenada(3, 3);
		Coordenada posActual = jugador.getCoordenada();

		assertEquals(posEsperada, posActual);
	}

	@Test
	public void choqueNpcConEscudo() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		AutoNpc npc = new AutoNpc(new Coordenada(4, 4), 10);

		jugador.setPosicion(3, 3);

		jugador.activarHabilidad(true);

		jugador.contactar(npc);

		Coordenada posEsperada = new Coordenada(6, 4);
		Coordenada posActual = npc.getCoordenada();

		assertEquals(posEsperada, posActual);
	}

	@Test
	public void choqueNpcConEscudoMiPosicion() {
		AutoJugador jugador = new AutoJugador(5, 150, 3);
		AutoNpc npc = new AutoNpc(new Coordenada(4, 4), 10);

		jugador.setPosicion(3, 3);

		jugador.activarHabilidad(true);

		jugador.contactar(npc);

		Coordenada posEsperada = new Coordenada(3, 3);
		Coordenada posActual = jugador.getCoordenada();

		assertEquals(posEsperada, posActual);
	}

	@Test
	public void finDeHabilidad() {
		AutoJugador jugador = new AutoJugador(5, 150, 1);
		AutoNpc npc = new AutoNpc(new Coordenada(4, 4), 10);

		jugador.setPosicion(3, 3);

		jugador.activarHabilidad(true);

		jugador.contactar(npc);

		assertEquals(false, jugador.activarHabilidad(true));
	}

	@Test
	public void testMovLuegoDeChoqueJugadores() {
		AutoJugador jugador = new AutoJugador(5, 100, 3);
		AutoJugador jugador2 = new AutoJugador(5, 90, 2);

		jugador.setPosicion(4, 4);
		jugador2.setPosicion(4, 5);
		jugador.contactar(jugador2);

		Boolean[] verificador = new Boolean[2];

		verificador[0] = jugador.moverse(true, true, true, false);
		verificador[1] = jugador2.moverse(true, true, true, false);

		Boolean[] esperado = new Boolean[2];

		esperado[0] = false;
		esperado[1] = false;

		assertArrayEquals(esperado, verificador);
	}

	@Test
	public void testPosLuegoDeChoqueJugadores() {
		AutoJugador jugador = new AutoJugador(5, 100, 3);
		AutoJugador jugador2 = new AutoJugador(5, 90, 2);

		jugador.setPosicion(4, 4);
		jugador2.setPosicion(3, 4);
		jugador.contactar(jugador2);

		Double[] verificador = new Double[2];

		verificador[0] = jugador.getCoordenada().getX();
		verificador[1] = jugador2.getCoordenada().getX();

		Double[] esperado = new Double[2];

		esperado[0] = 6.0;
		esperado[1] = 1.0;

		assertArrayEquals(esperado, verificador);
	}
}