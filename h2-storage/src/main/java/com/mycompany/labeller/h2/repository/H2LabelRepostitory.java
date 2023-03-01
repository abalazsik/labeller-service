package com.mycompany.labeller.h2.repository;

import com.mycompany.labeller.h2.H2Label;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ador
 */
public interface H2LabelRepostitory extends Repository<H2Label, Long> {

    public static final String CLASSIFYABLE = "getByClassifiable";

    @Cacheable(CLASSIFYABLE)
    @Query("SELECT * FROM LLabel WHERE classifier_data IS NOT NULL OR classifier_data <> ''")
    public Stream<H2Label> getByClassifiable();

    @Query("SELECT * FROM LLabel ORDER BY name LIMIT :limit OFFSET :offset")
    public Stream<H2Label> getAll(@Param("offset") int offset, @Param("limit") int limit);

    public H2Label save(H2Label label);

    @CacheEvict(CLASSIFYABLE)
    @Modifying
    @Query("DELETE LLabel WHERE id = :id")
    public void delete(long id);

    @Modifying
    @Query("UPDATE LLabel set parent = NULL WHERE parent = :id")
    public void unlink(long id);

    @Query("SELECT * FROM LLabel WHERE id = :id")
    public Optional<H2Label> getById(long id);

    @CacheEvict(CLASSIFYABLE)
    @Modifying
    @Query("UPDATE LLabel set description = :description, name = :name, classifier_data = :classifier_data, technical = :technical, update_date = :update_date, parent = :parent WHERE id = :id")
    public void update(@Param("id") long id,
            @Param("description") String description,
            @Param("name") String name,
            @Param("classifier_data") String classifier_data,
            @Param("technical") boolean technical,
            @Param("update_date") LocalDateTime update_date,
            @Param("parent") Long parent);

    @Query("SELECT * FROM LLabel WHERE name = :name")
    public Optional<H2Label> getByName(@Param("name") String name);
}
