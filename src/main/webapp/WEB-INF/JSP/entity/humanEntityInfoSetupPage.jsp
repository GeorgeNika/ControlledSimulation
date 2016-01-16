<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_modal_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/entity/human_entity_info_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body>
<div class="whole_human_info_page">

    <jsp:include page="../header/generalHeader.jsp"/>
    <jsp:include page="../header/setupHeader.jsp"/>
    <div class="full_line_header">
        <div class="left10_div">
            <img src="${context}/resources/images/button_back.png" alt="back" class="round_button"
                 onclick="window.location.href='${context}/setupMainPage'">
        </div>
        <div class="center80_div" id="info_header">
            <span>HUMAN INFO</span>
        </div>
        <div class="right10_div">
            <img src="${context}/resources/images/button_create_new.png" alt="add" class="round_button"
                 onclick="createHumanInfo()">
        </div>
    </div>

    <div class="common_table">
        <table id="mainTable">
            <tr>
                <td width="15%">№</td>
                <td width="50%">reasonable price (cent)</td>
                <td width="35%">delay time to remove (ms)</td>
            </tr>
        </table>
    </div>
</div>
<div hidden>
    <button id="start_big_modal_window"></button>
</div>
<div id="modal_form_big" hidden style="overflow: auto">
    <div>
        HUMAN INFO
    </div>
    <div class="common_table">
        <table id="humanEntityInfo">
            <tr>
                <td> type</td>
                <td> value</td>
            </tr>
        </table>
    </div>
    <div>
        <div class="space10_div"></div>
        <div class="space30_div">
            <div class="left33_div">
                <img src="${context}/resources/images/button_edit.png" alt="edit" class="round_button"
                     onclick="editHumanInfo()">
            </div>
            <div class="right33_div">
                <img src="${context}/resources/images/button_delete.png" alt="delete" class="round_button"
                     onclick="deleteHumanInfo()">
            </div>
        </div>
    </div>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function createHumanInfo() {
        $.ajax({
            url: '${context}/ajax/createHumanEntityInfo',
            type: 'POST',
            datatype: 'json',
            data: {},
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication.');
                }
                getAjaxContent();
            }
        });
    }
    function editHumanInfo() {
        $.ajax({
            url: '${context}/ajax/editHumanEntityInfo',
            type: 'POST',
            datatype: 'json',
            data: {
                idEntityInfo: $("#idEntityInfo").html(),
                reasonablePriceInCent: $("#reasonablePriceInCent").val(),
                delayTimeToRemoveMs: $("#delayTimeToRemoveMs").val()
            },
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication.');
                }
                $('#overlay').click();
                getAjaxContent();
            }
        });
    }
    function deleteHumanInfo() {
        $.ajax({
            url: '${context}/ajax/deleteHumanEntityInfo',
            type: 'POST',
            datatype: 'json',
            data: {idEntityInfo: $("#idEntityInfo").html()},
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication.');
                }
                $('#overlay').click();
                getAjaxContent();
            }
        });
    }
    function useObtainedDataForEntityType(data) {
        var humanEntityInfo = data;
        $("#humanEntityInfo").find("tr:gt(0)").remove();
        $('#humanEntityInfo tr:last').after(""
                + "<tr>"
                + "<td> id </td> <td id='idEntityInfo'>" + humanEntityInfo.idEntityInfo + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td> reasonable price (cent) </td> <td><input id='reasonablePriceInCent' type='number' min='0' value='"
                + humanEntityInfo.reasonablePriceInCent + "'/></td>"
                + "</tr>"
                + "<tr>"
                + "<td> delay time to remove (ms) </td> <td><input id='delayTimeToRemoveMs' type='number' min='0' value='"
                + humanEntityInfo.delayTimeToRemoveMs + "'/></td>"
                + "</tr>"
        );
        $('#start_big_modal_window').click();
    }
    function openEntityInfo(data) {
        var tempId = $(data.target).parent().attr('id');
        var searchId = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/getHumanEntityInfo',
            type: 'POST',
            datatype: 'json',
            data: {idEntityInfo: searchId},
            success: function (response) {
                useObtainedDataForEntityType(response);
            }
        });
    }
    function useObtainedData(data) {
        $("#mainTable").find("tr:gt(0)").remove();
        var entityInfoList = data;
        for (var ind in entityInfoList) {
            $('#mainTable tr:last').after(""
                    + "<tr id='idE" + entityInfoList[ind].idEntityInfo + "'>"
                    + "<td>" + entityInfoList[ind].idEntityInfo + "</td>"
                    + "<td>" + entityInfoList[ind].reasonablePriceInCent + "</td>"
                    + "<td>" + entityInfoList[ind].delayTimeToRemoveMs + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idE" + entityInfoList[ind].idEntityInfo);
            $(document).on("click", "#idE" + entityInfoList[ind].idEntityInfo, openEntityInfo);
        }
    }
    function getAjaxContent(sendData) {
        $.ajax({
            url: '${context}/ajax/getHumanEntityInfoList',
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
