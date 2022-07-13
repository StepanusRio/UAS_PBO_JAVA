package com.view;

import com.conf.KoneksiDBMS;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JPanel;

/**
 * CetakBarangSemua
 */
public class CetakBarangSemua extends JPanel{

    public CetakBarangSemua(){
        try {
            PrinterJob pjob = PrinterJob.getPrinterJob();
            pjob.setJobName("Laporan Barang");
            pjob.setCopies(1);
            pjob.setPrintable(new Printable() {
                public int print(Graphics g1, PageFormat pf, int pageNum) {
                    if (pageNum > 0) {
                        return Printable.NO_SUCH_PAGE;
                    }
                    g1.drawString("created by Stepanus Rio Defa Ardiantoro | A12.2020.06337", 10, 10);
                    try {
                        // Koneksi ke database
                        KoneksiDBMS.conn = KoneksiDBMS.connection();
                        KoneksiDBMS.stat = KoneksiDBMS.conn.createStatement();

                        String sql = "SELECT * FROM barang";
                        KoneksiDBMS.rs = KoneksiDBMS.stat.executeQuery(sql);

                        int i1, x1, y1;

                        // header
                        g1.drawString("Laporan Barang", 50, 100);
                        g1.drawLine(50, 140, 500, 140);
                        g1.drawString("No.", 50, 155);
                        g1.drawString("Kode", 80, 155);
                        g1.drawString("Nama", 150, 155);
                        g1.drawString("Harga", 250, 155);
                        g1.drawString("Stock", 300, 155);
                        g1.drawString("Kategori", 400, 155);
                        g1.drawLine(50, 165, 500, 165);
                        x1 = 50;
                        y1 = 190;
                        i1 = 1;

                        // Cetak Record Barang
                        while (KoneksiDBMS.rs.next()) {
                            g1.drawString(String.valueOf(i1), x1, y1);
                            g1.drawString(KoneksiDBMS.rs.getString("kdbrg"), x1 + 30, y1);
                            g1.drawString(KoneksiDBMS.rs.getString("nmbrg"), x1 + 100, y1);
                            g1.drawString(KoneksiDBMS.rs.getString("harga"), x1 + 200, y1);
                            g1.drawString(KoneksiDBMS.rs.getString("stok"), x1 + 250, y1);
                            g1.drawString(KoneksiDBMS.rs.getString("kategori"), x1 + 350, y1);
                            y1 += 20;
                            i1++;
                        }
                        g1.drawLine(x1, y1, 500, y1);
                        KoneksiDBMS.rs.close();
                        KoneksiDBMS.conn.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    return Printable.PAGE_EXISTS;
                }
            });
            if (pjob.printDialog() == false)
                return;
            pjob.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
    // public static void main(String[] args) {
    // }
}