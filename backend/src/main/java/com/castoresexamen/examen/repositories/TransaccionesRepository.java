package com.castoresexamen.examen.repositories;

import com.castoresexamen.examen.models.TransaccionesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionesRepository extends JpaRepository<TransaccionesModel, Long> {
    List<TransaccionesModel> findByTipo(String tipo);
    List<TransaccionesModel> findAllByOrderByFechaTransaccionAsc();
    List<TransaccionesModel> findByTipoOrderByFechaTransaccionAsc(String tipo);
}