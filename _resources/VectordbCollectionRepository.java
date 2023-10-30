package kr.co.mz.aoai.webchatadminserver.vectordb.repository;

import kr.co.mz.aoai.webchatadminserver.vectordb.dto.request.SearchVectordbCollectionRequest;
import kr.co.mz.aoai.webchatadminserver.vectordb.entity.VectordbCollectionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VectordbCollectionRepository extends
    JpaRepository<VectordbCollectionEntity, String> {

  @Query(value = """
            SELECT vc
              FROM VectordbCollectionEntity vc
              JOIN vc.vectordbCollectionFileEntities vcf
             WHERE (:#{#request.uuid()} IS NULL OR vc.uuid = :#{#request.uuid()})
             AND (:#{#request.collectionName()} IS NULL OR vc.collectionName LIKE CONCAT('%', :#{#request.collectionName()},'%'))
             AND (:#{#request.displayName()} IS NULL OR vc.displayName LIKE CONCAT('%', :#{#request.displayName()},'%'))
             AND (:#{#request.botType()} IS NULL OR vc.botType LIKE CONCAT('%', :#{#request.botType()},'%'))
             AND (:#{#request.description()} IS NULL OR vc.description LIKE CONCAT('%', :#{#request.description()},'%'))
             AND (:#{#request.fromCreatedTime()} IS NULL OR vc.createdTime >= :#{#request.fromCreatedTime()})
             AND (:#{#request.toCreatedTime()} IS NULL OR vc.createdTime < :#{#request.toCreatedTime()})
             AND (:#{#request.originalFilename()} IS NULL OR vcf.originalFilename LIKE CONCAT('%', :#{#request.originalFilename()},'%'))
             AND (:#{#request.storedFilename()} IS NULL OR vcf.storedFilename LIKE CONCAT('%', :#{#request.storedFilename()},'%'))
      """)
  Page<VectordbCollectionEntity> searchBy(@Param("request") SearchVectordbCollectionRequest request,
      Pageable pageable);
}