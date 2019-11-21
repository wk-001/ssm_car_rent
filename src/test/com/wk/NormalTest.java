package com.wk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NormalTest {

	@Test
	public void numberTest(){
		List<Double> sale = new ArrayList<>(12);
		for (int i = 3; i <= 12; i++) {
			sale.add(i,0.0);
		}
		System.out.println("sale = " + sale);
	}

}
