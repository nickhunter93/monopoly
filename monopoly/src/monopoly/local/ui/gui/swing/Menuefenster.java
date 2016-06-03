package monopoly.local.ui.gui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import monopoly.local.domain.Monopoly;
import monopoly.local.valueobjects.Spieler;
import net.miginfocom.swing.MigLayout;

public class Menuefenster {
	//Variablen f�r das Menue
		private JFrame menue;
		private JButton mButton;
		private JButton mButton1;
		private JButton mButton2;
		private JButton mButton3;
		private JTextField mTextField;
		private MenuePanel mPanel;
		private Font mFont;
		private Monopoly monopoly;
		
		
	public Menuefenster(){
		monopoly = new Monopoly();
		mInit();
	}

	public void mInit(){
		//Menue	
		MigLayout mLayout = new MigLayout("", "[]20[]20[]", "[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]");
		mFont = new Font("Berlin Sans FB",Font.ITALIC,20);
		
		menue = new JFrame("Menue");
		menue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		mButton = new JButton("Beitreten");
		mButton1 = new JButton("Verlassen");
		mButton2 = new JButton("Spiel starten");
		mButton3 = new JButton("Spiel laden");
		mPanel = new MenuePanel(600,600);
		
		mPanel.setLayout(mLayout);
		mPanel.add(mButton,"w 425, h 50, cell 2 20, pushx, growx");
		mPanel.add(new JLabel(""), "w 25");
		mPanel.add(mButton1,"w 425, h 50, cell 2 21, pushx, growx");
		mPanel.add(new JLabel(""), "w 25");
		mPanel.add(mButton2,"w 425, h 50, cell 2 22, pushx,growx");
		mPanel.add(new JLabel(""), "w 25");
		mPanel.add(mButton3,"w 425, h 50, cell 2 23, pushx, growx");
		mPanel.add(new JLabel(""), "w 25");
		menue.add(mPanel);
		
		mButton.setToolTipText("Du tritts einem Spiel bei");
		mButton1.setToolTipText("Du verl�sst ein Spiel");
		mButton2.setToolTipText("Du startest ein Spiel");
		mButton3.setToolTipText("Du l�dst ein angefangenes Spiel");
		
		mButton.setBackground(new Color(255,155,55));
		mButton1.setBackground(new Color(255,155,55));
		mButton2.setBackground(new Color(255,155,55));
		mButton3.setBackground(new Color(255,155,55));
		
		mButton.setBorderPainted(false);
		mButton1.setBorderPainted(false);
		mButton2.setBorderPainted(false);
		mButton3.setBorderPainted(false);
		
		mButton.setFont(mFont);
		mButton1.setFont(mFont);
		mButton2.setFont(mFont);
		mButton3.setFont(mFont);
		
		mButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menue.dispose();
				mBeitreten();
			}
		});
		
		mButton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Spielfenster spFenster = new Spielfenster();
				spFenster.sInit();
				menue.dispose();
			}
		});
		
		menue.setSize(600, 600);
		menue.setVisible(true);
	}
	public void mBeitreten(){
		//Menue	
				MigLayout mLayout = new MigLayout("", "[]20[]20[]", "[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]");
				mFont = new Font("Berlin Sans FB",Font.ITALIC,20);
				
				menue = new JFrame("Menue");
				menue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
				mTextField = new JTextField("Spielername");
				mButton = new JButton("Bestätigen");
				mPanel = new MenuePanel(600,600);
				
				mPanel.setLayout(mLayout);
				mPanel.add(mTextField,"w 425, h 50, cell 2 20, pushx, growx");
				mPanel.add(new JLabel(""), "w 25");
				mPanel.add(mButton,"w 425, h 50, cell 2 20, pushx, growx");
				mPanel.add(new JLabel(""), "w 25");
				menue.add(mPanel);
				
				mButton.setToolTipText("Du tritts einem Spiel bei");
				
				mTextField.setBackground(new Color(255,155,55));
				mButton.setBackground(new Color(255,155,55));
				
				mButton.setBorderPainted(false);
				
				mButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(!mTextField.getText().isEmpty()){
							String name = mTextField.getText();
							int spielernummer = monopoly.getAllSpieler().size();
							Spieler spieler = new Spieler(name,spielernummer+1,monopoly.getLos(),2000);
							boolean bestätigung = monopoly.beitreten(spieler);
							showErfolg(bestätigung);
							menue.dispose();
							mInit();
						}
						
					}
				});
				
				mTextField.setFont(mFont);
				mButton.setFont(mFont);
				
				menue.setSize(600, 600);
				menue.setVisible(true);
	}
	
	public void mEntfernen(){
		//Menue	
				JTextArea mTextArea;
				MigLayout mLayout = new MigLayout("", "[]20[]20[]", "[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]20[]");
				mFont = new Font("Berlin Sans FB",Font.ITALIC,20);
				
				menue = new JFrame("Menue");
				menue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
				mTextArea = new JTextArea("Spielername");
				mTextArea.setEditable(false);
				int pos = 0;
				for(Spieler spieler : monopoly.getAllSpieler()){
					String str = spieler.getSpielerName();
					mTextArea.insert(str, pos);
					pos++;
				}
				mButton = new JButton("Bestätigen");
				mPanel = new MenuePanel(600,600);
				
				mPanel.setLayout(mLayout);
				mPanel.add(mTextArea,"w 425, h 50, cell 2 20, pushx, growx");
				mPanel.add(new JLabel(""), "w 25");
				mPanel.add(mButton,"w 425, h 50, cell 2 20, pushx, growx");
				mPanel.add(new JLabel(""), "w 25");
				menue.add(mPanel);
				
				mButton.setToolTipText("Du tritts aus einem Spiel aus");
				
				mTextField.setBackground(new Color(255,155,55));
				mButton.setBackground(new Color(255,155,55));
				
				mButton.setBorderPainted(false);
				
				mButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(!mTextField.getText().isEmpty()){
							String name = mTextField.getText();
							int spielernummer = monopoly.getAllSpieler().size();
							Spieler spieler = new Spieler(name,spielernummer+1,monopoly.getLos(),2000);
							boolean bestätigung = monopoly.beitreten(spieler);
							showErfolg(bestätigung);
							menue.dispose();
							mInit();
						}
						
					}
				});
				
				mTextField.setFont(mFont);
				mButton.setFont(mFont);
				
				menue.setSize(600, 600);
				menue.setVisible(true);
	}
	
	public void showErfolg(boolean bestätigung){
		final JDialog dialog = new JDialog ();
		dialog.setModal (true);
		dialog.setAlwaysOnTop (true);
		dialog.setModalityType (ModalityType.APPLICATION_MODAL);
		JLabel label = new JLabel();
		JButton button = new JButton("Ok");
		if(bestätigung){
			label.setText("Erfolgreich");
		}else{
			label.setText("Fehlgeschlagen");
		}
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLayout(new BorderLayout());
		dialog.add(BorderLayout.NORTH,label);
		dialog.add(BorderLayout.SOUTH,button);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		dialog.setSize(200, 200);
		dialog.setVisible(true);
	}
}


