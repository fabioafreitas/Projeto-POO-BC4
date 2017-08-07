package ufrpe.negocio;

import ufrpe.beans.Produto;
import ufrpe.negocio.exception.IdentificacaoInvalidaException;
import ufrpe.negocio.exception.InstanciaInexistenteException;
import ufrpe.negocio.exception.InstanciaRepetidaException;
import ufrpe.negocio.exception.NegocioException;
import ufrpe.negocio.exception.QuantidadeInvalidaException;
import ufrpe.repositorio.IRepositorioEstoque;
import ufrpe.repositorio.RepositorioEstoque;
import ufrpe.repositorio.RepositorioVenda;

public class ControladorEstoque {

	// ATRIBUTOS
	RepositorioVenda repoVenda;
	private static ControladorEstoque instancia;
	private IRepositorioEstoque repoestoque;

	// SINGLETON
	private ControladorEstoque() {
		repoestoque = RepositorioEstoque.getInstancia();
		repoVenda = RepositorioVenda.getInstancia();
	}

	public static ControladorEstoque getInstancia() {
		if (instancia == null) {
			instancia = new ControladorEstoque();
		}
		return instancia;
	}

	// METODOS COM CONTROLE DE NEGOCIOS
	// TODO usar collections neste metodo
	public String listarProduto() throws NegocioException{
		String texto = "";
		if (repoestoque.listar().isEmpty() == true) {
			throw new InstanciaInexistenteException("\nNao ha produtos cadastrados!\n");
		}
		for (Produto prod : repoestoque.listar()) {
			texto += "\n" + prod.toString();
		}
		return texto;
	}

	public void subtrairProduto(int codigo, int quantidade) throws NegocioException{
		int pos = retornarPosicao(codigo);
		if (pos == -1) {
			throw new InstanciaInexistenteException("\nProduto de codigo("+codigo+") nao existe\n");
		}
		for (Produto prod : repoestoque.listar()) {
			if (codigo == prod.getCodigo()) {
				if (quantidade <= prod.getQuantidade()) {
					repoestoque.subtrairProduto(pos, quantidade);
				}
				else {
					throw new QuantidadeInvalidaException("\nQuantidade insuficiente para subtracao!\n");
				}
				break;
			}
		}
	}

	public void inserir(Produto prod) throws NegocioException{
		if (prod == null) {
			throw new RuntimeException("\nInstancia de Produto nula\n");
		}
		if(prod.getNome() == null) {
			throw new RuntimeException("\nString do Nome do Produto nula\n");
		}
		if(prod.getCodigo() <= 0) {
			throw new IdentificacaoInvalidaException("\nCodigo ("+prod.getCodigo()+") invalido\n");
		}
		if (retornarPosicao(prod.getCodigo()) != -1) {
			throw new InstanciaRepetidaException("\nProduto de codigo("+prod.getCodigo()+") ja cadastrado\n");
		}
		repoestoque.inserir(prod);
		instancia.repoestoque.salvarArquivo();
		System.out.println("Produto adicionado com sucesso!");
	}

	public Produto buscar(int cod) {
		int posicao;
		posicao = this.retornarPosicao(cod);

		if (posicao != -1) {
			return repoestoque.buscar(posicao);
		}
		System.out.println("Produto nao existe");
		return null;
	}

	public void alterar(Produto novoProduto) throws NegocioException{
		int posicao;
		if (novoProduto == null) {
			throw new RuntimeException("\nInstancia de Produto nula\n");
		}
		if(novoProduto.getNome() == null) {
			throw new RuntimeException("\nString do Nome do Produto nula\n");
		}
		posicao = this.retornarPosicao(novoProduto.getCodigo());
		if (posicao == -1) {
			throw new InstanciaInexistenteException("\nInstancia de Produto ("+novoProduto.getCodigo()+") nao existe\n");
		}
		if (repoestoque.listar().get(posicao).getCodigo() == novoProduto.getCodigo()) {
			repoestoque.alterar(novoProduto, posicao);
			instancia.repoestoque.salvarArquivo();
			System.out.println("Produto alterado com sucesso!");
		}
	}

	public void remover(int cod) throws NegocioException{
		if(cod <= 0) {
			throw new IdentificacaoInvalidaException("\nCodigo ("+cod+") invalido\n");
		}
		if (repoestoque.listar().isEmpty() == true) {
			//System.out.println("\n\tErro! Nao ha produtos para remover!");
			throw new InstanciaInexistenteException("\nNao ha produtos cadastrados no array\n");
		}
		int posicao = this.retornarPosicao(cod);
		if (posicao == -1) {
			//System.out.println("Codigo nao encontrado!");
			throw new InstanciaInexistenteException("\nNao ha produto de codigo ("+cod+") para remover\n");
		}
		repoestoque.remover(posicao);
		instancia.repoestoque.salvarArquivo();
		System.out.println("Produto removido com sucesso!");
	}

	private int retornarPosicao(int codigo) {

		if (codigo <= 0) {
			return -1;
		}
		for (Produto prod : repoestoque.listar()) {
			if (codigo == prod.getCodigo()) {
				return repoestoque.listar().indexOf(prod);
			}
		}
		return -1;
	}
}