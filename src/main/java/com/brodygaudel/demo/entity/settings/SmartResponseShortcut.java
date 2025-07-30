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
@Table(name = "smart_response_shortcuts", uniqueConstraints = @UniqueConstraint(columnNames = {"smart_response_id", "shortcut"}))
public class SmartResponseShortcut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shortcut_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "smart_response_id", nullable = false)
    private SmartResponse smartResponse;

    @Column(nullable = false, length = 100)
    private String shortcut;
}