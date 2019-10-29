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

import helper.Params;

import database.MySqlDB;
import database.Sql;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;

public class FrmRoomAvailiable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private HashMap<String, String> statusMap;
	private HashMap<String, String> typeMap;
	public static String[] paramsCode = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRoomAvailiable frame = new FrmRoomAvailiable();
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
		DefaultTableModel dataModel = new DefaultTableModel();
		dataModel.setColumnIdentifiers(
				new String[] { "Mã phòng", "Tên phòng", "Trạng thái", "Loại", "Diện tích", "Ghi chú" });
		table.setModel(dataModel);
		statusMap = new HashMap<String, String>();
		typeMap = new HashMap<String, String>();
		try {
			Connection conn = new MySqlDB().getConnection();
			ResultSet rows = MySqlDB.executeQuery(conn, Sql.selectAvaliableRoom());
			while (rows.next()) {
				dataModel.addRow(
						new Object[] { rows.getString("code"), rows.getString("name"), rows.getString("status_name"),
								rows.getString("type_name"), rows.getString("floor"), rows.getString("remark") });
				statusMap.put(rows.getString("code"), rows.getString("status"));
				typeMap.put(rows.getString("code"), rows.getString("type"));
			}
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
		paramsCode = new String[] { dataModel.getValueAt(table.getSelectedRow(), 0).toString() };
	}

	/**
	 * formWindowClosing
	 * 
	 * @param event
	 */
	public void formWindowClosing(WindowEvent event) {
		Params.CUSTOMER_CODE = paramsCode;
	}

	/**
	 * Create the frame.
	 */
	public FrmRoomAvailiable() {
		setTitle("Quản lý khách sạn | Cài đặt phòng");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				formWindowOpened(arg0);
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				formWindowClosing(arg0);
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

		JPanel pnListCustomer = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(pnListCustomer, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(pnListCustomer, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)));
		pnListCustomer.setLayout(new BorderLayout(0, 0));
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
		pnListCustomer.add(table, BorderLayout.CENTER);
		pnListCustomer.add(table.getTableHeader(), BorderLayout.NORTH);

		contentPane.setLayout(gl_contentPane);
	}

}
