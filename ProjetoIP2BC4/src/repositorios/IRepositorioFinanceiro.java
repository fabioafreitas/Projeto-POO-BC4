package repositorios;

import beans.Funcionario;

public interface IRepositorioFinanceiro {
	
	double getRendaBruta();

	void setRendaBruta(double rendaBruta);
	
	double totalSalarioFuncionarios ();
	
	double totalFornecedor ();
	
	void pagarFuncionario (Funcionario func);
	
	double rendaLiquida();
	
	void receberDinheiroVenda (double valor);
	
	String exibirFinancas ();
	
}
