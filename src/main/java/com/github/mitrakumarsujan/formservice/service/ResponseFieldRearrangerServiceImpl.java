package com.github.mitrakumarsujan.formservice.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Service
public class ResponseFieldRearrangerServiceImpl implements ResponseFieldRearrangerService {
	
	@Override
	public void rearrange(FormResponseRequest request) {
		Form form = request.getAssociatedForm();
		FormResponse response = request.getResponse();
		Map<String, Integer> indexedMap = getIndexedMap(form);

		List<Response> responses = response.getResponses();
		responses.sort(new ResponseComparator(indexedMap));
	}

	private Map<String, Integer> getIndexedMap(Form form) {
		MutableInteger index = new MutableInteger();
		return form	.getTemplate()
					.getFields()
					.stream()
					.sequential()
					.map(FormField::getId)
					.collect(Collectors.toMap(Function.identity(), f -> index.getAndIncrement()));
	}

	private static class MutableInteger {
		Integer val;

		MutableInteger() {
			val = Integer.valueOf(0);
		}

		Integer getAndIncrement() {
			return val++;
		}
	}

	private static class ResponseComparator implements Comparator<Response> {

		Map<String, Integer> indexMap;

		ResponseComparator(Map<String, Integer> indexMap) {
			this.indexMap = indexMap;
		}

		@Override
		public int compare(Response o1, Response o2) {
			String o1QuestionId = o1.getQuestionId();
			String o2QuestionId = o2.getQuestionId();

			int index1 = indexMap.get(o1QuestionId);
			int index2 = indexMap.get(o2QuestionId);

			return Integer.compare(index1, index2);
		}

	}
}
