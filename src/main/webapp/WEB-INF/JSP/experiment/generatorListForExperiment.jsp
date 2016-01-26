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
<body class="whole_setup_page">
<jsp:include page="../header/generalHeader.jsp"/>
<jsp:include page="../header/setupHeader.jsp"/>
<div class="full_line_header">
    <div class="left10_div">
        <img src="${context}/resources/images/button_back.png" alt="back" class="round_button"
             onclick="window.location.href='${context}/experimentSetupPage/${idExperiment}'">
    </div>
    <div class="center80_div" id="info_header">
        <span>GENERATOR LIST FOR EXPERIMENT</span>
    </div>
    <div class="right10_div">
        <img src="${context}/resources/images/button_add.png" alt="add" class="round_button"
             onclick="chooseGeneratorForAddToList()">
    </div>
</div>
<div class="space5_div"></div>
<div class="common_table">
    <table id="mainTable">
        <tr>
            <td width="10%">№</td>
            <td width="10%">type</td>
            <td width="45%">Name</td>
            <td width="10%">entity type</td>
            <td width="10%">id entity info</td>
            <td width="10%">quantity entity</td>
            <td width="5%">del</td>
        </tr>
    </table>
</div>
<div hidden>
    <button id="start_big_modal_window"></button>
</div>
<div id="modal_form_big" hidden class="common_table" style="overflow: auto">
    Generator List
    <table id="generatorSelectTable">
        <tr>
            <td width="10%">№</td>
            <td width="10%">type</td>
            <td width="50%">Name</td>
            <td width="10%">entity type</td>
            <td width="10%">id entity info</td>
            <td width="10%">quantity entity</td>
        </tr>
    </table>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function addGeneratorToExperimentList(data) {
        var tempId = $(data.target).parent().attr('id');
        var selectedId = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/addGeneratorToExperimentList',
            type: 'POST',
            datatype: 'json',
            data: {idExperiment: ${idExperiment}, idGenerator: selectedId},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
        $('#overlay').click();
    }
    function useObtainedDataForNewGenerator(data) {
        var generatorList = data;
        $("#generatorSelectTable").find("tr:gt(0)").remove();
        var index;
        for (var ind in generatorList) {
            index = generatorList[ind].idGenerator;
            $('#generatorSelectTable tr:last').after(""
                    + "<tr id='idG" + index + "'>"
                    + "<td>" + generatorList[ind].idGenerator + "</td>"
                    + "<td>" + generatorList[ind].generatorType + "</td>"
                    + "<td>" + generatorList[ind].generatorName + "</td>"
                    + "<td>" + generatorList[ind].entityType + "</td>"
                    + "<td>" + generatorList[ind].idEntity + "</td>"
                    + "<td>" + generatorList[ind].entityQuantity + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idG" + index);
            $(document).on("click", "#idG" + index, addGeneratorToExperimentList);
        }
        $('#start_big_modal_window').click();
    }
    function chooseGeneratorForAddToList() {
        $.ajax({
            url: '${context}/ajax/getNonUsedGeneratorListForExperiment',
            type: 'POST',
            datatype: 'json',
            data: {idExperiment: ${idExperiment}},
            success: function (response) {
                useObtainedDataForNewGenerator(response);
            }
        });
    }
    function deleteGeneratorFromList(event) {
        var tempId = $(event.target).attr('id');
        var searchedId = tempId.substring(3, tempId.length);
        alert
        $.ajax({
            url: '${context}/ajax/deleteGeneratorFromExperimentList',
            type: 'POST',
            datatype: 'json',
            data: {idExperiment: ${idExperiment}, idGenerator: searchedId},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent(response);
            }
        });
    }
    function useObtainedData(data) {
        $("#mainTable").find("tr:gt(0)").remove();
        var generatorList = data;
        var index;
        for (var ind in generatorList) {
            index = generatorList[ind].idGenerator;
            $('#mainTable tr:last').after(""
                    + "<tr id='idG" + index + "'>"
                    + "<td>" + generatorList[ind].idGenerator + "</td>"
                    + "<td>" + generatorList[ind].generatorType + "</td>"
                    + "<td>" + generatorList[ind].generatorName + "</td>"
                    + "<td>" + generatorList[ind].entityType + "</td>"
                    + "<td>" + generatorList[ind].idEntity + "</td>"
                    + "<td>" + generatorList[ind].entityQuantity + "</td>"
                    + "<td>" + "<img src='${context}/resources/images/button_del.png' alt='delete' "
                    + "id='idX" + index + "'>" + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idX" + index);
            $(document).on("click", "#idX" + index, deleteGeneratorFromList);
            $("#idX" + index).addClass('round_button');
        }
    }
    function getAjaxContent() {
        $.ajax({
            url: '${context}/ajax/getGeneratorListForExperiment',
            type: 'POST',
            datatype: 'json',
            data: {idExperiment: ${idExperiment}},
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
