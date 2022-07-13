package com.view;
import com.conf.KoneksiDBMS;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class vlogin extends JFrame implements ActionListener, FocusListener{
    private JLabel lNip = new JLabel("N I P       "),
            lNama = new JLabel("Nama        "),
            lPassword = new JLabel("Password    ");
            // lcreate = new JLabel("Stepanus Rio Defa Ardiantoro");
    private JTextField fNip = new JTextField(),
            fNama = new JTextField();
    private TextField fPassword = new TextField();
    private JButton btnOk = new JButton("OK"),
            btnBatal = new JButton("Batal");

    public vlogin() {
		setPreferredSize(new Dimension(350,230));
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JDesktopPane dLogin = new JDesktopPane();
				
		/* Mengatur letak objek Label Di container*/
		lNip.setBounds (15, 20, 100, 25);
	    lNama.setBounds (15, 55, 100, 25);
		lPassword.setBounds (15, 90, 100, 25);
        
		/* Mengatur letak objek Text Di Container */
		fNip.setBounds (110, 20, 100, 25);
		fNama.setBounds (110, 55, 205, 25);
		fPassword.setBounds (110, 90, 92, 25);
		fPassword.setEchoChar('ò');
		
		fNip.setToolTipText("Isi Nip dengan Angka !");

		/* Mengatur letak objek Button di Container */
		btnOk.setBounds (130, 140, 85, 25);	
		btnBatal.setBounds (230, 140, 85, 25);
		btnBatal.setToolTipText("Mengakhiri Program");
		// Mengatur objek untuk dapat berinteraksi
		fNip.addFocusListener (this);
		btnOk.addActionListener (this);
		btnBatal.addActionListener (this);
	
		// Meletakkan seluruh kontrol pada objek panel */
		dLogin.add (lNip);	
		dLogin.add (fNip);
		dLogin.add (lNama);	
		dLogin.add (fNama);
		dLogin.add (lPassword);		
		dLogin.add (fPassword);
		dLogin.add (btnOk);					
		dLogin.add (btnBatal);
		
		/* Menambahkan objek panel (pPgr) ke container frame */
		getContentPane().add (dLogin).setBackground(Color.getHSBColor(180,100,200));
		
		/* Menampilkan frame ke layar monitor */
		pack();
		setLocationRelativeTo(null);
		// Kosong();
	}





    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == fNip) {
            fNip.setText("");
        } else if (e.getSource() == fNama) {
            fNama.setText("");
        } else if (e.getSource() == fPassword) {
            fPassword.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(fNip.getText().equals("")){

        }else{
            int xcari = Cari();
            if (xcari == 1) {
                fPassword.requestFocus();
            } else {
                btnBatal.requestFocus();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj==btnOk){
            int xcari =  CariPassword();
            if(xcari==1){
                this.dispose();
                vMenu menu = new vMenu();
                menu.setVisible(true);
            }
        }
        if(obj == btnBatal){
            System.exit(0);
        }
    }
    // Mencari Pegawai
    int Cari(){
        int xcari = 0;
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String sql = "SELECT * FROM pegawai WHERE nip='"+fNip.getText()+"'";
            KoneksiDBMS.rs = KoneksiDBMS.stat.executeQuery(sql);
            if (KoneksiDBMS.rs.next()){
                fNama.setText(KoneksiDBMS.rs.getString("nama"));
                xcari = 1;
            }
            else{
                fNip.setText("");
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan !");
                fNip.requestFocus();
            }
            KoneksiDBMS.rs.close();
            KoneksiDBMS.conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data kosong !");
        }
        return xcari;
    }
    // Fungsi Cari Password
    int CariPassword(){
        int xcari = 0;
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String sql = "SELECT * FROM pegawai WHERE nip='"+fNip.getText()+"' AND password='"+fPassword.getText()+"'";
            KoneksiDBMS.rs = KoneksiDBMS.stat.executeQuery(sql);
            if(KoneksiDBMS.rs.next()){
                fNama.setText(KoneksiDBMS.rs.getString("nama"));
                xcari = 1;
            }
            else{
                fPassword.setText("");
                fPassword.requestFocus();
                JOptionPane.showMessageDialog(this, "Password Salah !");
            }
            KoneksiDBMS.rs.close();
            KoneksiDBMS.conn.close();
        } catch (Exception e) {
        }
        return xcari;
    }
    void Kosong(){
        fNip.setText("");
        fNama.setText("");
        fPassword.setText("");
        fNip.requestFocus();
    }
}
