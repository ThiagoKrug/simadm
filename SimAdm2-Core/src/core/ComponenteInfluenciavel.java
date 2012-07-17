package core;

import java.util.HashMap;
import java.util.Map;


/**
 * Representa um componente que pode ter seu valor definido com base nos valores 
 * de outros componentes. Na verdade, este comportamento � t�pico dos fluxos 
 * e das vari�veis auxiliares.
 * 
 * @author Alexandre
 */
public abstract class ComponenteInfluenciavel extends ComponenteDeModelo {
	private Map<String, ComponenteDeModelo> influencias;
	private String expressao;
	// registra se o valor deste componente j� foi calculado em um determinado ciclo
	private boolean[] calculadoEm;
	// indica se o valor do componente pode ser alterado durante a simula��o 
	private boolean alteravel;
	protected double valorAtual;
	//indica a ordem de execu��o do componente
	private int ordem;
	
	/**
	 * Cria uma inst�ncia desta classe.
	 * 
	 * @param nome		Nome identificador do componente.
	 * @param expressao	Express�o que determina o valor do componente.
	 * @param alteravel	Indica se o valor do componente pode ser definido pelo usu�rio.
	 * @param modelo	Modelo ao qual este componente pertence.
	 * @throws NomeDuplicadoException	Caso o modelo j� possua um componente com o nome informado.
	 * @throws InterpretadorException	Caso ocorra algum erro na avalia��o da express�o.
	 */
	public ComponenteInfluenciavel(String nome, String expressao, 
			boolean alteravel, ComponenteDeModelo[] influencias,
			Modelo modelo) throws NomeDuplicadoException, 
			InterpretadorException{
		
		super(nome, modelo);
		
		if(influencias != null){
			this.influencias = new HashMap<String, ComponenteDeModelo>(influencias.length);
			for(int i = 0; i < influencias.length; i++){
				this.influencias.put(influencias[i].getNome(), influencias[i]);
			}
		}
		else{
			this.influencias = new HashMap<String, ComponenteDeModelo>();
		}
		this.expressao = expressao;
		this.alteravel = alteravel;
		calculadoEm = new boolean[getModelo().getQtdCiclos()];
		//modelo.get
		if( alteravel ){
			//TODO : setar o valor default como resultado da express�o
			this.valorAtual = 0;
		}
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
	 * Insere um componente dispon�vel para a realiza��o do c�lculo 
	 * do valor deste componente.
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
	 * Altera o valor atual do componente.
	 * @param valor	O novo valor do componente.
	 */
	public void setValorAtual(double valor){
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
	 * Indica se o valor do componente pode ser definido pelo 
	 * usu�rio do modelo. Em caso positivo, isso indica que o 
	 * valor do componente � definido pelo usu�rio e n�o por 
	 * uma equa��o.
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
