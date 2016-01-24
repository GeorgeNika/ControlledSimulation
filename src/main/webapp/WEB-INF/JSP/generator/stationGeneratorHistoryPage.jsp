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
             onclick="window.location.href='${context}/generatorMainHistoryPage'">
    </div>
    <div class="center80_div" id="info_header">
        <span> ${generatorHistory.idExperimentHistory} -
               ${generatorHistory.idGeneratorHistory} -
               ${generatorHistory.generatorType} :
               ${generatorHistory.generatorName}
        </span>
    </div>
</div>
<div class="space10_div"></div>
<div class="common_table">
    <table>
        <tr>
            <td width="40%"></td>
            <td width="60%"></td>
        </tr>
        <tr>
            <td>id experiment - ${generatorHistory.idExperiment}</td>
            <td>send entity - ${generatorHistory.sendEntity}</td>
        </tr>
        <tr>
            <td>id generator - ${generatorHistory.idGenerator}</td>
            <td>receive entity - ${generatorHistory.receiveEntity}</td>
        </tr>
        <tr>
            <td>create entity - ${generatorHistory.createEntity}</td>
            <td>destroy entity - ${generatorHistory.destroyEntity}</td>
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
