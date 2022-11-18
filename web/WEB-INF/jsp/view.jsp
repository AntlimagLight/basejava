<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page import="com.urise.webapp.util.DateFormatUtil" %>
<%@ page import="com.urise.webapp.model.CompanySection" %><%--suppress XmlPathReference --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--suppress HtmlUnknownTarget --%>
    <link rel="stylesheet" href="css/view-resume-styles.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<br>
<a href="resume"><<< Назад к списку резюме</a>
<hr>
<section>
<%--suppress HtmlUnknownTarget --%>
    <h1>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png" alt="Редактировать"></a></h1>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <hr>
    <p>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.SectionType, java.lang.String>"/>
                <c:set var="type" value="${sectionEntry.key}"/>
                <c:set var="section" value="${sectionEntry.value}"/>
                <jsp:useBean id="section" type="com.urise.webapp.model.AbstractSection"/>
            <h3>${type.title}</h3>
            <c:choose>
            <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                <%=((TextSection) section).getContent()%>
            </c:when>
            <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                <table>
                <c:forEach var="item" items="<%=((ListSection) section).getContent()%>">
                    <tr><td>
                        ${item}
                    </td></tr>
                </c:forEach>
                </table>
            </c:when>
            <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                <table>
                <c:forEach var="company" items="<%=((CompanySection) section).getCompanies()%>">
                    <tr><th colspan="2" align="left">
                    ${company.name}
                    </th></tr>
                    <c:forEach var="period" items="${company.periods}">
                        <jsp:useBean id="period" type="com.urise.webapp.model.Period"/>
                    <tr>
                        <td width="200px">${DateFormatUtil.formatForView(period.startDate)} - ${DateFormatUtil.formatForView(period.endDate)}</td>
                        <td><b>${period.title}</b><br>
                        ${period.description}</td>
                    </tr>
                    </c:forEach>
                </c:forEach>
                </table>
            </c:when>
            </c:choose>
        </c:forEach>
    <p>
</section>
<hr>
<a href="resume"><<< Назад к списку резюме</a><br>
<br>
<jsp:include page="fragments/header.jsp"/>
</body>
</html>
