package com.demo.app.configuration.paging;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableValueFactoryProvider implements ValueFactoryProvider {

	private final ServiceLocator locator;

	@Inject
	public PageableValueFactoryProvider(ServiceLocator locator) {
		this.locator = locator;
	}

	@Override
	public Factory<?> getValueFactory(Parameter parameter) {
		if (parameter.getRawType() == Pageable.class && parameter.isAnnotationPresent(Pagination.class)) {
			return new PageableValueFactory(locator);
		}
		return null;
	}

	@Override
	public PriorityType getPriority() {
		return Priority.NORMAL;
	}

	private static class PageableValueFactory extends AbstractContainerRequestValueFactory<Pageable> {

		@QueryParam("page")
		@DefaultValue("0")
		Integer page;

		@QueryParam("size")
		@DefaultValue("1000")
		Integer size;

		@QueryParam("sort")
		List<String> sort = new ArrayList<>();

		private final ServiceLocator locator;

		private PageableValueFactory(ServiceLocator locator) {
			this.locator = locator;
		}

		@Override
		public Pageable provide() {
			locator.inject(this);
			List<Sort.Order> orders = new ArrayList<>();
			// Order by id by default
			if (sort.isEmpty()) {
				sort.add("id,ASC");
			}
			for (String propOrder : sort) {
				String[] propOrderSplit = propOrder.split(",");
				String property = propOrderSplit[0];
				if (propOrderSplit.length == 1) {
					orders.add(new Sort.Order(property));
				} else {
					Sort.Direction direction = Sort.Direction.fromStringOrNull(propOrderSplit[1]);
					orders.add(new Sort.Order(direction, property));
				}
			}
			return new PageRequest(page, size, new Sort(orders));
		}
	}
}
