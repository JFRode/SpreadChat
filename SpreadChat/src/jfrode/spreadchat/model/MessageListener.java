package jfrode.spreadchat.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import jfrode.spreadchat.view.FormChat;
import spread.BasicMessageListener;
import spread.SpreadGroup;
import spread.SpreadMessage;

public class MessageListener implements BasicMessageListener {

    private JTextArea chat;
    private javax.swing.JList users;
    private Connection connection;
    private DefaultListModel modelo;
    private boolean inicio;

    public MessageListener(Connection connection, JTextArea chat, javax.swing.JList users) {
        this.connection = connection;
        this.chat = chat;
        this.users = users;
        this.modelo = new DefaultListModel();
        this.inicio = true;
    }

    @Override
    public void messageReceived(SpreadMessage message) {
        try {
            String msg = new String(message.getData());
            if (message.isRegular()) {
                chat.setText((chat.getText() + "\n"
                        + message.getSender() + ": " + msg));
            } else {
                atualizarMembros(message);
                inicio = false;
            }
        } catch (Exception ex) {
            Logger.getLogger(FormChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizarMembros(SpreadMessage message) {
        if (inicio) {                                                               // So pega todos os membros que ja estao no grupo quando o usuario entrar.
            for (SpreadGroup member : message.getMembershipInfo().getMembers()) {
                modelo.add(modelo.getSize(), member);
            }
        } else if (message.getMembershipInfo().isCausedByJoin()) {
            chat.setText((chat.getText() + "\n"
                        + message.getMembershipInfo().getJoined() + " entrou no grupo."));
            modelo.add(modelo.getSize(), message.getMembershipInfo().getJoined());
        } else {
            chat.setText((chat.getText() + "\n"
                        + message.getMembershipInfo().getLeft() + " saiu no grupo."));
            modelo.removeElement(message.getMembershipInfo().getLeft());
        }
        users.setModel(modelo);
    }
}
