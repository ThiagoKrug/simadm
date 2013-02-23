package core.algoritmo;

import java.util.Iterator;

import core.ComponenteDeModelo;
import core.Fluxo;
import core.Modelo;

/**
 * Classe que calcula o valor de um fluxo com base no 
 * algoritmo de Euler.
 * 
 * @author Alexandre
 */
public class MetodoDeEuler extends Algoritmo {
	
	/**
	 * Cria uma inst�ncia desta classe.
	 * 
	 * @param modelo
	 */
	public MetodoDeEuler(Modelo modelo){
		super(modelo);
	}
	
	/**
	 * Calcula o valor de um fluxo no tempo atual da simula��o.
	 * 
	 * @param f	Fluxo que se deseja calcular o valor.
	 */
	@Override
	public void calcularFluxo(Fluxo f) {
		//adicionando as vari�veis no parser
		Iterator vars = f.getInfluencias().values().iterator();
		ComponenteDeModelo c;
		while(vars.hasNext()){
			c = (ComponenteDeModelo)vars.next();
			parser.addVariable(c.getNome(), c.getValorAtual());
		}
		//avaliando a express�o
		//TODO capturar e lan�ar erro de avalia��o da express�o
		parser.parseExpression(f.getExpressao());
		//obtendo o resultado e atualizando o valor do componente
		f.setValorAtual( parser.getValue() * modelo.getDt(), this );
	}
}
