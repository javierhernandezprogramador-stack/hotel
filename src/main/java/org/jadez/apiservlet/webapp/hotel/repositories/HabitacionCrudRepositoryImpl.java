package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.config.Repository;
import org.jadez.apiservlet.webapp.hotel.entity.Habitacion;
import org.jadez.apiservlet.webapp.hotel.entity.Image;

import java.sql.*;
import java.util.List;

@RepositoryJpa
@Repository
public class HabitacionCrudRepositoryImpl implements CrudRepositoryImage<Habitacion> {

    @Inject
    private EntityManager em;

    @Override
    public List<Habitacion> listar() throws SQLException {
        return em.createQuery("SELECT h FROM Habitacion h", Habitacion.class).getResultList();
    }

    @Override
    public Habitacion crear(Habitacion habitacion) throws SQLException {

        if(habitacion.getId() != null) {
            em.merge(habitacion);
        }else {
            em.persist(habitacion);
        }

        return habitacion;
    }

    @Override
    public Habitacion porId(Long id) throws SQLException {
        return em.find(Habitacion.class, id);
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        em.createQuery("UPDATE Habitacion h SET h.estado = :estado WHERE h.id = :id")
                .setParameter("estado", estado)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Image addImagen(Image image) {
        em.persist(image);
        return image;
    }

    @Override
    public void deleteImage(Long id, Image image) {
        em.createQuery("DELETE FROM Image i WHERE i.nombre = :nombre")
                .setParameter("nombre", image.getNombre())
                .executeUpdate();
    }

}
