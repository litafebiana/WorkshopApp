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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import workshopapp.Koneksi;

/**
 *
 * @author Sultan
 */
public class FormTambahNarasumber extends javax.swing.JFrame {
    DefaultListModel model1 = new DefaultListModel();
    DefaultListModel model2 = new DefaultListModel();
    
    public void getDataMateriTersedia(){
        String SQL = "SELECT nama_materi FROM tb_materi WHERE nama_materi NOT IN ( SELECT m.nama_materi FROM tb_materi m JOIN tb_narasumber_materi nm ON nm.id_materi = m.id_materi WHERE nm.id_narasumber = '"+lIdNarasumber.getText()+"')";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String list = rs.getString("nama_materi");
                model1.addElement(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTambahMateri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getDataMateriDilatih(){
        String SQL = "SELECT m.nama_materi FROM tb_materi m JOIN tb_narasumber_materi nm ON nm.id_materi = m.id_materi WHERE nm.id_narasumber = '"+lIdNarasumber.getText()+"'";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String list = rs.getString("nama_materi");
                model2.addElement(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTambahMateri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
                fu.getDataNarasumber();
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
                fu.getDataNarasumber();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal diupdate", "Sukses", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public void getHapusNarasumber(){
        int reply = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data narasumber dengan ID = "+lIdNarasumber.getText(), "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            String SQL = "DELETE FROM tb_narasumber WHERE id_narasumber = '"+lIdNarasumber.getText()+"'";
            int status = Koneksi.execute(SQL);
            
            if (status==1) {
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                FormUtama fu = new FormUtama();
                fu.getDataNarasumber();
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
        lbMateriTersedia.setModel(model1);
        lbMateriDilatih.setModel(model2);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tNamaNarasumber = new javax.swing.JTextField();
        tNoTelpNarasumber = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        bSimpanNarasumber = new javax.swing.JButton();
        bUbahNarasumber = new javax.swing.JButton();
        bHapusNarsumber = new javax.swing.JButton();
        lIdNarasumber = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbMateriTersedia = new javax.swing.JList<>();
        bTambahMateriLatih = new javax.swing.JButton();
        bHapusMateriLatih = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbMateriDilatih = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        tEmailNarasumber = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Peserta Baru");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("No. Telepon");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel6.setText("Email");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        jPanel1.add(tNamaNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 310, -1));
        jPanel1.add(tNoTelpNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 310, -1));

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

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setViewportView(lbMateriTersedia);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 160, 120));

        bTambahMateriLatih.setText(">");
        bTambahMateriLatih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahMateriLatihActionPerformed(evt);
            }
        });
        jPanel4.add(bTambahMateriLatih, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 50, 30));

        bHapusMateriLatih.setText("<");
        bHapusMateriLatih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusMateriLatihActionPerformed(evt);
            }
        });
        jPanel4.add(bHapusMateriLatih, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 50, 30));

        jScrollPane1.setViewportView(lbMateriDilatih);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 160, 120));

        jLabel1.setText("Materi yang dilatih");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        jLabel7.setText("Materi yang tersedia");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 410, 160));

        jLabel3.setText("Nama");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 280, 10));

        jLabel4.setText("Tambah Materi yang dilatih");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));
        jPanel1.add(tEmailNarasumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 310, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 430, 340));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("NARASUMBER BARU");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 70));

        setSize(new java.awt.Dimension(446, 448));
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

    private void bTambahMateriLatihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahMateriLatihActionPerformed
        if (lbMateriTersedia.getSelectedIndices().length > 0) {
            int[] selectedIndices = lbMateriTersedia.getSelectedIndices();
            
            for (int i = 0; i < selectedIndices.length; i++) {
                String SQL = "INSERT INTO tb_narasumber_materi (id_narasumber, id_materi) SELECT '"+lIdNarasumber.getText()+"', id_materi FROM tb_materi WHERE nama_materi = '"+lbMateriTersedia.getModel().getElementAt(selectedIndices[i])+"'";
                int status = Koneksi.execute(SQL);
                if (status == 1) {
                    JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    model2.addElement(lbMateriTersedia.getModel().getElementAt(selectedIndices[i]));
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal ditambahkan", "Sukses", JOptionPane.WARNING_MESSAGE);
                }
            }
            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                model1.removeElementAt(selectedIndices[i]);
            }
        }
    }//GEN-LAST:event_bTambahMateriLatihActionPerformed

    private void bHapusMateriLatihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusMateriLatihActionPerformed
        if (lbMateriDilatih.getSelectedIndices().length > 0) {
            int[] selectedIndices = lbMateriDilatih.getSelectedIndices();
            
            for (int i = 0; i < selectedIndices.length; i++) {
                String SQL = "DELETE FROM tb_narasumber_materi WHERE id_narasumber = '"+lIdNarasumber.getText()+"' AND id_materi = (SELECT id_materi FROM tb_materi WHERE nama_materi = '"+lbMateriDilatih.getModel().getElementAt(selectedIndices[i])+"')";
                int status = Koneksi.execute(SQL);
                if (status == 1) {
                    JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    model1.addElement(lbMateriDilatih.getModel().getElementAt(selectedIndices[i]));
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal ditambahkan", "Sukses", JOptionPane.WARNING_MESSAGE);
                }
            }
            for (int i = selectedIndices.length - 1; i >= 0; i--) {
                model2.removeElementAt(selectedIndices[i]);
            }
        }
    }//GEN-LAST:event_bHapusMateriLatihActionPerformed

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
    private javax.swing.JButton bHapusMateriLatih;
    private javax.swing.JButton bHapusNarsumber;
    private javax.swing.JButton bSimpanNarasumber;
    private javax.swing.JButton bTambahMateriLatih;
    private javax.swing.JButton bUbahNarasumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lIdNarasumber;
    private javax.swing.JList<String> lbMateriDilatih;
    private javax.swing.JList<String> lbMateriTersedia;
    private javax.swing.JTextField tEmailNarasumber;
    private javax.swing.JTextField tNamaNarasumber;
    private javax.swing.JTextField tNoTelpNarasumber;
    // End of variables declaration//GEN-END:variables
}
