package ru.mirea.uglovaa.pkmn.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import ru.mirea.uglovaa.pkmn.controllers.CardController;
import ru.mirea.uglovaa.pkmn.controllers.StudentController;
import ru.mirea.uglovaa.pkmn.entities.CardEntity;
import ru.mirea.uglovaa.pkmn.entities.StudentEntity;

import java.io.File;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataTesting {

    private final ObjectMapper objectMapper;
    private final CardController cardController;
    private final StudentController studentController;

    @PostConstruct
    @SneakyThrows
    public void init() {
        System.out.println("Post construct init");

        // Чтение данных из JSON файлов
        File jsonFile = new File("D:\\pkmn\\src\\main\\resources\\cards.json");
        CardEntity card = objectMapper.readValue(jsonFile, CardEntity.class);

        File jsonFile1 = new File("D:\\pkmn\\src\\main\\resources\\students.json");
        StudentEntity student = objectMapper.readValue(jsonFile1, StudentEntity.class);

        // Тестирование getCardsByOwner
        List<CardEntity> cardsByOwner = cardController.getCardsByOwner(student);
        System.out.println("Cards by Owner: " + cardsByOwner);

        // Тестирование createCard
        CardEntity createdCard = cardController.createCard(card).getBody();
        System.out.println("Created Card: " + createdCard);

        // Тестирование getCardImageByName
        String cardName = createdCard.getName();
        String imageUrl = cardController.getCardImageByName(cardName).getBody();
        if (imageUrl != null) {
            System.out.println("Image URL: " + imageUrl);
        } else {
            System.out.println("Image not found for card: " + cardName);
        }

        // Тестирование getAllCards
        List<CardEntity> allCards = cardController.getAllCards();
        System.out.println("All Cards: " + allCards);

        // Тестирование getCardById
        if (createdCard != null) {
            ResponseEntity<CardEntity> cardById = cardController.getCardById(createdCard.getId());
            System.out.println("Card by ID: " + cardById.getBody());
        }

        // Тестирование getAllStudents
        List<StudentEntity> allStudents = studentController.getAllStudents();
        System.out.println("All Students: " + allStudents);

        // Тестирование getStudentById
        if (student != null) {
            ResponseEntity<StudentEntity> studentById = studentController.getStudentById(createdCard.getPokemonOwner().getId());
            System.out.println("Student by ID: " + studentById.getBody());
        }

        StudentEntity createdStudent = student;

        // Тестирование getStudentsByGroup
        String group = "BSBO-04-23"; // Укажите вашу группу
        List<StudentEntity> studentsByGroup = studentController.getStudentsByGroup(group);
        System.out.println("Students by Group: " + studentsByGroup);

        System.out.println("Student by Full Name: " + studentController.getStudentByFullName(student));
    }
}
