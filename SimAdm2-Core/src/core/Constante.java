package core;

/**
 * Representa uma vari�vel auxiliar cujo valor n�o pode ser alterado ao longo da simula��o.
 * 
 * @author Alexandre
 *
 */
public class Constante extends ComponenteDeModelo{
	
	private double valor;
	
	public Constante(String nome, double valor, Modelo modelo) throws NomeDuplicadoException {
		super(nome, modelo);
		this.valor = valor;
		for(int i = 0; i < modelo.getQtdCiclos(); i++){
			getHistorico().setValor(i, valor);
		}
	}
	
	@Override
	public double getValorAtual() {
		return valor;
	}
	
}
