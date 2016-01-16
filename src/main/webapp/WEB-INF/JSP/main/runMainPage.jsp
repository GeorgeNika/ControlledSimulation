<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_modal_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body class="whole_run_page">
<jsp:include page="../header/generalHeader.jsp"/>
<jsp:include page="../header/runHeader.jsp"/>
<div class="div70_with_overflow">
    <div class="common_table">
        <table id="mainTable">
            <tr>
                <td width="10%">№ history</td>
                <td width="10%">№ experiment</td>
                <td width="30%">experiment name</td>
                <td width="10%">experiment type</td>
                <td width="10%">start_time</td>
                <td width="10%">current time</td>
                <td width="10%">end time</td>
                <td width="5%">pause</td>
                <td width="5%">end</td>
            </tr>
        </table>
    </div>
    <div class="space5_div"></div>
    <div class="space30_div">
        <div class="left45_div">
            <img src="${context}/resources/images/button_db_run.png" alt="db_run" class="round_button"
                 onclick="chooseExperiment()">
        </div>
        <div class="right45_div">
            <img src="${context}/resources/images/button_xml_run.png" alt="xml_run" class="round_button"
                 onclick="chooseXmlFile()">
        </div>
    </div>
</div>

<div hidden>
    <button id="start_small_modal_window"></button>
</div>
<div id="modal_form_small" hidden class="common_table" style="overflow: auto">
    <div class="common_table">
        <table>
            <tr>
                <td> Upload xml file</td>
            </tr>
            <tr>
                <td>

                    <form action="${context}/uploadFile" method="post" enctype="multipart/form-data">
                        <br/>
                        <input type="file" name="file" id="multipartFile"/>
                        <br/>
                        <br/>
                        <input type="submit" class="simple_button" value="Upload file"/>
                        <br/>
                    </form>
                </td>

            </tr>
        </table>
    </div>
</div>

<div hidden>
    <button id="start_big_modal_window"></button>
</div>
<div id="modal_form_big" hidden class="common_table" style="overflow: auto">
    <table id="experimentSelectTable">
        <tr>
            <td width="10%">№</td>
            <td width="10%">type</td>
            <td width="50%">Name</td>
            <td width="10%">start time</td>
            <td width="10%">end time</td>
            <td width="10%">step interval (ms)</td>
        </tr>
    </table>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function chooseXmlFile() {
        $('#start_small_modal_window').click();
    }

    function selectExperiment(data) {
        var tempId = $(data.target).parent().attr('id');
        var idExperiment = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/runExperiment',
            type: 'POST',
            datatype: 'json',
            data: {idExperiment: idExperiment},
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication.');
                }
                getAjaxContent();
            }
        });
        $('#overlay').click();
    }
    function useObtainedDataForSelectExperiment(data) {
        var experimentList = data;
        $("#experimentSelectTable").find("tr:gt(0)").remove();
        var index;
        for (var ind in experimentList) {
            index = experimentList[ind].idExperiment;
            $('#experimentSelectTable tr:last').after(""
                    + "<tr id='idE" + index + "'>"
                    + "<td>" + experimentList[ind].idExperiment + "</td>"
                    + "<td>" + experimentList[ind].experimentType + "</td>"
                    + "<td>" + experimentList[ind].experimentName + "</td>"
                    + "<td>" + experimentList[ind].startTime + "</td>"
                    + "<td>" + experimentList[ind].endTime + "</td>"
                    + "<td>" + experimentList[ind].stepIntervalMs + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idE" + index);
            $(document).on("click", "#idE" + index, selectExperiment);
        }
        $('#start_big_modal_window').click();
    }
    function chooseExperiment() {
        $.ajax({
            url: '${context}/ajax/getExperimentList',
            type: 'POST',
            datatype: 'json',
            data: {},
            success: function (response) {
                useObtainedDataForSelectExperiment(response);
            }
        });
    }
    function runExperiment(event) {
        var tempId = $(event.target).attr('id');
        var id = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/continueExperiment',
            type: 'POST',
            datatype: 'json',
            data: {idExperimentHistory: id},
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication.');
                }
                getAjaxContent();
            }
        });
        event.stopPropagation();
    }
    function pauseExperiment(event) {
        var tempId = $(event.target).attr('id');
        var id = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/pauseExperiment',
            type: 'POST',
            datatype: 'json',
            data: {idExperimentHistory: id},
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication.');
                }
                getAjaxContent();
            }
        });
        event.stopPropagation();
    }
    function stopExperiment(event) {
        var tempId = $(event.target).attr('id');
        var id = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/stopExperiment',
            type: 'POST',
            datatype: 'json',
            data: {idExperimentHistory: id},
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication.');
                }
                getAjaxContent();
            }
        });
        event.stopPropagation();
    }
    function experimentHref(data) {
        var tempId = $(data.target).parent().attr('id');
        var searchId = tempId.substring(4, tempId.length);
        window.location.href = '${context}/experimentRunPage/' + searchId;
    }
    function useObtainedData(data) {
        $("#mainTable").find("tr:gt(0)").remove();
        var RunExpList = data;
        var index;
        var action;
        for (var ind in RunExpList) {
            index = RunExpList[ind].idExperimentHistory;
            if (RunExpList[ind].paused) {
                action = "<img src='${context}/resources/images/button_run.png' alt='run' " + "id='idR" + index + "'>"
            } else {
                action = "<img src='${context}/resources/images/button_pause.png' alt='pause' " + "id='idP" + index + "'>"
            }
            $('#mainTable tr:last').after(""
                    + "<tr id='idEH" + index + "'>"
                    + "<td>" + RunExpList[ind].idExperimentHistory + "</td>"
                    + "<td>" + RunExpList[ind].idExperiment + "</td>"
                    + "<td>" + RunExpList[ind].experimentName + "</td>"
                    + "<td>" + RunExpList[ind].experimentType + "</td>"
                    + "<td>" + RunExpList[ind].startDateTime + "</td>"
                    + "<td>" + RunExpList[ind].currentDateTime + "</td>"
                    + "<td>" + RunExpList[ind].endDateTime + "</td>"
                    + "<td>" + action + "</td>"
                    + "<td>" + "<img src='${context}/resources/images/button_stop.png' alt='stop' "
                    + "id='idS" + index + "'>" + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idEH" + index);
            $(document).on("click", "#idEH" + index, experimentHref);
            $(document).off("click", "#idP" + index);
            $(document).on("click", "#idP" + index, pauseExperiment);
            $("#idP" + index).addClass('round_button');
            $(document).off("click", "#idR" + index);
            $(document).on("click", "#idR" + index, runExperiment);
            $("#idR" + index).addClass('round_button');
            $(document).off("click", "#idS" + index);
            $(document).on("click", "#idS" + index, stopExperiment);
            $("#idS" + index).addClass('round_button');
        }
    }
    function getAjaxContent() {
        $.ajax({
            url: '${context}/ajax/getRunningExperimentList',
            type: 'POST',
            datatype: 'json',
            data: {},
            success: function (response) {
                useObtainedData(response);
                setTimeout(getAjaxContent, 2000);
            }
        });
    }
    function adaptiveTextSize() {
        $('#general_header').textfill({maxFontPixels: 70});
        $('#run_header').textfill({maxFontPixels: 70});
    }
    $(document).ready(function () {
        getAjaxContent();
        adaptiveTextSize();
    });
    $(window).resize(function () {
        adaptiveTextSize();
    })
</script>
</body>
</html>
