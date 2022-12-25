package com.leadexperience.xspy.respository;

import com.leadexperience.xspy.models.tables.GalleryTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VictimGalleryRepo extends JpaRepository<GalleryTable,Long> {
}
