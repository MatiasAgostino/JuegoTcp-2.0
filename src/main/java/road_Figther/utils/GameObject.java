package road_Figther.utils;

public abstract class GameObject {
	protected double x;
	protected double y;
	protected boolean estaVivo;

	public abstract void destroy();

	public GameObject(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public boolean estaVivo() {
		return this.estaVivo;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.x;
	}
}
