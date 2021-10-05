package repository;

import lombok.Getter;
import lombok.Setter;
import model.User;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class UserRepository {
    public static Map<String, User> userMap = new HashMap<>();
}
