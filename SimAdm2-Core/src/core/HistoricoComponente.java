package core;

import java.io.Serializable;

/**
 * Uma inst�ncia desta classe armazena o hist�rico, ciclo a ciclo, dos valores  
 * assumidos por um componente de modelo. Conseq�entemente, um objeto desta 
 * classe estar� associado a um componente de modelo.
 * 
 * @author Alexandre Gomes de Lima
 */
public class HistoricoComponente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//componente de modelo ao qual a inst�ncia estar� associada
	private ComponenteDeModelo componente;
	//armazena os valores assumidos pelo componente de modelo
	private double[] valores;
	
	/**
	 * Cria uma inst�ncia desta classe.
	 * @param comp	O componente de modelo ao qual esta classe estar� associada.
	 */
	public HistoricoComponente(ComponenteDeModelo comp){
		componente = comp;
		valores = new double[comp.getModelo().getQtdCiclos()];
	}
	
	public double[] getValores(){
		return valores;
	}
	
	public String getNomeComponente(){
		return componente.getNome();
	}
	
	public void setValor(int ciclo, double valor){
		valores[ciclo] = valor;
	}
	
	public double getValor(int interacao){
		return valores[interacao];
	}
	/**
	 * @return Returns the componente.
	 */
	public ComponenteDeModelo getComponente() {
		return componente;
	}
}
