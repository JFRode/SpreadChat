package jfrode.spreadchat.view;

import jfrode.spreadchat.model.Connection;
import jfrode.spreadchat.model.MessageListener;
import spread.SpreadMessage;

public class FormChat extends javax.swing.JFrame {

    private String hostGroup;
    private Connection connection;
    
    public FormChat(String host, String hostGroup, String nickName) {
        connection = new Connection(host, hostGroup, nickName);
        this.hostGroup = hostGroup;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("SpreadChat - Host: " + host + " Group: " + hostGroup);
        textAreaMessages.setEditable(false);
        connection.addListner(new MessageListener(connection, textAreaMessages, listUsers));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        panelUsers = new javax.swing.JPanel();
        scrollPaneUsers = new javax.swing.JScrollPane();
        listUsers = new javax.swing.JList();
        panelMessages = new javax.swing.JPanel();
        scrollPaneMessages = new javax.swing.JScrollPane();
        textAreaMessages = new javax.swing.JTextArea();
        textFieldMessageSend = new javax.swing.JTextField();
        buttonSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelUsers.setBorder(javax.swing.BorderFactory.createTitledBorder("Users"));

        scrollPaneUsers.setViewportView(listUsers);

        javax.swing.GroupLayout panelUsersLayout = new javax.swing.GroupLayout(panelUsers);
        panelUsers.setLayout(panelUsersLayout);
        panelUsersLayout.setHorizontalGroup(
            panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelUsersLayout.setVerticalGroup(
            panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneUsers)
                .addContainerGap())
        );

        panelMessages.setBorder(javax.swing.BorderFactory.createTitledBorder("Messages"));

        textAreaMessages.setEditable(false);
        textAreaMessages.setColumns(20);
        textAreaMessages.setRows(5);
        scrollPaneMessages.setViewportView(textAreaMessages);

        buttonSend.setText("Send");
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMessagesLayout = new javax.swing.GroupLayout(panelMessages);
        panelMessages.setLayout(panelMessagesLayout);
        panelMessagesLayout.setHorizontalGroup(
            panelMessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMessagesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneMessages, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                    .addGroup(panelMessagesLayout.createSequentialGroup()
                        .addComponent(textFieldMessageSend)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSend)))
                .addContainerGap())
        );
        panelMessagesLayout.setVerticalGroup(
            panelMessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMessagesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneMessages, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelMessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldMessageSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSend))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelMessages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMessages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed
        SpreadMessage message = new spread.SpreadMessage();
        byte[] data = textFieldMessageSend.getText().getBytes();
        message.setData(data);
        message.addGroup(this.hostGroup);
        message.setReliable();
        connection.messageMulticast(message);
        textFieldMessageSend.setText("");
    }//GEN-LAST:event_buttonSendActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSend;
    private javax.swing.JList listUsers;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelMessages;
    private javax.swing.JPanel panelUsers;
    private javax.swing.JScrollPane scrollPaneMessages;
    private javax.swing.JScrollPane scrollPaneUsers;
    private javax.swing.JTextArea textAreaMessages;
    private javax.swing.JTextField textFieldMessageSend;
    // End of variables declaration//GEN-END:variables
}
