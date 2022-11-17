<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--suppress XmlPathReference --%>
<%@ page import="com.urise.webapp.model.*" %>
<%@ page import="com.urise.webapp.util.DateFormatUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--suppress HtmlUnknownTarget --%>
    <link rel="stylesheet" href="css/edit-resume-styles.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<br>
<a href="resume"><<< Назад к списку резюме</a>
<hr>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" pattern=[A-Za-zА-Яа-яЁё]{2}.+ required size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30
                           value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.AbstractSection"/>
            <h4><a>${type.title}</a></h4>
            <c:choose>
                <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                    <textarea name='${type}' cols=90 rows=5><%=resume.getUuid().equals("new") ? "" : section%></textarea>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <textarea name='${type}' cols=90
                              rows=12><%=resume.getUuid().equals("new") ? "" : String.join("\n", ((ListSection) section).getContent())%></textarea>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <div id="${type}">
                        <c:forEach var="company" items="<%=((CompanySection) section).getCompanies()%>"
                                   varStatus="counter">
                            <dl>
                                <dt>Название организации:</dt>
                                <dd><input type="text" name='${type}' size=50 value="${company.name}"></dd>
                            </dl>
                            <div id="${type}${counter.index}" style="margin-left: 30px" >
                                <c:forEach var="period" items="${company.periods}">
                                    <jsp:useBean id="period" type="com.urise.webapp.model.Period"/>
                                    <dl>
                                        <dt>Начальная дата:</dt>
                                        <dd>
                                            <input type="text" name="${type}${counter.index}startDate" size=8
                                                   value="<%=DateFormatUtil.format(period.getStartDate())%>" placeholder="MM/yyyy">
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>Конечная дата:</dt>
                                        <dd>
                                            <input type="text" name="${type}${counter.index}endDate" size=8
                                                   value="<%=DateFormatUtil.format(period.getEndDate())%>" placeholder="MM/yyyy">
                                    </dl>
                                    <dl>
                                        <dt>Позиция:</dt>
                                        <dd><input type="text" name='${type}${counter.index}title' size=100
                                                   value="${period.title}">
                                    </dl>
                                    <dl>
                                        <dt>Описание:</dt>
                                        <dd><textarea name="${type}${counter.index}description" rows=5
                                                      cols=90>${period.description}</textarea></dd>
                                    </dl>
                                </c:forEach>
                            </div>
                            <button class="addPeriod" value="${type}${counter.index}" type=button
                                    style="margin-left: 30px">Добавить период</button>
                            <c:set var="oldPeriodCounter" value="${counter.index}"/>
                        </c:forEach>
                    </div>
                    <br>
                    <button class="addCompany" value="${type}_${oldPeriodCounter}" type=button>Добавить организацию</button>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<script language="JavaScript">
    window.onload = function() {
        let allCompBut = document.getElementsByClassName("addCompany");
        let newExpPeriods = 0;
        let newEduPeriods = 0;
        for (let i = 0; i < allCompBut.length; i++) {
            allCompBut[i].onclick = newCompany;
        }
        initPeriodButtons();

        function initPeriodButtons(){
            let allPeriodBut = document.getElementsByClassName("addPeriod");
            for (let i = 0; i < allPeriodBut.length; i++) {
                allPeriodBut[i].onclick = newPeriod;
            }
        }

        function newCompany(){
            let compValue = this.value.split('_');
            let compType = compValue[0];
            let periodNum;
            if (compType === "EXPERIENCE") {
                newExpPeriods++;
                periodNum = Number(compValue[1]) + newExpPeriods;
            } else {
                newEduPeriods++;
                periodNum = Number(compValue[1]) + newEduPeriods;
            }
            document.getElementById(compType).innerHTML+='' +
                '<dl>' +
                '<dt>Название организации:</dt>' +
                '<dd><input type="text" name="' + compType + '" size=50 value=""></dd>' +
                '</dl>' +
                '<div id="' + compType + '' + periodNum + '" style="margin-left: 30px" ></div>' +
                '<button class="addPeriod" value="' + compType + '' + periodNum + '" type=button style="margin-left: 30px">Добавить период</button>';
            initPeriodButtons();
        }

        function newPeriod() {
            document.getElementById(this.value).innerHTML+='' +
                '<dl>' +
                    '<dt>Начальная дата:</dt>' +
                    '<dd><input type="text" name="' + this.value + 'startDate" size=8 value="" placeholder="MM/yyyy"></dd>' +
                '</dl>' +
                '<dl>' +
                    '<dt>Конечная дата:</dt>' +
                    '<dd><input type="text" name="' + this.value + 'endDate" size=8 value="" placeholder="MM/yyyy"></dd>' +
                '</dl>' +
                '<dl>' +
                    '<dt>Позиция:</dt>' +
                    '<dd><input type="text" name="' + this.value + 'title" size=100 value=""></dd>' +
                '</dl>' +
                '<dl>' +
                    '<dt>Описание:</dt>' +
                    '<dd><textarea name="' + this.value + 'description" rows=5 cols=90></textarea></dd>' +
                '</dl>'
        }
    }
</script>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
