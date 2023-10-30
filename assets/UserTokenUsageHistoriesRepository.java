package kr.co.mz.aoai.webchatadminserver.users.repository;

import kr.co.mz.aoai.webchatadminserver.users.dto.response.UserTokenUsageHistoryProjection;
import kr.co.mz.aoai.webchatadminserver.users.entity.UserTokenUsageHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserTokenUsageHistoriesRepository extends
    JpaRepository<UserTokenUsageHistoryEntity, Integer> {

  @Query(value = """
          SELECT t.id, t.user_id as userId, u.email, u.name as userName,
                 t.created_at as createdAt, t.question, t.year_month as yearMonth,
                 t.token_usage as tokenUsage
            FROM users u
           INNER JOIN user_token_usage_histories t on u.id = t.user_id
           WHERE (:userId IS NULL OR u.id = :userId)
             AND (:email IS NULL OR u.email = :email)
             AND (:userName IS NULL OR u.name = :userName)
             AND (:yearMonth IS NULL OR t.year_month = :yearMonth)
      """,
      countQuery = """
          SELECT COUNT(*)
            FROM users u
           INNER JOIN user_token_usage_histories t on u.id = t.user_id
           WHERE (:userId IS NULL OR u.id = :userId)
             AND (:email IS NULL OR u.email = :email)
             AND (:userName IS NULL OR u.name = :userName)
             AND (:yearMonth IS NULL OR t.year_month = :yearMonth)
          """,
      nativeQuery = true)
  Page<UserTokenUsageHistoryProjection> searchBy(
      @Param("userId") Integer userId,
      @Param("email") String email,
      @Param("userName") String userName,
      @Param("yearMonth") String yearMonth,
      Pageable pageable
  );

  @Query(value = """
          SELECT u.id,
                 u.email,
                 u.name as userName,
                 T.yearMonth,
                 t.aggregatedTokenUsage
          FROM users u
                   INNER JOIN (SELECT t.user_id          as userId,
                                      t.year_month       as yearMonth,
                                      sum(t.token_usage) as aggregatedTokenUsage
                               FROM user_token_usage_histories t
                               WHERE (:userId IS NULL OR t.user_id = :userId)
                                 AND (:yearMonth IS NULL OR t.year_month = :yearMonth)
                               GROUP BY t.user_id, t.year_month) T on u.id = T.userId
          WHERE (:email IS NULL OR u.email = :email)
            AND (:userName IS NULL OR u.name = :userName)
      """,
      countQuery = """
          SELECT COUNT(*)
          FROM users u
                   INNER JOIN (SELECT t.user_id          as userId,
                                      t.year_month       as yearMonth,
                                      sum(t.token_usage) as aggregatedTokenUsage
                               FROM user_token_usage_histories t
                               WHERE (:userId IS NULL OR t.user_id = :userId)
                                 AND (:yearMonth IS NULL OR t.year_month = :yearMonth)
                               GROUP BY t.user_id, t.year_month) T on u.id = T.userId
          WHERE (:email IS NULL OR u.email = :email)
            AND (:userName IS NULL OR u.name = :userName)
          """,
      nativeQuery = true)
  Page<UserTokenUsageHistoryProjection> searchAggreatedTokenUsageBy(
      @Param("userId") Integer userId,
      @Param("email") String email,
      @Param("userName") String userName,
      @Param("yearMonth") String yearMonth,
      Pageable pageable
  );
}
