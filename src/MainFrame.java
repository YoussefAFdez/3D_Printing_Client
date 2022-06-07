import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.*;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainFrame {

	private JFrame frame;
	private JTextField textField;
	private JFrame ventanaLogin;
	private Connection connection;
	private Model model;
	private int userid;
	private static DefaultListModel<Model> modelList = new DefaultListModel<>();

	/**
	 * Launch the application.
	 */

	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 */
	public MainFrame(JFrame ventanaLogin, Connection connection, int userid) {
		this.ventanaLogin = ventanaLogin;
		this.connection = connection;
		this.userid = userid;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 620);
		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(1100, 620));
		frame.setTitle("Main client");

		JLabel lblCredits = new JLabel("3D Wizard App by Youssef - 2021");
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblCredits, BorderLayout.SOUTH);
		lblCredits.setBorder(new EmptyBorder(0,0,5,0));

		JPanel pnlGeneral = new JPanel();
		frame.getContentPane().add(pnlGeneral, BorderLayout.CENTER);
		GridBagLayout gbl_pnlGeneral = new GridBagLayout();
		gbl_pnlGeneral.columnWidths = new int[]{212, 611, 297, 0};
		gbl_pnlGeneral.rowHeights = new int[]{0, 0};
		gbl_pnlGeneral.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnlGeneral.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlGeneral.setLayout(gbl_pnlGeneral);
		pnlGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel pnlLeft = new JPanel();
		GridBagConstraints gbc_pnlLeft = new GridBagConstraints();
		gbc_pnlLeft.fill = GridBagConstraints.BOTH;
		gbc_pnlLeft.insets = new Insets(0, 0, 0, 5);
		gbc_pnlLeft.gridx = 0;
		gbc_pnlLeft.gridy = 0;
		pnlGeneral.add(pnlLeft, gbc_pnlLeft);
		GridBagLayout gbl_pnlLeft = new GridBagLayout();
		gbl_pnlLeft.columnWidths = new int[]{195, 0};
		gbl_pnlLeft.rowHeights = new int[]{0, 0, 50, 50, 35, 0};
		gbl_pnlLeft.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlLeft.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlLeft.setLayout(gbl_pnlLeft);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		pnlLeft.add(textField, gbc_textField);
		textField.setColumns(10);

		Document docTxt = textField.getDocument();

		JPanel pnlList = new JPanel();
		GridBagConstraints gbc_pnlList = new GridBagConstraints();
		gbc_pnlList.insets = new Insets(0, 0, 5, 0);
		gbc_pnlList.fill = GridBagConstraints.BOTH;
		gbc_pnlList.gridx = 0;
		gbc_pnlList.gridy = 1;
		pnlLeft.add(pnlList, gbc_pnlList);
		pnlList.setLayout(new BorderLayout(0, 0));

		JScrollPane scrPaneSelected = new JScrollPane();
		pnlList.add(scrPaneSelected, BorderLayout.CENTER);

		DefaultListModel<Model> elementsList = new DefaultListModel<>();
		JList<Model> lstSelected = new JList<>(elementsList);
		lstSelected.setCellRenderer(new SelectedCellRenderer());
		scrPaneSelected.setViewportView(lstSelected);
		//pnlList.add(list, BorderLayout.CENTER);

		JPanel pnlBtn1 = new JPanel();
		GridBagConstraints gbc_pnlBtn1 = new GridBagConstraints();
		gbc_pnlBtn1.insets = new Insets(0, 0, 5, 0);
		gbc_pnlBtn1.fill = GridBagConstraints.BOTH;
		gbc_pnlBtn1.gridx = 0;
		gbc_pnlBtn1.gridy = 2;
		pnlLeft.add(pnlBtn1, gbc_pnlBtn1);
		pnlBtn1.setLayout(new GridLayout(1, 2, 0, 0));

		JButton btnNew = new JButton("New");
		btnNew.setIcon(new ImageIcon("plus-3.png"));
		btnNew.setFont(btnNew.getFont().deriveFont(24.0f));
		pnlBtn1.add(btnNew);

		JButton btnClear = new JButton("Clear");
		btnClear.setIcon(new ImageIcon("broom.png"));
		btnClear.setEnabled(false);
		pnlBtn1.add(btnClear);

		JPanel pnlBtn2 = new JPanel();
		GridBagConstraints gbc_pnlBtn2 = new GridBagConstraints();
		gbc_pnlBtn2.insets = new Insets(0, 0, 5, 0);
		gbc_pnlBtn2.fill = GridBagConstraints.BOTH;
		gbc_pnlBtn2.gridx = 0;
		gbc_pnlBtn2.gridy = 3;
		pnlLeft.add(pnlBtn2, gbc_pnlBtn2);
		pnlBtn2.setLayout(new BorderLayout());

		JButton btnExport = new JButton("Export");
		btnExport.setIcon(new ImageIcon("outside-2.png"));
		btnExport.setEnabled(false);
		btnExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ExportFrame(frame, elementsList, connection);
			}
		});
		pnlBtn2.add(btnExport, BorderLayout.CENTER);

		JPanel pnlSpacer1 = new JPanel();
		GridBagConstraints gbc_pnlSpacer1 = new GridBagConstraints();
		gbc_pnlSpacer1.fill = GridBagConstraints.BOTH;
		gbc_pnlSpacer1.gridx = 0;
		gbc_pnlSpacer1.gridy = 4;
		pnlLeft.add(pnlSpacer1, gbc_pnlSpacer1);

		JPanel pnlCenter = new JPanel();
		GridBagConstraints gbc_pnlCenter = new GridBagConstraints();
		gbc_pnlCenter.insets = new Insets(0, 0, 0, 5);
		gbc_pnlCenter.fill = GridBagConstraints.BOTH;
		gbc_pnlCenter.gridx = 1;
		gbc_pnlCenter.gridy = 0;
		pnlGeneral.add(pnlCenter, gbc_pnlCenter);
		pnlCenter.setLayout(new BorderLayout(0, 0));

		JPanel pnlFilters = new JPanel();
		pnlCenter.add(pnlFilters, BorderLayout.SOUTH);
		pnlFilters.setLayout(new BorderLayout(0, 0));

		JLabel lblCategory = new JLabel("Category");
		pnlFilters.add(lblCategory, BorderLayout.EAST);
		lblCategory.setBorder(new EmptyBorder(0,0,0,10));

		String[] categories = { "", "Container", "Stand", "Util", "Figures", "Organizer" };
		JComboBox<String> cmbCategory = new JComboBox<>(categories);
		pnlFilters.add(cmbCategory, BorderLayout.CENTER);
		cmbCategory.setBorder(new EmptyBorder(0,10,0,0));

		JPanel pnlCheckBoxes = new JPanel();
		pnlFilters.add(pnlCheckBoxes, BorderLayout.WEST);
		pnlCheckBoxes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JCheckBox cbImage = new JCheckBox("Image");
		pnlCheckBoxes.add(cbImage);

		JCheckBox cbPla = new JCheckBox("PLA");
		pnlCheckBoxes.add(cbPla);

		JPanel pnlElements = new JPanel();
		pnlCenter.add(pnlElements, BorderLayout.CENTER);
		pnlElements.setLayout(new BorderLayout(0, 0));

		JScrollPane scrlPaneElements = new JScrollPane();
		pnlElements.add(scrlPaneElements, BorderLayout.CENTER);

		JList<Model> lstElements = new JList<>();

		//Creating model container for all elements
		modelList = loadList();
		lstElements.setModel(modelList);
		scrlPaneElements.setViewportView(lstElements);
		lstElements.setSelectionModel(new DefaultListSelectionModel() {
			@Override
			public void setSelectionInterval(int index0, int index1) {
				if(super.isSelectedIndex(index0)) {
					super.removeSelectionInterval(index0, index1);
				}
				else {
					super.addSelectionInterval(index0, index1);
				}
			}
		});

		lstElements.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		lstElements.setFixedCellHeight(150);
		lstElements.setFixedCellWidth(130);
		lstElements.setVisibleRowCount(-1);

		JPanel pnlRight = new JPanel();
		GridBagConstraints gbc_pnlRight = new GridBagConstraints();
		gbc_pnlRight.fill = GridBagConstraints.BOTH;
		gbc_pnlRight.gridx = 2;
		gbc_pnlRight.gridy = 0;
		pnlGeneral.add(pnlRight, gbc_pnlRight);
		GridBagLayout gbl_pnlRight = new GridBagLayout();
		gbl_pnlRight.columnWidths = new int[]{0, 0};
		gbl_pnlRight.rowHeights = new int[]{340, 124, 50, 32, 0};
		gbl_pnlRight.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlRight.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlRight.setLayout(gbl_pnlRight);

		JPanel pnlMainImg = new JPanel();
		GridBagConstraints gbc_pnlMainImg = new GridBagConstraints();
		gbc_pnlMainImg.insets = new Insets(0, 0, 5, 0);
		gbc_pnlMainImg.fill = GridBagConstraints.BOTH;
		gbc_pnlMainImg.gridx = 0;
		gbc_pnlMainImg.gridy = 0;
		pnlRight.add(pnlMainImg, gbc_pnlMainImg);
		GridBagLayout gbl_pnlMainImg = new GridBagLayout();
		gbl_pnlMainImg.columnWidths = new int[]{300, 0};
		gbl_pnlMainImg.rowHeights = new int[]{260, 80, 0};
		gbl_pnlMainImg.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlMainImg.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		pnlMainImg.setLayout(gbl_pnlMainImg);

		JLabel lblMainImg = new JLabel("IMAGE");
		GridBagConstraints gbc_lblMainImg = new GridBagConstraints();
		gbc_lblMainImg.insets = new Insets(0, 0, 5, 0);
		gbc_lblMainImg.gridx = 0;
		gbc_lblMainImg.gridy = 0;
		pnlMainImg.add(lblMainImg, gbc_lblMainImg);

		JPanel pnlImg = new JPanel();
		GridBagConstraints gbc_pnlImg = new GridBagConstraints();
		gbc_pnlImg.fill = GridBagConstraints.BOTH;
		gbc_pnlImg.gridx = 0;
		gbc_pnlImg.gridy = 1;
		pnlMainImg.add(pnlImg, gbc_pnlImg);
		pnlImg.setMaximumSize(new Dimension(300, 70));
		pnlImg.setLayout(new BorderLayout(0, 0));

		JScrollPane sclPaneImgs = new JScrollPane();
		pnlImg.add(sclPaneImgs, BorderLayout.CENTER);

		DefaultListModel<byte[]> imagesListModel = new DefaultListModel<>();
		JList<byte[]> lstImages = new JList<>(imagesListModel);
		lstImages.setCellRenderer(new ImageListRenderer());
		sclPaneImgs.setViewportView(lstImages);
		lstImages.setFixedCellWidth(70);
		lstImages.setFixedCellHeight(70);
		lstImages.setVisibleRowCount(1);
		lstImages.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		lstImages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel pnlDescription = new JPanel();
		GridBagConstraints gbc_pnlDescription = new GridBagConstraints();
		gbc_pnlDescription.insets = new Insets(0, 0, 5, 0);
		gbc_pnlDescription.fill = GridBagConstraints.BOTH;
		gbc_pnlDescription.gridx = 0;
		gbc_pnlDescription.gridy = 1;
		pnlRight.add(pnlDescription, gbc_pnlDescription);
		pnlDescription.setLayout(new BorderLayout(0, 0));


		JTextArea txtAreaDescription = new JTextArea();
		txtAreaDescription.setEditable(false);
		txtAreaDescription.setLineWrap(true);
		txtAreaDescription.setWrapStyleWord(true);
		JScrollPane scrlPaneDescription = new JScrollPane(txtAreaDescription);

		pnlDescription.add(scrlPaneDescription, BorderLayout.CENTER);

		JPanel pnlBtnManage = new JPanel();
		GridBagConstraints gbc_pnlBtnManage = new GridBagConstraints();
		gbc_pnlBtnManage.insets = new Insets(0, 0, 5, 0);
		gbc_pnlBtnManage.fill = GridBagConstraints.BOTH;
		gbc_pnlBtnManage.gridx = 0;
		gbc_pnlBtnManage.gridy = 2;
		pnlRight.add(pnlBtnManage, gbc_pnlBtnManage);
		pnlBtnManage.setLayout(new GridLayout(1, 2, 0, 0));

		JButton btnEdit = new JButton("Edit");
		btnEdit.setIcon(new ImageIcon("pencil-2.png"));
		btnEdit.setEnabled(false);
		pnlBtnManage.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon("remove-2.png"));
		btnDelete.setEnabled(false);
		pnlBtnManage.add(btnDelete);

		JPanel pnlSpacer2 = new JPanel();
		GridBagConstraints gbc_pnlSpacer2 = new GridBagConstraints();
		gbc_pnlSpacer2.fill = GridBagConstraints.BOTH;
		gbc_pnlSpacer2.gridx = 0;
		gbc_pnlSpacer2.gridy = 3;
		pnlRight.add(pnlSpacer2, gbc_pnlSpacer2);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ventanaLogin.setVisible(true);
				frame.dispose();
			}
		});

		elementsList.addListDataListener(new ListDataListener() {
			@Override
			public void intervalAdded(ListDataEvent e) {
				check();
			}

			@Override
			public void intervalRemoved(ListDataEvent e) {
				check();
			}

			@Override
			public void contentsChanged(ListDataEvent e) {
				check();
			}

			private void check() {
				btnClear.setEnabled(elementsList.getSize() > 0);
				btnExport.setEnabled(elementsList.getSize() > 0);
			}
		});

		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new NewEditFrame(frame, 1, connection, userid);
				modelList = loadList();
			}
		});

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new NewEditFrame(frame, 2, lstSelected.getSelectedValue(), connection);
				modelList = loadList();
			}
		});

		lstElements.setCellRenderer(new ElementsCellRenderer(connection));

		lstSelected.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				txtAreaDescription.setText("");
				imagesListModel.clear();
				if (lstSelected.getSelectedIndex() != -1) {
					Model modelDescription = lstSelected.getSelectedValue();

					try {
						PreparedStatement s = connection.prepareStatement("SELECT image FROM images WHERE printid = ?");
						s.setInt(1, modelDescription.getId());
						ResultSet resultSet = s.executeQuery();
						while (resultSet.next()) {
							imagesListModel.add(imagesListModel.size(), resultSet.getBytes(1));
							if (imagesListModel.size() == 1) lstImages.setSelectedIndex(0);
						}
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}

					//Loading Description
					txtAreaDescription.append("Author: " + modelDescription.getAuthor() + "\n\n");
					txtAreaDescription.append("Category: " + modelDescription.getCategory() + "\n\n");
					txtAreaDescription.append("Description:\n");
					txtAreaDescription.append(modelDescription.getDescription() + "\n\n");
					txtAreaDescription.append("Material: " + modelDescription.getMaterial() + "\n\n");
					txtAreaDescription.append("Infill: " + modelDescription.getInfill() + "\n\n");
					txtAreaDescription.append("Resolution: " + "\n\n");
					txtAreaDescription.append("Supports: " + (modelDescription.isSupports() ? "Yes" : "No") + "\n\n");
					txtAreaDescription.append("URL: " + modelDescription.getUrl() + "\n\n");

					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
				} else {
					btnEdit.setEnabled(false);
					btnDelete.setEnabled(false);
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Model modelElim = lstSelected.getSelectedValue();
				int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the current selected model?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				if (option == 0) {
					try {
						PreparedStatement s = connection.prepareStatement("DELETE FROM images WHERE printid = ?");
						s.setInt(1, modelElim.getId());
						s.execute();

						s = connection.prepareStatement("DELETE FROM print WHERE printid = ?");
						s.setInt(1, modelElim.getId());
						s.execute();
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
					loadListTextFiltered(textField.getText(), cbImage, cbPla, cmbCategory);
				}
			}
		});

		lstElements.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					elementsList.clear();
					List<Model> list = new ArrayList<>();
					list = lstElements.getSelectedValuesList();
					for (Model modelElement : list) {
						elementsList.add(elementsList.size(), modelElement);
					}
				}
			}
		});

		lstImages.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (lstImages.getSelectedIndex() != -1) {
					ImageIcon img = new ImageIcon(lstImages.getSelectedValue());
					lblMainImg.setIcon(new ImageIcon(img.getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH)));
					lblMainImg.setText(null);
				} else {
					lblMainImg.setIcon(null);
					lblMainImg.setText("No Image");
				}
			}
		});


		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elementsList.clear();
				lstElements.clearSelection();
			}
		});


		docTxt.addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				modelList = loadListTextFiltered(textField.getText(), cbImage, cbPla, cmbCategory);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				modelList = loadListTextFiltered(textField.getText(), cbImage, cbPla, cmbCategory);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				modelList = loadListTextFiltered(textField.getText(), cbImage, cbPla, cmbCategory);
			}
		});

		ActionListener alComboBoxes = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelList = loadListTextFiltered(textField.getText(), cbImage, cbPla, cmbCategory);
			}
		};

		cbImage.addActionListener(alComboBoxes);
		cbPla.addActionListener(alComboBoxes);
		cmbCategory.addActionListener(alComboBoxes);


		//MenuBar Configuration
		//JMenuBar menuBar = new JMenuBar();
	}

	private DefaultListModel<Model> loadList() {
		Statement s;
		try {
			s = connection.createStatement();
			ResultSet result = s.executeQuery("SELECT printid, printname, printauthor, printfilename, printurl, printdescription, printcategory, printmaterial, printinfill, printresolution, printsupports, usercreator FROM print");
			modelList.clear();
			while (result.next()) {
				int id = result.getInt("printid");
				String name = result.getString("printname");
				String author = result.getString("printauthor");
				String url = result.getString("printurl");
				String fileName = result.getString("printfilename");
				String description = result.getString("printdescription");
				String category = result.getString("printcategory");
				String material = result.getString("printmaterial");
				String infill = result.getString("printinfill");
				String resolution = result.getString("printresolution");
				boolean support = result.getBoolean("printsupports");
				int creator = result.getInt("usercreator");

				modelList.addElement(new Model(id, name, author, url, fileName, description, category, material, infill, resolution, support, creator));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return modelList;
	}


	private DefaultListModel<Model> loadListTextFiltered(String filter, JCheckBox cbImagen, JCheckBox cbPla, JComboBox<String> cbCategoria) {
		PreparedStatement s;
		try {
			String sql = "SELECT printid, printname, printauthor, printurl, printfilename, printdescription, printcategory, printmaterial, printinfill, printresolution, printsupports, usercreator FROM print WHERE printname ILIKE ?";
			if (cbImagen.isSelected()) sql += " AND (SELECT count(*) FROM images WHERE images.printid = print.printid HAVING count(*) > 0) > 0";
			if (cbPla.isSelected()) sql += " AND printmaterial = 'PLA'";
			if (cbCategoria.getSelectedIndex() > 0) {
				String category = (String) cbCategoria.getSelectedItem();
				sql += " AND printcategory = '" + category + "'";
			}
			s = connection.prepareStatement(sql);
			s.setString(1, "%" + filter + "%");
			ResultSet result = s.executeQuery();
			modelList.clear();
			while (result.next()) {
				int id = result.getInt("printid");
				String name = result.getString("printname");
				String author = result.getString("printauthor");
				String url = result.getString("printurl");
				String filename = result.getString("printfilename");
				String description = result.getString("printdescription");
				String category = result.getString("printcategory");
				String material = result.getString("printmaterial");
				String infill = result.getString("printinfill");
				String resolution = result.getString("printresolution");
				boolean support = result.getBoolean("printsupports");
				int creator = result.getInt("usercreator");

				modelList.addElement(new Model(id, name, author, url, filename, description, category, material, infill, resolution, support, creator));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return modelList;
	}

}
