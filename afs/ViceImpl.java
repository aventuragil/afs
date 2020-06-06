// Implementación de la interfaz de servidor que define los métodos remotos
// para iniciar la carga y descarga de ficheros
package afs;

import java.io.FileNotFoundException;
import java.rmi.*;
import java.rmi.server.*;

public class ViceImpl extends UnicastRemoteObject implements Vice {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ViceImpl() throws RemoteException {
    }

    public ViceReader download(String fileName, String mode) throws RemoteException, FileNotFoundException {
        return new ViceReaderImp(fileName, mode);
    }

    public ViceWriter upload(String fileName, String mode) throws RemoteException {
        return new ViceWriterImpl(fileName, mode);
    }
}
