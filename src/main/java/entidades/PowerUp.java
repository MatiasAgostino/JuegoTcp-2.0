package entidades;

public class PowerUp extends Entidad {
	private final double POWER = 3.7;

	public PowerUp(double x, double y) {
		super(x, y);
	}

	public double getPower() {
		return this.POWER;
	}

	@Override
	public void recibirContactoDe(AutoJugador jugador) {
		this.estaVivo = false;
	}
}
