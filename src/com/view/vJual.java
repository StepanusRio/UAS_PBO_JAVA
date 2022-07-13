package com.view;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.conf.*;

public class vJual extends JFrame implements ActionListener, FocusListener{

    // Label
    private JLabel  lNoJual = new JLabel("No. Transaksi"),
                    lKode = new JLabel("Kode"),
                    lNama = new JLabel("Nama"),
                    lHarga = new JLabel("Harga"),
                    lJumlah = new JLabel("Jumlah"),
                    lBayar = new JLabel("Bayar"),
                    lKembali = new JLabel("Kembali"),
                    lTotal = new JLabel("Total"),
                    lToko1 = new JLabel("PT. Maju Sejahtera"),
                    lToko2 = new JLabel("Jl. Ahmad Dahlan 123 SMG Telp. 024 8127312");
    // TextField
    private JTextField  fNoJual = new JTextField(),
                        fKode = new JTextField(),
                        fNama = new JTextField(),
                        fHarga = new JTextField(),
                        fJumlah = new JTextField(),
                        fBayar = new JTextField(),
                        fKembali = new JTextField(),
                        fTotal = new JTextField();
    // Button
    private JButton     btnJual = new JButton("Jual"),
                        btnDelete = new JButton("Delete"),
                        btnCari = new JButton("Cari"),
                        btnSelesai = new JButton("Selesai");
    // Table
    String[] header = {"Kode", "Nama", "Harga", "Jumlah", "Total"};
    private static DefaultTableModel tabModel;
    JTable tabel = new JTable();
    JScrollPane scroll = new JScrollPane();
    float total = 0, bayar = 0, kembali = 0;
    int xjumlah = 0;

    public vJual(){
        setPreferredSize(new Dimension(580,600));
        setTitle("Transaksi Penjualan");
        
        JDesktopPane pTrx = new JDesktopPane();
        lToko1.setBounds(15, 28, 450, 40);
        lToko1.setFont(new Font("Dialog", 1, 36));
        lToko2.setBounds(15, 58, 450, 40);
        lToko2.setFont(new Font("Dialog", 1, 18));
        lToko1.setForeground(Color.BLUE);

        lNoJual.setBounds(15, 100, 120, 20);
        lKode.setBounds(15, 400, 60, 20);
        lHarga.setBounds(15, 425, 60, 20);
        lJumlah.setBounds(15, 450, 60, 20);
        lBayar.setBounds(350, 425, 60, 20);
        lKembali.setBounds(350, 450, 60, 20);

        /* Mengatur letak objek Text Di Container */
        fNoJual.setBounds(15, 125, 100, 20);
        fKode.setBounds(75, 400, 100, 20);
        fNama.setBounds(180, 400, 205, 20);
        fTotal.setBounds(400, 400, 150, 20);
        fHarga.setBounds(75, 425, 92, 20);
        fBayar.setBounds(400, 425, 150, 20);
        fKembali.setBounds(400, 450, 150, 20);
        fJumlah.setBounds(75, 450, 100, 20);

        /* Mengatur letak objek Button di Container */
        btnJual.setBounds(165, 500, 85, 25);
        btnDelete.setBounds(265, 500, 85, 25);
        btnCari.setBounds(365, 500, 85, 25);
        btnSelesai.setBounds(465, 500, 85, 25);

        tabModel = new DefaultTableModel(null,header);
        tabel.setModel(tabModel);
        scroll.getViewport().add(tabel);
        tabel.setEnabled(true);
        scroll.setBounds(15, 160, 540, 220);

        // Mengatur penataan text di TEXT
        fHarga.setHorizontalAlignment(JTextField.RIGHT);
        fJumlah.setHorizontalAlignment(JTextField.RIGHT);
        fBayar.setHorizontalAlignment(JTextField.RIGHT);
        fKembali.setHorizontalAlignment(JTextField.RIGHT);
        fTotal.setHorizontalAlignment(JTextField.RIGHT);

        /* Objek Button di Non Aktifkan dan di aktifkan */
        btnJual.setEnabled(true);
        btnDelete.setEnabled(false);
        btnSelesai.setEnabled(true);

        /* Mengatur objek untuk dapat berinteraksi */
        fKode.addFocusListener(this);
        fJumlah.addFocusListener(this);
        fKembali.addFocusListener(this);
        btnJual.addActionListener(this);
        btnDelete.addActionListener(this);
        btnCari.addActionListener(this);
        btnSelesai.addActionListener(this);

        // tabel.TampungMouseListener(this);

        pTrx.add(lNoJual);
        pTrx.add(fNoJual);
        pTrx.add(lKode);
        pTrx.add(fKode);
        pTrx.add(lNama);
        pTrx.add(fNama);
        pTrx.add(lHarga);
        pTrx.add(fHarga);
        pTrx.add(lJumlah);
        pTrx.add(fJumlah);
        pTrx.add(lBayar);
        pTrx.add(fBayar);
        pTrx.add(lKembali);
        pTrx.add(fKembali);
        pTrx.add(lTotal);
        pTrx.add(fTotal);
        pTrx.add(btnJual);
        pTrx.add(btnDelete);
        pTrx.add(btnCari);
        pTrx.add(btnSelesai);
        pTrx.add(scroll);
        pTrx.add(lToko1);
        pTrx.add(lToko2);
        
        getContentPane().add(pTrx);
        TampilTabel();
        pack();
        setLocationRelativeTo(null);
    }
// Memeriksa krusor saat meninggalkan object txtKode_Barang
    @Override
    public void focusGained(FocusEvent e) {
        if(fBayar.getText().equals("")){

        }else
            Bayar();
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(fKode.getText().equals("")){

        }else{
            Cari();
            fJumlah.requestFocus();
        }
        if(fJumlah.getText().equals("")){

        }else{
            int stat = CekJumlah();
                if(stat == 1){
                    fJumlah.requestFocus();
                }else{
                    TampilTabel();
                    Kosong();
                }
        }
    }   

// Fungsi jika user melakukan action penekanan tombol Button
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == btnJual){
            SimpanJual();
            BersihTabel();
            total = 0;
            fTotal.setText("");
            Kosong();
            CariNoJual();
        }
        if(obj == btnDelete){
            BersihTabel();
            Kosong();
        }
        if(obj == btnCari){
            Cari();
        }
        if(obj == btnSelesai){
            this.dispose();
        }
    }
    // Fungsi Untuk Mencari Kode_Barang ke Tabel Barang
    void CariNoJual(){
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String sql = "SELECT * FROM jual";
            KoneksiDBMS.rs = KoneksiDBMS.stat.executeQuery(sql);
            if(KoneksiDBMS.rs.next()){
                KoneksiDBMS.rs.last();
                // Jika Kode_Barang di temukan ditemukan di Tabel Barang
                int xno = Integer.parseInt(KoneksiDBMS.rs.getString("notransaksi"));
                fNoJual.setText(String.valueOf(xno + 1));
                btnJual.setEnabled(false);
                fKode.requestFocus();
            }
            else{
                // Jika Kode Barang Tidak ketemu di Tabel Barang ..
                fNoJual.setText("21001");
                fKode.setText("");
                fKode.requestFocus();
            }
            KoneksiDBMS.rs.close();
            KoneksiDBMS.conn.close();
        } catch (Exception e) {
        }
    }

    // Fungsi Untuk Cari 
    void Cari(){
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String sql ="SELECT * FROM jual WHERE kode ='" + fKode.getText() + "'";
            KoneksiDBMS.rs = KoneksiDBMS.stat.executeQuery(sql);
            if (KoneksiDBMS.rs.next()){
                // Jika Kode Barang ditemukan di Tabel Barang
                fKode.setText(KoneksiDBMS.rs.getString("kode"));
                fNama.setText(KoneksiDBMS.rs.getString("nama"));
                fHarga.setText(KoneksiDBMS.rs.getString("harga"));
                xjumlah = Integer.parseInt(KoneksiDBMS.rs.getString("jumlah"));
                btnJual.setEnabled(false);
                btnDelete.setEnabled(true);
                fNama.requestFocus();
            }else{
                // Jika kode barang tidak ditemukan di Tabel Barang
                fKode.setText("");
                JOptionPane.showMessageDialog(this, "Kode Barang tidak di temukan");
                fKode.requestFocus();
            }
            KoneksiDBMS.rs.close();
            KoneksiDBMS.conn.close();
        } catch (Exception e) {
        }
    }
    // Fungsi Cek Jumlah
    int CekJumlah(){
        int x = 0;
        try {
            if(xjumlah < Integer.parseInt(fJumlah.getText())){
                fJumlah.setText("");
                JOptionPane.showMessageDialog(this,"Jumlah Barang : " +xjumlah);
                x = 1;
            }
        } catch (Exception e) {
        }
        return x;
    }
    // Simpan Data Jual
    void SimpanJual(){
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            int brs = tabModel.getRowCount();
            for(int i=0;i<brs;i++){
                int vnojual = Integer.parseInt(fNoJual.getText());
                String vkode = String.valueOf(tabel.getValueAt(i, 0));
                String vharga = String.valueOf(tabel.getValueAt(i, 2));
                String vjumlah = String.valueOf(tabel.getValueAt(i, 3));
                String sql = "INSERT INTO jual(notransaksi, kode, harga, jumlah) " + " values ('" + vnojual + "', '" + vkode + "', '" + vharga + "', '" + vjumlah + "')";
                int prosses = KoneksiDBMS.stat.executeUpdate(sql);
                if (prosses == 1){
                    JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
                    btnJual.setEnabled(true);
                    btnDelete.setEnabled(false);
                    Kosong();
                    CariNoJual();
                }else{
                // Update Data 
                    String sql2 = "UPDATE jual set jumlah= jumlah - '"+vjumlah+"' WHERE kode = '"+vkode+"'";
                    int prosses2 = KoneksiDBMS.stat.executeUpdate(sql2);
                    if (prosses2 == 1){
                        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
                        btnJual.setEnabled(true);
                        btnDelete.setEnabled(false);
                        Kosong();
                        CariNoJual();
                    }
                }
            }
            KoneksiDBMS.conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ada Kesalahan !!!");
        }
    }
    void Bayar(){
        try {
            fBayar.requestFocus();
            kembali = Float.parseFloat(fBayar.getText()) - Float.parseFloat(fTotal.getText());
            fKembali.setText(String.valueOf(kembali));
        } catch (Exception se) {
            System.err.println("Pesan Salah : " + se.getMessage());
        }
    }
    void TampilTabel(){
        try {
            String Kode = fKode.getText();
            String Nama = fNama.getText();
            String Harga = fHarga.getText();
            String Jumlah = fJumlah.getText();
            String Total = String.valueOf(Float.parseFloat(fHarga.getText())* Float.parseFloat(fJumlah.getText()));
            String[] data = {Kode, Nama, Harga, Jumlah, Total};
            tabModel.addRow(data);
            // Masukan Ke Total
            total = total + Float.parseFloat(Total);
            fTotal.setText(String.valueOf(total));

        } catch (Exception se) {
            System.err.println("Pesan Salah : " + se.getMessage());
        }
    }
    void BersihTabel(){
        int brs = tabModel.getRowCount();
        for (int i=0;i<brs;i++){
            tabModel.removeRow(0);
        }
    }
    // Fungsi Untuk Mengkosongkan Object Masukan
    void Kosong(){
        fKode.setText("");
        fNama.setText("");
        fHarga.setText("");
        fJumlah.setText("");
        fBayar.setText("");
        fKembali.setText("");

        btnJual.setEnabled(true);
        btnDelete.setEnabled(false);
        fKode.requestFocus();

    }
}
