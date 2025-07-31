package com.brodygaudel.demo.entity.settings;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "knowledge_base_websites", uniqueConstraints = @UniqueConstraint(columnNames = {"knowledge_base_id", "website"}))
public class KnowledgeBaseWebsite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "website_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "knowledge_base_id", nullable = false)
    private KnowledgeBase knowledgeBase;

    @Column(nullable = false)
    private String website;
}