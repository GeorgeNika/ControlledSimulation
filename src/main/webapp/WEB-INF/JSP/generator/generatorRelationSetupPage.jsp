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
        <img src="${context}/resources/images/button_back.png" alt="back" class="round_button"
             onclick="window.location.href='${context}/generatorPage/${generator.idGenerator}'">
    </div>
    <div class="center80_div" id="info_header">
        <span>GENERATOR RELATION</span>
    </div>
    <div class="right10_div">
        <img src="${context}/resources/images/button_add.png" alt="add" class="round_button"
             onclick="addRelatedGeneratorData()">
    </div>
</div>
<div class="common_table">
    <table id="mainTable">
        <tr>
            <td width="10%">№ position</td>
            <td width="10%">type</td>
            <td width="10%">related id</td>
            <td width="40%">related name</td>
            <td width="10%">delay (ms)</td>
            <td width="5%">edit</td>
            <td width="5%">up</td>
            <td width="5%">down</td>
            <td width="5%">del</td>
        </tr>
    </table>
</div>
<div hidden>
    <button id="start_big_modal_window"></button>
</div>
<div id="modal_form_big" hidden class="common_table" style="overflow: auto">
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
    var workPosition;
    function addRelatedGeneratorData() {
        workPosition = 0;
        chooseGeneratorType();
    }
    function editRelatedGeneratorData() {
        var tempId = $(event.target).attr('id');
        var searchPosition = tempId.substring(3, tempId.length);
        workPosition = searchPosition;
        chooseGeneratorType();
    }
    function selectGenerator(data) {
        var tempId = $(data.target).parent().attr('id');
        var selectedRelatedId = tempId.substring(3, tempId.length);
        if (workPosition == 0) {
            $.ajax({
                url: '${context}/ajax/createRelatedGeneratorData',
                type: 'POST',
                datatype: 'json',
                data: {idGenerator: ${generator.idGenerator}, idRelatedGenerator: selectedRelatedId},
                success: function (response) {
                    if (response != true) {
                        $('html').html(response);
                    }
                    getAjaxContent();
                }
            });
        } else {
            urlAddress = '${context}/ajax/updateRelatedGeneratorData';
            $.ajax({
                url: '${context}/ajax/updateRelatedGeneratorData',
                type: 'POST',
                datatype: 'json',
                data: {
                    idGenerator: ${generator.idGenerator},
                    idRelatedGenerator: selectedRelatedId,
                    position: workPosition
                },
                success: function (response) {
                    if (response != true) {
                        $('html').html(response);
                    }
                    getAjaxContent();
                }
            });
        }

        $('#overlay').click();
    }
    function useObtainedDataForSelectGenerator(data) {
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
            $(document).on("click", "#idG" + index, selectGenerator);
        }
        $('#start_big_modal_window').click();
    }
    function chooseGeneratorType() {
        $.ajax({
            url: '${context}/ajax/getGeneratorList',
            type: 'POST',
            datatype: 'json',
            data: {},
            success: function (response) {
                useObtainedDataForSelectGenerator(response);
            }
        });
    }
    function delRelation(event) {
        var tempId = $(event.target).attr('id');
        var searchPosition = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/deleteRelatedGeneratorData',
            type: 'POST',
            datatype: 'json',
            data: {idGenerator: ${generator.idGenerator}, position: searchPosition},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
    }
    function relationUp(event) {
        var tempId = $(event.target).attr('id');
        var searchPosition = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/upRelatedGeneratorData',
            type: 'POST',
            datatype: 'json',
            data: {idGenerator: ${generator.idGenerator}, position: searchPosition},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
    }
    function relationDown(event) {
        var tempId = $(event.target).attr('id');
        var searchPosition = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/downRelatedGeneratorData',
            type: 'POST',
            datatype: 'json',
            data: {idGenerator: ${generator.idGenerator}, position: searchPosition},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
    }
    function changeDelay(event) {
        var tempId = $(event.target).attr('id');
        var searchPosition = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/setDelayForRelatedGeneratorData',
            type: 'POST',
            datatype: 'json',
            data: {idGenerator: ${generator.idGenerator}, position: searchPosition, delayMs: $(event.target).val()},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
    }
    function useObtainedData(data) {
        $("#mainTable").find("tr:gt(0)").remove();
        var RGDataList = data;
        var index;
        for (var ind in RGDataList) {
            index = RGDataList[ind].relatedPosition;
            $('#mainTable tr:last').after(""
                    + "<tr>"
                    + "<td>" + RGDataList[ind].relatedPosition + "</td>"
                    + "<td>" + RGDataList[ind].relatedGeneratorType + "</td>"
                    + "<td>" + RGDataList[ind].idRelatedGenerator + "</td>"
                    + "<td>" + RGDataList[ind].relatedGeneratorName + "</td>"
                    + "<td>" + "<input type='number' value='" + RGDataList[ind].delayMs + "' id='idT" + index + "'>"
                    + "<td>" + "<img src='${context}/resources/images/button_ed.png' alt='edit' "
                    + "id='idE" + index + "'>" + "</td>"
                    + "<td>" + "<img src='${context}/resources/images/button_up.png' alt='up' "
                    + "id='idU" + index + "'>" + "</td>"
                    + "<td>" + "<img src='${context}/resources/images/button_down.png' alt='down' "
                    + "id='idD" + index + "'>" + "</td>"
                    + "<td>" + "<img src='${context}/resources/images/button_del.png' alt='delete' "
                    + "id='idX" + index + "'>" + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idE" + index);
            $(document).on("click", "#idE" + index, editRelatedGeneratorData);
            $("#idE" + index).addClass('round_button');
            $(document).off("click", "#idU" + index);
            $(document).on("click", "#idU" + index, relationUp);
            $("#idU" + index).addClass('round_button');
            $(document).off("click", "#idD" + index);
            $(document).on("click", "#idD" + index, relationDown);
            $("#idD" + index).addClass('round_button');
            $(document).off("click", "#idX" + index);
            $(document).on("click", "#idX" + index, delRelation);
            $("#idX" + index).addClass('round_button');
            $("#idT" + index).attr('onchange', '').change(changeDelay);
        }
    }
    function getAjaxContent() {
        $.ajax({
            url: '${context}/ajax/getRelatedGeneratorDataList',
            type: 'POST',
            datatype: 'json',
            data: {idGenerator: ${generator.idGenerator}},
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
