package pokemonGUI;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funcionalidad.excepciones.PokemonNoExisteException;
import funcionalidad.excepciones.PokemonYaExisteException;
import funcionalidad.tipos.Pokemon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	private JComboBox<Pokemon> comboBox_1;
	private JComboBox<Pokemon> comboBox_2;
	private JComboBox<Pokemon> comboBox_3;
	private JComboBox<Pokemon> comboBox_4;
	private JComboBox<Pokemon> comboBox_5;
	private JComboBox<Pokemon> comboBox_6;
	private JComboBox<Pokemon> comboBox_7;
	private JComboBox<Pokemon> comboBox_8;
	private JComboBox<Pokemon> comboBox_9;
	private JComboBox<Pokemon> comboBox_10;
	private JComboBox<Pokemon> comboBox_11;
	private JComboBox<Pokemon> comboBox_12;

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
		lblEscojaSus.setBounds(179, 88, 163, 15);
		contentPane.add(lblEscojaSus);

		JLabel label_1 = new JLabel("#1");
		label_1.setBounds(72, 150, 28, 15);
		contentPane.add(label_1);

		JLabel label = new JLabel("#2");
		label.setBounds(72, 177, 28, 15);
		contentPane.add(label);

		JLabel label_2 = new JLabel("#3");
		label_2.setBounds(72, 204, 28, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("#4");
		label_3.setBounds(72, 231, 28, 15);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("#5");
		label_4.setBounds(72, 258, 28, 15);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("#6");
		label_5.setBounds(72, 285, 28, 15);
		contentPane.add(label_5);

		comboBox_1 = new JComboBox<Pokemon>();
		comboBox_1.setBounds(118, 145, 114, 24);
		contentPane.add(comboBox_1);

		comboBox_2 = new JComboBox<Pokemon>();
		comboBox_2.setBounds(118, 172, 114, 24);
		contentPane.add(comboBox_2);

		comboBox_3 = new JComboBox<Pokemon>();
		comboBox_3.setBounds(118, 199, 114, 24);
		contentPane.add(comboBox_3);

		comboBox_4 = new JComboBox<Pokemon>();
		comboBox_4.setBounds(118, 226, 114, 24);
		contentPane.add(comboBox_4);

		comboBox_5 = new JComboBox<Pokemon>();
		comboBox_5.setBounds(118, 253, 114, 24);
		contentPane.add(comboBox_5);

		comboBox_6 = new JComboBox<Pokemon>();
		comboBox_6.setBounds(118, 280, 114, 24);
		contentPane.add(comboBox_6);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					aniadirPokemons();

					dispose();
					Combate combate = new Combate();
					combate.setVisible(true);
				} catch (PokemonYaExisteException e) {
					JOptionPane.showMessageDialog(contentPane, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					Principal.jugador1.setCinturon();
				}
			}

		});
		
		comboBox_7 = new JComboBox<Pokemon>();
		comboBox_7.setBounds(244, 145, 114, 24);
		contentPane.add(comboBox_7);
		
		comboBox_8 = new JComboBox<Pokemon>();
		comboBox_8.setBounds(244, 172, 114, 24);
		contentPane.add(comboBox_8);
		
		comboBox_9 = new JComboBox<Pokemon>();
		comboBox_9.setBounds(244, 199, 114, 24);
		contentPane.add(comboBox_9);
		
		comboBox_10 = new JComboBox<Pokemon>();
		comboBox_10.setBounds(244, 226, 114, 24);
		contentPane.add(comboBox_10);
		
		comboBox_11 = new JComboBox<Pokemon>();
		comboBox_11.setBounds(244, 253, 114, 24);
		contentPane.add(comboBox_11);
		
		comboBox_12 = new JComboBox<Pokemon>();
		comboBox_12.setBounds(244, 280, 114, 24);
		contentPane.add(comboBox_12);
		btnAceptar.setBounds(380, 449, 117, 25);
		contentPane.add(btnAceptar);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(155, 118, 40, 15);
		contentPane.add(lblTipo);
		
		String[] tipos = { "Agua", "Fuego", "Electrico", "Volador", "Planta" };

		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon(EleccionPokemon.class.getResource("/resources/img/fondoEleccion.png")));
		labelFondo.setBounds(0, 0, 509, 486);
		contentPane.add(labelFondo);
		
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_7.setModel(new DefaultComboBoxModel(getPokemons(comboBox_1)));
			}

			
		});
		comboBox_1.setModel(new DefaultComboBoxModel(tipos));
		comboBox_7.setModel(new DefaultComboBoxModel(getPokemons(comboBox_1)));
		
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_8.setModel(new DefaultComboBoxModel(getPokemons(comboBox_2)));
			}

			
		});
		comboBox_2.setModel(new DefaultComboBoxModel(tipos));
		comboBox_8.setModel(new DefaultComboBoxModel(getPokemons(comboBox_2)));
		
		comboBox_3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_9.setModel(new DefaultComboBoxModel(getPokemons(comboBox_3)));
			}

			
		});
		comboBox_3.setModel(new DefaultComboBoxModel(tipos));
		comboBox_9.setModel(new DefaultComboBoxModel(getPokemons(comboBox_3)));
		
		comboBox_4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_10.setModel(new DefaultComboBoxModel(getPokemons(comboBox_4)));
			}

			
		});
		comboBox_4.setModel(new DefaultComboBoxModel(tipos));
		comboBox_10.setModel(new DefaultComboBoxModel(getPokemons(comboBox_4)));
		
		comboBox_5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_11.setModel(new DefaultComboBoxModel(getPokemons(comboBox_5)));
			}

			
		});
		comboBox_5.setModel(new DefaultComboBoxModel(tipos));
		comboBox_11.setModel(new DefaultComboBoxModel(getPokemons(comboBox_5)));
		
		comboBox_6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBox_12.setModel(new DefaultComboBoxModel(getPokemons(comboBox_6)));
			}

			
		});
		comboBox_6.setModel(new DefaultComboBoxModel(tipos));
		comboBox_12.setModel(new DefaultComboBoxModel(getPokemons(comboBox_6)));

		
		
	}
	
	private Object[] getPokemons(JComboBox<Pokemon> comboBox_1) {
		try {
			return Principal.listaPokemon.getPokemonsTipo(comboBox_1.getSelectedItem()).toArray();
		} catch (PokemonNoExisteException e) {
			
		}
		return null;
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
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox_7.getSelectedItem());
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox_8.getSelectedItem());
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox_9.getSelectedItem());
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox_10.getSelectedItem());
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox_11.getSelectedItem());
		Principal.jugador1.getCinturon().annadir((Pokemon) comboBox_12.getSelectedItem());

	}
}
