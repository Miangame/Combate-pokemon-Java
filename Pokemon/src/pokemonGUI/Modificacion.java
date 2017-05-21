package pokemonGUI;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.excepciones.PokemonNoExisteException;
import funcionalidad.excepciones.PokemonYaExisteException;
import funcionalidad.tipos.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * Modificacion de pokemons
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Modificacion extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 * 
	 * @param object
	 */
	@SuppressWarnings("unchecked")
	public Modificacion(Pokemon pokemon) {
		siguiente.setVisible(false);
		anterior.setVisible(false);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPokemon(pokemon);

				dispose();
			}
		});
		lblTipo.setText("Nuevo tipo:");
		lblNombre.setText("Nuevo nombre:");
		String[] tipos = { "Agua", "Fuego", "Eléctrico", "Volador", "Planta" };
		comboBox.setModel(new DefaultComboBoxModel<>(tipos));
		textField.setText(pokemon.getNombre());
	}

	/**
	 * Modifica el pokemon
	 * 
	 * @param pokemon
	 */
	private void modificarPokemon(Pokemon pokemon) {

		try {
			switch (comboBox.getSelectedItem().toString()) {
			case "Agua":
				Principal.listaPokemon.annadir(new Agua(textField.getText(), pokemon.getVida()));
				break;

			case "Fuego":
				Principal.listaPokemon.annadir(new Fuego(textField.getText(), pokemon.getVida()));
				break;

			case "Electrico":
				Principal.listaPokemon.annadir(new Electrico(textField.getText(), pokemon.getVida()));
				break;

			case "Planta":
				Principal.listaPokemon.annadir(new Planta(textField.getText(), pokemon.getVida()));
				break;

			default:
				Principal.listaPokemon.annadir(new Volador(textField.getText(), pokemon.getVida()));
				break;
			}

			Principal.listaPokemon.eliminar(pokemon);
			Principal.listaPokemon.escribir();
		} catch (PokemonYaExisteException | PokemonNoExisteException | IOException | EnergiaNoValidaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}

		JOptionPane.showMessageDialog(null, "Modificado correctamente");

	}
}
