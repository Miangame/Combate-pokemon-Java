package pokemonGUI;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import funcionalidad.excepciones.PokemonNoExisteException;
import funcionalidad.tipos.Pokemon;

import java.awt.event.ActionEvent;

/**
 * Busca un pokemon por nombre
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class BuscarNombre extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public BuscarNombre() {
		setTitle("Buscar por nombre");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pokemon pokemon;
				try {
					pokemon = Principal.listaPokemon.getPokemonNombre(textField.getText());
					mostrarPokemon(pokemon);
				} catch (PokemonNoExisteException e1) {
					JOptionPane.showMessageDialog(null, "No existe el pokemon", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		lblTipo.setVisible(false);
		comboBox.setVisible(false);
		comboBox.setEnabled(false);
		okButton.setText("Buscar");
		siguiente.setVisible(false);
		anterior.setVisible(false);
		comboBox_2.setVisible(false);
	}

	/**
	 * Muestra el pokemon encontrado
	 * 
	 * @param pokemon
	 */
	@SuppressWarnings("unchecked")
	private void mostrarPokemon(Pokemon pokemon) {
		lblTipo.setVisible(true);
		lblTipo.setText("Tipo");
		comboBox.setVisible(true);
		textField.setText(pokemon.getNombre());
		try {
			lblIcono.setIcon(new ImageIcon(
					VentanaPadre.class.getResource("/resources/img/characters/" + textField.getText() + ".png")));
		} catch (Exception e) {
		}
		comboBox.addItem(pokemon.getClass().getSimpleName());
		comboBox.setSelectedItem(pokemon.getClass().getSimpleName());
	}

}
