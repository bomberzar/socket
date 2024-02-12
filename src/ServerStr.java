import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringModifica = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerStr() {
    }

    public void attendi() {
        try {
            System.out.println("1 SERVER partito in esecuzione...");
            this.server = new ServerSocket(6789);
            this.client = this.server.accept();
            this.server.close();
            this.inDalClient = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            this.outVersoClient = new DataOutputStream(this.client.getOutputStream());
        } catch (IOException var2) {
            System.out.println(var2.getMessage());
            System.out.println("Errore durante l'istanza del sever !");
            System.exit(1);
        }

    }

    public void comunica() throws IOException {
        System.out.println("3 benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo...");
        this.stringaRicevuta = this.inDalClient.readLine();
        System.out.println("6 ricevuta la stringa dal cliente: " + this.stringaRicevuta);
        this.stringModifica = this.stringaRicevuta.toUpperCase();
        System.out.println("7 invio la stringa modificata al client...");
        this.outVersoClient.writeBytes(this.stringModifica + "\n");
        System.out.println("9 SERVER: fine elaborazione .... buoma notte!");
        this.client.close();
    }

    public static void main(String[] args) throws IOException {
        ServerStr servente = new ServerStr();

        servente.attendi();
        servente.comunica();
        //completato server
    }
}
