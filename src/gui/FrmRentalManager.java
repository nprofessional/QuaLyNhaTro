package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.MySqlDB;
import database.Sql;

import nnp_common.initForm;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Button;
import javax.swing.ImageIcon;

import gui.FrmRoomAvailiable;

public class FrmRentalManager extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    public static String ma_phong;

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
     */
    public FrmRentalManager() {
        setTitle("Quản lý khách sạn | Quản lý cho thuê");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 600);
        setMinimumSize(getSize());
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );
        
        JPanel pnCheckin = new JPanel();
        tabbedPane.addTab("Checkin", null, pnCheckin, null);
        
        JLabel lblMSKhc = new JLabel("Mã số khác hàng");
        
        textField = new JTextField();
        textField.setColumns(10);
        
        textField.addActionListener(new ActionListener()  {
            public void actionPerformed(ActionEvent e){
                String[] params  = new String[] { textField.getText()};
                ResultSet customer = null;
                try {
                    customer = MySqlDB.executeQuery(nnp_common.initForm.nnp_getConnect(), Sql.selectCustomer(), params);
                    if (customer.next()) {
                        textField_1.setText(customer.getString("FullName"));
                    }
                    else {
                        JOptionPane.showMessageDialog(tabbedPane, nnp_common.initForm.nnp_showMessage("M001"));
                        textField.setText("");
                        textField_1.setText("");
                    }
                    nnp_common.initForm.nnp_getConnect().close();

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
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        
        JLabel lblThiGianCheckin = new JLabel("Thời gian checkin");
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        
        JLabel lblMSPhng = new JLabel("Mã số phòng");
        
        JLabel lblLoiKhcHng = new JLabel("Loại khác hàng");
        
        JComboBox comboBox_1 = new JComboBox();
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        
        JButton btnNewButton = new JButton("");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrmRoomAvailiable FrmRoomAvailiable = new FrmRoomAvailiable();
                FrmRoomAvailiable.setVisible(true);
              
                
                FrmRoomAvailiable.addWindowListener(new WindowAdapter() {
                    public void windowClosing(FrmRoomAvailiable e) {
                        String [] code = FrmRoomAvailiable.paramsCode;
                        textField_3.setText(code[0]);
                    }
                });
                
            }
        });
        btnNewButton.setIcon(new ImageIcon(FrmRentalManager.class.getResource("/org/jdesktop/swingx/plaf/windows/resources/search.gif")));
        GroupLayout gl_pnCheckin = new GroupLayout(pnCheckin);
        gl_pnCheckin.setHorizontalGroup(
            gl_pnCheckin.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_pnCheckin.createSequentialGroup()
                    .addGap(26)
                    .addGroup(gl_pnCheckin.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pnCheckin.createSequentialGroup()
                            .addComponent(lblMSKhc, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                            .addGap(5)
                            .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pnCheckin.createSequentialGroup()
                            .addComponent(lblTnKh, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                            .addGap(5)
                            .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pnCheckin.createSequentialGroup()
                            .addComponent(lblLoiKhcHng, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                            .addGap(5)
                            .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pnCheckin.createSequentialGroup()
                            .addComponent(lblThiGianCheckin, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                            .addGap(5)
                            .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_pnCheckin.createSequentialGroup()
                            .addComponent(lblMSPhng, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(492, Short.MAX_VALUE))
        );
        gl_pnCheckin.setVerticalGroup(
            gl_pnCheckin.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_pnCheckin.createSequentialGroup()
                    .addGap(21)
                    .addGroup(gl_pnCheckin.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pnCheckin.createSequentialGroup()
                            .addGap(3)
                            .addComponent(lblMSKhc))
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(6)
                    .addGroup(gl_pnCheckin.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pnCheckin.createSequentialGroup()
                            .addGap(3)
                            .addComponent(lblTnKh))
                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_pnCheckin.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pnCheckin.createSequentialGroup()
                            .addGap(4)
                            .addComponent(lblLoiKhcHng))
                        .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(10)
                    .addGroup(gl_pnCheckin.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pnCheckin.createSequentialGroup()
                            .addGap(3)
                            .addComponent(lblThiGianCheckin))
                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(10)
                    .addGroup(gl_pnCheckin.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_pnCheckin.createSequentialGroup()
                            .addGap(3)
                            .addGroup(gl_pnCheckin.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblMSPhng)
                                .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btnNewButton))
                    .addGap(362))
        );
        pnCheckin.setLayout(gl_pnCheckin);
        
        JPanel pnCheckout = new JPanel();
        tabbedPane.addTab("Checkout", null, pnCheckout, null);
        
        JPanel pnService = new JPanel();
        tabbedPane.addTab("Thêm dịch vụ", null, pnService, null);
        contentPane.setLayout(gl_contentPane);
    }
}
