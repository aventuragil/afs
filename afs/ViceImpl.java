// Implementación de la interfaz de servidor que define los métodos remotos
// para iniciar la carga y descarga de ficheros
package afs;

import java.io.*;
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
        ViceReader vr = new ViceReaderImpl(fileName, mode);
        return vr;
    }

    public ViceWriter upload(String fileName, String mode) throws RemoteException, FileNotFoundException {
        //ViceWriter vw = new ViceWriterImpl(fileName, mode);
        //return vw;
        return null;
    }
}
