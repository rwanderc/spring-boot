package org.springframework.boot.autoconfigure.amqp;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

/**
 * Tests to ensure compatibility of {@link MultiRabbitAutoConfiguration} with {@link RabbitAutoConfiguration}
 * if MultiRabbit is not enabled.
 *
 * @author Wander Costa
 */
class MultiRabbitAutoConfigurationCompatibilityTest extends RabbitAutoConfigurationTests {

	MultiRabbitAutoConfigurationCompatibilityTest() {
		super.contextRunner = new ApplicationContextRunner().withConfiguration(
				AutoConfigurations.of(MultiRabbitAutoConfiguration.class, RabbitAutoConfiguration.class));
	}
}