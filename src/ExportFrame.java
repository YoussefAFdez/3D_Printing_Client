import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExportFrame {

	private JDialog frame;
	private JFrame parentWindow;
	private DefaultListModel<Model> prevListModel;
	private Connection connection;


	/**
	 * Create the application.
	 */
	public ExportFrame(JFrame parentWindow, DefaultListModel<Model> prevListModel, Connection connection) {
		this.parentWindow = parentWindow;
		this.prevListModel = prevListModel;
		this.connection = connection;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog(parentWindow, true);
		frame.setBounds(100, 100, 350, 450);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Export");
		
		JPanel pnlMain = new JPanel();
		frame.getContentPane().add(pnlMain, BorderLayout.CENTER);
		pnlMain.setBorder(new EmptyBorder(5, 15, 5, 15));
		GridBagLayout gbl_pnlMain = new GridBagLayout();
		gbl_pnlMain.columnWidths = new int[]{0, 0};
		gbl_pnlMain.rowHeights = new int[]{0, 35, 198, 50, 40, 0};
		gbl_pnlMain.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlMain.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlMain.setLayout(gbl_pnlMain);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		pnlMain.add(panel, gbc_panel);
		
		JPanel pnlCb = new JPanel();
		GridBagConstraints gbc_pnlCb = new GridBagConstraints();
		gbc_pnlCb.insets = new Insets(0, 0, 5, 0);
		gbc_pnlCb.fill = GridBagConstraints.BOTH;
		gbc_pnlCb.gridx = 0;
		gbc_pnlCb.gridy = 1;
		pnlMain.add(pnlCb, gbc_pnlCb);
		pnlCb.setLayout(new BorderLayout(0, 0));
		
		JTextField txtPath = new JTextField();
		Document docPath = txtPath.getDocument();
		pnlCb.add(txtPath, BorderLayout.CENTER);
		
		JButton btnExamine = new JButton("Browse...");
		pnlCb.add(btnExamine, BorderLayout.LINE_END);
		
		JPanel pnlList = new JPanel();
		GridBagConstraints gbc_pnlList = new GridBagConstraints();
		gbc_pnlList.insets = new Insets(0, 0, 5, 0);
		gbc_pnlList.fill = GridBagConstraints.BOTH;
		gbc_pnlList.gridx = 0;
		gbc_pnlList.gridy = 2;
		pnlMain.add(pnlList, gbc_pnlList);
		pnlList.setLayout(new BorderLayout(0, 0));

		JList<Model> lstModels = new JList<>(prevListModel);
		lstModels.setCellRenderer(new SelectedCellRenderer());
		pnlList.add(new JScrollPane(lstModels), BorderLayout.CENTER);
		
		JPanel pnlBtn = new JPanel();
		GridBagConstraints gbc_pnlBtn = new GridBagConstraints();
		gbc_pnlBtn.insets = new Insets(0, 0, 5, 0);
		gbc_pnlBtn.fill = GridBagConstraints.BOTH;
		gbc_pnlBtn.gridx = 0;
		gbc_pnlBtn.gridy = 3;
		pnlMain.add(pnlBtn, gbc_pnlBtn);
		pnlBtn.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnCopy = new JButton("Copy");
		btnCopy.setEnabled(false);
		pnlBtn.add(btnCopy);
		
		JButton btnCancel = new JButton("Cancel");
		pnlBtn.add(btnCancel);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 4;
		pnlMain.add(panel_3, gbc_panel_3);

		txtPath.setEditable(false);

		btnExamine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
				chooser.setMultiSelectionEnabled(false);
				int returnVal = chooser.showOpenDialog(frame);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					txtPath.setText(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		btnCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < prevListModel.getSize(); i++) {
					Model model = prevListModel.elementAt(i);
					try {
						FileOutputStream fos = new FileOutputStream(txtPath.getText() + "//" + model.getFileName());
						PreparedStatement s = connection.prepareStatement("SELECT printfile FROM print WHERE printid = ?");
						s.setInt(1, model.getId());
						ResultSet resultSet = s.executeQuery();
						if (resultSet.next()) fos.write(resultSet.getBytes(1));
					} catch (FileNotFoundException | SQLException fileNotFoundException) {
						fileNotFoundException.printStackTrace();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
				}
				frame.dispose();
			}
		});

		docPath.addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				check();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				check();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				check();
			}

			private void check() {
				btnCopy.setEnabled(docPath.getLength() > 0);
			}
		});

		frame.setVisible(true);
	}

}
