package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import model.PColor;
import view.properties;

/**
 *  Quadrato della scacchiera
 */
public class Tile extends JComponent implements MouseListener {

	private static final long serialVersionUID = 1L;
	private Image image=null;
	private int pieceID = -1;
	private PColor c;
	private boolean mouseEntered =false;
	private boolean focus = false;
	private boolean alert = false;
	private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
	
	public Tile(PColor c){
		this.c=c;
		//Border blackline = BorderFactory.createLineBorder(Color.black);
		//this.setBorder(blackline);
	}
	
	public void setPieceID(int pieceID){
		this.pieceID=pieceID;
	}
	/*				
	@Override
	
	protected void paintComponent(Graphics g){//renderizza la grafica
		super.paintComponent(g);
		
		if(image!=null){	
			//disegna l'immagine
			g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);
			}
		this.setBackground(Color.cyan);

		if(focus){
			this.setBackground(Color.green);
			}
		if(alert){
			this.setBackground(Color.red);
			}
		if(mouseEntered){
			System.out.println("over" );
			this.setBackground(Color.yellow);
		}
		this.repaint();//necessario 	
	}*/
	
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
	
	/*nuovooooo*/
	/**
	 * Method to paint the square component
	 */
	
	@Override
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		int dim=properties.tilesDimension;
		Image squareImage = new ImageIcon(properties.tilesImagePath).getImage();
		Graphics2D paintGraphics = (Graphics2D) graphics;
		paintGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		paintGraphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		paintGraphics.drawImage(squareImage, 0, 0, this.getWidth(), this.getHeight(),c.ordinal()*dim,0,c.ordinal()*dim+dim,dim, null); //TODO controlla ordinal...
		//Empathize the selected square
		if(focus){
			paintGraphics.setColor(properties.colorFocus);//TODO rifare il colore
			paintGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		if(alert){
			paintGraphics.setColor(properties.colorAlert);//TODO rifare il colore
			paintGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		if(mouseEntered){
			paintGraphics.setColor(properties.colorMouseEntered);//TODO rifare il colore
			paintGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		/*if(image!=null){	
			//disegna l'immagine
			graphics.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);
			}*/
		
		if (this.pieceID>=0) {
			int pieceDim=properties.piecesDimension;
			int offset = properties.pieceOffset;
			int color=0, pieceNumber=pieceID;
			if (pieceID>=6){
				color=1;
				pieceNumber-=6;
			}
			Image pieceImage = new ImageIcon(properties.piecesImagePath).getImage();
			paintGraphics.drawImage(pieceImage, 0+offset, 0+offset, this.getWidth()-offset, this.getHeight()-offset,pieceNumber*pieceDim,color*pieceDim/*TODO il colore del pezzo!*/,pieceNumber*pieceDim+pieceDim,color*pieceDim+pieceDim, null);
		}
		this.repaint();//necessario 	
	}
	  

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseEntered =true;
		repaint();
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseEntered =false;
		repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		notifyListeners(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Methods to use events
	 * @param mouseAdapter
	 */
    public void addActionListener(ActionListener listener)
    {
        listeners.add(listener);
    }
    
    private void notifyListeners(MouseEvent e)
    {
        ActionEvent evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, new String(), e.getWhen(), e.getModifiers());
        synchronized(listeners)
        {
            for (int i = 0; i < listeners.size(); i++)
            {
                ActionListener tmp = listeners.get(i);
                tmp.actionPerformed(evt);
            }
        }
    }
	
}
