package com.example.Lab2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
class Lab2ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateDocument() {
		// створити об'єкт документа
		DocumentDto document = new DocumentDto("Назва документа", DocumentType.LETTER, "Текст документа", LocalDate.now(), null, "user");

		// виконати POST-запит до ендпоінту
		ResponseEntity<DocumentDto> response = restTemplate.postForEntity("/documents", document, DocumentDto.class);

		// перевірити статус-код відповіді
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		// перевірити, що повернутий об'єкт містить id
		assertNotNull(response.getBody().getId());

		// зберегти id документа для подальшого використання
		Long documentId = response.getBody().getId();

		// отримати документ з бази даних та перевірити, що його поля співпадають з оригінальними даними
		DocumentDto savedDocument = restTemplate.getForObject("/documents/{id}", DocumentDto.class, documentId);
		assertEquals(documentrestTemplate.delete("/documents/{id}", documentId);
	}
}
