package br.org.simadm2.simadmgui.geometric;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public abstract class Arrow{
	
	private static final int WIDTH = 10;
	private static final int HEIGTH = 6;
	
	/**
	 * Cria a ponta de uma seta.
	 * @param x	A coordenada x da ponta.
	 * @param y	A coordenada y da ponta.
	 * @param alfa	O �gulo, em graus, da ponta em rela��o � horizontal. Para um �ngulo de 
	 * 				zero graus, a seta ficar� apontado para a posi��o de 3 horas. O �ngulo �
	 * 				medido no sentido anti-hor�rio, assim para 90 graus, a seta apontar� 
	 * 				para as 12 horas.
	 * @return
	 */
	public static Shape getSeta(int x, int y, double alfa){
		Polygon pol = new Polygon(new int[]{0, WIDTH, 0}, new int[]{0, HEIGTH/2, HEIGTH}, 3);
		pol.translate(-pol.getBounds().x, -pol.getBounds().y); //move para o ponto (0, 0)
		pol.translate(x - WIDTH, y - HEIGTH/2); //move para o local correto, j� que a refr�ncia � a ponta da seta
		GeneralPath path = new GeneralPath(pol);
		path.transform( AffineTransform.getRotateInstance(Math.toRadians(-alfa), x, y) );
		
		return path;
	}
}
