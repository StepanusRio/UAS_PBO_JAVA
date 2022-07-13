package com.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.conf.*;

public class vPegawai extends JFrame implements ActionListener {

    private static final JLabel lNip = new JLabel("N I P Pegawai"),
            lNama = new JLabel("Nama"),
            lBagian = new JLabel("Bagian"),
            lJenisKel = new JLabel("Jenis Kelamin"),
            lPassword = new JLabel("Password"),
            lCreate = new JLabel("Stepanus Rio Defa Ardiantoro");
    private static final JTextField fNip = new JTextField(20),
            fNama = new JTextField(20),
            fBagian = new JTextField(20),
            fJenisKel = new JTextField(20),
            fPassword = new JTextField(20);
    private static final JButton btnAdd = new JButton("Tambah"),
            btnCari = new JButton("Cari"),
            btnKoreksi = new JButton("Koreksi"),
            btnHapus = new JButton("Hapus"),
            btnSelesai = new JButton("Selesai");

    String[] header = { "NIP", "Nama", "Bagian", "Jenis Kelamin", "Password" };
    private static DefaultTableModel tabModel;
    JTable tabel = new JTable();
    JScrollPane scrollPane = new JScrollPane();

    public vPegawai() {
        setPreferredSize(new Dimension(670, 510));
        setTitle("Data Pegawai");

        JDesktopPane pPgw = new JDesktopPane();
        lNip.setBounds(20, 25, 100, 25);
        lNama.setBounds(20, 55, 100, 25);
        lBagian.setBounds(20, 90, 100, 25);
        lJenisKel.setBounds(20, 125, 100, 25);
        lPassword.setBounds(20, 160, 100, 25);

        lCreate.setBounds(20, 450, 500, 25);
        lCreate.setText("created by : Stepanus Rio Defa Ardiantoro || A12.2020.06337");

        fNip.setBounds(115, 20, 100, 25);
        fNama.setBounds(115, 55, 205, 25);
        fBagian.setBounds(115, 90, 92, 25);
        fJenisKel.setBounds(115, 125, 92, 25);
        fPassword.setBounds(115, 160, 92, 25);

        fNip.setToolTipText("Isi Nip dengan angka");

        btnAdd.setBounds(20, 380, 85, 25);
        btnAdd.setText("Tambah");
        btnAdd.setBackground(Color.green);
        btnAdd.setForeground(Color.getHSBColor(250, 0, 255));

        btnCari.setBounds(120, 380, 85, 25);
        btnCari.setText("Cari");
        btnKoreksi.setBounds(220, 380, 85, 25);
        // btnKoreksi.setLabel("Koreksi");
        btnHapus.setBounds(320, 380, 85, 25);
        // btnHapus.setLabel("Hapus");
        btnSelesai.setBounds(435, 380, 85, 25);
        // btnSelesai.setLabel("Selesai");
        btnSelesai.setToolTipText("Mengakhiri Program");

        tabModel = new DefaultTableModel(null, header);
        tabel.setModel(tabModel);
        tabel.setBackground(Color.orange);
        scrollPane.getViewport().add(tabel);
        tabel.setEnabled(true);
        scrollPane.setBounds(20, 200, 500, 170);

        btnAdd.setEnabled(true);
        btnSelesai.setEnabled(true);

        btnAdd.addActionListener(this);
        btnCari.addActionListener(this);
        btnKoreksi.addActionListener(this);
        btnHapus.addActionListener(this);
        btnSelesai.addActionListener(this);

        pPgw.add(scrollPane);
        pPgw.add(lNip);
        pPgw.add(lNama);
        pPgw.add(lBagian);
        pPgw.add(lJenisKel);
        pPgw.add(lPassword);
        pPgw.add(fNip);
        pPgw.add(fNama);
        pPgw.add(fBagian);
        pPgw.add(fJenisKel);
        pPgw.add(fPassword);
        pPgw.add(btnAdd);
        pPgw.add(btnCari);
        pPgw.add(btnKoreksi);
        pPgw.add(btnHapus);
        pPgw.add(btnSelesai);
        pPgw.add(lCreate);

        getContentPane().add(pPgw).setBackground(Color.getHSBColor(100, 150, 75));
        pack();
        TampilTabel();
        setLocationRelativeTo(null);
        settombol(1, 0, 0, 0, 1);
        setisian(0, 0, 0, 0, 0);

        fNip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fNama.requestFocus();
            }
        });
        fNama.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fBagian.requestFocus();
            }
        });
        fBagian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fJenisKel.requestFocus();
            }
        });
        fJenisKel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fPassword.requestFocus();
            }
        });
        fPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAdd.requestFocus();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnAdd) {
            setisian(1, 1, 1, 1, 1);
            settombol(0, 1, 1, 1, 1);
            if (btnAdd.getText() == "Simpan") {
                String xnip = fNip.getText();
                if (xnip.length() != 0) {
                    Add();
                    Kosong();
                    TampilTabel();
                } else
                    JOptionPane.showMessageDialog(this, "Nip Kosong !");
                btnAdd.setText("Add");
                Kosong();
                TampilTabel();
                settombol(1, 1, 0, 0, 1);
            } else {
                Kosong();
                settombol(1, 0, 0, 0, 1);
                btnAdd.setText("Simpan");
                btnAdd.setForeground(Color.getHSBColor(100, 10, 0));
                btnAdd.setBackground(Color.getHSBColor(200, 100, 100));
            }
        }
        if (source == btnCari) {
            String xcari = JOptionPane.showInputDialog(this, "Masukkan Nip yang dicari : ");
            if (xcari != null) {
                fNip.setText(xcari);
                int xx = Cari();
                if (xx == 1)
                    settombol(1, 1, 1, 1, 1);
            } else
                JOptionPane.showMessageDialog(this, "Nip Kosong !");

        }
        if (source == btnKoreksi) {
            if (btnKoreksi.getText() == "Simpan") {
                if (fNip.getText() != "")
                    Koreksi();
                btnKoreksi.setText("Koreksi");
                TampilTabel();
                Koreksi();
                settombol(1, 1, 0, 0, 1);
            } else {
                btnKoreksi.setText("Simpan");
                settombol(0, 0, 1, 0, 1);
                fNama.requestFocus();
            }
        }
        if (source == btnHapus) {
            int stt = JOptionPane.showConfirmDialog(this, "Yakin dihapus ?");
            System.out.print(stt);

            if (stt == 0) {
                Hapus();
                TampilTabel();
                Kosong();
            }

        }
        if (source == btnSelesai) {
            this.dispose();
        }
    }

    private void Hapus() {
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String strsql = "delete from pegawai where nip='" + fNip.getText() + "' ";
            int stsproses = KoneksiDBMS.stat.executeUpdate(strsql);
            if (stsproses == 1)
                JOptionPane.showMessageDialog(this, "Data Terhapus !");
            KoneksiDBMS.conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Penghapusan Gagal!!!");
            System.err.println("Kesalahan perintah SQL : " + e.getMessage());
        }
    }

    private void Koreksi() {
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String strsql = "update pegawai set nama='" + fNama.getText() +
                    "',bagian='" + fBagian.getText() +
                    "',jeniskelamin='" + fJenisKel.getText() +
                    "',password='" + fPassword.getText() +
                    "' where nip='" + fNip.getText() + "' ";
            KoneksiDBMS.stat.executeUpdate(strsql);
            int stsproses = KoneksiDBMS.stat.executeUpdate(strsql);
            if (stsproses == 1) {
                JOptionPane.showMessageDialog(this, "Sukses DiEdit!");
                KoneksiDBMS.conn.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koreksi Gagal !");
            System.err.println("Kesalahan perintah SQL : " + e.getMessage());
        }
    }

    private int Cari() {
        int x = 0;
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String strsql = "SELECT * FROM pegawai WHERE nip ='" + fNip.getText() + "'";
            KoneksiDBMS.rs = KoneksiDBMS.stat.executeQuery(strsql);
            if (KoneksiDBMS.rs.next()) {
                // Jika Nip Ditemukan Di Tabel

                fNip.setText(KoneksiDBMS.rs.getString("nip"));
                fNama.setText(KoneksiDBMS.rs.getString("nama"));
                fBagian.setText(KoneksiDBMS.rs.getString("bagian"));
                fJenisKel.setText(KoneksiDBMS.rs.getString("jeniskelamin"));
                fPassword.setText(KoneksiDBMS.rs.getString("password"));

                x = 1;
            } else {
                // Jika Nip tidak ditemukan
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan !");
                fNip.requestFocus();
            }
            // Close Connection
            KoneksiDBMS.rs.close();
            KoneksiDBMS.conn.close();
        } catch (Exception e) {
        }
        return x;
    }

    private void TampilTabel() {
        try {
            BersihTabel();
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String strsql = "select * from pegawai";
            KoneksiDBMS.rs = KoneksiDBMS.stat.executeQuery(strsql);
            // ResultSetMetaData meta = KoneksiDBMS.rs.getMetaData();
            KoneksiDBMS.rs.beforeFirst();
            while (KoneksiDBMS.rs.next()) {
                String Nip = KoneksiDBMS.rs.getString("nip");
                String Nama = KoneksiDBMS.rs.getString("nama");
                String Bagian = KoneksiDBMS.rs.getString("bagian");
                String JenisKel = KoneksiDBMS.rs.getString("jeniskelamin");
                String Password = KoneksiDBMS.rs.getString("password");
                String[] data = { Nip, Nama, Bagian, JenisKel, Password };
                tabModel.addRow(data);
            }
            // Close Connection
            KoneksiDBMS.stat.close();
            KoneksiDBMS.rs.close();
            KoneksiDBMS.conn.close();
        } catch (Exception e) {
            System.err.println("Kesalahan perintah SQL : " + e.getMessage());
        }
    }

    private void Add() {
        try {
            KoneksiDBMS.conn = KoneksiDBMS.connection();
            KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();
            String strsqlcr = "SELECT * FROM pegawai WHERE nip ='" + fNip.getText() + "'";
            KoneksiDBMS.rs = KoneksiDBMS.stat.executeQuery(strsqlcr);
            if (KoneksiDBMS.rs.next()) {
                JOptionPane.showMessageDialog(this, "Nip Sudah Ada !");
                return;
            }
            String strsql = "insert into pegawai(nip,nama,bagian,jeniskelamin,password) values " +
                    " ('" + fNip.getText() + "','" + fNama.getText() + "' " +
                    ",'" + fBagian.getText() + "','" + fJenisKel.getText() + "','" + fPassword.getText() + "')";
            int stsprosses = KoneksiDBMS.stat.executeUpdate(strsql);
            if (stsprosses == 1)
                JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan !");
            // Close Connection
            KoneksiDBMS.conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data Gagal Disimpan !");
            System.err.println("Error : " + e.getMessage());
        }
    }

    private void setisian(int a, int b, int c, int d, int e) {
        fNip.setEnabled(a >= 1 ? true : false);
        fNama.setEnabled(b >= 1 ? true : false);
        fBagian.setEnabled(c >= 1 ? true : false);
        fJenisKel.setEnabled(d >= 1 ? true : false);
        fPassword.setEnabled(e >= 1 ? true : false);
    }

    private void settombol(int a, int b, int c, int d, int e) {
        btnAdd.setEnabled(a >= 1 ? true : false);
        btnCari.setEnabled(b >= 1 ? true : false);
        btnKoreksi.setEnabled(c >= 1 ? true : false);
        btnHapus.setEnabled(d >= 1 ? true : false);
        btnSelesai.setEnabled(e >= 1 ? true : false);
    }

    private void Kosong() {
        fNip.setText("");
        fNama.setText("");
        fBagian.setText("");
        fJenisKel.setText("");
        fPassword.setText("");
        fNip.requestFocus();
    }

    private void BersihTabel() {
        int brs = tabModel.getRowCount();
        for (int i = 0; i < brs; i++) {
            tabModel.removeRow(0);
        }
    }
}
