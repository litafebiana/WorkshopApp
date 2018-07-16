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
import javax.swing.table.DefaultTableModel;
import workshopapp.Koneksi;

/**
 *
 * @author Sultan
 */
public class FormCRUD extends javax.swing.JFrame {

    public void getData(){
        String kolom[] = {"Nama","Kelas","Jurusan"};
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        String SQL = "SELECT * FROM tb_crud";
        ResultSet rs = Koneksi.executeQuery(SQL);
        try {
            while(rs.next()) {
                String kolSatu = rs.getString(1);
                String kolDua = rs.getString(2);
                String kolTiga = rs.getString(3);
                String data[] = {kolSatu,kolDua,kolTiga};
                dtm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbData.setModel(dtm);
    }
    
    public int genNumber() {
        Random r = new Random(System.currentTimeMillis());
        int number = 30000;
        for(int counter=1; counter<=1;counter++){
            number = 30000+r.nextInt(60000);
        }
        return number;
    }
    
    /**
     * Creates new form FormCRUD
     */
    public FormCRUD() {
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tNama = new javax.swing.JTextField();
        tKelas = new javax.swing.JTextField();
        tJurusan = new javax.swing.JTextField();
        bSimpan = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bGetData = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbData = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lNumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("CRUD");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 50));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nama");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel3.setText("Kelas");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel4.setText("Jurusan");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));
        jPanel2.add(tNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 350, -1));
        jPanel2.add(tKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 350, -1));
        jPanel2.add(tJurusan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 350, -1));

        bSimpan.setText("Simpan");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(bSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 80, -1));

        bEdit.setText("Edit");
        jPanel2.add(bEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 80, -1));

        bHapus.setText("Hapus");
        jPanel2.add(bHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 80, -1));

        bGetData.setText("getData");
        bGetData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGetDataActionPerformed(evt);
            }
        });
        jPanel2.add(bGetData, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 80, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 440, 140));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbData.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbData);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 420, 220));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 440, 240));

        jButton1.setText("Generate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, -1, -1));

        jLabel5.setText("Number");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        lNumber.setText("PES-");
        getContentPane().add(lNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        if ("".equals(tNama.getText()) || "".equals(tKelas.getText()) || "".equals(tJurusan.getText())) {
            JOptionPane.showMessageDialog(this, "Harap Lengkapi Data", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            String SQL = "INSERT INTO tb_crud (namaa, kelas, jurusan) "
                    + "VALUES('"+tNama.getText()+"','"+tKelas.getText()+"','"+tJurusan.getText()+"')";
            int status = Koneksi.execute(SQL);
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                getData();
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal ditambahkan", "Sukses", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bGetDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGetDataActionPerformed
        getData();
    }//GEN-LAST:event_bGetDataActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        getData();
    }//GEN-LAST:event_formWindowActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        lNumber.setText("PES-"+String.valueOf(genNumber()));
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCRUD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bGetData;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lNumber;
    private javax.swing.JTextField tJurusan;
    private javax.swing.JTextField tKelas;
    private javax.swing.JTextField tNama;
    private javax.swing.JTable tbData;
    // End of variables declaration//GEN-END:variables
}
