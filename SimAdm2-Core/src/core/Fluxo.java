package core;


public class Fluxo extends ComponenteInfluenciavel{
	private Estoque estoqueDeOrigem;
	private Estoque estoqueDeDestino;
	
	/**
	 * Cria uma inst�ncia desta classe.
	 * @param nome	Identificador do fluxo.
	 * @param equacao	Equa��o que determina o valor do fluxo.
	 * @param estoqueDeOrigem	Estoque de origem do fluxo.
	 * @param estoqueDeDestino	Estoque de destino do fluxo.
	 * @param alteravel	Indica se o valor do fluxo pode ser defindo interativamente  
	 * 					pelo usu�rio da simula��o.
	 * @param modelo	Modelo ao qual este fluxo pertence
	 * @throws NomeDuplicadoException	Caso j� exista um componente com o nome passado 
	 * 									como par�metro.
	 * @throws InterpretadorException	Caso haja algum erro na equa��o do fluxo.
	 */
	public Fluxo(String nome, String equacao, Estoque estoqueDeOrigem, 
			Estoque estoqueDeDestino, boolean alteravel, Modelo modelo) throws 
			NomeDuplicadoException, InterpretadorException{
		super(nome, equacao, alteravel, modelo);
		if( estoqueDeOrigem != null ){
			this.estoqueDeOrigem = estoqueDeOrigem;
			estoqueDeOrigem.adicionarFluxoDeSaida(this);
		}
		if( estoqueDeDestino != null){
			this.estoqueDeDestino = estoqueDeDestino;
			estoqueDeDestino.adicionarFluxoDeEntrada(this);
		}
	}
	
	/**
	 * Retorna o estoque de destino do fluxo.
	 * @return	Uma inst�ncia de <code>Estoque</code>. Caso o fluxo n�o possua 
	 * 			estoque de destino, retorna <code>null</code>.
	 */
	public Estoque getEstoqueDeDestino() {
		return estoqueDeDestino;
	}
	
	/**
	 * Retorna o estoque de origem do fluxo.
	 * @return	Uma inst�ncia de <code>Estoque</code>. Caso o fluxo n�o possua 
	 * 			estoque de origem, retorna <code>null</code>.
	 */
	public Estoque getEstoqueDeOrigem() {
		return estoqueDeOrigem;
	}
}
