/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package phase1;

import com.mysql.jdbc.PreparedStatement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.util.Vector;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author hp
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
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
        jButton1 = new javax.swing.JButton();
        clientUserName = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        password = new javax.swing.JTextField();
        pseudoname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 3, 18)); // NOI18N
        jButton1.setText("Connect");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 160, -1));

        clientUserName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(clientUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 94, 390, 32));

        jButton2.setFont(new java.awt.Font("Segoe UI Semilight", 3, 18)); // NOI18N
        jButton2.setText("exit");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 210, -1));

        password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 202, 390, 33));

        pseudoname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(pseudoname, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 144, 390, 33));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 3, 18)); // NOI18N
        jLabel1.setText("Email");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 145, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 3, 18)); // NOI18N
        jLabel2.setText("ID:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 95, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 3, 18)); // NOI18N
        jLabel3.setText("Password:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 203, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 1, 36)); // NOI18N
        jLabel4.setText("       Sign in");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 21, 232, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI Semilight", 3, 18)); // NOI18N
        jButton3.setText("Sign up");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 199, -1));

        jLabel5.setText(" ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 34, 15, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phase1/img/Working from Home.png"))); // NOI18N
        jLabel7.setText(" ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 280, 250));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phase1/img/Download Muslim Woman holding phone and peeking behind the wall while startled, shocked, Surprised, curiosity, listening, discovery and Pay attention concept illustration for free.jpg"))); // NOI18N
        jLabel8.setText(" ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 700, 770));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
          //  Vector<User> ParticipantList =  new Vector<User>();
            
            String id = clientUserName.getText(); // username entered by user
            String pseudo = pseudoname.getText();
            String pass = password.getText();
             
            int ss=pass.length();  
               
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost/loginformytvideo", "root", "");   
            java.sql.PreparedStatement stmt = con.prepareStatement("SELECT * FROM user WHERE name=? AND password=?");
           stmt.setString(1, pseudo);
        stmt.setString(2, pass);
        ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
               JOptionPane.showMessageDialog(this, "Hello "+id );
                 Socket s = new Socket("localhost", 2089); // create a socket
            DataInputStream inputStream = new DataInputStream(s.getInputStream()); // create input and output stream
            DataOutputStream outStream = new DataOutputStream(s.getOutputStream());
            
            outStream.writeUTF(id); // send username to the output stream
            
            String msgFromServer = new DataInputStream(s.getInputStream()).readUTF(); // receive message on socket
            
             
           
 String attack=msgFromServer;
        
            if (msgFromServer.equals("Username already taken")) {//if server sent this message then prompt user to enter other username
                JOptionPane.showMessageDialog(this, "Username already taken\n"); // show message in other dialog box
            } else {
                //new ClientVieww(id,pseudo,pass,s).setVisible(true); // otherwise just create a new thread of Client view and close the register jframe
                new ClientView(id, s,pseudo,pass,attack).setVisible(true);
               
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }

          
            
         
             
               
               
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          new  REgister().setVisible(true);
                this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clientUserName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField password;
    private javax.swing.JTextField pseudoname;
    // End of variables declaration//GEN-END:variables
}
