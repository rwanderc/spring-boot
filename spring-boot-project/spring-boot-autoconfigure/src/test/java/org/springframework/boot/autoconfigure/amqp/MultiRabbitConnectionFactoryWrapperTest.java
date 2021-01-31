package org.springframework.boot.autoconfigure.amqp;

import org.junit.Assert;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

@ExtendWith(MockitoExtension.class)
class MultiRabbitConnectionFactoryWrapperTest {

	private static final String DUMMY_KEY = "dummy-key";

	@Mock
	private ConnectionFactory connectionFactory;

	@Mock
	private SimpleRabbitListenerContainerFactory containerFactory;

	@Mock
	private RabbitAdmin rabbitAdmin;

	private final MultiRabbitConnectionFactoryWrapper wrapper = new MultiRabbitConnectionFactoryWrapper();

	@Test
	void shouldGetDefaultConnectionFactory() {
		this.wrapper.setDefaultConnectionFactory(this.connectionFactory);
		Assert.assertSame(this.connectionFactory, this.wrapper.getDefaultConnectionFactory());
	}

	@Test
	void shouldSetNullDefaultConnectionFactory() {
		this.wrapper.setDefaultConnectionFactory(null);
		Assert.assertNull(this.wrapper.getDefaultConnectionFactory());
	}

	@Test
	void shouldAddConnectionFactory() {
		this.wrapper.addConnectionFactory(DUMMY_KEY, this.connectionFactory);
		Assert.assertSame(this.connectionFactory, this.wrapper.getConnectionFactories().get(DUMMY_KEY));
	}

	@Test
	void shouldNotAddNullConnectionFactory() {
		final ThrowingRunnable runnable = () -> this.wrapper.addConnectionFactory(DUMMY_KEY, null);
		Assert.assertThrows("ConnectionFactory may not be null", IllegalArgumentException.class, runnable);
	}

	@Test
	void shouldNotAddConnectionFactoryWithEmptyKey() {
		final ThrowingRunnable runnable = () -> this.wrapper.addConnectionFactory("", this.connectionFactory);
		Assert.assertThrows("Key may not be null or empty", IllegalArgumentException.class, runnable);
	}

}
