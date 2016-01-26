<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_modal_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body class="whole_setup_page">
<jsp:include page="../header/generalHeader.jsp"/>
<jsp:include page="../header/setupHeader.jsp"/>
<div class="full_line_header">
    <div class="left10_div">
        <img src="${context}/resources/images/button_back.png" alt="setup" class="round_button"
             onclick="window.location.href='${context}/setupMainPage'">
    </div>
    <div class="center80_div" id="info_header">
        <span>EXPERIMENT</span>
    </div>
    <div class="right10_div">
        <img src="${context}/resources/images/button_create_new.png" alt="add" class="round_button"
             onclick="chooseExperimentType()">
    </div>
</div>
<div class="common_table">
    <table id="mainTable">
        <tr>
            <td width="5%">№</td>
            <td width="10%">type</td>
            <td width="35%">Name</td>
            <td width="10%">start time</td>
            <td width="10%">end time</td>
            <td width="10%">step interval (ms)</td>
            <td width="10%">created</td>
            <td width="10%">updated</td>
        </tr>
    </table>
</div>
<div hidden>
    <button id="start_small_modal_window"></button>
</div>
<div id="modal_form_small" hidden class="common_table" style="overflow: auto">
    <table id="experimentSelectTable">
        <tr>
            <td>
                Experiment Type
            </td>
        </tr>
    </table>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function selectExperimentType(data) {
        var tempId = $(data.target).parent().attr('id');
        var selectedType = tempId.substring(4, tempId.length);
        $.ajax({
            url: '${context}/ajax/createExperiment',
            type: 'POST',
            datatype: 'json',
            data: {experimentType: selectedType},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
        $('#overlay').click();
    }
    function useObtainedDataForSelectExperimentType(data) {
        var experimentList = data;
        $("#experimentSelectTable").find("tr:gt(0)").remove();
        for (var ind in experimentList) {
            $('#experimentSelectTable tr:last').after(""
                    + "<tr id='idGT" + experimentList[ind] + "'>"
                    + "<td>" + experimentList[ind] + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idGT" + experimentList[ind]);
            $(document).on("click", "#idGT" + experimentList[ind], selectExperimentType);
        }
        $('#start_small_modal_window').click();
    }
    function chooseExperimentType(data) {
        $.ajax({
            url: '${context}/ajax/getExperimentTypeList',
            type: 'POST',
            datatype: 'json',
            data: {},
            success: function (response) {
                useObtainedDataForSelectExperimentType(response);
            }
        });
    }
    function experimentHref(data) {
        var tempId = $(data.target).parent().attr('id');
        var searchId = tempId.substring(3, tempId.length);
        window.location.href = '${context}/experimentSetupPage/' + searchId;
    }
    function useObtainedData(data) {
        $("#mainTable").find("tr:gt(0)").remove();
        var experimentList = data;
        for (var ind in experimentList) {

            index = experimentList[ind].idExperiment;

            $('#mainTable tr:last').after(""
                    + "<tr id='idE" + index + "'>"
                    + "<td>" + experimentList[ind].idExperiment + "</td>"
                    + "<td>" + experimentList[ind].experimentType + "</td>"
                    + "<td>" + experimentList[ind].experimentName + "</td>"
                    + "<td>" + experimentList[ind].startTime + "</td>"
                    + "<td>" + experimentList[ind].endTime + "</td>"
                    + "<td>" + experimentList[ind].stepIntervalMs + "</td>"
                    + "<td>" + experimentList[ind].created + "</td>"
                    + "<td>" + experimentList[ind].updated + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idE" + index);
            $(document).on("click", "#idE" + index, experimentHref);
        }
    }
    function getAjaxContent(sendData) {
        $.ajax({
            url: '${context}/ajax/getExperimentList',
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
        $('#setup_header').textfill({maxFontPixels: 70});
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
