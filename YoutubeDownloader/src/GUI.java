import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI implements ActionListener{
	
	private JFrame frame;
	private JPanel panel;
	
	private String home = System.getProperty("user.home");
	private String defaultDownloadPath = home+"/Documents/youtube-dl/Downloads/";
	private String downloadPath = defaultDownloadPath;
	private String link = "https://www.youtube.com/watch?v=5QvgLlFyeok";
	private String filename = "%(title)s-%(id)s.%(ext)s";
	
	
	public GUI() {
		
		frame = new JFrame();
		
		JButton button = new JButton("Download");
		button.addActionListener(this);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		panel.setLayout(new GridLayout());
		panel.add(button);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new GUI();
		
	}
	
	public static String readClipboard(){
	    try {
			return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "";
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		link = readClipboard();

		
		try {
			Process process = Runtime.getRuntime().exec("youtube-dl -o " + downloadPath + filename + " " + link);
		    BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(process.getInputStream()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		 
		    reader.close();
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
