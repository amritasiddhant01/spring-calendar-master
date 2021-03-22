
package io.spring.calendar.release;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for exposing {@link Release Releases} as an iCalendar-format
 * download.
 *
 * @author Amrita Gupta
 */
@RestController
@RequestMapping("/ical")
class ReleaseICalController {
	private static final Logger log = LoggerFactory.getLogger(ReleaseICalController.class);

	@RequestMapping(produces = "text/calendar")
	String calendar() throws ParseException {
		log.info("inside calendar");
		// System.err.println("inside calendar");
		ICalendar calendar = new ICalendar();
		calendar.setExperimentalProperty("X-WR-CALNAME", "Spring Releases");
		calendar.setDescription("The Event Description");
		calendar.addEvent(createEvent());
		return Biweekly.write(calendar).go();
	}

	private VEvent createEvent() {
		log.info("inside createEvent");
		// System.err.println("inside createEvent");
		VEvent event = new VEvent();
		event.setSummary("Rackspace Standup");

		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998");
			event.setDateStart(date);
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}

		return event;
	}

}
