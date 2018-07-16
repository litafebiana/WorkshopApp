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
public class FormTambahNarasumber extends javax.swing.JFrame {

    public void roleButton(String role){
        if (role.equals("simpan")){
            bSimpanNarasumber.setEnabled(true);
            bUbahNarasumber.setEnabled(false);
            bHapusNarsumber.setEnabled(false);
            lIdNarasumber.setVisible(false);
        }else if (role.equals("ubahhapus")){
            bSimpanNarasumber.setEnabled(false);
            bUbahNarasumber.setEnabled(true);
            bHapusNarsumber.setEnabled(true);
            lIdNarasumber.setVisible(false);
        }
    }
    
    public int genID() {
        Random r = new Random(System.currentTimeMillis());
        int number = 10000;
        for(int counter=1; counter<=1;counter++){
            number = 10000+r.nextInt(60000);
        }
        return number;
    }
    
    public void getSimpanNarasumber(){
        if ("".equals(tNamaNarasumber.getText()) || "".equals(tNoTelpNarasumber.getText()) || "".equals(tEmailNarasumber.getText())) {
            JOptionPane.showMessageDialog(this, "Harap Lengkapi Data", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "INSERT INTO tb_narasumber (id_narasumber, nama_narasumber, no_telp, email_narasumber) "
                    + "VALUES('NAR-2018"+genID()+"','"+tNamaNarasumber.getText()+"','"+tNoTelpNarasumber.getText()+"','"+tEmailNarasumber.getText()+"')";
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
        String SQL = "SELECT id_narasumber, nama_narasumber, no_telp, email_narasumber FROM tb_narasumber where id_narasumber = '"+id+"'";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                lIdNarasumber.setText(rs.getString(1));
                tNamaNarasumber.setText(rs.getString(2));
                tNoTelpNarasumber.setText(rs.getString(3));
                tEmailNarasumber.setText(rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getUbahNarasumber(){
        if ("".equals(tNamaNarasumber.getText()) || "".equals(tNoTelpNarasumber.getText()) || "".equals(tEmailNarasumber.getText())) {
            JOptionPane.showMessageDialog(this, "Harap Lengkapi Data", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "UPDATE tb_narasumber SET "
                    + "nama_narasumber = '"+tNamaNarasumber.getText()+"',"
                    + "no_telp = '"+tNoTelpNarasumber.getText()+"',"
                    + "email_narasumber = '"+tEmailNarasumber.getText()+"' "
                    + "WHERE id_narasumber='"+lIdNarasumber.getText()+"'";
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
    
    public void getHapusNarasumber(){
        int reply = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data peserta dengan ID = "+lIdNarasumber.getText(), "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            String SQL = "DELETE FROM tb_narasumber WHERE id_narasumber = '"+lIdNarasumber.getText()+"'";
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
    public FormTambahNarasumber() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tNamaNarasumber = new javax.swing.JTextField();
        tNoTelpNarasumber = new javax.swing.JTextField();
        tEmailNarasumber = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        bSimpanNarasumber = new javax.swing.JButton();
        bUbahNarasumber = new javax.swing.JButton();
        bHapusNarsumber = new javax.swing.JButton();
        lIdNarasumber = new javax.swing.JLabel();
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

        jLabel6.setText("Email");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        jPanel1.add(tNamaNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 290, -1));
        jPanel1.add(tNoTelpNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 290, -1));
        jPanel1.add(tEmailNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 290, -1));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bSimpanNarasumber.setText("Simpan");
        bSimpanNarasumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanNarasumberActionPerformed(evt);
            }
        });
        jPanel2.add(bSimpanNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 20));

        bUbahNarasumber.setText("Ubah");
        bUbahNarasumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahNarasumberActionPerformed(evt);
            }
        });
        jPanel2.add(bUbahNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 20));

        bHapusNarsumber.setText("Hapus");
        bHapusNarsumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusNarsumberActionPerformed(evt);
            }
        });
        jPanel2.add(bHapusNarsumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 90, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 310, 40));

        lIdNarasumber.setText("id_narasumber");
        jPanel1.add(lIdNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 430, 150));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("NARASUMBER BARU");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanNarasumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanNarasumberActionPerformed
        getSimpanNarasumber();
    }//GEN-LAST:event_bSimpanNarasumberActionPerformed

    private void bUbahNarasumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahNarasumberActionPerformed
        getUbahNarasumber();
    }//GEN-LAST:event_bUbahNarasumberActionPerformed

    private void bHapusNarsumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusNarsumberActionPerformed
        getHapusNarasumber();
    }//GEN-LAST:event_bHapusNarsumberActionPerformed

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
            java.util.logging.Logger.getLogger(FormTambahNarasumber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTambahNarasumber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTambahNarasumber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTambahNarasumber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTambahNarasumber().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bHapusNarsumber;
    private javax.swing.JButton bSimpanNarasumber;
    private javax.swing.JButton bUbahNarasumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lIdNarasumber;
    private javax.swing.JTextField tEmailNarasumber;
    private javax.swing.JTextField tNamaNarasumber;
    private javax.swing.JTextField tNoTelpNarasumber;
    // End of variables declaration//GEN-END:variables
}
