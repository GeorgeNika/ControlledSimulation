<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_modal_v01.css" type="text/css">

    <link rel="stylesheet" href="${context}/resources/css/generator/human_appear_info_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body class="whole_human_appear_info_page">
<jsp:include page="../header/generalHeader.jsp"/>
<jsp:include page="../header/setupHeader.jsp"/>
<div class="full_line_header">
    <div class="left10_div">
        <img src="${context}/resources/images/button_back.png" alt="back" class="round_button"
             onclick="window.location.href='${context}/generatorPage/${generator.idGenerator}'">
    </div>
    <div class="center80_div" id="info_header">
        <span>HUMAN APPEAR INFO</span>
    </div>
    <div class="right10_div">
        <img src="${context}/resources/images/button_create_new.png" alt="add" class="round_button"
             onclick="createHumanAppearInfo()">
    </div>
</div>
<div>
    <h3> Generator id - ${generator.idGenerator} :: Name - ${generator.generatorName}</h3>
</div>
<div class="common_table">
    <table id="mainTable">
        <tr>
            <td width="10%">№ position</td>
            <td width="20%">start time(ms)</td>
            <td width="20%">end time(ms)</td>
            <td width="20%">percent of the total</td>
            <td width="20%">variation(%)</td>
            <td width="5%">edit</td>
            <td width="5%">del</td>
        </tr>
    </table>
</div>
<div hidden>
    <button id="start_big_modal_window"></button>
</div>
<div id="modal_form_big" hidden style="overflow: auto">
    <div>
        HUMAN APPEAR INFO
    </div>
    <div class="common_table">
        <table id="humanAppearInfo">
            <tr>
                <td> type</td>
                <td> value</td>
            </tr>
        </table>
    </div>
    <div class="space10_div"></div>
    <div class="space30_div">
        <div class="left33_div"></div>
        <div class="center34_div">
            <img src="${context}/resources/images/button_edit.png" alt="edit" class="round_button"
                 onclick="editHumanAppearInfo()">
        </div>
    </div>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function editHumanAppearInfo() {
        $.ajax({
            url: '${context}/ajax/editHumanAppearInfo',
            type: 'POST',
            datatype: 'json',
            data: {
                idGenerator: ${generator.idGenerator},
                idHumanAppearInfo: $("#idHumanAppearInfo").html(),
                startTimeMs: $("#startTimeMs").val(),
                endTimeMs: $("#endTimeMs").val(),
                percent: $("#percent").val(),
                variation: $("#variation").val()
            },
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                $('#overlay').click();
                getAjaxContent();
            }
        });
    }
    function useObtainedDataForHumanAppearInfo(data) {
        var humanAppearInfo = data;
        $("#humanAppearInfo").find("tr:gt(0)").remove();
        $('#humanAppearInfo tr:last').after(""
                + "<tr>"
                + "<td> id </td> <td id='idHumanAppearInfo'>" + humanAppearInfo.idHumanAppearInfo + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td> start time </td> <td><input id='startTimeMs' type='number' min='0' value='" +
                humanAppearInfo.startTimeMs + "'/></td>"
                + "</tr>"
                + "<tr>"
                + "<td> end time </td> <td><input id='endTimeMs' type='number' min='0' value='" +
                humanAppearInfo.endTimeMs + "'/></td>"
                + "</tr>"
                + "<tr>"
                + "<td> percent  </td> <td><input id='percent' type='number' min='0' value='"
                + humanAppearInfo.percent + "'/></td>"
                + "</tr>"
                + "<tr>"
                + "<td> variation (%) </td> <td><input id='variation' type='number' min='0' value='"
                + humanAppearInfo.variation + "'/></td>"
                + "</tr>"
        );
        $('#start_big_modal_window').click();
    }
    function openHumanAppearInfo(data) {
        var tempId = $(data.target).attr('id');
        var searchId = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/getHumanAppearInfo',
            type: 'POST',
            datatype: 'json',
            data: {idGenerator: ${generator.idGenerator}, idHumanAppearInfo: searchId},
            success: function (response) {
                useObtainedDataForHumanAppearInfo(response);
            }
        });
    }
    function createHumanAppearInfo() {
        $.ajax({
            url: '${context}/ajax/createHumanAppearInfo',
            type: 'POST',
            datatype: 'json',
            data: {idGenerator: ${generator.idGenerator}},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
    }
    function delHumanAppearInfo(data) {
        var tempId = $(data.target).attr('id');
        var searchId = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/deleteHumanAppearInfo',
            type: 'POST',
            datatype: 'json',
            data: {idHumanAppearInfo: searchId},
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
        var infoList = data;
        var index;
        for (var ind in infoList) {
            index = infoList[ind].idHumanAppearInfo;
            $('#mainTable tr:last').after(""
                    + "<tr>"
                    + "<td>" + infoList[ind].idHumanAppearInfo + "</td>"
                    + "<td>" + infoList[ind].startTimeMs + "</td>"
                    + "<td>" + infoList[ind].endTimeMs + "</td>"
                    + "<td>" + infoList[ind].percent + "</td>"
                    + "<td>" + infoList[ind].variation + "</td>"
                    + "<td>" + "<img src='${context}/resources/images/button_ed.png' alt='edit' "
                    + "id='idE" + index + "'>" + "</td>"
                    + "<td>" + "<img src='${context}/resources/images/button_del.png' alt='delete' "
                    + "id='idX" + index + "'>" + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idE" + index);
            $(document).on("click", "#idE" + index, openHumanAppearInfo);
            $("#idE" + index).addClass('round_button');
            $(document).off("click", "#idX" + index);
            $(document).on("click", "#idX" + index, delHumanAppearInfo);
            $("#idX" + index).addClass('round_button');
        }
    }
    function getAjaxContent() {
        $.ajax({
            url: '${context}/ajax/getHumanAppearInfoList',
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
