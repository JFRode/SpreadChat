package jfrode.spreadchat.model;

import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import spread.SpreadConnection;
import spread.SpreadException;
import spread.SpreadGroup;
import spread.SpreadMessage;


public class Connection {
    private SpreadConnection connection;
    private SpreadGroup group;

    public Connection(String host, String hostGroup, String nickName) {
        connection = new SpreadConnection();
        try {
            connection.connect(InetAddress.getByName(host), 0, nickName, false, false);
            //connection.connect(InetAddress.getByName(host), 0, nickName, false, false);
            
            this.group = new SpreadGroup();
            group.join(connection, hostGroup);
            
        } catch (Exception ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void messageMulticast(SpreadMessage message) {
        try {
            connection.multicast(message);
        } catch (SpreadException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public SpreadMessage messageReceive() throws Exception {
        return connection.receive();
    }
}
