package org.jadez.apiservlet.webapp.hotel.utils;

import jakarta.enterprise.context.ApplicationScoped;
import org.jadez.apiservlet.webapp.hotel.services.ServiceException;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class SaveImage {

    private static final String UPLOAD_DIR = "C:/uploads/";

    public List<String> saveImage(MultipartFormDataInput input) {

        List<String> paths = new ArrayList<>();

        try {
            Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
            List<InputPart> inputParts = uploadForm.get("file");

            for (InputPart part : inputParts) {
                //Extraermos el nombre del archivo desde los headers
                String contentDisp = part.getHeaders().getFirst("Content-Disposition");
                String nombreArchivo = contentDisp.replaceAll(".*filename=\"([^\"]+)\".*", "$1");
                String pathImage = UUID.randomUUID().toString() + "_" + nombreArchivo;

                //Extraer el contenido
                InputStream is = part.getBody(InputStream.class, null);

                writeImage(is, pathImage);
                paths.add(pathImage);
            }

            return paths;

        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    private void writeImage(InputStream uploadedInputStream, String path) throws IOException {
        File file = new File(UPLOAD_DIR + path);
        File parent = file.getParentFile();

        if(parent != null) {
            parent.mkdirs();
        }

        try (OutputStream out = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = uploadedInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
    }

    public void eliminarImagen(String nombre) {
        try {
            Path path = Paths.get(UPLOAD_DIR + nombre);
            Files.delete(path);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
