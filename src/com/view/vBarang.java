package com.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.conf.KoneksiDBMS;

public class vBarang extends JFrame implements ActionListener {

    private static final JLabel lKDBarang = new JLabel("Kode Barang"),
            lNamaBarang = new JLabel("Nama Barang"),
            lHarga = new JLabel("Harga"),
            lStok = new JLabel("Stok"),
            lKategori = new JLabel("Kategori");
    private static final JTextField tfKDBarang = new JTextField(),
            tfNamaBarang = new JTextField(),
            tfHarga = new JTextField(),
            tfStok = new JTextField(),
            tfKategori = new JTextField();
    private static final JButton btnAdd = new JButton("Tambah"),
            btnCari = new JButton("Cari"),
            btnKoreksi = new JButton("Koreksi"),
            btnHapus = new JButton("Hapus"),
            btnSelesai = new JButton("Selesai");
    String[] header = { "Kode Barang", "Nama Barang", "Harga", "Stok", "Kategori" };
    private static DefaultTableModel tabModel;
    JTable tabel = new JTable();
    JScrollPane scrollPane = new JScrollPane(tabel);

    public vBarang() {
        setPreferredSize(new Dimension(670, 510));
        setTitle("Data Barang");

        JDesktopPane pBrg = new JDesktopPane();
        lKDBarang.setBounds(20, 25, 100, 25);
        lNamaBarang.setBounds(20, 55, 100, 25);
        lHarga.setBounds(20, 90, 100, 25);
        lStok.setBounds(20, 125, 100, 25);
        lKategori.setBounds(20, 160, 100, 25);

        tfKDBarang.setBounds(115, 25, 100, 25);
        tfNamaBarang.setBounds(115, 55, 205, 25);
        tfHarga.setBounds(115, 90, 100, 25);
        tfStok.setBounds(115, 125, 100, 25);
        tfKategori.setBounds(115, 160, 100, 25);

        btnAdd.setBounds(20, 380, 85, 25);
        btnAdd.setText("Tambah");
        btnAdd.setBackground(Color.GREEN);
        btnAdd.setForeground(Color.WHITE);

        btnCari.setBounds(115, 380, 85, 25);
        btnCari.setText("Cari");

        btnKoreksi.setBounds(210, 380, 85, 25);

        btnHapus.setBounds(305, 380, 85, 25);

        btnSelesai.setBounds(400, 380, 85, 25);

        tabModel = new DefaultTableModel(null, header);
        tabel.setModel(tabModel);
        tabel.setBackground(Color.WHITE);
        scrollPane.getViewport().add(tabel);
        tabel.setEnabled(true);
        scrollPane.setBounds(20, 200, 500, 170);

        btnAdd.addActionListener(this);
        btnCari.addActionListener(this);
        btnKoreksi.addActionListener(this);
        btnHapus.addActionListener(this);
        btnSelesai.addActionListener(this);

        pBrg.add(scrollPane);
        pBrg.add(lKDBarang);
        pBrg.add(lNamaBarang);
        pBrg.add(lHarga);
        pBrg.add(lStok);
        pBrg.add(lKategori);
        pBrg.add(tfKDBarang);
        pBrg.add(tfNamaBarang);
        pBrg.add(tfHarga);
        pBrg.add(tfStok);
        pBrg.add(tfKategori);
        pBrg.add(btnAdd);
        pBrg.add(btnCari);
        pBrg.add(btnKoreksi);
        pBrg.add(btnHapus);
        pBrg.add(btnSelesai);

        getContentPane().add(pBrg).setBackground(Color.lightGray);
        pack();
        tampil();
        setLocationRelativeTo(null);
        settombol(1, 0, 0, 0, 1);
        setisian(0, 0, 0, 0, 0);

        tfKDBarang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfNamaBarang.requestFocus();
            }
        });
        tfNamaBarang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfHarga.requestFocus();
            }
        });
        tfHarga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfStok.requestFocus();
            }
        });
        tfStok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfKategori.requestFocus();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btnAdd) {
            setisian(1, 1, 1, 1, 1);
            settombol(0, 1, 1, 1, 1);
            if (btnAdd.getText() == "Simpan") {
                String xKDBarang = tfKDBarang.getText();
                if (xKDBarang.length() != 0) {
                    Add();
                    Kosong();
                    tampil();
                } else {
                    JOptionPane.showMessageDialog(null, "Kode Barang Tidak Boleh Kosong");
                }
                btnAdd.setText("Add");
                Kosong();
                tampil();
                settombol(1, 1, 0, 0, 1);
            } else {
                Kosong();
                settombol(1, 0, 0, 0, 1);
                btnAdd.setText("Simpan");
                btnAdd.setBackground(Color.GREEN);
                btnAdd.setForeground(Color.darkGray);
            }
        }
        if (o == btnCari) {
            String xCari = JOptionPane.showInputDialog("Masukkan Kode Barang");
            if (xCari != null) {
                tfKDBarang.setText(xCari);
                int x = Cari();
                if (x == 1) {
                    settombol(1, 1, 1, 1, 1);
                }
            } else {
                JOptionPane.showMessageDialog(this, "kdbrg Kosong !");
            }
        }
        if (o == btnKoreksi) {
            if (btnKoreksi.getText() == "Simpan") {
                if (tfKDBarang.getText() != "") {
                    Koreksi();
                }
                btnKoreksi.setText("Koreksi");
                tampil();
                Koreksi();
                settombol(1, 1, 0, 0, 1);
            } else {
                btnKoreksi.setText("Simpan");
                settombol(0, 0, 1, 0, 1);
                tfNamaBarang.requestFocus();
            }
        }
        if (o == btnHapus) {
            int sst = JOptionPane.showConfirmDialog(null, "Yakin Hapus Data ?", "Konfirmasi",
                    JOptionPane.YES_NO_OPTION);
            System.out.println(sst);
            if (sst == 0) {
                Hapus();
                tampil();
                Kosong();
            }
        }
        if (o == btnSelesai) {
            int sst = JOptionPane.showConfirmDialog(null, "Yakin Keluar ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            System.out.println(sst);
            if (sst == 0) {
                this.dispose();
            }
        }
    }

    private void Hapus() {
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String strlsql = "delete from barang where kdbrg = '" + tfKDBarang.getText() + "'";
            int stsprs = KoneksiDBMS.stat.executeUpdate(strlsql);
            if (stsprs == 1) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            }
            KoneksiDBMS.conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
            System.err.println("Error : " + e);
        }
    }

    private void Koreksi() {
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String sqlKoreksi = "UPDATE barang SET nmbrg='" + tfNamaBarang.getText() + "', harga='"
                    + tfHarga.getText() + "', stok='" + tfStok.getText() + "', kategori='" + tfKategori.getText()
                    + "' WHERE kdbrg='" + tfKDBarang.getText() + "'";
            KoneksiDBMS.stat.executeUpdate(sqlKoreksi);
            int stsprs = KoneksiDBMS.stat.executeUpdate(sqlKoreksi);
            if (stsprs == 1) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate");
                KoneksiDBMS.conn.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diupdate");
            System.err.println("SQL SYNTAX ERROR : " + e.getMessage());
        }
    }

    private int Cari() {
        int x = 0;
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String strsql = "SELECT * FROM barang WHERE kdbrg = '" + tfKDBarang.getText() + "'";
            KoneksiDBMS.rs = KoneksiDBMS.stat.executeQuery(strsql);
            if (KoneksiDBMS.rs.next()) {
                tfKDBarang.setText(KoneksiDBMS.rs.getString("kdbrg"));
                tfNamaBarang.setText(KoneksiDBMS.rs.getString("nmbrg"));
                tfHarga.setText(KoneksiDBMS.rs.getString("harga"));
                tfStok.setText(KoneksiDBMS.rs.getString("stok"));
                tfKategori.setText(KoneksiDBMS.rs.getString("kategori"));
                x = 1;
            } else {
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan");
                // Focus ke kode barang
                tfKDBarang.requestFocus();
                x = 0;
            }
            // Clos
            KoneksiDBMS.rs.close();
            KoneksiDBMS.stat.close();
            KoneksiDBMS.conn.close();
        } catch (Exception e) {
        }
        return x;
    }

    private void Add() {
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String strsqlcr = "SELECT * FROM barang WHERE kdbrg = '" + tfKDBarang.getText() + "'";
            KoneksiDBMS.rs = KoneksiDBMS.stat.executeQuery(strsqlcr);
            if (KoneksiDBMS.rs.next()) {
                JOptionPane.showMessageDialog(null, "Kode Barang sudah ada");
                return;
            } else {
                String addsql = "INSERT INTO barang VALUES ('" + tfKDBarang.getText() + "', '" + tfNamaBarang.getText()
                        + "', '" + tfHarga.getText() + "', '" + tfStok.getText() + "', '" + tfKategori.getText() + "')";
                int stssqlproses = KoneksiDBMS.stat.executeUpdate(addsql);
                if (stssqlproses == 1) {
                    JOptionPane.showMessageDialog(null, "Data Berhasil Di simpan!");
                    // close connection
                    KoneksiDBMS.conn.close();
                    // refresh table
                    tampil();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Di simpan!");
            System.err.println("Error : " + e.getMessage());
        }
    }

    private void settombol(int a, int b, int c, int d, int e) {
        btnAdd.setEnabled(a >= 1 ? true : false);
        btnCari.setEnabled(b >= 1 ? true : false);
        btnKoreksi.setEnabled(c >= 1 ? true : false);
        btnHapus.setEnabled(d >= 1 ? true : false);
        btnSelesai.setEnabled(e >= 1 ? true : false);
    }

    private void setisian(int a, int b, int c, int d, int e) {
        tfKDBarang.setEnabled(a >= 1 ? true : false);
        tfNamaBarang.setEnabled(b >= 1 ? true : false);
        tfHarga.setEnabled(c >= 1 ? true : false);
        tfStok.setEnabled(d >= 1 ? true : false);
        tfKategori.setEnabled(e >= 1 ? true : false);
    }

    private void Kosong() {
        tfKDBarang.setText("");
        tfNamaBarang.setText("");
        tfHarga.setText("");
        tfStok.setText("");
        tfKategori.setText("");
    }

    private void BersihTabel() {
        int row = tabModel.getRowCount();
        for (int i = 0; i < row; i++) {
            tabModel.removeRow(0);
        }
    }

    private void tampil() {
        try {
            BersihTabel();
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String strsql = "SELECT * FROM barang";
            KoneksiDBMS.rs = KoneksiDBMS.stat.executeQuery(strsql);
            KoneksiDBMS.rs.beforeFirst();
            while (KoneksiDBMS.rs.next()) {
                String kdbrg = KoneksiDBMS.rs.getString("kdbrg");
                String nmbrg = KoneksiDBMS.rs.getString("nmbrg");
                String Harga = KoneksiDBMS.rs.getString("harga");
                String Stock = KoneksiDBMS.rs.getString("stok");
                String Kategori = KoneksiDBMS.rs.getString("kategori");
                String[] data = { kdbrg, nmbrg, Harga, Stock, Kategori };
                tabModel.addRow(data);
            }
            // Close Conn
            KoneksiDBMS.stat.close();
            KoneksiDBMS.rs.close();
            KoneksiDBMS.conn.close();

        } catch (Exception er) {
            System.err.println("Error : " + er);
        }
    }
}
