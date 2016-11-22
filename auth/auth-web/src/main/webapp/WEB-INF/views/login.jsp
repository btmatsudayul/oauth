<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%-- omitted --%>
<div id="wrapper">
    <h3>Login Screen</h3>
    <%-- (1) --%>
    <c:if test="${param.error}">
        <t:messagesPanel messagesType="error"
            messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION"/> <%-- (2) --%>
    </c:if>
    <form:form action="${pageContext.request.contextPath}/login" method="post"> <%-- (3) --%>
        <table>
            <tr>
                <td><label for="username">User Name</label></td>
                <td><input type="text" id="username" name="username"></td>
            </tr>
            <tr>
                <td><label for="password">Password</label></td>
                <td><input type="password" id="password" name="password"></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="Login" /></td>
            </tr>
        </table>
    </form:form>
</div>
<%-- omitted --%>
