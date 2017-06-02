package pokemonGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import funcionalidad.EnvoltorioPokemons;
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
	private ListIterator<Pokemon> iterador;
	private Pokemon pokemonCopia;
	private Pokemon pokemonActual;

	/**
	 * Create the dialog.
	 */
	public BuscarTipo(ArrayList<Pokemon> listaPokemon) {
		super();
		setTitle("Mostrar por tipo");
		cancelButton.setText("Volver");
		this.listaPokemon = crearListaPokemons(listaPokemon);

		iterador = listaPokemon.listIterator();

		siguiente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (!anterior.isEnabled()) {
						return;
					}
					mostrarAnterior();
					anterior.grabFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (iterador.hasNext()) {
						mostrarSiguiente();
					}

				}
			}
		});

		anterior.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					mostrarAnterior();
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (!siguiente.isEnabled()) {
						return;
					}
					mostrarSiguiente();
					siguiente.grabFocus();

				}
			}
		});

		okButton.setVisible(false);
		textField.setEnabled(false);

		comboBox.setEnabled(false);
		textField.setEnabled(false);
		comboBox_2.setVisible(false);

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
		if (listaPokemon.size() == 1) {
			siguiente.setEnabled(false);
			anterior.setEnabled(false);
		} else {
			siguiente.setEnabled(true);
			anterior.setEnabled(false);
		}
		pokemonCopia = iterador.next();
		pokemonActual = pokemonCopia;
		mostrarPokemon(pokemonCopia);

	}

	/**
	 * Muestra el siguiente pokemon
	 */
	private void mostrarSiguiente() {
		if (iterador.hasNext()) {
			pokemonCopia = iterador.next();
			if (pokemonActual == pokemonCopia) {
				pokemonCopia = iterador.next();
			}
			mostrarPokemon(pokemonCopia);

			comprobarBotones();

			pokemonActual = pokemonCopia;

			if (!iterador.hasNext()) {
				iterador.previous();
				anterior.grabFocus();
			}

		}

	}

	/**
	 * Muestra el pokemon anterior
	 */
	private void mostrarAnterior() {
		if (iterador.hasPrevious()) {
			pokemonCopia = iterador.previous();
			if (pokemonActual == pokemonCopia) {
				pokemonCopia = iterador.previous();
			}
			mostrarPokemon(pokemonCopia);

			comprobarBotones();

			pokemonActual = pokemonCopia;
		}

		if (!iterador.hasPrevious()) {
			iterador.next();
			siguiente.grabFocus();
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
		if (!iterador.hasNext()) {
			siguiente.setEnabled(false);
		} else {
			siguiente.setEnabled(true);
		}
		if (!iterador.hasPrevious()) {
			anterior.setEnabled(false);
		} else {
			anterior.setEnabled(true);
		}
	}

	/**
	 * Genera un arrayList de pokemons
	 * 
	 * @param listaPokemon
	 * @return Lista de pokemons
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
