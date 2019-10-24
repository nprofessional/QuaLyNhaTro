package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import database.MySqlDB;
import database.Sql;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;

public class FrmServiceController extends JFrame {

	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtUnit;
	private JTextField txtPrice;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmServiceController frame = new FrmServiceController();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void loadData() {
		txtCode.setText("");
		txtName.setText("");
		txtUnit.setText("");
		txtPrice.setText("");
		DefaultTableModel dataModel = new DefaultTableModel();
		dataModel.setColumnIdentifiers(new String[] { "Code", "Name", "Unit", "Price" });
		table.setModel(dataModel);
		try {
			Connection conn = new MySqlDB().getConnection();
			ResultSet rows = MySqlDB.executeQuery(conn, Sql.selectAllService());
			while (rows.next()) {
				dataModel.addRow(new Object[] { rows.getString("code"), rows.getString("name"), rows.getString("unit"),
						rows.getLong("price") });
			}
			conn.close();
		} catch (Exception e) {

		}
	}

	public void formWindowOpened(WindowEvent event) {
		loadData();
	}

	public void rowMouseClicked(MouseEvent event) {
		DefaultTableModel dataModel = (DefaultTableModel) table.getModel();
		txtCode.setText(dataModel.getValueAt(table.getSelectedRow(), 0).toString());
		txtName.setText(dataModel.getValueAt(table.getSelectedRow(), 1).toString());
		txtUnit.setText(dataModel.getValueAt(table.getSelectedRow(), 2).toString());
		txtPrice.setText(dataModel.getValueAt(table.getSelectedRow(), 3).toString());
	}

	public void btnReloadClick(ActionEvent actionEvent) {
		loadData();
	}

	public void btnAddClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.insertService(),
				new String[] { txtCode.getText(), txtName.getText(), txtUnit.getText(), txtPrice.getText() });
		loadData();
	}

	public void btnUpdateClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.updateService(),
				new String[] { txtName.getText(), txtUnit.getText(), txtPrice.getText(), txtCode.getText() });
		loadData();
	}

	public void btnRemoveClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.deleteService(), new String[] { txtCode.getText() });
		loadData();
	}

	/**
	 * Create the frame.
	 */
	public FrmServiceController() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				formWindowOpened(arg0);
			}
		});
		try {
			UIManager.setLookAndFeel(new MaterialLookAndFeel());
			if (UIManager.getLookAndFeel() instanceof MaterialLookAndFeel) {
				MaterialLookAndFeel.changeTheme(new MaterialLiteTheme());
			}
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1500, 10, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)));
		panel_2.setLayout(new BorderLayout(0, 0));
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rowMouseClicked(arg0);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		panel_2.add(table, BorderLayout.CENTER);
		panel_2.add(table.getTableHeader(), BorderLayout.NORTH);
		panel_1.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnAddClick(arg0);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		panel_1.add(btnAdd, "2, 2");

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnUpdateClick(arg0);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		panel_1.add(btnUpdate, "4, 2");

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnRemoveClick(arg0);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		panel_1.add(btnRemove, "6, 2");

		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnReloadClick(arg0);
			}
		});
		panel_1.add(btnReload, "8, 2");
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel = new JLabel("Code");
		panel.add(lblNewLabel, "2, 2, right, default");

		txtCode = new JTextField();
		txtCode.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtCode, "4, 2, fill, default");
		txtCode.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Name");
		panel.add(lblNewLabel_1, "2, 4, right, default");

		txtName = new JTextField();
		txtName.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtName, "4, 4, fill, default");
		txtName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Unit");
		panel.add(lblNewLabel_2, "2, 6, right, default");

		txtUnit = new JTextField();
		txtUnit.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtUnit, "4, 6, fill, default");
		txtUnit.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Price");
		panel.add(lblNewLabel_3, "2, 8, right, default");

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtPrice, "4, 8, fill, default");
		txtPrice.setColumns(10);
		contentPane.setLayout(gl_contentPane);
	}
}
