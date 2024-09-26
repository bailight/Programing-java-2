package common.datamodule;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 4L;
	private int x;
    private Long y;

    /**
     * coordinates by x and y value
     *
     * @param x int value that must be below the 787
     * @param y long value (cannot be null)
     */

    public Coordinates() {
	}

	public Coordinates(int x, Long y) {
		this.x = x;
		this.y = y;
	}

	public void setCoordinates(int x, Long y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(Long y) {
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public Long getY(){
		return y;
	}


	@Override
	public String toString() {
        return "Coordinates(" + x +"," + y +')';
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x, x) == 0 && y == that.y;
    }

	@Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
