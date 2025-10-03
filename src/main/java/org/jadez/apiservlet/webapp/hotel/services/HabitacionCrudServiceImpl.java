package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.entity.Habitacion;
import org.jadez.apiservlet.webapp.hotel.entity.Image;
import org.jadez.apiservlet.webapp.hotel.repositories.CrudRepositoryImage;
import org.jadez.apiservlet.webapp.hotel.repositories.RepositoryJpa;
import org.jadez.apiservlet.webapp.hotel.utils.SaveImage;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Stateless
public class HabitacionCrudServiceImpl implements CrudServiceImageService<Habitacion> {
    @Inject
    @RepositoryJpa
    private CrudRepositoryImage<Habitacion> repository;

    @Inject
    private SaveImage saveImage;

    @Override
    public List<Habitacion> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Habitacion crear(Habitacion habitacion) {
        try {
            return repository.crear(habitacion);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Habitacion> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {
        try {
            repository.updateEstado(id, estado);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addImagen(MultipartFormDataInput input, Habitacion habitacion) {
        try {
            List<String> nombreArchivos =  saveImage.saveImage(input);
            for(String name: nombreArchivos) {

                Image img = new Image();
                img.setNombre(name);
                img.setHabitacion(habitacion);

                repository.addImagen(img);
            }

        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminarImagen(Long id, List<Image> imagenes) {
        try {
            for(Image i: imagenes) {
                saveImage.eliminarImagen(i.getNombre());
                repository.deleteImage(id, i);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
