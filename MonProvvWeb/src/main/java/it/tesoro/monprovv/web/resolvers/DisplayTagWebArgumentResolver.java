package it.tesoro.monprovv.web.resolvers;

import javax.servlet.http.HttpServletRequest;

import it.tesoro.monprovv.annotations.PagingAndSorting;
import it.tesoro.monprovv.dto.DisplayTagPagingAndSorting;

import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.util.WebUtils;

public class DisplayTagWebArgumentResolver implements WebArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest nativeWebRequest) throws Exception {
		// Get the annotation       
		PagingAndSorting annotation = 
				methodParameter.getParameterAnnotation(PagingAndSorting.class);

		if (annotation != null) {
			HttpServletRequest request = 
					(HttpServletRequest) nativeWebRequest.getNativeRequest();
			
			if (WebUtils.getParametersStartingWith(request, "d-").size() > 0) { 

				String page = request.getParameter(new ParamEncoder(annotation.tableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));
				String sortColumn = request.getParameter(new ParamEncoder(annotation.tableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
				String sortOrder = request.getParameter(new ParamEncoder(annotation.tableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));

				int p =  StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
				sortColumn = StringUtils.isNotBlank(sortColumn) ? sortColumn : null;
				sortOrder = StringUtils.isNotBlank(sortOrder) ? ("1".equals(sortOrder) ? "ASC" : "DESC") : null;
				
				DisplayTagPagingAndSorting object = new DisplayTagPagingAndSorting();
				object.setPage(p);
				object.setSortColumn(sortColumn);
				object.setSortOrder(sortOrder);
				return object;

			} else {
				return null;
			}
		}

		return UNRESOLVED;
	}

}
