package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/**
 * Die Klasse TrasapFrame ist die erste Klasse, die aufgerufen wird. 
 * Sie initialisiert eine Menuleiste, sowie die dazugehoerigen JButton's
 * @author Timothy Kura
 */
public class TrasapFrame {

	final JPanel topPanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.RIGHT));

	final JButton minimize = new JButton("-");
	final JButton maximize = new JButton("[]");
	final JButton exit = new JButton("X");
	
	private static Point compCoords;

	/**
	 * Startet die main Methode und oeffnet ein JFrame
	 * @param args 
	 */
	public static void main(String[] args) {
		new TrasapFrame();
	}

	/**
	 * Initialisiert eine neue Instanz dieser Klasse
	 */
	public TrasapFrame() {

		JFrame frame = new JFrame("Trasap");

		ComponentResizer cr = new ComponentResizer();
		cr.registerComponent(frame);
		cr.setSnapSize(new Dimension(10, 10));
		compCoords = null;

		topPanel.add(minimize);
		topPanel.add(maximize);
		topPanel.add(exit);
		
		frame.add(BorderLayout.NORTH, topPanel);
		frame.setPreferredSize(new Dimension(1080, 664));
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setResizable(true);

		Image logo = Toolkit.getDefaultToolkit().getImage("./resources/logo-1.png");
		frame.setIconImage(logo);

		HomePanel homeP = new HomePanel();
		homeP.setLayout(new BoxLayout(homeP, BoxLayout.PAGE_AXIS));

		frame.add(homeP, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		minimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});

		maximize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
			}
		});

		topPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				compCoords = null;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				compCoords = e.getPoint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		topPanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				frame.setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}
		});
	}
}
