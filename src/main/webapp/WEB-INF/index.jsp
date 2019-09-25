<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/material-icons.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/materialize.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>

<body>

    <nav class="nav-extended green accent-4">
        <div class="nav-wrapper">
            <a href="#" class="brand-logo">БНТУ</a>
        </div>
        <div class="nav-content">
            <ul class="tabs tabs-transparent">
                <li class="tab col s3"><a class="active" href="#study-data">Данные</a></li>
                <li class="tab"><a href="#study-plan">Учебный план</a></li>
                <li class="tab"><a class="active" href="#study-subjects">Учебные предметы</a></li>
                <li class="tab"><a href="#terms-distribution">Распределение по семестрам</a></li>
                <li class="tab"><a href="#exams-schedule">Расписание экзаменов</a></li>
            </ul>
        </div>
    </nav>

    <div id="study-plan" class="col s12">
        <h4 class="tab-body-header">Учебный план</h4>

        <div class="flex-row">

            <div class="subjects-list">

                <p class="subjects-list-header"><b>Учебные предметы</b></p>

                <ul class="collection sortable connectedSortable">
                    <li class="subject-item collection-item">Математика</li>
                    <li class="subject-item collection-item">ООП</li>
                    <li class="subject-item collection-item">Компьютерная графика</li>
                </ul>
            </div>

            <table class="highlight ">
                <thead>
                    <tr>
                        <th>Название дисциплины</th>
                        <th>Экзаменов</th>
                        <th>Зачетов</th>
                        <th>РГР</th>
                        <th>Контрольных работ</th>
                        <th>Аудиторные занятия</th>
                        <th>Лекции</th>
                        <th>Лабораторные работы</th>
                        <th>Практические занятия</th>
                        <th>Всего</th>
                    </tr>
                </thead>
            </table>
        </div>

    </div>

    <div id="study-subjects" class="col s12">
        <h4 class="tab-body-header">Учебные предметы</h4>

        <div class="flex-row study-subjects-body">
            <div class="subjects-tab subjects-list">

                <p class="subjects-list-header"><b>Учебные предметы</b></p>

                <ul id="subjects-list" class="collection">

                </ul>
            </div>

            <div id="subjects-form" class="subjects-form">
                <p id="subjects-form-header" class="subjects-form-header"><b>Добавление учебного предмета</b></p>
                <div class="subject-name-form-item">
                    <p class="subject-name-caption">Название учебного предмета</p>
                    <input id="subject-name" class="subject-name-field" type="text" align="middle">
                </div>

                <div id="loads-form" class="loads-form-list">

                </div>

                <div id="form-buttons" class="text-center">
                    <a id="add-subject-button" class="center-align waves-effect waves-light btn green accent-4">Добавить</a>
                </div>
            </div>

            <div class="loads-list">
                <p class="loads-list-header"><b>Нагрузки</b></p>

                <ul id="loads-list">

                </ul>
            </div>
        </div>
    </div>
	
	
    <div id="study-data" class="col s12">
        <h4 class="tab-body-header">Данные для заполенния</h4>
        <div class="flex-row study-subjects-body">
          <div class="subjects-tab subjects-list">

              <p class="subjects-list-header"><b>Формы</b></p>

              <ul id="data-list" class="collection">

              </ul>
          </div>

          <div id="subjects-form" class="subjects-form">
              <p id="subjects-form-header" class="subjects-form-header"><b>Таблица</b></p>
              <table class="highlight" id="data-tr-table">
              </table>
          </div>



          <div class="loads-list">
              <p class="loads-list-header"><b>Действия</b></p>


          </div>
        </div>
    </div>




    <div id="terms-distribution" class="col s12">
        <h4 class="tab-body-header">Распределение по семестрам</h4>
    </div>

    <div id="exams-schedule" class="col s12">
        <h4 class="tab-body-header">Расписание экзаменов</h4>

        <div class="flex-row">
            <div class="exams-list ">

                <p class="exams-list-heading"><b>Предметы</b></p>

                <ul class="collection sortable connectedSortable">
                    <li class="exam-item collection-item draggable">Математика</li>
                    <li class="exam-item collection-item draggable">ООП</li>
                    <li class="exam-item collection-item draggable">Компьютерная графика</li>
                </ul>
            </div>

            <div class="exams-schedule">
                <table class="highlight">
                    <thead>
                        <tr>
                            <th class="day-of-week">День недели</th>
                            <th>Группа 1</th>
                            <th>Группа 2</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td>Понедельник</td>
                            <td>
                                <ul class="collection sortable connectedSortable">
                                    <li class="collection-item">8.00
                                        <ul class="collection sortable connectedSortable"></ul>
                                    </li>
                                    <li class="collection-item">10.00
                                        <ul class="collection sortable connectedSortable"></ul>
                                    </li>
                                    <li class="collection-item">12.00
                                        <ul class="collection sortable connectedSortable"></ul>
                                    </li>
                                    <li class="collection-item">14.00
                                        <ul class="collection sortable connectedSortable"></ul>
                                    </li>
                                    <li class="collection-item">16.00
                                        <ul class="collection sortable connectedSortable"></ul>
                                    </li>
                                </ul>
                            </td>

                            <td>
                                <ul class="collection">
                                    <li class="collection-item">8.00
                                        <ul class="collection sortable connectedSortable"></ul>
                                    </li>
                                    <li class="collection-item">10.00
                                        <ul class="collection sortable connectedSortable"></ul>
                                    </li>
                                    <li class="collection-item">12.00
                                        <ul class="collection sortable connectedSortable"></ul>
                                    </li>
                                    <li class="collection-item">14.00
                                        <ul class="collection sortable connectedSortable"></ul>
                                    </li>
                                    <li class="collection-item">16.00
                                        <ul class="collection sortable connectedSortable"></ul>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</body>

</html>