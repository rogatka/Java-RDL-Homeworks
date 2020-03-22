package hometask;

import lombok.*;

@ToString
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Recruit {
    @NonNull private String name;
    private int age;
    @NonNull private Sex sex;
    @Setter private boolean isFit;
}
