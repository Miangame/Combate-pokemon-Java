package pokemonGUI;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import funcionalidad.General;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.excepciones.PokemonYaExisteException;
import funcionalidad.tipos.Agua;
import funcionalidad.tipos.Electrico;
import funcionalidad.tipos.Fuego;
import funcionalidad.tipos.Planta;
import funcionalidad.tipos.Pokemon;
import funcionalidad.tipos.Volador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Da de alta a un pokemon
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class AltaPokemon extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked" })
	public AltaPokemon() {

		setTitle("Alta de pokemons");
		lblTipo.setText("Seleccione el tipo:");
		String[] tipos = { "Agua", "Fuego", "Eléctrico", "Volador", "Planta" };

		siguiente.setVisible(false);
		anterior.setVisible(false);
		lblIcono.setVisible(false);

		comboBox.setModel(new DefaultComboBoxModel<>(tipos));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					altaPokemon();
				} catch (EnergiaNoValidaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		});

	}

	/**
	 * Añade un pokemon al archivo "pokemons.obj"
	 * @throws EnergiaNoValidaException 
	 */
	private static void altaPokemon() throws EnergiaNoValidaException {
		try {
			Principal.listaPokemon.annadir(pedirPokemon());
			Principal.listaPokemon.escribir();
			JOptionPane.showMessageDialog(null, "Pokemon añadido correctamente!");
		} catch (PokemonYaExisteException | IOException e) {
			JOptionPane.showMessageDialog(null, "Ese pokemon ya existe!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Pide un pokemon para posteriormente añadirlo
	 * 
	 * @return pokemon pedido
	 * @throws EnergiaNoValidaException 
	 */
	private static Pokemon pedirPokemon() throws EnergiaNoValidaException {

		switch (comboBox.getSelectedItem().toString()) {
		case "Agua":
			return new Agua(textField.getText(), General.generarAleatorio(300, 500));
		case "Fuego":
			return new Fuego(textField.getText(), General.generarAleatorio(300, 500));
		case "Planta":
			return new Planta(textField.getText(), General.generarAleatorio(300, 500));
		case "Electrico":
			return new Electrico(textField.getText(), General.generarAleatorio(300, 500));
		default:
			return new Volador(textField.getText(), General.generarAleatorio(300, 500));
		}

	}
}
