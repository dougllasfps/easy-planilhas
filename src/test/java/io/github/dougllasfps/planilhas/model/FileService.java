package io.github.dougllasfps.planilhas.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Criado por dougllas.sousa em 23/01/2017.
 */
public class FileService {

    public synchronized void save(String path, byte[] data) throws IOException {

        BufferedOutputStream outputStream = null;
        File arquivo = new File(path);

        if(arquivo.getParent() != null){
            File dir = new File(arquivo.getParent());

            if (!dir.exists()) {
                dir.mkdirs();
            }
        }

        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(arquivo));
            outputStream.write(data);
        } finally {
            if(outputStream != null){
                outputStream.flush();
                outputStream.close();
            }
        }
    }
}
