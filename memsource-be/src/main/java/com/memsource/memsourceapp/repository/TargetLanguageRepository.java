package com.memsource.memsourceapp.repository;


import com.memsource.memsourceapp.domain.TargetLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TargetLanguageRepository extends JpaRepository<TargetLanguage, Long> {
    Optional<TargetLanguage> findByName(String name);
}
