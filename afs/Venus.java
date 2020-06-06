// Clase de cliente que inicia la interacción con el servicio de
// ficheros remotos
package afs;
import java.rmi.*; 

public class Venus {
    private String tam;
    private Vice srvVice;

    public Venus() {
        try {
            String puerto = System.getenv("REGISTRY_PORT");
            String host = System.getenv("REGISTRY_HOST");
            this.tam = System.getenv("BLOCKSIZE");
            this.srvVice = (Vice) Naming.lookup("//" + host + ":" + puerto + "/afs");

        } catch (Exception e) {
            System.err.println("Excepcion en ClienteEco:");
            e.printStackTrace();
        }
    }

    public String getTam() {
        return this.tam;
    }

    public Vice getsrvVice () {
        return this.srvVice;
    }

    
}

