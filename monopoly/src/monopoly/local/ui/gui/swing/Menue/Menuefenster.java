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
	//Variablen f�r das Menue
		private JFrame menue;
		private MenuePanel mPanel;
		private Font mFont;
		private Monopoly monopoly;
		private MenueHauptPanel hauptPanel;
		private MenueBeitreten panel;
		private MenueAustreten panel2;
		
	public Menuefenster(){
		menue = new JFrame();
		menue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menue.setLayout(new GridLayout(1, 1));
		monopoly = new Monopoly();
		mInit();
	}

	public void mInit(){
		hauptPanel = new MenueHauptPanel();
		menue.add(hauptPanel.getmPanel(), 0,0);
		hauptPanel.getmButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menue.remove(hauptPanel.getmPanel());
				menue.repaint();
				mBeitreten();
			}
		});
		
		hauptPanel.getmButton1().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menue.remove(hauptPanel.getmPanel());
				menue.repaint();
				mEntfernen();
			}
		});
		
		hauptPanel.getmButton2().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(monopoly.getAllSpieler().size() < 2){
					JOptionPane fehler =  new JOptionPane();
					fehler.showMessageDialog(menue, "Es werden mindestens zwei Spieler ben�tigt");
					return;
				}
				Spielfenster spFenster = new Spielfenster(monopoly);
				spFenster.sInit();
				menue.dispose();
				
			}
		});
		
		menue.setSize(600, 600);
		menue.setVisible(true);
	}
	public void mBeitreten(){
		panel = new MenueBeitreten();
		menue.add(panel.getmPanel(),"cell 0 0, pushx, growx, shrinkx ");
		menue.validate();
		
		panel.getmButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!panel.getmTextField().getText().isEmpty()){
					String name = panel.getmTextField().getText();
					int spielernummer = monopoly.getAllSpieler().size();
					Spieler spieler = new Spieler(name,spielernummer+1,monopoly.getLos(),2000);
					boolean bestätigung = monopoly.beitreten(spieler);
					showErfolg(bestätigung);
					menue.remove(panel.getmPanel());
					mInit();
				}
						
			}
		});
		
		menue.validate();
		menue.setSize(600, 600);
		menue.setVisible(true);
	}
	
	public void mEntfernen(){
		panel2 = new MenueAustreten(monopoly);
		menue.add(panel2.getmPanel(),"cell 0 0, pushx, growx, shrinkx ");
		
		panel2.getmButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!panel2.getListe().isSelectionEmpty()){
					boolean bestätigung = monopoly.entfernen(panel2.getListe().getSelectedIndex()+1);
					showErfolg(bestätigung);
					menue.remove(panel2.getmPanel());
					mInit();
				}else{
					menue.remove(panel2.getmPanel());
					mInit();
				}
			}
		});
		
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


