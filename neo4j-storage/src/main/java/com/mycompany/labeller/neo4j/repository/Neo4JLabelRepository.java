package com.mycompany.labeller.neo4j.repository;

import com.mycompany.labeller.neo4j.Neo4JLabel;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ador
 */
public interface Neo4JLabelRepository extends Neo4jRepository<Neo4JLabel, Long> {
    
    @Query("MATCH (l) WHERE EXISTS(l.classifier_data) RETURN l")
    public Stream<Neo4JLabel> getByClassifiable();

    @Query("MATCH ()-[r:child_of]->(l: LLABEL {id: $id} DELETE r")
    public void unlink(@Param("id") long id);
    
    @Query("MATCH (l {name: $name}) return l")
    public Optional<Neo4JLabel> getByName(@Param("name") String name);
    
}
