// Interfaz de servidor que define los métodos remotos para iniciar
// la carga y descarga de ficheros
package afs;
import java.rmi.*;
import java.io.*;

public interface Vice extends Remote {
    public ViceReader download(String fileName, String mode)
            throws RemoteException, FileNotFoundException;
    public ViceWriter upload(String fileName, String mode)
            throws RemoteException, FileNotFoundException;

    /* añada los métodos remotos que requiera */
}
       

