package pokemonGUI;

import funcionalidad.excepciones.PokemonNoExisteException;
import funcionalidad.tipos.Pokemon;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * Da de baja un pokemon
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class BajaPokemon extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")

	public BajaPokemon() {
		lblIcono.setLocation(244, 59);
		lblTipo.setBounds(45, 31, 283, 15);
		lblTipo.setText("Escoja el pokemon que quiere eliminar");
		textField.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel<>(Principal.listaPokemon.getLista().toArray()));

		lblNombre.setVisible(false);
		textField.setVisible(false);

		siguiente.setVisible(false);
		anterior.setVisible(false);

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bajaPokemon();
				dispose();
			}
		});
		okButton.setText("Eliminar");
	}

	/**
	 * Elimina un pokemon del archivo "pokemons.obj"
	 */
	private void bajaPokemon() {
		try {
			Principal.listaPokemon.eliminar((Pokemon) comboBox.getSelectedItem());
			Principal.listaPokemon.escribir();
			JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente");
		} catch (PokemonNoExisteException | IOException e1) {
			JOptionPane.showMessageDialog(null, "El pokemon no existe!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

}
