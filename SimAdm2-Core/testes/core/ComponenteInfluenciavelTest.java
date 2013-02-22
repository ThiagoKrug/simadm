package core;

import core.algoritmo.MetodoDeEuler;
import util.Relatorio;
import junit.framework.TestCase;

/**
 * 
 * @author Alexandre
 */
public class ComponenteInfluenciavelTest extends TestCase{
	
	//verifica se � gerada exce��o ao se tentar alterar a express�o de um componente
	//n�o alter�vel
	public void testSetExpressao1() throws NomeDuplicadoException, InterpretadorException{
		Modelo modelo = new Modelo(1, 1, 10);
		ComponenteInfluenciavel c = new VariavelAuxiliar("c", "100.0", false, modelo);
		try{
			c.setExpressao("200.0");
			fail("Deveria ter gerado exce��o.");
		}
		catch(UnsupportedOperationException e){}
	}
	
	//verifica se o usu�rio consegue alterar a express�o do componente durante a simula��o
	public void testSetExpressao2() throws NomeDuplicadoException, InterpretadorException{
		Modelo modelo = new Modelo(1, 1, 10);
		Estoque e = new Estoque("e", 1000, modelo);
		VariavelAuxiliar v = new VariavelAuxiliar("v", "1.0", false, modelo);
		modelo.adicionarComponente(v);
		//lembre que Fluxo � ComponenteInfluenciavel
		Fluxo f = new Fluxo("f", "1.0", e, null, true, modelo);
		f.adicionarInfluencia(v);
		modelo.adicionarComponente(f);
		
		//t=0
		modelo.simular();
		//t=1
		f.setExpressao("100.0");
		modelo.simular();
		assertEquals(100.0, f.getValorAtual(), 0.001);
		//t=2
		f.setExpressao("235.4 + v");
		modelo.simular();
		assertEquals(235.4 + v.getValorAtual(), f.getValorAtual(), 0.001);
		//t=3
		f.setExpressao("sqrt(400)");
		modelo.simular();
		assertEquals(20, f.getValorAtual(), 0.001);
		
		Relatorio report = new Relatorio(modelo);
		report.printReport();
	}
	
	//verifica se � gerada uma exce��o quando um objeto indevido chama o m�todo setValorAtual
	public void testSetValorAtual() throws NomeDuplicadoException, InterpretadorException{
		Modelo modelo = new Modelo(1, 1, 10);
		ComponenteInfluenciavel v = new VariavelAuxiliar("v", "100.0", true, modelo);
		modelo.adicionarComponente(v);
		
		//Realiza um ciclo de simula��o para que o m�todo setValorAtual seja chamado pelo 
		//objeto de algoritmo de integra��o. Esta chamada n�o deve gerar exece��o.
		modelo.simular();
		
		//Chama o m�todo setValorAtual para verificar se a exece��o � gerada. Note que somente o 
		//objeto algoritmo de simula��o do modelo est� autorizado a realizar a chamada.
		try{
			v.setValorAtual(100.0, null);
			fail("Deveria ter gerado exce��o");
		}catch(IllegalArgumentException ex){}
		
		//Testa com uma inst�ncia de algoritmo que n�o � a associada ao modelo.
		try{
			MetodoDeEuler euler = new MetodoDeEuler(null);
			v.setValorAtual(100.0, euler);
			fail("Deveria ter gerado exce��o");
		}catch(IllegalArgumentException ex){}
	}
}
