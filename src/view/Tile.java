package view;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;
import javax.swing.JComponent;

import model.PColor;
import view.properties;

/**
 *  Quadrato della scacchiera
 */
public class Tile extends JComponent{

	private static final long serialVersionUID = 1L;
	private Image squareImage = null;
	private Image pieceImage = null;
	private int pieceID = -1;
	private PColor c;
	private boolean focus = false;
	private boolean alert = false;
	
	public Tile(PColor c, Image squareImage, Image pieceImage){
		this.c=c;
		this.squareImage = squareImage;
		this.pieceImage = pieceImage;
	}
	
	public void setPieceID(int pieceID){
		this.pieceID=pieceID;
	}
	public void focusOn(){
		focus=true;	
	}
	public void focusOff(){
		focus=false;
	}
	
	public void alertOn(){
		alert=true;	
	}
	public void alertOff(){
		alert=false;
	}
	
	/**
	 * Method to paint the square component
	 */
	
	@Override
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		int dim=properties.tilesDimension;

		Graphics2D paintGraphics = (Graphics2D) graphics;
		paintGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		paintGraphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		paintGraphics.drawImage(squareImage, 0, 0, this.getWidth(), this.getHeight(),c.ordinal()*dim,0,c.ordinal()*dim+dim,dim, null); 
		//Empathize the selected square
		if(focus){
			paintGraphics.setColor(properties.colorFocus);
			paintGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		if(alert){
			paintGraphics.setColor(properties.colorAlert);
			paintGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
			}		
		if (this.pieceID>=0) {
			int pieceDim=properties.piecesDimension;
			int offset = properties.pieceOffset;
			int color=0, pieceNumber=pieceID;
			if (pieceID>=6){
				color=1;
				pieceNumber-=6;
			}
			paintGraphics.drawImage(pieceImage, 0+offset, 0+offset, this.getWidth()-offset, this.getHeight()-offset,pieceNumber*pieceDim,color*pieceDim,pieceNumber*pieceDim+pieceDim,color*pieceDim+pieceDim, null);
		}
		this.repaint();//necessario 	
	}	
}
