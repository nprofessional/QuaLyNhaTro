package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;

public class FrmRentalManager extends JFrame {

    private JPanel contentPane;

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
        
        JPanel pnCustomer = new JPanel();
        tabbedPane.addTab("Thêm thông tin khách hàng", null, pnCustomer, null);
        
        JPanel pnCheckin = new JPanel();
        tabbedPane.addTab("Checkin", null, pnCheckin, null);
        
        JPanel pnCheckout = new JPanel();
        tabbedPane.addTab("Checkout", null, pnCheckout, null);
        
        JPanel pnService = new JPanel();
        tabbedPane.addTab("Thêm dịch vụ", null, pnService, null);
        contentPane.setLayout(gl_contentPane);
    }
}
