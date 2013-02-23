package core;

import java.util.HashMap;
import java.util.Map;

import core.algoritmo.Algoritmo;


/**
 * Representa um componente que pode ter seu valor definido com base nos valores 
 * de outros componentes. Na verdade, este comportamento � t�pico dos fluxos 
 * e das vari�veis auxiliares.
 * 
 * @author Alexandre
 */
public abstract class ComponenteInfluenciavel extends ComponenteDeModelo {
	private Map<String, ComponenteDeModelo> influencias; //chave -> nome do componente
	private String expressao;
	// registra se o valor deste componente j� foi calculado em um determinado ciclo
	private boolean[] calculadoEm;
	// indica se o valor do componente pode ser alterado pelo usu�rio durante a simula��o 
	private boolean alteravel;
	protected double valorAtual;
	//indica a ordem de execu��o do componente
	private int ordem;
	
	/**
	 * Cria uma inst�ncia desta classe.
	 * 
	 * @param nome		  Nome identificador do componente.
	 * @param expressao	  Express�o que determina o valor do componente.
	 * @param alteravel	  Indica se a express�o do componente pode ser alterada pelo usu�rio durante a simula��o.
	 * @param modelo	  Modelo ao qual este componente pertence.
	 * @throws NomeDuplicadoException	Caso o modelo j� possua um componente com o nome informado.
	 * @throws InterpretadorException	Caso ocorra algum erro na avalia��o da express�o.
	 */
	public ComponenteInfluenciavel(String nome, String expressao, 
			boolean alteravel, Modelo modelo) throws NomeDuplicadoException, 
			InterpretadorException{
		
		super(nome, modelo); //adiciona este componente ao modelo
		
		this.influencias = new HashMap<String, ComponenteDeModelo>();
		this.expressao = expressao;
		this.alteravel = alteravel;
		calculadoEm = new boolean[getModelo().getQtdCiclos()];
	}
	
	/**
	 * Retorna um mapa contendo os componente que influenciam o c�lculo 
	 * do valor deste componente. 
	 * @return	Um mapa contendo inst�ncias de <code>ComponenteDeModelo</code>, cujas 
	 * 			chaves s�o os nomes dos componentes. 
	 */
	public Map<String, ComponenteDeModelo> getInfluencias(){
		return influencias;
	}
	
	/**
	 * Insere um componente dispon�vel para compor a express�o num�rica deste componente.
	 * @param componente	O componente que estar� dispon�vel na 
	 * 						avalia��o do valor da express�o.
	 */
	public void adicionarInfluencia(ComponenteDeModelo componente){
		influencias.put(componente.getNome(), componente);
	}
	
	/**
	 * Remove um componente que est� dispon�vel para o c�lculo 
	 * deste componente.
	 * @param componente	O componente a ser removido.
	 */
	public void removerInfluencia(ComponenteDeModelo componente){
		influencias.remove(componente);
	}
	
	/**
	 * Retorna a express�o num�rica que determina o valor deste componente.
	 * @return	Uma representa��o textual da express�o num�rica
	 */
	public String getExpressao(){
		return expressao;
	}
	
	/**
	 * Altera a express�o num�rica deste componente.
	 * @param expressao a nova express�o
	 * @throws InterpretadorException Caso a nova express�o seja sintaticamente inv�lida. 
	 * @throws UnsupportedOperationException Caso este componente n�o seja alter�vel.
	 */
	public void setExpressao(String expressao) throws InterpretadorException{
		if(!alteravel){
			throw new UnsupportedOperationException("Opera��o permitida apenas para componentes alter�veis.");
		}
		getModelo().validarExpressao(expressao, null);
		
		this.expressao = expressao;
	}
	
	/**
	 * Altera o valor atual do componente. Este m�todo deve ser utilizado apenas pelo algoritmo de 
	 * integra��o do modelo.
	 * @param valor	    O novo valor do componente.
	 * @param algoritmo A inst�ncia de algoritmo de integra��o utilizada pelo modelo.
	 * @throws IllegalArgumentException Caso <code>algoritmo</code> n�o seja a inst�ncia referenciada pelo modelo.
	 */
	public void setValorAtual(double valor, Algoritmo algoritmo){
		if(algoritmo != getModelo().getAlgoritmoIntegracao()){
			throw new IllegalArgumentException("Opera��o n�o permitida.");
		}
		valorAtual = valor;
		calculadoEm[ getModelo().getCicloAtual() ] = true;
		getHistorico().setValor(getModelo().getCicloAtual(), valorAtual);
	}
	
	/**
	 * Retorna o valor atual deste componente.
	 */
	public double getValorAtual(){
		return valorAtual;
	}
	
	/**
	 * Indica se a express�o do componente pode ser alterada pelo 
	 * usu�rio do modelo durante a simula��o.
	 * @return
	 */
	public boolean isAlteravel() {
		return alteravel;
	}
	
	/**
	 * Indica se o valor do componente j� foi calculado em determinado ciclo.
	 * @param ciclo	Ciclo que se deseja obter a informa��o.
	 * @return	<code>true</code> - caso j� tenha sido calculado. <code>false</code> em caso contr�rio.
	 */
	public boolean isCalculadoEm(int ciclo){
		return calculadoEm[ciclo];
	}
	
	/**
	 * Retorna a ordem de execu��o deste componente.
	 * @return
	 */
	public int getOrdem() {
		return ordem;
	}
	
	/**
	 * Define a ordem de execu��o deste componente.
	 * @param ordem
	 */
	void setOrdem(int ordem) {
		this.ordem = ordem;
	}
}
