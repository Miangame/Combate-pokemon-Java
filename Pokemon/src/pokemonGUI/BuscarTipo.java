package pokemonGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import funcionalidad.EnvoltorioPokemons;
import funcionalidad.excepciones.PokemonNoExisteException;
import funcionalidad.excepciones.PokemonYaExisteException;
import funcionalidad.tipos.Pokemon;

/**
 * Busca un pokemon por tipo
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class BuscarTipo extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EnvoltorioPokemons listaPokemon;
	private int indice = -1;

	/**
	 * Create the dialog.
	 */
	public BuscarTipo(ArrayList<Pokemon> listaPokemon) {
		super();
		setTitle("Mostrar por tipo");
		cancelButton.setText("Volver");
		this.listaPokemon = crearListaPokemons(listaPokemon);

		okButton.setVisible(false);
		textField.setEnabled(false);

		comboBox.setEnabled(false);
		textField.setEnabled(false);

		anterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAnterior();
			}
		});

		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarSiguiente();
			}
		});
		actualizar();
	}

	void actualizar() {
		if (listaPokemon.size() == 0) {
			return;
		}

		try {
			indice = 0;
			mostrarPokemon(listaPokemon.get(indice));
			comprobarBotones();
		} catch (PokemonNoExisteException e) {

		}

	}

	/**
	 * Muestra el siguiente pokemon
	 */
	private void mostrarSiguiente() {
		try {
			mostrarPokemon(listaPokemon.get(++indice));
			comprobarBotones();
		} catch (PokemonNoExisteException e) {

		}

	}

	/**
	 * Muestra el pokemon anterior
	 */
	private void mostrarAnterior() {
		try {
			mostrarPokemon(listaPokemon.get(--indice));
			comprobarBotones();
		} catch (PokemonNoExisteException e) {

		}

	}

	/**
	 * Muestda un pokemon
	 * 
	 * @param pokemon
	 *            a mostrar
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
	 * Bloquea o desbloquea los botones de anterior y siguiente según si hay o
	 * no más pokemons para mostrar
	 */
	private void comprobarBotones() {

		try {
			if (listaPokemon.get(indice + 1) != null)
				siguiente.setEnabled(true);
		} catch (PokemonNoExisteException e) {
			siguiente.setEnabled(false);
		}

		try {
			if (listaPokemon.get(indice - 1) != null)
				anterior.setEnabled(true);

		} catch (PokemonNoExisteException e) {
			// TODO Auto-generated catch block
			anterior.setEnabled(false);
		}
	}

	/**
	 * Genera un arrayList de pokemons
	 * 
	 * @param listaPokemon
	 * @returnLista de pokemons
	 */
	private EnvoltorioPokemons crearListaPokemons(ArrayList<Pokemon> listaPokemon) {
		EnvoltorioPokemons listaPokemonsTipo = new EnvoltorioPokemons();
		for (Pokemon pokemon : listaPokemon) {

			try {
				listaPokemonsTipo.annadir(pokemon);
			} catch (PokemonYaExisteException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

		}
		return listaPokemonsTipo;
	}

}
