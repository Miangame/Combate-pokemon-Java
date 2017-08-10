package pokemonGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funcionalidad.ContraseniaNoValidaException;
import funcionalidad.CorreoNoValidoException;
import funcionalidad.FicheroUsuarios;
import funcionalidad.ManejoUsuarios;
import funcionalidad.Usuario;
import funcionalidad.excepciones.ElementoYaExisteException;
import funcionalidad.excepciones.UsuarioNoValidoException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/**
 * Ventana que regs¡istra a un usuario
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class RegistroUsuario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textFieldCorreo;

	/**
	 * Create the dialog.
	 */
	public RegistroUsuario() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblUsuario = new JLabel("Nuevo usuario");
		lblUsuario.setBounds(98, 25, 113, 15);
		contentPanel.add(lblUsuario);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(261, 23, 121, 19);
		contentPanel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setBounds(261, 54, 121, 19);
		contentPanel.add(textFieldCorreo);
		textFieldCorreo.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(261, 91, 121, 19);
		contentPanel.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(261, 122, 121, 19);
		contentPanel.add(passwordField_1);

		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(98, 93, 113, 15);
		contentPanel.add(lblContrasea);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(98, 52, 70, 15);
		contentPanel.add(lblCorreo);

		JLabel lblRepitaContrasea = new JLabel("Repita contraseña");
		lblRepitaContrasea.setBounds(98, 124, 145, 15);
		contentPanel.add(lblRepitaContrasea);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RegistroUsuario.class.getResource("/resources/img/registro.png")));
		lblNewLabel.setBounds(0, 0, 448, 235);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						crearCuenta();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * Crea una nueva cuenta
	 */
	private void crearCuenta() {
		char[] c = passwordField.getPassword();
		String s1 = new String(c);
		c = passwordField_1.getPassword();
		String s2 = new String(c);

		try {
			if (!s1.equals(s2)) {
				JOptionPane.showMessageDialog(contentPanel, "¡Las contraseñas no coinciden!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				ManejoUsuarios.listaUsuarios.annadir(new Usuario(textFieldUsuario.getText(), textFieldCorreo.getText(),
						String.valueOf(passwordField.getPassword())));
				FicheroUsuarios.escribir(ManejoUsuarios.listaUsuarios, ManejoUsuarios.archivoUsuarios);
				JOptionPane.showMessageDialog(contentPanel, "Creado con éxito");

				dispose();
				textFieldUsuario.setText("");
				passwordField.setText("");
				passwordField_1.setText("");
				textFieldCorreo.setText("");
			}
		} catch (ElementoYaExisteException | UsuarioNoValidoException | IOException | ContraseniaNoValidaException | CorreoNoValidoException e) {
			JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}
}
