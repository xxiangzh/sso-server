import com.xzh.sso.utils.TokenUtils;
import io.jsonwebtoken.Claims;

/**
 * @author 向振华
 * @date 2020/11/06 15:06
 */
public class TokenTest {

    public static void main(String[] args) {
        Claims claims = TokenUtils.getClaims("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjoxLFwidXNlcm5hbWVcIjpcImFkbWluXCJ9IiwiZXhwIjoxNjA0OTk3MzAwLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjA5Yjg1YThkLTM1NTQtNDFiMS1hYTI3LTkxZDliN2EwNzVlYiIsImNsaWVudF9pZCI6Im9yZGVyLWNsaWVudC1pZCIsInNjb3BlIjpbImFsbCJdfQ.QAq98BEzj-CpSpcbv5x_EXSmZ-0dTCr3KMcLIpPo8zc");
        String subject = claims.getSubject();
        System.out.println(claims);
        System.out.println(subject);

    }
}
