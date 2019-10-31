package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.MySqlDB;
import database.Sql;

import nnp_common.initForm;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Button;
import javax.swing.ImageIcon;

import gui.FrmRoomAvailiable;

import helper.Params;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JList;

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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public FrmRentalManager() throws ClassNotFoundException, SQLException {
		setTitle("Quản lý khách sạn | Quản lý cho thuê");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		setMinimumSize(getSize());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE));

		JPanel pnCheckin = new JPanel();
		tabbedPane.addTab("Checkin", null, pnCheckin, null);
		
		JLabel lblMSKhc = new JLabel("Mã số khác hàng");

		txtMaKH = new JTextField();
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JLabel lblTnKh = new JLabel("Tên KH");

		txtTenKh = new JTextField();
		txtTenKh.setColumns(10);

		JLabel lblMSPhng = new JLabel("Mã số phòng");

		JLabel lblLoiKhcHng = new JLabel("Loại khác hàng");
		String arrLoaiKH[]={"Vãn lai","Hợp đồng"};
		JComboBox cbxLoaiKH = new JComboBox(arrLoaiKH);

		txtMaPhong = new JTextField();
		txtMaPhong.setColumns(10);

		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRoomAvailiable frmRoomAvailiable = new FrmRoomAvailiable();
				frmRoomAvailiable.setVisible(true);
				JDialog dialog = new JDialog(frmRoomAvailiable);
				dialog.setModal(true);
				String[] code = Params.CUSTOMER_CODE;
				txtMaPhong.setText(code[0]);
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
                    String[] params = new String[] {maHD, txtMaPhong.getText(), "U001", txtMaKH.getText(), cbxLoaiKH.getSelectedItem().toString(), timeCheckin};          
                    btnInsertContractClick(arg0, params);
                    cbxLoaiKH.setSelectedIndex(0);
                } catch (ClassNotFoundException | SQLException e) {
                    try {
                        JOptionPane.showMessageDialog(tabbedPane, initForm.nnp_showMessage("M003"));
                    } catch (HeadlessException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }               
            }
		});
		btnInsert.setIcon(new ImageIcon(FrmRentalManager.class.getResource("/imgs/floppy_drive.png")));
		btnInsert.setSelectedIcon(new ImageIcon(FrmRentalManager.class.getResource("/imgs/floppy_drive.png")));
		GroupLayout gl_pnCheckin = new GroupLayout(pnCheckin);
		gl_pnCheckin.setHorizontalGroup(
		    gl_pnCheckin.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_pnCheckin.createSequentialGroup()
		            .addGap(26)
		            .addGroup(gl_pnCheckin.createParallelGroup(Alignment.TRAILING)
		                .addComponent(btnInsert, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
		                .addGroup(gl_pnCheckin.createParallelGroup(Alignment.LEADING, false)
		                    .addGroup(gl_pnCheckin.createSequentialGroup()
		                        .addComponent(lblMSKhc, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(ComponentPlacement.RELATED)
		                        .addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE))
		                    .addGroup(gl_pnCheckin.createSequentialGroup()
		                        .addComponent(lblTnKh, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(ComponentPlacement.RELATED)
		                        .addComponent(txtTenKh))
		                    .addGroup(gl_pnCheckin.createSequentialGroup()
		                        .addPreferredGap(ComponentPlacement.RELATED)
		                        .addGroup(gl_pnCheckin.createParallelGroup(Alignment.LEADING)
		                            .addGroup(gl_pnCheckin.createSequentialGroup()
		                                .addComponent(lblMSPhng, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
		                                .addPreferredGap(ComponentPlacement.RELATED)
		                                .addComponent(txtMaPhong, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
		                            .addGroup(gl_pnCheckin.createSequentialGroup()
		                                .addComponent(lblLoiKhcHng, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
		                                .addPreferredGap(ComponentPlacement.RELATED)
		                                .addComponent(cbxLoaiKH, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap(180, Short.MAX_VALUE))
		);
		gl_pnCheckin.setVerticalGroup(
		    gl_pnCheckin.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_pnCheckin.createSequentialGroup()
		            .addGap(21)
		            .addGroup(gl_pnCheckin.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_pnCheckin.createSequentialGroup()
		                    .addGap(24)
		                    .addComponent(lblMSKhc))
		                .addGroup(gl_pnCheckin.createSequentialGroup()
		                    .addGap(21)
		                    .addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		            .addGroup(gl_pnCheckin.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_pnCheckin.createSequentialGroup()
		                    .addGap(9)
		                    .addComponent(lblTnKh))
		                .addGroup(gl_pnCheckin.createSequentialGroup()
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(txtTenKh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addGroup(gl_pnCheckin.createParallelGroup(Alignment.BASELINE)
		                .addComponent(cbxLoaiKH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                .addComponent(lblLoiKhcHng))
		            .addPreferredGap(ComponentPlacement.UNRELATED)
		            .addGroup(gl_pnCheckin.createParallelGroup(Alignment.LEADING)
		                .addComponent(btnSearch, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
		                .addGroup(Alignment.TRAILING, gl_pnCheckin.createParallelGroup(Alignment.BASELINE)
		                    .addComponent(lblMSPhng)
		                    .addComponent(txtMaPhong)))
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(btnInsert, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
		            .addGap(352))
		);
		pnCheckin.setLayout(gl_pnCheckin);

		JPanel pnRental = new JPanel();
		tabbedPane.addTab("Quản lý cho thuê", null, pnRental, null);
		
		JPanel panel = new JPanel();
		
		JPanel pnService = new JPanel();
		pnService.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.RED);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnInsertService = new JButton("");
		btnInsertService.setIcon(new ImageIcon(FrmRentalManager.class.getResource("/imgs/floppy_drive.png")));
		btnInsertService.setForeground(Color.BLACK);
		GroupLayout gl_pnRental = new GroupLayout(pnRental);
		gl_pnRental.setHorizontalGroup(
		    gl_pnRental.createParallelGroup(Alignment.LEADING)
		        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
		        .addGroup(gl_pnRental.createSequentialGroup()
		            .addComponent(pnService, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
		        .addGroup(Alignment.TRAILING, gl_pnRental.createSequentialGroup()
		            .addContainerGap(642, Short.MAX_VALUE)
		            .addComponent(btnInsertService, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
		);
		gl_pnRental.setVerticalGroup(
		    gl_pnRental.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_pnRental.createSequentialGroup()
		            .addComponent(panel, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addGroup(gl_pnRental.createParallelGroup(Alignment.LEADING, false)
		                .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(pnService, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
		            .addPreferredGap(ComponentPlacement.UNRELATED)
		            .addComponent(btnInsertService, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap(19, Short.MAX_VALUE))
		);
		
		JLabel lblMaPhong_S = new JLabel("Mã số phòng");
		
		txtMaPhong_S = new JTextField();
		txtMaPhong_S.setEditable(false);
		txtMaPhong_S.setColumns(10);
		
		JLabel lblSanPham = new JLabel("Sản phẩm");
		
		JComboBox cbxDichVu = new JComboBox(getItems());
		
		JLabel lblSoLuong = new JLabel("Số lượng");
		
		txtSoLuong_S = new JTextField();
		txtSoLuong_S.setColumns(10);
		
		JButton btnNewButton = new JButton("Thêm dịch vụ");
		
		JList list = new JList();
		GroupLayout gl_pnService = new GroupLayout(pnService);
		gl_pnService.setHorizontalGroup(
		    gl_pnService.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_pnService.createSequentialGroup()
		            .addGroup(gl_pnService.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_pnService.createSequentialGroup()
		                    .addContainerGap()
		                    .addGroup(gl_pnService.createParallelGroup(Alignment.LEADING)
		                        .addComponent(lblMaPhong_S)
		                        .addComponent(lblSanPham)
		                        .addComponent(lblSoLuong))
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addGroup(gl_pnService.createParallelGroup(Alignment.LEADING)
		                        .addComponent(txtMaPhong_S, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
		                        .addGroup(gl_pnService.createSequentialGroup()
		                            .addComponent(txtSoLuong_S, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
		                            .addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
		                            .addComponent(btnNewButton))
		                        .addComponent(cbxDichVu, 0, 259, Short.MAX_VALUE)))
		                .addGroup(gl_pnService.createSequentialGroup()
		                    .addGap(104)
		                    .addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
		            .addContainerGap())
		);
		gl_pnService.setVerticalGroup(
		    gl_pnService.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_pnService.createSequentialGroup()
		            .addContainerGap()
		            .addGroup(gl_pnService.createParallelGroup(Alignment.BASELINE)
		                .addComponent(lblMaPhong_S)
		                .addComponent(txtMaPhong_S, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		            .addGap(18)
		            .addGroup(gl_pnService.createParallelGroup(Alignment.BASELINE)
		                .addComponent(lblSanPham)
		                .addComponent(cbxDichVu, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
		            .addGap(18)
		            .addGroup(gl_pnService.createParallelGroup(Alignment.BASELINE)
		                .addComponent(lblSoLuong)
		                .addComponent(txtSoLuong_S, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                .addComponent(btnNewButton))
		            .addGap(18)
		            .addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap(149, Short.MAX_VALUE))
		);
		pnService.setLayout(gl_pnService);
		
		JLabel lblMaPhong_LC = new JLabel("Mã số phòng");
		
		txtMaKH_LC = new JTextField();
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
		bntSearchContract_LC.setIcon(new ImageIcon(FrmRentalManager.class.getResource("/org/jdesktop/swingx/plaf/windows/resources/search.gif")));
		
		JPanel pnListContract = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
		    gl_panel.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel.createSequentialGroup()
		            .addContainerGap()
		            .addComponent(lblMaPhong_LC)
		            .addGap(5)
		            .addComponent(txtMaKH_LC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(bntSearchContract_LC, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap(555, Short.MAX_VALUE))
		        .addComponent(pnListContract, GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
		    gl_panel.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel.createSequentialGroup()
		            .addGap(20)
		            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
		                .addComponent(bntSearchContract_LC, 0, 0, Short.MAX_VALUE)
		                .addGroup(gl_panel.createSequentialGroup()
		                    .addGap(3)
		                    .addComponent(lblMaPhong_LC))
		                .addComponent(txtMaKH_LC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		            .addPreferredGap(ComponentPlacement.UNRELATED)
		            .addComponent(pnListContract, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
		);
		
		tblContract = new JTable();
		tblContract.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnListContract.setLayout(new BorderLayout());
		tblContract.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblContract.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));		
		tblContract.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent arg0) {
	                try {
	                    rowMouseClicked(arg0);
	                    cbxDichVu.setSelectedIndex(0);
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
    public void btnInsertContractClick(ActionEvent actionEvent, String[] params) throws ClassNotFoundException, SQLException {  
        MySqlDB.executeUpdate(initForm.nnp_getConnect(), Sql.insertContract(), params);  
        JOptionPane.showMessageDialog(this, initForm.nnp_showMessage("M002"));
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
        if(!txtMaKH_LC.getText().isEmpty()) {
            getContract();
        }
        else {
            getListContract();
        };
    }
    
    /**
     * loadData
     */
    public void getListContract() {
//        txtCode.setText("");
//        txtFullName.setText("");
//        dateBirth.setDate(null);
//        cboGender.setSelectedIndex(0);
//        txtPhone.setText("");
//        txtIdentityCard.setText("");
//        txtAddress.setText("");
        DefaultTableModel dataContract = new DefaultTableModel();
        dataContract.setColumnIdentifiers(new String[] { "Mã khách hàng", "Họ tên", "Loại hợp đồng", "Mã số phòng", "Thời gian checkin"});
        tblContract.setModel(dataContract);
        try {
            ResultSet rows = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.selectAllContract());
            if(!rows.next()) {
                JOptionPane.showMessageDialog(this, initForm.nnp_showMessage("M004"));
            };
            while (rows.next()) {
                dataContract.addRow(new Object[] { rows.getString("Cus_Id"), rows.getString("FullName"),
                        rows.getString("Cus_Type"), rows.getString("Room_Code"), rows.getString("From_Date"),
                        });
            }
            initForm.nnp_getConnect().close();
        } catch (Exception e) {

        }
    }
    
    /**
     * loadData
     */
    public void getContract() {
//        txtCode.setText("");
//        txtFullName.setText("");
//        dateBirth.setDate(null);
//        cboGender.setSelectedIndex(0);
//        txtPhone.setText("");
//        txtIdentityCard.setText("");
//        txtAddress.setText("");
        DefaultTableModel dataContract = new DefaultTableModel();
        dataContract.setColumnIdentifiers(new String[] { "Mã khách hàng", "Họ tên", "Loại hợp đồng", "Mã số phòng", "Thời gian checkin"});
        tblContract.setModel(dataContract);
        try {
           String [] params = {txtMaKH_LC.getText()};
            ResultSet rows = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.selectContract(), params);
            if(!rows.next()) {
                JOptionPane.showMessageDialog(this, initForm.nnp_showMessage("M004"));
            };
            while (rows.next()) {
                dataContract.addRow(new Object[] { rows.getString("Cus_Id"), rows.getString("FullName"),
                        rows.getString("Cus_Type"), rows.getString("Room_Code"), rows.getString("From_Date"),
                        });
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
     */
    public void rowMouseClicked(MouseEvent event) throws ParseException {
        DefaultTableModel dataContract= (DefaultTableModel) tblContract.getModel();
        txtMaPhong_S.setText(dataContract.getValueAt(tblContract.getSelectedRow(), 3).toString());     
        txtSoLuong_S.setText("");
    }
    
    private class ComboValue {

        private String iID;
        private String strName;
        
        public ComboValue(String iID, String strName){
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
        
        public String toString(){
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
        };
        return oItems;
    }
    
}
