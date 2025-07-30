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
@Table(name = "smart_response_websites", uniqueConstraints = @UniqueConstraint(columnNames = {"smart_response_id", "website"}))
public class SmartResponseWebsite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "website_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "smart_response_id", nullable = false)
    private SmartResponse smartResponse;

    @Column(nullable = false, length = 255)
    private String website;
}