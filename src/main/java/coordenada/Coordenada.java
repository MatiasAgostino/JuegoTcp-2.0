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

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
}
