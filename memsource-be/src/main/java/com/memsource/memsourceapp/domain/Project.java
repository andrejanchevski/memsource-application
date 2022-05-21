package com.memsource.memsourceapp.domain;

import com.memsource.memsourceapp.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false, unique = true)
    private Long externalId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "source_language", nullable = false)
    private String sourceLanguage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "projects_target_languages",
    joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "target_language_id"))
    private List<TargetLanguage> targetLanguages = new ArrayList<>();

    @Column(name = "project_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ProjectStatus projectStatus;

    private String description;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

}
