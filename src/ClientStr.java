import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStr {
    String nomeServer = "localHost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public ClientStr() {
    }

    public Socket conneti() {
        System.out.println("2 CLIENT partito in esecuzione....");

        try {
            this.tastiera = new BufferedReader(new InputStreamReader(System.in));
            this.miosocket = new Socket(this.nomeServer, this.portaServer);
            this.outVersoServer = new DataOutputStream(this.miosocket.getOutputStream());
            this.inDalServer = new BufferedReader(new InputStreamReader(this.miosocket.getInputStream()));
        } catch (UnknownHostException var2) {
            System.err.println("Host sconosciuto");
        } catch (Exception var3) {
            System.out.println(var3.getMessage());
            System.out.println("Errore durante la connesione!");
            System.exit(1);
        }

        return this.miosocket;
    }

    public void comunica() throws IOException {
        try {
            System.out.println("4... inserisci la stringa da trasmettere al server: \n");
            this.stringaUtente = this.tastiera.readLine();
            System.out.println("5... invio la stringa al server e attendo...");
            this.outVersoServer.writeBytes(this.stringaUtente + "\n");
            this.stringaRicevutaDalServer = this.inDalServer.readLine();
            System.out.println("8... risposta dal server\n" + this.stringaRicevutaDalServer);
            System.out.println("9 CLIENT: termina elaborazione e chiude connesione");
            this.miosocket.close();
        } catch (Exception var2) {
            System.out.println(var2.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
            System.exit(1);
        }

    }
    public static void main(String[] args) throws IOException {
        ClientStr cliente = new ClientStr();

        cliente.conneti();
        cliente.comunica();
    }


}
