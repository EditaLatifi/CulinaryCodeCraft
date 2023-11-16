package org.makerminds.jcoaching.internship.restaurantpoint.gui.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.junit.platform.commons.util.StringUtils;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.LoginController;
import org.makerminds.jcoaching.internship.restaurantpoint.dataprovider.UserDataProvider;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;

public class LoginView {

	private final Font GENERAL_LABEL_FONT = new Font("Arial", Font.PLAIN, 15);
	private final Color CORPORATE_BLUE = Color.decode("#4285f4");

	private static JFrame frame;

	private JTextField usernameTextField = new JTextField();
	private JPasswordField passwordTextField;

	private JLabel resultLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView loginView = new LoginView();
					loginView.prepareView();
				} catch (Exception e) {
					throw new RuntimeException("Unexpected error occured while user login.", e);
				}
			}
		});
	}

	protected void prepareView() {
		initializeLoginFrame();
		createLoginComponents();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeLoginFrame() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

	}

	private void createLoginComponents() {
		createUsernameComponent();
		createPasswordFieldComponent();
		createLoginButton();
		createResultLabel();
		createShowHidePasswordComponents();
	}

	private void createShowHidePasswordComponents() {
		JRadioButton hidePasswordRadioButton = new JRadioButton("Hide");
		JRadioButton showPasswordRadioButton = new JRadioButton("Show");

		setupHidePasswordRadioButton(hidePasswordRadioButton, showPasswordRadioButton);
		setupShowPasswordRadioButton(hidePasswordRadioButton, showPasswordRadioButton);

		frame.getContentPane().add(hidePasswordRadioButton);
		frame.getContentPane().add(showPasswordRadioButton);
	}

	private void setupShowPasswordRadioButton(JRadioButton hidePasswordRadioButton,
			JRadioButton showPasswordRadioButton) {
		showPasswordRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordTextField.setEchoChar((char) 0);
				hidePasswordRadioButton.setSelected(false);
			}
		});
		showPasswordRadioButton.setFont(new Font("Arial", Font.PLAIN, 15));
		showPasswordRadioButton.setBounds(263, 145, 63, 23);
	}

	private void setupHidePasswordRadioButton(JRadioButton hidePasswordRadioButton,
			JRadioButton showPasswordRadioButton) {
		hidePasswordRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordTextField.setEchoChar('*');
				showPasswordRadioButton.setSelected(false);
			}
		});
		hidePasswordRadioButton.setFont(new Font("Arial", Font.PLAIN, 15));
		hidePasswordRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		hidePasswordRadioButton.setBounds(331, 145, 62, 23);
	}

	private void createLoginButton() {
		JButton btnLogin = new JButton("Login ");
		btnLogin.setFont(GENERAL_LABEL_FONT);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(CORPORATE_BLUE);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginWithProvidedUserCredentials();
			}
		});

		btnLogin.setBounds(157, 175, 108, 37);
		frame.getContentPane().add(btnLogin);
	}

	private void createUsernameComponent() {
		JLabel usernameLabel = createUsernameTextfieldLabel();
		createUsernameTextfield();
		
		frame.getContentPane().add(usernameLabel);
		frame.getContentPane().add(usernameTextField);
		
	}


	public void loginWithProvidedUserCredentials() {
		LoginController.getInstance();
		String username = usernameTextField.getText();
		String password = String.valueOf(passwordTextField.getPassword());
		
		handleNoCredentialsProvided(username, password);

		LoginController.getInstance().loginUser(username, password);

		User loggedInUser = LoginController.getLoggedInUser();
		handleWrongUserCredentials(loggedInUser);
		

		// starting the view based on the user role
		UserRoleView userRoleView = new UserRoleView();
		userRoleView.prepareView();
		frame.dispose();
	}

	private void handleWrongUserCredentials(User loggedInUser) {
		if (loggedInUser == null) {
			resultLabel.setText("Wrong Username or Password");
			passwordTextField.setText("");
			usernameTextField.setText("");
			return;
		}
	}

	private void handleNoCredentialsProvided(String username, String password) {
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			// if no login data provided, mock user with first entry of data provider
			UserDataProvider userDataProvider = new UserDataProvider();
			User mockUser = userDataProvider.getUsers().get(0);
			username = mockUser.getUsername();
			password = mockUser.getPassword();
		}
	}
	
	// preparing GUI elements for login
	
	private void createResultLabel() {
		resultLabel = new JLabel("");
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setFont(GENERAL_LABEL_FONT);
		resultLabel.setBounds(107, 212, 209, 40);
		frame.getContentPane().add(resultLabel);
	}
	
	private void createUsernameTextfield() {
		usernameTextField.setColumns(10);
		usernameTextField.setFont(GENERAL_LABEL_FONT);
		usernameTextField.setBounds(87, 50, 306, 40);
	}
	
	private JLabel createUsernameTextfieldLabel() {
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setFont(GENERAL_LABEL_FONT);
		usernameLabel.setBackground(CORPORATE_BLUE);
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(0, 57, 91, 26);
		return usernameLabel;
	}
	
	private void createPasswordFieldComponent() {
		JLabel passwordLabel = createPasswordFieldLabel();
		createPasswordTextField();
		
		frame.getContentPane().add(passwordLabel);
		frame.getContentPane().add(passwordTextField);
	}
	
	private void createPasswordTextField() {
		passwordTextField = new JPasswordField();
		passwordTextField.setColumns(10);
		passwordTextField.setFont(GENERAL_LABEL_FONT);
		passwordTextField.setBounds(87, 98, 306, 40);
		passwordTextField.setEchoChar('*');
	}
	
	private JLabel createPasswordFieldLabel() {
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(GENERAL_LABEL_FONT);
		passwordLabel.setBackground(CORPORATE_BLUE);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBounds(0, 105, 91, 26);
		return passwordLabel;
	}
}
