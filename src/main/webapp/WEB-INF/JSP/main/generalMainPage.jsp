<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body>

<div class="whole_setup_page">
    <jsp:include page="../header/generalHeader.jsp"/>
    <div class="space10_div"></div>
    <div class="space20_div"></div>
    <div class="space20_div"></div>
    <div class="space35_div">
        <div class="left33_div">
            <img src="${context}/resources/images/button_setup.png" alt="setup" class="round_button"
                 onclick="window.location.href='${context}/setupMainPage'">
        </div>
        <div class="center34_div">
            <img src="${context}/resources/images/button_execute.png" alt="run" class="round_button"
                 onclick="window.location.href='${context}/runMainPage'">
        </div>
        <div class="right33_div">
            <img src="${context}/resources/images/button_history.png" alt="history" class="round_button"
                 onclick="window.location.href='${context}/historyMainPage'">
        </div>
    </div>
</div>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function adaptiveTextSize() {
        $('#general_header').textfill({maxFontPixels: 70});
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
