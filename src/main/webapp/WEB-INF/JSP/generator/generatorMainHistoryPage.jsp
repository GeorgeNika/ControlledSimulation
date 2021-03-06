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
<div class="full_line_header">
    <div class="left10_div">
        <img src="${context}/resources/images/button_back.png" alt="setup" class="round_button"
             onclick="window.location.href='${context}/historyMainPage'">
    </div>
    <div class="center80_div" id="info_header">
        <span>GENERATOR</span>
    </div>
    <div class="right10_div">
        <img src="${context}/resources/images/button_filter.png" alt="filter" class="round_button"
             onclick="openFilterWindow()">
    </div>
</div>
<div class="common_table">
    <table id="mainTable">
        <tr>
            <td width="10%">id gen history</td>
            <td width="10%">id generator</td>
            <td width="10%">id exp history</td>
            <td width="10%">id experiment</td>
            <td width="10%">gen type</td>
            <td width="30%">gen name</td>
            <td width="10%">create entity</td>
            <td width="10%">destroy entity</td>
        </tr>
    </table>
</div>
<div hidden>
    <button id="start_big_modal_window"></button>
</div>
<div id="modal_form_big" hidden style="overflow: auto">
    <div>
        Generator Filter
    </div>
    <div class="common_table">
        <table id="busStartInfo">
            <tr>
                <td> type</td>
                <td> value</td>
            </tr>
            <tr>
                <td> id experiment</td>
                <td>
                    <input id='filterIdExperiment' type='number' min='0' step="1" value='0'/>
                    <button type="button" onclick="$('#filterIdExperiment').val('0');">X</button>
                </td>
            </tr>
            <tr>
                <td> id experiment history</td>
                <td>
                    <input id='filterIdExperimentHistory' type='number' min='0' step="1" value='0'/>
                    <button type="button" onclick="$('#filterIdExperimentHistory').val('0');">X</button>
                </td>
            </tr>
            <tr>
                <td> id generator</td>
                <td>
                    <input id='filterIdGenerator' type='number' min='0' step="1" value='0'/>
                    <button type="button" onclick="$('#filterIdGenerator').val('0');">X</button>
                </td>
            </tr>
            <tr>
                <td> id generator history</td>
                <td>
                    <input id='filterIdGeneratorHistory' type='number' min='0' step="1" value='0'/>
                    <button type="button" onclick="$('#filterIdGeneratorHistory').val('0');">X</button>
                </td>
            </tr>
        </table>
    </div>
    <div class="space10_div"></div>
    <div class="space30_div">
        <div class="left33_div"></div>
        <div class="center34_div">
            <img src="${context}/resources/images/button_filter.png" alt="filter" class="round_button"
                 onclick="setFilter()">
        </div>
    </div>
</div>
<div id="overlay" hidden></div>
<script src="${context}/resources/js/modal_window_v01.js"></script>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function setFilter() {
        var tempStr = $('#filterIdExperiment').val().replace(/[^\d.]/g, '');
        if (tempStr == '') {
            tempStr = 0;
        }
        $('#filterIdExperiment').val(parseInt(tempStr, 10));
        var tempStr = $('#filterIdExperimentHistory').val().replace(/[^\d.]/g, '');
        if (tempStr == '') {
            tempStr = 0;
        }
        $('#filterIdExperimentHistory').val(parseInt(tempStr, 10));
        var tempStr = $('#filterIdGenerator').val().replace(/[^\d.]/g, '');
        if (tempStr == '') {
            tempStr = 0;
        }
        $('#filterIdGenerator').val(parseInt(tempStr, 10));
        var tempStr = $('#filterIdGeneratorHistory').val().replace(/[^\d.]/g, '');
        if (tempStr == '') {
            tempStr = 0;
        }
        $('#filterIdGeneratorHistory').val(parseInt(tempStr, 10));
        $('#overlay').click();
        getAjaxContent();
    }
    function openFilterWindow() {
        $('#start_big_modal_window').click();
    }

    function generatorHref(data) {
        var tempId = $(data.target).parent().attr('id');
        var searchId = tempId.substring(4, tempId.length);
        window.location.href = '${context}/generatorHistoryPage/' + searchId;
    }
    function useObtainedData(data) {
        $("#mainTable").find("tr:gt(0)").remove();
        var generatorList = data;
        var index;
        for (var ind in generatorList) {
            index = generatorList[ind].idGeneratorHistory;
            $('#mainTable tr:last').after(""
                    + "<tr id='idGH" + index + "'>"
                    + "<td>" + generatorList[ind].idGeneratorHistory + "</td>"
                    + "<td>" + generatorList[ind].idGenerator + "</td>"
                    + "<td>" + generatorList[ind].idExperimentHistory + "</td>"
                    + "<td>" + generatorList[ind].idExperiment + "</td>"
                    + "<td>" + generatorList[ind].generatorType + "</td>"
                    + "<td>" + generatorList[ind].generatorName + "</td>"
                    + "<td>" + generatorList[ind].createEntity + "</td>"
                    + "<td>" + generatorList[ind].destroyEntity + "</td>"
                    + "</tr>"
            );
            $(document).off("click", "#idGH" + index);
            $(document).on("click", "#idGH" + index, generatorHref);
        }
    }
    function getAjaxContent(sendData) {
        $.ajax({
            url: '${context}/ajax/getGeneratorHistoryListByFilter',
            type: 'POST',
            datatype: 'json',
            data: {
                filterIdExperiment: parseInt($('#filterIdExperiment').val(), 10),
                filterIdExperimentHistory: parseInt($('#filterIdExperimentHistory').val(), 10),
                filterIdGenerator: parseInt($('#filterIdGenerator').val(), 10),
                filterIdGeneratorHistory: parseInt($('#filterIdGeneratorHistory').val(), 10)
            },
            success: function (response) {
                useObtainedData(response);
            }
        });
    }
    function adaptiveTextSize() {
        $('#general_header').textfill({maxFontPixels: 70});
        $('#history_header').textfill({maxFontPixels: 70});
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
