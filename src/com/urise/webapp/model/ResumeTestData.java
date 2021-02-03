package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {

        Map<SectionType, Section> sections = new LinkedHashMap<>();
        Map<ContactType, String> contacts = new LinkedHashMap<>();

        // contacts
        contacts.put(ContactType.TELEPHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "Профиль LinkedIn");
        contacts.put(ContactType.GITHUB, "Профиль GitHub");
        contacts.put(ContactType.STACKOVERFLOW, "Профиль Stackoverflow");
        contacts.put(ContactType.HOMEPAGE, "Домашняя страница");

        //Section OBJECTIVE("Позиция")
        SingleLineSection objective = new SingleLineSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        sections.put(SectionType.OBJECTIVE, objective);

        //Section PERSONAL("Личные качества")
        SingleLineSection personal = new SingleLineSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры");
        sections.put(SectionType.PERSONAL, personal);

        //Section ACHIEVEMENT("Достижения")
        String achievement1 = "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven.\n Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\"\n. Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.";
        String achievement2 = "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio,\n DuoSecurity, Google Authenticator, Jira, Zendesk.";
        String achievement3 = "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM,\n CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO\n аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.";
        String achievement4 = "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT),\n Commet, HTML5, Highstock для алгоритмического трейдинга.";
        String achievement5 = "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура,\n JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios.\n Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).";
        String achievement6 = "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay,\n Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.";
        ArrayList<String> achievements = new ArrayList<>();
        achievements.add(achievement1);
        achievements.add(achievement2);
        achievements.add(achievement3);
        achievements.add(achievement4);
        achievements.add(achievement5);
        achievements.add(achievement6);
        BulletedListSection achievement = new BulletedListSection(achievements);
        sections.put(SectionType.ACHIEVEMENT, achievement);

        //Section QUALIFICATIONS("Квалификация")
        String qualification1 = "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2";
        String qualification2 = "Version control: Subversion, Git, Mercury, ClearCase, Perforce";
        String qualification3 = "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,";
        String qualification4 = "MySQL, SQLite, MS SQL, HSQLDB";
        String qualification5 = "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,";
        String qualification6 = "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,";
        String qualification7 = "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA\n (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit,\n Selenium (htmlelements).";
        String qualification8 = "Python: Django.";
        String qualification9 = "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js";
        String qualification10 = "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka";
        String qualification11 = "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC,\n JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.";
        String qualification12 = "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,";
        String qualification13 = "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita,\n pgBouncer.";
        String qualification14 = "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML,\n функционального программирования";
        String qualification15 = "Родной русский, английский \"upper intermediate\"";

        ArrayList<String> qualifications = new ArrayList<>();
        qualifications.add(qualification1);
        qualifications.add(qualification2);
        qualifications.add(qualification3);
        qualifications.add(qualification4);
        qualifications.add(qualification5);
        qualifications.add(qualification6);
        qualifications.add(qualification7);
        qualifications.add(qualification8);
        qualifications.add(qualification9);
        qualifications.add(qualification10);
        qualifications.add(qualification11);
        qualifications.add(qualification12);
        qualifications.add(qualification13);
        qualifications.add(qualification14);
        qualifications.add(qualification15);
        BulletedListSection qualification = new BulletedListSection(qualifications);
        sections.put(SectionType.QUALIFICATIONS, qualification);

        //Section EXPERIENCE("Опыт работы")
        PeriodInfo period1 = new PeriodInfo("Java Online Projects",
                "Автор проекта." +
                        "\n Создание, организация и проведение Java онлайн проектов и стажировок.",
                LocalDate.of(2013, 10, 1), LocalDate.now());

        PeriodInfo period2 = new PeriodInfo("Wrike",
                "Старший разработчик (backend)\n" +
                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring,\n" +
                        "MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                LocalDate.of(2014, 10, 01), LocalDate.of(2016, 1, 1));

        PeriodInfo period3 = new PeriodInfo("RIT Center",
                "Java архитектор\n" +
                        "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование,\n" +
                        "ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx),\n" +
                        "AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS,\n" +
                        "BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco\n" +
                        "JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache\n" +
                        "Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell\n" +
                        "remote scripting via ssh tunnels, PL/Python",
                LocalDate.of(2012, 1, 1), LocalDate.of(2014, 10, 1));

        PeriodInfo period4 = new PeriodInfo("Luxoft (Deutsche Bank)",
                "Ведущий программист\n" +
                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper,\n" +
                        "Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования,\n" +
                        "мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT,\n" +
                        "ExtGWT (GXT), Highstock, Commet, HTML5.",
                LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1));

        PeriodInfo period5 = new PeriodInfo("Yota",
                "Ведущий специалист\n" +
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J,\n" +
                        "EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и\n" +
                        "мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)",
                LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1));

        PeriodInfo period6 = new PeriodInfo("Enkata",
                "Разработчик ПО\n" +
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного\n" +
                        "J2EE приложения (OLAP, Data mining).",
                LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1));

        PeriodInfo period7 = new PeriodInfo("Siemens AG",
                "Разработчик ПО\n" +
                        "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN\n" +
                        "платформе Siemens @vantage (Java, Unix).",
                LocalDate.of(2007, 1, 1), LocalDate.of(2007, 2, 1));

        PeriodInfo period8 = new PeriodInfo("Alcatel",
                "Инженер по аппаратному и программному тестированию\n" +
                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).",
                LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1));

        ArrayList<PeriodInfo> experiences = new ArrayList<>();
        experiences.add(period1);
        experiences.add(period2);
        experiences.add(period3);
        experiences.add(period4);
        experiences.add(period5);
        experiences.add(period6);
        experiences.add(period7);
        experiences.add(period8);

        PeriodSection experience = new PeriodSection(experiences);
        sections.put(SectionType.EXPERIENCE, experience);

        //Section EDUCATION("Образование")

        PeriodInfo periodInfo1 = new PeriodInfo("Coursera",
                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                LocalDate.of(2013, 1, 1), LocalDate.of(2013, 5, 1));

        PeriodInfo periodInfo2 = new PeriodInfo("Luxoft",
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1));

        PeriodInfo periodInfo3 = new PeriodInfo("Siemens AG",
                "3 месяца обучения мобильным IN сетям (Берлин)",
                LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1));

        PeriodInfo periodInfo4 = new PeriodInfo("Alcatel",
                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1));

        PeriodInfo periodInfo5 = new PeriodInfo("Санкт-Петербургский национальный исследовательский университет информационных технологий,\n" +
                " механики и оптики",
                "Аспирантура (программист С, С++)",
                LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1));

        PeriodInfo periodInfo6 = new PeriodInfo("",
                "Инженер (программист Fortran, C)",
                LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1));

        PeriodInfo periodInfo7 = new PeriodInfo("Заочная физико-техническая школа при МФТИ",
                "Закончил с отличием",
                LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1));

        ArrayList<PeriodInfo> educations = new ArrayList<>();
        educations.add(periodInfo1);
        educations.add(periodInfo2);
        educations.add(periodInfo3);
        educations.add(periodInfo4);
        educations.add(periodInfo5);
        educations.add(periodInfo6);
        educations.add(periodInfo7);

        PeriodSection education = new PeriodSection(educations);

        sections.put(SectionType.EDUCATION, education);

        Resume resume = new Resume("Григорий Кислин", contacts, sections);


        System.out.println(resume.getFullName());
        System.out.println(" ");

        for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey().getTitle() + " : " + entry.getValue());
        }

        for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(entry.getKey().getTitle());
            entry.getValue().asText();
        }
    }

}
