<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Event Management</title>
	</head>
	<body>
	<div class="container mt-4">
		<h2 class="bg-success text-center text-white p-3 rounded">Events</h2>
		<h3 class="text-center">Add Event</h3>
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<hr>
				<form:form action="saveEvent" modelAttribute="event" method="POST">
					<div class="form-group">
						<label for="name">Event Name <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:input path="name" name="name" class="form-control"/>
						<form:errors path="name" cssClass="text-danger"/>
					</div>
					<div class="form-group">
						<label for="description">Event Description <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:textarea path="description" name="description" class="form-control"/>
						<form:errors path="description" cssClass="text-danger"/>
					</div>
					<div class="form-group">
						<label for="place">Event Place <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:input path="place" name="place" class="form-control"/>
						<form:errors path="place" cssClass="text-danger"/>
					</div>
                    <div class="form-group">
						<label for="eventDate">Event Date <i class="text-info"><i class="fas fa-asterisk"></i></i></label>
						<form:input type="date" path="eventDate" name="eventDate" format="yyyy/MM/dd"/>
						<form:errors path="eventDate" cssClass="text-danger"/>
					</div>

					<input type="submit" value="Save" class="btn btn-info btn-lg btn-block"/>
				</form:form>
			</div>
		</div>
		<a href="${pageContext.request.contextPath}/event/list" class="badge badge-secondary">Back to List</a>
	</div>
	</body>
</html>