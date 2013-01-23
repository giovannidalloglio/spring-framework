/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.test.context.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.Colour;
import org.springframework.beans.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration tests which verify the behavior of a class whose configuration
 * is divided in:
 * <ul>
 * <li>a @{@link SecondAnnotation} annotation (which declares a @{@link ContextConfiguration})</li>
 * <li>a @{@link ThirdAnnotation} annotation (which is annotated in the @{@link SecondAnnotation} and declares a @{@link ContextConfiguration})</li>
 * </ul>
 *  The ApplicationContext is divided in that files, and the declared beans depends on each others.
 * @author Giovanni Dall'Oglio Risso
 * @since 3.2.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SecondAnnotation
public class ExternalAnnotationTest {

	@Autowired
	private Employee employee;

	@Autowired
	private Colour colour;

	@Test
	public void verifyExtendedAnnotationAutowiredFields() {

		assertNotNull("The colour field should have been autowired.", this.colour);
		assertEquals("BLUE", this.colour.getLabel());

		assertNotNull("The employee field should have been autowired.", this.employee);
		assertEquals("Giovanni", this.employee.getName());

		assertNotNull("The colour field should have been autowired in employee.", this.employee.getFavouriteColour());
		assertEquals(this.colour.getLabel(), this.employee.getFavouriteColour().getLabel());
	}

}
