package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        printResume(createFullResume("uuid1", "Григорий Кислин"));
    }

    public static Resume createFullResume(String uuid, String fullName) {
// Создаем резюме
        Resume resume = new Resume(uuid, fullName);

//// Заполняем поля
//        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
//        resume.addContact(ContactType.SKYPE, "skype:grigory.kislin");
//        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
//        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
//        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
//        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");
//        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
//        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная " +
//                "логика, креативность, инициативность. Пурист кода и архитектуры."));
//        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного " +
//                "обучения по Java Web и Enterprise технологиям."));
//        List<String> exampleStringList = new ArrayList<>();
//        exampleStringList.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: " +
//                "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов " +
//                "на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для " +
//                "комплексных DIY смет. ");
//        exampleStringList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
//                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
//                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
//                "Более 3500 выпускников.");
//        exampleStringList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами" +
//                " Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
//        exampleStringList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM." +
//                " Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке:" +
//                " Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
//                "интеграция CIFS/SMB java сервера.");
//        exampleStringList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, " +
//                "Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
//        exampleStringList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных " +
//                "ервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о " +
//                "состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
//                "мониторинга системы по JMX (Jython/ Django).");
//        exampleStringList.add("Реализация протоколов по приему платежей всех основных платежных системы " +
//                "России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
//        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(exampleStringList));
//        exampleStringList = new ArrayList<>();
//        exampleStringList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
//        exampleStringList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
//        exampleStringList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, " +
//                "MySQL, SQLite, MS SQL, HSQLDB");
//        exampleStringList.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
//        exampleStringList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, " +
//                "Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, " +
//                "ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
//        exampleStringList.add("Python: Django.");
//        exampleStringList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
//        exampleStringList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
//        exampleStringList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, " +
//                "SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, " +
//                "OAuth1, OAuth2, JWT.");
//        exampleStringList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
//        exampleStringList.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
//                "Nagios, iReport, OpenCmis, Bonita, pgBouncer");
//        exampleStringList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
//                "архитектурных шаблонов, UML, функционального программирования");
//        exampleStringList.add("Родной русский, английский \"upper intermediate\"");
//        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(exampleStringList));
//
//        List<Company> exampleCompanyList = new ArrayList<>();
//        exampleCompanyList.add(createOnePeriodCompany("Alcatel", 1997, 9,
//                2005, 1, "Инженер по аппаратному и программному тестированию", "" +
//                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции " +
//                        "Alcatel 1000 S12 (CHILL, ASM)."));
//        exampleCompanyList.add(createOnePeriodCompany("Siemens AG", 2005, 1,
//                2007, 2, "Разработчик ПО", "Разработка информационной модели, " +
//                        "проектирование интерфейсов, реализация и отладка ПО " +
//                        "на мобильной IN платформе Siemens @vantage (Java, Unix)."));
//        exampleCompanyList.add(createOnePeriodCompany("Enkata", 2007, 3,
//                2008, 6, "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и " +
//                        "серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения " +
//                        "(OLAP, Data mining)."));
//        exampleCompanyList.add(createOnePeriodCompany("Yota", 2008, 6,
//                2012, 10, "Ведущий специалист", "Дизайн и имплементация Java EE" +
//                        " фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI \n" +
//                        "2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики " +
//                        "и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));
//        exampleCompanyList.add(createOnePeriodCompany("Luxoft (Deutsche Bank)", 2010, 12,
//                2012, 4, "Ведущий программист", "Участие в проекте Deutsche Bank CRM " +
//                        "(WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация \n" +
//                        "клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, " +
//                        "мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, \n" +
//                        "Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
//        exampleCompanyList.add(createOnePeriodCompany("RIT Center", 2012, 4,
//                2014, 10, "Java архитектор", "Организация процесса разработки системы " +
//                        "ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins),\n " +
//                        "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), " +
//                        "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных " +
//                        "сервисов:\n CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт " +
//                        "в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера " +
//                        "документов MS Office.\n Maven + plugin development, Ant, Apache Commons, Spring security, " +
//                        "Spring MVC, Tomcat,WSO2, " +
//                        "xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh" +
//                        " tunnels, PL/Python"));
//        exampleCompanyList.add(createOnePeriodCompany("Luxoft (Deutsche Bank)", 2014, 10,
//                2016, 1, "Старший разработчик (backend)", "Проектирование и " +
//                        "разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, " +
//                        "Guava, \n Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1," +
//                        " OAuth2, JWT SSO."));
//        exampleCompanyList.add(createOnePeriodCompany("Java Online Projects", 2013, 10,
//                2022, 9, "Автор проекта.", "Создание, организация и" +
//                        " проведение Java онлайн проектов и стажировок."));
//        resume.addSection(SectionType.EXPERIENCE, new CompanySection(exampleCompanyList));
//        exampleCompanyList = new ArrayList<>();
//        exampleCompanyList.add(createOnePeriodCompany("Заочная физико-техническая школа при МФТИ",
//                1984, 9, 1987, 6, "Закончил с отличием", null));
//// Два периода
//        List<Period> spUniversePeriodList = new ArrayList<>();
//        Period spUniversePeriod = new Period(LocalDate.of(1987, 9, 1),
//                LocalDate.of(1993, 7, 1), "Инженер (программист Fortran, C)", null);
//        spUniversePeriodList.add(spUniversePeriod);
//        spUniversePeriod = new Period(LocalDate.of(1993, 9, 1),
//                LocalDate.of(1996, 7, 1), "Аспирантура (программист С, С++)", null);
//        spUniversePeriodList.add(spUniversePeriod);
//        Company spUniverse = new Company("Санкт-Петербургский национальный исследовательский университет " +
//                "информационных технологий, механики и оптики", spUniversePeriodList);
//        exampleCompanyList.add(spUniverse);
//// Двухпериодная компания закончилась
//        exampleCompanyList.add(createOnePeriodCompany("Alcatel",
//                1997, 9, 1998, 3, "6 месяцев обучения цифровым телефонным" +
//                        " сетям (Москва)", null));
//        exampleCompanyList.add(createOnePeriodCompany("Siemens AG",
//                2005, 1, 2005, 4, "3 месяца обучения мобильным IN сетям (Берлин)",
//                null));
//        exampleCompanyList.add(createOnePeriodCompany("Luxoft",
//                2011, 3, 2011, 4, "Курс 'Объектно-ориентированный анализ ИС. " +
//                        "Концептуальное моделирование на UML.'",
//                null));
//        exampleCompanyList.add(createOnePeriodCompany("Coursera",
//                2013, 3, 2013, 5, "'Functional Programming Principles " +
//                        "in Scala' by Martin Odersky",
//                null));
//        resume.addSection(SectionType.EDUCATION, new CompanySection(exampleCompanyList));
        return resume;
    }

    public static void printResume(Resume resume) {
        // Выпускаем кракена
        System.out.println("Резюме: " + resume.getFullName() + " / индентификатор " + resume.getUuid());
        System.out.println();
        System.out.println("Контакты:");
        for (ContactType type : ContactType.values()) {
            if (!(resume.getContact(type) == null)) {
                System.out.println(type.getTitle() + " : " + resume.getContact(type));
            }
        }
        for (SectionType type : SectionType.values()) {
            if (!(resume.getSection(type) == null)) {
                System.out.println("\n >>> " + type.getTitle() + " <<<");
                // Честно говоря, я пока не разобрался как проверить соотетствие типа секции, поэтому проверяю описание.
                if (type.getTitle().equals("Образование") | type.getTitle().equals("Опыт работы")) {
                    System.out.println(resume.getSection(type));
                } else {
                    System.out.println(stringToText(String.valueOf(resume.getSection(type))));
                }
            }
        }
    }

    public static String stringToText(String text) {
        StringBuilder newText = new StringBuilder(text);
        int tab = 180;
        for (int i = 0; i < text.length(); i++) {
            if (i % (tab + 1) == 0) {
                newText.insert(i, "\n");
            }
        }
        return newText.toString();
    }

    public static Company createOnePeriodCompany(String companyName, int startYear, int startMonth,
                                                 int endYear, int endMonth, String title, String description) {
        Period examplePeriod = new Period(LocalDate.of(startYear, startMonth, 1),
                LocalDate.of(endYear, endMonth, 1), title, description);
        List<Period> examplePeriodList = new ArrayList<>();
        examplePeriodList.add(examplePeriod);
        return new Company(companyName, examplePeriodList);
    }
}
