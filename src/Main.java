import java.awt.EventQueue;

import javax.swing.JFrame;

import view.ChessFrame;


public class Main {

	public static void main(String[] args) {

			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {
					JFrame frame = new ChessFrame();
					frame.setSize(500, 500);
					frame.setLocation(500, 200);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
			});
		

		
		
		
		
		
		
	}

}
