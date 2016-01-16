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
<div class="space20_div"></div>
<div class="space20_div"></div>
<div class="space30_div">
    <div class="left33_div">
        <img src="${context}/resources/images/button_experiment.png" alt="experiment" class="round_button"
             onclick="window.location.href='${context}/experimentMainSetupPage'">
    </div>
    <div class="center34_div">
        <img src="${context}/resources/images/button_generator.png" alt="generator" class="round_button"
             onclick="window.location.href='${context}/generatorMainSetupPage'">
    </div>
    <div class="right33_div">
        <img src="${context}/resources/images/button_entity.png" alt="entity" class="round_button"
             id="image_entity" onclick="chooseEntityInfoType()">
    </div>
</div>
<div hidden>
    <button id="start_small_modal_window"></button>
</div>
<div id="modal_form_small" hidden class="common_table" style="overflow: auto">
    <table id="entityInfoSelectTable">
        <tr>
            <td>
                Entity Info Type
            </td>
        </tr>
    </table>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>

<script>
    function selectEntityInfoType(data) {
        var tempId = $(data.target).parent().attr('id');
        var selectedType = tempId.substring(4, tempId.length);
        $('#overlay').click();
        window.location.href = '${context}/entityInfoPage/' + selectedType;
    }
    function useObtainedDataForSelectEntityInfoType(data) {
        var entityInfoList = data;
        $("#entityInfoSelectTable").find("tr:gt(0)").remove();
        for (var ind in entityInfoList) {
            $('#entityInfoSelectTable tr:last').after(""
                    + "<tr id='idET" + entityInfoList[ind] + "'>"
                    + "<td>" + entityInfoList[ind] + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idET" + entityInfoList[ind]);
            $(document).on("click", "#idET" + entityInfoList[ind], selectEntityInfoType);
        }
        $('#start_small_modal_window').click();
    }
    function chooseEntityInfoType() {
        $.ajax({
            url: '${context}/ajax/getEntityInfoTypeList',
            type: 'POST',
            datatype: 'json',
            data: {},
            success: function (response) {
                useObtainedDataForSelectEntityInfoType(response);
            }
        });
    }
    function adaptiveTextSize() {
        $('#general_header').textfill({maxFontPixels: 70});
        $('#setup_header').textfill({maxFontPixels: 70});
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
