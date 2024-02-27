package com.blogging.app.payloads;

import com.blogging.app.enitities.Category;
import com.blogging.app.enitities.Comment;
import com.blogging.app.enitities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;

    private Set<CommentsDto>comments=new HashSet<>();


    private CategoryDto category;
    private UserDto user;

}
