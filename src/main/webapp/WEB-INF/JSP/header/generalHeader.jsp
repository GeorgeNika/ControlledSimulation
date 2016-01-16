<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="general_header" onclick="window.location.href='${context}/generalMainPage'">
    <div class="left10_div"></div>
    <div class="center80_div">
        <span>EXPERIMENT SIMULATION</span>
    </div>
    <div class="right10_div" id="button_log">
        <c:choose>
            <c:when test="${sessionScope.get('isUserLogin')}">
                <img src="${context}/resources/images/button_log_out.png" alt="logout" class="round_button"
                     onclick="window.location.href='${context}/logout'; event.stopPropagation()">
            </c:when>
            <c:otherwise>
                <img src="${context}/resources/images/button_log_in.png" alt="login" class="round_button"
                     onclick="window.location.href='${context}/login'; event.stopPropagation()">
            </c:otherwise>
        </c:choose>
    </div>
</div>

