package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private final TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        System.out.println("Error");
    }

    @Override
    public void disconnect() {
        tcpConnection.setState(new Disconnected(tcpConnection));
    }

    @Override
    public void write(String data) {
        System.out.println(data);
    }

    @Override
    public String toString() {
        return "connected";
    }
}
// END
