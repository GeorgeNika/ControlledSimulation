<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_modal_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/jquery.datetimepicker.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body class="whole_setup_page">
<jsp:include page="../header/generalHeader.jsp"/>
<jsp:include page="../header/setupHeader.jsp"/>
<div class="full_line_header">
    <div class="left10_div">
        <img src="${context}/resources/images/button_back.png" alt="back" class="round_button"
             onclick="window.location.href='${context}/experimentMainSetupPage'">
    </div>
    <div class="center80_div" id="info_header">
        <span>REPEAT EXPERIMENT</span>
    </div>
    <div class="right10_div">
        <img src="${context}/resources/images/button_delete.png" alt="del" class="round_button"
             onclick="deleteExperiment()">
    </div>
</div>
<div class="div70_with_overflow">
    <div class="space5_div"></div>

    <div class="common_table">
        <table>
            <tr>
                <td> Data</td>
            </tr>
            <tr>
                <td>
                    <form:form method="POST" action="${context}/editExperimentAction/${experiment.idExperiment}"
                               commandName="experimentMainForm">
                        <table>
                            <tr>
                                <h2> Edit experiment id - ${experiment.idExperiment} :: name
                                    - ${experiment.experimentName}</h2>
                            </tr>
                            <tr>
                                <td>Name</td>
                                <td><form:input path="experimentName"/></td>
                                <td>Start time</td>
                                <td><form:input path="startTime" id="startTime"/></td>
                            </tr>
                            <tr>
                                <td>step interval (ms)</td>
                                <td><form:input path="stepIntervalMs" type='number' min='0'/></td>
                                <td>End time</td>
                                <td><form:input path="endTime" id="endTime"/></td>
                            </tr>
                            <tr>
                                <td colspan="4" style="text-align:match-parent;">
                                    <input type="submit" class="simple_button" value="Save"/>
                                </td>
                            </tr>
                        </table>
                    </form:form>
                </td>

            </tr>
        </table>
    </div>
    <div>
        <h3></h3>

        <div class="left20_div">
            <div class="space35_div">
                <img src="${context}/resources/images/button_change_experiment_type.png" alt="change"
                     class="round_button"
                     onclick="changeExperimentType()">

            </div>
        </div>
        <div class="center60_div">
            <div class="common_table">
                <table>
                    <tr>
                        <td> Data</td>
                    </tr>
                    <tr>
                        <td>
                            <form:form method="POST"
                                       action="${context}/editRepeatExperimentExtraDataAction/${experiment.idExperiment}"
                                       commandName="repeatExperimentExtraDataForm">
                                <table>
                                    <tr><h6></h6></tr>
                                    <tr>
                                        <td>Repeat quantity</td>
                                        <td><form:input path="repeat" type='number' min='0'/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="text-align:match-parent;">
                                            <input type="submit" class="simple_button" value="Save"/>
                                        </td>
                                    </tr>
                                </table>
                            </form:form>
                        </td>

                    </tr>
                </table>
            </div>
        </div>
        <div class="right20_div">
            <div class="space35_div">
                <img src="${context}/resources/images/button_generator_list.png" alt="list"
                     class="round_button"
                     onclick="window.location.href='${context}/generatorListForExperiment/${experiment.idExperiment}'">
            </div>
        </div>
    </div>
</div>
<div hidden>
    <button id="start_small_modal_window"></button>
</div>
<div id="modal_form_small" hidden class="common_table" style="overflow: auto">
    <table id="experimentTypeSelectTable">
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
<script src="${context}/resources/js/jquery.datetimepicker.js"></script>
<script>
    function selectExperimentType(data) {
        var tempId = $(data.target).parent().attr('id');
        var selectedType = tempId.substring(4, tempId.length);
        $.ajax({
            url: '${context}/ajax/changeExperimentType',
            type: 'POST',
            datatype: 'json',
            data: {idExperiment: ${experiment.idExperiment}, experimentType: selectedType},
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication.');
                    window.location.href = '${context}/experimentMainSetupPage';
                } else {
                    window.location.href = '${context}/experimentSetupPage/${experiment.idExperiment}';
                }
                $('#overlay').click();
            }
        });
    }
    function useObtainedDataForSelectExperimentType(data) {
        var experimentTypeList = data;
        $("#experimentTypeSelectTable").find("tr:gt(0)").remove();
        for (var ind in experimentTypeList) {
            $('#experimentTypeSelectTable tr:last').after(""
                    + "<tr id='idET" + experimentTypeList[ind] + "'>"
                    + "<td>" + experimentTypeList[ind] + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idET" + experimentTypeList[ind]);
            $(document).on("click", "#idET" + experimentTypeList[ind], selectExperimentType);
        }
        $('#start_small_modal_window').click();
    }
    function changeExperimentType() {
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
    function deleteExperiment() {
        $.ajax({
            url: '${context}/ajax/deleteExperiment',
            type: 'POST',
            datatype: 'json',
            data: {idExperiment: ${experiment.idExperiment}},
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication.');
                } else {
                    window.location.href = '${context}/experimentMainSetupPage';
                }
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
        $('#startTime').datetimepicker();
        $('#endTime').datetimepicker();

    });
    $(window).resize(function () {
        adaptiveTextSize();
    })
</script>
</body>
</html>



