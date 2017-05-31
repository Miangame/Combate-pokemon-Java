package pokemonGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import funcionalidad.excepciones.PokemonNoExisteException;
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
		
		String[] tipos = { "Agua", "Fuego", "Electrico", "Volador", "Planta" };

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					comboBox_2.setModel(new DefaultComboBoxModel<Object>(
							Principal.listaPokemon.getPokemonsTipo(comboBox.getSelectedItem()).toArray()));
				} catch (PokemonNoExisteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		comboBox.setModel(new DefaultComboBoxModel<Object>(tipos));
		try {
			comboBox_2.setModel(new DefaultComboBoxModel<Object>(
					Principal.listaPokemon.getPokemonsTipo(comboBox.getSelectedItem()).toArray()));
		} catch (PokemonNoExisteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificacion modificacion = new Modificacion((Pokemon) comboBox_2.getSelectedItem());
				modificacion.setVisible(true);
				dispose();
			}
		});
	}

}
