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
        <span>GENERATOR</span>
    </div>
    <div class="right10_div">

    </div>
</div>
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
        var experimentList = data;
        var index;
        for (var ind in experimentList) {
            index = experimentList[ind].idGeneratorHistory;
            $('#mainTable tr:last').after(""
                    + "<tr id='idGH" + index + "'>"
                    + "<td>" + experimentList[ind].idGeneratorHistory + "</td>"
                    + "<td>" + experimentList[ind].idGenerator + "</td>"
                    + "<td>" + experimentList[ind].idExperimentHistory + "</td>"
                    + "<td>" + experimentList[ind].idExperiment + "</td>"
                    + "<td>" + experimentList[ind].generatorType + "</td>"
                    + "<td>" + experimentList[ind].generatorName + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idGH" + index);
            $(document).on("click", "#idGH" + index, generatorHref);
        }
    }
    function getAjaxContent(sendData) {
        $.ajax({
            url: '${context}/ajax/getGeneratorHistoryList',
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
