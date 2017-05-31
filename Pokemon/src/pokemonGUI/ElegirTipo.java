package pokemonGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import funcionalidad.excepciones.PokemonNoExisteException;
import funcionalidad.tipos.Pokemon;

/**
 * Elige el tipo de pokemon para mostrarlos
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class ElegirTipo extends VentanaPadre {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public ElegirTipo() {
		setTitle("Buscar por tipo");
		textField.setVisible(false);
		lblNombre.setVisible(false);
		lblIcono.setVisible(false);
		anterior.setVisible(false);
		siguiente.setVisible(false);
		comboBox_2.setVisible(false);
		lblTipo.setText("Seleccione el tipo:");
		String[] tipos = { "Agua", "Fuego", "Electrico", "Volador", "Planta" };

		comboBox.setModel(new DefaultComboBoxModel<>(tipos));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object tipo = comboBox.getSelectedItem();
				if (tipo != null) {
					ArrayList<Pokemon> pokemons;
					try {
						pokemons = Principal.listaPokemon.getPokemonsTipo(tipo);
						BuscarTipo buscarTipo = new BuscarTipo(pokemons);
						buscarTipo.setVisible(true);
						dispose();
					} catch (PokemonNoExisteException e1) {
						JOptionPane.showMessageDialog(null, "No existe ning�n pokemon de ese tipo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Debes seleccionar un tipo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

}
