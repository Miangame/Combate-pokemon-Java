package pokemonGUI;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Muestra un acerca de...
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class AcercaDe extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setModal(true);
		setResizable(false);
		setTitle("Acerca de");
		getContentPane().setBackground(Color.ORANGE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 448, 235);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		{
			Font fuente = new Font("Tahoma", 0, 19);
			JLabel lblMiguelngelGaviln = new JLabel("Miguel \u00C1ngel Gavil\u00E1n Merino");
			lblMiguelngelGaviln.setFont(fuente);
			lblMiguelngelGaviln.setForeground(Color.LIGHT_GRAY);
			lblMiguelngelGaviln.setBounds(87, 12, 331, 48);
			contentPanel.add(lblMiguelngelGaviln);
		}
		{
			JLabel lblProyectoFinalDe = new JLabel("Proyecto final de Java");
			lblProyectoFinalDe.setBounds(12, 208, 166, 15);
			contentPanel.add(lblProyectoFinalDe);
		}
		{
			JLabel lblIesGranCapitn = new JLabel("IES Gran Capit\u00E1n. 1 DAW");
			lblIesGranCapitn.setBounds(249, 208, 187, 15);
			contentPanel.add(lblIesGranCapitn);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(AcercaDe.class.getResource("/resources/img/fondoAcercaDe.png")));
			label.setBounds(0, 0, 448, 235);
			contentPanel.add(label);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 235, 448, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
