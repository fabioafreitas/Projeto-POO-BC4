package principal;

import java.util.Scanner;

import beans.*;
import negocio.*;
import repositorios.*;

public class PrincipalTeste {
	public static void main(String[] args) {
		
		// SCANNER
		
		Scanner scanf = new Scanner(System.in);
		
		// OBJETOS DE TESTE - Armazena-los em arquivo depois!
		
		String[] nomes = {"Salgadinho","Biscoito","Sorvete","Arroz","Coca-Cola","Feijao","Macarrao","Acucar","Agua","Farinha"};
		double[] precos = {1.50, 1.50, 13.00, 4.00, 8.00, 7.00, 2.50, 3.00, 1.00, 4.00};
		int codigoProduto[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		Produto[] produto = new Produto[10];
		ItemVenda[] itens = new ItemVenda[10];
		
		for (int i = 0; i < produto.length; i++) {
			produto[i] = new Produto( nomes[i], codigoProduto[i],1000, precos[i]);
			itens[i] = new ItemVenda(codigoProduto[i], nomes[i], precos[i], i);
		}
		
		Endereco e1 = new Endereco("Rua A", "Cidade A", "12345-100", "999");
		Endereco e2 = new Endereco("Rua B", "Cidade B", "98765-100", "333");
		Endereco enderecoTeste;
		
		Pessoa p1 = new Pessoa(e1, "fabio", "11111111111");
		Pessoa p2 = new Pessoa(e2, "duda", "99999999999");
		Pessoa pessoaTeste;
		
		Funcionario f1 = new Funcionario(p1,"vendedor",2000,1);
		Funcionario f2 = new Funcionario(p2,"vendedor",2000,2);
		Funcionario funcionarioTeste;

		int opcao, identificacao;
		boolean parar = false;
		boolean vender = true;
		
		//FACHADA 
		Fachada fachada = Fachada.getInstancia();
		while(!parar) {
			System.out.println("\n\n=========================================\n\n\tMercadinho mil grau\n\n(1) - Sistema de Vendas\n"
							 + "(2) - Sistema de Estoque\n(3) - Sistema de Funcionario\n(4) - Sistema Financeiro\n(5) - Fechar programa\n\n==> ");
			opcao = scanf.nextInt();
			
			switch(opcao) {
			case 1: {
				System.out.println("\n\n=========================================\n\n\tSistema de Vendas\n\n(1) - Registrar Venda"
						         + "\n(2) - Listar Vendas\n(3) - Deletar Historico\n(4) - Menu principal");
				opcao = scanf.nextInt();
				
				if( opcao == 1 ) {
					// receber id do funcionario
					// abrir tabela de itens de venda
					// selecionar item e quantidade em um la�o
					// se terminar pergunta se quer concluir a venda
					// se concluir chamar o metodo de pedido - encerrarPedido
					// se cancelar o pedido chamar o metodo de peddo - resetarPedido
					
						
				} 
				else if (opcao == 2) {
					// Chamar controlador venda, que possui o metodo listar todas as vendas
				} 
				else if(opcao == 3) {
					// Chamar o controlador venda, que apaga o historico
				}
				
				else if (opcao == 4){
							// Corrigir a redundancia depois 			
				}
				else {
					System.out.println("\n\n\tOpcao invalida\n\n");
				}
				
				break;
			}
			case 2: {
				System.out.println("\n\n=========================================\n\n\tSistema de estoque\n\n(1) - Listar Produtos"
				         + "\n(2) - Adicionar produto\n(3) - Atualizar produtor\n(4) - Remover produto\n(5) - Buscar produto\n(6) - Menu principal\n\n");
				
				opcao = scanf.nextInt();
				String nome;
				int codigo;
				int quantidade;
				double preco;
				if( opcao == 1){
					int i = 0;
					for(i=0;i<fachada.getControladorEstoque().getRepoestoque().getQuantSKU();i++){
						System.out.println("#" + (i+1));
						System.out.println(fachada.getControladorEstoque().getRepoestoque().getProdutos()[i]);
					}
				}
				else if( opcao == 2 ) {
					
					System.out.println("Digite o codigo do produto");
					codigo = scanf.nextInt();
					System.out.println("Digite o nome do produto");
					scanf.nextLine();
					nome = scanf.nextLine();
					System.out.println("Digite o preço do produto");
					preco = scanf.nextDouble();
					System.out.println("Digite a sua quantidade");
					quantidade = scanf.nextInt();
					Produto prod = new Produto(nome,codigo,quantidade,preco);
					fachada.inserirProduto(prod);				
				}
				else if (opcao == 3) {
					//TODO Reestruturar a alteracao do produto
					opcao = 0;
					System.out.println("Digite o codigo do produto que será atualizado");
					codigo = scanf.nextInt();
					System.out.println("O que você deseja alterar?\n(1)Quantidade\n(2)Preco\n(3)Nome");
					opcao = scanf.nextInt();
					if(opcao == 1){
						Produto prod = new Produto();
						System.out.println("Digite a nova quantidade");
						quantidade = scanf.nextInt();
						fachada.atualizarProduto(prod);
					}
					else if(opcao == 2){
						
						System.out.println("Digite o novo preco");
						preco = scanf.nextDouble();
					}
					else if(opcao == 3){
						
						System.out.println("Digite o novo nome");
						nome = scanf.nextLine();
					}
					//Produto prod = controladorestoque.getRepoestoque().alterar(prod, posicao);
				} 
				else if(opcao == 4) {
					System.out.println("Digite o codigo do produto a ser removido");
					codigo = scanf.nextInt();
					fachada.removerProduto(codigo);
				}
				
				else if (opcao == 5){
					System.out.println("Digite o codigo do produto a ser buscado");
					codigo = scanf.nextInt();
					System.out.println(fachada.buscarProduto(codigo));
				}
				else {
					System.out.println("\n\n\tOpcao invalida\n\n");
				}
				
				break;
			}
			case 3: {
				System.out.println("\n\n=========================================\n\n\tSistema de Funcionario\n\n(1) - Adicionar funcionario"
				         + "\n(2) - Atualizar funcionario\n(3) - Remover funcionario\n(4) - Buscar funcionario\n(5) - Menu principal\n\n");
				
				opcao = scanf.nextInt();
				
				if( opcao == 1 ) {
					
						
				} 
				else if (opcao == 2) {
				
				} 
				else if(opcao == 3) {
					
				}
				
				else if (opcao == 4){
		
				}
				else {
					System.out.println("\n\n\tOpcao invalida\n\n");
				}
				
				
				break;
			}
			case 4: {
				System.out.println("\n\n=========================================\n\n\tSistema Financeiro\n\n(1) - Pagar funcionario"
				         + "\n(2) - Contatar fornecedor\n(3) - Informacoes das financas\n(4) - Menu principal\n\n");
				
				opcao = scanf.nextInt();
				
				if( opcao == 1 ) {
					
						
				} 
				else if (opcao == 2) {
				
				} 
				else if(opcao == 3) {
					
				}
				
				else if (opcao == 4){
		
				}
				else {
					System.out.println("\n\n\tOpcao invalida\n\n");
				}
				
				
				
				break;
			}	
			case 5: {
				parar = true;				
				break;
			}	
		}
		} // fechamento do switch externo
	}
}