package pokemonGUI;

import funcionalidad.excepciones.PokemonNoExisteException;
import funcionalidad.tipos.Pokemon;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

/**
 * Muestra todos los pokemons
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Mostrar extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int indice = -1;

	/**
	 * Create the dialog.
	 */
	public Mostrar() {

		setResizable(false);
		setTitle("Listado de pokemons");

		if (Principal.listaPokemon.size() == 0) {
			JOptionPane.showMessageDialog(null, "No existen pokemons", "ERROR", JOptionPane.ERROR_MESSAGE);
			dispose();
		} else {
			siguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarSiguiente();
				}
			});
			anterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarAnterior();
				}
			});

			lblTipo.setText("Tipo:");
			lblNombre.setText("Nombre:");
			comboBox.setEnabled(false);
			textField.setEnabled(false);
			okButton.setVisible(false);
			cancelButton.setText("Volver");
			actualizar();
		}
	}

	void actualizar() {
		if (Principal.listaPokemon.size() == 0) {
			return;
		}

		try {
			indice = 0;
			mostrarPokemon(Principal.listaPokemon.get(indice));
			comprobarBotones();
		} catch (PokemonNoExisteException e) {

		}

	}

	/**
	 * Muestra el siguiente
	 */
	private void mostrarSiguiente() {
		try {
			mostrarPokemon(Principal.listaPokemon.get(++indice));
			comprobarBotones();
		} catch (PokemonNoExisteException e) {

		}

	}

	/**
	 * Muestra el anterior
	 */
	private void mostrarAnterior() {
		try {
			mostrarPokemon(Principal.listaPokemon.get(--indice));
			comprobarBotones();
		} catch (PokemonNoExisteException e) {

		}

	}

	/**
	 * Muestra el pokemon pasado por parametros
	 * 
	 * @param pokemon
	 */
	@SuppressWarnings("unchecked")
	private void mostrarPokemon(Pokemon pokemon) {
		textField.setText(pokemon.getNombre());
		try {
			lblIcono.setIcon(new ImageIcon(
					VentanaPadre.class.getResource("/resources/img/characters/" + textField.getText() + ".png")));
		} catch (Exception e) {
		}
		comboBox.addItem(pokemon.getClass().getSimpleName());
		comboBox.setSelectedItem(pokemon.getClass().getSimpleName());
	}

	/**
	 * Si no hay siguiente pokemon, se bloquea el boton de siguiente y viceversa
	 */
	private void comprobarBotones() {

		try {
			if (Principal.listaPokemon.get(indice + 1) != null)
				siguiente.setEnabled(true);
		} catch (PokemonNoExisteException e) {
			siguiente.setEnabled(false);
		}

		try {
			if (Principal.listaPokemon.get(indice - 1) != null)
				anterior.setEnabled(true);

		} catch (PokemonNoExisteException e) {
			// TODO Auto-generated catch block
			anterior.setEnabled(false);
		}

	}
}
