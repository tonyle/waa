package edu.miu.lab1.entity.dto;

import edu.miu.lab1.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private List<Integer> roleIds;
    private List<Post> posts;
}
