package in.co.madguy.springbootpoc.configuration;

import in.co.madguy.springbootpoc.util.Constants;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

/**
 * This sets timezone after all the beans are loaded and application is in Ready state.
 * Alternatively, TimeZone can also be set in the PostConstruct of the main class.
 */
@Component
public class TimeZoneConfig implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        TimeZone.setDefault(TimeZone.getTimeZone(Constants.USER_TZ));
    }
}
