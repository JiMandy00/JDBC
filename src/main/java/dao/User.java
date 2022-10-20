package dao;// dao.User class는 사용자 정보를 저장합니다.
// 사용자 정보에는 id, name, password가 있습니다.
// 그래서 private로 변수 id, name, password를 생성합니다.
// 매개변수가 없는, 있는 constructor를 생성합니다.
// dao.User Class는 id, name, password를 설정하고 불러올 수 있으므로 get, set method를 생성합니다.

public class User {
    private String id;
    private String name;
    private String password;

    public User() {
    }


    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

