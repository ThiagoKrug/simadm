package core.algoritmo;

import core.Estoque;
import core.Fluxo;
import core.InterpretadorException;
import core.Modelo;
import core.ModeloException;
import core.NomeDuplicadoException;
import junit.framework.TestCase;

public class RungeKutta4Test extends TestCase{
	
	//Verifica se gera exce��o quando o fluxo usa uma express�o inv�lida
	public void testCalcularFluxo1() throws NomeDuplicadoException, InterpretadorException, ModeloException{
		Modelo modelo = new Modelo(1, 1, 4);
		modelo.setAlgoritmoDeIntegracao(new RungeKutta4(modelo));
		Estoque e = new Estoque("e", 100.0, modelo);
		new Fluxo("f", "{{", e, null, false, modelo);
		try{
			modelo.simular();
			fail("Deveria ter gerado exce��o");
		}
		catch(RuntimeException ex){
			if(ex.getCause().getClass() != InterpretadorException.class){
				fail("N�o gerou exce��o de interpreta��o");
			}
		}
	}
}
