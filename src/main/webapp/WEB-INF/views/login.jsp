<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>๋ก๊ทธ์ธ</title>
    <link href="/resources/css/login.css" media="all" rel="Stylesheet" type="text/css" />
    
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="/resources/js/login.js" type="text/javascript"></script>
    <script src="/resources/js/pwShowToggle.js" type="text/javascript"></script>
    <script src="/resources/js/guesthouse.js" type="text/javascript"></script>
</head>
<body>
<%@ include file="./header.jsp" %>
<c:choose>
    <c:when test="${user ne null}">
        <center>
            ๐ ์ด๋ฏธ ๋ก๊ทธ์ธ ์ค์๋๋ค.
        </center>
    </c:when>

    <c:otherwise>

        <form action="/loginNormal" method="POST" onsubmit="return blankCheck();">
            <div id = "mainbox">
                <h2>๋ก๊ทธ์ธ</h2>
                <table aria-describedby="loginTable">
                    <th id="loginTable"></th>
                    <tr><td class="inputHead">์์ด๋</td></tr>
                    <tr><td colspan="2" class="inputID" ><input type="text" id="userID" name="username" placeholder="please enter your id"></td></tr>
                    <tr><td class="inputHead">๋น๋ฐ๋ฒํธ</td></tr>
                    <tr>
                        <td class="inputPW"><input type="password" id="userPW" name="password" placeholder="please enter your password"></td>
                        <td class="inputPWShow"><input type="image" id="userPWShow" onclick="pwShowToggle(); return false;" src="resources/img/visibility.png" alt="show password"/></td>
                    </tr>
                        <%--                <tr>--%>
                        <%--                    <td><input type="radio" id="host" name = "user_type" value="HOST"><label for="host">ํธ์คํธ</label>--%>
                        <%--                    <input type="radio" id="user" name = "user_type" value="USER" ><label for="user"> ์ฌํ๊ฐ</label>--%>
                        <%--                    <input type="radio" id="admin" name = "user_type" value="ADMIN"><label for="admin"> ๊ด๋ฆฌ์</label>--%>
                        <%--                    </td>--%>
                        <%--                </tr>--%>
                    <tr></tr>
                    <tr><td colspan="2"><input class="inputButton" type="submit" value="๋ก๊ทธ์ธ"></td></tr>
                    <tr>
                        <td class="inputFinder"><a href="/login/findID">์์ด๋</a><a href="/login/findPW">/๋น๋ฐ๋ฒํธ ์ฐพ๊ธฐ</a></td>
                        <td class="inputRegister"><a href="javascript:checkPopup()">ํ์๊ฐ์</a></td>
                    </tr>
                </table>
                <div class="sns">
                    <a href="/oauth2/authorization/google"><img src="/resources/img/btn_google_signin_light.png"  style="width: 125px; height: 35px;" alt="๊ตฌ๊ธ ๋ก๊ทธ์ธ"></a>
                    <a href="/oauth2/authorization/naver"><img src="/resources/img/btn_naver_signin_official.png" alt="๋ค์ด๋ฒ ๋ก๊ทธ์ธ" style="width: 138px; height: 35px;"></a>
                    <a href="/oauth2/authorization/kakao"><img src="/resources/img/btn_kakao_signin_large_narrow.png" style="width: 122px; height: 35px;" alt="์นด์นด์ค ๋ก๊ทธ์ธ"></a>
                </div>
            </div>
        </form>

    </c:otherwise>

</c:choose>

<%--            <a href="/oauth2/authorization/google"><img src="/resources/img/btn_google_signin_light.png" alt="๊ตฌ๊ธ ๋ก๊ทธ์ธ" style="width: 55%; height: 55%"></a>--%>
<%@ include file="./footer.jsp" %>
</body>
</html>