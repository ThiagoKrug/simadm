package br.org.simadm2.simadmgui.component;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

/**
 * Classe respons�vel por carregar e disponibilizar os cursores 
 * personalizados da aplica��o.
 * @author Alexandre
 *
 */
public abstract class Cursores {
	
	/**
	 * Cursor do modo de adi��o de estoque.
	 */
	public static final Cursor ESTOQUE;
	/**
	 * Cursor do modo de adi��o de vari�vel auxiliar.
	 */
	public static final Cursor VARIAVEL;
	/**
	 * Cursor do modo de adi��o de fluxo.
	 */
	public static final Cursor FLUXO;
	/**
	 * Cursor do modo de adi��o de canal.
	 */
	public static final Cursor CANAL;
	/**
	 * Cursor do modo de adi��o de constante.
	 */
	public static final Cursor CONSTANTE;
	
	static{
		Toolkit tool = Toolkit.getDefaultToolkit();
		ImageIcon img = new ImageIcon(Icones.class.getResource("/br/org/simadm2/simadmgui/component/icons/cursor_estoque.png"));
		ESTOQUE = tool.createCustomCursor(img.getImage(), new Point(15, 15), "cursor de estoque");
		img = new ImageIcon(Icones.class.getResource("/br/org/simadm2/simadmgui/component/icons/cursor_variavel.png"));
		VARIAVEL = tool.createCustomCursor(img.getImage(), new Point(15, 15), "cursor de vari�vel");
		img = new ImageIcon(Icones.class.getResource("/br/org/simadm2/simadmgui/component/icons/cursor_fluxo.png"));
		FLUXO = tool.createCustomCursor(img.getImage(), new Point(15, 15), "cursor de fluxo");
		img = new ImageIcon(Icones.class.getResource("/br/org/simadm2/simadmgui/component/icons/cursor_canal.png"));
		CANAL = tool.createCustomCursor(img.getImage(), new Point(0, 0), "cursor de canal");
		img = new ImageIcon(Icones.class.getResource("/br/org/simadm2/simadmgui/component/icons/cursor_constante.png"));
		CONSTANTE = tool.createCustomCursor(img.getImage(), new Point(0, 0), "cursor de constante");
	}
}
