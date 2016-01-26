<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body class="whole_run_page">
<jsp:include page="../header/generalHeader.jsp"/>
<jsp:include page="../header/runHeader.jsp"/>
<div class="full_line_header">
    <div class="left10_div">
        <img src="${context}/resources/images/button_back.png" alt="back" class="round_button"
             onclick="window.location.href='${context}/experimentRunPage/${generatorHistory.idExperimentHistory}'">
    </div>
    <div class="center70_div" id="info_header">
        <span> ${generatorHistory.idExperimentHistory} -
               ${generatorHistory.idGeneratorHistory} -
               ${generatorHistory.generatorType} :
               ${generatorHistory.generatorName}
        </span>
    </div>
    <div class="right20_div">
        <div class="left33_div">
            <img src="${context}/resources/images/button_run.png" alt="run" class="round_button" id="run_button"
                 onclick="runExperiment()">
        </div>
        <div class="center34_div">
            <img src="${context}/resources/images/button_pause.png" alt="pause" class="round_button" id="pause_button"
                 onclick="pauseExperiment()">
        </div>
        <div class="right33_div">
            <img src="${context}/resources/images/button_stop.png" alt="stop" class="round_button"
                 onclick="stopExperiment()">
        </div>
    </div>
</div>
<div class="space5_div" id="info_string">
    <span>Current time - <span id="currentTime">current time  -----  no info -----</span></span>
</div>
<div class="space5_div"></div>
<div class="full_line_header">
    <div class="left10_div"> </div>
    <div class="center80_div" id="current_state_size">
        <span id="current_state_string">no info</span>
    </div>
</div>
<div class="full_line_header">
    <div class="left10_div"> </div>
    <div class="center80_div" id="global_state_size">
        <span id="global_state_string">no info</span>
    </div>
</div>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function runExperiment(event) {
        $.ajax({
            url: '${context}/ajax/continueExperiment',
            type: 'POST',
            datatype: 'json',
            data: {idExperimentHistory: ${generatorHistory.idExperimentHistory}},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
        event.stopPropagation();
    }
    function pauseExperiment(event) {
        $.ajax({
            url: '${context}/ajax/pauseExperiment',
            type: 'POST',
            datatype: 'json',
            data: {idExperimentHistory: ${generatorHistory.idExperimentHistory}},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
        event.stopPropagation();
    }
    function stopExperiment(event) {
        $.ajax({
            url: '${context}/ajax/stopExperiment',
            type: 'POST',
            datatype: 'json',
            data: {idExperimentHistory: ${generatorHistory.idExperimentHistory}},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
        event.stopPropagation();
    }


    function useObtainedData(data) {
        $("#currentTime").html(data.currentDateTime);
        if (data.paused) {
            $("#run_button").attr('hidden', false);
            $("#pause_button").attr('hidden', true);
        } else {
            $("#run_button").attr('hidden', true);
            $("#pause_button").attr('hidden', false);
        }
        $("#current_state_string").html(data.currentState);
        $("#global_state_string").html(data.globalState);
        adaptiveInfoString();
    }
    function getAjaxContent() {
        $.ajax({
            url: '${context}/ajax/getStationGeneratorRunInfo',
            type: 'POST',
            datatype: 'json',
            data: {
                idExperimentHistory: ${generatorHistory.idExperimentHistory},
                idGeneratorHistory: ${generatorHistory.idGeneratorHistory}
            },
            success: function (response) {
                useObtainedData(response);
                setTimeout(getAjaxContent, 2000);
            }
        });
    }
    function adaptiveInfoString(){
        $('#current_state_size').textfill({maxFontPixels: 70});
        $('#global_state_size').textfill({maxFontPixels: 70});
    }
    function adaptiveTextSize() {
        $('#general_header').textfill({maxFontPixels: 70});
        $('#run_header').textfill({maxFontPixels: 70});
        $('#info_header').textfill({maxFontPixels: 70});
        $('#info_string').textfill({maxFontPixels: 70});
        adaptiveInfoString();
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
