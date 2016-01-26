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
             onclick="window.location.href='${context}/setupMainPage'">
    </div>
    <div class="center80_div" id="info_header">
        <span>GENERATOR</span>
    </div>
    <div class="right10_div">
        <img src="${context}/resources/images/button_create_new.png" alt="add" class="round_button"
             onclick="chooseGeneratorType()">
    </div>
</div>
<div class="common_table" >
    <table id="mainTable">
        <tr>
            <td width="5%">№</td>
            <td width="10%">type</td>
            <td width="35%">Name</td>
            <td width="10%">entity type</td>
            <td width="10%">id entity info</td>
            <td width="10%">quantity entity</td>
            <td width="10%">created</td>
            <td width="10%">updated</td>
        </tr>
    </table>
</div>
<div hidden>
    <button id="start_small_modal_window"></button>
</div>
<div id="modal_form_small" hidden class="common_table" style="overflow: auto">
    <table id="generatorSelectTable">
        <tr>
            <td>
                Generator Type
            </td>
        </tr>
    </table>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function selectGeneratorType(data) {
        var tempId = $(data.target).parent().attr('id');
        var selectedType = tempId.substring(4, tempId.length);
        $.ajax({
            url: '${context}/ajax/createGenerator',
            type: 'POST',
            datatype: 'json',
            data: {generatorType: selectedType},
            success: function (response) {
                if (response != true) {
                    $('html').html(response);
                }
                getAjaxContent();
            }
        });
        $('#overlay').click();
    }
    function useObtainedDataForSelectGeneratorType(data) {
        var generatorList = data;
        $("#generatorSelectTable").find("tr:gt(0)").remove();
        for (var ind in generatorList) {
            $('#generatorSelectTable tr:last').after(""
                    + "<tr id='idGT" + generatorList[ind] + "'>"
                    + "<td>" + generatorList[ind] + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idGT" + generatorList[ind]);
            $(document).on("click", "#idGT" + generatorList[ind], selectGeneratorType);
        }
        $('#start_small_modal_window').click();
    }
    function chooseGeneratorType(data) {
        $.ajax({
            url: '${context}/ajax/getGeneratorTypeList',
            type: 'POST',
            datatype: 'json',
            data: {},
            success: function (response) {
                useObtainedDataForSelectGeneratorType(response);
            }
        });
    }
    function generatorHref(data) {
        var tempId = $(data.target).parent().attr('id');
        var searchId = tempId.substring(3, tempId.length);
        window.location.href = '${context}/generatorPage/' + searchId;
    }
    function useObtainedData(data) {
        $("#mainTable").find("tr:gt(0)").remove();
        var generatorList = data;
        var dateCreate, dateUpdated;
        var index;
        for (var ind in generatorList) {

            index = generatorList[ind].idGenerator;
            dateCreate = generatorList[ind].created;
            dateUpdated = generatorList[ind].updated;

            $('#mainTable tr:last').after(""
                    + "<tr id='idG" + index + "'>"
                    + "<td>" + generatorList[ind].idGenerator + "</td>"
                    + "<td>" + generatorList[ind].generatorType + "</td>"
                    + "<td>" + generatorList[ind].generatorName + "</td>"
                    + "<td>" + generatorList[ind].entityType + "</td>"
                    + "<td>" + generatorList[ind].idEntity + "</td>"
                    + "<td>" + generatorList[ind].entityQuantity + "</td>"
                    + "<td>" + dateCreate + "</td>"
                    + "<td>" + dateUpdated + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idG" + index);
            $(document).on("click", "#idG" + index, generatorHref);
        }
    }
    function getAjaxContent(sendData) {
        $.ajax({
            url: '${context}/ajax/getGeneratorList',
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
