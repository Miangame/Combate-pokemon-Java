package pokemonGUI;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funcionalidad.excepciones.PokemonYaExisteException;
import funcionalidad.tipos.Pokemon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;

/**
 * El jugador elige 6 pokemons
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class EleccionPokemon extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Pokemon> comboBox;
	private JComboBox<Pokemon> comboBox_1;
	private JComboBox<Pokemon> comboBox_2;
	private JComboBox<Pokemon> comboBox_3;
	private JComboBox<Pokemon> comboBox_4;
	private JComboBox<Pokemon> comboBox_5;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EleccionPokemon() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 511, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblhola = new JLabel("Hola " + Principal.jugador1.getAlias());
		lblhola.setBounds(179, 12, 160, 35);
		contentPane.add(lblhola);

		JLabel lblAsd = new JLabel(Principal.jugador1.calcularTiempoRegistrado());
		lblAsd.setBounds(140, 45, 229, 15);
		contentPane.add(lblAsd);

		JLabel lblEscojaSus = new JLabel("Escoja sus 6 Pokemons");
		lblEscojaSus.setBounds(176, 113, 163, 15);
		contentPane.add(lblEscojaSus);

		JLabel label_1 = new JLabel("#1");
		label_1.setBounds(179, 150, 28, 15);
		contentPane.add(label_1);

		JLabel label = new JLabel("#2");
		label.setBounds(179, 177, 28, 15);
		contentPane.add(label);

		JLabel label_2 = new JLabel("#3");
		label_2.setBounds(179, 204, 28, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("#4");
		label_3.setBounds(179, 231, 28, 15);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("#5");
		label_4.setBounds(179, 258, 28, 15);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("#6");
		label_5.setBounds(179, 285, 28, 15);
		contentPane.add(label_5);

		comboBox = new JComboBox(Principal.listaPokemon.getLista().toArray());
		comboBox.setBounds(225, 145, 114, 24);
		contentPane.add(comboBox);

		comboBox_1 = new JComboBox(Principal.listaPokemon.getLista().toArray());
		comboBox_1.setBounds(225, 172, 114, 24);
		contentPane.add(comboBox_1);

		comboBox_2 = new JComboBox(Principal.listaPokemon.getLista().toArray());
		comboBox_2.setBounds(225, 199, 114, 24);
		contentPane.add(comboBox_2);

		comboBox_3 = new JComboBox(Principal.listaPokemon.getLista().toArray());
		comboBox_3.setBounds(225, 226, 114, 24);
		contentPane.add(comboBox_3);

		comboBox_4 = new JComboBox(Principal.listaPokemon.getLista().toArray());
		comboBox_4.setBounds(225, 253, 114, 24);
		contentPane.add(comboBox_4);

		comboBox_5 = new JComboBox(Principal.listaPokemon.getLista().toArray());
		comboBox_5.setBounds(225, 280, 114, 24);
		contentPane.add(comboBox_5);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					aniadirPokemons();

					dispose();
					Combate combate = new Combate();
					combate.setVisible(true);
				} catch (PokemonYaExisteException e) {
					JOptionPane.showMessageDialog(contentPane, e.getMessage());
					Principal.jugador1.setCinturon();
				}
			}

		});
		btnAceptar.setBounds(380, 449, 117, 25);
		contentPane.add(btnAceptar);

		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon(EleccionPokemon.class.getResource("/resources/img/fondoEleccion.png")));
		labelFondo.setBounds(0, 0, 509, 486);
		contentPane.add(labelFondo);

	}

	/**
	 * Añade los pokemons seleccionados en los comboboxes al cinturón del
	 * jugador
	 * 
	 * @param jugador1
	 * @throws PokemonYaExisteException
	 */
	private void aniadirPokemons() throws PokemonYaExisteException {
		Principal.jugador1.setCinturon();
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox.getSelectedItem());
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox_1.getSelectedItem());
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox_2.getSelectedItem());
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox_3.getSelectedItem());
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox_4.getSelectedItem());
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox_5.getSelectedItem());

	}

}
