package pokemonGUI;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 * Ventana padre de la que heredarán todas las ventanas de la gestion de
 * pokemons
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class VentanaPadre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final JPanel contentPanel = new JPanel();
	protected static JTextField textField;

	@SuppressWarnings("rawtypes")
	protected static JComboBox comboBox;
	@SuppressWarnings("rawtypes")
	protected static JComboBox comboBox_2;
	protected JButton okButton;
	protected JLabel lblNombre;
	protected JLabel lblTipo;
	protected JButton siguiente;
	protected JButton anterior;
	protected JButton cancelButton;
	protected JLabel lblIcono;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("rawtypes")
	public VentanaPadre() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 448, 235);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			lblTipo = new JLabel();
			lblTipo.setBounds(45, 32, 141, 15);
			contentPanel.add(lblTipo);
		}

		comboBox = new JComboBox();
		comboBox.setSelectedItem(null);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					lblIcono.setIcon(new ImageIcon(VentanaPadre.class.getResource(
							"/resources/img/characters/" + comboBox.getSelectedItem().toString() + ".png")));
				} catch (Exception e1) {

				}
			}
		});
		comboBox.setBounds(45, 59, 141, 24);
		contentPanel.add(comboBox);
		{
			comboBox_2 = new JComboBox();
			comboBox_2.setBounds(198, 59, 141, 24);
			contentPanel.add(comboBox_2);
		}

		lblNombre = new JLabel("Introduzca el nombre:");
		lblNombre.setBounds(45, 113, 168, 15);
		contentPanel.add(lblNombre);

		textField = new JTextField();
		textField.setBounds(45, 140, 141, 19);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			lblIcono = new JLabel("");
			lblIcono.setBounds(246, 23, 160, 147);
			contentPanel.add(lblIcono);
		}

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(VentanaPadre.class.getResource("/resources/img/fondoMenusGestion.png")));
		label.setBounds(0, 0, 448, 235);
		contentPanel.add(label);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 235, 448, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					anterior = new JButton("<-");
					buttonPane.add(anterior);
				}
				{
					siguiente = new JButton("->");
					buttonPane.add(siguiente);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
