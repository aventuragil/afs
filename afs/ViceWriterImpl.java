// Implementación de la interfaz de servidor que define los métodos remotos
// para completar la carga de un fichero
package afs;

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

public class ViceWriterImpl extends UnicastRemoteObject implements ViceWriter {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String AFSDir = "AFSDir/";
    private RandomAccessFile file;

    public ViceWriterImpl(String fileName, String mode) throws RemoteException, FileNotFoundException {
        file = new RandomAccessFile(AFSDir + fileName, mode);
    }
    public void write(byte [] b) throws RemoteException, IOException {
        file.write(b);
    }
    public void close() throws RemoteException, IOException {
        file.close();
    }
}