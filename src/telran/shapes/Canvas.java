package telran.shapes;

import java.util.NoSuchElementException;

import telran.util.Arrays;

public class Canvas extends AbstractShape {
	private AbstractShape[] shapes;

	public Canvas(long id) {
		super(id);
		shapes = new AbstractShape[0];
	}

	public void addShape(AbstractShape shape) {
		long id = shape.getId();
		if(id<0) {
			throw new IllegalStateException(String.format("employee with id %d already exists",
					id));
		}
		shapes = Arrays.insertSorted(shapes, shape, (o1, o2) -> Long.compare(o1.getId(), o2.getId()));
	}

	public AbstractShape removeShape(long id) {
		if (shapes.length == 0) {
		    throw new NoSuchElementException();
		  }
		 int index = -1;
		 while (index < shapes.length && shapes[index].getId() != id) {
			    index++;
			  }
		 AbstractShape removedShape = shapes[index];
		 shapes = Arrays.removeIf(shapes, e -> e.getId() == id);
		  
		return removedShape;
	}

	@Override
	public int square() {
		 int sum = 0;
	        for (AbstractShape shape : shapes) {
	            sum += shape.square();
	        }
	        return sum;
	}

	@Override
	public int perimeter() {
		int sum =0;
		 for (AbstractShape shape : shapes) {
	            sum += shape.perimeter();
	        }
	        return sum;
	}
}
