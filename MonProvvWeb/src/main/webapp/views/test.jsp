<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


UNIT TEST

<security:authorize
				access="hasPermission(#provv, 'richiesta')">
	OK				
</security:authorize>