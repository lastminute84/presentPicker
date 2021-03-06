/**
 * <p>This class defines the behavior of the start screen of the program.</p>
 * <p>Date of last modification: 29/10/2015</p>
 * 
 * @author Csaba Farkas csaba.farkas@mycit.ie R00117945
 */
package view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.actionlistener.LaunchWorkPanelActionListener;

public class GiftIdeaApplicationMainPanel extends JPanel {

	private static final long serialVersionUID = 1369857900211748899L;
	
	private static final String WELCOME_LABEL_MESSAGE = "Hello Stranger! Please enter your name:";
	private static final String LAUNCH_BUTTON_TEXT = "Launch!";
	
	protected static final String ERROR_MESSAGE_TITLE = "Error";
	
	private JTextField nameField;		//nameField instance variable referencing a JTextField object
	private JButton launchButton;		//launchButton referencing a JButton object
	private JFrame parent;				//parent referencing the JFrame to which this panel is added to
	
	/**
	 * <p>Constructor method makes this class inherit the state and behavior of
	 * {@link JPanel}. It sets the layout to BorderLayout and then uses private methods
	 * to create the two panels added to the frame.</p>
	 * 
	 * @param parent indicates a JFrame object that is the container frame of this panel
	 */
	public GiftIdeaApplicationMainPanel(JFrame parent) {
		super();
		this.parent = parent;
		
		this.setLayout(new BorderLayout());
		JPanel southPanel = new JPanel();
		southPanel.add(createSouthPanel());
		southPanel.setPreferredSize(new Dimension(200, 300));
		southPanel.setOpaque(false);
		this.add(southPanel, BorderLayout.SOUTH);
		this.add(createCenterPanel(), BorderLayout.CENTER);
		this.setOpaque(false);
		this.parent.getRootPane().setDefaultButton(this.launchButton);

	}

	/**
	 * <p>Private method which creates a {@link JPanel} that is added to {@link BorderLayout}.CENTER.
	 * The panel includes a JLabel and a JTextField object. It's layout is also set to
	 * {@link BorderLayout} and its contents are added to {@link BorderLayout}.CENTER so they are 
	 * stretched across the whole panel.</p>
	 * 
	 * @return a JPanel object
	 */
	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setOpaque(false);
		JLabel centerLabel = new JLabel(WELCOME_LABEL_MESSAGE);
		centerLabel.setHorizontalAlignment(JLabel.CENTER);
		centerLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		centerLabel.setForeground(new Color(90, 93, 89));
		centerPanel.add(centerLabel);
		return centerPanel;
	}

	private JPanel createSouthPanel() {
		JPanel southPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(southPanel, BoxLayout.Y_AXIS);
		southPanel.setLayout(boxLayout);
		this.nameField = new JTextField(25);
		this.launchButton = new JButton(LAUNCH_BUTTON_TEXT);
		this.launchButton.setEnabled(false);
		southPanel.add(nameField);
		this.nameField.setAlignmentY(CENTER_ALIGNMENT);
		this.nameField.setHorizontalAlignment(JTextField.CENTER);
		this.nameField.setFont(new Font("Arial Narrow", Font.PLAIN, 21));
		this.nameField.setForeground(new Color(90, 93, 89));
		this.nameField.getDocument().addDocumentListener(new DocumentListener() {

			/**
			 * I attached a DocumentListener to the JTextField. Launch button is
			 * enabled only if JTextField has contents (whitespaces are trimmed).
			 * DocumentListener captures any change, insert or remove event
			 * generated by the JTextField.
			 */
			@Override
			public void changedUpdate(DocumentEvent e) {
				launchButton.setEnabled(checkTextFieldContent());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				launchButton.setEnabled(checkTextFieldContent());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				launchButton.setEnabled(checkTextFieldContent());
			}

			private boolean checkTextFieldContent() {
				return (nameField.getText() != null) && (!nameField.getText().trim().isEmpty());
			}
			
		});
		southPanel.add(Box.createVerticalStrut(10));
		
		southPanel.add(launchButton);
		this.launchButton.setAlignmentX(CENTER_ALIGNMENT);
		this.launchButton.setBackground(new Color(62, 241, 8));
		this.launchButton.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		this.launchButton.addActionListener(new LaunchWorkPanelActionListener(this.nameField.getText().trim(), this.parent));
		
		southPanel.setOpaque(false);
		return southPanel;
	}
}
