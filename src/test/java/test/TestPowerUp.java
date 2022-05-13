package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import entidades.AutoJugador;
import entidades.PowerUp;

public class TestPowerUp {

	@Test
	public void test() {
		AutoJugador jugador = new AutoJugador(5, 50, 2);
		PowerUp powerUp = new PowerUp(0, 0);

		jugador.contactar(powerUp);

		boolean resultado = powerUp.getEstado();
		boolean esperado = false;

		assertEquals(esperado, resultado);
	}

}