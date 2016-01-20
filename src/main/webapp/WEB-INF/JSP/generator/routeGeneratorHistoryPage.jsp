<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="${context}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${context}/resources/css/common_v01.css" type="text/css">
    <link rel="stylesheet" href="${context}/resources/css/common_table_v01.css" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body class="whole_history_page">
<jsp:include page="../header/generalHeader.jsp"/>
<jsp:include page="../header/historyHeader.jsp"/>
<div class="full_line_header">
    <div class="left10_div">
        <img src="${context}/resources/images/button_back.png" alt="back" class="round_button"
             onclick="window.location.href='${context}/experimentMainHistoryPage'">
    </div>
    <div class="center80_div" id="info_header">
        <span> ${generatorHistory.idExperimentHistory} -
               ${generatorHistory.idGeneratorHistory} -
               ${generatorHistory.generatorType} :
               ${generatorHistory.generatorName}
        </span>
    </div>
</div>
<div class="space5_div"></div>
<div class="common_table">
    <table>
        <tr>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td width="40%">id experiment - ${generatorHistory.idExperiment}</td>
            <td width="60%">create entity - ${generatorHistory.createEntity}</td>
        </tr>
        <tr>
            <td>id generator - ${generatorHistory.idGenerator}</td>
            <td>processed entity - ${generatorHistory.processedEntity}</td>
        </tr>
    </table>
</div>
<div class="space5_div"></div>
<div class="common_table">
    <table id="mainTable">
        <tr>
            <td width="15%">idEntityHistory</td>
            <td width="15%">idEntity</td>
            <td width="15%">idGeneratorHistory</td>
            <td width="15%">idGenerator</td>
            <td width="40%">entity type</td>
        </tr>
    </table>
</div>
<script src="${context}/resources/js/adaptive_size_v01.js"></script>
<script>
    function useObtainedData(data) {
        $("#mainTable").find("tr:gt(0)").remove();
        var entityList = data;
        var index;
        for (var ind in entityList) {
            index = entityList[ind].idEntityHistory;
            $('#mainTable tr:last').after(""
                    + "<tr id='idEH" + index + "'>"
                    + "<td>" + entityList[ind].idEntityHistory + "</td>"
                    + "<td>" + entityList[ind].idEntity + "</td>"
                    + "<td>" + entityList[ind].idGeneratorHistory + "</td>"
                    + "<td>" + entityList[ind].idGenerator + "</td>"
                    + "<td>" + entityList[ind].entityType + "</td>"
                    + "</tr>"
            );
        }
    }
    function getAjaxContent() {
        $.ajax({
            url: '${context}/ajax/getEntityHistoryListByGenerator',
            type: 'POST',
            datatype: 'json',
            data: {idGeneratorHistory: ${generatorHistory.idGeneratorHistory}},
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
