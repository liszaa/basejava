package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        // contacts
        resume.addContact(ContactType.TELEPHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "Профиль LinkedIn");
        resume.addContact(ContactType.GITHUB, "Профиль GitHub");
        resume.addContact(ContactType.STACKOVERFLOW, "Профиль Stackoverflow");
        resume.addContact(ContactType.HOMEPAGE, "Домашняя страница");

        // Section OBJECTIVE("Позиция")
        SingleLineSection objective = new SingleLineSection("Ведущий стажировок и корпоративного обучения по " +
                                                            "Java Web и Enterprise технологиям");
        resume.addSection(SectionType.OBJECTIVE, objective);

        // Section PERSONAL("Личные качества")
        SingleLineSection personal = new SingleLineSection("Аналитический склад ума, сильная логика, " +
                                                            "креативность, инициативность. Пурист кода и архитектуры");
        resume.addSection(SectionType.PERSONAL, personal);

        // Section ACHIEVEMENT("Достижения")
        String achievement1 = "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                                "\"Многомодульный maven.\n Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                                "Удаленное взаимодействие (JMS/AKKA)\"\n. Организация онлайн стажировок и ведение " +
                                "проектов. Более 1000 выпускников.";
        String achievement2 = "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                                "Интеграция с Twilio,\n DuoSecurity, Google Authenticator, Jira, Zendesk.";
        String achievement3 = "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                                "Интеграция с 1С, Bonita BPM,\n CMIS, LDAP. Разработка приложения управления окружением " +
                                "на стеке: Scala/Play/Anorm/JQuery. Разработка SSO\n аутентификации и авторизации " +
                                "различных ERP модулей, интеграция CIFS/SMB java сервера.";
        String achievement4 = "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                                "Spring-MVC, GWT, ExtGWT (GXT),\n Commet, HTML5, Highstock для алгоритмического трейдинга.";
        String achievement5 = "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных " +
                                "сервисов (SOA-base архитектура,\n JAX-WS, JMS, AS Glassfish). Сбор статистики " +
                                "сервисов и информации о состоянии через систему мониторинга Nagios.\n Реализация " +
                                "онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).";
        String achievement6 = "Реализация протоколов по приему платежей всех основных платежных системы России " +
                                "(Cyberplat, Eport, Chronopay,\n Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.";
        ArrayList<String> achievements = new ArrayList<>();
        achievements.add(achievement1);
        achievements.add(achievement2);
        achievements.add(achievement3);
        achievements.add(achievement4);
        achievements.add(achievement5);
        achievements.add(achievement6);
        BulletedListSection achievement = new BulletedListSection(achievements);
        resume.addSection(SectionType.ACHIEVEMENT, achievement);

        // Section QUALIFICATIONS("Квалификация")
        String qualification1 = "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2";
        String qualification2 = "Version control: Subversion, Git, Mercury, ClearCase, Perforce";
        String qualification3 = "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,";
        String qualification4 = "MySQL, SQLite, MS SQL, HSQLDB";
        String qualification5 = "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,";
        String qualification6 = "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,";
        String qualification7 = "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                                "(MVC, Security, Data, Clouds, Boot), JPA\n (Hibernate, EclipseLink), Guice, " +
                                "GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, J" +
                                "Unit,\n Selenium (htmlelements).";
        String qualification8 = "Python: Django.";
        String qualification9 = "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js";
        String qualification10 = "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka";
        String qualification11 = "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX," +
                                    " SAX, DOM, XSLT, MDB, JMX, JDBC,\n JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, " +
                                    "ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.";
        String qualification12 = "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,";
        String qualification13 = "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway," +
                                    " Nagios, iReport, OpenCmis, Bonita,\n pgBouncer.";
        String qualification14 = "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования," +
                                    " архитектурных шаблонов, UML,\n функционального программирования";
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
        resume.addSection(SectionType.QUALIFICATIONS, qualification);


        // Section EXPERIENCE("Опыт работы")
        Organization organization1 = new Organization(
                "Java Online Projects",
                "http://javaops.ru/",
                new Organization.Position(
                        "Автор проекта",
                        "Создание, организация и проведение Java онлайн проектов и стажировок.",
                        LocalDate.of(2013, 10, 1),
                        LocalDate.now()
                )
        );

        Organization organization2 = new Organization(
                "Wrike",
                null,
                new Organization.Position(
                    "Старший разработчик (backend)",
                    null,
                    LocalDate.of(2014, 10, 01),
                    LocalDate.of(2016, 1, 1)
                )
        );

        Organization organization3 = new Organization(
                "RIT Center",
                null,
                new Organization.Position(
                        "Java архитектор",
                        "организация процесса разработки " +
                        "системы erp для разных окружений: релизная политика, версионирование,\n" +
                        "ведение ci (jenkins), миграция базы (кастомизация flyway), конфигурирование системы " +
                        "(pgboucer, nginx),\n aaa via sso. архитектура бд и серверной части системы. разработка " +
                        "интергационных сервисов: cmis,\n bpmn2, 1c (webservices), сервисов общего назначения " +
                        "(почта, экспорт в pdf, doc, html). интеграция alfresco\n jlan для online редактирование из" +
                        " браузера документов ms office. maven + plugin development, ant, apache\n commons, spring " +
                        "security, spring mvc, tomcat,wso2, xcmis, opencmis, bonita, python scripting, unix shell\n" +
                        "remote scripting via ssh tunnels, pl/python",
                        LocalDate.of(2012, 1, 1),
                        LocalDate.of(2014, 10, 1)
                )
        );

        Organization organization4 = new Organization(
                "Luxoft (Deutsche Bank)",
                "https://www.luxoft.ru/",
                new Organization.Position(
                        "Ведущий программист",
                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, " +
                        "GWT, Jasper,\n Oracle). Реализация клиентской и серверной части CRM. Реализация" +
                        " RIA-приложения для администрирования,\n мониторинга и анализа результатов в области " +
                        "алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT,\n ExtGWT (GXT), Highstock, Commet, " +
                        "HTML5.",
                    LocalDate.of(2010, 12, 1),
                    LocalDate.of(2012, 4, 1)
                )
        );

        Organization organization5 = new Organization(
                "Yota",
                "https://www.yota.ru/",
                new Organization.Position(
                        "Ведущий специалист",
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                        "(GlassFish v2.1, v3, OC4J,\n EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                        "Реализация администрирования, статистики и\n мониторинга фреймворка. " +
                        "Разработка online JMX клиента (Python/ Jython, Django, ExtJS)",
                        LocalDate.of(2008, 6, 1),
                        LocalDate.of(2010, 12, 1)
                )
        );

        Organization organization6 = new Organization(
                "Enkata",
                "http://enkata.com/",
                new Organization.Position(
                        "Разработчик ПО",
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS)" +
                                    " частей кластерного\n J2EE приложения (OLAP, Data mining).",
                        LocalDate.of(2007, 3, 1),
                        LocalDate.of(2008, 6, 1)
                )
        );


        Organization organization7 = new Organization(
                "Siemens AG",
                "https://www.siemens.com/ru/ru/home.html",
                new Organization.Position("Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО " +
                        "на мобильной IN\n платформе Siemens @vantage (Java, Unix).",
                LocalDate.of(2007, 1, 1),
                LocalDate.of(2007, 2, 1)
                )
        );

        Organization organization8 = new Organization("Alcatel","http://www.alcatel.ru/",
                new Organization.Position("Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).",
                LocalDate.of(1997, 9, 1),
                LocalDate.of(2005, 1, 1)
                )
        );

        OrganizationSection experiences = new OrganizationSection(organization1, organization2, organization3, organization4,
                                                        organization5, organization6, organization7, organization8);
        resume.addSection(SectionType.EXPERIENCE, experiences);


        // Section EDUCATION("Образование")
        Organization organ1 = new Organization(
                "Coursera",
                "https://www.coursera.org/course/progfun",
                new Organization.Position(
                        "\"Functional Programming Principles in Scala\" by Martin Odersky",
                        "",
                        LocalDate.of(2013, 1, 1),
                        LocalDate.of(2013, 5, 1)
                )
        );

        Organization organ2 = new Organization(
                "Luxoft",
                "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                   new Organization.Position(
                           "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                           "",
                           LocalDate.of(2011, 3, 1),
                           LocalDate.of(2011, 4, 1)
                   )
        );

        Organization organ3 = new Organization(
                "Siemens AG",
                "http://www.siemens.ru/",
                new Organization.Position(
                        "3 месяца обучения мобильным IN сетям (Берлин)",
                        "",
                        LocalDate.of(2005, 1, 1),
                        LocalDate.of(2005, 4, 1)
                )
        );

        Organization organ4 = new Organization(
                "Alcatel",
                "http://www.alcatel.ru/",
                new Organization.Position(
                        "6 месяцев обучения цифровым телефонным сетям (Москва)",
                        "",
                        LocalDate.of(1997, 9, 1),
                        LocalDate.of(1998, 3, 1))
        );

        Organization organ5 = new Organization(
                "Санкт-Петербургский национальный исследовательский университет информационных " +
                        "технологий, механики и оптики",
                "http://www.ifmo.ru/",
                new Organization.Position(
                        "Аспирантура (программист С, С++)",
                        "",
                        LocalDate.of(1993, 9, 1),
                                LocalDate.of(1996, 7, 1)),
                new Organization.Position(
                        "Инженер (программист Fortran, C)",
                        "",
                        LocalDate.of(1987, 9, 1),
                        LocalDate.of(1993, 7, 1)
                )
        );


        Organization organ6 = new Organization(
                "Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru/",
                new Organization.Position(
                        "Закончил с отличием",
                        "",
                        LocalDate.of(1984, 9, 1),
                        LocalDate.of(1987, 6, 1)
                )
        );

        OrganizationSection educations = new OrganizationSection(organ1, organ2, organ3, organ4, organ5, organ6);
        resume.addSection(SectionType.EDUCATION, educations);

        System.out.println(resume.getFullName());
        System.out.println(" ");

        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            System.out.println(entry.getKey().getTitle() + " : " + entry.getValue());
        }

        for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(entry.getKey().getTitle());
            System.out.println(entry.getValue().toString());
        }
    }

    public static Resume initResume(String uuid, String fullName) {

        Resume resume = new Resume(uuid, fullName);

        // contacts
        resume.addContact(ContactType.TELEPHONE, "Телефон " + fullName + " : " + uuid);
        resume.addContact(ContactType.SKYPE, "Skype " + fullName + " : " + uuid);
        resume.addContact(ContactType.EMAIL, "Email " + fullName + " : " + uuid);
        resume.addContact(ContactType.LINKEDIN, "Профиль LinkedIn " + fullName + " : " + uuid);
        resume.addContact(ContactType.GITHUB, "Профиль GitHub " + fullName + " : " + uuid);
        resume.addContact(ContactType.STACKOVERFLOW, "Профиль Stackoverflow " + fullName + " : " + uuid);
        resume.addContact(ContactType.HOMEPAGE, "Домашняя страница " + fullName + " : " + uuid);

        // Section OBJECTIVE("Позиция")
        SingleLineSection objective = new SingleLineSection("Позиция " + fullName + " : " + uuid);
        resume.addSection(SectionType.OBJECTIVE, objective);
//
//        // Section PERSONAL("Личные качества")
        SingleLineSection personal = new SingleLineSection("Личные качества " + fullName + " : " + uuid);
        resume.addSection(SectionType.PERSONAL, personal);
//
//        // Section ACHIEVEMENT("Достижения")
        BulletedListSection achievement = new BulletedListSection("Достижение1 " + fullName + " : " + uuid,
                                                                  "Достижение2 " + fullName + " : " + uuid,
                                                                  "Достижение3 " + fullName + " : " + uuid);
        resume.addSection(SectionType.ACHIEVEMENT, achievement);
//
//        // Section QUALIFICATIONS("Квалификация")
        BulletedListSection qualification = new BulletedListSection("Квалификация1 " + fullName + " : " + uuid,
                                                                    "Квалификация2 " + fullName + " : " + uuid,
                                                                    "Квалификация3 " + fullName + " : " + uuid);
        resume.addSection(SectionType.QUALIFICATIONS, qualification);
//
        // Section EXPERIENCE("Опыт работы")
        Organization organization1 = new Organization(
                "Организация1",
                null,
                new Organization.Position("Должность в организации1 " + fullName + " : " + uuid,
                        null,
                         LocalDate.of(2013, 10, 1),
                         LocalDate.now()
                )
        );

        Organization organization2 = new Organization(
                "Организация2 ",
                null,
                 new Organization.Position(
                         "Должность в организации2 " + fullName + " : " + uuid,
                         "Описание организации2",
                         LocalDate.of(2014, 10, 01),
                         LocalDate.of(2016, 1, 1)
                 )
        );

        Organization organization3 = new Organization(
                "Организация3",
                null,
                new Organization.Position(
                        "Должность в организации3 " + fullName + " : " + uuid,
                        "Описание организации2",
                        LocalDate.of(2012, 1, 1),
                        LocalDate.of(2014, 10, 1)
                )
        );

        OrganizationSection experiences = new OrganizationSection(organization1, organization2, organization3);
        resume.addSection(SectionType.EXPERIENCE, experiences);
//
        // Section EDUCATION("Образование")
        Organization organ1 = new Organization(
                "Обучение1",
                null,
                new Organization.Position(
                        "Описание обучения1 " + fullName + " : " + uuid,
                         null,
                        LocalDate.of(2013, 10, 1),
                        LocalDate.now()
                )
        );

        Organization organ2 = new Organization(
                "Обучение2",
                null,
                new Organization.Position(
                        "Описание обучения22 " + fullName + " : " + uuid,
                        null,
                        LocalDate.of(2014, 10, 01),
                        LocalDate.of(2016, 1, 1)
                )
        );

        Organization organ3 = new Organization(
                "Обучение3",
                null,
                new Organization.Position(
                        "Описание обучения3 " + fullName + " : " + uuid,
                        null,
                        LocalDate.of(2012, 1, 1),
                        LocalDate.of(2014, 10, 1)
                )
        );

        OrganizationSection educations = new OrganizationSection(organ1, organ2, organ3);
        resume.addSection(SectionType.EDUCATION, educations);

        return resume;

    }
}