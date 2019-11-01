package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import database.MySqlDB;
import database.Sql;
import helper.Params;
import nnp_common.initForm;

public class FrmInvoice extends JFrame {

	private JPanel contentPane;
	private JTable tblInvoice;
	private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat hour = new SimpleDateFormat("HH");
	private ArrayList<Integer> prices = new ArrayList<Integer>();
	private Date date;
	private int total;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmInvoice frame = new FrmInvoice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void btnPayClick(ActionEvent arg0) throws ClassNotFoundException, SQLException {
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.updateStatusOfRoom(), new String[] { "TT003", Params.ROOM_CODE });
		MySqlDB.executeUpdate(conn, Sql.insertInvoice(),
				new String[] { "SHD" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString(),
						Params.CONTRACT_CODE, String.valueOf(total), String.valueOf(total * (1 + 0.1)),
						Params.EMP_CODE });
		MySqlDB.executeUpdate(conn, Sql.updateCheckOutForContract(),
				new String[] { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date), Params.CONTRACT_CODE });
		conn.close();
		JOptionPane.showMessageDialog(this, "Thanh toán thành công.");
		this.setVisible(false);
		this.dispose();
		FrmRentalManager frmRentalManager = new FrmRentalManager();
		frmRentalManager.setVisible(true);
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 */
	public FrmInvoice() throws ClassNotFoundException, SQLException, ParseException {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					setVisible(false);
					FrmRentalManager frmRentalManager = new FrmRentalManager();
					frmRentalManager.setVisible(true);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		setTitle("Quản lý khách sạn | Hóa đơn");
		setBounds(0, 0, 800, 600);
		setMinimumSize(getSize());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 776, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(37, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)));
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblHanThanh = new JLabel("Hóa đơn thanh toán");
		lblHanThanh.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(lblHanThanh, "2, 2");

		JLabel lblRoom = new JLabel("lblRoom");
		lblRoom.setFont(new Font("Arial", Font.PLAIN, 16));
		lblRoom.setText("Phòng: " + Params.ROOM_CODE);
		panel.add(lblRoom, "2, 4");

		JButton btnPay = new JButton("Thanh toán");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnPayClick(arg0);
					btnPay.setEnabled(false);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnPay, "2, 6");

		tblInvoice = new JTable();
		tblInvoice.setShowGrid(false);
		tblInvoice.setRowSelectionAllowed(false);
		tblInvoice.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setLayout(new BorderLayout());
		panel_1.add(tblInvoice, BorderLayout.CENTER);
		panel_1.add(tblInvoice.getTableHeader(), BorderLayout.NORTH);
		createInvoice();
		panel_1.add(tblInvoice);
		contentPane.setLayout(gl_contentPane);
	}

	public void createInvoice() throws ClassNotFoundException, SQLException, ParseException {
		Date timeCheckOut = new Date();
		date = timeCheckOut;
		DefaultTableModel dataContract = new DefaultTableModel();
		dataContract.setColumnIdentifiers(
				new String[] { "Title", "Quantity", "Per Item", "Discount Percent", "Line Total" });
		tblInvoice.setModel(dataContract);
		tblInvoice.getColumnModel().getColumn(0).setWidth(250);
		tblInvoice.getColumnModel().getColumn(0).setMinWidth(250);
		tblInvoice.getColumnModel().getColumn(0).setPreferredWidth(250);

		tblInvoice.getColumnModel().getColumn(1).setWidth(130);
		tblInvoice.getColumnModel().getColumn(1).setMinWidth(130);
		tblInvoice.getColumnModel().getColumn(1).setPreferredWidth(130);

		tblInvoice.getColumnModel().getColumn(2).setWidth(132);
		tblInvoice.getColumnModel().getColumn(2).setMinWidth(132);
		tblInvoice.getColumnModel().getColumn(2).setPreferredWidth(132);

		tblInvoice.getColumnModel().getColumn(3).setWidth(129);
		tblInvoice.getColumnModel().getColumn(3).setMinWidth(129);
		tblInvoice.getColumnModel().getColumn(3).setPreferredWidth(129);

		tblInvoice.getColumnModel().getColumn(4).setWidth(129);
		tblInvoice.getColumnModel().getColumn(4).setMinWidth(129);
		tblInvoice.getColumnModel().getColumn(4).setPreferredWidth(129);

		String[] params = { Params.CONTRACT_CODE };
		ResultSet rows = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.createInvoice(), params);
		if (rows.wasNull()) {
			JOptionPane.showMessageDialog(this, initForm.nnp_showMessage("M004"));
		}
		while (rows.next()) {
			dataContract.addRow(new Object[] { rows.getString("Name"), "", "", "", "" });
			dataContract.addRow(new Object[] { rows.getString("FullName"), "", "", "", "" });
			dataContract.addRow(
					new Object[] { "From " + sdf.parse(rows.getString("From_Date").toString()), "", "", "", "" });

			long getDiff = timeCheckOut.getTime() - sdf.parse(rows.getString("From_Date")).getTime();
			long getDaysDiff = TimeUnit.MILLISECONDS.toDays(getDiff);
			int hour = timeCheckOut.getHours();

			// Tinh thoi gian
			if (getDaysDiff == 0) {
				getDaysDiff = getDaysDiff + 1;
			} else if (getDaysDiff > 0 && 12 < hour && hour < 15) {
				getDaysDiff = (long) (getDaysDiff + 0.25);

			} else if (getDaysDiff > 0 && 15 < hour && hour < 18) {
				getDaysDiff = (long) (getDaysDiff + 0.5);

			} else if (getDaysDiff > 0 && hour > 18) {
				getDaysDiff = (long) (getDaysDiff + 1);

			}

			// lay gia phong
			String[] pMaPhong = { Params.ROOM_CODE };
			ResultSet rPrice = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.selectRoomPrice(), pMaPhong);
			Double roomRrice = null;
			Double discount = null;
			while (rPrice.next()) {
				roomRrice = rPrice.getDouble("Room_Price");
				// check thoi gian giam gia
				if (timeCheckOut.getTime() >= sdf.parse(rPrice.getString("From_Date")).getTime()
						&& timeCheckOut.getTime() <= sdf.parse(rPrice.getString("To_Date")).getTime()) {
					discount = rPrice.getDouble("Discount_Percent");
				}
			}

			// Tinh tien phong
			int tienThuePhong = 0;
			if (discount > 0) {
				tienThuePhong = (int) Math.round(roomRrice * getDaysDiff * (1 - (discount / 100)));
			} else {
				tienThuePhong = (int) Math.round((roomRrice * getDaysDiff));
			}
			dataContract.addRow(new Object[] { "To      " + timeCheckOut.toString(), getDaysDiff, roomRrice,
					discount + "%", tienThuePhong + " VND" });
			prices.add(tienThuePhong);
		}

		dataContract.addRow(new Object[] { "", "", "", "", "" });
		dataContract.addRow(new Object[] { "", "", "", "", "" });
		dataContract.addRow(new Object[] { "List of services used" });

		String[] pService = { Params.CONTRACT_CODE };
		ResultSet listService = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.getServiceOfContract(), pService);
		while (listService.next()) {
			dataContract.addRow(new Object[] { listService.getString("Name"), (int) listService.getFloat("Quantity"),
					(int) listService.getFloat("Price"), "",
					(int) (listService.getFloat("Quantity") * listService.getFloat("Price")) + " VND" });
			prices.add((int) (listService.getFloat("Quantity") * listService.getFloat("Price")));
		}
		total = 0;
		for (int price : prices) {
			total += price;
		}
		dataContract.addRow(new Object[] { "", "", "", "", "" });
		dataContract.addRow(new Object[] { "", "", "", "", "" });
		dataContract.addRow(new Object[] { "", "", "", "Total:", total + " VND" });
		dataContract.addRow(new Object[] { "", "", "", "Total (+ 10% VAT):", Math.round(total * (1 + 0.1)) + " VND" });
		initForm.nnp_getConnect().close();
	}
}
