package br.com.alura.leilao.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.alura.leilao.dao.PagamentoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Pagamento;
import br.com.alura.leilao.model.Usuario;

class GeradorDePagamentoTest {

	private GeradorDePagamento gerador;
	
	@Mock
	private PagamentoDao pagamentoDao;
	
	//pegar o objeto pagamento gerado dentro da classe a ser testada - GeradorDePagamento
	@Captor
	private ArgumentCaptor<Pagamento> captor;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.gerador = new GeradorDePagamento(pagamentoDao);
	}
	
	@Test
	void deveriaCriarPagamentoParaVencedorDoLeilao() {
		Leilao leilao = criaLeilao();
		Lance vencedor = leilao.getLanceVencedor();
		gerador.gerarPagamento(vencedor);
		//captura o objeto passado para o objeto
		Mockito.verify(pagamentoDao).salvar(captor.capture());
		//pagamento capturado da ultima linha
		Pagamento pagamento = captor.getValue();
		//realizando verificações do pagamento gerado
		Assert.assertEquals(LocalDate.now().plusDays(1), pagamento.getVencimento());
		Assert.assertEquals(vencedor.getValor(), pagamento.getValor());
		Assert.assertFalse(pagamento.getPago());
		Assert.assertEquals(vencedor.getUsuario(), pagamento.getUsuario());
		Assert.assertEquals(leilao, pagamento.getLeilao());
		
	}

	private Leilao criaLeilao(){
		
		Leilao leilao = new Leilao("Celular", new BigDecimal("500"), new Usuario("Fulano"));
		
		Lance lance = new Lance(new Usuario("Ciclano"), new BigDecimal("900"));
		
		leilao.propoe(lance);
		leilao.setLanceVencedor(lance);
		
		return leilao;
	}
	
}
