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

public class FrmRoomPriceController extends JFrame {

	private JPanel contentPane;
	private JComboBox cboCode;
	private JTextField txtPrice;
	private JDateChooser dateFrom;
	private JDateChooser dateTo;
	private JTextField txtDiscountPercent;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRoomPriceController frame = new FrmRoomPriceController();
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
		cboCode.removeAllItems();
		txtPrice.setText("");
		dateFrom.setDate(null);
		dateTo.setDate(null);
		txtDiscountPercent.setText("");
		DefaultTableModel dataModel = new DefaultTableModel();
		dataModel.setColumnIdentifiers(
				new String[] { "Mã phòng", "Giá phòng", "Hiệu lực từ", "Hiệu lực đến", "Phần trăm giảm giá" });
		table.setModel(dataModel);
		try {
			Connection conn = new MySqlDB().getConnection();
			ResultSet rows = MySqlDB.executeQuery(conn, Sql.selectAllRoomPrice());
			while (rows.next()) {
				dataModel.addRow(new Object[] { rows.getString("room_code"), rows.getString("room_price"),
						rows.getString("from"), rows.getString("to"), rows.getString("discount_percent") });
			}
			ResultSet rooms = MySqlDB.executeQuery(conn, Sql.selectRoomCode());
			while (rooms.next()) {
				cboCode.addItem(rooms.getString("code") + " - " + rooms.getString("name"));
			}
			conn.close();
			cboCode.setSelectedIndex(0);
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
	 * @throws ParseException
	 */
	public void rowMouseClicked(MouseEvent event) throws ParseException {
		DefaultTableModel dataModel = (DefaultTableModel) table.getModel();
		for (int i = 0; i < cboCode.getItemCount(); i++) {
			if (dataModel.getValueAt(table.getSelectedRow(), 0).toString()
					.compareTo(cboCode.getItemAt(i).toString().split(" - ")[0].toString()) == 0) {
				cboCode.setSelectedIndex(i);
				break;
			}
		}
		txtPrice.setText(dataModel.getValueAt(table.getSelectedRow(), 1).toString());
		dateFrom.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse(dataModel.getValueAt(table.getSelectedRow(), 2).toString()));
		dateTo.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse(dataModel.getValueAt(table.getSelectedRow(), 3).toString()));
		txtDiscountPercent.setText(dataModel.getValueAt(table.getSelectedRow(), 4).toString());
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
		String[] params = new String[] { cboCode.getSelectedItem().toString().split(" - ")[0], txtPrice.getText(),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateFrom.getDate()),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTo.getDate()), txtDiscountPercent.getText() };
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.insertRoomPrice(), params);
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
	public void btnUpdateClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, ParseException {
		Connection conn = new MySqlDB().getConnection();
		DefaultTableModel dataModel = (DefaultTableModel) table.getModel();
		MySqlDB.executeUpdate(conn, Sql.deleteRoomPrice(),
				new String[] { cboCode.getSelectedItem().toString().split(" - ")[0],
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
								.parse(dataModel.getValueAt(table.getSelectedRow(), 2).toString())),
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
								.parse(dataModel.getValueAt(table.getSelectedRow(), 3).toString())) });
		String[] params = new String[] { cboCode.getSelectedItem().toString().split(" - ")[0], txtPrice.getText(),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateFrom.getDate()),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTo.getDate()), txtDiscountPercent.getText(), };

		MySqlDB.executeUpdate(conn, Sql.insertRoomPrice(), params);
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
		MySqlDB.executeUpdate(conn, Sql.deleteRoomPrice(),
				new String[] { cboCode.getSelectedItem().toString().split(" - ")[0],
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateFrom.getDate()),
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTo.getDate()) });
		conn.close();
		loadData();
	}

	/**
	 * Create the frame.
	 */
	public FrmRoomPriceController() {
		setTitle("Quản lý khách sạn | Cài đặt giá phòng");
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
				try {
					rowMouseClicked(arg0);
				} catch (ParseException e) {
					e.printStackTrace();
				}
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
					try {
						btnUpdateClick(arg0);
					} catch (ParseException e) {
						e.printStackTrace();
					}
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

		cboCode = new JComboBox();
		cboCode.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(cboCode, "4, 2, fill, default");

		JLabel lblNewLabel_1 = new JLabel("Giá phòng");
		panel.add(lblNewLabel_1, "2, 4, right, default");

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtPrice, "4, 4, fill, default");
		txtPrice.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Hiệu lực từ");
		panel.add(lblNewLabel_2, "2, 6, right, default");

		dateFrom = new JDateChooser();
		dateFrom.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(dateFrom, "4, 6, fill, default");

		JLabel lblNewLabel_3 = new JLabel("Hiệu lực đến");
		panel.add(lblNewLabel_3, "2, 8, right, default");

		dateTo = new JDateChooser();
		dateTo.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(dateTo, "4, 8, fill, default");

		JLabel lblNewLabel_4 = new JLabel("Phần trăm giảm giá");
		panel.add(lblNewLabel_4, "2, 10, right, default");

		txtDiscountPercent = new JTextField();
		txtDiscountPercent.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtDiscountPercent, "4, 10, fill, default");
		txtDiscountPercent.setColumns(10);

		contentPane.setLayout(gl_contentPane);
	}

}
