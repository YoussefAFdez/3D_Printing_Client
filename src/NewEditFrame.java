import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;

public class NewEditFrame extends JDialog {

	private final JPanel pnlBorder = new JPanel();
	private JTextField txtAuthor;
	private JTextField txtName;
	private JTextField txtResolution;
	private JTextField txtInfill;
	private JTextField txtUrl;
	private Model model;
	private int mode;
	private Connection connection;
	private boolean radioButton;
	private int userid;
	private DefaultListModel<String> editImgPath = new DefaultListModel<>();
	private DefaultListModel<String> editImgName = new DefaultListModel<>();
	private DefaultListModel<String> oldImg = new DefaultListModel<>();
	private JTextField txtFileName;
	private String filePath;


	public NewEditFrame(JFrame parent, int mode, Model model, Connection connection) {
		super(parent, true);
		this.model = model;
		this.mode = mode;
		this.connection = connection;
		initialize();
	}

	public NewEditFrame(JFrame parent, int mode, Connection connection, int userid) {
		super(parent, true);
		this.mode = mode;
		this.connection = connection;
		this.userid = userid;
		initialize();
	}

	public void initialize() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 750);
		getContentPane().setLayout(new BorderLayout());
		pnlBorder.setBackground(new Color(232, 232, 232));
		pnlBorder.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlBorder, BorderLayout.CENTER);
		GridBagLayout gbl_pnlBorder = new GridBagLayout();
		gbl_pnlBorder.columnWidths = new int[]{0, 0};
		gbl_pnlBorder.rowHeights = new int[]{0, 0};
		gbl_pnlBorder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlBorder.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlBorder.setLayout(gbl_pnlBorder);
		pnlBorder.setBorder(new EmptyBorder(10,10,10,10));

		JPanel pnlMain = new JPanel();
		pnlMain.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlMain = new GridBagConstraints();
		gbc_pnlMain.fill = GridBagConstraints.BOTH;
		gbc_pnlMain.gridx = 0;
		gbc_pnlMain.gridy = 0;
		pnlBorder.add(pnlMain, gbc_pnlMain);
		pnlMain.setBorder(new EmptyBorder(7,7,7,7));
		GridBagLayout gbl_pnlMain = new GridBagLayout();
		gbl_pnlMain.columnWidths = new int[]{0, 0};
		gbl_pnlMain.rowHeights = new int[]{125, 0, 0};
		gbl_pnlMain.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlMain.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		pnlMain.setLayout(gbl_pnlMain);

		JPanel pnlMainInfo = new JPanel();
		GridBagConstraints gbc_pnlMainInfo = new GridBagConstraints();
		gbc_pnlMainInfo.insets = new Insets(0, 0, 5, 0);
		gbc_pnlMainInfo.fill = GridBagConstraints.BOTH;
		gbc_pnlMainInfo.gridx = 0;
		gbc_pnlMainInfo.gridy = 0;
		pnlMain.add(pnlMainInfo, gbc_pnlMainInfo);
		GridBagLayout gbl_pnlMainInfo = new GridBagLayout();
		gbl_pnlMainInfo.columnWidths = new int[]{125, 0, 0};
		gbl_pnlMainInfo.rowHeights = new int[]{125, 0};
		gbl_pnlMainInfo.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlMainInfo.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlMainInfo.setLayout(gbl_pnlMainInfo);

		//Label PhotoMain
		JLabel lblPhotoMain = new JLabel("No Image");
		GridBagConstraints gbc_lblPhotoMain = new GridBagConstraints();
		gbc_lblPhotoMain.insets = new Insets(0, 0, 0, 5);
		gbc_lblPhotoMain.gridx = 0;
		gbc_lblPhotoMain.gridy = 0;
		pnlMainInfo.add(lblPhotoMain, gbc_lblPhotoMain);

		JPanel pnlHeader = new JPanel();
		GridBagConstraints gbc_pnlHeader = new GridBagConstraints();
		gbc_pnlHeader.fill = GridBagConstraints.BOTH;
		gbc_pnlHeader.gridx = 1;
		gbc_pnlHeader.gridy = 0;
		pnlMainInfo.add(pnlHeader, gbc_pnlHeader);
		GridBagLayout gbl_pnlHeader = new GridBagLayout();
		gbl_pnlHeader.columnWidths = new int[]{0, 0};
		gbl_pnlHeader.rowHeights = new int[]{10, 70, 10, 0};
		gbl_pnlHeader.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlHeader.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlHeader.setLayout(gbl_pnlHeader);

		JPanel pnlSpacer1 = new JPanel();
		GridBagConstraints gbc_pnlSpacer1 = new GridBagConstraints();
		gbc_pnlSpacer1.insets = new Insets(0, 0, 5, 0);
		gbc_pnlSpacer1.fill = GridBagConstraints.BOTH;
		gbc_pnlSpacer1.gridx = 0;
		gbc_pnlSpacer1.gridy = 0;
		pnlHeader.add(pnlSpacer1, gbc_pnlSpacer1);

		JPanel pnlNameAuthor = new JPanel();
		GridBagConstraints gbc_pnlNameAuthor = new GridBagConstraints();
		gbc_pnlNameAuthor.insets = new Insets(0, 0, 5, 0);
		gbc_pnlNameAuthor.fill = GridBagConstraints.BOTH;
		gbc_pnlNameAuthor.gridx = 0;
		gbc_pnlNameAuthor.gridy = 1;
		pnlHeader.add(pnlNameAuthor, gbc_pnlNameAuthor);
		GridBagLayout gbl_pnlNameAuthor = new GridBagLayout();
		gbl_pnlNameAuthor.columnWidths = new int[]{60, 0, 0};
		gbl_pnlNameAuthor.rowHeights = new int[]{0, 0};
		gbl_pnlNameAuthor.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlNameAuthor.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlNameAuthor.setLayout(gbl_pnlNameAuthor);

		JPanel pnlLabels = new JPanel();
		GridBagConstraints gbc_pnlLabels = new GridBagConstraints();
		gbc_pnlLabels.insets = new Insets(0, 0, 0, 5);
		gbc_pnlLabels.fill = GridBagConstraints.BOTH;
		gbc_pnlLabels.gridx = 0;
		gbc_pnlLabels.gridy = 0;
		pnlNameAuthor.add(pnlLabels, gbc_pnlLabels);
		pnlLabels.setLayout(new GridLayout(4, 1, 0, 0));

		JLabel lblName = new JLabel("Name:*");
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		pnlLabels.add(lblName);

		JLabel lblAuthor = new JLabel("Author:*");
		lblAuthor.setHorizontalAlignment(SwingConstants.TRAILING);
		pnlLabels.add(lblAuthor);

		JLabel lblUrl = new JLabel("URL:");
		lblUrl.setHorizontalAlignment(SwingConstants.TRAILING);
		pnlLabels.add(lblUrl);

		JLabel lblFile = new JLabel("File:*");
		lblFile.setHorizontalAlignment(SwingConstants.TRAILING);
		pnlLabels.add(lblFile);

		JPanel pnlTextFields = new JPanel();
		GridBagConstraints gbc_pnlTextFields = new GridBagConstraints();
		gbc_pnlTextFields.fill = GridBagConstraints.BOTH;
		gbc_pnlTextFields.gridx = 1;
		gbc_pnlTextFields.gridy = 0;
		pnlNameAuthor.add(pnlTextFields, gbc_pnlTextFields);
		pnlTextFields.setLayout(new GridLayout(4, 1, 0, 0));
		pnlTextFields.setBorder(new EmptyBorder(0,0, 0, 10));

		txtName = new JTextField();
		pnlTextFields.add(txtName);
		txtName.setColumns(10);

		txtAuthor = new JTextField();
		pnlTextFields.add(txtAuthor);
		txtAuthor.setColumns(10);

		txtUrl = new JTextField();
		pnlTextFields.add(txtUrl);
		txtUrl.setColumns(10);

		JPanel pnlBrowse = new JPanel();
		pnlTextFields.add(pnlBrowse);
		pnlBrowse.setLayout(new BorderLayout(0, 0));

		JButton btnBrowseFile = new JButton("Browse...");
		pnlBrowse.add(btnBrowseFile, BorderLayout.EAST);

		txtFileName = new JTextField();
		txtFileName.setEditable(false);
		pnlBrowse.add(txtFileName, BorderLayout.CENTER);
		txtFileName.setColumns(10);

		JPanel pnlSpacer2 = new JPanel();
		GridBagConstraints gbc_pnlSpacer2 = new GridBagConstraints();
		gbc_pnlSpacer2.fill = GridBagConstraints.BOTH;
		gbc_pnlSpacer2.gridx = 0;
		gbc_pnlSpacer2.gridy = 2;
		pnlHeader.add(pnlSpacer2, gbc_pnlSpacer2);

		JPanel pnlBody = new JPanel();
		GridBagConstraints gbc_pnlBody = new GridBagConstraints();
		gbc_pnlBody.fill = GridBagConstraints.BOTH;
		gbc_pnlBody.gridx = 0;
		gbc_pnlBody.gridy = 1;
		pnlMain.add(pnlBody, gbc_pnlBody);
		pnlBody.setBorder(new EmptyBorder(10,30,10,30));
		GridBagLayout gbl_pnlBody = new GridBagLayout();
		gbl_pnlBody.columnWidths = new int[]{0, 0};
		gbl_pnlBody.rowHeights = new int[]{0, 0};
		gbl_pnlBody.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlBody.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlBody.setLayout(gbl_pnlBody);

		JPanel pnlBodyMain = new JPanel();
		pnlBodyMain.setBackground(new Color(238,238,238));
		GridBagConstraints gbc_pnlBodyMain = new GridBagConstraints();
		gbc_pnlBodyMain.fill = GridBagConstraints.BOTH;
		gbc_pnlBodyMain.gridx = 0;
		gbc_pnlBodyMain.gridy = 0;
		pnlBody.add(pnlBodyMain, gbc_pnlBodyMain);
		GridBagLayout gbl_pnlBodyMain = new GridBagLayout();
		gbl_pnlBodyMain.columnWidths = new int[]{0, 0};
		gbl_pnlBodyMain.rowHeights = new int[]{70, 130, 70, 45, 0, 20, 0};
		gbl_pnlBodyMain.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlBodyMain.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		pnlBodyMain.setLayout(gbl_pnlBodyMain);

		JPanel pnlCatMaterial = new JPanel();
		pnlCatMaterial.setBackground(new Color(238,238,238));
		GridBagConstraints gbc_pnlCatMaterial = new GridBagConstraints();
		gbc_pnlCatMaterial.insets = new Insets(0, 0, 5, 0);
		gbc_pnlCatMaterial.fill = GridBagConstraints.BOTH;
		gbc_pnlCatMaterial.gridx = 0;
		gbc_pnlCatMaterial.gridy = 0;
		pnlBodyMain.add(pnlCatMaterial, gbc_pnlCatMaterial);
		GridBagLayout gbl_pnlCatMaterial = new GridBagLayout();
		gbl_pnlCatMaterial.columnWidths = new int[]{86, 0, 0};
		gbl_pnlCatMaterial.rowHeights = new int[]{0, 0};
		gbl_pnlCatMaterial.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCatMaterial.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlCatMaterial.setLayout(gbl_pnlCatMaterial);

		JPanel pnlCMLabels = new JPanel();
		GridBagConstraints gbc_pnlCMLabels = new GridBagConstraints();
		gbc_pnlCMLabels.insets = new Insets(0, 0, 0, 5);
		gbc_pnlCMLabels.fill = GridBagConstraints.BOTH;
		gbc_pnlCMLabels.gridx = 0;
		gbc_pnlCMLabels.gridy = 0;
		pnlCatMaterial.add(pnlCMLabels, gbc_pnlCMLabels);
		pnlCMLabels.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblCategory = new JLabel("Category:*");
		lblCategory.setHorizontalAlignment(SwingConstants.TRAILING);
		pnlCMLabels.add(lblCategory);

		JLabel lblMaterial = new JLabel("Material:*");
		lblMaterial.setHorizontalAlignment(SwingConstants.TRAILING);
		pnlCMLabels.add(lblMaterial);

		JPanel pnlCMComboBox = new JPanel();
		GridBagConstraints gbc_pnlCMComboBox = new GridBagConstraints();
		gbc_pnlCMComboBox.fill = GridBagConstraints.BOTH;
		gbc_pnlCMComboBox.gridx = 1;
		gbc_pnlCMComboBox.gridy = 0;
		pnlCatMaterial.add(pnlCMComboBox, gbc_pnlCMComboBox);
		pnlCMComboBox.setLayout(new GridLayout(2, 1, 0, 0));

		String[] categories = { "", "Container", "Stand", "Util", "Figures", "Organizer" };
		JComboBox cbCategory = new JComboBox(categories);
		pnlCMComboBox.add(cbCategory);

		String[] materials = { "", "PLA", "PETG", "ABS" };
		JComboBox cbMaterial = new JComboBox(materials);
		pnlCMComboBox.add(cbMaterial);

		JPanel pnlDescription = new JPanel();
		GridBagConstraints gbc_pnlDescription = new GridBagConstraints();
		gbc_pnlDescription.insets = new Insets(0, 0, 5, 0);
		gbc_pnlDescription.fill = GridBagConstraints.BOTH;
		gbc_pnlDescription.gridx = 0;
		gbc_pnlDescription.gridy = 1;
		pnlBodyMain.add(pnlDescription, gbc_pnlDescription);
		pnlDescription.setLayout(new BorderLayout(0, 0));
		pnlDescription.setBorder(new EmptyBorder(5,5,5,5));

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBorder(new EmptyBorder(0,0,10,0));
		pnlDescription.add(lblDescription, BorderLayout.NORTH);

		JScrollPane scrPaneDescription = new JScrollPane();
		pnlDescription.add(scrPaneDescription, BorderLayout.CENTER);

		JTextArea txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		scrPaneDescription.setViewportView(txtDescription);

		JPanel pnlExtras = new JPanel();
		GridBagConstraints gbc_pnlExtras = new GridBagConstraints();
		gbc_pnlExtras.insets = new Insets(0, 0, 5, 0);
		gbc_pnlExtras.fill = GridBagConstraints.BOTH;
		gbc_pnlExtras.gridx = 0;
		gbc_pnlExtras.gridy = 2;
		pnlBodyMain.add(pnlExtras, gbc_pnlExtras);
		GridBagLayout gbl_pnlExtras = new GridBagLayout();
		gbl_pnlExtras.columnWidths = new int[]{86, 100, 0};
		gbl_pnlExtras.rowHeights = new int[]{0, 0};
		gbl_pnlExtras.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlExtras.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlExtras.setLayout(gbl_pnlExtras);

		JPanel pnlExtrasLabels = new JPanel();
		GridBagConstraints gbc_pnlExtrasLabels = new GridBagConstraints();
		gbc_pnlExtrasLabels.insets = new Insets(0, 0, 0, 5);
		gbc_pnlExtrasLabels.fill = GridBagConstraints.BOTH;
		gbc_pnlExtrasLabels.gridx = 0;
		gbc_pnlExtrasLabels.gridy = 0;
		pnlExtras.add(pnlExtrasLabels, gbc_pnlExtrasLabels);
		pnlExtrasLabels.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblInfill = new JLabel("Infill:");
		lblInfill.setHorizontalAlignment(SwingConstants.TRAILING);
		pnlExtrasLabels.add(lblInfill);

		JLabel lblResolution = new JLabel("Resolution:");
		lblResolution.setHorizontalAlignment(SwingConstants.TRAILING);
		pnlExtrasLabels.add(lblResolution);

		JPanel pnlExtrasTextFields = new JPanel();
		GridBagConstraints gbc_pnlExtrasTextFields = new GridBagConstraints();
		gbc_pnlExtrasTextFields.fill = GridBagConstraints.BOTH;
		gbc_pnlExtrasTextFields.gridx = 1;
		gbc_pnlExtrasTextFields.gridy = 0;
		pnlExtras.add(pnlExtrasTextFields, gbc_pnlExtrasTextFields);
		pnlExtrasTextFields.setLayout(new GridLayout(2, 1, 0, 0));

		txtInfill = new JTextField();
		pnlExtrasTextFields.add(txtInfill);
		txtInfill.setColumns(10);

		txtResolution = new JTextField();
		pnlExtrasTextFields.add(txtResolution);
		txtResolution.setColumns(10);

		JPanel pnlSupports = new JPanel();
		GridBagConstraints gbc_pnlSupports = new GridBagConstraints();
		gbc_pnlSupports.insets = new Insets(0, 0, 5, 0);
		gbc_pnlSupports.fill = GridBagConstraints.BOTH;
		gbc_pnlSupports.gridx = 0;
		gbc_pnlSupports.gridy = 3;
		pnlBodyMain.add(pnlSupports, gbc_pnlSupports);
		pnlSupports.setBorder(new EmptyBorder(5,0,5,0));
		GridBagLayout gbl_pnlSupports = new GridBagLayout();
		gbl_pnlSupports.columnWidths = new int[]{86, 0, 0};
		gbl_pnlSupports.rowHeights = new int[]{0, 0};
		gbl_pnlSupports.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlSupports.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlSupports.setLayout(gbl_pnlSupports);

		JPanel pnlSupportsLabel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlSupportsLabel.getLayout();
		flowLayout.setVgap(7);
		GridBagConstraints gbc_pnlSupportsLabel = new GridBagConstraints();
		gbc_pnlSupportsLabel.insets = new Insets(0, 0, 0, 5);
		gbc_pnlSupportsLabel.fill = GridBagConstraints.BOTH;
		gbc_pnlSupportsLabel.gridx = 0;
		gbc_pnlSupportsLabel.gridy = 0;
		pnlSupports.add(pnlSupportsLabel, gbc_pnlSupportsLabel);

		JLabel lblSupports = new JLabel("Supports:");
		pnlSupportsLabel.add(lblSupports);

		JPanel pnlSupportsRButtons = new JPanel();
		GridBagConstraints gbc_pnlSupportsRButtons = new GridBagConstraints();
		gbc_pnlSupportsRButtons.fill = GridBagConstraints.BOTH;
		gbc_pnlSupportsRButtons.gridx = 1;
		gbc_pnlSupportsRButtons.gridy = 0;
		pnlSupports.add(pnlSupportsRButtons, gbc_pnlSupportsRButtons);
		pnlSupportsRButtons.setLayout(new BorderLayout(5, 5));

		JRadioButton rbYes = new JRadioButton("Yes");
		pnlSupportsRButtons.add(rbYes, BorderLayout.WEST);

		JRadioButton rbNo = new JRadioButton("No");
		pnlSupportsRButtons.add(rbNo, BorderLayout.CENTER);

		ButtonGroup bgSupport = new ButtonGroup();
		bgSupport.add(rbYes);
		bgSupport.add(rbNo);
		rbNo.setSelected(true);
		radioButton = false;

		JPanel pnlImageList = new JPanel();
		GridBagConstraints gbc_pnlImageList = new GridBagConstraints();
		gbc_pnlImageList.insets = new Insets(0, 0, 5, 0);
		gbc_pnlImageList.fill = GridBagConstraints.BOTH;
		gbc_pnlImageList.gridx = 0;
		gbc_pnlImageList.gridy = 4;
		pnlBodyMain.add(pnlImageList, gbc_pnlImageList);
		pnlImageList.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		pnlImageList.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblImages = new JLabel("Images Selected:");
		panel.add(lblImages, BorderLayout.WEST);

		JButton btnBrowse = new JButton("Browse...");
		panel.add(btnBrowse, BorderLayout.EAST);

		JScrollPane scrImg = new JScrollPane();
		pnlImageList.add(scrImg, BorderLayout.CENTER);

		DefaultListModel<String> imgElements = new DefaultListModel<>();
		DefaultListModel<String> imgPath = new DefaultListModel<>();
		JList lstImages = new JList(imgElements);
		scrImg.setViewportView(lstImages);

		JPanel pnlImageButtons = new JPanel();
		GridBagConstraints gbc_pnlImageButtons = new GridBagConstraints();
		gbc_pnlImageButtons.fill = GridBagConstraints.BOTH;
		gbc_pnlImageButtons.gridx = 0;
		gbc_pnlImageButtons.gridy = 5;
		pnlBodyMain.add(pnlImageButtons, gbc_pnlImageButtons);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		pnlImageButtons.add(btnDelete);

		JButton btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		pnlImageButtons.add(btnClear);

		lstImages.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (lstImages.getSelectedIndex() != -1) btnDelete.setEnabled(true);
				else btnDelete.setEnabled(false);
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = lstImages.getSelectedIndex();
				imgElements.remove(index);
				if (mode == 1) imgPath.remove(index);
				else if (editImgName.contains(lstImages.getSelectedValue())){
					index = editImgName.indexOf(lstImages.getSelectedValue());
					editImgName.remove(index);
					editImgPath.remove(index);
				}
			}
		});

		imgElements.addListDataListener(new ListDataListener() {
			@Override
			public void intervalAdded(ListDataEvent e) {
				test();
			}

			@Override
			public void intervalRemoved(ListDataEvent e) {
				test();
			}

			@Override
			public void contentsChanged(ListDataEvent e) {
				test();
			}

			private void test() {
				btnClear.setEnabled(imgElements.getSize() > 0);
			}
		});

		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgElements.clear();
				imgPath.clear();
				editImgPath.clear();
				editImgName.clear();
			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		btnOk.setActionCommand("OK");
		buttonPane.add(btnOk);
		getRootPane().setDefaultButton(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setActionCommand("Cancel");
		buttonPane.add(btnCancel);

		ActionListener rbActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rbYes.isSelected()) radioButton = true;
				else radioButton = false;
			}
		};

		btnBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG, PNG & JPEG Images", "jpg", "png", "jpeg");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					imgElements.add(imgElements.size(), chooser.getSelectedFile().getName());
					if (mode == 1) imgPath.add(imgPath.size(), chooser.getSelectedFile().getAbsolutePath());
					else {
						editImgPath.add(editImgPath.size(), chooser.getSelectedFile().getAbsolutePath());
						editImgName.add(editImgName.size(), chooser.getSelectedFile().getName());
					}
				}
			}
		});

		btnBrowseFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"STL & GCode Files", "stl", "gcode");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					txtFileName.setText(chooser.getSelectedFile().getName());
					filePath = chooser.getSelectedFile().getAbsolutePath();
				}
			}
		});

		//Setting up preloaded values
		if (mode == 2) {
			txtName.setText(model.getName());
			txtAuthor.setText(model.getAuthor());
			txtUrl.setText(model.getUrl());
			cbCategory.setSelectedItem(model.getCategory());
			cbMaterial.setSelectedItem(model.getMaterial());
			txtDescription.setText(model.getDescription());
			txtInfill.setText(model.getInfill());
			txtFileName.setText(model.getFileName());
			txtResolution.setText(model.getResolution());
			rbYes.setSelected(model.isSupports());
			btnOk.setEnabled(true);

			try {
				PreparedStatement s = connection.prepareStatement("SELECT image, imagedescription, imageid FROM images WHERE printid = ?");
				s.setInt(1, model.getId());
				ResultSet results = s.executeQuery();
				int imageId;

				while (results.next()) {
					imageId = results.getInt(3);
					imgElements.addElement(results.getString(2));
					oldImg.addElement(results.getString(2));
					if (imgElements.size() == 1) {
						ImageIcon img = new ImageIcon(results.getBytes(1));
						lblPhotoMain.setIcon(new ImageIcon(img.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
						lblPhotoMain.setText(null);
					}
				}

			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (mode) {
					case 1: //Insert

						//Insert print
						String name = txtName.getText();
						String author = txtAuthor.getText();
						String description = txtDescription.getText().length() > 0 ? txtDescription.getText() : "";
						String category = categories[cbCategory.getSelectedIndex()];
						String material = materials[cbMaterial.getSelectedIndex()];
						String infill = txtInfill.getText().length() > 0 ? txtInfill.getText() : "";
						String resolution = txtResolution.getText().length() > 0 ? txtResolution.getText() : "";
						String url = txtUrl.getText().length() > 0 ? txtUrl.getText() : "";
						boolean support = radioButton;

						PreparedStatement s = null;
						try {
							s = connection.prepareStatement
									("INSERT INTO print (printname, printauthor, printdescription, printcategory, printmaterial, printinfill, printresolution, printurl, printsupports, usercreator, printFile, printFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							s.setString(1, name);
							s.setString(2, author);
							s.setString(3, description);
							s.setString(4, category);
							s.setString(5, material);
							s.setString(6, infill);
							s.setString(7, resolution);
							s.setString(8, url);
							s.setBoolean(9, support);
							s.setInt(10, userid);
							File file = new File(filePath);
							FileInputStream fis = new FileInputStream(file);
							s.setBinaryStream(11, fis, (int) file.length());
							s.setString(12, txtFileName.getText());
							s.execute();
						} catch (SQLException | FileNotFoundException throwables) {
							throwables.printStackTrace();
						}

						//Insert images
						if (imgPath.size() > 0) {
							Statement sCurrval = null;
							int printid = 0;
							try {
								sCurrval = connection.createStatement();
								ResultSet resultSet = sCurrval.executeQuery("SELECT currval('print_printid_seq') FROM print");
								if (resultSet.next()) printid = resultSet.getInt(1);
							} catch (SQLException throwables) {
								throwables.printStackTrace();
							}
							try {
								s = connection.prepareStatement("Insert INTO images (imagedescription, image, printid) values (?, ?, ?)");
								for (int i = 0; i < imgPath.size(); i++) {
									s.setString(1, imgElements.getElementAt(i));
									File file = new File(imgPath.getElementAt(i));
									FileInputStream fis = new FileInputStream(file);
									s.setBinaryStream(2, fis, (int) file.length());
									s.setInt(3, printid);
									s.execute();
								}
							} catch (SQLException | FileNotFoundException throwables) {
								throwables.printStackTrace();
							}
						}
						dispose();
						break;
					case 2: //Update
						FileInputStream fis;
						PreparedStatement s2 = null;
						try {
							if (filePath != null) {
								s2 = connection.prepareStatement
										("UPDATE print SET printname = ?, printauthor = ?, printdescription = ?, printcategory = ?, printmaterial = ?, printinfill = ?, printresolution = ?, printurl = ?, printsupports = ?, printfile = ?, printfilename = ? WHERE printid = ?");
							} else {
								s2 = connection.prepareStatement
										("UPDATE print SET printname = ?, printauthor = ?, printdescription = ?, printcategory = ?, printmaterial = ?, printinfill = ?, printresolution = ?, printurl = ?, printsupports = ?, printfilename = ? WHERE printid = ?");
							}
							s2.setString(1, txtName.getText());
							s2.setString(2, txtAuthor.getText());
							s2.setString(3, txtDescription.getText());
							s2.setString(4, (String) cbCategory.getSelectedItem());
							s2.setString(5, (String) cbMaterial.getSelectedItem());
							s2.setString(6, txtInfill.getText());
							s2.setString(7, txtResolution.getText());
							s2.setString(8, txtUrl.getText());
							s2.setBoolean(9, radioButton);
							if (filePath != null) {
								File modelFile = new File(filePath);
								fis = new FileInputStream(modelFile);
								s2.setBinaryStream(10, fis, (int) modelFile.length());
								s2.setString(11, txtFileName.getText());
								s2.setInt(12, model.getId());
							}  else {
								s2.setString(10, txtFileName.getText());
								s2.setInt(11, model.getId());
							}
							s2.executeUpdate();

							for (int i = 0; i < oldImg.size(); i++) {
								if (!imgElements.contains(oldImg.getElementAt(i))) {
									s = connection.prepareStatement("DELETE from images WHERE printid = ? AND imagedescription = ?");
									s.setInt(1, model.getId());
									s.setString(2, oldImg.elementAt(i));
									s.execute();
								}
							}

							if (editImgPath.size() > 0) {
								s = connection.prepareStatement("Insert INTO images (imagedescription, image, printid) values (?, ?, ?)");
								for (int i = 0; i < editImgPath.size(); i++) {
									if (!oldImg.contains(editImgName.getElementAt(i))) {
										s.setString(1, editImgName.getElementAt(i));
										File file = new File(editImgPath.getElementAt(i));
										fis = new FileInputStream(file);
										s.setBinaryStream(2, fis, (int) file.length());
										s.setInt(3, model.getId());
										s.execute();
									}
								}
							}

						} catch (SQLException | FileNotFoundException throwables) {
							throwables.printStackTrace();
						}
						dispose();
						break;
					default:
				}
			}
		});

		imgPath.addListDataListener(new ListDataListener() {
			@Override
			public void intervalAdded(ListDataEvent e) {
				updatePic();
			}

			@Override
			public void intervalRemoved(ListDataEvent e) {
				updatePic();
			}

			@Override
			public void contentsChanged(ListDataEvent e) {
				updatePic();
			}

			private void updatePic() {
				if (imgPath.size() > 0) {
					ImageIcon imgMain = new ImageIcon(imgPath.getElementAt(0));
					lblPhotoMain.setIcon(new ImageIcon(imgMain.getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
					lblPhotoMain.setText(null);
				} else {
					lblPhotoMain.setIcon(null);
					lblPhotoMain.setText("No Image");
				}
			}
		});

		Document docName = txtName.getDocument();
		Document docAuthor = txtAuthor.getDocument();
		Document docFile = txtFileName.getDocument();

		DocumentListener dl = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				testGeneral(docName, docAuthor, docFile, cbCategory, cbMaterial, btnOk);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				testGeneral(docName, docAuthor, docFile, cbCategory, cbMaterial, btnOk);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				testGeneral(docName, docAuthor, docFile, cbCategory, cbMaterial, btnOk);
			}
		};

		docName.addDocumentListener(dl);
		docAuthor.addDocumentListener(dl);
		docFile.addDocumentListener(dl);

		ActionListener cbActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				testGeneral(docName, docAuthor, docFile, cbCategory, cbMaterial, btnOk);
			}
		};

		cbCategory.addActionListener(cbActionListener);
		cbMaterial.addActionListener(cbActionListener);

		setResizable(false);
		setVisible(true);
	}

	public static void testGeneral(Document docName, Document docAuthor, Document docFile, JComboBox<String> cbCategory, JComboBox<String> cbMaterial, JButton btnOk) {
		btnOk.setEnabled(docName.getLength() > 0 && docAuthor.getLength() > 0 && docFile.getLength() > 0 && cbCategory.getSelectedIndex() > 0 && cbMaterial.getSelectedIndex() > 0);
	}

}
