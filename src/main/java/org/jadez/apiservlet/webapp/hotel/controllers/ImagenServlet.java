package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/image")
public class ImagenServlet extends HttpServlet {
    private String imagePath;

    @Override
    public void init() throws ServletException {
        imagePath = getServletContext().getInitParameter("upload.path");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("name");

        if(fileName == null || fileName.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el nombre de la imagen");
            return;
        }

        File file = new File(imagePath, fileName);
        if(!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String mime = getServletContext().getMimeType(file.getName());
        if(mime == null) {
            mime = "application/octet-stream";
        }

        resp.setContentType(mime);
        resp.setContentLengthLong(file.length());

        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
