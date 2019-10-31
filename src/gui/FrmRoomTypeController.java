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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
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

import database.MySqlDB;
import database.Sql;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;

public class FrmRoomTypeController extends JFrame {

	private JPanel contentPane;
	private JTextField txtCode;
	private JTextField txtName;
	private JTable table;

	/**
	 * loadData
	 */
	public void loadData() {
		txtCode.setText("");
		txtName.setText("");
		DefaultTableModel dataModel = new DefaultTableModel();
		dataModel.setColumnIdentifiers(new String[] { "Mã loại phòng", "Tên loại phòng" });
		table.setModel(dataModel);
		try {
			Connection conn = new MySqlDB().getConnection();
			ResultSet rows = MySqlDB.executeQuery(conn, Sql.selectAllRoomType());
			while (rows.next()) {
				dataModel.addRow(new Object[] { rows.getString("code"), rows.getString("name") });
			}
			conn.close();
		} catch (Exception e) {

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
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.insertRoomType(), new String[] { txtCode.getText(), txtName.getText() });
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
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.updateRoomType(), new String[] { txtName.getText(), txtCode.getText() });
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
		MySqlDB.executeUpdate(conn, Sql.deleteRoomType(), new String[] { txtCode.getText() });
		conn.close();
		loadData();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRoomTypeController frame = new FrmRoomTypeController();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmRoomTypeController() {
		setTitle("Quản lý khách sạn | Cài đặt loại phòng");
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

		JLabel lblNewLabel = new JLabel("Mã loại phòng");
		panel.add(lblNewLabel, "2, 2, right, default");

		txtCode = new JTextField();
		txtCode.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtCode, "4, 2, fill, default");
		txtCode.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tên loại phòng");
		panel.add(lblNewLabel_1, "2, 4, right, default");

		txtName = new JTextField();
		txtName.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtName, "4, 4, fill, default");
		txtName.setColumns(10);
		contentPane.setLayout(gl_contentPane);
	}

}
