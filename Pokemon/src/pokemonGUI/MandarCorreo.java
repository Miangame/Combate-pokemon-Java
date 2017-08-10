package pokemonGUI;

import java.awt.FlowLayout;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funcionalidad.ManejoUsuarios;
import funcionalidad.excepciones.ElementoNoExisteException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MandarCorreo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public MandarCorreo() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 448, 235);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setBounds(239, 82, 114, 19);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblIntroduzcaSuCorreo = new JLabel("Introduzca su correo");
			lblIntroduzcaSuCorreo.setBounds(53, 84, 168, 15);
			contentPanel.add(lblIntroduzcaSuCorreo);
		}

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MandarCorreo.class.getResource("/resources/img/correo.jpg")));
		label.setBounds(0, 0, 448, 235);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 235, 448, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						mandarCorreo();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void mandarCorreo() {
		boolean encontrado = false;
		int contador = 0;
		if (!correoValido(textField.getText())) {
			JOptionPane.showMessageDialog(contentPanel, "El correo introducido no es válido", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			for (int i = 0; i < ManejoUsuarios.listaUsuarios.size(); i++) {
				if (textField.getText().equals(ManejoUsuarios.listaUsuarios.get(i).getCorreo())) {
					encontrado = true;
				} else {
					contador++;
				}

			}
		} catch (ElementoNoExisteException e) {

		}

		if (!encontrado) {
			JOptionPane.showMessageDialog(contentPanel,
					"El correo introducido no se encuentra registrado en nuestro sistema.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {
			final String username = "proyectopokemon@outlook.es";
			final String password = "Proyecto_pokemon";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "outlook.office365.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("proyectopokemon@outlook.es"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("miangame1@gmail.com"));
				message.setSubject("Recuperación de contraseña");
				message.setText("Usuario: " + ManejoUsuarios.listaUsuarios.get(contador).getAlias() + "\nContraseña: "
						+ ManejoUsuarios.listaUsuarios.get(contador).getContrasenia());

				Transport.send(message);

				JOptionPane.showMessageDialog(contentPanel,
						"Se le ha enviado un correo con sus datos. Por favor revise su bandeja de entrada.");
				dispose();
			} catch (MessagingException | ElementoNoExisteException e) {
				throw new RuntimeException(e);
			}
		}

	}

	private boolean correoValido(String correo) {
		Pattern patronCorreo = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
		return patronCorreo.matcher(correo).matches();
	}
}
