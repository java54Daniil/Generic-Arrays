package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;

public class Canvas extends AbstractShape implements Iterable<AbstractShape> {
	private AbstractShape[] shapes = new AbstractShape[0];

	public Canvas(long id) {
		super(id);
	}

	public void addShape(AbstractShape shape) {
		shapes = Arrays.add(shapes, shape);
	}

	public void removeShape(long id) {
		shapes = Arrays.removeIf(shapes, s -> s.getId() == id);
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
		int sum = 0;
		for (AbstractShape shape : shapes) {
			sum += shape.perimeter();
		}
		return sum;
	}

	@Override
	public Iterator<AbstractShape> iterator() {
		return new CanvasIterator();
	}

	private class CanvasIterator implements Iterator<AbstractShape> {
		int currentIndex = 0;

		@Override
		public boolean hasNext() {

			return currentIndex < shapes.length;
		}

		@Override
		public AbstractShape next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return shapes[currentIndex++];
		}
	}
}
