package com.example.Lab1;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentRepository documentRepository;

    public DocumentController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @GetMapping
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));
    }

    @PostMapping
    public Document createDocument1(@RequestBody Document document) {
        return documentRepository.save(document);
    }

    @DeleteMapping("/{id}")
    public void deleteDocument2(@PathVariable Long id) {
        documentRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Document updateDocument(@PathVariable Long id, @RequestBody Document updatedDocument) {
        return documentRepository.findById(id)
                .map(document -> {
                    document.setName(updatedDocument.getName());
                    document.setDocumentType(updatedDocument.getDocumentType());
                    document.setBody(updatedDocument.getBody());
                    document.setCreationDate(updatedDocument.getCreationDate());
                    document.setSignatureDate(updatedDocument.getSignatureDate());
                    document.setUserLogin(updatedDocument.getUserLogin());
                    return documentRepository.save(document);
                })
                .orElseThrow(() -> new DocumentNotFoundException(id));
    }



    //Post-запит «Створити новий документ»:
    @PostMapping("/documents")
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        Document savedDocument = documentRepository.save(document);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDocument);
    }

    //Put-запит «Змінити документ»:
    @PutMapping("/documents/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable(value = "id") Long id,
                                                   @RequestBody Map<String, Object> updates) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));

        // Update document fields with new values
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();
            BeanUtils.setProperty(document, fieldName, fieldValue);
        }

        Document updatedDocument = documentRepository.save(document);
        return ResponseEntity.ok(updatedDocument);
    }

    //Delete-запит «Видалити документ»:
    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable(value = "id") Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));

        documentRepository.delete(document);
        return ResponseEntity.noContent().build();
    }

    //Get-запит, який повертає список документів, що належать певному користувачу:
    @GetMapping("/users/{userId}/documents")
    public ResponseEntity<List<Document>> getDocumentsByUser(@PathVariable(value = "userId") Long userId) {
        List<Document> documents = documentRepository.findByUserId(userId);
        return ResponseEntity.ok(documents);
    }

    //Get-запит, який повертає список підписаних, або не підписаних документів, за певним користувачем:
    @GetMapping("/users/{userId}/documents/signed")
    public ResponseEntity<List<Document>> getSignedDocumentsByUser(@PathVariable(value = "userId") Long userId,
                                                                   @RequestParam(value = "signed") boolean signed) {
        List<Document> documents = documentRepository.findByUserIdAndSigned(userId, signed);
        return ResponseEntity.ok(documents);
    }

    //Get-запит, який повертає список документів створених в певний проміжок дат (від - до):
    @GetMapping("/documents/created")
    public ResponseEntity<List<Document>> getDocumentsByCreationDate(@RequestParam(value = "from") LocalDate from,
                                                                     @RequestParam(value = "to") LocalDate to) {
        List<Document> documents = documentRepository.findByCreationDateBetween(from, to);
        return ResponseEntity.ok(documents);
    }



}

