package kevtech;

/**
 * A Point contains an X and a Y to make one spot on a Cartesian coordinate
 * system
 * 
 * @author Kevin Humphreys
 */
public final class Coord3D {
	/**
	 * Data:
	 * 
	 * @param x
	 *            The X of the coordinate
	 * @param y
	 *            The Y of the coordinate
	 * @param z
	 *            The Z of the coordinate
	 */
	public int x;
	public int y;
	public int z;

	/**
	 * 
	 * Constructs a new Point
	 * 
	 * @param x
	 *            The X of the coordinate
	 * @param y
	 *            The Y of the coordinate
	 * @param z
	 *            The Z of the coordinate
	 */
	public Coord3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Coord3D() {
		this(0, 0, 0);
	}

	/**
	 * 
	 * @return A String representation of a Point
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}

	public void translate(int dx, int dy, int dz) {
		x = x + dx;
		y = y + dy;
		z = y + dz;
	}

	public Coord3D clone() {
		return new Coord3D(x, y, z);
	}
}