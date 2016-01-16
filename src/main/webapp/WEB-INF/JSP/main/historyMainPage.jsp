<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_modal_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body class="whole_history_page">
<jsp:include page="../header/generalHeader.jsp"/>
<jsp:include page="../header/historyHeader.jsp"/>
<div class="space20_div"></div>
<div class="space20_div"></div>
<div class="space30_div">
    <div class="left33_div">
        <img src="${context}/resources/images/button_experiment.png" alt="experiment" class="round_button"
             onclick="window.location.href='${context}/experimentMainHistoryPage'">
    </div>
    <div class="center34_div">
        <img src="${context}/resources/images/button_generator.png" alt="generator" class="round_button"
             onclick="window.location.href='${context}/generatorMainHistoryPage'">
    </div>
    <div class="right33_div">
        <img src="${context}/resources/images/button_entity.png" alt="entity" class="round_button"
             id="image_entity" onclick="chooseEntityType()">
    </div>
</div>
<div hidden>
    <button id="start_small_modal_window"></button>
</div>
<div id="modal_form_small" hidden class="common_table" style="overflow: auto">
    <table id="entitySelectTable">
        <tr>
            <td>
                Entity Type
            </td>
        </tr>
    </table>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>

<script>
    function selectEntityType(data) {
        var tempId = $(data.target).parent().attr('id');
        var selectedType = tempId.substring(3, tempId.length);
        $('#overlay').click();
        window.location.href = '${context}/entityHistoryPage/' + selectedType;
    }
    function useObtainedDataForSelectEntityType(data) {
        var entityList = data;
        $("#entitySelectTable").find("tr:gt(0)").remove();
        var index;
        for (var ind in entityList) {
            index = entityList[ind];
            $('#entitySelectTable tr:last').after(""
                    + "<tr id='idE" + index + "'>"
                    + "<td>" + entityList[ind] + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idE" + index);
            $(document).on("click", "#idE" + index, selectEntityType);
        }
        $('#start_small_modal_window').click();
    }
    function chooseEntityType() {
        $.ajax({
            url: '${context}/ajax/getEntityTypeList',
            type: 'POST',
            datatype: 'json',
            data: {},
            success: function (response) {
                useObtainedDataForSelectEntityType(response);
            }
        });
    }
    function adaptiveTextSize() {
        $('#general_header').textfill({maxFontPixels: 70});
        $('#history_header').textfill({maxFontPixels: 70});
    }
    $(document).ready(function () {
        adaptiveTextSize();

    });
    $(window).resize(function () {
        adaptiveTextSize();
    })
</script>
</body>
</html>

