package entidades;

public class Obstaculo extends Entidad {
	private int DESPLAZAMIENTO = 3;

	public Obstaculo(double x, double y) {
		super(x, y);
		this.estaVivo = true;
	}

	public int getDesplazamiento() {
		return this.DESPLAZAMIENTO;
	}

	@Override
	public void recibirContactoDe(AutoJugador jugador) {
		this.estaVivo = false;
	}
}
