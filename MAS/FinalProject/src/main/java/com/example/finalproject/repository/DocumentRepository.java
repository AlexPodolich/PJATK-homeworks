package com.example.finalproject.repository;

import com.example.finalproject.model.Document;
import com.example.finalproject.model.Employment;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long> {
}
