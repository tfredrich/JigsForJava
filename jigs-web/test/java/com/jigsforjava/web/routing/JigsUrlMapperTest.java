/*
    Copyright 2008, Strategic Gains, Inc.

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package com.jigsforjava.web.routing;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.jigsforjava.web.context.ViewDefinition;
import com.jigsforjava.web.controller.MultiActionController;


/**
 * @author toddf
 * @since Sep 2, 2008
 */
public class JigsUrlMapperTest
{
	private JigsUrlMapper mapper;
	private JigsUrlMapper m2;

	@Before
	public void setup()
	{
		mapper = new JigsUrlMapper();
		mapper.setControllerPackageBase("com.jigsforjava.web");

		m2 = new JigsUrlMapper();
		m2.setControllerPackageBase("com.jigsforjava.web");
		m2.setActionSuffix("html");
	}

	@Test
	public void testGetControllerName()
	{
		String controller = mapper.getControllerName("/the_test/edit.action");
		assertEquals("com.jigsforjava.web.TheTestController", controller);

		controller = mapper.getControllerName("basic_admin/big_main/ticket_counter/add_to_cart.action");
		assertEquals("com.jigsforjava.web.basicAdmin.bigMain.TicketCounterController", controller);

		controller = mapper.getControllerName("basic_admin/big_main/ticket_counter/");
		assertEquals("com.jigsforjava.web.basicAdmin.bigMain.TicketCounterController", controller);

		controller = mapper.getControllerName("basic_admin/main/ticket_counter");
		assertEquals("com.jigsforjava.web.basicAdmin.main.TicketCounterController", controller);

		controller = m2.getControllerName("basic_admin/big_main/ticket_counter/add_to_cart.html");
		assertEquals("com.jigsforjava.web.basicAdmin.bigMain.TicketCounterController", controller);

		controller = m2.getControllerName("basic_admin/main/ticket_counter");
		assertEquals("com.jigsforjava.web.basicAdmin.main.TicketCounterController", controller);
	}

	@Test
	public void testGetActionName()
	{
		String action = mapper.getActionName("/the_test/edit.action");
		assertEquals("Edit", action);

		action = mapper.getActionName("basic_admin/big_main/ticket_counter/add_to_cart.action");
		assertEquals("AddToCart", action);

		action = mapper.getActionName("basic_admin/big_main/ticket_counter/");
		assertEquals("View", action);

		action = mapper.getActionName("basic_admin/main/ticket_counter");
		assertEquals("View", action);

		action = m2.getActionName("basic_admin/big_main/ticket_counter/add_to_cart.html");
		assertEquals("AddToCart", action);

		action = m2.getActionName("basic_admin/main/ticket_counter");
		assertEquals("View", action);
	}
	
	@Test
	public void testToUrl()
	{
		assertEquals("/routing/jigs_url_mapper_test$_test/test_the_stuff.action",
			mapper.toUrl(TestController.class, "TestTheStuff", (Map<String, Object>)null));

		assertEquals("/routing/jigs_url_mapper_test$_test/test_the_stuff.html",
			m2.toUrl(TestController.class, "TestTheStuff", (Map<String, Object>)null));

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("id", Integer.valueOf(45));
		params.put("owner", Integer.valueOf(333));
		params.put("other stuff", "something else");

		assertEquals("/routing/jigs_url_mapper_test$_test/test_the_stuff.action?id=45&owner=333&other+stuff=something+else",
			mapper.toUrl(TestController.class, "TestTheStuff", params));


		assertEquals("/routing/jigs_url_mapper_test$_test/test_the_stuff.html?id=45&owner=333&other+stuff=something+else",
			m2.toUrl(TestController.class, "TestTheStuff", params));
	}
	
	@Test
	public void testToView()
	{
		assertEquals("/routing/jigs_url_mapper_test$_test/edit",
			mapper.toView(TestController.class, "edit"));

		assertEquals("/routing/jigs_url_mapper_test$_test/editThisStuff",
			mapper.toView(TestController.class, "EditThisStuff"));

		assertEquals("/routing/jigs_url_mapper_test$_test/whatDoYouThink",
			mapper.toView(TestController.class, "whatDoYouThink"));
	}

	
	// SECTION: INNER CLASS

	private class TestController
	extends MultiActionController
	{
		// do nothing.
	}
}
