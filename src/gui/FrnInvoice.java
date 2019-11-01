package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.MySqlDB;
import database.Sql;
import nnp_common.initForm;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

public class FrnInvoice extends JFrame {

    private JPanel contentPane;
    private JTable tblInvoice;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat hour = new SimpleDateFormat("HH");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrnInvoice frame = new FrnInvoice();
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
     * @throws ParseException
     */
    public FrnInvoice() throws ClassNotFoundException, SQLException, ParseException {
        setTitle("Quản lý khách sạn | Hóa đơn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(130, Short.MAX_VALUE)));

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
        DefaultTableModel dataContract = new DefaultTableModel();
        dataContract.setColumnIdentifiers(new String[] { "Title", "Quantity", "Per Item", "Discount Percent", "Line Total" });
        tblInvoice.setModel(dataContract);
        // contractNos = new ArrayList<String>();
        String[] params = { "HDKH0012019-11-01 08:46:24" };
        ResultSet rows = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.createInvoice(), params);
        if (rows.wasNull()) {
            JOptionPane.showMessageDialog(this, initForm.nnp_showMessage("M004"));
        }
        ;
        while (rows.next()) {
            dataContract.addRow(new Object[] { rows.getString("Name"), "", "", "", "" });
            dataContract.addRow(new Object[] { rows.getString("FullName"), "", "", "", "" });
            dataContract
                    .addRow(new Object[] { "From " + sdf.parse(rows.getString("From_Date").toString()), "", "", "", "" });

            long getDiff = timeCheckOut.getTime() - sdf.parse(rows.getString("From_Date")).getTime();
            long getDaysDiff = TimeUnit.MILLISECONDS.toDays(getDiff);
            int hour = timeCheckOut.getHours();

            //Tinh thoi gian
            if (getDaysDiff == 0) {
                getDaysDiff = getDaysDiff + 1;
            } else if (getDaysDiff > 0 && 12 < hour && hour < 15) {
                getDaysDiff = (long) (getDaysDiff + 0.25);

            } else if (getDaysDiff > 0 && 15 < hour && hour < 18) {
                getDaysDiff = (long) (getDaysDiff + 0.5);

            } else if (getDaysDiff > 0 && hour > 18) {
                getDaysDiff = (long) (getDaysDiff + 1);

            }
            
            //lay gia phong
            String[] pMaPhong = { "PH001" };
            ResultSet rPrice = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.selectRoomPrice(), pMaPhong);
            Double roomRrice = null;
            Double discount = null;
            while (rPrice.next()) {
                roomRrice = rPrice.getDouble("Room_Price");
                //check thoi gian giam gia
                if (timeCheckOut.getTime() >= sdf.parse(rPrice.getString("From_Date")).getTime()
                        && timeCheckOut.getTime() <= sdf.parse(rPrice.getString("To_Date")).getTime()) {
                    discount = rPrice.getDouble("Discount_Percent");                   
                }
            }
            
            //Tinh tien phong
            int tienThuePhong = 0;
            if (discount > 0) {
                tienThuePhong = (int) Math.round(roomRrice * getDaysDiff * (1 - (discount / 100)));
            }
                else {
                    tienThuePhong = (int) Math.round((roomRrice * getDaysDiff));
            }       
            dataContract.addRow(new Object[] { "To      " + timeCheckOut.toString(), getDaysDiff, roomRrice, discount +"%", tienThuePhong + "VND"});

        }
        
        
        dataContract.addRow(new Object[] {"", "", "", "", "" });
        dataContract.addRow(new Object[] { "", "", "", "", "" });
        dataContract.addRow(new Object[] { "List of services used"});
        
        String[] pService = { "HDKH0012019-11-01 08:46:24" };
        ResultSet listService = MySqlDB.executeQuery(initForm.nnp_getConnect(), Sql.getServiceOfContract(), pService);
        while (listService.next()) {
            dataContract.addRow(new Object[] {listService.getString("Name"), (int) listService.getFloat("Quantity"), (int) listService.getFloat("Price"), "", (int) (listService.getFloat("Quantity") * listService.getFloat("Price")) +"VND"});
        }

        initForm.nnp_getConnect().close();
    }
}
