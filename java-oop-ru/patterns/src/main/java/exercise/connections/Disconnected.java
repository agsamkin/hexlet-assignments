package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private final TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        tcpConnection.setState(new Connected(tcpConnection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error");
    }

    @Override
    public void write(String data) {
        System.out.println("Error");
    }

    @Override
    public String toString() {
        return "disconnected";
    }

}
// END
