package com.isthisteamisthis.lalalia.post.command.domain.repository;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommandRepository extends JpaRepository<Post, Long> {
}
