package core.algoritmo;

import core.InterpretadorException;
import core.Modelo;
import core.NomeDuplicadoException;
import core.VariavelAuxiliar;
import junit.framework.TestCase;

public class AlgoritmoTest extends TestCase{
	
	//verifica se � gerada exce��o o caso de uma express�o inv�lida
	public void testCalcularVariavel1() throws NomeDuplicadoException, InterpretadorException{
		Modelo modelo = new Modelo(1, 1, 4);
		new VariavelAuxiliar("var", "{{", false, modelo);
		try{
			modelo.simular();
			fail("Deveria ter gerado exce��o");
		}
		catch(RuntimeException ex){
			if(ex.getCause().getClass() != InterpretadorException.class){
				fail("N�o gerou erro de interpreta��o de express�o.");
			}
		}
	}
}
