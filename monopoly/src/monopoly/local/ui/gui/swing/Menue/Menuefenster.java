package monopoly.local.ui.gui.swing.Menue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import monopoly.local.domain.Monopoly;
import monopoly.local.ui.gui.swing.Spielfenster;
import monopoly.local.valueobjects.Spieler;
import net.miginfocom.swing.MigLayout;

public class Menuefenster {
		//Variablen fuer das Menue
		private JFrame menue;
		private MenuePanel mPanel;
		private Font mFont;
		private Monopoly monopoly;
		private MenueHauptPanel hauptPanel;
		private MenueBeitreten panel;
		private MenueAustreten panel2;
		private MenueLaden panel3;
		
	/**
	 * Konstruktor der Klasse Menuefenster
	 * Frame menue wird erstellt und das Menuefenster gestartet
	 */
	public Menuefenster(){
		menue = new JFrame();
		menue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menue.setLayout(new GridLayout(1, 1));
		monopoly = new Monopoly();
		mInit();
	}

	/**
	 * startet das Menuefenster
	 * enthält alle ActionListener für das eigendlich Menuefenster
	 */
	public void mInit(){
		hauptPanel = new MenueHauptPanel();
		menue.add(hauptPanel.getmPanel(), 0,0);
		
		//ActionListener für den Beitreten-Button
		hauptPanel.getmButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menue.remove(hauptPanel.getmPanel());
				menue.repaint();
				mBeitreten();
			}
		});
		
		//ActionListener für den Austreten-Button
		hauptPanel.getmButton1().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menue.remove(hauptPanel.getmPanel());
				menue.repaint();
				mEntfernen();
			}
		});
		
		//ActionListener für den Spiel-starten-Button
		hauptPanel.getmButton2().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(monopoly.getAllSpieler().size() < 2){
					JOptionPane fehler =  new JOptionPane();
					fehler.showMessageDialog(menue, "Es werden mindestens zwei Spieler benötigt");
					return;
				}
				Spielfenster spFenster = new Spielfenster(monopoly);
				spFenster.sInit();
				menue.dispose();
				
			}
		});
		
		//ActionListener für den Spiel-laden-Button
		hauptPanel.getmButton3().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menue.remove(hauptPanel.getmPanel());
				menue.repaint();
				mLaden();
			}
		});
		
		menue.setSize(600, 600);
		menue.setVisible(true);
	}
	
	/**
	 * Methode die, die ActionListener für das Beitretenmenuefenster enthält
	 */
	public void mBeitreten(){
		panel = new MenueBeitreten();
		menue.add(panel.getmPanel(),"cell 0 0, pushx, growx, shrinkx ");
		menue.validate();
		
		//ActionListener für den Beitreten-Button
		panel.getmButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!panel.getmTextField().getText().isEmpty()){
					String name = panel.getmTextField().getText();
					int spielernummer = monopoly.getAllSpieler().size();
					Spieler spieler = new Spieler(name,spielernummer+1,monopoly.getLos(),2000);
					boolean bestÃ¤tigung = monopoly.beitreten(spieler);
					showErfolg(bestÃ¤tigung);
					menue.remove(panel.getmPanel());
					mInit();
				}
						
			}
		});
		
		//ActionListener für den zurueck-Button
		panel.getmButton2().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menue.remove(panel.getmPanel());
				mInit();
			}
		});
		
		menue.validate();
		menue.setSize(600, 600);
		menue.setVisible(true);
	}
	
	/**
	 * Methode die, die Actionlisterner für das Austretenmenuefenster enthaelt
	 */
	public void mEntfernen(){
		panel2 = new MenueAustreten(monopoly);
		menue.add(panel2.getmPanel(),"cell 0 0, pushx, growx, shrinkx ");
		
		//ActionListener fuer den Austreten-Button
		panel2.getmButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!panel2.getListe().isSelectionEmpty()){
					boolean bestÃ¤tigung = monopoly.entfernen(panel2.getListe().getSelectedIndex()+1);
					showErfolg(bestÃ¤tigung);
					menue.remove(panel2.getmPanel());
					mInit();
				}else{
					menue.remove(panel2.getmPanel());
					mInit();
				}
			}
		});
		
		//ActionListener für den zurueck-Button
		panel2.getmButton2().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menue.remove(panel2.getmPanel());
				mInit();
			}
		});
		
		menue.setSize(600, 600);
		menue.setVisible(true);
	}
	
	/**
	 * Methode die, die ActionListener fuer das Spielstand-laden-Menuefenster enthaelt
	 */
	public void mLaden(){
		panel3 = new MenueLaden();
		menue.add(panel3.getMlPanel(),"cell 0 0, pushx, growx, shrinkx ");
		
		//ActionListener für den Laden-Button
		panel3.getMlButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String datei = "";
				if(!panel3.getMlListe().isSelectionEmpty()){
					JOptionPane OP = new JOptionPane();
					OP.showMessageDialog(menue, "mach ich später");
					datei = panel3.getListenInhalt();
						monopoly.spielStandLaden(datei);
						monopoly.saveFilesLaden();
				}
//				else{
//					menue.remove(panel3.getMlPanel());
//					mInit();
//				}
			}
		});
		
		//ActionListener für den zurueck-Button
		panel3.getMlButton2().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menue.remove(panel3.getMlPanel());
				mInit();
			}
		});
		
		menue.validate();
		menue.setSize(600, 600);
		menue.setVisible(true);
	}
	
	/**
	 * Methode zum sichtbar setzten des Menufensters
	 */
	public void mSetVisible(){
		menue.setVisible(true);
	}
	
	/**
	 * Dialogfenster um dem Nutzer zu bestätigen das sich etwas getan hat
	 * @param bestÃ¤tigung: boolean-Wert ob eine Bestaetigung gebraucht wird
	 */
	//ggf. mit JOptionPane ersetzen?
	public void showErfolg(boolean bestÃ¤tigung){
		final JDialog dialog = new JDialog ();
		dialog.setModal (true);
		dialog.setAlwaysOnTop (true);
		dialog.setModalityType (ModalityType.APPLICATION_MODAL);
		JLabel label = new JLabel();
		JButton button = new JButton("Ok");
		if(bestÃ¤tigung){
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


