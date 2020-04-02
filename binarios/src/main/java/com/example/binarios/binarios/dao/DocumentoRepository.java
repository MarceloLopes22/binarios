package com.example.binarios.binarios.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.binarios.binarios.basicas.Documento;

@Repository
public interface DocumentoRepository extends CrudRepository<Documento, Long>{

	Documento findDocumentoById(Long id);
	
}
