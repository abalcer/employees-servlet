<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-bordered">
    <thead>
    <tr>
        <th>Name</th>
        <th>Gender</th>
        <th>Birth Date</th>
        <th>Hire Date</th>
        <th>Department</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.name}</td>
            <td>${employee.gender}</td>
            <td>${employee.birthDate}</td>
            <td>${employee.hireDate}</td>
            <td>${employee.department.name}</td>
            <td class="controls">
                <a href="/employee/edit?id=${employee.id}" class="btn btn-default btn-sm edit">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                </a>
                <a href="/employee/delete?id=${employee.id}" class="btn btn-danger btn-sm delete">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="text-center">
    <nav aria-label="Page navigation">
      <ul class="pager">
        <li>
            <c:if test="${page > 1}">
              <a href="/employee?page=${page-1}" aria-label="Previous">
                <span aria-hidden="true">Prev</span>
              </a>
          </c:if>
        </li>
        <li>${page} of ${totalPage}</li>
        <li>
            <c:if test="${page < totalPage}">
              <a href="/employee?page=${page+1}" aria-label="Next">
                <span aria-hidden="true">Next</span>
              </a>
          </c:if>
        </li>
      </ul>
    </nav>
</div>