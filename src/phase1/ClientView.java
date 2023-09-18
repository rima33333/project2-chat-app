package phase1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.crypto.spec.SecretKeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientView extends javax.swing.JFrame {

    private ObjectOutputStream out;
    DataInputStream inputStream;
    DataOutputStream outStream;
    DefaultListModel<String> dm;
    //private ObjectInputStream in;
    String id, clientIds = "";
    int shift = 3; // set the shift value  

    public ClientView(String id, Socket s, String pseudo, String password,String attack) { // constructor call, it will initialize required variables

        initComponents();
        this.id = id;
        try {
            JPanel panel = new JPanel();
            getContentPane().add(panel);
          att.setText(attack);
            m.setText(id);
            this.setTitle("Client View - " + id);
            dm = new DefaultListModel<String>(); // default list used for showing active users on UI
            list.setModel(dm);// show that list on UI component JList named clientActiveUsersList
            inputStream = new DataInputStream(s.getInputStream()); // initilize input and output stream
            outStream = new DataOutputStream(s.getOutputStream());
            //////////////

            /////////////////
            new Read().start();
            //   new Reade().start();// create a new thread for reading the messages
             
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //////////////////////////////////////////////////
    class Read extends Thread {

        @Override
        public void run() {
            while (true) {
                try {

                    String m = inputStream.readUTF();  // read message from server, this will contain :;.,/=<comma seperated clientsIds>

                    System.out.println("inside read thread : " + m); // print message for testing purpose
                    if (m.contains(":;.,/=")) { // prefix(i know its random)
                        m = m.substring(6); // comma separated all active user ids
                        dm.clear(); // clear the list before inserting fresh elements
                        StringTokenizer st = new StringTokenizer(m, ","); // split all the clientIds and add to dm below
                        while (st.hasMoreTokens()) {
                            String u = st.nextToken();
                            if (!id.equals(u)) // we do not need to show own user id in the active user list pane
                            {
                                dm.addElement(u);
                                // add all the active user ids to the defaultList to display on active
                            }													// user pane on client view
                        }
                    } else {

                        
                        
                        
                        
                        
                        ///////////////////////////////////dechiffrement/////////////////
                        int shift = 3; // set the shift value

                        String ciphertext = m.toLowerCase(); // convert the encrypted message to lowercase
                        StringBuilder plaintext = new StringBuilder(); // initialize the plaintext string

                        for (int i = 0; i < ciphertext.length(); i++) {
                            char c = ciphertext.charAt(i);
                            if (Character.isLetter(c)) {
                                int ascii = ((int) c - shift - 97) % 26; // apply the reverse shift
                                if (ascii < 0) {
                                    ascii += 26; // wrap around if the result is negative
                                }
                                ascii += 97; // convert back to ASCII code
                                plaintext.append((char) ascii); // append the decrypted character to the plaintext string
                            } else {
                                plaintext.append(c); // keep non-letter characters as is
                            }
                        }

                        String decryptedMessage = plaintext.toString(); // get the decrypted message

                        ////////////////////////////////////////////////////////////////////
                        
                        
                        
                        msgBox.append(decryptedMessage + "    \n"); //otherwise print on the clients message board
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        send = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgBox = new javax.swing.JTextArea();
        msg = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        select = new javax.swing.JRadioButton();
        all = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        m = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        att = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        send.setFont(new java.awt.Font("Segoe UI Historic", 3, 14)); // NOI18N
        send.setText("send");
        send.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });
        jPanel1.add(send, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 387, 85, -1));

        msgBox.setColumns(20);
        msgBox.setRows(5);
        msgBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jScrollPane1.setViewportView(msgBox);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 104, 372, 210));

        msg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 364, 307, 45));

        list.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(list);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 104, 158, 210));

        buttonGroup.add(select);
        select.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        select.setText("select");
        select.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel1.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(662, 162, 83, -1));

        buttonGroup.add(all);
        all.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        all.setText("select all");
        all.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(all, new org.netbeans.lib.awtextra.AbsoluteConstraints(662, 122, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 3, 24)); // NOI18N
        jLabel1.setText("SEND MSG: .......................................................................................");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 326, 372, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 104, 14, 250));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 3, 24)); // NOI18N
        jLabel2.setText("Hello:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 49, 123, -1));

        m.setFont(new java.awt.Font("Segoe UI Semibold", 3, 24)); // NOI18N
        m.setText(" ...........................");
        jPanel1.add(m, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 49, 71, -1));

        exit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        exit.setText("Exit");
        exit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanel1.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(674, 387, 148, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Send file");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 387, 79, -1));

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Image", "File" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 323, 79, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("proxy");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 387, 109, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Open file");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(577, 325, 79, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 3, 24)); // NOI18N
        jLabel3.setText("Active user:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 149, -1));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Save file");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 325, 79, -1));

        att.setText("...........................................");
        jPanel1.add(att, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 54, -1, 32));

        jLabel4.setText(" ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 100, -1));

        jLabel5.setText(" ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 120, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phase1/img/PREMIU~3.JPG"))); // NOI18N
        jLabel6.setText(" ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 220, 250, 270));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phase1/img/Premium Vector _ Cheerful woman gesturing welcome sign and smiling while standing extending hands towards as wanting cuddle smiling welcoming concept illustration.jpg"))); // NOI18N
        jLabel7.setText(" ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 250, 270));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        try {

            String textAreaMessage = msg.getText(); // get the message from textbox

            
            
            
            ////////////////////////chiffrement //////////////////////////////////////
            String plaintext = textAreaMessage.toLowerCase(); // convert the message to lowercase
            StringBuilder ciphertext = new StringBuilder(); // initialize the ciphertext string

            for (int i = 0; i < plaintext.length(); i++) {
                char c = plaintext.charAt(i);
                if (Character.isLetter(c)) {
                    int ascii = ((int) c + shift - 97) % 26 + 97; // apply the shift and wrap around if needed
                    ciphertext.append((char) ascii); // append the encrypted character to the ciphertext string
                } else {
                    ciphertext.append(c); // keep non-letter characters as is
                }
            }

            String encryptedMessage = ciphertext.toString(); // get the encrypted message

            //////////////////////////////
            
            
            
            
            
            if (textAreaMessage != null && !textAreaMessage.isEmpty()) {  // only if message is not empty then send it further otherwise do nothing

                String messageToBeSentToServer = "";
                String cast = "broadcast"; // this will be an identifier to identify type of message
                int flag = 0; // flag used to check whether used has selected any client or not for multicast 
                if (select.isSelected()) { // if 1-to-N is selected then do this
                    cast = "multicast";
                    List<String> clientList = list.getSelectedValuesList(); // get all the users selected on UI
                    if (clientList.size() == 0) // if no user is selected then set the flag for further use
                    {
                        flag = 1;
                    }
                    for (String selectedUsr : clientList) { // append all the usernames selected in a variable
                        if (clientIds.isEmpty()) {
                            clientIds += selectedUsr;
                        } else {
                            clientIds += "," + selectedUsr;
                        }
                    }
                    messageToBeSentToServer = cast + ":" + clientIds + ":" + encryptedMessage; // prepare message to be sent to server
                } else {
                    messageToBeSentToServer = cast + ":" + encryptedMessage; // in case of broadcast we don't need to know userIds
                }
                if (cast.equalsIgnoreCase("multicast")) {
                    if (flag == 1) { // for multicast check if no user was selected then prompt a message dialog
                        JOptionPane.showMessageDialog(this, "No user selected");
                    } else { // otherwise just send the message to the user
                        outStream.writeUTF(messageToBeSentToServer);
                        msg.setText("");
                        msgBox.append("< You sent msg to " + clientIds + ">" + textAreaMessage + "\n"); //show the sent message to the sender's message board
                    }
                } else { // in case of broadcast
                    outStream.writeUTF(messageToBeSentToServer);
                    msg.setText("");
                    msgBox.append("< You sent msg to All >" + textAreaMessage + "\n");
                }
                clientIds = ""; // clear the all the client ids 
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "User does not exist anymore."); // if user doesn't exist then show message
        }


    }//GEN-LAST:event_sendActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        try {
            outStream.writeUTF("exit"); // closes the thread and show the message on server and client's message
            // board
            msgBox.append("You are disconnected now.\n");
            this.dispose(); // close the frame 
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }//GEN-LAST:event_exitActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            Socket s = new Socket("localhost", 2089);
            out = new ObjectOutputStream(s.getOutputStream());
            Data data = new Data();
            data.setStatus("new");
            data.setName("Laing raven");
            out.writeObject(data);
            out.flush();
            JFileChooser ch = new JFileChooser();
            int c = ch.showOpenDialog(this);
            if (c == JFileChooser.APPROVE_OPTION) {
                File f = ch.getSelectedFile();
                FileInputStream in = new FileInputStream(f);
                byte b[] = new byte[in.available()];
                in.read(b);
                //Data data = new Data();
                data.setStatus(jComboBox1.getSelectedItem() + "");
                data.setName(msg.getText().trim());
                data.setFile(b);
                out.writeObject(data);
                out.flush();
                msgBox.append("send 1 file ../n");
                msgBox.append("/n");

                //////////////////////////////////////
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton1ActionPerformed


    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
           try { 
        String a = att.getText();
          
               if (a.equals("attack"))
                        {
                           JOptionPane.showMessageDialog(this, "You cannot send message because you are a hacker"); 
    }
               else{
               
                   
                     String textAreaMessage = msg.getText(); // get the message from textbox

            ////////////////////////chiffrement //////////////////////////////////////
            String plaintext = textAreaMessage.toLowerCase(); // convert the message to lowercase
            StringBuilder ciphertext = new StringBuilder(); // initialize the ciphertext string

            for (int i = 0; i < plaintext.length(); i++) {
                char c = plaintext.charAt(i);
                if (Character.isLetter(c)) {
                    int ascii = ((int) c + shift - 97) % 26 + 97; // apply the shift and wrap around if needed
                    ciphertext.append((char) ascii); // append the encrypted character to the ciphertext string
                } else {
                    ciphertext.append(c); // keep non-letter characters as is
                }
            }

            String encryptedMessage = ciphertext.toString(); // get the encrypted message

            //////////////////////////////
            if (textAreaMessage != null && !textAreaMessage.isEmpty()) {  // only if message is not empty then send it further otherwise do nothing

                String messageToBeSentToServer = "";
                String cast = "broadcast"; // this will be an identifier to identify type of message
                int flag = 0; // flag used to check whether used has selected any client or not for multicast 
                if (select.isSelected()) { // if 1-to-N is selected then do this
                    cast = "multicast";
                    List<String> clientList = list.getSelectedValuesList(); // get all the users selected on UI
                    if (clientList.size() == 0) // if no user is selected then set the flag for further use
                    {
                        flag = 1;
                    }
                    for (String selectedUsr : clientList) { // append all the usernames selected in a variable
                        if (clientIds.isEmpty()) {
                            clientIds += selectedUsr;
                        } else {
                            clientIds += "," + selectedUsr;
                        }
                    }
                    messageToBeSentToServer = cast + ":" + clientIds + ":" + encryptedMessage; // prepare message to be sent to server
                } else {
                    messageToBeSentToServer = cast + ":" + encryptedMessage; // in case of broadcast we don't need to know userIds
                }
                if (cast.equalsIgnoreCase("multicast")) {
                    if (flag == 1) { // for multicast check if no user was selected then prompt a message dialog
                        JOptionPane.showMessageDialog(this, "No user selected");
                    } else { // otherwise just send the message to the user
                        outStream.writeUTF(messageToBeSentToServer);
                        msg.setText("");
                        msgBox.append("< You sent msg to " + clientIds + ">" + textAreaMessage + "\n"); //show the sent message to the sender's message board
                    }
                } else { // in case of broadcast
                    outStream.writeUTF(messageToBeSentToServer);
                    msg.setText("");
                    msgBox.append("< You sent msg to All >" + textAreaMessage + "\n");
                }
                clientIds = ""; // clear the all the client ids 
            }
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
               
               
               }
                
                } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
                        } 
               
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton all;
    private javax.swing.JLabel att;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton exit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> list;
    private javax.swing.JLabel m;
    private javax.swing.JTextField msg;
    private javax.swing.JTextArea msgBox;
    private javax.swing.JRadioButton select;
    private javax.swing.JButton send;
    // End of variables declaration//GEN-END:variables
}
