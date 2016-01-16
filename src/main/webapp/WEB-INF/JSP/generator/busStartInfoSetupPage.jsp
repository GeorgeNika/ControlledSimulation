<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_modal_v01.css" type="text/css">

    <link rel="stylesheet" href="${context}/resources/css/generator/bus_start_info_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body class="whole_bus_start_info_page">
<jsp:include page="../header/generalHeader.jsp"/>
<jsp:include page="../header/setupHeader.jsp"/>
<div class="full_line_header">
    <div class="left10_div">
        <img src="${context}/resources/images/button_back.png" alt="back" class="round_button"
             onclick="window.location.href='${context}/generatorPage/${generator.idGenerator}'">
    </div>
    <div class="center80_div" id="info_header">
        <span>BUS START INFO</span>
    </div>
    <div class="right10_div">
        <img src="${context}/resources/images/button_create_new.png" alt="add" class="round_button"
             onclick="createBusStartInfo()">
    </div>
</div>
<div>
    <h3> Generator id - ${generator.idGenerator} :: Name - ${generator.generatorName}</h3>
</div>
<div class="common_table">
    <table id="mainTable">
        <tr>
            <td width="10%">№ position</td>
            <td width="30%">start time(ms)</td>
            <td width="15%">forward direction</td>
            <td width="20%">repeat quantity</td>
            <td width="15%">change direction</td>
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
        BUS START INFO
    </div>
    <div class="common_table">
        <table id="busStartInfo">
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
                 onclick="editBusStartInfo()">
        </div>
    </div>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function editBusStartInfo() {
        $.ajax({
            url: '${context}/ajax/editBusStartInfo',
            type: 'POST',
            datatype: 'json',
            data: {
                idGenerator: ${generator.idGenerator},
                idBusStartInfo: $("#idBusStartInfo").html(),
                startTimeMs: $("#startTimeMs").val(),
                forwardDirection: $("#forwardDirection").is(':checked'),
                repeatQuantity: $("#repeatQuantity").val(),
                changeDirection: $("#changeDirection").is(':checked')
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
    function useObtainedDataForBusStartInfo(data) {
        var busStartInfo = data;
        $("#busStartInfo").find("tr:gt(0)").remove();
        $('#busStartInfo tr:last').after(""
                + "<tr>"
                + "<td> id </td> <td id='idBusStartInfo'>" + busStartInfo.idBusStartInfo + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td> start time </td> <td><input id='startTimeMs' type='number' min='0' value='" +
                busStartInfo.startTimeMs + "'/></td>"
                + "</tr>"
                + "<tr>"
                + "<td> forward direction  </td> <td><input id='forwardDirection' type='checkbox'/> </td>"
                + "</tr>"
                + "<tr>"
                + "<td> repeat quantity  </td> <td><input id='repeatQuantity' type='number' min='0' value='" +
                busStartInfo.repeatQuantity + "'/></td>"
                + "</tr>"
                + "<tr>"
                + "<td> change direction  </td> <td><input id='changeDirection' type='checkbox'/> </td>"
                + "</tr>"
        );
        $("#forwardDirection").attr("checked", busStartInfo.forwardDirection);
        $("#changeDirection").attr("checked", busStartInfo.changeDirection);
        $('#start_big_modal_window').click();
    }
    function openBusStartInfo(data) {
        var tempId = $(data.target).attr('id');
        var searchId = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/getBusStartInfo',
            type: 'POST',
            datatype: 'json',
            data: {idGenerator: ${generator.idGenerator}, idBusStartInfo: searchId},
            success: function (response) {
                useObtainedDataForBusStartInfo(response);
            }
        });
    }
    function createBusStartInfo() {
        $.ajax({
            url: '${context}/ajax/createBusStartInfo',
            type: 'POST',
            datatype: 'json',
            data: {idGenerator: ${generator.idGenerator}},
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication.');
                }
                getAjaxContent();
            }
        });
    }
    function delBusStartInfo(data) {
        var tempId = $(data.target).attr('id');
        var searchId = tempId.substring(3, tempId.length);
        $.ajax({
            url: '${context}/ajax/deleteBusStartInfo',
            type: 'POST',
            datatype: 'json',
            data: {idBusStartInfo: searchId},
            success: function (response) {
                if (response != true) {
                    alert('ERROR. You should check your authentication.');
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
            index = infoList[ind].idBusStartInfo;
            $('#mainTable tr:last').after(""
                    + "<tr>"
                    + "<td>" + infoList[ind].idBusStartInfo + "</td>"
                    + "<td>" + infoList[ind].startTimeMs + "</td>"
                    + "<td>" + infoList[ind].forwardDirection + "</td>"
                    + "<td>" + infoList[ind].repeatQuantity + "</td>"
                    + "<td>" + infoList[ind].changeDirection + "</td>"
                    + "<td>" + "<img src='${context}/resources/images/button_ed.png' alt='edit' "
                    + "id='idE" + index + "'>" + "</td>"
                    + "<td>" + "<img src='${context}/resources/images/button_del.png' alt='delete' "
                    + "id='idX" + index + "'>" + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idE" + index);
            $(document).on("click", "#idE" + index, openBusStartInfo);
            $("#idE" + index).addClass('round_button');
            $(document).off("click", "#idX" + index);
            $(document).on("click", "#idX" + index, delBusStartInfo);
            $("#idX" + index).addClass('round_button');
        }
    }
    function getAjaxContent() {
        $.ajax({
            url: '${context}/ajax/getBusStartInfoList',
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
