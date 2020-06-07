// Implementación de la interfaz de servidor que define los métodos remotos
// para completar la descarga de un fichero
package afs;

import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

public class ViceReaderImpl extends UnicastRemoteObject implements ViceReader {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String AFSDir = "AFSDir/";
    private RandomAccessFile file;

    public ViceReaderImpl(String fileName, String mode) throws RemoteException, FileNotFoundException {
        file = new RandomAccessFile(AFSDir + fileName, mode);
    }

    public byte[] read(int tam) throws RemoteException, IOException {
        byte[] dest = new byte[tam];
        int offset = 0;
        int length = tam;
        int bytesRead = file.read(dest, offset, length);
        return dest;
    }

    public void close() throws RemoteException, IOException {
        file.close();
    }
}
