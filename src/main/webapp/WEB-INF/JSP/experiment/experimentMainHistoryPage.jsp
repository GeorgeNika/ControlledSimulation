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
        <img src="${context}/resources/images/button_back.png" alt="setup" class="round_button"
             onclick="window.location.href='${context}/historyMainPage'">
    </div>
    <div class="center80_div" id="info_header">
        <span>EXPERIMENT</span>
    </div>
    <div class="right10_div">

    </div>
</div>
<div class="common_table">
    <table id="mainTable">
        <tr>
            <td width="5%">id history</td>
            <td width="5%">id experiment</td>
            <td width="7%">type</td>
            <td width="28%">Name</td>
            <td width="5%">generator quantity</td>
            <td width="10%">execute date</td>
            <td width="10%">start time</td>
            <td width="10%">end time</td>
            <td width="10%">last work time</td>
            <td width="5%">log</td>
            <td width="5%">xml</td>

        </tr>
    </table>
</div>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function downloadLogFile(event) {
        var tempId = $(event.target).attr('id');
        var searchId = tempId.substring(4, tempId.length);
        window.open("${context}/downloadLogFile/"+searchId);
    }
    function downloadXmlFile(event) {
        var tempId = $(event.target).attr('id');
        var searchId = tempId.substring(4, tempId.length);
        window.open("${context}/downloadXmlFile/"+searchId);
    }
    function experimentHref(data) {
        var tempId = $(data.target).parent().attr('id');
        var searchId = tempId.substring(4, tempId.length);
        window.location.href = '${context}/experimentHistoryPage/' + searchId;
    }
    function useObtainedData(data) {
        $("#mainTable").find("tr:gt(0)").remove();
        var experimentList = data;
        var index;
        for (var ind in experimentList) {
            index = experimentList[ind].idExperimentHistory;
            $('#mainTable tr:last').after(""
                    + "<tr id='idEH" + index + "'>"
                    + "<td>" + experimentList[ind].idExperimentHistory + "</td>"
                    + "<td>" + experimentList[ind].idExperiment + "</td>"
                    + "<td>" + experimentList[ind].experimentType + "</td>"
                    + "<td>" + experimentList[ind].experimentName + "</td>"
                    + "<td>" + experimentList[ind].generatorQuantity + "</td>"
                    + "<td>" + experimentList[ind].executeDate + "</td>"
                    + "<td>" + experimentList[ind].startTime + "</td>"
                    + "<td>" + experimentList[ind].endTime + "</td>"
                    + "<td>" + experimentList[ind].lastPoint + "</td>"
                    + "<td>" + "<img src='${context}/resources/images/button_download_log.png' alt='log' "
                    + "id='idDL" + index + "'>" + "</td>"
                    + "<td>" + "<img src='${context}/resources/images/button_download_xml.png' alt='log' "
                    + "id='idDX" + index + "'>" + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idEH" + index);
            $(document).on("click", "#idEH" + index, experimentHref);

            $(document).off("click", "#idDL" + index);
            $(document).on("click", "#idDL" + index, downloadLogFile);
            $("#idDL" + index).addClass('round_button');

            $(document).off("click", "#idDX" + index);
            $(document).on("click", "#idDX" + index, downloadXmlFile);
            $("#idDX" + index).addClass('round_button');
        }
    }
    function getAjaxContent(sendData) {
        $.ajax({
            url: '${context}/ajax/getExperimentHistoryList',
            type: 'POST',
            datatype: 'json',
            data: sendData,
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
