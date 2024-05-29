<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Event Management</title>
	</head>
	<body>
		<h2>Event List</h2>

		<!-- put button: Add Event -->
		<input type="button" value="Add Event" onclick="window.location.href='addEventForm'; return false;"/>
		<a href="/pastEvents">View Past Events</a>
		<!--  add a search box -->
        <form:form action="search" method="POST">
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
        	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=name,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=name"> Asc </a>
				</th>
				<th>Event Description
        	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=description,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=description"> Asc </a>
				</th>
				<th>Event Place
        	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=place,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=place"> Asc </a>
				</th>
				<th>Event Date
        	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=eventDate,desc"> Desc </a>
                    &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=eventDate"> Asc </a>
				</th>
				<th colspan="2">Action</th>
			</tr>
			<c:set var="index" value="${page * 5 + 1}" />
			<!-- loop over and print events -->
			<c:forEach var="event" items="${events }">
				<!-- construct an "update" link with event id -->
				<c:url var="updateLink" value="/event/updateEventForm">
					<c:param name="eventId" value="${event.id}"></c:param>
				</c:url>

				<!-- construct an "delete" link with event id -->
				<c:url var="deleteLink" value="/event/delete">
					<c:param name="eventId" value="${event.id}"></c:param>
				</c:url>

				<tr>
					<td>${index}</td>
					<td>${event.name}</td>
					<td>${event.description}</td>
					<td>${event.place}</td>
					<td>${event.eventDate}</td>
					<td>
						<!-- display the update link -->
						<a href="${updateLink}" class="btn btn-info btn-sm">Update</a>
					</td>
					<td>
						<a href="${deleteLink}" class="btn btn-danger btn-sm" onclick="if (!(confirm('Are you sure you want to delete this event?'))) return false">Delete</a>
					</td>
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
                            &nbsp &nbsp<a href="/search?page=${loop.index}&size=5&theSearchName=${theSearchName}&sort=${sortBy}">${loop.index + 1}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>


	</body>
</html>