/* Class which builds the GUI */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Graphics {
	static JFrame window;
	static JPanel player;
	static JButton play;
	static JButton stop;
	static JButton pause;
	static JButton open;

	 // Initialises the basic GUI
	
	public Graphics() {
		window = new JFrame("Music Player for Wave");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setMinimumSize(new Dimension(400, 200));

		player = new JPanel();
		window.add(player);
		window.pack();
		window.setVisible(true);

		createButtons();
	} 

		public static void createButtons() {

			open = new JButton();
			open.setText("Open");
			openListener();
			player.add(open);

			stop = new JButton();
			stop.setText("Stop");
			stopListener();
			player.add(stop);

			window.pack();
		}

		
	public static void main(String args[]) {
		new Graphics();
	}
	
	public static void openListener() {
		Graphics.open.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent me) {

			}

			@Override
			public void mousePressed(MouseEvent me) {

			}

			@Override
			public void mouseExited(MouseEvent me) {

			}

			@Override
			public void mouseEntered(MouseEvent me) {

			}

			@Override
			public void mouseClicked(MouseEvent me) {

				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							PlaySong.openFile();
						} catch (UnsupportedAudioFileException e) {
							System.out.println("Unsupported File Format");
						} catch (IOException e) {
							System.out.println("Try Again");
						} catch (LineUnavailableException e) {
							System.out.println("Try Again");
						}

					}
				});

				thread.start();
			}
		});

	}


	public static void stopListener() {
		Graphics.stop.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent me) {

			}

			@Override
			public void mousePressed(MouseEvent me) {

			}

			@Override
			public void mouseExited(MouseEvent me) {

			}

			@Override
			public void mouseEntered(MouseEvent me) {

			}

			@Override
			public void mouseClicked(MouseEvent me) {

				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {

						PlaySong.stopSong();

					}
				});

				thread.start();
			}
		});

	}


}