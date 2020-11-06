import com.xzh.sso.utils.TokenUtils;
import io.jsonwebtoken.Claims;

/**
 * @author 向振华
 * @date 2020/11/06 15:06
 */
public class TokenTest {

    public static void main(String[] args) {
        Claims claims = TokenUtils.getClaims("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDQ2NDc3MzcsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiI5ZTYxMWU5OS1lMDIyLTQ3ODMtOGQ2My1hYjlkN2ZiZjNmOTUiLCJjbGllbnRfaWQiOiJvcmRlci1jbGllbnQtaWQiLCJzY29wZSI6WyJhbGwiXX0.srWmQbZhWNXARxHSCz2bgqXjRUCsiym4yY5MENWBBR4");
        System.out.println(claims);
    }
}
