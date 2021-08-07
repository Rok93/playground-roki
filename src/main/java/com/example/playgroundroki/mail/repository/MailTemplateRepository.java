package com.example.playgroundroki.mail.repository;

import com.example.playgroundroki.mail.domain.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailTemplateRepository extends JpaRepository<MailTemplate, Long> {
}
