// Interfaz de servidor que define los métodos remotos
// para completar la carga de un fichero
package afs;
import java.rmi.*;
import java.io.IOException;

public interface ViceWriter extends Remote {
    public void setLength(long l) throws RemoteException, IOException;
    public void write(byte [] b) throws RemoteException, IOException;
    public void close() throws RemoteException, IOException ;
    /* añada los métodos remotos que requiera */
}       

