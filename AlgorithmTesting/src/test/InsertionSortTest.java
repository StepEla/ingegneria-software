package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import algorithms.*;

public class InsertionSortTest {

	@Test
	public void sortOfOneElementShouldReturnTheElement() { //testo l'algoritmo nel caso limite di un array con un solo elemento
		String[] elements = { "ciao" };
		String[] sortedElements = elements.clone();
		InsertionSort.IS(sortedElements, sortedElements.length);
		Assert.assertArrayEquals(elements, sortedElements);
	}
	
	public void sortOfNullArray(){ //Le specifiche non indicano delle precondizioni precise
		
	}
	
	@Test
	public void sortOfEqualElements(){ //testo l'algoritmo con un array di valori uguali
		String[] elements = { "ciao","ciao","ciao","ciao","ciao","ciao","ciao"};
		String[] sortedElements = elements.clone();
		InsertionSort.IS(sortedElements, sortedElements.length);
		Assert.assertEquals(sortedElements[0], elements[0]);
	}
	
	@Test
	public void notSortedArrayTest(){
		String[] elements = { "ciao","come","stai","?"};
		String[] shouldReturn = {"?","ciao","come","stai"};
		InsertionSort.IS(elements, elements.length);
		Assert.assertArrayEquals(shouldReturn,elements);
	}
	
	@Test
	public void sortOfSortedArrayShouldReturnOriginalArray(){
		Integer[] elements = {1,2,3,4,5,6,7,8};
		Integer[] sortedElements = {1,2,3,4,5,6,7,8};
		InsertionSort.IS(elements, elements.length);
		Assert.assertArrayEquals(sortedElements, elements);
		
	}

}
