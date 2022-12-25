package com.leadexperience.xspy.models.tables;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContactTable {

    @Id
    @SequenceGenerator(name = "victim_contact_sequence", sequenceName = "victim_contact_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "victim_contact_sequence")
    private Long id;

    private String contactName;

    private String contactNumber;
    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
