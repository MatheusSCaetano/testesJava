package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Funcionario;

class BonusServiceTest {

	//Teste que lança Exception
	@Test
	void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {
		BonusService service = new BonusService();
		
		//tratando exceptions
		assertThrows(IllegalArgumentException.class, 
				() -> service.calcularBonus(new Funcionario("Funcionari 1", LocalDate.now(),new BigDecimal("25000"))));
		
	}
	
	//Teste que lança Exception
	@Test
	void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto2() {
		BonusService service = new BonusService();
		
		//tratando exceptions 2
		try {
			service.calcularBonus(new Funcionario("Funcionari 1", LocalDate.now(),new BigDecimal("25000")));
			fail("Não deu a Exception");
		} catch (Exception e) {
			assertEquals("Funcionario com o salario maior do que  R$ 10.000 não pode receber bonus", e.getMessage());
		}
	}
	
	@Test
	void bonusDeveriaSer10PorCentoDoSalario() {
		BonusService service = new BonusService();
		BigDecimal bonus = service.calcularBonus(new Funcionario("Funcionario 2", LocalDate.now(), new BigDecimal("2500")));
		
		assertEquals(new BigDecimal("250.0"), bonus);
	}
	
	@Test
	void bonusDe1000ParaFuncionario() {
		BonusService service = new BonusService();
		BigDecimal bonus = service.calcularBonus(new Funcionario("Funcionario 3", LocalDate.now(), new BigDecimal("10000")));
		
		assertEquals(new BigDecimal("1000.0"), bonus);
	}
}
