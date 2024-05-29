<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Event Management</title>
	</head>
	<body>
		<h2>Past Events</h2>

        <a href="${pageContext.request.contextPath}/event/list" class="badge badge-secondary">Back to List</a>
		<!--  add a search box -->
        <form:form action="pastEvents" method="POST">
			    <input type="text" placeholder="Search By Event Name/Place" name="theSearchName" value = "${theSearchName}">
			    <input type="submit" value="Search"/>
			</div>
        </form:form>

		<!-- add html table here -->
		<h3>Upcoming Events</h3>
		<table border="1">
			<tr>
				<th>S No.</th>
				<th>Event Name
        	        &nbsp &nbsp <a href= "/pastEvents?page=0&size=5&theSearchName=${theSearchName}&sort=name,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/pastEvents?page=0&size=5&theSearchName=${theSearchName}&sort=name"> Asc </a>
				</th>
				<th>Event Description
        	        &nbsp &nbsp <a href= "/pastEvents?page=0&size=5&theSearchName=${theSearchName}&sort=description,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/pastEvents?page=0&size=5&theSearchName=${theSearchName}&sort=description"> Asc </a>
				</th>
				<th>Event Place
        	        &nbsp &nbsp <a href= "/pastEvents?page=0&size=5&theSearchName=${theSearchName}&sort=place,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/pastEvents?page=0&size=5&theSearchName=${theSearchName}&sort=place"> Asc </a>
				</th>
				<th>Event Date
        	        &nbsp &nbsp <a href= "/pastEvents?page=0&size=5&theSearchName=${theSearchName}&sort=eventDate,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/pastEvents?page=0&size=5&theSearchName=${theSearchName}&sort=eventDate"> Asc </a>
				</th>
			</tr>
			<c:set var="index" value="${page * 5 + 1}" />
			<!-- loop over and print events -->
			<c:forEach var="event" items="${events }">
				<tr>
					<td>${index}</td>
					<td>${event.name}</td>
					<td>${event.description}</td>
					<td>${event.place}</td>
					<td>${event.eventDate}</td>
				<c:set var="index" value="${index + 1}" />
				</tr>
			</c:forEach>
		</table>

        <br><br>
        	<c:choose>
                <c:when test="${totalPage == 0}">
                    No Record Found
                </c:when>
                <c:otherwise>
                    <c:forEach begin="0" end="${totalPage-1}" varStatus="loop">
                            &nbsp &nbsp<a href="/pastEvents?page=${loop.index}&size=5&theSearchName=${theSearchName}&sort=${sortBy}">${loop.index + 1}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>


	</body>
</html>