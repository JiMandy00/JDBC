package dao;

import java.sql.*;
import java.util.Map;

public class UserDao {

    //보니까.. Class.fornName을 계속 사용해서 이걸 또 새로 만들어 줄 것이다.
    private ConnectionMaker connectionMaker;

    // UserDAo의 add method는 새로운 사용자를 생성하는 기능
    public void add(User user) throws ClassNotFoundException, SQLException {
        // 환경 변수를 담아와서 map에 담는다.
        // 보안 때문에 코드에 직접 입력하지 말고 변수로 받아오는 것을 추천합니다.
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbName = env.get("DB_NAME");
        String dbPassword = env.get("DB_PASSWORD");

        // Class.forName(); 드라이버 로드
        Class.forName("com.mysql.cj.jdbc.Driver");
        // DriverManager.getConnection(); 연결
        // DB를 들낙할 수 있는 변수 c
        // url은 ... 아마 localhost자리엔 port입력
        // user, password는 SQL wordkbench에 생성해둔 user, password를 입력
        Connection c = DriverManager.getConnection(dbHost, dbName, dbPassword);

        //SQL을 담을 prepareStatement를 생성해서 db(==c)에 있는 내용을 가져온다.
        PreparedStatement ps = c.prepareStatement (
                // db에 입력 될 sql문
                // 삽입하라 into users table에(id, name, password)를
                // value(?, ?, ?)는 printf처럼 사용한다고 생각
                "insert into users(id, name, password) value(?, ?, ?)");
        // SQL을 담을 ps에 setString method를 이용해서 value값을 담는다..

        // 첫번째 ?에 user.getId();
        // 두번째 ?에 user.getName();
        // 세번째 ?에 user.getPassword();
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(1, user.getPassword());

        // sql문 업데이트
        // .executeUpdate() : 데이터베이스에서 insert, delete, update SQL문을 실행
        ps.executeUpdate();

        // 선언한 역순으로 닫아주기
        ps.close();
        c.close();

    }
    // 아이디를 가지고 사용자 정보를 읽어주는 get method
    public User get(String id) throws SQLException, ClassNotFoundException {
        // 드라이버를 로드
        Class.forName("com.mysql.cj.jdbc.Driver");
        // db와 연결
        Connection c = DriverManager.getConnection(
                "jdbc:mywql://localhost:3306/likelion-db\", \"root\", \"password");
        // sql문을 담을 ps
        PreparedStatement ps = c.prepareStatement(
                // users table에서 id값을 모두 출력하라
                // 단일 ?는 value로 묶지 않아도 되나봅니다.
                "select * from users where id=?");
        ps.setString(1, id);

        // SQL 쿼리 실행 결과를 ResultSet으로 받아서 정보를 저장할  오브젝트(여기선 suer)에 옮겨준다.
        // .executeQuery(); : DB에서 데이터를 가져와서 결과 집합을 반환
        // Select문에서만 실행되는 특징이 있습니다.
        ResultSet rs = ps.executeQuery();
        // 다음 토큰을 문자열로 return
        rs.next();
        // dao.User 객체 생성
        User user = new User();
        // user.setId에 Select문 실행 결과를 저장? 대입?
        // sql문이 실행된 후 결과를 rs에 담았다. 그리고 그 결과에서 column의  값이 id인 것을 뽑아서 dao.User class의 setId에 담는다.
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        // 사용했던 명령어들 역순으로 닫아주기
        rs.close();
        ps.close();
        c.close();

        // 저장된 user정보
        return user;

    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "DELETE From users"
        );

        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public int getCountAll() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "SELECT COUNT(*) From users"
        );

        ResultSet rs = ps.executeQuery();
        rs.next();
        // 지정된 열 이름의 값을 int로 검색
        int count = rs.getInt(1);
        rs.close();
        ps.close();
        c.close();

        return count;

    }

    public User findById(String id) {
        return null; // 임시로 설정 해둠
    }
}
