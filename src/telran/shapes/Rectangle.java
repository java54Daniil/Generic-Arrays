package telran.shapes;

public class Rectangle extends AbstractShape {
	int width;
	int height;
	protected Rectangle(long id,int width,int height ) {
		super(id);
		this.height=height;
		this.width= width;
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public int square() {
		return height*width;
	}

	@Override
	public int perimeter() {
		return (height+width)*2;
	}

}
