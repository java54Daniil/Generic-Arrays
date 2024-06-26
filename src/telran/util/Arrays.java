package telran.util;

import java.util.Comparator;
import java.util.function.Predicate; 




public class Arrays {
	public static <T> int indexOf(T[] array, T element) {
		int index = 0;
		while (index < array.length && !equals(array[index], element)) {
			index++;
		}
		return index == array.length ? -1 : index;
	}

	private static <T> boolean equals(T elem1, T elem2) {
		return elem1 == null ? elem1 == elem2 : elem1.equals(elem2);
	}

	public static <T> T min(T[] array, Comparator<T> comp) {
		T res = null;
		if (array != null && array.length > 0) {
			res = array[0];
			for (int i = 1; i < array.length; i++) {
				if (comp.compare(res, array[i]) > 0) {
					res = array[i];
				}
			}
		}

		return res;
	}
	public static <T> void bubbleSort(T[] array, Comparator<T> comp) {
		boolean swapped = true;
		for (int i = 0; i < array.length - 1 && swapped; i++) {
			swapped =false;
	        for (int j = 0; j < array.length - i - 1; j++) {
	        	if (comp.compare(array[j], array[j + 1]) > 0) {
	        	T temp = array[j];
	        	array[j] = array[j + 1];
	        	array[j + 1] = temp;
	        	swapped = true;
	        	}
	        }
		}
	}
	@SuppressWarnings("unchecked")
	public static <T> void bubbleSort(T[] array) {
		bubbleSort(array, (Comparator<T>)Comparator.naturalOrder());
	}

	public static<T> int binarySearch(T[] array,T key, Comparator<T> comp) {
		// left index = 0
		// right index = array.length -1
		//middle (left + right) /2
		// left part - left index ,right index = middle -1
		// right part - left index =middle +1 ,right index
		//while left <= right 
		//returns exactly what the standard binarySearch does
		//if there are several equaled elements 
		//no guarantee that being return index is one to first occurrence
		int left = 0;
		int right = array.length - 1;
		int middle = (left + right) / 2;
		int res = -1;
		while(left <= right && (res = comp.compare(key, array[middle])) != 0) {
			if (res > 0) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
			middle = (left + right) / 2;
		}
		return left > right ? -(left + 1) : middle;
	}
	public static <T> T[] search (T[] array,Predicate<T> predicate) {
		//Impossible to allocate memory for generic array
		//only Arrays.copyOf may be used
		T[] arResult =  java.util.Arrays.copyOf(array, array.length);
		int index = 0;
		for(int i=0;i <array.length;i++) {
			if(predicate.test(array[i])) {
				arResult[index++] =array[i];     
			}
		}
		return java.util.Arrays.copyOf(arResult, index);
	}
	//HW
	public static <T> T[] removeIf(T[] array,Predicate<T> predicate){
		//removes all elements of array matching a given predicate
		return Arrays.search(array, predicate.negate());
	
	}
	public static <T> T[] add(T[] array, T element) {
	T[] result  = java.util.Arrays.copyOf(array, array.length+1);
		result[array.length] =element; 
		return result;
	}
	
	public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {
		//research:write the code based on the existing one of bubbleSort with comparator
		boolean swapped = true;
	    for (int i = 0; i < array.length - 1 && swapped; i++) {
	        swapped = false;
	        for (int j = 0; j < array.length - i - 1; j++) {
	            if (array[j].compareTo(array[j + 1]) > 0) {
	                T temp = array[j];
	                array[j] = array[j + 1];
	                array[j + 1] = temp;
	                swapped = true;
	            }
	        }
	    }
	}

	public static <T> T[] copy(T[] array ) {
		return java.util.Arrays.copyOf(array, array.length);
	}
	public static <T> T[] insert(T[] array, int index, T element) {
		T[] result = java.util.Arrays.copyOf(array, array.length + 1);
		System.arraycopy(array, index, result, index + 1, array.length - index);
		result[index] = element;
		return result;
	}
	public static <T> T[] insertSorted(T[] array, T element,
			Comparator<T> comp) {
		int index = binarySearch(array, element, comp);
		if (index < 0) {
			index = - (index + 1);
		}
		return insert(array, index, element);
	}
}
