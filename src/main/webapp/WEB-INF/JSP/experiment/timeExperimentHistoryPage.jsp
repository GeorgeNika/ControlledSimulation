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
<div class="full_line_header">
    <div class="left10_div">
        <img src="${context}/resources/images/button_back.png" alt="back" class="round_button"
             onclick="window.location.href='${context}/experimentMainHistoryPage'">
    </div>
    <div class="center80_div" id="info_header">
        <span>${experimentHistory.idExperimentHistory} - ${experimentHistory.experimentName}</span>
    </div>
</div>
<div class="space5_div"></div>
<div class="common_table">
    <table>
        <tr>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td width="30%">id experiment - ${experimentHistory.idExperiment}</td>
            <td width="30%">start time - ${lightExperimentHistory.startTime}</td>
            <td width="40%">execute date - ${lightExperimentHistory.executeDate}</td>
        </tr>
        <tr>
            <td>experiment type - ${experimentHistory.experimentType}</td>
            <td>end time - ${lightExperimentHistory.endTime}</td>
            <td>log file name - ${experimentHistory.logFile}</td>
        </tr>
        <tr>
            <td>generator quantity - ${experimentHistory.generatorQuantity}</td>
            <td>last point - ${lightExperimentHistory.lastPoint}</td>
            <td>xml file name - ${experimentHistory.xmlFile}</td>
        </tr>
    </table>
</div>
<div class="space5_div"></div>
<div class="common_table">
    <table>
        <tr>
            <td>from</td>
            <td>to</td>
            <td>time</td>
        </tr>
        <tr>
            <td width="30%">start id generator - ${experimentHistory.idStartGenerator}</td>
            <td width="30%">end id generator - ${experimentHistory.idEndGenerator}</td>
            <td width="40%">min time - ${experimentHistory.minTimeMs}</td>
        </tr>
    </table>
</div>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>

    function adaptiveTextSize() {
        $('#general_header').textfill({maxFontPixels: 70});
        $('#history_header').textfill({maxFontPixels: 70});
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
