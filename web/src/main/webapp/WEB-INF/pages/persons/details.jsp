<%--
  Created by IntelliJ IDEA.
  User: andreas
  Date: 2/22/15
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<head>
    <title>Person Details</title>
</head>

<body>
  <div class="container">
    <div class="col-lg-12">
      <h1>Edit / Add Person</h1>
      <div class="well well-lg">
        <form:form class="form-horizontal" action="/persons" method="post" modelAttribute="person">
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">ID</span>
              <form:input path="id" class="form-control" readonly="true" />
            </div>
          </div>
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">Firstname</span>
              <form:input path="firstName" class="form-control" placeholder="Please put your firstname here..." />
            </div>
          </div>
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">LastName</span>
              <form:input path="lastName" class="form-control" placeholder="Please put your lastname here..." />
            </div>
          </div>
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">eMail</span>
              <form:input path="eMail" class="form-control" placeholder="Please put your eMail here..." />
            </div>
          </div>
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">birthDate</span>
              <form:input path="birthDate" class="form-control" placeholder="Please put your birthDate here..." />
            </div>
          </div>
          <button type="submit" class="btn btn-primary">Save</button>
          <button type="button" class="btn btn-info" onclick="location.href='/persons';">Abort</button>
        </form:form>
      </div>
    </div>
  </div>
</body>
