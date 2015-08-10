package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 *  Quadrato della scacchiera
 */
public class Tile extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image=null;
	private Color c;

	public Tile(Color c){
		this.c=c;
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
	}
	
	public void setImg(Image image){
		this.image=image;
	}
	
	@Override
	protected void paintComponent(Graphics g){//renderizza la grafica
		super.paintComponent(g);
		
		if(image!=null){	
			//disegna l'immagine
			g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);
		}
		this.setBackground(c);
		this.repaint();//necessario 	
	}
	
	public void setColor(Color c){
		this.c=c;	
	}
	
}
