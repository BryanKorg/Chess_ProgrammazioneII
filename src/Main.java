import java.awt.EventQueue;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;

import javax.swing.JFrame;

import view.ChessFrame;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ChessFrame frame = new ChessFrame();
				frame.setSize(500, 500);
				frame.setLocation(500, 200);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				/* TODO sembra non andare, elimina
				frame.getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener(){

					@Override
					public void ancestorMoved(HierarchyEvent arg0) {					
					}

					@Override
					public void ancestorResized(HierarchyEvent arg0) {
						System.out.printf("w: %d, h: %d, min: %d.\n",frame.WIDTH,frame.HEIGHT,1);
						frame.changeSize(frame.WIDTH,frame.HEIGHT);
					}           
		        });*/
			}
		});	
	}

}
