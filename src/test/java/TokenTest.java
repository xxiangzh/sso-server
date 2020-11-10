import com.xzh.sso.utils.TokenUtils;
import io.jsonwebtoken.Claims;

/**
 * @author 向振华
 * @date 2020/11/06 15:06
 */
public class TokenTest {

    public static void main(String[] args) {
        Claims claims = TokenUtils.getClaims("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDQ5NzI4ODQsInVzZXJJZCI6MiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJiNWY1MzE2ZC0wMzQ2LTQ3ODgtYjg2MC1lNGQwMWY2MTdkMTgiLCJjbGllbnRfaWQiOiJvcmRlci1jbGllbnQtaWQiLCJzY29wZSI6WyJhbGwiXX0.1BEPc6WWtDIMsMnsNwlhMNNcet_ueRvULOlHwsnTBb4");
        String subject = claims.getSubject();
        System.out.println(claims);
        System.out.println(subject);
    }
}
