package pokemonGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		lblUsuario.setBounds(100, 99, 113, 15);
		contentPanel.add(lblUsuario);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(231, 97, 121, 19);
		contentPanel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

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
						dispose();
						textFieldUsuario.setText("");
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
		try {
			ManejoUsuarios.listaUsuarios.annadir(new Usuario(textFieldUsuario.getText()));
			FicheroUsuarios.escribir(ManejoUsuarios.listaUsuarios, ManejoUsuarios.archivoUsuarios);
			JOptionPane.showMessageDialog(contentPanel, "Creado con éxito");
		} catch (ElementoYaExisteException | UsuarioNoValidoException | IOException e) {
			JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}
}
