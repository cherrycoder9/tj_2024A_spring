package example.day09.todo;


import lombok.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@ToString @Getter @Setter
public class TodoDto {
    private int tno;
    private String tcontent;
    private int tstate;
}
