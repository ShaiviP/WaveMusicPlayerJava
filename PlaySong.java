
/*
 Class contains the logic for opening,playing and stopping a .wav file
 */

//header files
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.sound.sampled.*;


public class PlaySong {

	static JFileChooser browse;
	static SourceDataLine audioSource;
	static int BUFFER_SIZE;

	static JFrame window;
	static JPanel player;
	static JButton play;
	static JButton stop;
	static JButton pause;
	static JButton open;

	// Function to sync the variables between GUI and logic
	 
	private static void setter() {
		window = Graphics.window;
		player = Graphics.player;
		play = Graphics.play;
		stop = Graphics.stop;
		pause = Graphics.pause;
		open = Graphics.open;

	}

	// Function to stop the currently playing song
	 
	public static void stopSong() {
		if (audioSource.isOpen()) {
			System.out.println("File is stop");
			audioSource.stop();
		}
	}

	
	//Function to open the .wav file and start playing it
	 
	public static void openFile() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		setter();
		browse = new JFileChooser();
		int ret = browse.showOpenDialog(player);

		if (ret == JFileChooser.APPROVE_OPTION) {
			// Browse using File browser to get the file of song
			File file = browse.getSelectedFile();
			System.out.println("To play: " + file.getName());

			// To Create an audio stream
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			AudioFormat audioFormat = stream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class,
					audioFormat);

			// Adds the stream to the source data line

			audioSource = (SourceDataLine) AudioSystem.getLine(info);
			audioSource.open(audioFormat);

			audioSource.start();

			// Reads the dataline in buffer of length BUFFER_SIZE
			int readBytes = 0;
			BUFFER_SIZE = audioSource.getBufferSize();
			byte[] audioBuffer = new byte[BUFFER_SIZE];

			while (readBytes != -1) {
				readBytes = stream.read(audioBuffer, 0, audioBuffer.length);
				if (readBytes >= 0) {
					audioSource.write(audioBuffer, 0, readBytes);
				}
			}

			audioSource.drain();
			audioSource.stop();
			audioSource.close();

			System.out.println("Finished playing : " + file.getName());
		} else {
			System.out.println("Command cancelled by user.");
		}
	}
}