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
<div class="space5_div"> </div>
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
<div class="space5_div"> </div>
<div class="common_table">
    <table id="mainTable">
        <tr>
            <td width="10%">id gen history</td>
            <td width="10%">id generator</td>
            <td width="10%">id exp history</td>
            <td width="10%">id experiment</td>
            <td width="10%">gen type</td>
            <td width="50%">gen name</td>
        </tr>
    </table>
</div>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function generatorHref(data) {
        var tempId = $(data.target).parent().attr('id');
        var searchId = tempId.substring(4, tempId.length);
        window.location.href = '${context}/generatorHistoryPage/' + searchId;
    }
    function useObtainedData(data) {
        $("#mainTable").find("tr:gt(0)").remove();
        var generatorList = data;
        var index;
        for (var ind in generatorList) {
            index = generatorList[ind].idGeneratorHistory;
            $('#mainTable tr:last').after(""
                    + "<tr id='idGH" + index + "'>"
                    + "<td>" + generatorList[ind].idGeneratorHistory + "</td>"
                    + "<td>" + generatorList[ind].idGenerator + "</td>"
                    + "<td>" + generatorList[ind].idExperimentHistory + "</td>"
                    + "<td>" + generatorList[ind].idExperiment + "</td>"
                    + "<td>" + generatorList[ind].generatorType + "</td>"
                    + "<td>" + generatorList[ind].generatorName + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idGH" + index);
            $(document).on("click", "#idGH" + index, generatorHref);
        }
    }
    function getAjaxContent() {
        $.ajax({
            url: '${context}/ajax/getGeneratorHistoryListByExperiment',
            type: 'POST',
            datatype: 'json',
            data: {idExperimentHistory : ${experimentHistory.idExperimentHistory}},
            success: function (response) {
                useObtainedData(response);
            }
        });
    }
    function adaptiveTextSize() {
        $('#general_header').textfill({maxFontPixels: 70});
        $('#history_header').textfill({maxFontPixels: 70});
        $('#info_header').textfill({maxFontPixels: 70});
    }
    $(document).ready(function () {
        adaptiveTextSize();
        getAjaxContent();
    });
    $(window).resize(function () {
        adaptiveTextSize();
    })
</script>
</body>
</html>
