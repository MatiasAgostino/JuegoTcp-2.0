package coordenada;

public class Coordenada {
	private double x;
	private double y;

	public Coordenada(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getDistaciaAlCentro() {
		return Math.hypot(this.x, this.y);
	}

	public double getDistanciaA(Coordenada otra) {
		return Math.hypot(otra.x - this.x, otra.y - this.y);
	}

	public boolean getDistanciaRespectoAFin(Coordenada finCarrera) { //// hecho por bruno y agustin xd
		return finCarrera.y - this.y == 0 ? true : false;
	}

	public void setX(double dx) {
		this.x += dx;
	}

	public void setY(double dy) {
		this.y += dy;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
}
