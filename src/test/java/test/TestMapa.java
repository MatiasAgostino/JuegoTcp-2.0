/*
 * package test;
 * 
 * import static org.junit.Assert.assertArrayEquals; import static
 * org.junit.Assert.assertEquals;
 * 
 * import java.util.ArrayList;
 * 
 * import org.junit.Test;
 * 
 * import roadFighter.objects.AutoJugador; import roadFighter.objects.AutoNpc;
 * import roadFighter.objects.Mapa; import roadFighter.objects.Obstaculo; import
 * roadFighter.objects.PowerUp; import roadFigther.utils.Coordenada;
 * 
 * public class TestMapa {
 * 
 * private ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>(); private
 * ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>(); private
 * ArrayList<AutoNpc> autoNpc = new ArrayList<AutoNpc>(); private
 * ArrayList<AutoJugador> autoJugador = new ArrayList<AutoJugador>();
 * 
 * @Test public void testGeneracionObstaculos() { Obstaculo obstaculo = new
 * Obstaculo(0, 0); Obstaculo obstaculo2 = new Obstaculo(0, 0); Obstaculo
 * obstaculo3 = new Obstaculo(0, 0); Obstaculo obstaculo4 = new Obstaculo(0, 0);
 * Obstaculo obstaculo5 = new Obstaculo(0, 0); Obstaculo obstaculo6 = new
 * Obstaculo(0, 0);
 * 
 * obstaculos.add(obstaculo); obstaculos.add(obstaculo2);
 * obstaculos.add(obstaculo3); obstaculos.add(obstaculo4);
 * obstaculos.add(obstaculo5); obstaculos.add(obstaculo6);
 * 
 * Integer esperado = 6;
 * 
 * Mapa mapa = new Mapa(autoNpc, autoJugador, powerUps, obstaculos);
 * 
 * Integer salida = mapa.alinearObstaculos();
 * 
 * assertEquals(esperado, salida); }
 * 
 * @Test public void testGeneracionPowerUps() { PowerUp PowerUp = new PowerUp(0,
 * 0); PowerUp PowerUp2 = new PowerUp(0, 0); PowerUp PowerUp3 = new PowerUp(0,
 * 0); PowerUp PowerUp4 = new PowerUp(0, 0); PowerUp PowerUp5 = new PowerUp(0,
 * 0); PowerUp PowerUp6 = new PowerUp(0, 0);
 * 
 * powerUps.add(PowerUp); powerUps.add(PowerUp2); powerUps.add(PowerUp3);
 * powerUps.add(PowerUp4); powerUps.add(PowerUp5); powerUps.add(PowerUp6);
 * 
 * Mapa map = new Mapa(autoNpc, autoJugador, powerUps, obstaculos);
 * 
 * Integer esperado = 6; Integer actual = map.alinearPowerUps();
 * 
 * assertEquals(esperado, actual); }
 * 
 * @Test public void testGeneracionNpcs() { AutoNpc autoNpc1 = new AutoNpc(new
 * Coordenada(0, 0), 1); AutoNpc autoNpc2 = new AutoNpc(new Coordenada(0, 0),
 * 1); AutoNpc autoNpc3 = new AutoNpc(new Coordenada(0, 0), 1); AutoNpc autoNpc4
 * = new AutoNpc(new Coordenada(0, 0), 1); AutoNpc autoNpc5 = new AutoNpc(new
 * Coordenada(0, 0), 1); AutoNpc autoNpc6 = new AutoNpc(new Coordenada(0, 0),
 * 1);
 * 
 * autoNpc.add(autoNpc1); autoNpc.add(autoNpc2); autoNpc.add(autoNpc3);
 * autoNpc.add(autoNpc4); autoNpc.add(autoNpc5); autoNpc.add(autoNpc6);
 * 
 * Mapa map = new Mapa(autoNpc, autoJugador, powerUps, obstaculos);
 * 
 * Integer esperado = 6; Integer actual = map.alinearNpc();
 * 
 * assertEquals(esperado, actual); }
 * 
 * @Test public void testAlinearAutoJugador() { AutoJugador autoJugador1 = new
 * AutoJugador(10, 20, 30); AutoJugador autoJugador2 = new AutoJugador(10, 20,
 * 30); AutoJugador autoJugador3 = new AutoJugador(10, 20, 30);
 * 
 * autoJugador.add(autoJugador1); autoJugador.add(autoJugador2);
 * autoJugador.add(autoJugador3);
 * 
 * Mapa map = new Mapa(autoNpc, autoJugador, powerUps, obstaculos);
 * 
 * map.alinearJugadores();
 * 
 * Coordenada[] actual = new Coordenada[3]; Coordenada[] esperado = new
 * Coordenada[3];
 * 
 * actual[0] = autoJugador1.getCoordenada(); actual[1] =
 * autoJugador2.getCoordenada(); actual[2] = autoJugador3.getCoordenada();
 * 
 * esperado[0] = new Coordenada(1, 0); esperado[1] = new Coordenada(2, 0);
 * esperado[2] = new Coordenada(3, 0);
 * 
 * assertArrayEquals(esperado, actual); } }
 */