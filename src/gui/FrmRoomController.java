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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

import database.MySqlDB;
import database.Sql;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;

public class FrmRoomController extends JFrame {

	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JComboBox cboStatus;
	private JComboBox cboType;
	private JTextField txtFloor;
	private JTextField txtRemark;
	private JTable table;
	private HashMap<String, String> statusMap;
	private HashMap<String, String> typeMap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRoomController frame = new FrmRoomController();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * loadData
	 */
	public void loadData() {
		txtCode.setText("");
		txtName.setText("");
		txtFloor.setText("");
		txtRemark.setText("");
		cboStatus.removeAllItems();
		cboType.removeAllItems();
		DefaultTableModel dataModel = new DefaultTableModel();
		dataModel.setColumnIdentifiers(
				new String[] { "Mã phòng", "Tên phòng", "Trạng thái", "Loại", "Diện tích", "Ghi chú" });
		table.setModel(dataModel);
		statusMap = new HashMap<String, String>();
		typeMap = new HashMap<String, String>();
		try {
			Connection conn = new MySqlDB().getConnection();
			ResultSet rows = MySqlDB.executeQuery(conn, Sql.selectAllRoom());
			while (rows.next()) {
				dataModel.addRow(
						new Object[] { rows.getString("code"), rows.getString("name"), rows.getString("status_name"),
								rows.getString("type_name"), rows.getString("floor"), rows.getString("remark") });
				statusMap.put(rows.getString("code"), rows.getString("status"));
				typeMap.put(rows.getString("code"), rows.getString("type"));
			}
			ResultSet status = MySqlDB.executeQuery(conn, Sql.selectAllRoomStatus());
			while (status.next()) {
				cboStatus.addItem(status.getString("code") + " - " + status.getString("name"));
			}
			ResultSet types = MySqlDB.executeQuery(conn, Sql.selectAllRoomType());
			while (types.next()) {
				cboType.addItem(types.getString("code") + " - " + types.getString("name"));
			}
			cboStatus.setSelectedIndex(0);
			cboType.setSelectedIndex(0);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * formWindowOpened
	 * 
	 * @param event
	 */
	public void formWindowOpened(WindowEvent event) {
		loadData();
	}

	/**
	 * rowMouseClicked
	 * 
	 * @param event
	 */
	public void rowMouseClicked(MouseEvent event) {
		DefaultTableModel dataModel = (DefaultTableModel) table.getModel();
		txtCode.setText(dataModel.getValueAt(table.getSelectedRow(), 0).toString());
		txtName.setText(dataModel.getValueAt(table.getSelectedRow(), 1).toString());
		for (int i = 0; i < cboStatus.getItemCount(); i++) {
			if (statusMap.get(dataModel.getValueAt(table.getSelectedRow(), 0).toString())
					.compareTo(cboStatus.getItemAt(i).toString().split(" - ")[0].toString()) == 0) {
				cboStatus.setSelectedIndex(i);
				break;
			}
		}
		for (int i = 0; i < cboType.getItemCount(); i++) {
			if (typeMap.get(dataModel.getValueAt(table.getSelectedRow(), 0).toString())
					.compareTo(cboType.getItemAt(i).toString().split(" - ")[0].toString()) == 0) {
				cboType.setSelectedIndex(i);
				break;
			}
		}
		txtFloor.setText(dataModel.getValueAt(table.getSelectedRow(), 4).toString());
		txtRemark.setText(dataModel.getValueAt(table.getSelectedRow(), 5).toString());
	}

	/**
	 * btnReloadClick
	 * 
	 * @param actionEvent
	 */
	public void btnReloadClick(ActionEvent actionEvent) {
		loadData();
	}

	/**
	 * btnAddClick
	 * 
	 * @param actionEvent
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void btnAddClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		String[] params = new String[] { txtCode.getText(), txtName.getText(),
				cboStatus.getSelectedItem().toString().split(" - ")[0],
				cboType.getSelectedItem().toString().split(" - ")[0], txtFloor.getText(), txtRemark.getText() };
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.insertRoom(), params);
		conn.close();
		loadData();
	}

	/**
	 * btnUpdateClick
	 * 
	 * @param actionEvent
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void btnUpdateClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		String[] params = new String[] { txtName.getText(),
				cboStatus.getSelectedItem().toString().split(" - ")[0].toString(),
				cboType.getSelectedItem().toString().split(" - ")[0].toString(), txtFloor.getText(),
				txtRemark.getText(), txtCode.getText() };
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.updateRoom(), params);
		conn.close();
		loadData();
	}

	/**
	 * btnRemoveClick
	 * 
	 * @param actionEvent
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void btnRemoveClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.deleteRoom(), new String[] { txtCode.getText() });
		conn.close();
		loadData();
	}

	/**
	 * Create the frame.
	 */
	public FrmRoomController() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Quản lý khách sạn | Cài đặt phòng");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				formWindowOpened(arg0);
			}

			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				FrmDashBoard frmDashBoard = new FrmDashBoard();
				frmDashBoard.setVisible(true);
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
		setBounds(0, 0, 800, 600);
		setMinimumSize(getSize());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
				.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)));
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
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
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
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel = new JLabel("Mã phòng");
		panel.add(lblNewLabel, "2, 2, right, default");

		txtCode = new JTextField();
		txtCode.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtCode, "4, 2, fill, default");
		txtCode.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tên phòng");
		panel.add(lblNewLabel_1, "2, 4, right, default");

		txtName = new JTextField();
		txtName.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtName, "4, 4, fill, default");
		txtName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Trạng thái");
		panel.add(lblNewLabel_2, "2, 6, right, default");

		cboStatus = new JComboBox();
		cboStatus.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(cboStatus, "4, 6, fill, default");

		JLabel lblNewLabel_3 = new JLabel("Loại");
		panel.add(lblNewLabel_3, "2, 8, right, default");

		cboType = new JComboBox();
		cboType.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(cboType, "4, 8, fill, default");

		JLabel lblNewLabel_4 = new JLabel("Diện tích");
		panel.add(lblNewLabel_4, "2, 10, right, default");

		txtFloor = new JTextField();
		txtFloor.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtFloor, "4, 10, fill, default");
		txtFloor.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Ghi chú");
		panel.add(lblNewLabel_5, "2, 12, right, default");

		txtRemark = new JTextField();
		txtRemark.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtRemark, "4, 12, fill, default");
		txtRemark.setColumns(10);

		contentPane.setLayout(gl_contentPane);
	}

}
