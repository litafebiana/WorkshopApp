/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import workshopapp.Koneksi;

/**
 *
 * @author Sultan
 */
public class FormTambahPeserta extends javax.swing.JFrame {

    public void roleButton(String role){
        if (role.equals("simpan")){
            bSimpanPeserta.setEnabled(true);
            bUbahPeserta.setEnabled(false);
            bHapusPeserta.setEnabled(false);
            lIdPeserta.setVisible(false);
        }else if (role.equals("ubahhapus")){
            bSimpanPeserta.setEnabled(false);
            bUbahPeserta.setEnabled(true);
            bHapusPeserta.setEnabled(true);
            lIdPeserta.setVisible(false);
        }
    }
    
    public int genID() {
        Random r = new Random(System.currentTimeMillis());
        int number = 30000;
        for(int counter=1; counter<=1;counter++){
            number = 30000+r.nextInt(60000);
        }
        return number;
    }
    
    public void getSimpanPeserta(){
        String bulanAngka = "Bulan";
        if(cbBulanLahir.getSelectedItem().equals("Januari")){
            bulanAngka = "01";
        }else if(cbBulanLahir.getSelectedItem().equals("Pebruari")){
            bulanAngka = "02";
        }else if(cbBulanLahir.getSelectedItem().equals("Maret")){
            bulanAngka = "03";
        }
        
        String jenisKelamin = "jeniskelamin";
        if (rbLakiLaki.isSelected()){
            jenisKelamin = "Laki-Laki";
        }else if (rbPerempuan.isSelected()){
            jenisKelamin = "Perempuan";
        }
        
        if ("".equals(tNamaPeserta.getText()) || "".equals(tNoTelpPeserta.getText()) || "".equals(tTempatLahir.getText()) || "".equals(tEmailPeserta.getText()) || "".equals(taAlamatPeserta.getText()) || jenisKelamin.equals("jeniskelamin") || "Tahun".equals(cbTahunLahir.getSelectedItem()) || "Bulan".equals(cbBulanLahir.getSelectedItem()) || "Tanggal".equals(cbTanggalLahir.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Harap Lengkapi Data", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "INSERT INTO peserta (id_peserta, nama_peserta, no_telp, jenis_kelamin, tempat_lahir, tanggal_lahir, email, alamat) "
                    + "VALUES('PES-2018"+genID()+"','"+tNamaPeserta.getText()+"','"+tNoTelpPeserta.getText()+"','"+jenisKelamin+"','"+tTempatLahir.getText()+"','"+cbTahunLahir.getSelectedItem()+"-"+bulanAngka+"-"+cbTanggalLahir.getSelectedItem()+"','"+tEmailPeserta.getText()+"','"+taAlamatPeserta.getText()+"')";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                FormUtama fu = new FormUtama();
                fu.getDataPeserta();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal ditambahkan", "Sukses", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public void getTextField(String id){
        String SQL = "SELECT id_peserta, nama_peserta, no_telp, jenis_kelamin, tempat_lahir, tanggal_lahir, email, alamat FROM peserta where id_peserta = '"+id+"'";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                if(rs.getString(4).equals("Perempuan")){
                    rbPerempuan.setSelected(true);
                }else{
                    rbLakiLaki.setSelected(true);
                }
                
                String tanggal = rs.getString(6).substring(8, 10);
                String bulan = rs.getString(6).substring(5, 7);
                String tahun = rs.getString(6).substring(0, 4);
                String bulanHuruf = "Bulan";
                if(bulan.equals("01")){
                    bulanHuruf = "Januari";
                }else if(bulan.equals("02")){
                    bulanHuruf = "Pebruari";
                }else if(bulan.equals("03")){
                    bulanHuruf = "Maret";
                }else{
                    bulanHuruf = "Bulan";
                }
                cbTanggalLahir.setSelectedItem(tanggal);
                cbBulanLahir.setSelectedItem(bulanHuruf);
                cbTahunLahir.setSelectedItem(tahun);
                
                lIdPeserta.setText(rs.getString(1));
                tNamaPeserta.setText(rs.getString(2));
                tNoTelpPeserta.setText(rs.getString(3));
                tTempatLahir.setText(rs.getString(5));
                tEmailPeserta.setText(rs.getString(7));
                taAlamatPeserta.setText(rs.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getUbahPeserta(){
        String bulanAngka = "Bulan";
        if(cbBulanLahir.getSelectedItem().equals("Januari")){
            bulanAngka = "01";
        }else if(cbBulanLahir.getSelectedItem().equals("Pebruari")){
            bulanAngka = "02";
        }else if(cbBulanLahir.getSelectedItem().equals("Maret")){
            bulanAngka = "03";
        }
        
        String jenisKelamin = "";
        if (rbLakiLaki.isSelected()){
            jenisKelamin = "Laki-Laki";
        }else if (rbPerempuan.isSelected()){
            jenisKelamin = "Perempuan";
        }
        
        if ("".equals(tNamaPeserta.getText()) || "".equals(tNoTelpPeserta.getText()) || "".equals(tTempatLahir.getText()) || "".equals(tEmailPeserta.getText()) || "".equals(taAlamatPeserta.getText()) || jenisKelamin.equals("jeniskelamin") || "Tahun".equals(cbTahunLahir.getSelectedItem()) || "Bulan".equals(cbBulanLahir.getSelectedItem()) || "Tanggal".equals(cbTanggalLahir.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Harap Lengkapi Data", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "UPDATE peserta SET "
                    + "nama_peserta = '"+tNamaPeserta.getText()+"',"
                    + "no_telp = '"+tNoTelpPeserta.getText()+"',"
                    + "jenis_kelamin = '"+jenisKelamin+"',"
                    + "tempat_lahir = '"+tTempatLahir.getText()+"',"
                    + "tanggal_lahir = '"+cbTahunLahir.getSelectedItem()+"-"+bulanAngka+"-"+cbTanggalLahir.getSelectedItem()+"',"
                    + "email = '"+tEmailPeserta.getText()+"',"
                    + "alamat = '"+taAlamatPeserta.getText()+"' "
                    + "WHERE id_peserta='"+lIdPeserta.getText()+"'";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Data berhasil diupdate", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                FormUtama fu = new FormUtama();
                fu.getDataPeserta();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal diupdate", "Sukses", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public void getHapusPeserta(){
        int reply = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data peserta dengan ID = "+lIdPeserta.getText(), "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            String SQL = "DELETE FROM peserta WHERE id_peserta = '"+lIdPeserta.getText()+"'";
            int status = Koneksi.execute(SQL);
            
            if (status==1) {
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                FormUtama fu = new FormUtama();
                fu.getDataPeserta();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal dihapus", "Gagal", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Hmmm.!!! -___-");
        }
    }
    
    /**
     * Creates new form FormTambahPeserta
     */
    public FormTambahPeserta() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbJenisKelamin = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tNamaPeserta = new javax.swing.JTextField();
        tNoTelpPeserta = new javax.swing.JTextField();
        tTempatLahir = new javax.swing.JTextField();
        tEmailPeserta = new javax.swing.JTextField();
        rbLakiLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAlamatPeserta = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        bSimpanPeserta = new javax.swing.JButton();
        bUbahPeserta = new javax.swing.JButton();
        bHapusPeserta = new javax.swing.JButton();
        cbTanggalLahir = new javax.swing.JComboBox<>();
        cbBulanLahir = new javax.swing.JComboBox<>();
        cbTahunLahir = new javax.swing.JComboBox<>();
        lIdPeserta = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Peserta Baru");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nama");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setText("No. Telepon");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel3.setText("Jenis Kelamin");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel4.setText("Tempat Lahir");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel5.setText("Tanggal Lahir");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel6.setText("Email");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel7.setText("Alamat");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));
        jPanel1.add(tNamaPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 290, -1));
        jPanel1.add(tNoTelpPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 290, -1));
        jPanel1.add(tTempatLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 290, -1));
        jPanel1.add(tEmailPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 290, -1));

        rbLakiLaki.setBackground(new java.awt.Color(204, 204, 204));
        rbJenisKelamin.add(rbLakiLaki);
        rbLakiLaki.setSelected(true);
        rbLakiLaki.setText("Laki-Laki");
        jPanel1.add(rbLakiLaki, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 70, -1));

        rbPerempuan.setBackground(new java.awt.Color(204, 204, 204));
        rbJenisKelamin.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");
        jPanel1.add(rbPerempuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 90, -1));

        taAlamatPeserta.setColumns(20);
        taAlamatPeserta.setRows(5);
        jScrollPane1.setViewportView(taAlamatPeserta);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 170, 100));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bSimpanPeserta.setText("Simpan");
        bSimpanPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanPesertaActionPerformed(evt);
            }
        });
        jPanel2.add(bSimpanPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 20));

        bUbahPeserta.setText("Ubah");
        bUbahPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahPesertaActionPerformed(evt);
            }
        });
        jPanel2.add(bUbahPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 20));

        bHapusPeserta.setText("Hapus");
        bHapusPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusPesertaActionPerformed(evt);
            }
        });
        jPanel2.add(bHapusPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 90, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 110, 100));

        cbTanggalLahir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tanggal", "01", "02", "03" }));
        jPanel1.add(cbTanggalLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 80, -1));

        cbBulanLahir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bulan", "Januari", "Pebruari", "Maret" }));
        jPanel1.add(cbBulanLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 100, -1));

        cbTahunLahir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tahun", "1990", "1991", "1992", "1993" }));
        jPanel1.add(cbTahunLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 70, -1));

        lIdPeserta.setText("id_peserta");
        jPanel1.add(lIdPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 430, 300));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("PESERTA BARU");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanPesertaActionPerformed
        getSimpanPeserta();
    }//GEN-LAST:event_bSimpanPesertaActionPerformed

    private void bUbahPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahPesertaActionPerformed
        getUbahPeserta();
    }//GEN-LAST:event_bUbahPesertaActionPerformed

    private void bHapusPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusPesertaActionPerformed
        getHapusPeserta();
    }//GEN-LAST:event_bHapusPesertaActionPerformed

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
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTambahPeserta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bHapusPeserta;
    private javax.swing.JButton bSimpanPeserta;
    private javax.swing.JButton bUbahPeserta;
    private javax.swing.JComboBox<String> cbBulanLahir;
    private javax.swing.JComboBox<String> cbTahunLahir;
    private javax.swing.JComboBox<String> cbTanggalLahir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lIdPeserta;
    private javax.swing.ButtonGroup rbJenisKelamin;
    private javax.swing.JRadioButton rbLakiLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTextField tEmailPeserta;
    private javax.swing.JTextField tNamaPeserta;
    private javax.swing.JTextField tNoTelpPeserta;
    private javax.swing.JTextField tTempatLahir;
    private javax.swing.JTextArea taAlamatPeserta;
    // End of variables declaration//GEN-END:variables
}
