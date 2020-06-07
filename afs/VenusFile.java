// Clase de cliente que define la interfaz a las aplicaciones.
// Proporciona la misma API que RandomAccessFile.
package afs;

import java.rmi.*;
import java.io.*;

public class VenusFile {
    public static final String cacheDir = "Cache/";
    public RandomAccessFile file;
    public Venus venus;
    public String fileName;
    public String mode;


    public VenusFile(Venus venus, String fileName, String mode) throws RemoteException, IOException, FileNotFoundException {
        this.mode = mode;
        this.fileName = fileName;
        if (mode.equals("rw")) {
            this.mode = mode;
            if (existeEnCache(fileName)) {
                file = new RandomAccessFile(cacheDir + fileName, mode);
            } else {
                file = new RandomAccessFile(cacheDir + fileName, "rw");
                ViceReader viceReader = venus.getsrvVice().download(fileName, mode);
                int blockSize = Integer.parseInt(venus.getTam());
                byte[] buf;
                while ((buf = viceReader.read(blockSize)) != null) {
                    file.write(buf);
                }
                file.close();
                viceReader.close();
                file = new RandomAccessFile(cacheDir + fileName, mode);
            }
        } else if (mode.equals("r")) {
            file = new RandomAccessFile(cacheDir + fileName, mode);
        }

    }

    public int read(byte[] b) throws RemoteException, IOException {
        return file.read(b);
    }

    public void write(byte[] b) throws RemoteException, IOException {
        file.write(b);
    }

    public void seek(long p) throws RemoteException, IOException {
        file.seek(p);
    }

    public void setLength(long l) throws RemoteException, IOException {
        file.setLength(l);
    }

    public void close() throws RemoteException, IOException {
        if (this.mode.equals("rw")) {
            ViceWriter viceWriter = venus.getsrvVice().upload(this.fileName, this.mode);
            int blockSize = Integer.parseInt(venus.getTam());
            long tam = file.length();
            byte[] buf = new byte[blockSize];
            for (long t = 0; t < tam; t += blockSize) {
                file.read(buf, (int)t, blockSize);
                viceWriter.write(buf);
            }
            viceWriter.close();
        }
        file.close();
    }

    private boolean existeEnCache(String filename) {
        File f = new File(cacheDir);
        File[] files = f.listFiles();
        for (File file : files) {
            if (file.getName().equals(filename)) {
                return true;
            }
        }
        return false;
    }
}

