package pokemonGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import funcionalidad.tipos.Pokemon;

/**
 * Pantalla para seleccionar el pokemon que se quiere modificar
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class ModificarPokemon extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public ModificarPokemon() {
		setTitle("Modificación de pokemons");
		lblIcono.setLocation(248, 52);
		lblTipo.setBounds(45, 32, 300, 15);
		lblTipo.setText("Escoja el pokemon que quiere modificar: ");

		siguiente.setVisible(false);
		anterior.setVisible(false);
		lblNombre.setVisible(false);
		textField.setVisible(false);

		textField.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel<>(Principal.listaPokemon.getLista().toArray()));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificacion modificacion = new Modificacion((Pokemon) comboBox.getSelectedItem());
				modificacion.setVisible(true);
				dispose();
			}
		});
	}

}
