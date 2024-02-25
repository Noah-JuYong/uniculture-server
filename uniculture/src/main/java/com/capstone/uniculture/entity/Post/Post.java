package com.capstone.uniculture.entity.Post;

import com.capstone.uniculture.dto.Post.PostUpdateDto;
import com.capstone.uniculture.entity.BaseEntity;
import com.capstone.uniculture.entity.Member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PostType posttype;

    private Integer likeCount = 0;

    private Integer commentCount = 0;

    private String title;

    private String content;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Photo> photos = new ArrayList<>();

    // 게시물의 주인 표시
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id",nullable = false)
    private Member member;

    public Post(Member member, String title, String content) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    //연관관계 메소드
    public void addComment(Comment comment){
        comment.setPost(this);
        this.commentCount += 1;
    }

    public void setMember(Member newMember){
        this.member = newMember;
        newMember.getPost().add(this);
    }

    public void removeComment(){
        this.commentCount -= 1;
    }

    //편의 메소드
    public void likePost(){
        this.likeCount += 1;
    }

    public void unlikePost(){
        this.likeCount -= 1;
    }

    public void update(PostUpdateDto postUpdateDto) {
        this.title = postUpdateDto.getTitle();
        this.content = postUpdateDto.getContents();
        this.posttype = postUpdateDto.getPosttype();

    }
}
