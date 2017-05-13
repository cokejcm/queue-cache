package com.demo.app.controller.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import com.demo.app.configuration.aop.SampleAspect;
import com.demo.app.domain.Message;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;

@RunWith(MockitoJUnitRunner.class)
public class LoggerTest {

	@Mock
	private Appender<ILoggingEvent> mockAppender;
	@Captor
	private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

	@Before
	public void setup() {
		final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		logger.addAppender(mockAppender);
	}

	@After
	public void teardown() {
		final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		logger.detachAppender(mockAppender);
	}

	@Test
	public void shouldConcatAndLog() {
		SampleAspect example = new SampleAspect();
		Message m = new Message();
		m.setId("109");
		example.logExecution(null, m);
		// Now verify our logging interactions
		verify(mockAppender).doAppend(captorLoggingEvent.capture());
		final LoggingEvent loggingEvent = captorLoggingEvent.getValue();
		// Check log level is correct
		assertThat(loggingEvent.getLevel(), is(Level.INFO));
		// Check the message being logged is correct
		assertThat(loggingEvent.getFormattedMessage(), is("Reading id: 109"));
	}
}