package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
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

@SuppressWarnings("unused")
public class FrmRentalManager extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaKH;
	private JTextField txtTenKh;
	private JTextField txtMaPhong;
	private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String ma_phong;
	private JTextField txtMaKH_LC;
	private JTable tblContract;
	private JTextField txtMaPhong_S;
	private JTextField txtSoLuong_S;
	private JTable tblService;
	private JComboBox cbxDichVu;
	private List<String> contractNos = new ArrayList<String>();
	private String serviceCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRentalManager frame = new FrmRentalManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public FrmRentalManager() throws ClassNotFoundException, SQLException {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				FrmDashBoard frmDashBoard = new FrmDashBoard();
				frmDashBoard.setVisible(true);
			}
		});
		setTitle("Quản lý khách sạn | Quản lý cho thuê");
		setBounds(0, 0, 800, 600);
		setMinimumSize(getSize());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.PREFERRED_SIZE, 774, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.PREFERRED_SIZE, 551, Short.MAX_VALUE));

		JPanel pnCheckin = new JPanel();
		tabbedPane.addTab("Checkin", null, pnCheckin, null);

		JLabel lblMSKhc = new JLabel("Mã số khác hàng");

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMaKH.setColumns(10);

		txtMaKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] params = new String[] { txtMaKH.getText() };
				ResultSet customer = null;
				try {
					customer = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.selectCustomer(), params);
					if (customer.next()) {
						txtTenKh.setText(customer.getString("FullName"));
					} else {
						JOptionPane.showMessageDialog(tabbedPane, initForm.nnp_showMessage("M001"));
						txtMaKH.setText("");
						txtTenKh.setText("");
					}
					initForm.nnp_getConnect().close();

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		JLabel lblTnKh = new JLabel("Tên KH");

		txtTenKh = new JTextField();
		txtTenKh.setEnabled(false);
		txtTenKh.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTenKh.setColumns(10);

		JLabel lblMSPhng = new JLabel("Mã số phòng");

		JLabel lblLoiKhcHng = new JLabel("Loại khác hàng");
		String arrLoaiKH[] = { "Vãn lai", "Hợp đồng" };
		JComboBox cbxLoaiKH = new JComboBox(arrLoaiKH);
		cbxLoaiKH.setFont(new Font("Arial", Font.PLAIN, 16));

		txtMaPhong = new JTextField();
		txtMaPhong.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMaPhong.setColumns(10);

		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRoomAvailiable frmRoomAvailiable = new FrmRoomAvailiable();
				frmRoomAvailiable.setVisible(true);
				JDialog dialog = new JDialog(frmRoomAvailiable);
				dialog.setModal(true);
//				if (Params.CUSTOMER_CODE.length > 0) {
//					String[] code = Params.CUSTOMER_CODE;
//					txtMaPhong.setText(code[0]);
//				}
			}
		});
		btnSearch.setIcon(new ImageIcon(
				FrmRentalManager.class.getResource("/org/jdesktop/swingx/plaf/windows/resources/search.gif")));

		JButton btnInsert = new JButton("");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Date date = new Date();
					String timeCheckin = sdf.format(date);
					String maHD = "HD" + txtMaKH.getText() + timeCheckin;
					String[] params = new String[] { maHD, txtMaPhong.getText(), Params.EMP_CODE, txtMaKH.getText(),
							cbxLoaiKH.getSelectedItem().toString(), timeCheckin };
					btnInsertContractClick(arg0, params);
					cbxLoaiKH.setSelectedIndex(0);
				} catch (ClassNotFoundException | SQLException e) {
					try {
						JOptionPane.showMessageDialog(tabbedPane, initForm.nnp_showMessage("M003"));
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		pnCheckin.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("26px"), ColumnSpec.decode("105px"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("423px"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("23px"), },
				new RowSpec[] { RowSpec.decode("42px"), RowSpec.decode("19px"), FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("19px"), FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("19px"),
						FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("19px"), FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("41px"), }));
		btnInsert.setIcon(new ImageIcon(FrmRentalManager.class.getResource("/imgs/floppy_drive.png")));
		btnInsert.setSelectedIcon(new ImageIcon(FrmRentalManager.class.getResource("/imgs/floppy_drive.png")));
		pnCheckin.add(btnInsert, "4, 10, right, fill");
		pnCheckin.add(lblMSKhc, "2, 2, fill, center");
		pnCheckin.add(txtMaKH, "4, 2, fill, top");
		pnCheckin.add(lblTnKh, "2, 4, fill, center");
		pnCheckin.add(txtTenKh, "4, 4, fill, top");
		pnCheckin.add(lblMSPhng, "2, 8, fill, center");
		pnCheckin.add(txtMaPhong, "4, 8, fill, top");
		pnCheckin.add(lblLoiKhcHng, "2, 6, fill, center");
		pnCheckin.add(cbxLoaiKH, "4, 6, 1, 2, fill, top");
		pnCheckin.add(btnSearch, "6, 8, fill, fill");

		JPanel pnRental = new JPanel();
		tabbedPane.addTab("Quản lý cho thuê", null, pnRental, null);

		JPanel panel = new JPanel();

		JPanel pnService = new JPanel();
		pnService.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.RED);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JPanel panel_1 = new JPanel();

		JPanel panel_3 = new JPanel();
		GroupLayout gl_pnRental = new GroupLayout(pnRental);
		gl_pnRental.setHorizontalGroup(gl_pnRental.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnRental
				.createSequentialGroup()
				.addGroup(gl_pnRental.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
						.addGroup(gl_pnRental.createSequentialGroup()
								.addComponent(pnService, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_pnRental.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
										.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)))
						.addGroup(gl_pnRental.createSequentialGroup().addGap(12).addComponent(panel_1,
								GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)))
				.addContainerGap()));
		gl_pnRental.setVerticalGroup(gl_pnRental.createParallelGroup(Alignment.LEADING).addGroup(gl_pnRental
				.createSequentialGroup()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_pnRental.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnRental.createSequentialGroup()
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(pnService, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addContainerGap()));

		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setVisible(false);
					dispose();
					FrmInvoice frmInvoice;
					frmInvoice = new FrmInvoice();
					frmInvoice.setVisible(true);
					Params.CONTRACT_CODE = contractNos.get(tblContract.getSelectedRow());
					Params.ROOM_CODE = txtMaPhong_S.getText();
				} catch (ClassNotFoundException | SQLException | ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_1.createSequentialGroup().addComponent(btnCheckout).addContainerGap(683, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(btnCheckout).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JButton btnRemoveRow = new JButton("Remove");

		JButton btnInsertService = new JButton("");
		btnInsertService.setSize(btnRemoveRow.getSize());
		btnInsertService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnAddServiceClick(arg0);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnInsertService.setIcon(new ImageIcon(FrmRentalManager.class.getResource("/imgs/floppy_drive.png")));
		btnInsertService.setForeground(Color.BLACK);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addGap(2).addComponent(btnRemoveRow)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnInsertService,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(256)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_3
				.createSequentialGroup()
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addComponent(btnRemoveRow).addComponent(
						btnInsertService, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_3.setLayout(gl_panel_3);
		btnRemoveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRemoveRowClick(arg0);
			}
		});

		tblService = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tblService.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblService.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(tblService, BorderLayout.CENTER);
		panel_2.add(tblService.getTableHeader(), BorderLayout.NORTH);

		JLabel lblMaPhong_S = new JLabel("Mã số phòng");

		txtMaPhong_S = new JTextField();
		txtMaPhong_S.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMaPhong_S.setEditable(false);
		txtMaPhong_S.setColumns(10);

		JLabel lblSanPham = new JLabel("Sản phẩm");

		cbxDichVu = new JComboBox(getItems());
		cbxDichVu.setFont(new Font("Arial", Font.PLAIN, 16));
		cbxDichVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox oBox = (JComboBox) arg0.getSource();
				ComboValue oValue = (ComboValue) oBox.getSelectedItem();
				serviceCode = oValue.getID();
			}
		});

		JLabel lblSoLuong = new JLabel("Số lượng");

		txtSoLuong_S = new JTextField();
		txtSoLuong_S.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSoLuong_S.setColumns(10);

		JButton btnNewButton = new JButton("Thêm dịch vụ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAddDataServiceTable(arg0);
			}
		});

		JList list = new JList();
		GroupLayout gl_pnService = new GroupLayout(pnService);
		gl_pnService.setHorizontalGroup(gl_pnService.createParallelGroup(Alignment.LEADING).addGroup(gl_pnService
				.createSequentialGroup()
				.addGroup(gl_pnService.createParallelGroup(Alignment.LEADING).addGroup(gl_pnService
						.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnService.createParallelGroup(Alignment.LEADING).addComponent(lblMaPhong_S)
								.addComponent(lblSanPham).addComponent(lblSoLuong))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnService.createParallelGroup(Alignment.TRAILING)
								.addComponent(cbxDichVu, 0, 259, Short.MAX_VALUE)
								.addComponent(txtMaPhong_S, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
								.addGroup(gl_pnService.createSequentialGroup()
										.addComponent(txtSoLuong_S, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton))))
						.addGroup(gl_pnService.createSequentialGroup().addGap(104).addComponent(list,
								GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_pnService.setVerticalGroup(gl_pnService.createParallelGroup(Alignment.LEADING).addGroup(gl_pnService
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnService.createParallelGroup(Alignment.BASELINE).addComponent(lblMaPhong_S).addComponent(
						txtMaPhong_S, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pnService.createParallelGroup(Alignment.BASELINE).addComponent(lblSanPham)
						.addComponent(cbxDichVu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pnService.createParallelGroup(Alignment.BASELINE).addComponent(lblSoLuong)
						.addComponent(txtSoLuong_S, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
				.addGap(18).addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(149, Short.MAX_VALUE)));
		pnService.setLayout(gl_pnService);

		JLabel lblMaPhong_LC = new JLabel("Mã số phòng");

		txtMaKH_LC = new JTextField();
		txtMaKH_LC.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMaKH_LC.setColumns(10);

		JButton bntSearchContract_LC = new JButton("");
		bntSearchContract_LC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnSearchContractClick(e);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		bntSearchContract_LC.setIcon(new ImageIcon(
				FrmRentalManager.class.getResource("/org/jdesktop/swingx/plaf/windows/resources/search.gif")));

		JPanel pnListContract = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblMaPhong_LC).addGap(5)
						.addComponent(txtMaKH_LC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(bntSearchContract_LC, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(555, Short.MAX_VALUE))
				.addComponent(pnListContract, GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(20)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(bntSearchContract_LC, 0, 0, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(lblMaPhong_LC))
								.addComponent(txtMaKH_LC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(pnListContract, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)));

		tblContract = new JTable();
		tblContract.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnListContract.setLayout(new BorderLayout());
		tblContract.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblContract.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		tblContract.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					try {
						rowMouseClicked(arg0);
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		pnListContract.add(tblContract, BorderLayout.CENTER);
		pnListContract.add(tblContract.getTableHeader(), BorderLayout.NORTH);
		pnListContract.add(tblContract);
		panel.setLayout(gl_panel);
		pnRental.setLayout(gl_pnRental);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * btnAddClick
	 * 
	 * @param actionEvent
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void btnInsertContractClick(ActionEvent actionEvent, String[] params)
			throws ClassNotFoundException, SQLException {
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.insertContract(), params);
		MySqlDB.executeUpdate(conn, Sql.updateStatusOfRoom(), new String[] { "TT002", params[1] });
		JOptionPane.showMessageDialog(this, initForm.nnp_showMessage("M002"));
		conn.close();
		txtMaKH.setText("");
		txtTenKh.setText("");

	}

	/**
	 * btnSearchContract
	 * 
	 * @param actionEvent
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void btnSearchContractClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		if (!txtMaKH_LC.getText().isEmpty()) {
			getContract();
		} else {
			getListContract();
		}
	}

	/**
	 * btnAddDataServiceTable
	 * 
	 * @param actionEvent
	 */
	public void btnAddDataServiceTable(ActionEvent actionEvent) {
		DefaultTableModel dataModel = (DefaultTableModel) tblService.getModel();
		tblService.setModel(dataModel);
		dataModel.addRow(new String[] { contractNos.get(tblContract.getSelectedRow()), serviceCode, Params.EMP_CODE,
				txtSoLuong_S.getText(), new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()) });
	}

	/**
	 * btnRemoveRowClick
	 * 
	 * @param actionEvent
	 */
	public void btnRemoveRowClick(ActionEvent actionEvent) {
		DefaultTableModel dataModel = (DefaultTableModel) tblService.getModel();
		dataModel.removeRow(tblService.getSelectedRow());
		tblService.setModel(dataModel);
	}

	/**
	 * btnAddServiceClick
	 * 
	 * @param actionEvent
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void btnAddServiceClick(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		Connection conn = new MySqlDB().getConnection();
		MySqlDB.executeUpdate(conn, Sql.deleteServiceDetail(),
				new String[] { contractNos.get(tblContract.getSelectedRow()) });
		for (int i = 0; i < tblService.getRowCount(); i++) {
			System.out.println(tblService.getValueAt(i, 0));
			MySqlDB.executeUpdate(conn, Sql.insertServiceDetail(),
					new String[] { tblService.getValueAt(i, 0).toString(), tblService.getValueAt(i, 1).toString(),
							tblService.getValueAt(i, 2).toString(), tblService.getValueAt(i, 3).toString(),
							tblService.getValueAt(i, 4).toString() });
		}
		conn.close();
	}

	/**
	 * loadData
	 */
	public void getListContract() {
		DefaultTableModel dataContract = new DefaultTableModel();
		dataContract.setColumnIdentifiers(
				new String[] { "Mã khách hàng", "Họ tên", "Loại hợp đồng", "Mã số phòng", "Thời gian checkin" });
		tblContract.setModel(dataContract);
		contractNos = new ArrayList<String>();
		try {
			ResultSet rows = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.selectAllContract());
			if (rows.wasNull()) {
				JOptionPane.showMessageDialog(this, initForm.nnp_showMessage("M004"));
			}
			while (rows.next()) {
				dataContract.addRow(new Object[] { rows.getString("Cus_Id"), rows.getString("FullName"),
						rows.getString("Cus_Type"), rows.getString("Room_Code"), rows.getString("From_Date"), });
				contractNos.add(rows.getString("contract_no"));
			}
			initForm.nnp_getConnect().close();
		} catch (Exception e) {

		}
	}

	/**
	 * loadData
	 */
	public void getContract() {
		DefaultTableModel dataContract = new DefaultTableModel();
		dataContract.setColumnIdentifiers(
				new String[] { "Mã khách hàng", "Họ tên", "Loại hợp đồng", "Mã số phòng", "Thời gian checkin" });
		tblContract.setModel(dataContract);
		contractNos = new ArrayList<String>();
		try {
			String[] params = { txtMaKH_LC.getText() };
			ResultSet rows = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.selectContract(), params);
			if (rows.wasNull()) {
				JOptionPane.showMessageDialog(this, initForm.nnp_showMessage("M004"));
			}
			while (rows.next()) {
				dataContract.addRow(new Object[] { rows.getString("Cus_Id"), rows.getString("FullName"),
						rows.getString("Cus_Type"), rows.getString("Room_Code"), rows.getString("From_Date"), });
				contractNos.add(rows.getString("contract_no"));
			}
			initForm.nnp_getConnect().close();
		} catch (Exception e) {

		}
	}

	/**
	 * rowMouseClicked
	 * 
	 * @param event
	 * @throws ParseException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void rowMouseClicked(MouseEvent event) throws ParseException, ClassNotFoundException, SQLException {
		DefaultTableModel dataContract = (DefaultTableModel) tblContract.getModel();
		txtMaPhong_S.setText(dataContract.getValueAt(tblContract.getSelectedRow(), 3).toString());
		txtSoLuong_S.setText("");
		DefaultTableModel dataService = new DefaultTableModel();
		dataService.setColumnIdentifiers(
				new String[] { "Mã hợp đồng", "Mã dịch vụ", "Mã nhân viên phục vụ", "Số lượng", "Thời gian" });
		tblService.setModel(dataService);
		Connection conn = new MySqlDB().getConnection();
		ResultSet rows = MySqlDB.executeQuery(conn, Sql.selectServiceDetail(),
				new String[] { contractNos.get(tblContract.getSelectedRow()) });
		while (rows.next()) {
			dataService.addRow(new String[] { rows.getString("contract_no"), rows.getString("service_code"),
					rows.getString("emp_code"), rows.getString("quantity"), rows.getString("created_at") });
		}
		conn.close();
		cbxDichVu.setSelectedIndex(0);
	}

	private class ComboValue {

		private String iID;
		private String strName;

		public ComboValue(String iID, String strName) {
			setID(iID);
			setName(strName);
		}

		public void setName(String strName) {
			this.strName = strName;
		}

		public String getName() {
			return strName;
		}

		public void setID(String iID) {
			this.iID = iID;
		}

		public String getID() {
			return iID;
		}

		public String toString() {
			return getName();
		}
	}

	private ComboValue[] getItems() throws ClassNotFoundException, SQLException {
		ResultSet rows = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.selectAllService());
		rows.last();
		int count = rows.getRow();
		rows.beforeFirst();
		ComboValue[] oItems = new ComboValue[count];
		int i = 0;
		while (rows.next()) {
			oItems[i] = new ComboValue(rows.getString("Code"), rows.getString("Name"));
			i++;
		}
		return oItems;
	}
}
