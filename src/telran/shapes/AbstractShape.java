package telran.shapes;

public abstract class AbstractShape {
	protected long id;
	protected AbstractShape(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public abstract int square();
    public abstract int perimeter();
}
