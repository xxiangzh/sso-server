import com.xzh.sso.utils.TokenUtils;
import io.jsonwebtoken.Claims;

/**
 * @author 向振华
 * @date 2020/11/06 15:06
 */
public class TokenTest {

    public static void main(String[] args) {
        Claims claims = TokenUtils.getClaims("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjo0LFwidXNlcm5hbWVcIjpcInFxXCJ9IiwiZXhwIjoxNjA0OTc4NjAxLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjE2MDdkNTE2LWYyZmEtNGQwZS1iZGExLWY1NzdkY2E1NTRkNyIsImNsaWVudF9pZCI6Im9yZGVyLWNsaWVudC1pZCIsInNjb3BlIjpbImFsbCJdfQ.H405RKtHL2_dFD3zfzyNsefsTdsystfOWyHSbh8TvlA");
        String subject = claims.getSubject();
        System.out.println(claims);
        System.out.println(subject);

    }
}
