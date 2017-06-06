package pokemonGUI;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funcionalidad.Usuario;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Pantalla final que muestra el ganador y da la opción de jugar otra partida
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class PantallaFinal extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 * 
	 * @param jugador
	 */
	public PantallaFinal(Usuario jugador) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 448, 235);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel label = new JLabel("El ganador es: " + jugador.getAlias());
		label.setBounds(46, 39, 311, 33);
		contentPanel.add(label);
		{
			JLabel label_1 = new JLabel("");
			label_1.setIcon(new ImageIcon(PantallaFinal.class.getResource("/resources/img/fondoFinal.png")));
			label_1.setBounds(0, 0, 448, 235);
			contentPanel.add(label_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 235, 448, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Volver a jugar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EleccionPokemon eleccion = new EleccionPokemon();
						eleccion.setVisible(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Principal principal = new Principal();
						principal.setVisible(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
