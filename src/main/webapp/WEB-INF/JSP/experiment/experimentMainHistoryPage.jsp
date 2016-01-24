<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_modal_v01.css" type="text/css">

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
        <img src="${context}/resources/images/button_filter.png" alt="filter" class="round_button"
             onclick="openFilterWindow()">
    </div>
</div>
<div class="common_table">
    <table id="mainTable">
        <tr>
            <td width="5%">id history</td>
            <td width="5%">id experiment</td>
            <td width="5%">type</td>
            <td width="25%">Name</td>
            <td width="5%">generator quantity</td>
            <td width="10%">execute date</td>
            <td width="10%">start time</td>
            <td width="10%">end time</td>
            <td width="10%">last work time</td>
            <td width="5%">log</td>
            <td width="5%">xml</td>
            <td width="5%">del</td>

        </tr>
    </table>
</div>
<div hidden>
    <button id="start_big_modal_window"></button>
</div>
<div id="modal_form_big" hidden style="overflow: auto">
    <div>
        Experiment Filter
    </div>
    <div class="common_table">
        <table id="busStartInfo">
            <tr>
                <td> type</td>
                <td> value</td>
            </tr>
            <tr>
                <td> id experiment</td>
                <td>
                    <input id='filterIdExperiment' type='number' min='0' step="1" value='0'/>
                    <button type="button" onclick="$('#filterIdExperiment').val('0');">X</button>
                </td>
            </tr>
            <tr>
                <td> id experiment history</td>
                <td>
                    <input id='filterIdExperimentHistory' type='number' min='0' step="1" value='0'/>
                    <button type="button" onclick="$('#filterIdExperimentHistory').val('0');">X</button>
                </td>
            </tr>
        </table>
    </div>
    <div class="space10_div"></div>
    <div class="space30_div">
        <div class="left33_div"></div>
        <div class="center34_div">
            <img src="${context}/resources/images/button_filter.png" alt="filter" class="round_button"
                 onclick="setFilter()">
        </div>
    </div>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function setFilter() {
        var tempStr = $('#filterIdExperiment').val().replace(/[^\d.]/g, '');
        if (tempStr == '') {
            tempStr = 0;
        }
        $('#filterIdExperiment').val(parseInt(tempStr, 10));
        var tempStr = $('#filterIdExperimentHistory').val().replace(/[^\d.]/g, '');
        if (tempStr == '') {
            tempStr = 0;
        }
        $('#filterIdExperimentHistory').val(parseInt(tempStr, 10));
        $('#overlay').click();
        getAjaxContent();
    }
    function openFilterWindow() {
        $('#start_big_modal_window').click();
    }
    function deleteHistory(event) {
        var tempId = $(event.target).attr('id');
        var searchId = tempId.substring(4, tempId.length);
        $.ajax({
            url: '${context}/ajax/deleteExperimentHistory',
            type: 'POST',
            datatype: 'json',
            data: {idExperimentHistory: searchId},
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication or Experiment is still running.');
                }
                getAjaxContent();
            }
        });
    }
    function downloadLogFile(event) {
        var tempId = $(event.target).attr('id');
        var searchId = tempId.substring(4, tempId.length);
        window.open("${context}/downloadLogFile/" + searchId);
    }
    function downloadXmlFile(event) {
        var tempId = $(event.target).attr('id');
        var searchId = tempId.substring(4, tempId.length);
        window.open("${context}/downloadXmlFile/" + searchId);
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
                    + "<td>" + "<img src='${context}/resources/images/button_del.png' alt='del' "
                    + "id='idDH" + index + "'>" + "</td>"
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

            $(document).off("click", "#idDH" + index);
            $(document).on("click", "#idDH" + index, deleteHistory);
            $("#idDH" + index).addClass('round_button');
        }
    }
    function getAjaxContent(sendData) {
        $.ajax({
            url: '${context}/ajax/getExperimentHistoryListByFilter',
            type: 'POST',
            datatype: 'json',
            data: {
                filterIdExperiment: parseInt($('#filterIdExperiment').val(), 10),
                filterIdExperimentHistory: parseInt($('#filterIdExperimentHistory').val(), 10)
            },
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
