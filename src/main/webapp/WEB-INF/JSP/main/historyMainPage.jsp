<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body class="whole_history_page">
<jsp:include page="../header/generalHeader.jsp"/>
<jsp:include page="../header/historyHeader.jsp"/>
<div class="space20_div"></div>
<div class="space20_div"></div>
<div class="space30_div">
    <div class="left33_div">
        <img src="${context}/resources/images/button_experiment.png" alt="experiment" class="round_button"
             onclick="window.location.href='${context}/experimentMainHistoryPage'">
    </div>
    <div class="center34_div">
        <img src="${context}/resources/images/button_generator.png" alt="generator" class="round_button"
             onclick="window.location.href='${context}/generatorMainHistoryPage'">
    </div>
    <div class="right33_div">
        <img src="${context}/resources/images/button_entity.png" alt="entity" class="round_button"
             id="image_entity" onclick="window.location.href='${context}/entityMainHistoryPage'">
    </div>
</div>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function adaptiveTextSize() {
        $('#general_header').textfill({maxFontPixels: 70});
        $('#history_header').textfill({maxFontPixels: 70});
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

