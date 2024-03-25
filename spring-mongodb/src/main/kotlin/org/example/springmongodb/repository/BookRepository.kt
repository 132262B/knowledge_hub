package org.example.springmongodb.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface BookRepository : MongoRepository<Book, String>