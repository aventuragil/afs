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
        int bytesRead = file.read(dest);
        if (bytesRead == -1) {
            return null;
        } else if(bytesRead < tam) {
            byte [] dest2 = new byte[bytesRead];
            for(int i=0;i<bytesRead;i++){
                dest2[i]=dest[i];
            }
            return dest2;
        }
        return dest;
    }

    public void close() throws RemoteException, IOException {
        file.close();
    }
}
