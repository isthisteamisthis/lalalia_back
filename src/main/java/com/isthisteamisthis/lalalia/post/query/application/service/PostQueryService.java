package com.isthisteamisthis.lalalia.post.query.application.service;

import com.isthisteamisthis.lalalia.composesong.command.domain.aggregate.entity.ComposeSong;
import com.isthisteamisthis.lalalia.composesong.query.domain.repository.ComposeSongQueryRepository;
import com.isthisteamisthis.lalalia.post.command.application.dto.response.UserResponse;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.entity.Post;
import com.isthisteamisthis.lalalia.post.command.domain.aggregate.vo.UserNoVO;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetAllPostsResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetMyPagePostResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetPostResponse;
import com.isthisteamisthis.lalalia.post.query.application.dto.response.GetUserPostResponse;
import com.isthisteamisthis.lalalia.post.query.domain.repository.PostQueryRepository;
import com.isthisteamisthis.lalalia.post.query.infrastructure.service.ApiLikePostQueryService;
import com.isthisteamisthis.lalalia.post.query.infrastructure.service.ApiUserPostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostQueryRepository postQueryRepository;
    private final ApiLikePostQueryService apiLikePostQueryService;
    private final ApiUserPostQueryService apiUserPostQueryService;
    private final ComposeSongQueryRepository composeSongQueryRepository;

    // 사용자의 게시물 전체 조회
    @Transactional(readOnly = true)
    public List<GetUserPostResponse> getPostsByUserNo(UserResponse user) {

        Long userNo = user.getUserNo();
        // userNoVO 로 사용자의 전체 게시물 조회
        List<Post> postList = postQueryRepository.findPostsByUserNoVO(new UserNoVO(userNo));

        List<GetUserPostResponse> response = postList.stream()
                .map(GetUserPostResponse::from).collect(Collectors.toList());

        return response;

    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<GetAllPostsResponse> getAllPosts() {
        // 게시물 전체 조회 : 좋아요 내림차순
        List<Post> postList = postQueryRepository.findAllByOrderByLikeCntDesc();
        // 조회한 List<Post>를 List<GetAllPostsResponse>로 변환
        List<GetAllPostsResponse> response = postList.stream().map(
                post -> {

                    Long userNo = post.getUserNoVO().getUserNo();
                    // userNo 로 user 를 조회해서 nickname 가져오기
                    String nickname = apiUserPostQueryService.getNicknameByUserNo(userNo);

                    return GetAllPostsResponse.from(post, nickname);

                }).collect(Collectors.toList());

        return response;
    }

    // my page : 하나의 게시물 상세 조회
    @Transactional(readOnly = true)
    public GetMyPagePostResponse getMyPagePost(UserResponse user, Long postNo) {
        // post Id로 게시물 조회
        Post findPost = postQueryRepository.findByPostNo(postNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Post Id"));
        // 작성자와 요청자가 같은지 확인 : 다르면 예외처리
        if (!((findPost.getUserNoVO().getUserNo()).equals(user.getUserNo()))) {
            throw new IllegalArgumentException("Not Matched Writer!!");
        }
        // 사용자가 게시물에 좋아요를 했는지 여부
        boolean like = apiLikePostQueryService.getLike(user.getUserNo(), postNo);

        Optional<ComposeSong> optionalComposeSong = composeSongQueryRepository.findByComposeSongNo(findPost.getComposeSongVO().getComposeSongNo());
        ComposeSong composeSong = optionalComposeSong.get();

        return GetMyPagePostResponse.from(findPost, like, composeSong.getAiSongFile());
    }

    // community : 하나의 게시물 상세 조회
    @Transactional(readOnly = true)
    public GetPostResponse getCommunityPost(UserResponse user, Long postNo) {
        // post Id로 게시물 조회
        Post findPost = postQueryRepository.findByPostNo(postNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Post Id"));
        // 게시물 작성자가 본인인지 여부
        boolean isMe = (findPost.getUserNoVO().getUserNo()).equals(user.getUserNo());
        // 사용자가 게시물에 좋아요를 했는지 여부
        boolean like = apiLikePostQueryService.getLike(user.getUserNo(), postNo);

        Optional<ComposeSong> optionalComposeSong = composeSongQueryRepository.findByComposeSongNo(findPost.getComposeSongVO().getComposeSongNo());
        ComposeSong composeSong = optionalComposeSong.get();

        return GetPostResponse.from(findPost, isMe, like, composeSong.getAiSongFile());

    }
}
