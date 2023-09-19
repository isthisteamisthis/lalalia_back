package com.isthisteamisthis.lalalia.post.query.domain.repository;

import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostQueryRepository extends JpaRepository<Post, Long> {

    List<Post> findPostsByUserNoVO(UserNoVO userNoVO);

}
