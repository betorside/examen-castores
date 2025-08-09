package com.castoresexamen.examen.repositories;

import com.castoresexamen.examen.models.ProductosModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<ProductosModel, Long> {
    List<ProductosModel> findByEstatus(Integer estatus);
}