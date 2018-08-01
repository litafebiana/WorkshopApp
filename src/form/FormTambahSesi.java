/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import workshopapp.Koneksi;

/**
 *
 * @author Sultan
 */
public class FormTambahSesi extends javax.swing.JFrame {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Creates new form FormTambahSesi
     */
    public FormTambahSesi() {
        initComponents();
        lIdSesi.setVisible(false);
    }

    // rbSesi
    public void roleButtonSesi(String role){
        if (role.equals("simpan")){
            bSimpanSesi.setEnabled(true);
            bUbahSesi.setEnabled(false);
            bHapusSesi.setEnabled(false);
            lJudulSesi.setText("SESI BARU");
            this.setTitle("Tambah Sesi Baru");
        }else if (role.equals("ubahhapus")){
            dcTanggalMulai.setEnabled(false);
            tLamaSesi.setEnabled(false);
            cbNarasumberSesi.setEnabled(false);
            cbMateriSesi.setEnabled(false);
            bCekRuangan.setEnabled(false);
            tbCekRuangan.setEnabled(false);
            bSimpanSesi.setEnabled(false);
            bUbahSesi.setEnabled(true);
            bHapusSesi.setEnabled(true);
            lJudulSesi.setText("UBAH/HAPUS SESI");
            this.setTitle("Ubah/Hapus Sesi");
        }
    }
    
    // giSesi
    public int genIdSesi() {
        Random r = new Random(System.currentTimeMillis());
        int number = 1000;
        for(int counter=1; counter<=1;counter++){
            number = 1000+r.nextInt(6000);
        }
        return number;
    }
    
    // gtfSesi
    public void getTextFieldSesi(String id) throws ParseException{
        String SQL = "SELECT id_sesi, (SELECT nama_narasumber FROM tb_narasumber n WHERE n.id_narasumber = s.id_narasumber) AS nama_narasumber, (SELECT nama_materi FROM tb_materi m WHERE m.id_materi = s.id_materi) AS nama_materi, (SELECT nama_ruangan FROM tb_ruangan r WHERE r.id_ruangan = s.id_ruangan) AS nama_ruangan, tema, tanggal_mulai, tanggal_selesai, kuota FROM tb_sesi s WHERE s.id_sesi = '"+id+"'";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            if(rs.next()) {
                lIdSesi.setText(rs.getString(1));
                cbNarasumberSesi.setSelectedItem(rs.getString(2));
                cbMateriSesi.setSelectedItem(rs.getString(3));
                tTemaSesi.setText(rs.getString(5));
                Date tanggalMulai = sdf.parse(rs.getString(6));
                dcTanggalMulai.setDate(tanggalMulai);
                Date tanggalSelesai = sdf.parse(rs.getString(7));
                long selisih = tanggalSelesai.getTime() - tanggalMulai.getTime();
                int day = (int) ((selisih / (24 * 60 * 60 * 1000))+1);
                tLamaSesi.setText(String.valueOf(day));
                tKuotaSesi.setText(rs.getString(8));
            }
            System.out.println("getTextFieldSesi() berhasil...");
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // gcNarasumber
    public void getComboNarasumber(){
        String SQL = "SELECT nama_narasumber FROM tb_narasumber";
        ResultSet rs = Koneksi.executeQuery(SQL);
        cbNarasumberSesi.removeAllItems();
        cbNarasumberSesi.addItem("-Pilih-");
        try {
            while(rs.next()) {
                cbNarasumberSesi.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTambahSesi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // gcMateri
    public void getComboMateri(){
        String SQL = "SELECT m.nama_materi FROM tb_materi m JOIN tb_narasumber_materi nm ON nm.id_materi = m.id_materi WHERE nm.id_narasumber = (SELECT id_narasumber FROM tb_narasumber WHERE nama_narasumber = '"+cbNarasumberSesi.getSelectedItem()+"')";
        ResultSet rs = Koneksi.executeQuery(SQL);
        cbMateriSesi.removeAllItems();
        try {
            while(rs.next()) {
                cbMateriSesi.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTambahSesi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // gdRuangan
    public void getDataRuangan(){
        String kolom[] = {"ID","Nama Ruangan","Kapasitas"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM tb_ruangan";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNamaMateri = rs.getString(2);
                String kolKapasitas = rs.getString(3);
                String data[] = {kolID, kolNamaMateri, kolKapasitas};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTambahSesi.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbCekRuangan.setModel(dtm);
    }
    
    // gsSesi
    public void getSimpanSesi(){
        int baris = tbCekRuangan.getSelectedRow();
        //***************************************************
        int lama = Integer.parseInt(tLamaSesi.getText()) - 1;
        Calendar c = Calendar.getInstance();
        c.setTime(dcTanggalMulai.getDate());
        c.add(Calendar.DATE, lama);
        Date newDate = c.getTime();
        //***************************************************
        if ("".equals(tTemaSesi.getText()) || "".equals(dcTanggalMulai.getDate()) || "".equals(tLamaSesi.getText()) || "".equals(tKuotaSesi.getText())) {
            JOptionPane.showMessageDialog(this, "Harap Lengkapi Data", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "INSERT INTO tb_sesi (id_sesi, id_narasumber, id_materi, id_ruangan, tema,  tanggal_mulai, tanggal_selesai, kuota) VALUES ("
                    + "'SESI-2018"+genIdSesi()+"',"
                    + "(SELECT id_narasumber FROM tb_narasumber WHERE nama_narasumber = '"+cbNarasumberSesi.getSelectedItem()+"'),"
                    + "(SELECT id_materi FROM tb_materi WHERE nama_materi = '"+cbMateriSesi.getSelectedItem()+"'),"
                    + "'"+tbCekRuangan.getValueAt(baris, 0).toString()+"',"
                    + "'"+tTemaSesi.getText()+"',"
                    + "'"+String.valueOf(sdf.format(dcTanggalMulai.getDate()))+"',"
                    + "'"+String.valueOf(sdf.format(newDate))+"',"
                    + "'"+tKuotaSesi.getText()+"'"
                    + ");";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                FormUtama fu = new FormUtama();
                fu.getDataRuangan();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal ditambahkan", "Sukses", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    // guSesi
    public void getUbahSesi(){
        if ("".equals(tTemaSesi.getText()) || "".equals(tKuotaSesi.getText()) ) {
            JOptionPane.showMessageDialog(this, "Harap lengkapi data !", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "UPDATE tb_sesi SET "
                    + "tema = '"+tTemaSesi.getText()+"',"
                    + "kuota = '"+tKuotaSesi.getText()+"' "
                    + "WHERE id_sesi='"+lIdSesi.getText()+"'";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Sesi berhasil diubah", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("getUbahSesi() berhasil...");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sesi gagal diubah", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ghSesi
    public void getHapusSesi(){
        int reply = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data Sesi dengan Tema '"+tTemaSesi.getText()+"'", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            String SQL = "DELETE FROM tb_sesi WHERE id_sesi = '"+lIdSesi.getText()+"'";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Sesi dengan Tema '"+tTemaSesi.getText()+"' berhasil dihapus", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("getHapusSesi() berhasil...");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sesi dengan Tema '"+tTemaSesi.getText()+"' gagal dihapus", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Hmmm.!!! -___-");
        }
    }
    
    // gCekRuangan
    public void getCekRuangan(){
        int lama = Integer.parseInt(tLamaSesi.getText()) - 1;
        Calendar c = Calendar.getInstance();
        c.setTime(dcTanggalMulai.getDate());
        c.add(Calendar.DATE, lama);
        Date newDate = c.getTime();
        
        String kolom[] = {"ID","Nama Ruangan","Kapasitas"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM tb_ruangan WHERE id_ruangan NOT IN (SELECT m.id_ruangan FROM tb_ruangan m JOIN tb_sesi nm ON nm.id_ruangan = m.id_ruangan WHERE nm.tanggal_mulai BETWEEN '"+String.valueOf(sdf.format(dcTanggalMulai.getDate()))+"' AND '"+String.valueOf(sdf.format(newDate))+"' OR '"+String.valueOf(sdf.format(dcTanggalMulai.getDate()))+"' BETWEEN nm.tanggal_mulai AND nm.tanggal_selesai)";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolID = rs.getString(1);
                String kolNamaMateri = rs.getString(2);
                String kolKapasitas = rs.getString(3);
                String data[] = {kolID, kolNamaMateri, kolKapasitas};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbCekRuangan.setModel(dtm);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lJudulSesi = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tTemaSesi = new javax.swing.JTextField();
        tKuotaSesi = new javax.swing.JTextField();
        cbMateriSesi = new javax.swing.JComboBox<>();
        cbNarasumberSesi = new javax.swing.JComboBox<>();
        dcTanggalMulai = new com.toedter.calendar.JDateChooser();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCekRuangan = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        bCekRuangan = new javax.swing.JButton();
        tLamaSesi = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        bUbahSesi = new javax.swing.JButton();
        bSimpanSesi = new javax.swing.JButton();
        bHapusSesi = new javax.swing.JButton();
        lIdSesi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lJudulSesi.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lJudulSesi.setText("JUDUL");
        jPanel3.add(lJudulSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Tema");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setText("Tanggal Mulai");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel4.setText("Lama");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        jLabel6.setText("Materi");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel7.setText("Narasumber");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel9.setText("Kursi");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        jLabel10.setText("Cek Ruangan Tersedia");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));
        jPanel1.add(tTemaSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 320, -1));
        jPanel1.add(tKuotaSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 80, -1));

        jPanel1.add(cbMateriSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 120, -1));

        cbNarasumberSesi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNarasumberSesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNarasumberSesiActionPerformed(evt);
            }
        });
        jPanel1.add(cbNarasumberSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 120, -1));
        jPanel1.add(dcTanggalMulai, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 120, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 320, 10));

        jLabel11.setText("Kuota");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        tbCekRuangan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbCekRuangan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 410, 210));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 160, 10));

        bCekRuangan.setText("Cek Ruangan");
        bCekRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCekRuanganActionPerformed(evt);
            }
        });
        jPanel1.add(bCekRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 120, 20));
        jPanel1.add(tLamaSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 80, -1));

        jLabel12.setText("Hari");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, -1));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bUbahSesi.setText("Ubah");
        bUbahSesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahSesiActionPerformed(evt);
            }
        });
        jPanel2.add(bUbahSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 20));

        bSimpanSesi.setText("Simpan");
        bSimpanSesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanSesiActionPerformed(evt);
            }
        });
        jPanel2.add(bSimpanSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 120, 20));

        bHapusSesi.setText("Hapus");
        bHapusSesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusSesiActionPerformed(evt);
            }
        });
        jPanel2.add(bHapusSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 130, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 410, 40));

        lIdSesi.setText("id_sesi");
        jPanel1.add(lIdSesi, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 430, 440));

        setSize(new java.awt.Dimension(446, 549));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //getComboNarasumber();
    }//GEN-LAST:event_formWindowActivated

    private void bSimpanSesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanSesiActionPerformed
        getSimpanSesi();
    }//GEN-LAST:event_bSimpanSesiActionPerformed

    private void cbNarasumberSesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNarasumberSesiActionPerformed
        getComboMateri();
    }//GEN-LAST:event_cbNarasumberSesiActionPerformed

    private void bCekRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCekRuanganActionPerformed
        getCekRuangan();
    }//GEN-LAST:event_bCekRuanganActionPerformed

    private void bUbahSesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahSesiActionPerformed
        getUbahSesi();
    }//GEN-LAST:event_bUbahSesiActionPerformed

    private void bHapusSesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusSesiActionPerformed
        getHapusSesi();
    }//GEN-LAST:event_bHapusSesiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormTambahSesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTambahSesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTambahSesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTambahSesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTambahSesi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCekRuangan;
    private javax.swing.JButton bHapusSesi;
    private javax.swing.JButton bSimpanSesi;
    private javax.swing.JButton bUbahSesi;
    private javax.swing.JComboBox<String> cbMateriSesi;
    private javax.swing.JComboBox<String> cbNarasumberSesi;
    private com.toedter.calendar.JDateChooser dcTanggalMulai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lIdSesi;
    private javax.swing.JLabel lJudulSesi;
    private javax.swing.JTextField tKuotaSesi;
    private javax.swing.JTextField tLamaSesi;
    private javax.swing.JTextField tTemaSesi;
    private javax.swing.JTable tbCekRuangan;
    // End of variables declaration//GEN-END:variables
}
