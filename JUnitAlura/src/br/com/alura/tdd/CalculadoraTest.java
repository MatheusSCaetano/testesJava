package br.com.alura.tdd;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class CalculadoraTest {
	
	@Test
	public void somarDoisNumeros() {
		Calculadora calc = new Calculadora();
		int soma = calc.somar(9, 1);
		
		Assert.assertEquals(10, soma);
	}

}
