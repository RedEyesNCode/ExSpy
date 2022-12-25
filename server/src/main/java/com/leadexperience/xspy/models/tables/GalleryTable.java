package com.leadexperience.xspy.models.tables;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GalleryTable {



    @Id
    @SequenceGenerator(name = "victim_gallery_sequence", sequenceName = "victim_gallery_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "victim_gallery_sequence")
    private Long id;

    private String imageUrl;

    private String victimName;





}
