package ufrpe.negocio.beans;

import java.io.Serializable;

public class ItemVenda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4973037462013975193L;
	// ATRIBUTOS

	private String nome;
	private double preco;
	private int codigo;
	private int qtd;
	// CONSTRUTORES

	public ItemVenda(int codigo, String nome, double preco, int qtd) {
		this.nome = nome;
		this.preco = preco;
		this.codigo = codigo;
		this.qtd = qtd;
	}

	public ItemVenda(Produto prod, int quantidade) {
		this.nome = prod.getNome();
		this.preco = prod.getPreco();
		this.codigo = prod.getCodigo();
		this.qtd = quantidade;
	}

	public double valorTotal() {
		return qtd * preco;
	}

	// GET E SET

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	// EQUALS

	public boolean equals(ItemVenda item) {
		if (item == null)
			return false;

		if (item.codigo == this.codigo)
			return true;

		return false;
	}

	// TO STRING

	public String toString() {
		return "\n\tProduto: " + nome + "\n\tQtd: " + qtd + "\n\tPreco: " + preco + "\n\tSubTotal: " + valorTotal()
				+ "\n";
		// return "Nome: " + nome + ", Preco: R$" + preco;
	}
}