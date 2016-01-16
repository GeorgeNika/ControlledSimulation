<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_modal_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/generator/station_generator_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body class="whole_station_page">
<jsp:include page="../header/generalHeader.jsp"/>
<jsp:include page="../header/setupHeader.jsp"/>
<div class="full_line_header">
    <div class="left10_div" id="button_back">
        <img src="${context}/resources/images/button_back.png" alt="back" class="round_button"
             onclick="window.location.href='${context}/generatorMainSetupPage'">
    </div>
    <div class="center80_div" id="info_header">
        <span>STATION</span>
    </div>
    <div class="right10_div" id="button_delete">
        <img src="${context}/resources/images/button_delete.png" alt="del" class="round_button"
             onclick="deleteGenerator()">
    </div>
</div>
<div>
    <h2></h2>
</div>
<div class="common_table">
    <table>
        <tr>
            <td> Data</td>
        </tr>
        <tr>
            <td>
                <form:form method="POST" action="${context}/editGeneratorAction/${generator.idGenerator}"
                           commandName="generatorMainForm">
                    <table>
                        <tr>
                            <h2> Edit generator id - ${generator.idGenerator} :: name
                                - ${generator.generatorName}</h2>
                        </tr>
                        <tr>
                            <td>Name</td>
                            <td><form:input path="generatorName"/></td>
                            <td>Entity type</td>
                            <td>
                                <form:input path="entityType" id="entityType" readonly="true"/>
                                <button type="button" onclick="chooseEntityType()">...</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Entity Quantity</td>
                            <td><form:input path="entityQuantity" type='number' min='0'/></td>
                            <td>Entity Info ID</td>
                            <td>
                                <form:input path="idEntityInfo" id="idEntityInfo" readonly="true"/>
                                <button type="button" onclick="chooseIdEntityInfo()">...</button>
                            </td>
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
<div class="space10_div"></div>
<div class="space20_div">
    <div class="left33_div">
        <img src="${context}/resources/images/button_relation.png" alt="relation" class="round_button"
             onclick="window.location.href='${context}/generatorRelationSetupPage/${generator.idGenerator}'">
    </div>
    <div class="right33_div">
        <img src="${context}/resources/images/button_HAInfo.png" alt="info" class="round_button"
             onclick="window.location.href='${context}/humanAppearInfoSetupPage/${generator.idGenerator}'">
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
<div hidden>
    <button id="start_big_modal_window"></button>
</div>
<div id="modal_form_big" hidden style="overflow: auto">
    <div>
        ENTITY INFO
    </div>
    <div class="common_table">
        <table id="entityInfoSelectTable">
            <tr>
                <td> id</td>
                <td> info</td>
            </tr>
        </table>
    </div>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function selectIdEntityInfo(data) {
        var tempId = $(data.target).parent().attr('id');
        var selectedId = tempId.substring(3, tempId.length);
        $('#overlay').click();
        $('#idEntityInfo').val(selectedId);
    }
    function useObtainedDataForSelectIdEntityInfo(data) {
        var entityInfoList = data;
        $("#entityInfoSelectTable").find("tr:gt(0)").remove();
        for (var ind in entityInfoList) {
            $('#entityInfoSelectTable tr:last').after(""
                    + "<tr id='idI" + entityInfoList[ind].idEntityInfo + "'>"
                    + "<td>" + entityInfoList[ind].idEntityInfo + "</td>"
                    + "<td>" + entityInfoList[ind].fullInfo + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idI" + entityInfoList[ind].idEntityInfo);
            $(document).on("click", "#idI" + entityInfoList[ind].idEntityInfo, selectIdEntityInfo);
        }
        $('#start_big_modal_window').click();
    }
    function chooseIdEntityInfo() {
        var entityType = $('#entityType').val();
        if (entityType != "") {
            $.ajax({
                url: '${context}/ajax/getEntityInfoListByEntityType',
                type: 'POST',
                datatype: 'json',
                data: {entityType: entityType},
                success: function (response) {
                    useObtainedDataForSelectIdEntityInfo(response);
                }
            });
        }

    }
    function selectEntityType(data) {
        var tempId = $(data.target).parent().attr('id');
        var selectedType = tempId.substring(4, tempId.length);
        $('#overlay').click();
        $('#idEntityInfo').val("");
        $('#entityType').val(selectedType);
    }
    function useObtainedDataForSelectEntityType(data) {
        var entityList = data;
        $("#entitySelectTable").find("tr:gt(0)").remove();
        for (var ind in entityList) {
            $('#entitySelectTable tr:last').after(""
                    + "<tr id='idET" + entityList[ind] + "'>"
                    + "<td>" + entityList[ind] + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idET" + entityList[ind]);
            $(document).on("click", "#idET" + entityList[ind], selectEntityType);
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
    function deleteGenerator() {
        $.ajax({
            url: '${context}/ajax/deleteGenerator',
            type: 'POST',
            datatype: 'json',
            data: {idGenerator: ${generator.idGenerator}},
            success: function (response) {
                window.location.href = '${context}/generatorMainSetupPage';
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

    });
    $(window).resize(function () {
        adaptiveTextSize();
    })
</script>
</body>
</html>
