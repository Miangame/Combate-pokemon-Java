package pokemonGUI;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funcionalidad.General;
import funcionalidad.Usuario;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.excepciones.PokemonNoExisteException;
import funcionalidad.excepciones.PokemonYaExisteException;
import funcionalidad.excepciones.UsuarioNoValidoException;
import funcionalidad.interfaces.Defensable;
import funcionalidad.tipos.Pokemon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

/**
 * Simula un combate entre pokemons
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Combate extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelBotones;
	private JButton btnAtacar;
	private JButton btnPokemon;
	private JButton btnHuir;
	private JTextArea textArea;
	private JButton btnAceptar;
	private JProgressBar progressBarEnemigo;
	private JProgressBar progressBarAliado;
	private JLabel lblNombreEnemigo;
	private JLabel lblNombreAliado;
	private JLabel labelAliado;
	private JLabel labelEnemigo;
	private JButton btnAtaque1;
	private JButton btnAtaque2;
	private JButton btnAtaque3;
	private JButton btnAtaque4;

	private PantallaFinal pantallaFinal;

	private Usuario jugador1 = Principal.jugador1;
	private Usuario jugador2;

	private Pokemon pokemonAliado;
	private Pokemon pokemonEnemigo;
	private int contadorAliado = 0;
	private int contadorEnemigo = 0;
	private int estadoBotonAceptar = 0;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JButton btnCambiar;
	private JButton btnVolver;
	private JProgressBar progressBarEnemigoEnergia;
	private JProgressBar progressBarAliadoEnergia;

	/**
	 * Create the frame.
	 * 
	 * @throws UsuarioNoValidoException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Combate() {
		setModal(true);

		try {
			jugador2 = new Usuario("Entrenador Pepe");
		} catch (UsuarioNoValidoException e1) {
			e1.printStackTrace();
		}

		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 25, 701, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelBotones = new JPanel();
		panelBotones.setBounds(0, 313, 699, 140);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);

		btnAtacar = new JButton("Atacar");
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarBotonesAtaques();
			}
		});
		btnAtacar.setBounds(101, 12, 133, 38);
		panelBotones.add(btnAtacar);

		btnPokemon = new JButton("Pokemon");
		btnPokemon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarLista();
			}

		});
		btnPokemon.setBounds(444, 12, 133, 38);
		panelBotones.add(btnPokemon);

		btnHuir = new JButton("Huir");
		btnHuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaFinal = new PantallaFinal(jugador2);
				pantallaFinal.setVisible(true);
			}
		});
		btnHuir.setBounds(274, 71, 123, 38);
		panelBotones.add(btnHuir);

		btnAtaque1 = new JButton("");
		btnAtaque1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atacarJugador(pokemonAliado.getAtaques(0));
			}
		});
		btnAtaque1.setBounds(123, 19, 157, 25);
		panelBotones.add(btnAtaque1);

		btnAtaque2 = new JButton("");
		btnAtaque2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atacarJugador(pokemonAliado.getAtaques(1));
			}
		});
		btnAtaque2.setBounds(123, 62, 157, 25);
		panelBotones.add(btnAtaque2);

		btnAtaque3 = new JButton("");
		btnAtaque3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atacarJugador(pokemonAliado.getAtaques(2));
			}
		});
		btnAtaque3.setBounds(384, 19, 157, 25);
		panelBotones.add(btnAtaque3);

		btnAtaque4 = new JButton("");
		btnAtaque4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atacarJugador(pokemonAliado.getAtaques(3));
			}
		});
		btnAtaque4.setBounds(384, 62, 157, 25);
		panelBotones.add(btnAtaque4);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (estadoBotonAceptar == 0) {
					atacarEnemigo();
					estadoBotonAceptar++;
				} else {
					mostrarBotonesMenu();
					estadoBotonAceptar = 0;
				}
			}

		});

		btnCambiar = new JButton("Cambiar");
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPokemon();
			}
		});
		btnCambiar.setBounds(444, 109, 117, 25);
		panelBotones.add(btnCambiar);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel<>(pokemonsJugador().toArray()));
		comboBox.setBounds(258, 35, 139, 24);
		panelBotones.add(comboBox);
		btnAceptar.setBounds(570, 109, 117, 25);
		panelBotones.add(btnAceptar);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 699, 97);
		panelBotones.add(textArea);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarBotonesMenu();
			}
		});
		btnVolver.setBounds(570, 109, 117, 25);
		panelBotones.add(btnVolver);

		progressBarEnemigo = new JProgressBar();
		progressBarEnemigo.setStringPainted(true);
		progressBarEnemigo.setForeground(Color.GREEN);
		progressBarEnemigo.setBounds(44, 49, 171, 14);
		contentPane.add(progressBarEnemigo);

		progressBarAliado = new JProgressBar();
		progressBarAliado.setStringPainted(true);
		progressBarAliado.setForeground(Color.GREEN);
		progressBarAliado.setBounds(491, 204, 171, 14);
		contentPane.add(progressBarAliado);

		progressBarEnemigoEnergia = new JProgressBar();
		progressBarEnemigoEnergia.setStringPainted(true);
		progressBarEnemigoEnergia.setForeground(Color.BLUE);
		progressBarEnemigoEnergia.setBounds(44, 75, 171, 14);
		contentPane.add(progressBarEnemigoEnergia);

		progressBarAliadoEnergia = new JProgressBar();
		progressBarAliadoEnergia.setStringPainted(true);
		progressBarAliadoEnergia.setForeground(Color.BLUE);
		progressBarAliadoEnergia.setBounds(491, 230, 171, 14);
		contentPane.add(progressBarAliadoEnergia);

		JLabel lblPs = new JLabel("PS");
		lblPs.setForeground(Color.WHITE);
		lblPs.setBounds(12, 48, 28, 15);
		contentPane.add(lblPs);

		JLabel lblEnerga = new JLabel("PP");
		lblEnerga.setForeground(Color.WHITE);
		lblEnerga.setBounds(12, 74, 28, 15);
		contentPane.add(lblEnerga);

		JLabel lblPs_1 = new JLabel("PS");
		lblPs_1.setForeground(Color.WHITE);
		lblPs_1.setBounds(445, 203, 28, 15);
		contentPane.add(lblPs_1);

		JLabel lblPp = new JLabel("PP");
		lblPp.setForeground(Color.WHITE);
		lblPp.setBounds(445, 229, 28, 15);
		contentPane.add(lblPp);

		lblNombreEnemigo = new JLabel();
		lblNombreEnemigo.setForeground(Color.WHITE);
		lblNombreEnemigo.setBounds(44, 22, 135, 15);
		contentPane.add(lblNombreEnemigo);

		lblNombreAliado = new JLabel();
		lblNombreAliado.setForeground(Color.WHITE);
		lblNombreAliado.setBounds(491, 177, 135, 15);
		contentPane.add(lblNombreAliado);

		labelAliado = new JLabel("");

		labelAliado.setBounds(144, 146, 171, 169);
		contentPane.add(labelAliado);

		labelEnemigo = new JLabel("");

		labelEnemigo.setBounds(449, 12, 165, 153);
		contentPane.add(labelEnemigo);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Combate.class.getResource("/resources/img/fondoCombate.png")));
		label.setBounds(0, 0, 699, 315);
		contentPane.add(label);

		generarPokemonsEnemigo();
		cargarPokemonEnemigo();
		cargarPokemonAliado();
		mostrarBotonesMenu();
	}

	/**
	 * Cambia el pokemon según el seleccionado
	 */
	private void cambiarPokemon() {

		boolean bandera = true;

		if (comboBox.getSelectedItem() == pokemonAliado) {
			JOptionPane.showMessageDialog(contentPane, "Ese pokemon ya está combatiendo!!");
			bandera = false;
		}

		if (((Pokemon) comboBox.getSelectedItem()).getVida() <= 0) {
			JOptionPane.showMessageDialog(contentPane, "Ese pokemon se encuentra debilitado!!");
			bandera = false;
		}

		if (bandera) {
			try {
				pokemonAliado = jugador1.getCinturon().get(comboBox.getSelectedIndex());

			} catch (PokemonNoExisteException e) {
				e.printStackTrace();
			}

			lblNombreAliado.setText(pokemonAliado.getNombre());

			labelAliado.setIcon(new ImageIcon(
					Combate.class.getResource("/resources/img/characters/" + pokemonAliado.getNombre() + "b.png")));
			lblNombreAliado.setText(pokemonAliado.getNombre());

			progressBarAliado.setMaximum(pokemonAliado.getVida());
			progressBarAliado.setMinimum(0);
			progressBarAliado.setValue(pokemonAliado.getVida());

			progressBarAliadoEnergia.setMaximum(pokemonAliado.getEnergia());
			progressBarAliadoEnergia.setMinimum(0);
			progressBarAliadoEnergia.setValue(pokemonAliado.getEnergia());

			mostrarBotonesMenu();
			atacarEnemigo();
		}

	}

	/**
	 * Oculta todos los botones excepto el combobox que mostrará una lista con
	 * los pokemons del jugador
	 */
	private void mostrarLista() {
		textArea.setVisible(false);
		btnAceptar.setVisible(false);
		btnAtaque1.setVisible(false);
		btnAtaque2.setVisible(false);
		btnAtaque3.setVisible(false);
		btnAtaque4.setVisible(false);
		btnAtacar.setVisible(false);
		btnPokemon.setVisible(false);
		btnHuir.setVisible(false);
		comboBox.setVisible(true);
		btnCambiar.setVisible(true);
		btnVolver.setVisible(true);

		estadoBotonAceptar = 2;

	}

	/**
	 * Genera un ArrayList con los pokemons del jugador
	 * 
	 * @return ArrayList con los pokemons del jugador
	 */
	private ArrayList<Pokemon> pokemonsJugador() {
		ArrayList<Pokemon> lista = new ArrayList<>();
		for (Pokemon pokemon : jugador1.getCinturon()) {
			lista.add(pokemon);
		}

		return lista;
	}

	/**
	 * Asigna textos a las etiquetas del pokemon enemigo según el que esté
	 * seleccionado en ese momento
	 */
	private void cargarPokemonEnemigo() {
		try {
			pokemonEnemigo = jugador2.getCinturon().get(contadorEnemigo);

			lblNombreEnemigo.setText(pokemonEnemigo.getNombre());
			labelEnemigo.setIcon(new ImageIcon(
					Combate.class.getResource("/resources/img/characters/" + pokemonEnemigo.getNombre() + ".png")));
			progressBarEnemigo.setMaximum(pokemonEnemigo.getVida());
			progressBarEnemigo.setMinimum(0);
			progressBarEnemigo.setValue(pokemonEnemigo.getVida());

			progressBarEnemigoEnergia.setMaximum(pokemonEnemigo.getEnergia());
			;
			progressBarEnemigoEnergia.setMinimum(0);
			progressBarEnemigoEnergia.setValue(pokemonEnemigo.getEnergia());
		} catch (PokemonNoExisteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Asigna textos a las etiquetas del pokemon del jugador según el que esté
	 * seleccionado en ese momento
	 */
	private void cargarPokemonAliado() {
		try {
			pokemonAliado = jugador1.getCinturon().get(contadorAliado);

		} catch (PokemonNoExisteException e) {
			e.printStackTrace();
		}

		lblNombreAliado.setText(pokemonAliado.getNombre());

		labelAliado.setIcon(new ImageIcon(
				Combate.class.getResource("/resources/img/characters/" + pokemonAliado.getNombre() + "b.png")));
		lblNombreAliado.setText(pokemonAliado.getNombre());

		progressBarAliado.setMaximum(pokemonAliado.getVida());
		progressBarAliado.setMinimum(0);
		progressBarAliado.setValue(pokemonAliado.getVida());

		progressBarAliadoEnergia.setMaximum(pokemonAliado.getEnergia());
		progressBarAliadoEnergia.setMinimum(0);
		progressBarAliadoEnergia.setValue(pokemonAliado.getEnergia());

	}

	/**
	 * Muestra los 4 botones de los ataques y oculta el resto
	 */
	private void mostrarBotonesAtaques() {
		textArea.setVisible(false);
		btnAceptar.setVisible(false);
		btnAtaque1.setVisible(true);
		btnAtaque2.setVisible(true);
		btnAtaque3.setVisible(true);
		btnAtaque4.setVisible(true);
		btnAtacar.setVisible(false);
		btnPokemon.setVisible(false);
		btnHuir.setVisible(false);
		comboBox.setVisible(false);
		btnCambiar.setVisible(false);
		btnVolver.setVisible(false);

		btnAtaque1.setText(pokemonAliado.getAtaques(0).toString());
		btnAtaque2.setText(pokemonAliado.getAtaques(1).toString());
		btnAtaque3.setText(pokemonAliado.getAtaques(2).toString());
		btnAtaque4.setText(pokemonAliado.getAtaques(3).toString());

	}

	/**
	 * Muestra los 3 botones principales y oculta el resto
	 */
	private void mostrarBotonesMenu() {
		textArea.setVisible(false);
		btnAceptar.setVisible(false);
		btnAtaque1.setVisible(false);
		btnAtaque2.setVisible(false);
		btnAtaque3.setVisible(false);
		btnAtaque4.setVisible(false);
		btnAtacar.setVisible(true);
		btnPokemon.setVisible(true);
		btnHuir.setVisible(true);
		comboBox.setVisible(false);
		btnCambiar.setVisible(false);
		btnVolver.setVisible(false);
	}

	/**
	 * Muestra el textField y el botón de aceptar y oculta el resto
	 */
	private void mostrarTexto() {
		textArea.setText("");
		textArea.setVisible(true);
		btnAceptar.setVisible(true);
		btnAtaque1.setVisible(false);
		btnAtaque2.setVisible(false);
		btnAtaque3.setVisible(false);
		btnAtaque4.setVisible(false);
		btnAtacar.setVisible(false);
		btnPokemon.setVisible(false);
		btnHuir.setVisible(false);
		comboBox.setVisible(false);
		btnVolver.setVisible(false);
	}

	/**
	 * Genera los 6 pokemons del enemigo de forma aleatoria
	 */
	private void generarPokemonsEnemigo() {
		// Los 6 pokemmons del enemigo se generan automaticamente
		for (int i = 0; i < 6;) {
			try {
				jugador2.getCinturon().annadir(Principal.listaPokemon.get(General.generarAleatorio(0, 24)));
				i++;
			} catch (PokemonYaExisteException | PokemonNoExisteException e) {

			}
		}
	}

	/**
	 * Ataca el jugador
	 * 
	 * @param ataque
	 *            Ataque que va a realizar
	 */
	private void atacarJugador(Ataque ataque) {

		Defensable defensable = (Defensable) pokemonEnemigo;
		String cadena = "\n\t" + pokemonAliado.getNombre() + " USÓ " + ataque.name() + "\n";

		mostrarTexto();
		try {
			cadena += "\n\t" + defensable.getDefensa(pokemonAliado, pokemonAliado.getAtaque(ataque));
		} catch (EnergiaNoValidaException e) {
			cadena += e.getMessage();
		}
		progressBarEnemigo.setValue(pokemonEnemigo.getVida());
		progressBarAliadoEnergia.setValue(pokemonAliado.getEnergia());
		textArea.setText(cadena);

		if (pokemonEnemigo.getVida() <= 0) {
			contadorEnemigo++;
			if (contadorEnemigo != 6) {
				cargarPokemonEnemigo();
			}

		}
		try {
			pokemonEnemigo.setEnergia(pokemonEnemigo.getEnergia() + 10);
			progressBarEnemigoEnergia.setValue(pokemonEnemigo.getEnergia());
		} catch (EnergiaNoValidaException e) {

		}
		comprobarGanador();

	}

	/**
	 * Ataca el enemigo
	 */
	private void atacarEnemigo() {

		Defensable defensable = (Defensable) pokemonAliado;
		Ataque ataque = pokemonEnemigo.getAtaques(General.generarAleatorio(0, 3));
		String cadena = "\n\t" + pokemonEnemigo.getNombre() + " USÓ " + ataque.name() + "\n";

		mostrarTexto();
		try {
			cadena += "\n\t" + defensable.getDefensa(pokemonEnemigo, pokemonEnemigo.getAtaque(ataque));
		} catch (EnergiaNoValidaException e) {
			cadena += e.getMessage();
		}
		progressBarAliado.setValue(pokemonAliado.getVida());
		progressBarEnemigoEnergia.setValue(pokemonEnemigo.getEnergia());
		textArea.setText(cadena);

		if (pokemonAliado.getVida() <= 0) {

			contadorAliado++;
			if (contadorAliado != 6) {
				cargarPokemonAliado();
			}

		}
		try {
			pokemonAliado.setEnergia(pokemonAliado.getEnergia() + 10);
			progressBarAliadoEnergia.setValue(pokemonAliado.getEnergia());
		} catch (EnergiaNoValidaException e) {

		}
		comprobarGanador();

	}

	/**
	 * Comprueba el ganador
	 */
	private void comprobarGanador() {
		if (contadorAliado == 6) {
			pantallaFinal = new PantallaFinal(jugador2);
			pantallaFinal.setVisible(true);
		}

		if (contadorEnemigo == 6) {
			pantallaFinal = new PantallaFinal(jugador1);
			pantallaFinal.setVisible(true);
		}

	}
}