import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerStr servente = new ServerStr();
        ClientStr cliente = new ClientStr();
        servente.attendi();
        cliente.conneti();
        servente.comunica();
        cliente.comunica();
    }
}