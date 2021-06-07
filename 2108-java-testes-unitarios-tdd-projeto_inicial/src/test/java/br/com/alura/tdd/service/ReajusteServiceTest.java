package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;

class ReajusteServiceTest {

	private ReajusteService service;
	private Funcionario funcionario;

	//BeforeEach -> antes de inicar os teste faça este metodo
	@BeforeEach
	public void iniciar() {
		System.out.println("Iniciar");
		this.service = new ReajusteService();
		this.funcionario = new Funcionario("Nome teste 1", LocalDate.now(), new BigDecimal("1000"));
	}
	
	@AfterEach
	public void finalizar() {
		System.out.println("Finalizar");
	}
	
	//este precisa ser static
	@BeforeAll
	public static void antesDeTodos() {
		System.out.println("INICIALIZA ANTES DE TODOS");
	}
	
	//este precisa ser static
	@AfterAll
	public static void depoisDeTodos() {
		System.out.print("FINALIZA DEPOIS DE TODOS");
	}
	
	@Test
	public void desempenhoDoFuncionarioADesejarReajustede3PorCento() {
		service.concederReajuste(funcionario, Desempenho.A_DESEJAR);
		assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
	}
	
	@Test
	public void desempenhoDoFuncionarioBomReajustede15PorCento() {
		service.concederReajuste(funcionario, Desempenho.BOM);
		assertEquals(new BigDecimal("1150.00"), funcionario.getSalario());
	}
	
	@Test
	public void desempenhoDoFuncionarioOtimoReajustede20PorCento() {
		service.concederReajuste(funcionario, Desempenho.OTIMO);
		assertEquals(new BigDecimal("1300.00"), funcionario.getSalario());
	}
	

}
