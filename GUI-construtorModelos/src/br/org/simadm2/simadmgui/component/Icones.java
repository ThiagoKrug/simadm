package br.org.simadm2.simadmgui.component;

import javax.swing.ImageIcon;

/**
 * Classe respons�vel por criar e disponibilizar os �cones da aplica��o.
 * @author Alexandre
 *
 */
public abstract class Icones {
	
	/**
	 * �cone de estoque.
	 */
	public static final ImageIcon ICON_ESTOQUE = new ImageIcon(Icones.class.getResource("/br/org/simadm2/simadmgui/component/icons/estoque.png"));
	/**
	 * �cone de vari�vel auxiliar.
	 */
	public static final ImageIcon ICON_VARIAVEL = new ImageIcon(Icones.class.getResource("/br/org/simadm2/simadmgui/component/icons/variavel.png"));
	/**
	 * �cone de fluxo.
	 */
	public static final ImageIcon ICON_FLUXO = new ImageIcon(Icones.class.getResource("/br/org/simadm2/simadmgui/component/icons/fluxo.png"));
	/**
	 * �cone de canal.
	 */
	public static final ImageIcon ICON_CANAL = new ImageIcon(Icones.class.getResource("/br/org/simadm2/simadmgui/component/icons/canal.png"));
	/**
	 * �cone de constante.
	 */
	public static final ImageIcon ICON_CONSTANTE = new ImageIcon(Icones.class.getResource("/br/org/simadm2/simadmgui/component/icons/constante.png"));
	
}
