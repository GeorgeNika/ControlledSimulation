<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_modal_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/entity/bus_entity_info_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body>
<div class="whole_bus_info_page">

    <jsp:include page="../header/generalHeader.jsp"/>
    <jsp:include page="../header/setupHeader.jsp"/>
    <div class="full_line_header">
        <div class="left10_div">
            <img src="${context}/resources/images/button_back.png" alt="back" class="round_button"
                 onclick="window.location.href='${context}/setupMainPage'">
        </div>
        <div class="center80_div" id="info_header">
            <span>BUS INFO</span>
        </div>
        <div class="right10_div">
            <img src="${context}/resources/images/button_create_new.png" alt="add" class="round_button"
                 onclick="createBusInfo()">
        </div>
    </div>

    <div class="common_table">
        <table id="mainTable">
            <tr>
                <td width="15%">№</td>
                <td width="70%">bus capacity</td>
                <td width="15%">price in cent</td>
            </tr>
        </table>
    </div>
</div>
<div hidden>
    <button id="start_big_modal_window"></button>
</div>
<div id="modal_form_big" hidden style="overflow: auto">
    <div>
        BUS INFO
    </div>
    <div class="common_table">
        <table id="busEntityInfo">
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
                <img src="${context}/resources/images/button_edit.png" alt="setup" class="round_button"
                     onclick="editBusInfo()">
            </div>
            <div class="right33_div">
                <img src="${context}/resources/images/button_delete.png" alt="setup" class="round_button"
                     onclick="deleteBusInfo()">
            </div>
        </div>
    </div>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function createBusInfo() {
        $.ajax({
            url: '${context}/ajax/createBusEntityInfo',
            type: 'POST',
            datatype: 'json',
            data: {},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
    }
    function editBusInfo() {
        $.ajax({
            url: '${context}/ajax/editBusEntityInfo',
            type: 'POST',
            datatype: 'json',
            data: {
                idEntityInfo: $("#idEntityInfo").html(),
                capacity: $("#capacity").val(),
                priceInCent: $("#priceInCent").val()
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
    function deleteBusInfo() {
        $.ajax({
            url: '${context}/ajax/deleteBusEntityInfo',
            type: 'POST',
            datatype: 'json',
            data: {idEntityInfo: $("#idEntityInfo").html()},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                $('#overlay').click();
                getAjaxContent();
            }
        });
    }
    function useObtainedDataForEntityType(data) {
        var busEntityInfo = data;
        $("#busEntityInfo").find("tr:gt(0)").remove();
        $('#busEntityInfo tr:last').after(""
                + "<tr>"
                + "<td> id </td> <td id='idEntityInfo'>" + busEntityInfo.idEntityInfo + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td> capacity </td> <td><input id='capacity' type='number' min='0' value='"
                + busEntityInfo.capacity + "'/></td>"
                + "</tr>"
                + "<tr>"
                + "<td> price in cent </td> <td><input id='priceInCent' type='number' min='0' value='"
                + busEntityInfo.priceInCent + "'/></td>"
                + "</tr>"
        );
        $('#start_big_modal_window').click();
    }
    function openEntityInfo(data) {
        var tempId = $(data.target).parent().attr('id');
        var searchId = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/getBusEntityInfo',
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
                    + "<td>" + entityInfoList[ind].capacity + "</td>"
                    + "<td>" + entityInfoList[ind].priceInCent + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idE" + entityInfoList[ind].idEntityInfo);
            $(document).on("click", "#idE" + entityInfoList[ind].idEntityInfo, openEntityInfo);
        }
    }
    function getAjaxContent(sendData) {
        $.ajax({
            url: '${context}/ajax/getBusEntityInfoList',
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
