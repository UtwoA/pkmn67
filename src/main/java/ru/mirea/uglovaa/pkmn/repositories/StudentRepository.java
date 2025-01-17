package ru.mirea.uglovaa.pkmn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.uglovaa.pkmn.entities.StudentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    List<StudentEntity> findByGroup(String group);

    List<StudentEntity> findByFirstNameAndSurNameAndFamilyName(String firstName, String surName, String familyName);}