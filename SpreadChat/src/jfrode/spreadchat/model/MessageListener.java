package jfrode.spreadchat.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import jfrode.spreadchat.view.FormChat;
import spread.BasicMessageListener;
import spread.SpreadMessage;

public class MessageListener implements BasicMessageListener {

    private JTextArea chat;
    private javax.swing.JList users;
    private Connection connection;
    
    public MessageListener(Connection connection, JTextArea chat, javax.swing.JList users) {
        this.connection = connection;
        this.chat = chat;
        this.users = users;
    }

    @Override
    public void messageReceived(SpreadMessage message) {
        try {
            String msg = new String(message.getData());
              if (message.isRegular()) {
                 chat.setText((chat.getText() + "\n"
                        + message.getSender() + ": " + msg));
              }
        } catch (Exception ex) {
            Logger.getLogger(FormChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        users.removeAll();
        for (String user : connection.getUserList()) {
            users.add(new JLabel(user));
        }*/
    }
    
}
