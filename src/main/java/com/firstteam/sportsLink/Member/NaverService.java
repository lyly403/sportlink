package com.firstteam.sportsLink.Member;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class NaverService {
    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;
    private String accessToken;
    MemberDTO memberDTO = new MemberDTO();

//    public static void main(String[] args) {
//        String token = "YOUR_ACCESS_TOKEN"; // 네이버 로그인 접근 토큰;
//        String header = "Bearer " + token; // Bearer 다음에 공백 추가
//
//        String apiURL = "https://openapi.naver.com/v1/nid/me";
//
//        Map<String, String> requestHeaders = new HashMap<>();
//        requestHeaders.put("Authorization", header);
//        String responseBody = get(apiURL,requestHeaders);
//
//        System.out.println(responseBody);
//    }
//
//
//    private static String get(String apiUrl, Map<String, String> requestHeaders){
//        HttpURLConnection con = connect(apiUrl);
//        try {
//            con.setRequestMethod("GET");
//            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
//                con.setRequestProperty(header.getKey(), header.getValue());
//            }
//
//
//            int responseCode = con.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
//                return readBody(con.getInputStream());
//            } else { // 에러 발생
//                return readBody(con.getErrorStream());
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("API 요청과 응답 실패", e);
//        } finally {
//            con.disconnect();
//        }
//    }
//
//
//    private static HttpURLConnection connect(String apiUrl){
//        try {
//            URL url = new URL(apiUrl);
//            return (HttpURLConnection)url.openConnection();
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
//        } catch (IOException e) {
//            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
//        }
//    }
//
//
//    private static String readBody(InputStream body){
//        InputStreamReader streamReader = new InputStreamReader(body);
//
//
//        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
//            StringBuilder responseBody = new StringBuilder();
//
//
//            String line;
//            while ((line = lineReader.readLine()) != null) {
//                responseBody.append(line);
//            }
//
//
//            return responseBody.toString();
//        } catch (IOException e) {
//            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
//        }
//    }
    public void getAccessToken(String code) {
        String reqUrl = "https://nid.naver.com/oauth2.0/token";
        String reqParam = "grant_type=authorization_code";
        reqParam += "&client_id=p3ROwzA2JVE8fm6AJHhi";
        reqParam += "&client_secret=1JKnW9W0wf";
        reqParam += "&code="+code;

        try {
            URL url = new URL(reqUrl); // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST"); // POST 요청을 위해 기본값 false에서 setDoOutput을 true로 변경
            conn.setDoOutput(true); // POST 메소드를 이용해서 데이터를 전달하기 위한 설정
            // 기본 outputStream을 통해 문자열로 처리할 수 있는
            // OutPutStreamWriter 변환 후 처리속도를 빠르게 하기위해
            // BufferedWriter로 변환해서 사용한다.
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(conn.getOutputStream())
            );
            bw.write(reqParam);
            bw.flush();

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            ObjectMapper om = new ObjectMapper();
            Map<String, String> map = om.readValue(isr, new TypeReference<Map<String, String>>() {});
            accessToken = map.get("access_token");

            System.out.println("accessToken : " + map.get("access_token"));
            System.out.println(map);

                /*
                {
                    access_token=2rnZmwrzweTvbH6Mwh3JkLTW45AzGYMM6owKKiURAAABi6NeRkYicpf3YNJZ6g,
                    token_type=bearer,
                    refresh_token=Rn-dXWM81FHzgqUF6fVqjkH65W_UenSwIsQKKiURAAABi6NeRkQicpf3YNJZ6g,

                    id_token=eyJraWQiOiI5ZjI1MmRhZGQ1ZjIzM2Y5M2QyZmE1MjhkMTJmZWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.
                    eyJhdWQiOiI0ZDZjMTFmOTI2YWVkNzEyZTc0NTY1M2ViY2RlM2RhZiIsInN1YiI6IjMxNTAzOTg3NDMiLCJhdXRoX3RpbWUiOj
                    E2OTkyNTI5NDYsImlzcyI6Imh0dHBzOi8va2F1dGgua2FrYW8uY29tIiwibmlja25hbWUiOiLstZzsooXsmrQiLCJleHAiOjE2
                    OTkyNzQ1NDYsImlhdCI6MTY5OTI1Mjk0Nn0.VP9Cfjon03MXOC_XCuQTXF4lwEQbecK4wm6zz-dGaVzeUCIL1taQQ0vyJcJAU9
                    A0GNGRc3xos5KYBf9SCnw8SQLWDqToAuIXeGkSqq-ZetBO_oodAvSU50xfl8YMYwYokp3AeemowsHPeMh8zFnqvn7VJHq2Egfy
                    iRduzhEstWndsocRXj3b3i-0sF6i5wWPzon2r1qhig4NdawGtoTH5vQjzIs3wDzo0p7Veq-LbxlQjnc2bN_AYqev_FLGH9OHA7
                    askUfBSwGN58inCy0IDNC8BAw3Sl3qjurtnKn0otb1sP3EN3JAIPOQIv9uyCrJUiHVzjsGuPOQN0NE6B90dg,

                    expires_in=21599,
                    scope=openid profile_nickname,
                    refresh_token_expires_in=5183999
                }

                */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Autowired
    private HttpSession session;
    public MemberDTO getUserInfo() {
        String reqUrl = "https://openapi.naver.com/v1/nid/me";
        //	Authorization: Bearer ${ACCESS_TOKEN}

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer "+accessToken);

            int responseCode = conn.getResponseCode(); // 결과 코드가 200이라면 성공
            System.out.println("responseCode : " + responseCode);

            ObjectMapper om = new ObjectMapper();
            JsonNode jsonNode = om.readTree(conn.getInputStream());
            System.out.println(jsonNode.get("response"));
            System.out.println(jsonNode.get("response").get("name").get("email"));

            String userid = jsonNode.get("response").get("id").asText();
            String provider = "naver_";
            userid = provider + userid;
            String email = jsonNode.get("response").get("email").asText();
            String username = jsonNode.get("response").get("name").asText();

            memberDTO.setUserid(userid);
            memberDTO.setUsername(username);
            memberDTO.setEmail(email);
            memberDTO.setCreate_date(LocalDate.now());
//            memberDTO.setRole("user");

            if (!memberService.isMemberExists(userid)) {
                memberService.registerNewMember(memberDTO);
            }
            Optional<MemberEntity> check = Optional.ofNullable(memberRepository.findByUserid(userid));
            session.setAttribute("userid", check.get().getUserid());
            session.setAttribute("username", check.get().getUsername());
            session.setAttribute("email", check.get().getEmail());
            session.setAttribute("mobile", check.get().getMobile());
            session.setAttribute("role", check.get().getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberDTO;
		/*
		responseCode : 200
		{
			"profile_nickname_needs_agreement":false,
			"profile_image_needs_agreement":true,
			"profile":
			{"nickname":"최종운"}
		}
		"최종운"
		 */
    }
}
