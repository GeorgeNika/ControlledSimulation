<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body>

<div class="whole_setup_page">
    <jsp:include page="../header/generalHeader.jsp"/>
    <div class="space10_div"></div>
    <div class="space20_div"></div>
    <div class="common_table">
        <table>
            <form:form method="POST" action="${context}/loginAction" commandName="loginForm">
                <tr>
                    <td colspan="2"> L O G I N</td>
                </tr>
                <tr>
                    <td>User Name</td>
                    <td><form:input path="userName"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><form:input path="password" type='password'/></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align:match-parent;">
                        <input type="submit" class="simple_button" value="LOGIN"/>
                    </td>
                </tr>
            </form:form>
        </table>
    </div>
</div>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function adaptiveTextSize() {
        $('#general_header').textfill({maxFontPixels: 70});
        $('#setup_header').textfill({maxFontPixels: 70});
        $('#info_header').textfill({maxFontPixels: 70});
    }
    $(document).ready(function () {
        adaptiveTextSize();

    });
    $(window).resize(function () {
        adaptiveTextSize();
    })
</script>
</body>
</html>
