import java.awt.Container;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.DefaultListModel; 
import javax.swing.JList;
import javax.swing.JOptionPane;
public class Server extends javax.swing.JFrame {

   private static final long serialVersionUID = 1L;

    private static Map<String, Socket> allUsersList = new ConcurrentHashMap<>(); // keeps the mapping of all the
    // usernames used and their socket connections
    private static Set<String> activeUserSet = new HashSet<>(); // this set keeps track of all the active users 

    private static int port = 2089;  // port number to be used

    private ServerSocket serverSocket; //server socket variable

    private DefaultListModel<String> activeDlm = new DefaultListModel<String>(); // keeps list of active users for display on UI

    private DefaultListModel<String> allDlm = new DefaultListModel<String>(); // keeps list of all users for display on UI
       // List<String> messagesList = new ArrayList<String>();
    DefaultListModel<String> dm;
    /**
     * Create the application.
     */
    public Server() {
        initComponents();
        // initialize();  // components of swing app will be initialized here.
        try {
            serverSocket = new ServerSocket(port);  // create a socket for server
            serverMessageBoard.append("Server started on port: " + port + "\n"); // print messages to server message board
            serverMessageBoard.append("Waiting for the clients...\n");
            new ClientAccept().start(); // this will create a thread for client
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    class ClientAccept extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                   
                    Socket clientSocket = serverSocket.accept();  // create a socket for client
                    String uName = new DataInputStream(clientSocket.getInputStream()).readUTF(); // this will receive the username sent from client register view
                    
                    DataOutputStream cOutStream = new DataOutputStream(clientSocket.getOutputStream()); // create an output stream for client

/////////////////////////
                    
List<String> list1 = new ArrayList<>();
list1.add("111");
list1.add("12");
list1.add("13");
list1.add("14");
list1.add("15");
String myVariable = uName;

DefaultListModel<String> model = new DefaultListModel<>();
for (String element : list1) {
    model.addElement(element);
}
h.setModel(model);

if (!model.contains(myVariable)) {
             cOutStream.writeUTF("attack");
} else {
    System.out.println("The list does not contain the element: " + myVariable);
}

///////////////////////////////////////////////////////////////////////
                    
                    
                    
                    
                    
                    
                    
                    
                    
                  
 if (activeUserSet != null && activeUserSet.contains(uName)) { // if username is in use then we need to prompt user to enter new name
                        
                        
                    
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        cOutStream.writeUTF("Username already taken");
                    } else {
                        allUsersList.put(uName, clientSocket); // add new user to allUserList and activeUserSet
                        activeUserSet.add(uName);
                        cOutStream.writeUTF(""); // clear the existing message
                        activeDlm.addElement(uName); // add this user to the active user JList
                        if (!allDlm.contains(uName)) // if username taken previously then don't add to allUser JList otherwise add it
                        {
                            allDlm.addElement(uName);
                        }
                        activeClientList.setModel(activeDlm); // show the active and allUser List to the swing app in JList
                        allUserNameList.setModel(allDlm);
                        serverMessageBoard.append("Client " + uName + " Connected...\n"); // print message on server that new client has been connected.
                        new MsgRead(clientSocket, uName).start(); // create a thread to read messages
                        new PrepareCLientList().start(); //create a thread to update all the active clients
                    }
                } catch (IOException ioex) {  // throw any exception occurs
                    ioex.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MsgRead extends Thread { // this class reads the messages coming from client and take appropriate actions

        Socket s;
        String Id;

        private MsgRead(Socket s, String uname) { // socket and username will be provided by client
            this.s = s;
            this.Id = uname;
        }

        @Override
        public void run() {
                     
 
            while (allUserNameList != null && !allUsersList.isEmpty()) {  // if allUserList is not empty then proceed further
                try {
                    String message = new DataInputStream(s.getInputStream()).readUTF(); // read message from client
                    System.out.println("message read ==> " + message); // just print the message for testing
                   
            lii.append( message +"    \n");
          
                    
                    
                    
                    String[] msgList = message.split(":"); // I have used my own identifier to identify what action to take on the received message from client
                    // i have appended actionToBeTaken:clients_for_receiving_msg:message
                    if (msgList[0].equalsIgnoreCase("multicast")) { // if action is multicast then send messages to selected active users
                        String[] sendToList = msgList[1].split(","); //this variable contains list of clients which will receive message
                        for (String usr : sendToList) { // for every user send message
                            try {
                                if (activeUserSet.contains(usr)) { // check again if user is active then send the message
                                    new DataOutputStream(((Socket) allUsersList.get(usr)).getOutputStream())
                                            .writeUTF("< " + Id + " >" + msgList[2]); // put message in output stream
                                    
                                }
                            } catch (Exception e) { // throw exceptions
                                e.printStackTrace();
                            }
                          
                        }
                    } else if (msgList[0].equalsIgnoreCase("broadcast")) { // if broadcast then send message to all active clients

                        Iterator<String> itr1 = allUsersList.keySet().iterator(); // iterate over all users
                        while (itr1.hasNext()) {
                            String usrName = (String) itr1.next(); // it is the username
                            if (!usrName.equalsIgnoreCase(Id)) { // we don't need to send message to ourself, so we check for our Id
                                try {
                                    if (activeUserSet.contains(usrName)) { // if client is active then send message through output stream
                                        new DataOutputStream(((Socket) allUsersList.get(usrName)).getOutputStream())
                                                .writeUTF("< " + Id + " >" + msgList[1]);
                                    } else {
                                        //if user is not active then notify the sender about the disconnected client
                                        new DataOutputStream(s.getOutputStream())
                                                .writeUTF("Message couldn't be delivered to user " + usrName + " because it is disconnected.\n");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace(); // throw exceptions
                                }
                            }
                        }
                    } else if (msgList[0].equalsIgnoreCase("exit")) { // if a client's process is killed then notify other clients
                        activeUserSet.remove(Id); // remove that client from active usre set
                        serverMessageBoard.append(Id + " disconnected....\n"); // print message on server message board

                        new PrepareCLientList().start(); // update the active and all user list on UI

                        Iterator<String> itr = activeUserSet.iterator(); // iterate over other active users
                        while (itr.hasNext()) {
                            String usrName2 = (String) itr.next();
                            if (!usrName2.equalsIgnoreCase(Id)) { // we don't need to send this message to ourself
                                try {
                                    new DataOutputStream(((Socket) allUsersList.get(usrName2)).getOutputStream())
                                            .writeUTF(Id + " disconnected..."); // notify all other active user for disconnection of a user
                                } catch (Exception e) { // throw errors
                                    e.printStackTrace();
                                }
                                new PrepareCLientList().start(); // update the active user list for every client after a user is disconnected
                            }
                        }
                        activeDlm.removeElement(Id); // remove client from Jlist for server
                        activeClientList.setModel(activeDlm); //update the active user list
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class PrepareCLientList extends Thread { // it prepares the list of active user to be displayed on the UI

        @Override
        public void run() {
            try {
                String ids = "";
                Iterator itr = activeUserSet.iterator(); // iterate over all active users
                while (itr.hasNext()) { // prepare string of all the users
                    String key = (String) itr.next();
                    ids += key + ",";
                }
                if (ids.length() != 0) { // just trimming the list for the safe side.
                    ids = ids.substring(0, ids.length() - 1);
                }
                itr = activeUserSet.iterator();
                while (itr.hasNext()) { // iterate over all active users
                    String key = (String) itr.next();
                    try {
                        new DataOutputStream(((Socket) allUsersList.get(key)).getOutputStream())
                                .writeUTF(":;.,/=" + ids); // set output stream and send the list of active users with identifier prefix :;.,/=
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        activeClientList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        serverMessageBoard = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        allUserNameList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lii = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        h = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Server:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 23, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("All user");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(739, 15, -1, -1));

        activeClientList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        activeClientList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(activeClientList);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 61, 163, 228));

        serverMessageBoard.setColumns(20);
        serverMessageBoard.setRows(5);
        serverMessageBoard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(serverMessageBoard);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 66, 464, 200));

        allUserNameList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        allUserNameList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(allUserNameList);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 128, 228));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("msg chiffr:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 292, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Active user");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 35, -1, -1));

        lii.setColumns(20);
        lii.setRows(5);
        jScrollPane5.setViewportView(lii);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 323, -1, -1));

        h.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(h);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 292, 152, 140));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phase1/img/PREMIU~3.JPG"))); // NOI18N
        jLabel5.setText(" ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 140, 290));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phase1/img/Premium Vector _ Cheerful woman gesturing welcome sign and smiling while standing extending hands towards as wanting cuddle smiling welcoming concept illustration.jpg"))); // NOI18N
        jLabel6.setText(" ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, 210, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> activeClientList;
    private javax.swing.JList<String> allUserNameList;
    private javax.swing.JList<String> h;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea lii;
    private javax.swing.JTextArea serverMessageBoard;
    // End of variables declaration//GEN-END:variables
}
