package com.example.newsapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.newsapi.domain.News;

public interface NewsRepository extends JpaRepository<News, Long> {
	boolean existsByUrl(String url);

	Page<News> findByTitleContainingOrSummaryContaining(
			String titleKeyword,
			String summaryKeyword,
			Pageable pageable
			);

	@Query(
			value = """
					SELECT
					n.id,
					n.title,
					n.summary,

					(
					COALESCE(SUM(
					CASE
					WHEN n.title   LIKE CONCAT('%', ui.keyword, '%') THEN 3
					WHEN n.summary LIKE CONCAT('%', ui.keyword, '%') THEN 2
					ELSE 0
					END
					), 0)

					+

					COALESCE(SUM(
					CASE
					WHEN ua.action_type = 'CLICK' THEN 1
					WHEN ua.action_type = 'LIKE'  THEN 3
					END
					), 0)

					+

					CASE
					WHEN n.created_at >= NOW() - INTERVAL 1 DAY THEN 2
					ELSE 0
					END
					) AS total_score

					FROM news n
					LEFT JOIN user_interest ui
					ON ui.user_id = :userId
					LEFT JOIN user_actions ua
					ON ua.user_id = :userId
					AND ua.news_id = n.id
					GROUP BY n.id
					HAVING total_score > 0
					ORDER BY total_score DESC, n.created_at DESC
					LIMIT :limit
					""",
					nativeQuery = true
			)
	List<Object[]> findRecommendedNews(
			@Param("userId") Long userId,
			@Param("limit") int limit
			);
}
