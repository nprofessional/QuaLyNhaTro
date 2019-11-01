package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FrmDashBoard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDashBoard frame = new FrmDashBoard();
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
	public FrmDashBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(new MaterialLookAndFeel());
			if (UIManager.getLookAndFeel() instanceof MaterialLookAndFeel) {
				MaterialLookAndFeel.changeTheme(new MaterialLiteTheme());
			}
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setTitle("Quản lý khách sạn | Bảng chức năng");
		setBounds(0, 0, 380, 450);
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

		JButton btnQunLCi = new JButton("Qu\u1EA3n l\u00FD c\u00E0i \u0111\u1EB7t th\u00F4ng tin kh\u00E1ch h\u00E0ng");
		btnQunLCi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				FrmCustomerController frmCustomerController = new FrmCustomerController();
				frmCustomerController.setVisible(true);
			}
		});
		panel.add(btnQunLCi, "2, 2");

		JButton btnQunLCi_1 = new JButton("Qu\u1EA3n l\u00FD c\u00E0i \u0111\u1EB7t th\u00F4ng nh\u00E2n vi\u00EAn");
		btnQunLCi_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				FrmEmployeeController frmEmployeeController = new FrmEmployeeController();
				frmEmployeeController.setVisible(true);
			}
		});
		panel.add(btnQunLCi_1, "2, 4");

		JButton btnQunLCho = new JButton("Qu\u1EA3n l\u00FD cho thu\u00EA ph\u00F2ng");
		btnQunLCho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				try {
					FrmRentalManager frmRentalManager;
					frmRentalManager = new FrmRentalManager();
					frmRentalManager.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		panel.add(btnQunLCho, "2, 6");

		JButton btnQunLCi_2 = new JButton("Qu\u1EA3n l\u00FD c\u00E0i \u0111\u1EB7t d\u1ECBch v\u1EE5");
		btnQunLCi_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				FrmServiceController frmServiceController = new FrmServiceController();
				frmServiceController.setVisible(true);
			}
		});
		panel.add(btnQunLCi_2, "2, 8");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(panel,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(116, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane
						.createSequentialGroup().addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(135, Short.MAX_VALUE)));

		JButton btnQunLPhng = new JButton("Qu\u1EA3n l\u00FD ph\u00F2ng");
		btnQunLPhng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				FrmRoomController frmRoomController = new FrmRoomController();
				frmRoomController.setVisible(true);
			}
		});
		panel.add(btnQunLPhng, "2, 10");

		JButton btnQunLCi_3 = new JButton("Qu\u1EA3n l\u00FD c\u00E0i \u0111\u1EB7t gi\u00E1 ph\u00F2ng");
		btnQunLCi_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				FrmRoomPriceController frmRoomPriceController = new FrmRoomPriceController();
				frmRoomPriceController.setVisible(true);
			}
		});
		panel.add(btnQunLCi_3, "2, 12");

		JButton btnQunLCi_5 = new JButton("Qu\u1EA3n l\u00FD c\u00E0i \u0111\u1EB7t tr\u1EA1ng th\u00E1i ph\u00F2ng");
		btnQunLCi_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				FrmRoomStatusController frmRoomStatusController = new FrmRoomStatusController();
				frmRoomStatusController.setVisible(true);
			}
		});
		panel.add(btnQunLCi_5, "2, 14");

		JButton btnQunLCi_4 = new JButton("Qu\u1EA3n l\u00FD c\u00E0i \u0111\u1EB7t lo\u1EA1i ph\u00F2ng");
		btnQunLCi_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				FrmRoomTypeController frmRoomTypeController = new FrmRoomTypeController();
				frmRoomTypeController.setVisible(true);
			}
		});
		panel.add(btnQunLCi_4, "2, 16");
		contentPane.setLayout(gl_contentPane);
	}
}
