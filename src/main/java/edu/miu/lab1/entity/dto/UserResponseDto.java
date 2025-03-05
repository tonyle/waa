package edu.miu.lab1.entity.dto;

import edu.miu.lab1.entity.Post;
import edu.miu.lab1.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private long id;
    private String name;
    private String email;
    private List<Role> roles;
    private List<Post> posts;
}
